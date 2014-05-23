package org.plos.namedentity.persist;

import static org.jooq.impl.DSL.currentTimestamp;
import static org.plos.namedentity.persist.db.namedentities.Tables.JOURNALS;
import static org.plos.namedentity.persist.db.namedentities.Tables.GLOBALTYPES;
import static org.plos.namedentity.persist.db.namedentities.Tables.NAMEDENTITYIDENTIFIERS;
import static org.plos.namedentity.persist.db.namedentities.Tables.ROLES;
import static org.plos.namedentity.persist.db.namedentities.Tables.TYPEDESCRIPTIONS;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UpdatableRecord;

import org.plos.namedentity.api.GlobaltypesDTO;
import org.plos.namedentity.api.JournalsDTO;
import org.plos.namedentity.api.TypedescriptionsDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Transactional;

public final class NamedEntityDBServiceImpl implements NamedEntityDBService {

    @Autowired DSLContext context;
    @Autowired DataSourceTransactionManager txMgr;

    @Override @Transactional @SuppressWarnings("unchecked")
    public <T> Integer create(T t) {
        // load jooq-generated record from pojo. insert (implicitly)
        UpdatableRecord record = (UpdatableRecord) context.newRecord(table(t.getClass()), t);
        record.store();

        // assume first attribute is always the primary key
        return record.getValue(0, Integer.class);
    }

    @Override @Transactional @SuppressWarnings("unchecked")
    public <T> boolean update(T t) {
        // load jooq-generated record from pojo. update (explicitly) 
        UpdatableRecord record = (UpdatableRecord) context.newRecord(table(t.getClass()), t);
        return (context.executeUpdate(record) == 1);
    }

    @Override @Transactional @SuppressWarnings("unchecked")
    public <T> boolean delete(T t) {
        // load jooq-generated record from pojo. delete (explicitly) 
        UpdatableRecord record = (UpdatableRecord) context.newRecord(table(t.getClass()), t);
        return (record.delete() == 1);
    }

    @Override
    public <T> List<T> findAll(Class<T> clazz) {
        return context.select().from(table(clazz)).fetchInto(clazz);
    }

    @Override
    public <T> T findById(Integer id, Class<T> clazz) {
        Record r = context.select().from(table(clazz))
                          .where(pkField(clazz).equal(id))
                          .fetchOne();

        return (r != null ? r.into(clazz) : null);
    }

    @Override @Transactional
    public Integer newNamedEntityId(String typeCode) {

        return this.context.insertInto(NAMEDENTITYIDENTIFIERS) 
                   .set(NAMEDENTITYIDENTIFIERS.TYPEID, findTypeIdByName(TypeClassEnum.NAMED_ENTITY_TYPES, typeCode))
                   .set(ROLES.CREATEDBY, 1)
                   .set(ROLES.CREATED, currentTimestamp())
                   .set(ROLES.LASTMODIFIEDBY, 1)
                   .set(ROLES.LASTMODIFIED, currentTimestamp())
                   .returning(NAMEDENTITYIDENTIFIERS.NAMEDENTITYID)
                   .fetchOne()
                   .getNamedentityid();
    }

    private Integer findTypeIdByName(TypeClassEnum typeClass, String typeValue) {
        if (isEmptyOrBlank(typeValue)) {
            return null;
        }

        return this.context.select()
                   .from(GLOBALTYPES)
                   .join(TYPEDESCRIPTIONS)
                   .on(TYPEDESCRIPTIONS.TYPEID.equal(GLOBALTYPES.TYPEID))
                   .where(TYPEDESCRIPTIONS.DESCRIPTION.equal(typeClass.getName()))
                   // conditional expression
                   .and(GLOBALTYPES.SHORTDESCRIPTION.equalIgnoreCase(typeValue)
                        .or(GLOBALTYPES.TYPECODE.equalIgnoreCase(typeValue))
                        .or(GLOBALTYPES.LONGDESCRIPTION.equalIgnoreCase(typeValue)))
                   .fetchOne()
                   .getValue(GLOBALTYPES.GLOBALTYPEID);
    }

    private boolean isEmptyOrBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    /* ---------------------------------------------------------------------- */
    /*  INTERNAL MAP : DTO POJO -> { JooqTable, JooqPkFieldForTable }         */
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
     * This map is used internally to associate a DTO with a database table
     * and primary key for that table. The database references are JOOQ wrappers
     * (Table and TableField objects).
     *
     * If you ADD or DELETE a table in NED's schema, you will need to update
     * the map populated below. This is done manually and not auto-generated!
     *
     * You can get the table name (static) and primary key (instance var) from 
     * the appropriate class in org.plos.namedentity.persist.db.namedentities.tables
     */

    private static final Map<Class,TablePkPair> dtoTableMap;
    static {
        dtoTableMap = new ConcurrentHashMap<>();
        dtoTableMap.put(GlobaltypesDTO.class, new TablePkPair(GLOBALTYPES, GLOBALTYPES.GLOBALTYPEID));
        dtoTableMap.put(JournalsDTO.class, new TablePkPair(JOURNALS, JOURNALS.JOURNALID));
        dtoTableMap.put(TypedescriptionsDTO.class, new TablePkPair(TYPEDESCRIPTIONS, TYPEDESCRIPTIONS.TYPEID));
    }
    private static Table table(Class key) {
        return dtoTableMap.get(key).table();
    }
    private static TableField pkField(Class key) {
        return dtoTableMap.get(key).pkField();
    }
}
