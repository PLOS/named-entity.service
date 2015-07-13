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

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableField;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.ringgold.*;
import org.plos.namedentity.persist.db.ringgold.tables.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.plos.namedentity.api.NedException.ErrorType.ServerError;
import static org.plos.namedentity.persist.db.ringgold.Tables.*;

public final class RinggoldDBServiceImpl implements RinggoldDBService {

  @Autowired DSLContext context;
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

    Object x = context.select()
      .from(table(t.getClass()))
      .where( buildWhereClause(t));

    return context.select()
      .from(table(t.getClass()))
      .where( buildWhereClause(t) )
      .fetchInto((Class<T>)t.getClass());
  }

  //public <T> List<T> findByInstitutionName(T t) {
  //}

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
        return String.format("LOWER(%s) LIKE LOWER('%%%s%%')",field,value);
      }
    } else {
      throw new UnsupportedOperationException("Unsupported entity " + cname);
    }

    // default handler
    StringBuilder condition = new StringBuilder();
    condition.append(field).append("=");
    if (value instanceof Number || value instanceof Boolean) {
      condition.append(value);
    } else {
      condition.append("'").append(value).append("'");
    }
    return condition.toString();
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
