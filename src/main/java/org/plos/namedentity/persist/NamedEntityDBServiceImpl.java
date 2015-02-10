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
import org.jooq.SelectOnConditionStep;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UpdatableRecord;
import org.plos.namedentity.api.EntityNotFoundException;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.persist.db.namedentities.tables.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.plos.namedentity.persist.db.namedentities.Tables.*;

public final class NamedEntityDBServiceImpl implements NamedEntityDBService {

  @Autowired DSLContext context;
  public void setContext(DSLContext context) {
    this.context = context;
  }

  @Override @SuppressWarnings("unchecked")
  public <T> Integer create(T t) {
    // load jooq-generated record from pojo.
    UpdatableRecord record = (UpdatableRecord) context.newRecord(table(t.getClass()), t);
    record.insert();

    // assume first attribute is always the primary key
    return record.getValue(0, Integer.class);
  }

  @Override @SuppressWarnings("unchecked")
  public <T> boolean update(T t) {
    // load jooq-generated record from pojo.
    UpdatableRecord record = (UpdatableRecord) context.newRecord(table(t.getClass()), t);

    if (t instanceof Entity) {
      if (((Entity)t).getId() == null) {
        throw new NedValidationException("Can't update entity without primary key: " + (Entity)t);
      }

      // in jooq 3.5.1, the field change flag isn't set for null entity pojos
      // values. this seems different behavior from 3.4.1 which did this for all
      // attributes. let's manually control this for now by explicitly setting 
      // the changed flag for all attributes except created and lastmodified.

      for (Field<?> f : record.fields()) {
        String fieldName = f.getName();
        if (fieldName.equalsIgnoreCase("created") || fieldName.equalsIgnoreCase("lastmodified")) {
          record.changed(fieldName,false);
        } else {
          record.changed(fieldName,true);
        }
      }
    }
    return (context.executeUpdate(record) == 1);
  }

  @Override @SuppressWarnings("unchecked")
  public <T> boolean delete(T t) {
    // load jooq-generated record from pojo. delete (explicitly) 
    UpdatableRecord record = (UpdatableRecord) context.newRecord(table(t.getClass()), t);
    return (record.delete() == 1);
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
  
    if (t instanceof Globaltype) {
      Globaltype gt = (Globaltype)t;
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
    else if (t instanceof Email) {
      Email et = (Email)t;

      // lookup emails for an individual
      if (et.getNedid() != null) {
        return context
          .select().from(EMAILS)
          .where(EMAILS.NEDID.equal( et.getNedid()) )
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
    else if (t instanceof Phonenumber) {
      Phonenumber pt = (Phonenumber)t;

      // lookup phone numbers for an individual
      if (pt.getNedid() != null) {
        return context
          .select().from(PHONENUMBERS)
          .where(PHONENUMBERS.NEDID.equal( pt.getNedid()) )
          .fetchInto((Class<T>)t.getClass());
      }
      //TODO - lookup by phone number
    }

    throw new UnsupportedOperationException("findByAttribute hasn't been implemented for all types");
  }

  @Override
  public Integer findTypeClass(String description) {

    //TODO - cache type classes and values ?

    for (Typedescription typeClass : findAll(Typedescription.class, 0, Integer.MAX_VALUE)) {
      if (typeClass.getDescription().equals(description)) {
        return typeClass.getId();
      }
    }
    throw new NedValidationException("No type class found with description " + description);
  }

  @Override
  public Integer findTypeValue(Integer typeClassId, String name) {
    for (Globaltype typeValue : findAll(Globaltype.class, 0, Integer.MAX_VALUE)) {
      if (typeClassId.equals(typeValue.getTypeid()) &&
          typeValue.getShortdescription().equalsIgnoreCase(name)) {
        return typeValue.getId();
      }
    }
    throw new NedValidationException("No type value found with short description =  " + name);
  }

  @Override
  public Integer newNamedEntityId(String typeCode) {

    return this.context.insertInto(NAMEDENTITYIDENTIFIERS) 
               .set(NAMEDENTITYIDENTIFIERS.TYPEID, findTypeIdByName(TypeClassEnum.NAMED_ENTITY_TYPES, typeCode))
               .set(ROLES.CREATEDBY, 1)
               .set(ROLES.LASTMODIFIEDBY, 1)
               .returning(NAMEDENTITYIDENTIFIERS.ID)
               .fetchOne()
               .getId();
  }

  @Override
  public void checkNedIdForType(Integer nedId, String namedPartyType) {

    // select * from namedEntityIdentifiers as n, globalTypes as g where g.id=n.typeId AND shortDescription="Individual" AND n.id=nedId

    if (this.context.select()
        .from(NAMEDENTITYIDENTIFIERS)
        .join(GLOBALTYPES).on(GLOBALTYPES.ID.equal(NAMEDENTITYIDENTIFIERS.TYPEID))
        .and(GLOBALTYPES.SHORTDESCRIPTION.equal(namedPartyType))
        .and(NAMEDENTITYIDENTIFIERS.ID.equal(nedId)).fetchOne()
      == null)
        throw new EntityNotFoundException(namedPartyType);
  }

  private Integer findTypeIdByName(TypeClassEnum typeClass, String typeValue) {
    if (isEmptyOrBlank(typeValue)) {
      return null;
    }

    return this.context.select()
               .from(GLOBALTYPES)
               .join(TYPEDESCRIPTIONS)
               .on(TYPEDESCRIPTIONS.ID.equal(GLOBALTYPES.TYPEID))
               .where(TYPEDESCRIPTIONS.DESCRIPTION.equal(typeClass.getName()))
               // conditional expression
               .and(GLOBALTYPES.SHORTDESCRIPTION.equalIgnoreCase(typeValue)
                  .or(GLOBALTYPES.TYPECODE.equalIgnoreCase(typeValue))
                  .or(GLOBALTYPES.LONGDESCRIPTION.equalIgnoreCase(typeValue)))
               .fetchOne()
               .getValue(GLOBALTYPES.ID);
  }

  private boolean isEmptyOrBlank(String s) {
    return s == null || s.trim().isEmpty();
  }

  /* ---------------------------------------------------------------------- */
  /*  NAMED ENTITY QUERIES                                                  */
  /* ---------------------------------------------------------------------- */

  @SuppressWarnings("unchecked")
  public <T extends Entity> T findResolvedEntityByKey(Integer pk, Class<T> clazz) {

    String cname = clazz.getCanonicalName();

    if (cname.equals(Individualprofile.class.getCanonicalName()))
      return (T)findIndividualByPrimaryKey(pk);
    if (cname.equals(Email.class.getCanonicalName()))
      return (T)findEmailByPrimaryKey(pk);
    if (cname.equals(Address.class.getCanonicalName()))
      return (T)findAddressByPrimaryKey(pk);
    if (cname.equals(Role.class.getCanonicalName()))
      return (T)findRoleByPrimaryKey(pk);
    if (cname.equals(Uniqueidentifier.class.getCanonicalName()))
      return (T)findUniqueIdsByPrimaryKey(pk);

    throw new UnsupportedOperationException("Can not resolve entity for " + clazz);
  }

  @SuppressWarnings("unchecked")
  public <T extends Entity> T findResolvedEntityByUid(String srcType, String uid, Class<T> clazz) {
    String cname = clazz.getCanonicalName();

    if (cname.equals(Individualprofile.class.getCanonicalName()))
      return (T)findIndividualByUid(srcType, uid);
    if (cname.equals(Organization.class.getCanonicalName()))
      return (T)findOrganizationByUid(srcType, uid);

    throw new UnsupportedOperationException("Can not resolve entity for " + clazz);
  }

  @SuppressWarnings("unchecked")
  public <T extends Entity> List<T> findResolvedEntities(Integer nedId, Class<T> clazz) {

    String cname = clazz.getCanonicalName();

    if (cname.equals(Individualprofile.class.getCanonicalName()))
      return (List<T>)findProfilesByNedId(nedId);
    if (cname.equals(Organization.class.getCanonicalName()))
      return (List<T>)findOrganizationsByNedId(nedId);
    if (cname.equals(Address.class.getCanonicalName()))
      return (List<T>)findAddressesByNedId(nedId);
    if (cname.equals(Email.class.getCanonicalName()))
      return (List<T>)findEmailsByNedId(nedId);
    if (cname.equals(Phonenumber.class.getCanonicalName()))
      return (List<T>)findPhoneNumbersByNedId(nedId);
    if (cname.equals(Role.class.getCanonicalName()))
      return (List<T>)findRolesByNedId(nedId);
    if (cname.equals(Uniqueidentifier.class.getCanonicalName()))
      return (List<T>)findUniqueIdsByNedId(nedId);
    if (cname.equals(Degree.class.getCanonicalName()))
      return (List<T>)findDegreesByNedId(nedId);
    if (cname.equals(Url.class.getCanonicalName()))
      return (List<T>)findUrlsByNedId(nedId);

    throw new UnsupportedOperationException("Can not resolve entity for " + clazz);

  }

  private SelectOnConditionStep select(Uniqueidentifiers uid) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");

    return this.context
        .select(
            uid.ID, uid.NEDID,
            uid.UNIQUEIDENTIFIER, gt1.SHORTDESCRIPTION.as("type"),
            gt2.SHORTDESCRIPTION.as("source"),
            uid.CREATED, uid.LASTMODIFIED)
        .from(uid)
        .leftOuterJoin(gt1).on(uid.TYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(uid.SOURCETYPEID.equal(gt2.ID));
  }

  private SelectOnConditionStep select(Individualprofiles i) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Globaltypes gt5 = GLOBALTYPES.as("gt5");

    return this.context
        .select(
            i.ID, i.NEDID, i.FIRSTNAME, i.MIDDLENAME, i.LASTNAME, i.DISPLAYNAME, 
            i.BIOGRAPHY, i.NICKNAME,
            gt1.SHORTDESCRIPTION.as("nameprefix"),
            gt2.SHORTDESCRIPTION.as("namesuffix"),
            gt5.SHORTDESCRIPTION.as("source"),
            i.CREATED, i.LASTMODIFIED)
        .from(i)
        .leftOuterJoin(gt1).on(i.NAMEPREFIXTYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(i.NAMESUFFIXTYPEID.equal(gt2.ID))
        .leftOuterJoin(gt5).on(i.SOURCETYPEID.equal(gt5.ID));
  }

  private SelectOnConditionStep select(Emails e) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");

    return this.context
        .select(
            e.ID, e.NEDID,
            e.EMAILADDRESS, gt1.SHORTDESCRIPTION.as("type"),
            gt2.SHORTDESCRIPTION.as("source"),
            e.CREATED, e.LASTMODIFIED)
        .from(e)
        .leftOuterJoin(gt1).on(e.TYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(e.SOURCETYPEID.equal(gt2.ID));
  }

  private SelectOnConditionStep select(Addresses a) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Globaltypes gt3 = GLOBALTYPES.as("gt3");
    Globaltypes gt4 = GLOBALTYPES.as("gt4");

    return this.context
        .select(
            a.ID, a.NEDID,
            a.ADDRESSLINE1, a.ADDRESSLINE2, a.ADDRESSLINE3, a.CITY,
            a.POSTALCODE,
            gt1.SHORTDESCRIPTION.as("type"),
            gt2.SHORTDESCRIPTION.as("statecodetype"),
            gt3.SHORTDESCRIPTION.as("countrycodetype"),
            gt4.SHORTDESCRIPTION.as("source"),
            a.CREATED, a.LASTMODIFIED)
        .from(a)
        .leftOuterJoin(gt1).on(a.TYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(a.STATECODETYPEID.equal(gt2.ID))
        .leftOuterJoin(gt3).on(a.COUNTRYCODETYPEID.equal(gt3.ID))
        .leftOuterJoin(gt4).on(a.SOURCETYPEID.equal(gt4.ID));
  }

  private SelectOnConditionStep select(Roles r) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Globaltypes gt3 = GLOBALTYPES.as("gt3");

    return this.context
        .select(
            r.ID, r.STARTDATE, r.ENDDATE, r.NEDID,
            gt1.SHORTDESCRIPTION.as("applicationtype"),
            gt2.SHORTDESCRIPTION.as("type"),
            gt3.SHORTDESCRIPTION.as("source"),
            r.CREATED, r.LASTMODIFIED)
        .from(r)
        .leftOuterJoin(gt1).on(r.APPLICATIONTYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(r.TYPEID.equal(gt2.ID))
        .leftOuterJoin(gt3).on(r.SOURCETYPEID.equal(gt3.ID));
  }

  private SelectOnConditionStep select(Organizations o) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");

    return this.context
        .select(o.ID,
            o.NEDID, o.FAMILIARNAME,
            o.LEGALNAME, o.ISACTIVE,
            gt1.SHORTDESCRIPTION.as("type"),
            gt2.SHORTDESCRIPTION.as("source"),
            o.CREATED, o.LASTMODIFIED)
        .from(o)
        .leftOuterJoin(gt1).on(o.TYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(o.SOURCETYPEID.equal(gt2.ID));
  }

  private Organization findOrganizationByUid(String srcType, String uid) {

    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Organizations o = ORGANIZATIONS.as("o");
    Uniqueidentifiers u = UNIQUEIDENTIFIERS.as("u");

    Record record = this.context
        .select(
            o.NEDID, o.FAMILIARNAME,
            o.LEGALNAME, o.ISACTIVE,
            gt2.SHORTDESCRIPTION.as("uniqueidentifiertype"),
            o.CREATED, o.LASTMODIFIED)
        .from(o)
        .join(u).on(o.NEDID.equal(u.NEDID))
        .leftOuterJoin(gt2).on(u.TYPEID.equal(gt2.ID))
        .where(u.UNIQUEIDENTIFIER.equal(uid)).and(gt2.SHORTDESCRIPTION.equal(srcType)).fetchAny();

    if (record == null)
      throw new EntityNotFoundException("Organization");

    return record.into(Organization.class);
  }

  private Individualprofile findIndividualByUid(String srcType, String uid) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Globaltypes gt5 = GLOBALTYPES.as("gt5");
    Individualprofiles i = INDIVIDUALPROFILES.as("i");
    Uniqueidentifiers u = UNIQUEIDENTIFIERS.as("u");

    Record record = this.context
      .select(
        i.NEDID, i.FIRSTNAME, i.MIDDLENAME, i.LASTNAME, i.DISPLAYNAME, 
        gt1.SHORTDESCRIPTION.as("nameprefix"),                 
        gt2.SHORTDESCRIPTION.as("namesuffix"),
        gt5.SHORTDESCRIPTION.as("uniqueidentifiertype"),
        u.UNIQUEIDENTIFIER, i.CREATED, i.LASTMODIFIED)
      .from(i)
      .leftOuterJoin(gt1).on(i.NAMEPREFIXTYPEID.equal(gt1.ID))
      .leftOuterJoin(gt2).on(i.NAMESUFFIXTYPEID.equal(gt2.ID))
      .join(u).on(i.NEDID.equal(u.NEDID))
      .leftOuterJoin(gt5).on(u.TYPEID.equal(gt5.ID))
      .where(u.UNIQUEIDENTIFIER.equal(uid)).and(gt5.SHORTDESCRIPTION.eq(srcType))
      .fetchAny();

    if (record == null)
      throw new EntityNotFoundException("Individual");

    return record.into(Individualprofile.class);
  }


  private List<Individualprofile> findProfilesByNedId(Integer nedId) {
    Individualprofiles i   = INDIVIDUALPROFILES.as("i");
    return select(i).where(i.NEDID.equal(nedId)).fetch().into(Individualprofile.class);
  }

  private List<Organization> findOrganizationsByNedId(Integer nedId) {
    Organizations e   = ORGANIZATIONS.as("e");
    return select(e).where(e.NEDID.equal(nedId)).fetch().into(Organization.class);
  }

  private List<Address> findAddressesByNedId(Integer nedId) {
    Addresses a = ADDRESSES.as("a");
    return select(a).where(a.NEDID.equal(nedId)).fetch().into(Address.class);
  }

  private List<Email> findEmailsByNedId(Integer nedId) {
    Emails e   = EMAILS.as("e");
    return select(e).where(e.NEDID.equal(nedId)).fetch().into(Email.class);
  }

  private List<Role> findRolesByNedId(Integer nedId) {
    Roles r = ROLES.as("r");
    return select(r).where(r.NEDID.equal(nedId)).fetch().into(Role.class);
  }

  private List<Uniqueidentifier> findUniqueIdsByNedId(Integer nedId) {

    Uniqueidentifiers uid = UNIQUEIDENTIFIERS.as("uid");

    return select(uid).where(uid.NEDID.equal(nedId))
        .fetch().into(Uniqueidentifier.class);
  }

  private List<Phonenumber> findPhoneNumbersByNedId(Integer nedId) {

    Globaltypes  gt1 = GLOBALTYPES.as("gt1");
    Globaltypes  gt2 = GLOBALTYPES.as("gt2");
    Globaltypes  gt3 = GLOBALTYPES.as("gt3");
    Phonenumbers p   = PHONENUMBERS.as("p");

    return this.context
        .select(
            p.ID, p.NEDID,
            p.PHONENUMBER, p.EXTENSION,
            gt1.SHORTDESCRIPTION.as("type"),
            gt2.SHORTDESCRIPTION.as("countrycodetype"),
            gt3.SHORTDESCRIPTION.as("source"),
            p.CREATED, p.LASTMODIFIED)
        .from(p)
        .leftOuterJoin(gt1).on(p.TYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(p.COUNTRYCODETYPEID.equal(gt2.ID))
        .leftOuterJoin(gt3).on(p.SOURCETYPEID.equal(gt3.ID))
        .where(p.NEDID.equal(nedId))
        .fetch()
        .into(Phonenumber.class);
  }

  private List<Degree> findDegreesByNedId(Integer nedId) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Degrees     d   = DEGREES.as("d");

    return this.context
        .select(
            d.ID, d.NEDID,
            gt1.SHORTDESCRIPTION.as("type"),
            gt2.SHORTDESCRIPTION.as("source"),
            d.CREATED, d.LASTMODIFIED)
        .from(d)
        .leftOuterJoin(gt1).on(d.TYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(d.SOURCETYPEID.equal(gt2.ID))
        .where(d.NEDID.equal(nedId))
        .fetch()
        .into(Degree.class);
  }

  private List<Url> findUrlsByNedId(Integer nedId) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Urls        u   = URLS.as("u");

    return this.context
        .select(u.URL, u.NEDID, gt1.SHORTDESCRIPTION.as("source"), 
                u.CREATED, u.LASTMODIFIED)
        .from(u)
        .leftOuterJoin(gt1).on(u.SOURCETYPEID.equal(gt1.ID))
        .where(u.NEDID.equal(nedId))
        .fetch()
        .into(Url.class);
  }

  private Uniqueidentifier findUniqueIdsByPrimaryKey(Integer id) {

    Uniqueidentifiers uid = UNIQUEIDENTIFIERS.as("uid");

    Record record = select(uid).where(uid.ID.equal(id)).fetchOne();

    if (record == null) throw new EntityNotFoundException("Uniqueidentifier");

    return record.into(Uniqueidentifier.class);
  }

  private Individualprofile findIndividualByPrimaryKey(Integer individualId) {

    Individualprofiles i   = INDIVIDUALPROFILES.as("i");

    Record record = select(i).where(i.ID.equal(individualId)).fetchOne();

    if (record == null) throw new EntityNotFoundException("Individual");

    return record.into(Individualprofile.class);
  }

  private Email findEmailByPrimaryKey(Integer emailId) {

    Emails e = EMAILS.as("e");

    Record record = select(e).where(e.ID.equal(emailId)).fetchOne();

    if (record == null) throw new EntityNotFoundException("Email");

    return record.into(Email.class);
  }

  private Address findAddressByPrimaryKey(Integer addressId) {

    Addresses a = ADDRESSES.as("a");

    Record record = select(a).where(a.ID.equal(addressId)).fetchOne();

    if (record == null) throw new EntityNotFoundException("Address");

    return record.into(Address.class);
  }

  private Role findRoleByPrimaryKey(Integer roleId) {

    Roles r = ROLES.as("r");

    Record record = select(r).where(r.ID.equal(roleId)).fetchOne();

    if (record == null) throw new EntityNotFoundException("Role");

    return record.into(Role.class);
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

    // TODO: use reflection to set this for all Entities

    entityTableMap.put(Address.class, new TablePkPair(ADDRESSES, ADDRESSES.ID));
    entityTableMap.put(Email.class, new TablePkPair(EMAILS, EMAILS.ID));
    entityTableMap.put(Globaltype.class, new TablePkPair(GLOBALTYPES, GLOBALTYPES.ID));
    entityTableMap.put(Individualprofile.class, new TablePkPair(INDIVIDUALPROFILES, INDIVIDUALPROFILES.ID));
    entityTableMap.put(Journal.class, new TablePkPair(JOURNALS, JOURNALS.ID));
    entityTableMap.put(Phonenumber.class, new TablePkPair(PHONENUMBERS, PHONENUMBERS.ID));
    entityTableMap.put(Role.class, new TablePkPair(ROLES, ROLES.ID));
    entityTableMap.put(Typedescription.class, new TablePkPair(TYPEDESCRIPTIONS, TYPEDESCRIPTIONS.ID));
    entityTableMap.put(Uniqueidentifier.class, new TablePkPair(UNIQUEIDENTIFIERS, UNIQUEIDENTIFIERS.ID));
    entityTableMap.put(Organization.class, new TablePkPair(ORGANIZATIONS, ORGANIZATIONS.ID));
    entityTableMap.put(Degree.class, new TablePkPair(DEGREES, DEGREES.ID));
    entityTableMap.put(Url.class, new TablePkPair(URLS, URLS.ID));
  }
  private static Table table(Class key) {
    return entityTableMap.get(key).table();
  }
  private static TableField pkField(Class key) {
    return entityTableMap.get(key).pkField();
  }
}
