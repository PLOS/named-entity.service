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
import org.jooq.Record2;
import org.jooq.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.Consumer;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.api.enums.UidTypeEnum;
import org.plos.namedentity.persist.db.namedentities.tables.Globaltypes;
import org.plos.namedentity.persist.db.namedentities.tables.Typedescriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidUrl;
import static org.plos.namedentity.api.NedException.ErrorType.ServerError;
import static org.plos.namedentity.persist.db.namedentities.Tables.GLOBALTYPES;
import static org.plos.namedentity.persist.db.namedentities.Tables.TYPEDESCRIPTIONS;
import static org.plos.namedentity.api.enums.NamedPartyEnum.*;
import static org.plos.namedentity.api.enums.TypeClassEnum.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/ambra-spring-beans.xml","/spring-beans.test.xml","/ambra-spring-beans.test.xml"})
public class NamedEntityDBServiceTest {

  @Autowired NamedEntityDBService         nedDBSvc;
  @Autowired DSLContext                   context;
  @Autowired DataSourceTransactionManager txMgr;

  @Test
  public void testTypeClassesAndTypeValues() {
    Globaltypes v = GLOBALTYPES.as("v");
    Typedescriptions t = TYPEDESCRIPTIONS.as("t"); 

    Result<Record2<String,String>> result = context
      .select(t.DESCRIPTION, v.SHORTDESCRIPTION)
      .from(t)
      .join(v).on(v.TYPEID.equal(t.ID))
      .fetch();

    assertTrue(result.size() >= 76);
  }

  @Test
  public void testExplicitTransactions() {
    boolean rollback = false;

    TransactionStatus tx = txMgr.getTransaction(new DefaultTransactionDefinition());
    try {
      // This should raise a unique constraint violation exception 
      context.insertInto(TYPEDESCRIPTIONS)
             .set(TYPEDESCRIPTIONS.ID, 1)
             .set(TYPEDESCRIPTIONS.DESCRIPTION, "Type Class (dupe)")
             .execute();

      Assert.fail();
    }
    // catch constraint and roll back transaction
    catch (DataAccessException e) {
      txMgr.rollback(tx);
      rollback = true;
    }

    assertTrue(rollback);
  }

  @Test
  public void testNamedEntityIdGeneration() throws Exception {

    Integer nedId1 = nedDBSvc.newNamedEntityId(INDIVIDUAL);
    Integer nedId2 = nedDBSvc.newNamedEntityId(INDIVIDUAL);
    assertTrue(nedId1.intValue() > 0);
    assertEquals(nedId2.intValue(), nedId1.intValue()+1);

    assertEquals(100L, (long)nedDBSvc.newNamedEntityId(INDIVIDUAL,100));

    try {
      assertEquals(100L, (long)nedDBSvc.newNamedEntityId(INDIVIDUAL,100));
      fail();
    }
    catch (org.springframework.dao.DataIntegrityViolationException e) {
      // dupe key db exception
    }

    assertEquals(nedId2.intValue()+1, (long)nedDBSvc.newNamedEntityId(INDIVIDUAL,null));
  }

  @Test
  public void testNonNullDbConstraint() throws Exception {

    Integer emailTypeClassId = nedDBSvc.findTypeClass("Email Address Types");

    // CREATE Work Email

    Email workEmail = new Email();
    workEmail.setNedid(1);
    workEmail.setTypeid(nedDBSvc.findTypeValue(emailTypeClassId, "Work"));

    try {
      Integer pkid = nedDBSvc.create( workEmail );
      fail("entity created " + pkid);
    } catch (NonTransientDataAccessException expected) {
      // this should happen because emailAddress is required
    }
  }

  @Test
  public void testTypedescriptionCRUD() {

    // CREATE
    Typedescription typedescription = new Typedescription();
    typedescription.setDescription("New Type Class");
    typedescription.setHowused("Yada yada");

    Integer newTypeClassId = nedDBSvc.create( typedescription );
    assertNotNull(newTypeClassId);

    // UPDATE description.
    Typedescription entity = nedDBSvc.findById(newTypeClassId, Typedescription.class);
    entity.setDescription( entity.getDescription() + "2");
    assertTrue( nedDBSvc.update(entity) );

    // Get another instance of same type class
    Typedescription entity2 = nedDBSvc.findById(newTypeClassId, Typedescription.class);
    assertEquals(entity, entity2);

    // Find all type classes
    List<Typedescription> typeClasses = nedDBSvc.findAll(Typedescription.class, 0, Integer.MAX_VALUE);
    assertTrue(typeClasses.size() >= 20);

    // Try to find a type class which doesn't exist
    Typedescription entity3 = nedDBSvc.findById(666, Typedescription.class);
    assertNull(entity3);

    // DELETE. we should be able to delete newly added type class because
    // it doesn't have any values associated with it (ie, has no globaltypes).

    Typedescription typeDescription = new Typedescription();
    typeDescription.setId(newTypeClassId);

    assertTrue( nedDBSvc.delete(typeDescription) );

    // However, trying to delete a type class with type values should 
    // raise a foreign key constraint exception.

    try {
      typeDescription.setId(1);
      nedDBSvc.delete(typeDescription);
      fail();
    }
    catch (DataAccessException ignore) {
      // declarative transaction will rollback changes on exception
    }
  }

  @Test
  public void testGlobaltypesCRUD() {

    Globaltype newGlobaltype = new Globaltype();
    newGlobaltype.setTypeid( nedDBSvc.findTypeClass("Named Party Types") );
    newGlobaltype.setShortdescription("Group XYZ");
    newGlobaltype.setTypecode("GRPX");
    newGlobaltype.setLastmodified(new Timestamp(new Date().getTime()));
    newGlobaltype.setCreated(new Timestamp(new Date().getTime()));

    assertNull(newGlobaltype.getId());
    assertNotNull(newGlobaltype.getTypeid());
    assertNotNull(newGlobaltype.getCreated());
    assertNotNull(newGlobaltype.getLastmodified());

    // CREATE
    Integer newGlobalTypeId = nedDBSvc.create( newGlobaltype );
    assertNotNull(newGlobalTypeId);

    // UPDATE short description.
    Globaltype entity = nedDBSvc.findById(newGlobalTypeId, Globaltype.class);
    entity.setShortdescription( entity.getShortdescription() + "2");
    assertTrue( nedDBSvc.update(entity) );

    Globaltype entity2 = nedDBSvc.findById(newGlobalTypeId, Globaltype.class);
    assertEquals(entity, entity2);

    // Find all global types 
    List<Globaltype> globalTypes = nedDBSvc.findAll(Globaltype.class, 0, Integer.MAX_VALUE);
    assertTrue(globalTypes.size() >= 75);

    // Find global types for a type class
    Globaltype typeClassFilter = new Globaltype();
    typeClassFilter.setTypeid(1);
    List<Globaltype> globalTypesForTypeClass = nedDBSvc.findByAttribute(typeClassFilter);
    assertEquals(3, globalTypesForTypeClass.size());

    // Try to find a global type which doesn't exist
    Globaltype entity3 = nedDBSvc.findById(666, Globaltype.class);
    assertNull(entity3);

    // DELETE
    Globaltype typeValueToDelete = new Globaltype();
    typeValueToDelete.setId(newGlobalTypeId);

    assertTrue( nedDBSvc.delete(typeValueToDelete) );

    // TODO: add foreign key constraint voilation test for globalTypes.
  }

  @Test
  public void testGlobaltypeValueCaseInsensitiveComparison() {

    Integer countryTypeId = nedDBSvc.findTypeClass("Country Types");

    String usa = "United States of America";
    String countryInput[] = new String[] { usa, usa.toLowerCase(), usa.toUpperCase() };

    for (String country : countryInput) {
      assertNotNull( nedDBSvc.findTypeValue(countryTypeId,country) );
    }
  }

  @Test
  public void testEmailsCRUD() {

    Integer nedId = nedDBSvc.newNamedEntityId(INDIVIDUAL);
    Integer emailTypeClassId = nedDBSvc.findTypeClass("Email Address Types");

    // CREATE Work Email

    Email workEmail = _(new Email());
    workEmail.setNedid(nedId);
    workEmail.setTypeid(nedDBSvc.findTypeValue(emailTypeClassId, "Work"));
    workEmail.setEmailaddress("walter.work@foo.com");
    workEmail.setSourcetypeid( getSourceTypeId(UidTypeEnum.EDITORIAL_MANAGER.getName()) );

    assertNull(workEmail.getId());
    assertNotNull(workEmail.getNedid());
    assertNotNull(workEmail.getTypeid());

    Integer workEmailId = nedDBSvc.create( workEmail );
    assertNotNull(workEmailId);

    // Verify CREATED and LASTMODIFIED attributes populated (by db)

    Email savedWorkEmail = nedDBSvc.findById(workEmailId, Email.class);

    Timestamp created1      = savedWorkEmail.getCreated()      ; assertNotNull(created1);
    Timestamp lastModified1 = savedWorkEmail.getLastmodified() ; assertNotNull(lastModified1);
    assertEquals(created1, lastModified1);

    // Inserting duplicate email address should fail

    try {
      nedDBSvc.create( workEmail );
      fail("duplicate email saved: " + workEmail);
    }
    catch (NonTransientDataAccessException expected) {
      // expected
    }

    // UPDATE #1 : Try to update record violating Not Null Constraint

    Integer savedSourceTypeId = savedWorkEmail.getSourcetypeid();
    try {
      savedWorkEmail.setSourcetypeid(null);
      assertFalse( nedDBSvc.update(savedWorkEmail) );
      fail("entity updated: " + savedWorkEmail);
    }
    catch (NonTransientDataAccessException expected) {
      // this is expected because source type id is required (ie, not null)
      // restore source type before continuing.
      savedWorkEmail.setSourcetypeid(savedSourceTypeId);
    }

    // sleep for a second
    try { Thread.sleep(1000); } catch(InterruptedException ex) { }

    // UPDATE #2 : Work Email and Status

    savedWorkEmail.setEmailaddress("super." + savedWorkEmail.getEmailaddress());
    savedWorkEmail.setIsactive(false);
    assertTrue( nedDBSvc.update(savedWorkEmail) );

    // Get another instance of same email record.

    Email savedWorkEmail2 = nedDBSvc.findById(workEmailId, Email.class);
    assertEquals(savedWorkEmail, savedWorkEmail2);

    // Verify CREATED hasn't changed and LASTMODIFIED has.

    Timestamp created2      = savedWorkEmail2.getCreated()      ; assertNotNull(created2);
    Timestamp lastModified2 = savedWorkEmail2.getLastmodified() ; assertNotNull(lastModified2);
    assertEquals(created1, created2);
    assertTrue( lastModified1.before(lastModified2) );

    // CREATE Home Email

    Email homeEmail = _(new Email());
    homeEmail.setNedid(nedId);
    homeEmail.setTypeid(nedDBSvc.findTypeValue(emailTypeClassId, "Personal"));
    homeEmail.setEmailaddress("walter.home@foo.com");
    homeEmail.setSourcetypeid( getSourceTypeId(UidTypeEnum.EDITORIAL_MANAGER.getName()) );

    assertNull(homeEmail.getId());
    assertNotNull(homeEmail.getNedid());
    assertNotNull(homeEmail.getTypeid());

    Integer homeEmailId = nedDBSvc.create( homeEmail );
    assertNotNull(homeEmailId);

    // FIND ALL Email Records 

    List<Email> allEmailsInDB = nedDBSvc.findAll(Email.class, 0, Integer.MAX_VALUE);
    assertTrue( allEmailsInDB.size() >= 2 );

    // FIND BY ATTRIBUTE (Lookup email by address)

    Email emailSearchByAddress = new Email();
    emailSearchByAddress.setEmailaddress("super.walter.work@foo.com");
    emailSearchByAddress.setIsactive(false);
    List<Email> foundEmails = nedDBSvc.findByAttribute(emailSearchByAddress);
    assertEquals(1, foundEmails.size());
    assertEquals("super.walter.work@foo.com", foundEmails.get(0).getEmailaddress());

    // FIND BY ATTRIBUTE (Lookup email addresses by nedid)
    // Do 3 lookups: only active, only inactive, both active and inactive
    
    Boolean[] isactiveFilter = { true, false, null };
    int[]     expectedEmailCount = { 1, 1, 2 };

    for (int i = 0; i < expectedEmailCount.length; i++) {
      Email emailSearchByIndividual = new Email();
      emailSearchByIndividual.setNedid(nedId);
      emailSearchByIndividual.setIsactive( isactiveFilter[i] );
      List<Email> foundEmails2 = nedDBSvc.findByAttribute(emailSearchByIndividual);
      assertEquals(expectedEmailCount[i], foundEmails2.size());

      for (Email email : allEmailsInDB) {
        assertTrue( allEmailsInDB.contains(email) );
      }
    }

    // FIND BY PRIMARY KEY

    for (Email email : allEmailsInDB) {
      assertNotNull( nedDBSvc.findResolvedEntityByKey(email.getId(), Email.class) );
    }
    // FIND BY JOIN-QUERY 
    List<Email> emails = nedDBSvc.findResolvedEntities(nedId, Email.class);

    assertTrue( emails.size() > 0 );
    assertNotNull(emails.get(0).getNedid());

    // Try to find an email which doesn't exist

    Email entity3 = nedDBSvc.findById(666, Email.class);
    assertNull(entity3);

    // DELETE

    Email emailToDelete = new Email();
    emailToDelete.setId(workEmailId);

    assertTrue( nedDBSvc.delete(emailToDelete) );
  }

  @Test
  public void testIndividualCRUD() {

    // CREATE

    Integer nedId = nedDBSvc.newNamedEntityId(INDIVIDUAL);

    Integer prefixTypeClassId      = nedDBSvc.findTypeClass("Named Party Prefixes");
    Integer suffixTypeClassId      = nedDBSvc.findTypeClass("Named Party Suffixes");
    Integer langTypeClassId        = nedDBSvc.findTypeClass("Languages");
    Integer commMethodsTypeClassId = nedDBSvc.findTypeClass("Communication Methods");

    Integer prefixTypeId     = nedDBSvc.findTypeValue(prefixTypeClassId, "Mr.")        ; assertNotNull(prefixTypeId)           ;
    Integer suffixTypeId     = nedDBSvc.findTypeValue(suffixTypeClassId, "II")         ; assertNotNull(suffixTypeId)           ;
    Integer langTypeId       = nedDBSvc.findTypeValue(langTypeClassId, "Italian")      ; assertNotNull(langTypeClassId)        ;
    Integer commMethodTypeId = nedDBSvc.findTypeValue(commMethodsTypeClassId, "Email") ; assertNotNull(commMethodsTypeClassId) ;

    Individualprofile individualProfile = _(new Individualprofile());
    individualProfile.setNedid(nedId);
    individualProfile.setFirstname("firstname");
    individualProfile.setMiddlename("middlename");
    individualProfile.setLastname("lastname");
    individualProfile.setDisplayname("displayname_i_nes");
    individualProfile.setBiography("bio");
    individualProfile.setNameprefixtypeid(prefixTypeId);
    individualProfile.setNamesuffixtypeid(suffixTypeId);
    individualProfile.setSourcetypeid( getSourceTypeId(UidTypeEnum.EDITORIAL_MANAGER.getName()) );

    Integer individualId = nedDBSvc.create(individualProfile);
    assertNotNull(individualId);

    // UPDATE
    
    Individualprofile savedIndividualProfile = nedDBSvc.findById(individualId, Individualprofile.class);
    Integer savedProfileId = savedIndividualProfile.getId();
    savedIndividualProfile.setMiddlename("chuck");

    try {
      // Try to update without a primary key. This should fail.
      savedIndividualProfile.setId(null);
      nedDBSvc.update(savedIndividualProfile);
      fail();
    } catch (NedException expected) { }

    // Restore primary key and try to update again. This should succeed.
    savedIndividualProfile.setId(savedProfileId);
    assertTrue( nedDBSvc.update(savedIndividualProfile) );

    // Get another instance of same individual record
    Individualprofile savedIndividualProfileAfterUpdate = nedDBSvc.findById(individualId, Individualprofile.class);
    assertEquals(savedIndividualProfile, savedIndividualProfileAfterUpdate);

    // FIND ALL Email Records 

    List<Individualprofile> allIndividualsInDB = nedDBSvc.findAll(Individualprofile.class, 0, Integer.MAX_VALUE);
    assertTrue(allIndividualsInDB.size() > 0);

    // FIND BY JOIN-QUERY 

    List<Individualprofile> entities = nedDBSvc.findResolvedEntities(nedId, Individualprofile.class);
    assertNotNull( entities );
    assertEquals("firstname", entities.get(0).getFirstname());
    assertEquals("Mr.", entities.get(0).getNameprefix());
    assertEquals("II", entities.get(0).getNamesuffix());
    assertEquals(nedId, entities.get(0).getNedid());

    // DELETE

    Individualprofile individualProfileToDelete = new Individualprofile();
    individualProfileToDelete.setId(individualId);

    assertTrue( nedDBSvc.delete(individualProfileToDelete) );
  }

  @Test
  public void testOrganizationCRUD() {

    // CREATE

    Integer nedId = nedDBSvc.newNamedEntityId(ORGANIZATION);

    Integer organizationTypeId = nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Organization Types"), "University");

    Organization organization = _(new Organization());
    organization.setNedid(nedId);
    organization.setTypeid(organizationTypeId);
    organization.setFamiliarname("familiarname");
    organization.setLegalname("legalname");
    organization.setIsactive(true);
    organization.setSourcetypeid( getSourceTypeId(UidTypeEnum.EDITORIAL_MANAGER.getName()) );

    Integer organizationId = nedDBSvc.create(organization);
    assertNotNull(organizationId);

    // UPDATE
    Organization savedEntity = nedDBSvc.findById(organizationId, Organization.class);
    savedEntity.setLegalname("legalname2");
    assertTrue(nedDBSvc.update(savedEntity));

    // Get another instance of same individual record
    Organization savedEntity2 = nedDBSvc.findById(organizationId, Organization.class);
    assertEquals(savedEntity, savedEntity2);

    // FIND ALL Email Records

    List<Organization> allEntitiesInDB = nedDBSvc.findAll(Organization.class, 0, Integer.MAX_VALUE);
    assertTrue(allEntitiesInDB.size() > 0);

  }

  @Test
  public void testPhoneNumberCRUD() {

    // CREATE (Mobile Phone)

    Integer phoneTypeClassId       = nedDBSvc.findTypeClass("Telephone Number Types");
    Integer countryCodeTypeClassId = nedDBSvc.findTypeClass("Country Codes for Phone Numbers");

    Integer officePhoneTypeId = nedDBSvc.findTypeValue(phoneTypeClassId, "Office") ; assertNotNull(officePhoneTypeId);
    Integer mobilePhoneTypeId = nedDBSvc.findTypeValue(phoneTypeClassId, "Mobile") ; assertNotNull(mobilePhoneTypeId);

    Integer usaCountryCodeTypeId = nedDBSvc.findTypeValue(countryCodeTypeClassId, "1");
    assertNotNull(usaCountryCodeTypeId); 

    Phonenumber mobilePhone = _(new Phonenumber());
    mobilePhone.setNedid(1);
    mobilePhone.setTypeid(mobilePhoneTypeId);
    mobilePhone.setCountrycodetypeid(usaCountryCodeTypeId);
    mobilePhone.setPhonenumber("650-123-4567");
    mobilePhone.setSourcetypeid( getSourceTypeId(UidTypeEnum.EDITORIAL_MANAGER.getName()) );

    assertNull(mobilePhone.getId());
    assertNotNull(mobilePhone.getNedid());

    Integer mobilePhoneId = nedDBSvc.create( mobilePhone );
    assertNotNull(mobilePhoneId);

    // UPDATE

    Phonenumber savedMobilePhone = nedDBSvc.findById(mobilePhoneId, Phonenumber.class);
    savedMobilePhone.setExtension("xxx");
    assertTrue( nedDBSvc.update(savedMobilePhone) );

    // Get another instance of same email record 

    Phonenumber savedMobilePhone2 = nedDBSvc.findById(mobilePhoneId, Phonenumber.class);
    assertEquals(savedMobilePhone, savedMobilePhone2);

    // CREATE (Office Phone)

    Phonenumber officePhone = _(new Phonenumber());
    officePhone.setNedid(1);
    officePhone.setTypeid(officePhoneTypeId);
    officePhone.setCountrycodetypeid(usaCountryCodeTypeId);
    officePhone.setPhonenumber("650-222-9876");
    officePhone.setSourcetypeid( getSourceTypeId(UidTypeEnum.EDITORIAL_MANAGER.getName()) );

    assertNull(officePhone.getId());
    assertNotNull(officePhone.getNedid());

    Integer officePhoneId = nedDBSvc.create( officePhone );
    assertNotNull(officePhoneId);

    // FIND ALL Phone Numbers 

    List<Phonenumber> allPhonenumbersInDb = nedDBSvc.findAll(Phonenumber.class, 0, Integer.MAX_VALUE);
    assertTrue( allPhonenumbersInDb.size() >= 2 );
            
    //TODO : FIND BY ATTRIBUTE (Lookup email by phone number)

    // FIND BY ATTRIBUTE (Lookup phone numbers for an individual)

    Phonenumber phoneSearchByIndividual = new Phonenumber();
    phoneSearchByIndividual.setNedid(1);
    List<Phonenumber> foundPhones = nedDBSvc.findByAttribute(phoneSearchByIndividual);
    assertEquals(2, foundPhones.size());

    for (Phonenumber phone : foundPhones) {
      assertTrue( allPhonenumbersInDb.contains(phone) );
    }

    // FIND BY JOIN-QUERY 

    List<Phonenumber> phonenumbers = nedDBSvc.findResolvedEntities(foundPhones.get(0).getNedid(), Phonenumber.class);
    assertTrue( phonenumbers.size() > 0 );
    assertNotNull(phonenumbers.get(0).getNedid());

    // DELETE

    Phonenumber phoneToDelete = new Phonenumber();

    phoneToDelete.setId(mobilePhoneId);
    assertTrue( nedDBSvc.delete(phoneToDelete) );

    phoneToDelete.setId(officePhoneId);
    assertTrue( nedDBSvc.delete(phoneToDelete) );
  }

  @Test
  public void testAddressCRUD() {

    // CREATE

    Integer addressTypeClassId   = nedDBSvc.findTypeClass("Physical Address Types");
    Integer countryTypeClassId   = nedDBSvc.findTypeClass("Country Types");
    Integer stateCodeTypeClassId = nedDBSvc.findTypeClass("State and Province Codes");

    Integer officeAddressTypeId = nedDBSvc.findTypeValue(addressTypeClassId, "Office")                  ; assertNotNull(officeAddressTypeId);
    Integer countryTypeId       = nedDBSvc.findTypeValue(countryTypeClassId, "United States of America"); assertNotNull(countryTypeId)      ;
    Integer stateCodeTypeId     = nedDBSvc.findTypeValue(stateCodeTypeClassId, "CA")                    ; assertNotNull(stateCodeTypeId)    ;

    Address address = _(new Address());
    address.setNedid(1);
    address.setTypeid(officeAddressTypeId);
    address.setAddressline1("addressline1");
    address.setAddressline2("addressline2");
    address.setAddressline3("addressline3");
    address.setCity("San Francisco");
    address.setStatecodetypeid(stateCodeTypeId);
    address.setCountrycodetypeid(countryTypeId);
    address.setPostalcode("94501");
    address.setSourcetypeid( getSourceTypeId(UidTypeEnum.EDITORIAL_MANAGER.getName()) );

    assertNull(address.getId());
    assertNotNull(address.getNedid());

    Integer addressId = nedDBSvc.create( address );
    assertNotNull(addressId);

    // UPDATE

    Address savedAddress = nedDBSvc.findById(addressId, Address.class);
    savedAddress.setAddressline2("updated addressline2");
    assertTrue( nedDBSvc.update(savedAddress) );

    // Get another instance of same address record.

    Address savedAddress2 = nedDBSvc.findById(addressId, Address.class);
    assertEquals(savedAddress, savedAddress2);

    // FIND ALL Phone Numbers 

    List<Address> allAddressesInDb = nedDBSvc.findAll(Address.class, 0, Integer.MAX_VALUE);
    assertTrue( allAddressesInDb.size() > 0 );

    // FIND BY JOIN-QUERY 

    List<Address> addresses = nedDBSvc.findResolvedEntities(savedAddress.getNedid(), Address.class);
    assertTrue( addresses.size() > 0 );
    assertEquals(address.getNedid(), addresses.get(0).getNedid());
            
    // DELETE

    Address addressToDelete = new Address();

    addressToDelete.setId(addressId);
    assertTrue( nedDBSvc.delete(addressToDelete) );
  }

  @Test
  public void testGroupCRUD() {

    // CREATE

    Integer srcAppTypeClassId = nedDBSvc.findTypeClass(USER_APPLICATIONS.getName());
    Integer srcAppTypeId = nedDBSvc.findTypeValue(srcAppTypeClassId, "Named party DB");
    assertNotNull(srcAppTypeId);

    Integer groupTypeClassId  = nedDBSvc.findTypeClass(GROUPS.getName());
    Integer groupTypeId = nedDBSvc.findTypeValue(groupTypeClassId, "NED Manage Users");
    assertNotNull(groupTypeId);

    Group kbPlosOneGrp = _(new Group());
    kbPlosOneGrp.setNedid(1);
    kbPlosOneGrp.setApplicationtypeid(srcAppTypeId);
    kbPlosOneGrp.setTypeid(groupTypeId);

    java.sql.Date startDate = dateNow();
    kbPlosOneGrp.setStartdate(startDate);

    kbPlosOneGrp.setLastmodified(new Timestamp(Calendar.getInstance().getTime().getTime()));
    kbPlosOneGrp.setCreated(new Timestamp(Calendar.getInstance().getTime().getTime()));
    kbPlosOneGrp.setSourcetypeid( getSourceTypeId("Ambra") );

    assertNull(kbPlosOneGrp.getId());
    assertNotNull(kbPlosOneGrp.getNedid());

    Integer groupId = nedDBSvc.create( kbPlosOneGrp );
    assertNotNull(groupId);

    // UPDATE

    Group savedGroup = nedDBSvc.findById(groupId, Group.class);

    java.sql.Date endDate = dateNow();
    savedGroup.setEnddate(endDate);

    assertTrue( nedDBSvc.update(savedGroup) );

    // Get another instance of same group record

    Group savedGroup2 = nedDBSvc.findById(groupId, Group.class);
    assertEquals(savedGroup, savedGroup2);
    assertEquals(savedGroup.getStartdate(), savedGroup2.getStartdate());
    assertEquals(savedGroup.getEnddate(), savedGroup2.getEnddate());

    // FIND (all group assignments)

    List<Group> groupAssignments = nedDBSvc.findAll(Group.class, 0, Integer.MAX_VALUE);
    assertTrue( groupAssignments.size() > 0 );

    // FIND BY JOIN-QUERY (all groups assigned to a person)

    List<Group> groups = nedDBSvc.findResolvedEntities(savedGroup.getNedid(), Group.class);
    Group group = groups.get(0);
    assertEquals("NED Manage Users", group.getType());
    assertEquals(kbPlosOneGrp.getNedid(), group.getNedid());

    // DELETE

    Group groupToDelete = new Group();
    groupToDelete.setId(groupId);
    assertTrue( nedDBSvc.delete(groupToDelete) );
  }


  @Test
  public void testAlertCRUD() {

    // CREATE

    Integer typeId = nedDBSvc.findTypeValue(nedDBSvc.findTypeClass(ALERT_TYPES.getName()), "journal");
    assertNotNull(typeId);

    Integer frequencyId = nedDBSvc.findTypeValue(nedDBSvc.findTypeClass(ALERT_FREQUENCY.getName()), "weekly");
    assertNotNull(frequencyId);

    Integer journalId = nedDBSvc.findTypeValue(nedDBSvc.findTypeClass(JOURNAL_TYPES.getName()), "PLOS Biology");
    assertNotNull(journalId);

    Alert entity = _(new Alert());
    entity.setNedid(1);
    entity.setJournaltypeid(journalId);
    entity.setFrequencytypeid(frequencyId);
    entity.setTypeid(typeId);
    entity.setName("name goes here");
    entity.setQuery("{query goes here}");

    entity.setLastmodified(new Timestamp(Calendar.getInstance().getTime().getTime()));
    entity.setCreated(new Timestamp(Calendar.getInstance().getTime().getTime()));
    entity.setSourcetypeid( getSourceTypeId("Ambra") );

    Integer entityId = nedDBSvc.create( entity );
    assertNotNull(entityId);

    // UPDATE

    Alert savedEntity = nedDBSvc.findById(entityId, Alert.class);

    savedEntity.setQuery("a new query");

    assertTrue( nedDBSvc.update(savedEntity) );

    // Get another instance of same entity

    Alert savedEntityAfterUpdate = nedDBSvc.findById(entityId, Alert.class);
    assertEquals(savedEntity, savedEntityAfterUpdate);
    assertEquals(savedEntity.getQuery(), savedEntityAfterUpdate.getQuery());
    assertEquals(savedEntity.getName(), savedEntityAfterUpdate.getName());

    // FIND (all assignments)

    List<Alert> entityList = nedDBSvc.findAll(Alert.class, 0, Integer.MAX_VALUE);
    assertTrue( entityList.size() == 1 );

    // DELETE

    Alert entityToDelete = new Alert();
    entityToDelete.setId(entityId);
    assertTrue( nedDBSvc.delete(entityToDelete) );
  }

  @Test
  public void testRelationshipCRUD() {

    // CREATE

    Integer relationshipTypeClassId = nedDBSvc.findTypeClass("Relationship Types");
    Integer relationshipTypeId = nedDBSvc.findTypeValue(relationshipTypeClassId, "Organization-Author");
    assertNotNull(relationshipTypeId);

    // create a relationship where individual participates on the "related" id
    // side. relationship should still be found when querying all relationships for
    // individual by ned id (below).

    Relationship orgAuthRel = _(new Relationship());
    orgAuthRel.setTypeid(relationshipTypeId);
    orgAuthRel.setNedid(2);        /* seeded organization */
    orgAuthRel.setNedidrelated(1); /* seeded individual   */
    orgAuthRel.setStartdate(dateNow());
    orgAuthRel.setSourcetypeid( getSourceTypeId(UidTypeEnum.EDITORIAL_MANAGER.getName()) );

    Integer relationshipId = nedDBSvc.create( orgAuthRel );
    assertNotNull(relationshipId);

    // UPDATE

    Relationship savedRelationship = nedDBSvc.findById(relationshipId, Relationship.class);

    savedRelationship.setEnddate(dateNow());

    assertTrue( nedDBSvc.update(savedRelationship) );

    // Get another instance of same relationship record

    Relationship savedRelationship2 = nedDBSvc.findById(relationshipId, Relationship.class);
    assertEquals(savedRelationship, savedRelationship2);
    assertEquals(savedRelationship.getStartdate(), savedRelationship2.getStartdate());
    assertEquals(savedRelationship.getEnddate(), savedRelationship2.getEnddate());

    // FIND ALL Relationships

    List<Relationship> allRelationshipsInDb = nedDBSvc.findAll(Relationship.class, 0, Integer.MAX_VALUE);
    assertTrue( allRelationshipsInDb.size() > 0 );

    // FIND BY JOIN-QUERY 

    List<Relationship> relationships = nedDBSvc.findResolvedEntities(savedRelationship.getNedid(), Relationship.class);

    // relationship set size may vary depending how test is run. if all tests
    // are run, size will be 2 (new relationship above + relationship defined in
    // composite). size will only be 1 if this test class is run standalone. a
    // bit brittle; refactor.

    assertTrue(relationships.size() > 0);

    boolean foundRelationship = false;
    for (Relationship r : relationships) {
      if (r.getId().equals(relationshipId)) {
        assertEquals(orgAuthRel.getNedidrelated(), r.getNedidrelated());
        assertEquals(relationshipId, r.getId());
        assertEquals(orgAuthRel.getNedid(), r.getNedid());
        assertEquals(orgAuthRel.getStartdate(), r.getStartdate());
        foundRelationship = true;
      }
    }
    if (!foundRelationship) fail();

    // DELETE

    Relationship relationshipToDelete = new Relationship();
    relationshipToDelete.setId(relationshipId);
    assertTrue( nedDBSvc.delete(relationshipToDelete) );
  }

  @Test
  public void testUniqueIdentifiersCRUD() {

    final String ORCID_ID = "0000-0001-9430-319X_UIDSERVICE";

    // FIND Individuals with an ORCID id. There should be none. 

    try {
      nedDBSvc.findResolvedEntityByUid(UidTypeEnum.ORCID.getName(), ORCID_ID, Individualprofile.class);
    } catch (NedException expected) {
      assertEquals(EntityNotFound, expected.getErrorType());
    }

    // Create two individuals with the same Orcid#

    Integer uidIdType = nedDBSvc.findTypeClass(UID_INDIVIDUAL_TYPES.getName());

    Integer orcidTypeId  = nedDBSvc.findTypeValue(uidIdType, UidTypeEnum.ORCID.getName());
    assertNotNull(orcidTypeId);

    Integer nedId = nedDBSvc.newNamedEntityId(INDIVIDUAL);

    Individualprofile individualProfile = _(new Individualprofile());
    individualProfile.setNedid(nedId);
    individualProfile.setFirstname("firstname");
    individualProfile.setMiddlename("middlename");
    individualProfile.setLastname("lastname");
    individualProfile.setDisplayname("displayname_u");
    individualProfile.setSourcetypeid( getSourceTypeId(UidTypeEnum.EDITORIAL_MANAGER.getName()) );

    assertNotNull(nedDBSvc.create(individualProfile));

    Uniqueidentifier uidEntity1 = _(new Uniqueidentifier());
    uidEntity1.setNedid(nedId);
    uidEntity1.setTypeid(orcidTypeId);
    uidEntity1.setUniqueidentifier(ORCID_ID);
    uidEntity1.setMetadata("[{'sometimes':'json','goes':'here'},{'sometimes':'not'}]");
    uidEntity1.setSourcetypeid( getSourceTypeId(UidTypeEnum.EDITORIAL_MANAGER.getName()) );

    assertNull(uidEntity1.getId());
    assertNotNull(uidEntity1.getNedid());

    Integer uidId1 = nedDBSvc.create( uidEntity1 );
    assertNotNull(uidId1);

    // FIND By UID (ORCID) #2
    assertNotNull(nedDBSvc.findResolvedEntityByUid(UidTypeEnum.ORCID.getName(), ORCID_ID, Individualprofile.class));

    // UPDATE

    Uniqueidentifier savedUid = nedDBSvc.findById(uidId1, Uniqueidentifier.class);
    savedUid.setUniqueidentifier(savedUid.getUniqueidentifier() + "Z");
    assertTrue( nedDBSvc.update(savedUid) );

    // Get another instance of same uid record

    Uniqueidentifier savedUid2 = nedDBSvc.findById(uidId1, Uniqueidentifier.class);
    assertEquals(savedUid, savedUid2);

    // Restore orcid id.

    savedUid.setUniqueidentifier(ORCID_ID);
    assertTrue( nedDBSvc.update(savedUid) );

    // FIND BY JOIN-QUERY

    List<Uniqueidentifier> uids = nedDBSvc.findResolvedEntities(savedUid.getNedid(), Uniqueidentifier.class);

    assertEquals(ORCID_ID, uids.get(0).getUniqueidentifier());
    assertEquals(nedId, uids.get(0).getNedid());
  }

  @Test
  public void testUrlCRUD() {

    // CREATE : Invalid URL

    try {    
      Url url = new Url();
      url.setUrl("htp:/www.plos.org");
      url.validate();
    } catch (NedException expected) {
      assertEquals(InvalidUrl, expected.getErrorType());
    }

    // CREATE : No URL! 

    try {    
      Url url = new Url();
      url.setNedid(1);
      url.setSourcetypeid( getSourceTypeId(UidTypeEnum.AMBRA.getName()) );

      url.validate();

      nedDBSvc.create( url );
    }
    catch (NonTransientDataAccessException expected) {
      // MySQL ErrorCode: 1364, SQLState:HY000 (ER_NO_DEFAULT_FOR_FIELD)
      assertEquals(1364, ((SQLException)expected.getCause()).getErrorCode());
    }

    // CREATE : Valid URL

    Url url = _(new Url());
    url.setNedid(1);
    url.setUrl("http://www.plos.org");
    url.setSourcetypeid( getSourceTypeId(UidTypeEnum.AMBRA.getName()) );

    url.validate();

    Integer urlId = nedDBSvc.create( url );
    assertNotNull(urlId);

    // UPDATE

    Url savedUrl = nedDBSvc.findById(urlId, Url.class);
    savedUrl.setUrl("http://www.plos2.org");
    assertTrue( nedDBSvc.update(savedUrl) );

    // Get another instance of same address record.

    Url savedUrl2 = nedDBSvc.findById(urlId, Url.class);
    assertEquals(savedUrl, savedUrl2);

    // FIND ALL URL's 

    List<Url> allUrlsInDb = nedDBSvc.findAll(Url.class, 0, Integer.MAX_VALUE);
    assertTrue( allUrlsInDb.size() > 0 );

    // FIND BY JOIN-QUERY 

    List<Url> urls = nedDBSvc.findResolvedEntities(savedUrl.getNedid(), Url.class);
    assertTrue( urls.size() > 0 );
    assertEquals(url.getNedid(), urls.get(0).getNedid());

    // DELETE

    Url urlToDelete = new Url();
    urlToDelete.setId(urlId);
    assertTrue( nedDBSvc.delete(urlToDelete) );
  }

  @Test
  public void testDegreeCRUD() {

    // CREATE

    Integer degreeType = nedDBSvc.findTypeClass(DEGREES.getName());

    Degree degree = _(new Degree());
    degree.setNedid(1);
    degree.setTypeid( nedDBSvc.findTypeValue(degreeType,"Doctorate") );
    degree.setSourcetypeid( getSourceTypeId(UidTypeEnum.AMBRA.getName()) );

    Integer degreeId = nedDBSvc.create(degree);
    assertNotNull(degreeId);

    // UPDATE

    Degree savedDegree = nedDBSvc.findById(degreeId, Degree.class);
    savedDegree.setFulltitle("Ph.D Physics");
    assertTrue( nedDBSvc.update(savedDegree) );

    // Get another instance of same degree record.

    Degree savedDegree2 = nedDBSvc.findById(degreeId, Degree.class);
    assertEquals(savedDegree, savedDegree2);

    // FIND ALL DEGREE's 

    List<Degree> allDegreesInDb = nedDBSvc.findAll(Degree.class, 0, Integer.MAX_VALUE);
    assertTrue( allDegreesInDb.size() > 0 );

    // FIND BY JOIN-QUERY 

    List<Degree> degrees = nedDBSvc.findResolvedEntities(savedDegree.getNedid(), Degree.class);
    assertTrue( degrees.size() > 0 );
    assertEquals(degree.getNedid(), degrees.get(0).getNedid());

    // DELETE

    Degree degreeToDelete = new Degree();
    degreeToDelete.setId(degreeId);
    assertTrue( nedDBSvc.delete(degreeToDelete) );
  }

  @Test
  public void testConsumerTableFinders() {

    List<Consumer> allConsumersInDb = nedDBSvc.findAll(Consumer.class, 0, Integer.MAX_VALUE);
    assertTrue( allConsumersInDb.size() > 0 );

    String[] registeredApps = { "test" };

    for (String app : registeredApps) {
      Consumer filter = new Consumer();
      filter.setName(app);
      List<Consumer> consumers = nedDBSvc.findByAttribute(filter);
      assertEquals(1, consumers.size());
      assertEquals(app, consumers.get(0).getName());
      assertTrue(BCrypt.checkpw(app, consumers.get(0).getPassword()));
    }

    Consumer filter = new Consumer() ; filter.setName("bleck");
    List<Consumer> consumers = nedDBSvc.findByAttribute(filter);
    assertEquals(0, consumers.size());
  }

  @Test
  public void testValidate() {

    Integer individualNedId       = nedDBSvc.newNamedEntityId(INDIVIDUAL);
    Integer organizationNedId     = nedDBSvc.newNamedEntityId(ORGANIZATION);

    Integer individualUidTypeId   = nedDBSvc.findTypeClass("UID Individual Types");
    Integer orcidTypeId           = nedDBSvc.findTypeValue(individualUidTypeId, UidTypeEnum.ORCID.getName());
    String  orcidTypeValue        = "0000-0001-9430-319X_UIDSERVICE";

    Integer organizationUidTypeId = nedDBSvc.findTypeClass("UID Organization Types");
    Integer ringgoldTypeId        = nedDBSvc.findTypeValue(organizationUidTypeId, UidTypeEnum.RINGGOLD.getName());
    String  ringgoldTypeValue     = "307713"; // ringgold id (aka, p_code)

    Integer[] nedIds = { individualNedId, organizationNedId };

    // VALID ENTITY/UID COMBOS

    Integer[] validUidTypeIds     = { orcidTypeId, ringgoldTypeId };
    String[]  validUidTypeValues  = { orcidTypeValue, ringgoldTypeValue };

    for (int i = 0; i < nedIds.length; i++) {

      Uniqueidentifier uidEntity = _(new Uniqueidentifier());
      uidEntity.setNedid( nedIds[i] );
      uidEntity.setTypeid( validUidTypeIds[i] );
      uidEntity.setUniqueidentifier( validUidTypeValues[i]+i );
      uidEntity.setSourcetypeid( getSourceTypeId(UidTypeEnum.EDITORIAL_MANAGER.getName()) );

      Integer uidId = nedDBSvc.create(uidEntity) ; assertTrue(uidId > 0);
      uidEntity.setId(uidId);

      nedDBSvc.validate(uidEntity);
    }

    // *** INVALID ENTITY/UID COMBOS ***

    Integer[] invalidUidTypeIds    = { ringgoldTypeId, orcidTypeId };
    String[]  invalidUidTypeValues = { ringgoldTypeValue, orcidTypeValue };

    for (int i = 0; i < nedIds.length; i++) {
      try {
        Uniqueidentifier uidEntity = _(new Uniqueidentifier());
        uidEntity.setNedid( nedIds[i] );
        uidEntity.setTypeid( invalidUidTypeIds[i] );
        uidEntity.setUniqueidentifier( invalidUidTypeValues[i]+i );
        uidEntity.setSourcetypeid( getSourceTypeId(UidTypeEnum.EDITORIAL_MANAGER.getName()) );

        Integer uidId = nedDBSvc.create(uidEntity) ; assertTrue(uidId > 0);
        uidEntity.setId(uidId);

        nedDBSvc.validate(uidEntity);
        fail();
      } catch (NedException expected) { }
    }
  }

  @Test
  public void testFindByAttribute() {
    // verify single quote is escaped
    Organization orgFilter = new Organization();
    orgFilter.setFamiliarname("ABC's Inc (FN)");
    List<Organization> foundOrgs = nedDBSvc.findByAttribute(orgFilter);
    assertEquals(1, foundOrgs.size());
  }

  @Test
  public void testFindTypeClassByInspection() {

    // Unsupported Entity Type

    try {
      nedDBSvc.findTypeClassByInspection(null, new Email());
      fail();
    } catch (UnsupportedOperationException expected) { }

    // Invalid Typename For Entity

    Uniqueidentifier uid = new Uniqueidentifier();
    try {
      nedDBSvc.findTypeClassByInspection("bogustypename", uid);
      fail();
    } catch (NedException expected) {
      assertEquals(ServerError, expected.getErrorType()); 
    }

    // Lookup type in Uniqueidentifier (happy path)

    uid.setNedid( nedDBSvc.newNamedEntityId(INDIVIDUAL) );
    Integer typeClassId = nedDBSvc.findTypeClassByInspection("type",uid);
    assertTrue( typeClassId > 0 );
  }

  private Integer getSourceTypeId(String source) {
    return getTypeId("Source Applications", source);
  }

  private Integer getTypeId(String typeClass, String typeValue) {
    Integer typeClassId = nedDBSvc.findTypeClass(typeClass);
    assertNotNull(typeClassId);
    Integer typeValueId = nedDBSvc.findTypeValue(typeClassId, typeValue);
    assertNotNull(typeValueId);
    return typeValueId;
  }

  private java.sql.Date dateNow() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new java.util.Date());
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return new java.sql.Date( cal.getTimeInMillis() );
  }

  // decorator function to endow entity with core attribute values, such as createdby.
  private <S extends Entity>
  S _(S entity) {
    entity.setCreatedby(1);
    entity.setLastmodifiedby(1);
    return entity;
  }
}
