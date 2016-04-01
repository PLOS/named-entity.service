/*
 * Copyright (c) 2006-2014 by Public Library of Science
 * http://plos.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.plos.namedentity.persist;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableField;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.ringgold.*;
import org.plos.namedentity.persist.db.ringgold.tables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static org.plos.namedentity.api.NedException.ErrorType.ServerError;
import static org.plos.namedentity.persist.db.ringgold.Tables.*;

public final class RinggoldDBServiceImpl implements RinggoldDBService {

  private static final Logger logger = Logger.getLogger(RinggoldDBServiceImpl.class);

  @Autowired @Qualifier("ringgoldDsl") DSLContext context;
  public void setContext(DSLContext context) {
    this.context = context;
  }

  @Override
  public <T> T findById(Integer id, Class<T> clazz) {
    Record r = context.select().from(table(clazz))
                      .where(pkField(clazz).equal(id))
                      .fetchOne();

    return (r != null ? r.into(clazz) : null);
  }

  @Override @SuppressWarnings("unchecked")
  public <T> List<T> findByAttribute(T t) {
    return context.select()
      .from(table(t.getClass()))
      .where( buildWhereClause(t) )
      .fetchInto((Class<T>)t.getClass());
  }

  @Override
  public List<Institution> findByInstitutionName(String searchString) {
    long start = System.nanoTime();

    List<Institution> results = context.select(INSTITUTIONS.fields())
      .from(INSTITUTIONS)
      .leftOuterJoin(SIZES)
      .on(INSTITUTIONS.RINGGOLD_ID.equal(SIZES.RINGGOLD_ID))
      .and(SIZES.SIZE_TYPE.equal("size"))
      .where(institutionNameSearchCondition(searchString))
      .orderBy(SIZES.VALUE.desc().nullsLast())
      .limit(100)
      .fetchInto(Institution.class);

    results = bubbleCountryToTop(results);

    logger.info(String.format("Search:[%s]  Size:%d  Elapsed(ms):%d", searchString,
      results.size(), TimeUnit.MILLISECONDS.convert(System.nanoTime()-start, TimeUnit.NANOSECONDS)));

    return results;
  }

  private List<Institution> bubbleCountryToTop(List<Institution> list) {
    return bubbleCountryToTop(list, "US");
  }

  private List<Institution> bubbleCountryToTop(List<Institution> list, String country) {
    if (list.isEmpty()) { return list; }

    List<Institution> preferred    = new ArrayList<>();
    List<Institution> nonpreferred = new ArrayList<>();

    for(Institution inst : list) {
      if(inst.getCountry().equals(country)){
        preferred.add(inst);
      } else {
        nonpreferred.add(inst);
      }
    }
    preferred.addAll(nonpreferred);
    return preferred;
  }

  private <T> String buildWhereClause(T t) {
    StringBuilder where = new StringBuilder();
    try {
      List<java.lang.reflect.Field> fields = new ArrayList<>();
      fields.addAll( Arrays.asList(t.getClass().getDeclaredFields()) );

      for (java.lang.reflect.Field f : fields) {
        // ignore static member variables
        if (Modifier.isStatic(f.getModifiers())) continue;

        f.setAccessible(true);    // allow access to private members

        Object v = f.get(t);      // get attribute value

        if (v != null) {
          if (where.length() > 0) where.append(" and "); 
          where.append( whereCondition(f.getName(), v, t.getClass()) );
        }
      }
    }
    catch (Exception e) {
      throw new NedException(ServerError, "Problem building filter expression");
    }
    return where.toString();
  }

  private <T> String whereCondition(String field, Object value, Class<T> clazz) {
    String cname = clazz.getCanonicalName();
    if (cname.equals(Institution.class.getCanonicalName())) {
      if (field.equals("name")) {
        return institutionNameSearchCondition((String) value);
      }
    } else {
      throw new UnsupportedOperationException("Unsupported entity " + cname);
    }

    // default handler
    StringBuilder condition = new StringBuilder();
    condition.append( dbFieldName(field) ).append("=");
    if (value instanceof Number || value instanceof Boolean) {
      condition.append(value);
    } else {
      condition.append("'").append(value).append("'");
    }
    return condition.toString();
  }

  protected String dbFieldName(String field) {
    StringBuilder dbfield = new StringBuilder();

    // convert camel case to snake case (ex: ringgoldId -> ringgold_id)
    for (char c : field.toCharArray()) {
      if (Character.isUpperCase(c)) {
        dbfield.append("_"+Character.toLowerCase(c));
      } else {
        dbfield.append(c);
      }
    }
    return dbfield.toString();
  }

  private String institutionNameSearchCondition(String searchString) {
    // ignore case when looking up institutions by name (assumes utf8 char set)
    return String.format(
      "(name LIKE '%s%%' collate utf8_unicode_ci OR name LIKE '%% %s%%' collate utf8_unicode_ci)",
        searchString, searchString);
  }

  /* ---------------------------------------------------------------------- */
  /*  INTERNAL MAP : ENTITY POJO -> { JooqTable, JooqPkFieldForTable }      */
  /* ---------------------------------------------------------------------- */

  static class TablePkPair {
    private final Table      table;
    private final TableField pkField;

    public TablePkPair(Table table, TableField pkField) {
      this.table   = table;
      this.pkField = pkField;
    }
    public Table      table()   { return table;   } 
    public TableField pkField() { return pkField; } 
  }

  /*
   * This map is used internally to associate an entity pojo with a database table
   * and primary key for that table. The database references are JOOQ wrappers
   * (Table and TableField objects).
   *
   * If you ADD or DELETE a table in NED's schema, you will need to update
   * the map populated below. This is done manually and not auto-generated!
   *
   * You can get the table name (static) and primary key (instance var) from 
   * the appropriate class in org.plos.namedentity.persist.db.namedentities.tables
   */

  private static final Map<Class,TablePkPair> entityTableMap;
  static {
    entityTableMap = new ConcurrentHashMap<>();
    entityTableMap.put(Institution.class, new TablePkPair(INSTITUTIONS, INSTITUTIONS.REC_ID));
  }
  private static Table table(Class key) {
    return entityTableMap.get(key).table();
  }
  private static TableField pkField(Class key) {
    return entityTableMap.get(key).pkField();
  }
}
