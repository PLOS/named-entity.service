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

// to reduce verbosity, static import generated tables and jooq functions

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.SelectOnConditionStep;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UpdatableRecord;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.ringgold.*;
import org.plos.namedentity.persist.db.ringgold.tables.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.plos.namedentity.api.NedException.ErrorType.ServerError;
import static org.plos.namedentity.persist.db.ringgold.Tables.*;

public final class RinggoldDBServiceImpl implements RinggoldDBService {

  @Autowired DSLContext context;
  public void setContext(DSLContext context) {
    this.context = context;
  }

  @Override
  public <T> List<T> findAll(Class<T> clazz, Integer offset, Integer limit) {
    return context.select().from(table(clazz)).limit(limit).offset(offset).fetchInto(clazz);
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
          where.append(f.getName()).append("=");
          if (v instanceof Number || v instanceof Boolean) {
            where.append(v);
          } else {
            where.append("'").append(v).append("'");
          }
        }
      }
    }
    catch (Exception e) {
      throw new NedException(ServerError, "Problem building filter expression");
    }
    return where.toString();
  }

  private boolean isEmptyOrBlank(String s) {
    return s == null || s.trim().isEmpty();
  }

  /* ------------------------------------------------------------------------ */
  /*  RINGGOLD QUERIES                                                        */
  /* ------------------------------------------------------------------------ */

  @SuppressWarnings("unchecked")
  public <T> List<T> findInstitutionByName(String name, Class<T> clazz) {
    String cname = clazz.getCanonicalName();

    if (cname.equals(Institution.class.getCanonicalName()))
      return (List<T>)findInstitutionByName(name);

    throw new UnsupportedOperationException("Can not resolve institution for " + clazz);
  }

  private List<Institution> findInstitutionByName(String name) {

    name = name + "%";

    Parents p = PARENTS.as("p");
    Tiers   t = TIERS.as("t");

    return this.context
      .select(p.P_CODE, p.GP_CODE, p.NAME, p.CITY, p.STATE, p.COUNTRY, p.TYPE)
      .from(p)
      .join(t).on(p.P_CODE.equal(t.P_CODE))
      .where(p.GP_CODE.equal(Long.valueOf(0)))
      .and(t.TIER.like("A%")).and(p.NAME.like(name))
      .fetch()
      .into(Institution.class);

    //if (record == null)
      //throw new NedException(EntityNotFound, 
        //String.format("Organization not found with UID type %s and value %s", srcType, uid));
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
    entityTableMap.put(Institution.class, new TablePkPair(PARENTS, PARENTS.REC_ID));
  }
  private static Table table(Class key) {
    return entityTableMap.get(key).table();
  }
  private static TableField pkField(Class key) {
    return entityTableMap.get(key).pkField();
  }
}
