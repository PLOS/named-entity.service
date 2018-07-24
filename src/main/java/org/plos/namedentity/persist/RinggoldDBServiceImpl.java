/*
 * Copyright (c) 2017 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package org.plos.namedentity.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  private static final Logger logger = LoggerFactory.getLogger(RinggoldDBServiceImpl.class);

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

    WhereObject whereObj = buildWhereClause(t);

    return context.select()
      .from(table(t.getClass()))
      .where(whereObj.clause, whereObj.bindings)
      .fetchInto((Class<T>)t.getClass());
  }

  @Override
  public List<Institution> findByInstitutionName(String searchString) {
    long start = System.nanoTime();

    Institution i = new Institution(); i.setName(searchString);
    WhereObject whereObj = buildWhereClause(i);

    List<Institution> results = context.select(INSTITUTIONS.fields())
      .from(INSTITUTIONS)
      .where(whereObj.clause, whereObj.bindings)
      .orderBy(INSTITUTIONS.REC_ID)
      .limit(100)
      .fetchInto(Institution.class);

    logger.debug(String.format("Search:[%s]  Results:%d  Elapsed(ms):%d", searchString,
      results.size(), TimeUnit.MILLISECONDS.convert(System.nanoTime()-start, TimeUnit.NANOSECONDS)));

    return results;
  }

  class WhereObject {
    public WhereObject(String clause, Object[] bindings) {
      this.clause   = clause;
      this.bindings = bindings;
    }
    String   clause;
    Object[] bindings;
  }

  private <T> WhereObject buildWhereClause(T t) {
    StringBuilder where = new StringBuilder();
    List<Object>  bindings = new ArrayList<>();
    try {
      List<java.lang.reflect.Field> fields = new ArrayList<>();
      fields.addAll( Arrays.asList(t.getClass().getDeclaredFields()) );

      for (java.lang.reflect.Field f : fields) {
        // ignore static member variables
        if (Modifier.isStatic(f.getModifiers())) continue;

        f.setAccessible(true);    // allow access to private members

        Object v = f.get(t);      // get attribute value

        if (v != null) {
          if (where.length() > 0) where.append(" AND "); 

          if (f.getName().equals("name")) {
            // ignore case when looking up institutions by name (assumes utf8 char set)
            where.append("(name LIKE ? collate utf8_unicode_ci OR name LIKE ? collate utf8_unicode_ci)");
            bindings.add(v + "%");
            bindings.add("%" + v + "%");
          } else {
            where.append( dbFieldName(f.getName()) + " = ?");
            bindings.add(v);
          }
        }
      }
    }
    catch (Exception e) {
      throw new NedException(ServerError, "Problem building filter expression");
    }
    return new WhereObject(where.toString(), bindings.toArray());
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
