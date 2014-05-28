package org.plos.namedentity.persist;

// to reduce verbosity, static import generated tables and jooq functions
import static org.jooq.impl.DSL.currentTimestamp;
import static org.plos.namedentity.persist.db.namedentities.Tables.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UpdatableRecord;

import org.plos.namedentity.api.dto.IndividualDTO;
import org.plos.namedentity.api.entity.AddressEntity;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.JournalEntity;
import org.plos.namedentity.api.entity.PhonenumberEntity;
import org.plos.namedentity.api.entity.RoleEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;
import org.plos.namedentity.persist.db.namedentities.tables.*;

import org.springframework.beans.factory.annotation.Autowired;

public final class NamedEntityDBServiceImpl implements NamedEntityDBService, NamedEntityQueries {

    @Autowired DSLContext context;

    @Override @SuppressWarnings("unchecked")
    public <T> Integer create(T t) {
        // load jooq-generated record from pojo. insert (implicitly)
        UpdatableRecord record = (UpdatableRecord) context.newRecord(table(t.getClass()), t);
        record.store();

        // assume first attribute is always the primary key
        return record.getValue(0, Integer.class);
    }

    @Override @SuppressWarnings("unchecked")
    public <T> boolean update(T t) {
        // load jooq-generated record from pojo. update (explicitly) 
        UpdatableRecord record = (UpdatableRecord) context.newRecord(table(t.getClass()), t);
        return (context.executeUpdate(record) == 1);
    }

    @Override @SuppressWarnings("unchecked")
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

    @Override @SuppressWarnings("unchecked")
    public <T> List<T> findByAttribute(T t) {
    
        if (t instanceof GlobaltypeEntity) {
			GlobaltypeEntity gt = (GlobaltypeEntity)t;
			if (gt.getTypeid() != null) {

				// lookup a specific type value
				if (gt.getShortdescription() != null) {
					return context
						.select().from(GLOBALTYPES)
						.where(GLOBALTYPES.TYPEID.equal( gt.getTypeid()) )
						.and(GLOBALTYPES.SHORTDESCRIPTION.equal(gt.getShortdescription()))
						.fetchInto((Class<T>)t.getClass());

				// lookup type values for a type class
				} else {
					return context
						.select().from(GLOBALTYPES)
						.where(GLOBALTYPES.TYPEID.equal( gt.getTypeid()) )
						.fetchInto((Class<T>)t.getClass());
				}
			}
        }
        else if (t instanceof EmailEntity) {
			EmailEntity et = (EmailEntity)t;

            // lookup emails for an individual
			if (et.getNamedentityid() != null) {
                return context
                    .select().from(EMAILS)
                    .where(EMAILS.NAMEDENTITYID.equal( et.getNamedentityid()) )
                    .fetchInto((Class<T>)t.getClass());
            }
            // lookup email by address
            if (et.getEmailaddress() != null) {
                return context
                    .select().from(EMAILS)
                    .where(EMAILS.EMAILADDRESS.equal( et.getEmailaddress()) )
                    .fetchInto((Class<T>)t.getClass());
            }
        }
        else if (t instanceof PhonenumberEntity) {
			PhonenumberEntity pt = (PhonenumberEntity)t;

            // lookup phone numbers for an individual
			if (pt.getNamedentityid() != null) {
                return context
                    .select().from(PHONENUMBERS)
                    .where(PHONENUMBERS.NAMEDENTITYID.equal( pt.getNamedentityid()) )
                    .fetchInto((Class<T>)t.getClass());
            }
            //TODO - lookup by phone number
        }

        throw new UnsupportedOperationException("findByAttribute hasn't been implemented for all types");
    }

    @Override
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
    /*  NAMED ENTITY QUERIES                                                  */
    /* ---------------------------------------------------------------------- */

    @Override
    public IndividualDTO findIndividualById(Integer individualId) {

        Globaltypes  gt1 = GLOBALTYPES.as("gt1");
        Globaltypes  gt2 = GLOBALTYPES.as("gt2");
        Globaltypes  gt3 = GLOBALTYPES.as("gt3");
        Globaltypes  gt4 = GLOBALTYPES.as("gt4");
        Globaltypes  gt5 = GLOBALTYPES.as("gt5");
        Globaltypes  gt6 = GLOBALTYPES.as("gt6");
        Individuals  i   = INDIVIDUALS.as("i");

        List<IndividualDTO> individuals = this.context
            .select(
                i.NAMEDENTITYID, i.FIRSTNAME, i.MIDDLENAME, i.LASTNAME, i.URL,
                gt1.SHORTDESCRIPTION.as("nameprefix"),                 
                gt2.SHORTDESCRIPTION.as("namesuffix"),
                gt3.SHORTDESCRIPTION.as("preferredlanguage"), 
                gt4.SHORTDESCRIPTION.as("preferredcommunication"))
            .from(i)
            .leftOuterJoin(gt1).on(i.NAMEPREFIXTYPEID.equal(gt1.GLOBALTYPEID))
            .leftOuterJoin(gt2).on(i.NAMESUFFIXTYPEID.equal(gt2.GLOBALTYPEID))
            .leftOuterJoin(gt3).on(i.PREFERREDLANGUAGETYPEID.equal(gt3.GLOBALTYPEID))
            .leftOuterJoin(gt4).on(i.PREFERREDCOMMUNICATIONMETHODTYPEID.equal(gt4.GLOBALTYPEID))
            .where(i.NAMEDENTITYID.equal(individualId))
            .fetch()
            .into(IndividualDTO.class);

        assert( individuals.size() == 1 );
        return individuals.get(0);
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
        dtoTableMap.put(AddressEntity.class, new TablePkPair(ADDRESSES, ADDRESSES.ADDRESSID));
        dtoTableMap.put(EmailEntity.class, new TablePkPair(EMAILS, EMAILS.EMAILID));
        dtoTableMap.put(GlobaltypeEntity.class, new TablePkPair(GLOBALTYPES, GLOBALTYPES.GLOBALTYPEID));
        dtoTableMap.put(IndividualEntity.class, new TablePkPair(INDIVIDUALS, INDIVIDUALS.NAMEDENTITYID));
        dtoTableMap.put(JournalEntity.class, new TablePkPair(JOURNALS, JOURNALS.JOURNALID));
        dtoTableMap.put(PhonenumberEntity.class, new TablePkPair(PHONENUMBERS, PHONENUMBERS.PHONENUMBERID));
        dtoTableMap.put(RoleEntity.class, new TablePkPair(ROLES, ROLES.ROLEID));
        dtoTableMap.put(TypedescriptionEntity.class, new TablePkPair(TYPEDESCRIPTIONS, TYPEDESCRIPTIONS.TYPEID));
    }
    private static Table table(Class key) {
        return dtoTableMap.get(key).table();
    }
    private static TableField pkField(Class key) {
        return dtoTableMap.get(key).pkField();
    }
}
