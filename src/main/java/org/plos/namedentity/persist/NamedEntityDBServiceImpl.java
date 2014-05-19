package org.plos.namedentity.persist;

import static org.jooq.impl.DSL.currentTimestamp;
import static org.plos.namedentity.persist.db.namedentities.Tables.GLOBALTYPES;
import static org.plos.namedentity.persist.db.namedentities.Tables.NAMEDENTITYIDENTIFIERS;
import static org.plos.namedentity.persist.db.namedentities.Tables.ROLES;
import static org.plos.namedentity.persist.db.namedentities.Tables.TYPEDESCRIPTIONS;

import java.io.Serializable;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import org.plos.namedentity.api.TypedescriptionsDTO;
import org.plos.namedentity.persist.db.namedentities.tables.records.TypedescriptionsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Transactional;

public final class NamedEntityDBServiceImpl implements NamedEntityDBService {

    @Autowired DSLContext context;
    @Autowired DataSourceTransactionManager txMgr;

    @Override @Transactional
    public Integer create(Serializable entity) {
        // TODO: if-else construct screams polymorphism.

        Integer primaryKey = null;

        if (entity instanceof TypedescriptionsDTO) {
            // load jooq-generated record from pojo. insert (implicitly)
            TypedescriptionsRecord record = context.newRecord(TYPEDESCRIPTIONS, (TypedescriptionsDTO)entity);
            record.store();

            // get db-generated id
            primaryKey = record.getTypeid();
        } else {
            throw new RuntimeException("Unknown entity: " + entity);
        }
        return primaryKey;
    }

    @Override @Transactional
    public boolean update(Serializable entity) {
        int result;

        // TODO: optimistic locking
        if (entity instanceof TypedescriptionsDTO) {
            // load jooq-generated record from pojo. update (explicitly) 
            TypedescriptionsRecord record = context.newRecord(TYPEDESCRIPTIONS, (TypedescriptionsDTO)entity);
            result = context.executeUpdate(record);
        } else {
            throw new RuntimeException("Unknown entity: " + entity);
        }
        return (result == 1);
    }

    @Override @Transactional
    public boolean delete(Serializable entity) {
        int result = 0;

        // TODO: REFACTOR! if-else screams polymorphism.
        // TODO: optimistic locking

        if (entity instanceof TypedescriptionsDTO) {
            // load jooq-generated record from pojo. delete. 
            TypedescriptionsRecord record = context.newRecord(TYPEDESCRIPTIONS, (TypedescriptionsDTO)entity);
            result = record.delete();
        } else {
            throw new RuntimeException("Unknown entity: " + entity);
        }
        return (result == 1); 
    }

    @Override
    public List<TypedescriptionsDTO> findTypedescriptionAll() {
        return context.select().from(TYPEDESCRIPTIONS).fetchInto(TypedescriptionsDTO.class);
    }

    @Override
    public TypedescriptionsDTO findTypedescriptionById(Integer typeId) {
        // TODO: will throw a NPE if record not found. handle.
        return context.select().from(TYPEDESCRIPTIONS).where(TYPEDESCRIPTIONS.TYPEID.equal(typeId))
                      .fetchOne().into(TypedescriptionsDTO.class);
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
}
