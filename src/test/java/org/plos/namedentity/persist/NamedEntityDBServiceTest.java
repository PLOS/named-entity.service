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
import org.plos.namedentity.api.EntityNotFoundException;
import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.persist.db.namedentities.tables.Globaltypes;
import org.plos.namedentity.persist.db.namedentities.tables.Typedescriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.plos.namedentity.persist.db.namedentities.Tables.GLOBALTYPES;
import static org.plos.namedentity.persist.db.namedentities.Tables.TYPEDESCRIPTIONS;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})
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

    Integer nedId1 = nedDBSvc.newNamedEntityId("Individual");
    Integer nedId2 = nedDBSvc.newNamedEntityId("Individual");
    assertTrue(nedId1.intValue() > 0);
    assertEquals(nedId2.intValue(), nedId1.intValue()+1);

    try {
      nedDBSvc.newNamedEntityId("BogusEntityType");
      fail();
    }
    catch (NullPointerException e) {
      // TODO: NamedEntityDBService should throw a uniform runtime
      //       exception (capturing this exception as cause)
    }
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
    } catch (DataIntegrityViolationException expected) {
      // this should happen because emailAddress is required
    }

  }

  @Test
  public void testFindAllOnEmptyTable() {
    List<Journal> journals = nedDBSvc.findAll(Journal.class, 0, Integer.MAX_VALUE);
    assertNotNull(journals);
    assertEquals(0, journals.size());
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

    // Get another instance of same type value
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
  public void testEmailsCRUD() {

    Integer emailTypeClassId = nedDBSvc.findTypeClass("Email Address Types");

    // CREATE Work Email

    Email workEmail = new Email();
    workEmail.setNedid(1);
    workEmail.setTypeid(nedDBSvc.findTypeValue(emailTypeClassId, "Work"));
    workEmail.setEmailaddress("walter.work@foo.com");
    workEmail.setSourcetypeid(78);

    assertNull(workEmail.getId());
    assertNotNull(workEmail.getNedid());
    assertNotNull(workEmail.getTypeid());
    assertEquals(Byte.valueOf((byte)1), workEmail.getIsactive());

    Integer workEmailId = nedDBSvc.create( workEmail );
    assertNotNull(workEmailId);

    // UPDATE Work Email and Status 

    Email savedWorkEmail = nedDBSvc.findById(workEmailId, Email.class);
    savedWorkEmail.setEmailaddress("super." + savedWorkEmail.getEmailaddress());
    savedWorkEmail.setIsactive((byte)0);
    assertTrue( nedDBSvc.update(savedWorkEmail) );

    // Get another instance of same email record 

    Email savedWorkEmail2 = nedDBSvc.findById(workEmailId, Email.class);
    assertEquals(savedWorkEmail, savedWorkEmail2);

    // CREATE Home Email

    Email homeEmail = new Email();
    homeEmail.setNedid(1);
    homeEmail.setTypeid(nedDBSvc.findTypeValue(emailTypeClassId, "Personal"));
    homeEmail.setEmailaddress("walter.home@foo.com");
    homeEmail.setSourcetypeid(78);

    assertNull(homeEmail.getId());
    assertNotNull(homeEmail.getNedid());
    assertNotNull(homeEmail.getTypeid());
    assertEquals(Byte.valueOf((byte)1), homeEmail.getIsactive());

    Integer homeEmailId = nedDBSvc.create( homeEmail );
    assertNotNull(homeEmailId);

    // FIND ALL Email Records 

    List<Email> allEmailsInDB = nedDBSvc.findAll(Email.class, 0, Integer.MAX_VALUE);
    assertTrue( allEmailsInDB.size() >= 2 );

    // FIND BY ATTRIBUTE (Lookup email by address)

    Email emailSearchByAddress = new Email();
    emailSearchByAddress.setEmailaddress("super.walter.work@foo.com");
    List<Email> foundEmails = nedDBSvc.findByAttribute(emailSearchByAddress);
    assertEquals(1, foundEmails.size());
    assertEquals("super.walter.work@foo.com", foundEmails.get(0).getEmailaddress());

    // FIND BY ATTRIBUTE (Lookup email addresses for an individual)

    Email emailSearchByIndividual = new Email();
    emailSearchByIndividual.setNedid(1);
    List<Email> foundEmails2 = nedDBSvc.findByAttribute(emailSearchByIndividual);
    assertEquals(2, foundEmails2.size());

    for (Email email : allEmailsInDB) {
      assertTrue( allEmailsInDB.contains(email) );
    }

    // FIND BY PRIMARY KEY

    for (Email email : allEmailsInDB) {
      assertNotNull( nedDBSvc.findResolvedEntityByKey(email.getId(), Email.class) );
    }
    // FIND BY JOIN-QUERY 
    List<Email> emails = nedDBSvc.findResolvedEntities(foundEmails2.get(0).getNedid(), Email.class);


    assertTrue( emails.size() > 0 );

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

    Integer nedId = nedDBSvc.newNamedEntityId("Individual");

    Integer prefixTypeClassId      = nedDBSvc.findTypeClass("Named Party Prefixes");
    Integer suffixTypeClassId      = nedDBSvc.findTypeClass("Named Party Suffixes");
    Integer langTypeClassId        = nedDBSvc.findTypeClass("Languages");
    Integer commMethodsTypeClassId = nedDBSvc.findTypeClass("Communication Methods");

    Integer prefixTypeId     = nedDBSvc.findTypeValue(prefixTypeClassId, "Mr.")        ; assertNotNull(prefixTypeId)           ;
    Integer suffixTypeId     = nedDBSvc.findTypeValue(suffixTypeClassId, "II")         ; assertNotNull(suffixTypeId)           ;
    Integer langTypeId       = nedDBSvc.findTypeValue(langTypeClassId, "Italian")      ; assertNotNull(langTypeClassId)        ;
    Integer commMethodTypeId = nedDBSvc.findTypeValue(commMethodsTypeClassId, "Email") ; assertNotNull(commMethodsTypeClassId) ;

    Individual individual = new Individual();
    individual.setNedid(nedId);
    individual.setFirstname("firstname");
    individual.setMiddlename("middlename");
    individual.setLastname("lastname");
    individual.setDisplayname("displayname");
    individual.setNameprefixtypeid(prefixTypeId);
    individual.setNamesuffixtypeid(suffixTypeId);
    individual.setPreferredlanguagetypeid(langTypeId);
    individual.setPreferredcommunicationmethodtypeid(commMethodTypeId);
    individual.setSourcetypeid(78);

    Integer individualId = nedDBSvc.create( individual );
    assertNotNull(individualId);

    // UPDATE
    
    Individual savedIndividual = nedDBSvc.findById(individualId, Individual.class);
    savedIndividual.setMiddlename("chuck");
    assertTrue( nedDBSvc.update(savedIndividual) );

    // Get another instance of same individual record
    Individual savedIndividualAfterUpdate = nedDBSvc.findById(individualId, Individual.class);
    assertEquals(savedIndividual, savedIndividualAfterUpdate);

    // FIND ALL Email Records 

    List<Individual> allIndividualsInDB = nedDBSvc.findAll(Individual.class, 0, Integer.MAX_VALUE);
    assertTrue(allIndividualsInDB.size() > 0);

    // FIND BY JOIN-QUERY 

    List<Individual> entities = nedDBSvc.findResolvedEntities(nedId, Individual.class);
    assertNotNull( entities );
    assertEquals("firstname", entities.get(0).getFirstname());
    assertEquals("Mr.", entities.get(0).getNameprefix());
    assertEquals("II", entities.get(0).getNamesuffix());
    assertEquals("Italian", entities.get(0).getPreferredlanguage());
    assertEquals("Email", entities.get(0).getPreferredcommunication());

    // DELETE

    Individual individualToDelete = new Individual();
    individualToDelete.setId(individualId);

    assertTrue( nedDBSvc.delete(individualToDelete) );
  }

  @Test
  public void testOrganizationCRUD() {

    // CREATE

    Integer nedId = nedDBSvc.newNamedEntityId("Organization");

    Integer organizationTypeId = nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Organization Types"), "University");

    Organization organization = new Organization();
    organization.setNedid(nedId);
    organization.setTypeid(organizationTypeId);
    organization.setFamiliarname("familiarname");
    organization.setLegalname("legalname");
    organization.setIsactive((byte)1);
    organization.setIsvisible((byte)1);
    organization.setSourcetypeid(78);

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

    Integer usaCountryCodeTypeId = nedDBSvc.findTypeValue(countryCodeTypeClassId, "01");
    assertNotNull(usaCountryCodeTypeId); 

    Phonenumber mobilePhone = new Phonenumber();
    mobilePhone.setNedid(1);
    mobilePhone.setTypeid(mobilePhoneTypeId);
    mobilePhone.setCountrycodetypeid(usaCountryCodeTypeId);
    mobilePhone.setPhonenumber("650-123-4567");
    mobilePhone.setSourcetypeid(78);

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

    Phonenumber officePhone = new Phonenumber();
    officePhone.setNedid(1);
    officePhone.setTypeid(officePhoneTypeId);
    officePhone.setCountrycodetypeid(usaCountryCodeTypeId);
    officePhone.setPhonenumber("650-222-9876");
    officePhone.setSourcetypeid(78);

    assertNull(officePhone.getId());
    assertNotNull(officePhone.getNedid());
    assertTrue(officePhone.getIsactive());

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

    Integer officeAddressTypeId = nedDBSvc.findTypeValue(addressTypeClassId, "Office")       ; assertNotNull(officeAddressTypeId);
    Integer countryTypeId       = nedDBSvc.findTypeValue(countryTypeClassId, "United States"); assertNotNull(countryTypeId)      ;
    Integer stateCodeTypeId     = nedDBSvc.findTypeValue(stateCodeTypeClassId, "CA")         ; assertNotNull(stateCodeTypeId)    ;

    Address address = new Address();
    address.setNedid(1);
    address.setTypeid(officeAddressTypeId);
    address.setAddressline1("addressline1");
    address.setAddressline2("addressline2");
    address.setAddressline3("addressline3");
    address.setCity("San Francisco");
    address.setStatecodetypeid(stateCodeTypeId);
    address.setCountrycodetypeid(countryTypeId);
    address.setPostalcode("94501");
    address.setSourcetypeid(78);

    assertNull(address.getId());
    assertNotNull(address.getNedid());
    assertEquals(Byte.valueOf((byte)1), address.getIsactive());

    Integer addressId = nedDBSvc.create( address );
    assertNotNull(addressId);

    // UPDATE

    Address savedAddress = nedDBSvc.findById(addressId, Address.class);
    savedAddress.setAddressline2("updated addressline2");
    assertTrue( nedDBSvc.update(savedAddress) );

    // Get another instance of same address record 

    Address savedAddress2 = nedDBSvc.findById(addressId, Address.class);
    assertEquals(savedAddress, savedAddress2);

    // FIND ALL Phone Numbers 

    List<Address> allAddressesInDb = nedDBSvc.findAll(Address.class, 0, Integer.MAX_VALUE);
    assertTrue( allAddressesInDb.size() > 0 );

    // FIND BY JOIN-QUERY 

    List<Address> addresses = nedDBSvc.findResolvedEntities(savedAddress.getNedid(), Address.class);
    assertTrue( addresses.size() > 0 );
            
    // DELETE

    Address addressToDelete = new Address();

    addressToDelete.setId(addressId);
    assertTrue( nedDBSvc.delete(addressToDelete) );
  }

  @Test
  public void testRoleCRUD() {

    // CREATE

    Integer srcAppTypeClassId = nedDBSvc.findTypeClass("User Applications");
    Integer roleTypeClassId   = nedDBSvc.findTypeClass("Roles");

    Integer srcAppTypeId = nedDBSvc.findTypeValue(srcAppTypeClassId, "Editorial Manager"); assertNotNull(srcAppTypeId);
    Integer roleTypeId   = nedDBSvc.findTypeValue(roleTypeClassId, "Author")             ; assertNotNull(roleTypeId)  ;

    Role authorRole = new Role();
    authorRole.setNedid(1);
    authorRole.setApplicationtypeid(srcAppTypeId);
    authorRole.setTypeid(roleTypeId);

    java.sql.Date startDate = dateNow();
    authorRole.setStartdate(startDate);

    authorRole.setLastmodified(new Timestamp(Calendar.getInstance().getTime().getTime()));
    authorRole.setCreated(new Timestamp(Calendar.getInstance().getTime().getTime()));
    authorRole.setSourcetypeid(78);

    assertNull(authorRole.getId());
    assertNotNull(authorRole.getNedid());

    Integer authorId = nedDBSvc.create( authorRole );
    assertNotNull(authorId);

    // UPDATE

    Role savedRole = nedDBSvc.findById(authorId, Role.class);

    java.sql.Date endDate = dateNow();
    savedRole.setEnddate(endDate);

    assertTrue( nedDBSvc.update(savedRole) );

    // Get another instance of same role record 

    Role savedRole2 = nedDBSvc.findById(authorId, Role.class);
    assertEquals(savedRole, savedRole2);
    assertEquals(savedRole.getStartdate(), savedRole2.getStartdate());
    assertEquals(savedRole.getEnddate(), savedRole2.getEnddate());

    // FIND ALL Roles 

    List<Role> allRolesInDb = nedDBSvc.findAll(Role.class, 0, Integer.MAX_VALUE);
    assertTrue( allRolesInDb.size() > 0 );

    // FIND BY JOIN-QUERY 

    List<Role> roles = nedDBSvc.findResolvedEntities(savedRole.getNedid(), Role.class);
    Role role = roles.get(0);
    assertEquals("Author", role.getType());
              
    // DELETE

    Role roleToDelete = new Role();
    roleToDelete.setId(authorId);
    assertTrue( nedDBSvc.delete(roleToDelete) );
  }

  @Test
  public void testUniqueIdentifiersCRUD() {

    final String ORCID_ID = "0000-0001-9430-319X";

    // FIND Individuals with an ORCID id. There should be none. 

    try {
      nedDBSvc.findResolvedEntityByUid("ORCID", ORCID_ID, Individual.class);
    } catch (EntityNotFoundException expected) {
    }

    // Create two individuals with the same Orcid#

    Integer uidId = nedDBSvc.findTypeClass("Unique Identifier Types");


    Integer nedId = nedDBSvc.newNamedEntityId("Individual");

    Individual individual = new Individual();
    individual.setNedid(nedId);
    individual.setFirstname("firstname");
    individual.setMiddlename("middlename");
    individual.setLastname("lastname");
    individual.setDisplayname("displayname");
    individual.setSourcetypeid(78);

    assertNotNull(nedDBSvc.create(individual));

    Uniqueidentifier uidEntity1 = new Uniqueidentifier();
    uidEntity1.setNedid(nedId);
    uidEntity1.setTypeid(uidId);
    uidEntity1.setUniqueidentifier(ORCID_ID);
    uidEntity1.setSourcetypeid(78);

    assertNull(uidEntity1.getId());
    assertNotNull(uidEntity1.getNedid());

    Integer uidId1 = nedDBSvc.create( uidEntity1 );
    assertNotNull(uidId1);

    // FIND By UID (ORCID) #2
    assertNotNull(nedDBSvc.findResolvedEntityByUid("ORCID", ORCID_ID, Individual.class));

    // UPDATE

    Uniqueidentifier savedUid = nedDBSvc.findById(uidId1, Uniqueidentifier.class);
    savedUid.setUniqueidentifier(savedUid.getUniqueidentifier() + "Z");
    assertTrue( nedDBSvc.update(savedUid) );

    // Get another instance of same role record

    Uniqueidentifier savedUid2 = nedDBSvc.findById(uidId1, Uniqueidentifier.class);
    assertEquals(savedUid, savedUid2);

    // Restore orcid id.

    savedUid.setUniqueidentifier(ORCID_ID);
    assertTrue( nedDBSvc.update(savedUid) );

    // FIND BY JOIN-QUERY

    List<Uniqueidentifier> uids = nedDBSvc.findResolvedEntities(savedUid.getNedid(), Uniqueidentifier.class);

    assertEquals(ORCID_ID, uids.get(0).getUniqueidentifier());

    // FIND ALL Roles 

    List<Uniqueidentifier> allUidsInDb = nedDBSvc.findAll(Uniqueidentifier.class, 0, Integer.MAX_VALUE);
    assertEquals(1, allUidsInDb.size());

    for (Uniqueidentifier uid : allUidsInDb) {
      Uniqueidentifier uidToDelete = new Uniqueidentifier();
      uidToDelete.setId(uid.getId());
      assertTrue( nedDBSvc.delete(uidToDelete) );
    }
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
}
