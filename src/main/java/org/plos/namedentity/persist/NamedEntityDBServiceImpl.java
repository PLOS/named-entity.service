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
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UpdatableRecord;
import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.persist.db.namedentities.tables.Addresses;
import org.plos.namedentity.persist.db.namedentities.tables.Emails;
import org.plos.namedentity.persist.db.namedentities.tables.Globaltypes;
import org.plos.namedentity.persist.db.namedentities.tables.Individuals;
import org.plos.namedentity.persist.db.namedentities.tables.Organizations;
import org.plos.namedentity.persist.db.namedentities.tables.Phonenumbers;
import org.plos.namedentity.persist.db.namedentities.tables.Roles;
import org.plos.namedentity.persist.db.namedentities.tables.Uniqueidentifiers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.jooq.impl.DSL.currentTimestamp;
import static org.plos.namedentity.persist.db.namedentities.Tables.*;

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
  public IndividualEntity findIndividualByNedId(Integer nedId) {
/*
        SELECT gt1.shortDescription nameprefix, i.firstName firstname, 
               i.middleName middlename, i.lastName lastname, 
               gt2.shortDescription namesuffix, i.url, 
               gt3.shortDescription preferredlanguage, 
               gt4.shortDescription preferredcommunication
          FROM individuals i
     LEFT JOIN globalTypes gt1 ON i.namePrefixTypeId        = gt1.globalTypeId
     LEFT JOIN globalTypes gt2 ON i.nameSuffixTypeId        = gt2.globalTypeId
     LEFT JOIN globalTypes gt3 ON i.preferredLanguageTypeId = gt3.globalTypeId
     LEFT JOIN globalTypes gt4 ON i.preferredCommunicationMethodTypeId = gt4.globalTypeId
         WHERE i.namedEntityId = 37
*/
    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Globaltypes gt3 = GLOBALTYPES.as("gt3");
    Globaltypes gt4 = GLOBALTYPES.as("gt4");
    Individuals i   = INDIVIDUALS.as("i");

    return this.context
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
      .where(i.NAMEDENTITYID.equal(nedId))
      .fetchOne()
      .into(IndividualEntity.class);
  }

  @Override
  public OrganizationEntity findOrganizationByNedId(Integer nedId) {
/*
        SELECT o.namedentityid, organizationfamiliarname, organizationlegalname,
               isactive, isvisible, url, gt1.shortdescription organizationtype
          FROM organizations o
     LEFT JOIN globalTypes gt1 ON o.organizationtypeid = gt1.globalTypeId
         WHERE o.namedentityid = 100
*/
    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Organizations o = ORGANIZATIONS.as("o");

    return this.context
        .select(
            o.NAMEDENTITYID, o.ORGANIZATIONFAMILIARNAME,
            o.ORGANIZATIONLEGALNAME, o.ISACTIVE, o.ISVISIBLE,
            o.URL, gt1.SHORTDESCRIPTION.as("organizationtype"))
        .from(o)
        .leftOuterJoin(gt1).on(o.ORGANIZATIONTYPEID.equal(gt1.GLOBALTYPEID))
        .where(o.NAMEDENTITYID.equal(nedId))
        .fetchOne()
        .into(OrganizationEntity.class);
  }

  @Override
  public List<IndividualEntity> findIndividualsByUid(Integer srcTypeId, String uid) {
/*
   EXPLAIN
        SELECT gt1.shortDescription nameprefix, i.firstName firstname, 
               i.middleName middlename, i.lastName lastname, 
               gt2.shortDescription namesuffix, i.url, 
               gt3.shortDescription preferredlanguage, 
               gt4.shortDescription preferredcommunication,
               gt4.shortDescription preferredcommunication,
         gt5.shortDescription uniqueidentifiertype, uid.uniqueIdentifier 
          FROM individuals i
     LEFT JOIN globalTypes gt1 ON i.namePrefixTypeId                   = gt1.globalTypeId
     LEFT JOIN globalTypes gt2 ON i.nameSuffixTypeId                   = gt2.globalTypeId
     LEFT JOIN globalTypes gt3 ON i.preferredLanguageTypeId            = gt3.globalTypeId
     LEFT JOIN globalTypes gt4 ON i.preferredCommunicationMethodTypeId = gt4.globalTypeId
        JOIN uniqueIdentifiers uid ON i.namedEntityId = uid.namedEntityId AND uid.uniqueIdentifierTypeId = 50
     LEFT JOIN globalTypes gt5 ON uid.uniqueIdentifierTypeId           = gt5.globalTypeId
         WHERE uid.uniqueIdentifier LIKE '%0000-%'

  TODO: wilcard(%) on both sides uid value?
*/
    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Globaltypes gt3 = GLOBALTYPES.as("gt3");
    Globaltypes gt4 = GLOBALTYPES.as("gt4");
    Globaltypes gt5 = GLOBALTYPES.as("gt5");
    Individuals i   = INDIVIDUALS.as("i");
    Uniqueidentifiers u = UNIQUEIDENTIFIERS.as("u");

    return this.context
      .select(
        i.NAMEDENTITYID, i.FIRSTNAME, i.MIDDLENAME, i.LASTNAME, i.URL,
        gt1.SHORTDESCRIPTION.as("nameprefix"),                 
        gt2.SHORTDESCRIPTION.as("namesuffix"),
        gt3.SHORTDESCRIPTION.as("preferredlanguage"), 
        gt4.SHORTDESCRIPTION.as("preferredcommunication"),
        gt5.SHORTDESCRIPTION.as("uniqueidentifiertype"),
        u.UNIQUEIDENTIFIER)
      .from(i)
      .leftOuterJoin(gt1).on(i.NAMEPREFIXTYPEID.equal(gt1.GLOBALTYPEID))
      .leftOuterJoin(gt2).on(i.NAMESUFFIXTYPEID.equal(gt2.GLOBALTYPEID))
      .leftOuterJoin(gt3).on(i.PREFERREDLANGUAGETYPEID.equal(gt3.GLOBALTYPEID))
      .leftOuterJoin(gt4).on(i.PREFERREDCOMMUNICATIONMETHODTYPEID.equal(gt4.GLOBALTYPEID))
      .join(u).on(i.NAMEDENTITYID.equal(u.NAMEDENTITYID)).and(u.UNIQUEIDENTIFIERTYPEID.equal(srcTypeId))
      .leftOuterJoin(gt5).on(u.UNIQUEIDENTIFIERTYPEID.equal(gt5.GLOBALTYPEID))
      .where(u.UNIQUEIDENTIFIER.like(uid + "%"))
      .fetch()
      .into(IndividualEntity.class);
  }

  @Override
  public List<AddressEntity> findAddressesByNedId(Integer nedId) {
/*
        SELECT gt1.shortDescription addresstype, a.addressline1, a.addressline2, 
               a.addressline3, a.city, a.postalCode, a.isprimary,
               gt2.shortDescription statecodetype,
               gt3.shortDescription countrycodetype
          FROM addresses a
     LEFT JOIN globalTypes gt1 ON a.addressTypeId     = gt1.globalTypeId
     LEFT JOIN globalTypes gt2 ON a.stateCodeTypeId   = gt2.globalTypeId
     LEFT JOIN globalTypes gt3 ON a.countryCodeTypeId = gt3.globalTypeId
         WHERE a.namedEntityId = 59
*/
    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Globaltypes gt3 = GLOBALTYPES.as("gt3");
    Addresses   a   = ADDRESSES.as("a");

    return this.context
      .select(
        a.ADDRESSLINE1, a.ADDRESSLINE2, a.ADDRESSLINE3, a.CITY, 
        a.POSTALCODE, a.ISPRIMARY,
        gt1.SHORTDESCRIPTION.as("addresstype"),                 
        gt2.SHORTDESCRIPTION.as("statecodetype"),
        gt3.SHORTDESCRIPTION.as("countrycodetype"))
      .from(a)
      .leftOuterJoin(gt1).on(a.ADDRESSTYPEID.equal(gt1.GLOBALTYPEID))
      .leftOuterJoin(gt2).on(a.STATECODETYPEID.equal(gt2.GLOBALTYPEID))
      .leftOuterJoin(gt3).on(a.COUNTRYCODETYPEID.equal(gt3.GLOBALTYPEID))
      .where(a.NAMEDENTITYID.equal(nedId))
      .fetch()
      .into(AddressEntity.class);
  }

  @Override
  public List<EmailEntity> findEmailsByNedId(Integer nedId) {
/*
        SELECT gt1.shortDescription emailtype, e.emailaddress, e.isprimary
          FROM emails e
     LEFT JOIN globalTypes gt1 ON e.emailTypeId = gt1.globalTypeId
         WHERE e.namedentityid = 59
*/
    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Emails      e   = EMAILS.as("e");

    return this.context
      .select(
        e.EMAILADDRESS, e.ISPRIMARY, 
        gt1.SHORTDESCRIPTION.as("emailtype"))
      .from(e)
      .leftOuterJoin(gt1).on(e.EMAILTYPEID.equal(gt1.GLOBALTYPEID))
      .where(e.NAMEDENTITYID.equal(nedId))
      .fetch()
      .into(EmailEntity.class);
  }

  @Override
  public List<PhonenumberEntity> findPhoneNumbersByNedId(Integer nedId) {
/*
        SELECT gt1.shortDescription phonenumbertype,
               gt2.shortDescription countrycodetype,
               p.phoneNumber phonenumber, p.extension, p.isPrimary
          FROM phoneNumbers p
     LEFT JOIN globalTypes gt1 ON p.phoneNumberTypeId = gt1.globalTypeId
     LEFT JOIN globalTypes gt2 ON p.countryCodeTypeId = gt2.globalTypeId
         WHERE p.namedentityid = 59
*/
    Globaltypes  gt1 = GLOBALTYPES.as("gt1");
    Globaltypes  gt2 = GLOBALTYPES.as("gt2");
    Phonenumbers p   = PHONENUMBERS.as("p");

    return this.context
      .select(
        p.PHONENUMBER, p.EXTENSION, p.ISPRIMARY,
        gt1.SHORTDESCRIPTION.as("phonenumbertype"),                 
        gt2.SHORTDESCRIPTION.as("countrycodetype"))
      .from(p)
      .leftOuterJoin(gt1).on(p.PHONENUMBERTYPEID.equal(gt1.GLOBALTYPEID))
      .leftOuterJoin(gt2).on(p.COUNTRYCODETYPEID.equal(gt2.GLOBALTYPEID))
      .where(p.NAMEDENTITYID.equal(nedId))
      .fetch()
      .into(PhonenumberEntity.class);
  }

  @Override
  public List<RoleEntity> findRolesByNedId(Integer nedId) {
/*
        SELECT gt1.shortDescription sourceapplicationtype,
               gt2.shortDescription roletype,
               r.startDate, r.endDate
          FROM roles r
     LEFT JOIN globalTypes gt1 ON r.sourceApplicationTypeId = gt1.globalTypeId
     LEFT JOIN globalTypes gt2 ON r.roleTypeID = gt2.globalTypeId
         WHERE r.namedentityid = 59
*/
    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Roles       r   = ROLES.as("r");

    return this.context
      .select(
        r.STARTDATE, r.ENDDATE,
        gt1.SHORTDESCRIPTION.as("sourceapplicationtype"),                 
        gt2.SHORTDESCRIPTION.as("roletype"))
      .from(r)
      .leftOuterJoin(gt1).on(r.SOURCEAPPLICATIONTYPEID.equal(gt1.GLOBALTYPEID))
      .leftOuterJoin(gt2).on(r.ROLETYPEID.equal(gt2.GLOBALTYPEID))
      .where(r.NAMEDENTITYID.equal(nedId))
      .fetch()
      .into(RoleEntity.class);
  }

  @Override public List<UniqueidentifierEntity> findUniqueIdsByNedId(Integer nedId) {
/*
   EXPLAIN
        SELECT gt.shortDescription uniqueidentifiertype, uid.uniqueIdentifier 
          FROM uniqueIdentifiers uid
     LEFT JOIN globalTypes gt ON uid.uniqueIdentifierTypeId = gt.globalTypeId
         WHERE uid.namedentityid = 59
*/
    Globaltypes       gt  = GLOBALTYPES.as("gt");
    Uniqueidentifiers uid = UNIQUEIDENTIFIERS.as("uid");

    return this.context
      .select(
        uid.UNIQUEIDENTIFIER, gt.SHORTDESCRIPTION.as("uniqueidentifiertype"))
      .from(uid)
      .leftOuterJoin(gt).on(uid.UNIQUEIDENTIFIERTYPEID.equal(gt.GLOBALTYPEID))
      .where(uid.NAMEDENTITYID.equal(nedId))
      .fetch()
      .into(UniqueidentifierEntity.class);
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
    entityTableMap.put(AddressEntity.class, new TablePkPair(ADDRESSES, ADDRESSES.ADDRESSID));
    entityTableMap.put(EmailEntity.class, new TablePkPair(EMAILS, EMAILS.EMAILID));
    entityTableMap.put(GlobaltypeEntity.class, new TablePkPair(GLOBALTYPES, GLOBALTYPES.GLOBALTYPEID));
    entityTableMap.put(IndividualEntity.class, new TablePkPair(INDIVIDUALS, INDIVIDUALS.NAMEDENTITYID));
    entityTableMap.put(JournalEntity.class, new TablePkPair(JOURNALS, JOURNALS.JOURNALID));
    entityTableMap.put(PhonenumberEntity.class, new TablePkPair(PHONENUMBERS, PHONENUMBERS.PHONENUMBERID));
    entityTableMap.put(RoleEntity.class, new TablePkPair(ROLES, ROLES.ROLEID));
    entityTableMap.put(TypedescriptionEntity.class, new TablePkPair(TYPEDESCRIPTIONS, TYPEDESCRIPTIONS.TYPEID));
    entityTableMap.put(UniqueidentifierEntity.class, new TablePkPair(UNIQUEIDENTIFIERS, UNIQUEIDENTIFIERS.UNIQUEIDENTIFIERSID));
    entityTableMap.put(OrganizationEntity.class, new TablePkPair(ORGANIZATIONS, ORGANIZATIONS.NAMEDENTITYID));
  }
  private static Table table(Class key) {
    return entityTableMap.get(key).table();
  }
  private static TableField pkField(Class key) {
    return entityTableMap.get(key).pkField();
  }
}
