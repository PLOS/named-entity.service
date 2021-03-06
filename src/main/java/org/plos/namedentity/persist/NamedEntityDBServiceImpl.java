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

// to reduce verbosity, static import generated tables and jooq functions

import org.jooq.*;
import org.plos.namedentity.api.Consumer;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.NedException.ErrorType;
import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.api.enums.NamedPartyEnum;
import org.plos.namedentity.api.enums.TypeClassEnum;
import org.plos.namedentity.persist.db.namedentities.tables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;
import static org.plos.namedentity.api.NedException.ErrorType.EntityWithNoPK;
import static org.plos.namedentity.api.NedException.ErrorType.ServerError;
import static org.plos.namedentity.persist.db.namedentities.Tables.*;

public final class NamedEntityDBServiceImpl implements NamedEntityDBService {

  @Autowired @Qualifier("nedDsl") DSLContext context;
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
        throw new NedException(EntityWithNoPK, "Can't update entity without primary key: "+(Entity)t);
      }

      // in jooq 3.5.1, the field change flag isn't set for null entity pojo
      // values. this is different behavior from 3.4.1 which did set this for all
      // attributes. let's manually control this for now by ...
      //   
      //   1. ignoring created and lastmodified timestamp attributes
      //   2. ignoring createdby (recognized when inserting, not updating)
      //   3. updating lastmodifiedby if not null
      //   4. updating boolean attributes only if set (ie, modified)
      //   5. setting the changed flag for all other attributes

      for (Field<?> f : record.fields()) {
        String fieldName = f.getName();
        if (fieldName.equalsIgnoreCase("created") || fieldName.equalsIgnoreCase("lastmodified") ||
            fieldName.equalsIgnoreCase("createdby")) {
          record.changed(fieldName,false);
        }
        else if (fieldName.equalsIgnoreCase("lastmodifiedby")) {
          record.changed(fieldName, (record.getValue(f) == null) ? false : true);
        }
        else if (fieldName.equalsIgnoreCase("isactive") ||
                 fieldName.equalsIgnoreCase("verified") ||
                 fieldName.equalsIgnoreCase("passwordreset")) {
          // do nothing. honor changed flag setting for this attribute.
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

  @Override
  @SuppressWarnings("unchecked")
  public <T> List<T> findByAttribute(T t, Boolean partial) {

    WhereObject whereObj = buildWhereClause(t, partial);

    return context.select()
                  .from(table(t.getClass()))
                  .where(whereObj.clause, whereObj.bindings)
                  .fetchInto((Class<T>) t.getClass());
  }

  class WhereObject {
    public WhereObject(String clause, Object[] bindings) {
      this.clause   = clause;
      this.bindings = bindings;
    }
    String   clause;
    Object[] bindings;
  }

  private <T> WhereObject buildWhereClause(T t, Boolean partial) {
    StringBuilder where = new StringBuilder();
    List<Object>  bindings = new ArrayList<>();
    try {
      List<java.lang.reflect.Field> fields = new ArrayList<>();
      fields.addAll( Arrays.asList(t.getClass().getDeclaredFields()) );
      if (t instanceof Entity) {
        // get nedid, source, and pk from parent
        fields.addAll( Arrays.asList(t.getClass().getSuperclass().getDeclaredFields()) );
      }

      for (java.lang.reflect.Field f : fields) {
        // ignore static member variables
        if (Modifier.isStatic(f.getModifiers())) continue;

        f.setAccessible(true);    // allow access to private members

        Object v = f.get(t);      // get attribute value

        if (v != null) {
          if (where.length() > 0) where.append(" AND ");

          if(v instanceof String && partial){
            where.append(f.getName()+" LIKE ?");
            bindings.add("%" + v + "%");
          } else {
            where.append(f.getName() + " = ?");
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

  @Override
  public <T extends Entity> Integer findTypeClassByInspection(String typename, T t) {

    if (t instanceof Uniqueidentifier) {
      Uniqueidentifier uid = (Uniqueidentifier)t;

      if ("type".equals(typename)) {    // UID Type

        Globaltypes gt = GLOBALTYPES.as("gt");
        Namedentityidentifiers nei = NAMEDENTITYIDENTIFIERS.as("nei");

        SelectConditionStep<Record1<String>> query = this.context
          .select(gt.SHORTDESCRIPTION) 
          .from(nei)
          .join(gt).on(gt.ID.equal(nei.TYPEID))
          .where(nei.ID.equal(uid.getNedid()));

        String entityType = query.fetchOne().value1();

        if ("Individual".equals(entityType)) {
          return findTypeClass(TypeClassEnum.UID_INDIVIDUAL_TYPES.getName());
        } else if ("Organization".equals(entityType)) {
          return findTypeClass(TypeClassEnum.UID_ORGANIZATION_TYPES.getName());
        }
      }

      throw new NedException(String.format("Unable to determine type class for entity:%s type-field:%s",
        t.getClass().getSimpleName(), typename));
    }

    throw new UnsupportedOperationException(
      "findTypeClassByInspection() hasn't been implemented for " + t.getClass().getSimpleName());
  }

  @Override
  public Integer findTypeClass(String description) {

    List<Typedescription> typeClasses = findAll(Typedescription.class, 0, Integer.MAX_VALUE);

    for (Typedescription typeClass : typeClasses) {
      if (typeClass.getDescription().equals(description)) {
        return typeClass.getId();
      }
    }

    // type class not found. assemble list of valid values to pass to error handler
    Set<String> lovs = new HashSet<String>();
    for (Typedescription typeClass : typeClasses) {
      lovs.add(typeClass.getDescription());
    }
    throw new NedException(ErrorType.InvalidTypeClass, "Type Class:"+description, lovs);
  }

  @Override
  public Integer findTypeValue(Integer typeClassId, String name) {

    Globaltype globalTypesearchCriteria = new Globaltype();
    globalTypesearchCriteria.setTypeid(typeClassId);

    List<Globaltype> globalTypesForTypeClass = findByAttribute(globalTypesearchCriteria, false);
    for (Globaltype globalType : globalTypesForTypeClass) {
      if (globalType.getShortdescription().equalsIgnoreCase(name)) {
        return globalType.getId();
      }
    }

    // type value not found. assemble list of valid values to pass to error handler
    Set<String> lovs = new HashSet<String>();
    for (Globaltype globalType : globalTypesForTypeClass) {
      lovs.add(globalType.getShortdescription());
    }
      throw new NedException(ErrorType.InvalidTypeValue,
                           String.format("TypeClassId:%d, TypeValue:%s", typeClassId, name), lovs);
  }

  @Override
  public Integer newNamedEntityId(NamedPartyEnum typeCode) {
    return newNamedEntityId(typeCode, null);
  }

  @Override
  public Integer newNamedEntityId(NamedPartyEnum typeCode, Integer ambraId) {

    return this.context.insertInto(NAMEDENTITYIDENTIFIERS)
               .set(NAMEDENTITYIDENTIFIERS.ID, ambraId)
               .set(NAMEDENTITYIDENTIFIERS.TYPEID, findTypeIdByName(TypeClassEnum.NAMED_ENTITY_TYPES, typeCode.getName()))
               .set(NAMEDENTITYIDENTIFIERS.CREATEDBY, 1)
               .set(NAMEDENTITYIDENTIFIERS.LASTMODIFIEDBY, 1)
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
        throw new NedException(EntityNotFound, namedPartyType + " not found");
  }

  @Override
  public <T extends Entity> void validate(T t) {

    if (t instanceof Uniqueidentifier) {
      Uniqueidentifier uid = (Uniqueidentifier)t;

      String[][] validTypePairs = {
        {"Individual", TypeClassEnum.UID_INDIVIDUAL_TYPES.getName()},
        {"Organization", TypeClassEnum.UID_ORGANIZATION_TYPES.getName()}
      };

      for (int i = 0; i < validTypePairs.length; i++) {

        Globaltypes gt1            = GLOBALTYPES.as("gt1");
        Globaltypes gt2            = GLOBALTYPES.as("gt2");
        Namedentityidentifiers nei = NAMEDENTITYIDENTIFIERS.as("nei");
        Uniqueidentifiers u        = UNIQUEIDENTIFIERS.as("u");
        Typedescriptions td        = TYPEDESCRIPTIONS.as("td");

        SelectConditionStep<Record1<Integer>> query = this.context
          .selectCount()
          .from(u)
          .join(nei).on(u.NEDID.equal(nei.ID))
          .join(gt1).on(nei.TYPEID.equal(gt1.ID))
          .and(gt1.SHORTDESCRIPTION.equal( validTypePairs[i][0]) )
          .join(gt2).on(u.TYPEID.equal(gt2.ID))
          .join(td).on(gt2.TYPEID.equal(td.ID))
          .and(td.DESCRIPTION.equal( validTypePairs[i][1]) )
          .where(u.ID.equal(uid.getId()));

        Integer count = query.fetchOne().value1();
        if (count > 0) return; // valid uid record
      }
      throw new NedException(ErrorType.UidValueError, 
        String.format("Invalid uniqueidentifier type (%s) for entity", uid.getType()));
    }

    // entity is a type we don't yet handle. do nothing.
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
    if (cname.equals(Group.class.getCanonicalName()))
      return (T)findGroupByPrimaryKey(pk);
    if (cname.equals(Uniqueidentifier.class.getCanonicalName()))
      return (T)findUniqueIdsByPrimaryKey(pk);
    if (cname.equals(Auth.class.getCanonicalName()))
      return (T)findAuthCasByPrimaryKey(pk);
    if (cname.equals(Relationship.class.getCanonicalName()))
      return (T)findRelationshipByPrimaryKey(pk);
    if (cname.equals(Alert.class.getCanonicalName()))
      return (T)findAlertByPrimaryKey(pk);

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
    if (cname.equals(Alert.class.getCanonicalName()))
      return (List<T>)findAlertsByNedId(nedId);
    if (cname.equals(Email.class.getCanonicalName()))
      return (List<T>)findEmailsByNedId(nedId);
    if (cname.equals(Phonenumber.class.getCanonicalName()))
      return (List<T>)findPhoneNumbersByNedId(nedId);
    if (cname.equals(Group.class.getCanonicalName()))
      return (List<T>)findGroupsByNedId(nedId);
    if (cname.equals(Uniqueidentifier.class.getCanonicalName()))
      return (List<T>)findUniqueIdsByNedId(nedId);
    if (cname.equals(Degree.class.getCanonicalName()))
      return (List<T>)findDegreesByNedId(nedId);
    if (cname.equals(Url.class.getCanonicalName()))
      return (List<T>)findUrlsByNedId(nedId);
    if (cname.equals(Auth.class.getCanonicalName()))
      return (List<T>)findAuthByNedId(nedId);
    if (cname.equals(Relationship.class.getCanonicalName()))
      return (List<T>)findRelationshipsByNedId(nedId);

    throw new UnsupportedOperationException("Can not resolve entity for " + clazz);

  }

  public Integer countGlobalTypes() {
    return this.context.selectCount().from(GLOBALTYPES).fetchOne(0, int.class);
  }

  public Integer countConsumers() {
    return this.context.selectCount().from(CONSUMERS).fetchOne(0, int.class);
  }

  private SelectOnConditionStep select(Uniqueidentifiers uid) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");

    return this.context
        .select(
            uid.ID, uid.NEDID, uid.METADATA,
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
            i.ISACTIVE, i.CREATED, i.LASTMODIFIED)
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
            e.VERIFIED, e.ISACTIVE, e.CREATED, e.LASTMODIFIED)
        .from(e)
        .leftOuterJoin(gt1).on(e.TYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(e.SOURCETYPEID.equal(gt2.ID));
  }

  private SelectOnConditionStep select(Alerts e) {

    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Globaltypes gt3 = GLOBALTYPES.as("gt3");

    return this.context
        .select(
            e.ID, e.NEDID, e.QUERY, e.NAME,
            gt2.SHORTDESCRIPTION.as("source"),
            gt3.SHORTDESCRIPTION.as("frequency"),
            e.CREATED, e.LASTMODIFIED)
        .from(e)
        .leftOuterJoin(gt2).on(e.SOURCETYPEID.equal(gt2.ID))
        .leftOuterJoin(gt3).on(e.FREQUENCYTYPEID.eq(gt3.ID));
  }

  private SelectOnConditionStep select(Relationships r) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");

    // TODO: resolve nedid names here?
    // nedid's can be either an individual or organization.

    return this.context
        .select(
            r.ID, r.NEDID, r.NEDIDRELATED,
            gt1.SHORTDESCRIPTION.as("type"),
            gt2.SHORTDESCRIPTION.as("source"),
            r.TITLE, r.STARTDATE, r.ENDDATE,
            r.CREATED, r.LASTMODIFIED)
        .from(r)
        .leftOuterJoin(gt1).on(r.TYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(r.SOURCETYPEID.equal(gt2.ID));
  }

  private SelectOnConditionStep select(Authcas auth) {

    Emails e = EMAILS.as("e");

    return this.context
        .select(
            auth.ID, auth.NEDID, auth.EMAILID,
            e.EMAILADDRESS.as("email"),
            auth.AUTHID, auth.PASSWORD, auth.ISACTIVE,
            auth.PASSWORDRESET, auth.VERIFICATIONTOKEN,
            auth.VERIFIED, auth.CREATED, auth.LASTMODIFIED)
        .from(auth)
        .join(e).on(auth.EMAILID.equal(e.ID));
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
            a.ISACTIVE, a.CREATED, a.LASTMODIFIED)
        .from(a)
        .leftOuterJoin(gt1).on(a.TYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(a.STATECODETYPEID.equal(gt2.ID))
        .leftOuterJoin(gt3).on(a.COUNTRYCODETYPEID.equal(gt3.ID))
        .leftOuterJoin(gt4).on(a.SOURCETYPEID.equal(gt4.ID));
  }

  private SelectOnConditionStep select(Groups g) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Globaltypes gt3 = GLOBALTYPES.as("gt3");

    return this.context
        .select(
            g.ID, g.STARTDATE, g.ENDDATE, g.NEDID,
            gt1.SHORTDESCRIPTION.as("applicationtype"),
            gt2.SHORTDESCRIPTION.as("type"),
            gt3.SHORTDESCRIPTION.as("source"),
            g.CREATED, g.LASTMODIFIED)
        .from(g)
        .leftOuterJoin(gt1).on(g.APPLICATIONTYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(g.TYPEID.equal(gt2.ID))
        .leftOuterJoin(gt3).on(g.SOURCETYPEID.equal(gt3.ID));
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

    Globaltypes gt      = GLOBALTYPES.as("gt");
    Typedescriptions td = TYPEDESCRIPTIONS.as("td");
    Organizations o     = ORGANIZATIONS.as("o");
    Uniqueidentifiers u = UNIQUEIDENTIFIERS.as("u");

    Record record = this.context
        .select(
            o.NEDID, o.FAMILIARNAME,
            o.LEGALNAME, o.ISACTIVE,
            gt.SHORTDESCRIPTION.as("uniqueidentifiertype"),
            o.CREATED, o.LASTMODIFIED)
        .from(o)
        .join(u).on(o.NEDID.equal(u.NEDID))
        .join(gt).on(u.TYPEID.equal(gt.ID))
        .join(td).on(gt.TYPEID.equal(td.ID))
                 .and(td.DESCRIPTION.eq(TypeClassEnum.UID_ORGANIZATION_TYPES.getName()))
        .where(u.UNIQUEIDENTIFIER.equal(uid)).and(gt.SHORTDESCRIPTION.equal(srcType)).fetchAny();

    if (record == null)
      throw new NedException(EntityNotFound, 
        String.format("Organization not found with UID type %s and value %s", srcType, uid));

    return record.into(Organization.class);
  }

  private Individualprofile findIndividualByUid(String srcType, String uid) {

    Globaltypes gt1      = GLOBALTYPES.as("gt1");
    Globaltypes gt2      = GLOBALTYPES.as("gt2");
    Globaltypes gt3      = GLOBALTYPES.as("gt3");
    Typedescriptions td  = TYPEDESCRIPTIONS.as("td");
    Individualprofiles i = INDIVIDUALPROFILES.as("i");
    Uniqueidentifiers u  = UNIQUEIDENTIFIERS.as("u");

    Record record = this.context
      .select(
        i.NEDID, i.FIRSTNAME, i.MIDDLENAME, i.LASTNAME, i.DISPLAYNAME, 
        gt1.SHORTDESCRIPTION.as("nameprefix"),                 
        gt2.SHORTDESCRIPTION.as("namesuffix"),
        gt3.SHORTDESCRIPTION.as("uniqueidentifiertype"),
        u.UNIQUEIDENTIFIER, i.CREATED, i.LASTMODIFIED)
      .from(i)
      .leftOuterJoin(gt1).on(i.NAMEPREFIXTYPEID.equal(gt1.ID))
      .leftOuterJoin(gt2).on(i.NAMESUFFIXTYPEID.equal(gt2.ID))
      .join(u).on(i.NEDID.equal(u.NEDID))
      .join(gt3).on(u.TYPEID.equal(gt3.ID))
      .join(td).on(gt3.TYPEID.equal(td.ID))
               .and(td.DESCRIPTION.eq(TypeClassEnum.UID_INDIVIDUAL_TYPES.getName()))
      .where(u.UNIQUEIDENTIFIER.equal(uid)).and(gt3.SHORTDESCRIPTION.eq(srcType))
      .fetchAny();

    if (record == null)
      throw new NedException(EntityNotFound, 
        String.format("Individual not found with UID type %s and value %s", srcType, uid));

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

  private List<Alert> findAlertsByNedId(Integer nedId) {
    Alerts a = ALERTS.as("a");
    return select(a).where(a.NEDID.equal(nedId)).fetch().into(Alert.class);
  }

  private List<Email> findEmailsByNedId(Integer nedId) {
    Emails e   = EMAILS.as("e");
    return select(e).where(e.NEDID.equal(nedId)).fetch().into(Email.class);
  }

  private List<Group> findGroupsByNedId(Integer nedId) {
    Groups g = GROUPS.as("g");
    return select(g).where(g.NEDID.equal(nedId)).fetch().into(Group.class);
  }

  private List<Uniqueidentifier> findUniqueIdsByNedId(Integer nedId) {

    Uniqueidentifiers uid = UNIQUEIDENTIFIERS.as("uid");

    return select(uid).where(uid.NEDID.equal(nedId))
        .fetch().into(Uniqueidentifier.class);
  }

  public List<Alert> getAlerts(String frequency) {

    StringBuilder where = new StringBuilder();
    where.append("true");

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Alerts a = ALERTS.as("a");

    if (frequency != null) {
      // check the global type so it can return a meaningful error about acceptable types
      findTypeValue(findTypeClass("Alert Frequency"), frequency);
      where.append(" AND " + gt1.SHORTDESCRIPTION.equal(frequency));
    }

    return this.context
        .select(
            a.ID, a.NEDID,
            a.NAME, a.QUERY,
            gt1.SHORTDESCRIPTION.as("frequency"),
            a.CREATED, a.LASTMODIFIED)
        .from(a)
        .leftOuterJoin(gt1).on(a.FREQUENCYTYPEID.equal(gt1.ID))
        .where(where.toString().replace("\"", "`"))
        .fetch()
        .into(Alert.class);
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
            p.ISACTIVE, p.CREATED, p.LASTMODIFIED)
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
            d.ID, d.NEDID, d.FULLTITLE,
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

  private List<Auth> findAuthByNedId(Integer nedId) {

    Authcas a = AUTHCAS.as("a");
    Emails  e = EMAILS.as("e");

    return this.context
        .select(
            a.ID, a.NEDID, a.EMAILID,
            e.EMAILADDRESS.as("email"),
            a.AUTHID, a.PASSWORD, a.ISACTIVE,
            a.PASSWORDRESET, a.VERIFICATIONTOKEN,
            a.VERIFIED, a.CREATED, a.LASTMODIFIED)
        .from(a)
        .join(e).on(a.EMAILID.equal(e.ID))
        .where(a.NEDID.equal(nedId))
        .fetch()
        .into(Auth.class);
  }

  private List<Relationship> findRelationshipsByNedId(Integer nedId) {

    Globaltypes gt1 = GLOBALTYPES.as("gt1");
    Globaltypes gt2 = GLOBALTYPES.as("gt2");
    Relationships r = RELATIONSHIPS.as("r");

    // fetch ALL relationships that this entity participates (nedid OR nedidrelated)

    return this.context
        .select(
            r.ID, r.NEDID, r.NEDIDRELATED,
            gt1.SHORTDESCRIPTION.as("type"),
            gt2.SHORTDESCRIPTION.as("source"),
            r.TITLE, r.STARTDATE, r.ENDDATE,
            r.CREATED, r.LASTMODIFIED)
        .from(r)
        .leftOuterJoin(gt1).on(r.TYPEID.equal(gt1.ID))
        .leftOuterJoin(gt2).on(r.SOURCETYPEID.equal(gt2.ID))
        .where(r.NEDID.equal(nedId)).or(r.NEDIDRELATED.equal(nedId))
        .fetch()
        .into(Relationship.class);
  }

  private Uniqueidentifier findUniqueIdsByPrimaryKey(Integer id) {

    Uniqueidentifiers uid = UNIQUEIDENTIFIERS.as("uid");

    Record record = select(uid).where(uid.ID.equal(id)).fetchOne();

    if (record == null) 
      throw new NedException(EntityNotFound, String.format("Uniqueidentifier not found with id %d", id));

    return record.into(Uniqueidentifier.class);
  }

  private Auth findAuthCasByPrimaryKey(Integer id) {

    Authcas auth = AUTHCAS.as("auth");

    Record record = select(auth).where(auth.ID.equal(id)).fetchOne();

    if (record == null) 
      throw new NedException(EntityNotFound, String.format("Authcas not found with id %d", id));

    return record.into(Auth.class);
  }

  private Relationship findRelationshipByPrimaryKey(Integer id) {

    Relationships rel = RELATIONSHIPS.as("r");

    Record record = select(rel).where(rel.ID.equal(id)).fetchOne();

    if (record == null)
      throw new NedException(EntityNotFound, String.format("Relationship not found with id %d", id));

    return record.into(Relationship.class);
  }

  private Individualprofile findIndividualByPrimaryKey(Integer individualId) {

    Individualprofiles i   = INDIVIDUALPROFILES.as("i");

    Record record = select(i).where(i.ID.equal(individualId)).fetchOne();

    if (record == null)
      throw new NedException(EntityNotFound, String.format("Individual not found with id %d", individualId));

    return record.into(Individualprofile.class);
  }

  private Email findEmailByPrimaryKey(Integer emailId) {

    Emails e = EMAILS.as("e");

    Record record = select(e).where(e.ID.equal(emailId)).fetchOne();

    if (record == null)
      throw new NedException(EntityNotFound, String.format("Email not found with id %d", emailId));

    return record.into(Email.class);
  }

  private Address findAddressByPrimaryKey(Integer addressId) {

    Addresses a = ADDRESSES.as("a");

    Record record = select(a).where(a.ID.equal(addressId)).fetchOne();

    if (record == null)
      throw new NedException(EntityNotFound, String.format("Address not found with id %d", addressId));

    return record.into(Address.class);
  }

  private Alert findAlertByPrimaryKey(Integer entityId) {

    Alerts e = ALERTS.as("a");

    Record record = select(e).where(e.ID.equal(entityId)).fetchOne();

    if (record == null)
      throw new NedException(EntityNotFound, String.format("Alert not found with id %d", entityId));

    return record.into(Alert.class);
  }

  private Group findGroupByPrimaryKey(Integer groupId) {

    Groups g = GROUPS.as("g");

    Record record = select(g).where(g.ID.equal(groupId)).fetchOne();

    if (record == null)
      throw new NedException(EntityNotFound, String.format("Group not found with id %d", groupId));

    return record.into(Group.class);
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
    entityTableMap.put(Auth.class, new TablePkPair(AUTHCAS, AUTHCAS.ID));
    entityTableMap.put(Alert.class, new TablePkPair(ALERTS, ALERTS.ID));
    entityTableMap.put(Consumer.class, new TablePkPair(CONSUMERS, CONSUMERS.ID));
    entityTableMap.put(Degree.class, new TablePkPair(DEGREES, DEGREES.ID));
    entityTableMap.put(Email.class, new TablePkPair(EMAILS, EMAILS.ID));
    entityTableMap.put(Globaltype.class, new TablePkPair(GLOBALTYPES, GLOBALTYPES.ID));
    entityTableMap.put(Individualprofile.class, new TablePkPair(INDIVIDUALPROFILES, INDIVIDUALPROFILES.ID));
    entityTableMap.put(Namedentityidentifier.class, new TablePkPair(NAMEDENTITYIDENTIFIERS, NAMEDENTITYIDENTIFIERS.ID));
    entityTableMap.put(Organization.class, new TablePkPair(ORGANIZATIONS, ORGANIZATIONS.ID));
    entityTableMap.put(Phonenumber.class, new TablePkPair(PHONENUMBERS, PHONENUMBERS.ID));
    entityTableMap.put(Group.class, new TablePkPair(GROUPS, GROUPS.ID));
    entityTableMap.put(Typedescription.class, new TablePkPair(TYPEDESCRIPTIONS, TYPEDESCRIPTIONS.ID));
    entityTableMap.put(Uniqueidentifier.class, new TablePkPair(UNIQUEIDENTIFIERS, UNIQUEIDENTIFIERS.ID));
    entityTableMap.put(Url.class, new TablePkPair(URLS, URLS.ID));
    entityTableMap.put(Relationship.class, new TablePkPair(RELATIONSHIPS, RELATIONSHIPS.ID));
  }
  private static Table table(Class key) {
    return entityTableMap.get(key).table();
  }
  private static TableField pkField(Class key) {
    return entityTableMap.get(key).pkField();
  }
}
