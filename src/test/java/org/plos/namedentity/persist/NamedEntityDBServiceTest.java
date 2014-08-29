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
import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.persist.db.namedentities.tables.Globaltypes;
import org.plos.namedentity.persist.db.namedentities.tables.Typedescriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Timestamp;
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
      .join(v).on(v.TYPEID.equal(t.TYPEID))
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
             .set(TYPEDESCRIPTIONS.TYPEID, 1)
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
  public void testFindAllOnEmptyTable() {
    List<Journal> journals = nedDBSvc.findAll(Journal.class);
    assertNotNull(journals);
    assertEquals(0, journals.size());
  }

  @Test
  public void testTypedescriptionCRUD() {

    // CREATE
    Integer newTypeClassId = nedDBSvc.create( new Typedescription(null, "New Type Class", "Yada yada") );
    assertNotNull(newTypeClassId);

    // UPDATE description.
    Typedescription entity = nedDBSvc.findById(newTypeClassId, Typedescription.class);
    entity.setDescription( entity.getDescription() + "2");
    assertTrue( nedDBSvc.update(entity) );

    // Get another instance of same type class
    Typedescription entity2 = nedDBSvc.findById(newTypeClassId, Typedescription.class);
    assertEquals(entity, entity2);

    // Find all type classes
    List<Typedescription> typeClasses = nedDBSvc.findAll(Typedescription.class);
    assertTrue(typeClasses.size() >= 20);

    // Try to find a type class which doesn't exist
    Typedescription entity3 = nedDBSvc.findById(666, Typedescription.class);
    assertNull(entity3);

    // DELETE. we should be able to delete newly added type class because
    // it doesn't have any values associated with it (ie, has no globaltypes).

    Typedescription typeDescription = new Typedescription();
    typeDescription.setTypeid(newTypeClassId);

    assertTrue( nedDBSvc.delete(typeDescription) );

    // However, trying to delete a type class with type values should 
    // raise a foreign key constraint exception.

    try {
      typeDescription.setTypeid(1);
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
    newGlobaltype.setTypeid( findTypeClassStartWith("Named Party") );
    newGlobaltype.setShortdescription("Group XYZ");
    newGlobaltype.setTypecode("GRPX");

    assertNull(newGlobaltype.getGlobaltypeid());
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
    List<Globaltype> globalTypes = nedDBSvc.findAll(Globaltype.class);
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
    typeValueToDelete.setGlobaltypeid(newGlobalTypeId);

    assertTrue( nedDBSvc.delete(typeValueToDelete) );

    // TODO: add foreign key constraint voilation test for globalTypes.
  }

  @Test
  public void testEmailsCRUD() {

    Integer emailTypeClassId = findTypeClassStartWith("Email Address Types");

    // CREATE Work Email

    Email workEmail = new Email();
    workEmail.setNamedentityid(1);
    workEmail.setEmailtypeid(findTypeValueByName(emailTypeClassId, "Work"));
    workEmail.setEmailaddress("walter.work@foo.com");

    assertNull(workEmail.getEmailid());
    assertNotNull(workEmail.getNamedentityid());
    assertNotNull(workEmail.getEmailtypeid());
    assertEquals(Byte.valueOf((byte)1), workEmail.getIsprimary());
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
    homeEmail.setNamedentityid(1);
    homeEmail.setEmailtypeid(findTypeValueByName(emailTypeClassId, "Personal"));
    homeEmail.setEmailaddress("walter.home@foo.com");

    assertNull(homeEmail.getEmailid());
    assertNotNull(homeEmail.getNamedentityid());
    assertNotNull(homeEmail.getEmailtypeid());
    assertEquals(Byte.valueOf((byte)1), homeEmail.getIsprimary());
    assertEquals(Byte.valueOf((byte)1), homeEmail.getIsactive());

    Integer homeEmailId = nedDBSvc.create( homeEmail );
    assertNotNull(homeEmailId);

    // FIND ALL Email Records 

    List<Email> allEmailsInDB = nedDBSvc.findAll(Email.class);
    assertTrue( allEmailsInDB.size() >= 2 );

    // FIND BY ATTRIBUTE (Lookup email by address)

    Email emailSearchByAddress = new Email();
    emailSearchByAddress.setEmailaddress("super.walter.work@foo.com");
    List<Email> foundEmails = nedDBSvc.findByAttribute(emailSearchByAddress);
    assertEquals(1, foundEmails.size());
    assertEquals("super.walter.work@foo.com", foundEmails.get(0).getEmailaddress());

    // FIND BY ATTRIBUTE (Lookup email addresses for an individual)

    Email emailSearchByIndividual = new Email();
    emailSearchByIndividual.setNamedentityid(1);
    List<Email> foundEmails2 = nedDBSvc.findByAttribute(emailSearchByIndividual);
    assertEquals(2, foundEmails2.size());

    for (Email email : allEmailsInDB) {
      assertTrue( allEmailsInDB.contains(email) );
    }

    // FIND BY PRIMARY KEY

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;

    for (Email email : allEmailsInDB) {
      assertNotNull( nedQuery.findResolvedEntityByKey(email.getEmailid(), Email.class) );
    }
    // FIND BY JOIN-QUERY 
    List<Email> emails = nedQuery.findResolvedEntities(foundEmails2.get(0).getNamedentityid(), Email.class);


    assertTrue( emails.size() > 0 );

    // Try to find an email which doesn't exist

    Email entity3 = nedDBSvc.findById(666, Email.class);
    assertNull(entity3);

    // DELETE

    Email emailToDelete = new Email();
    emailToDelete.setEmailid(workEmailId);

    assertTrue( nedDBSvc.delete(emailToDelete) );
  }

  @Test
  public void testIndividualCRUD() {

    // CREATE

    Integer nedId = nedDBSvc.newNamedEntityId("Individual");

    Integer prefixTypeClassId      = findTypeClassStartWith("Named Party Prefixes");
    Integer suffixTypeClassId      = findTypeClassStartWith("Named Party Suffixes");
    Integer langTypeClassId        = findTypeClassStartWith("Languages");
    Integer commMethodsTypeClassId = findTypeClassStartWith("Communication Methods");

    Integer prefixTypeId     = findTypeValueByName(prefixTypeClassId, "Mr.")        ; assertNotNull(prefixTypeId)           ; 
    Integer suffixTypeId     = findTypeValueByName(suffixTypeClassId, "II")         ; assertNotNull(suffixTypeId)           ; 
    Integer langTypeId       = findTypeValueByName(langTypeClassId, "Italian")      ; assertNotNull(langTypeClassId)        ; 
    Integer commMethodTypeId = findTypeValueByName(commMethodsTypeClassId, "Email") ; assertNotNull(commMethodsTypeClassId) ; 

    Individual individual = new Individual();
    individual.setNamedentityid(nedId);
    individual.setFirstname("firstname");
    individual.setMiddlename("middlename");
    individual.setLastname("lastname");
    individual.setNameprefixtypeid(prefixTypeId);
    individual.setNamesuffixtypeid(suffixTypeId);
    individual.setPreferredlanguagetypeid(langTypeId);
    individual.setPreferredcommunicationmethodtypeid(commMethodTypeId);

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

    List<Individual> allIndividualsInDB = nedDBSvc.findAll(Individual.class);
    assertTrue(allIndividualsInDB.size() > 0);

    // FIND BY JOIN-QUERY 

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;
    Individual entity = nedQuery.findResolvedEntity(individualId, Individual.class);
    assertNotNull( entity );
    assertEquals("firstname", entity.getFirstname());
    assertEquals("Mr.", entity.getNameprefix());
    assertEquals("II", entity.getNamesuffix());
    assertEquals("Italian", entity.getPreferredlanguage());
    assertEquals("Email", entity.getPreferredcommunication());

    // DELETE

    Individual individualToDelete = new Individual();
    individualToDelete.setNamedentityid(individualId);

    assertTrue( nedDBSvc.delete(individualToDelete) );
  }


  @Test
  public void testOrganizationCRUD() {

    // CREATE

    Integer nedId = nedDBSvc.newNamedEntityId("Organization");

    Integer organizationTypeId = findTypeValueByName(findTypeClassStartWith("Organization Types"), "University");

    Organization organization = new Organization();
    organization.setNamedentityid(nedId);
    organization.setOrganizationtypeid(organizationTypeId);
    organization.setOrganizationfamiliarname("familiarname");
    organization.setOrganizationlegalname("legalname");
    organization.setIsactive((byte)1);
    organization.setIsvisible((byte)1);
    organization.setUrl("http://url.org");

    Integer organizationId = nedDBSvc.create(organization);
    assertNotNull(organizationId);

    // UPDATE
    Organization savedEntity = nedDBSvc.findById(organizationId, Organization.class);
    savedEntity.setOrganizationlegalname("legalname2");
    assertTrue(nedDBSvc.update(savedEntity));

    // Get another instance of same individual record
    Organization savedEntity2 = nedDBSvc.findById(organizationId, Organization.class);
    assertEquals(savedEntity, savedEntity2);

    // FIND ALL Email Records

    List<Organization> allEntitiesInDB = nedDBSvc.findAll(Organization.class);
    assertTrue(allEntitiesInDB.size() > 0);

    // FIND entity with

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;

    Organization entity = nedQuery.findResolvedEntity(organizationId, Organization.class);
    assertNotNull( entity );

    entity.setOrganizationtypeid(organizationTypeId);
    assertEquals(entity, savedEntity2);

    // DELETE

    Organization entityToDelete = new Organization();
    entityToDelete.setNamedentityid(organizationId);

    assertTrue( nedDBSvc.delete(entityToDelete) );
  }

  @Test
  public void testPhoneNumberCRUD() {

    // CREATE (Mobile Phone)

    Integer phoneTypeClassId       = findTypeClassStartWith("Telephone Number Types");
    Integer countryCodeTypeClassId = findTypeClassStartWith("Country Codes for Phone Numbers");

    Integer officePhoneTypeId = findTypeValueByName(phoneTypeClassId, "Office") ; assertNotNull(officePhoneTypeId); 
    Integer mobilePhoneTypeId = findTypeValueByName(phoneTypeClassId, "Mobile") ; assertNotNull(mobilePhoneTypeId); 

    Integer usaCountryCodeTypeId = findTypeValueByName(countryCodeTypeClassId, "01");
    assertNotNull(usaCountryCodeTypeId); 

    Phonenumber mobilePhone = new Phonenumber();
    mobilePhone.setNamedentityid(1);
    mobilePhone.setPhonenumbertypeid(mobilePhoneTypeId);
    mobilePhone.setCountrycodetypeid(usaCountryCodeTypeId);
    mobilePhone.setPhonenumber("650-123-4567");

    assertNull(mobilePhone.getPhonenumberid());
    assertNotNull(mobilePhone.getNamedentityid());
    assertTrue(mobilePhone.getIsprimary());
    assertTrue(mobilePhone.getIsactive());

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
    officePhone.setNamedentityid(1);
    officePhone.setPhonenumbertypeid(officePhoneTypeId);
    officePhone.setCountrycodetypeid(usaCountryCodeTypeId);
    officePhone.setPhonenumber("650-222-9876");

    assertNull(officePhone.getPhonenumberid());
    assertNotNull(officePhone.getNamedentityid());
    assertTrue(officePhone.getIsprimary());
    assertTrue(officePhone.getIsactive());

    Integer officePhoneId = nedDBSvc.create( officePhone );
    assertNotNull(officePhoneId);

    // FIND ALL Phone Numbers 

    List<Phonenumber> allPhonenumbersInDb = nedDBSvc.findAll(Phonenumber.class);
    assertTrue( allPhonenumbersInDb.size() >= 2 );
            
    //TODO : FIND BY ATTRIBUTE (Lookup email by phone number)

    // FIND BY ATTRIBUTE (Lookup phone numbers for an individual)

    Phonenumber phoneSearchByIndividual = new Phonenumber();
    phoneSearchByIndividual.setNamedentityid(1);
    List<Phonenumber> foundPhones = nedDBSvc.findByAttribute(phoneSearchByIndividual);
    assertEquals(2, foundPhones.size());

    for (Phonenumber phone : foundPhones) {
      assertTrue( allPhonenumbersInDb.contains(phone) );
    }

    // FIND BY JOIN-QUERY 

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;
    List<Phonenumber> phonenumbers = nedQuery.findResolvedEntities(foundPhones.get(0).getNamedentityid(), Phonenumber.class);
    assertTrue( phonenumbers.size() > 0 );

    // DELETE

    Phonenumber phoneToDelete = new Phonenumber();

    phoneToDelete.setPhonenumberid(mobilePhoneId);
    assertTrue( nedDBSvc.delete(phoneToDelete) );

    phoneToDelete.setPhonenumberid(officePhoneId);
    assertTrue( nedDBSvc.delete(phoneToDelete) );
  }

  @Test
  public void testAddressCRUD() {

    // CREATE

    Integer addressTypeClassId   = findTypeClassStartWith("Physical Address Types");
    Integer countryTypeClassId   = findTypeClassStartWith("Country Types");
    Integer stateCodeTypeClassId = findTypeClassStartWith("State and Province Codes");

    Integer officeAddressTypeId = findTypeValueByName(addressTypeClassId, "Office")       ; assertNotNull(officeAddressTypeId); 
    Integer countryTypeId       = findTypeValueByName(countryTypeClassId, "United States"); assertNotNull(countryTypeId)      ; 
    Integer stateCodeTypeId     = findTypeValueByName(stateCodeTypeClassId, "CA")         ; assertNotNull(stateCodeTypeId)    ; 

    Address address = new Address();
    address.setNamedentityid(1);
    address.setAddresstypeid(officeAddressTypeId);
    address.setAddressline1("addressline1");
    address.setAddressline2("addressline2");
    address.setAddressline3("addressline3");
    address.setCity("San Francisco");
    address.setStatecodetypeid(stateCodeTypeId);
    address.setCountrycodetypeid(countryTypeId);
    address.setPostalcode("94501");

    assertNull(address.getAddressid());
    assertNotNull(address.getNamedentityid());
    assertEquals(Byte.valueOf((byte)1), address.getIsprimary());
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

    List<Address> allAddressesInDb = nedDBSvc.findAll(Address.class);
    assertTrue( allAddressesInDb.size() > 0 );

    // FIND BY JOIN-QUERY 

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;
    List<Address> addresses = nedQuery.findResolvedEntities(savedAddress.getNamedentityid(), Address.class);
    assertTrue( addresses.size() > 0 );
            
    // DELETE

    Address addressToDelete = new Address();

    addressToDelete.setAddressid(addressId);
    assertTrue( nedDBSvc.delete(addressToDelete) );
  }

  @Test
  public void testRoleCRUD() {

    // CREATE

    Integer srcAppTypeClassId = findTypeClassStartWith("Source Applications");
    Integer roleTypeClassId   = findTypeClassStartWith("Roles");

    Integer srcAppTypeId = findTypeValueByName(srcAppTypeClassId, "Editorial Manager"); assertNotNull(srcAppTypeId); 
    Integer roleTypeId   = findTypeValueByName(roleTypeClassId, "Author")             ; assertNotNull(roleTypeId)  ; 

    Role authorRole = new Role();
    authorRole.setNamedentityid(1);
    authorRole.setSourceapplicationtypeid(srcAppTypeId);
    authorRole.setRoletypeid(roleTypeId);
    authorRole.setStartdate(new Timestamp(new Date().getTime()));

    assertNull(authorRole.getRoleid());
    assertNotNull(authorRole.getNamedentityid());

    Integer authorId = nedDBSvc.create( authorRole );
    assertNotNull(authorId);

    // UPDATE

    Role savedRole = nedDBSvc.findById(authorId, Role.class);
    savedRole.setEnddate(new Timestamp(new Date().getTime()));
    assertTrue( nedDBSvc.update(savedRole) );

    // Get another instance of same role record 

    Role savedRole2 = nedDBSvc.findById(authorId, Role.class);
    assertEquals(savedRole, savedRole2);

    // FIND ALL Roles 

    List<Role> allRolesInDb = nedDBSvc.findAll(Role.class);
    assertTrue( allRolesInDb.size() > 0 );

    // FIND BY JOIN-QUERY 

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;
    List<Role> roles = nedQuery.findResolvedEntities(savedRole.getNamedentityid(), Role.class);
    Role role = roles.get(0);
    assertEquals("Author", role.getRoletype());
              
    // DELETE

    Role roleToDelete = new Role();
    roleToDelete.setRoleid(authorId);
    assertTrue( nedDBSvc.delete(roleToDelete) );
  }

  @Test
  public void testUniqueIdentifiersCRUD() {

    final String ORCID_ID = "0000-0001-9430-319X";

    // FIND Individuals with an ORCID id. There should be none. 

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;
    List<Individual> peopleWithOrcidId = nedQuery.findResolvedEntityByUid("ORCID", ORCID_ID, Individual.class);
    assertEquals(0, peopleWithOrcidId.size());

    // Create two individuals with the same Orcid#
  
    for (int i = 1; i <= 2; i++) {

      Individual individual = new Individual();
      individual.setFirstname("firstname");
      individual.setMiddlename("middlename");
      individual.setLastname("lastname");
      Integer individualId = nedDBSvc.create( individual );
      assertNotNull(individualId);

      Uniqueidentifier uidEntity1 = new Uniqueidentifier();
      uidEntity1.setNamedentityid(individualId);
      uidEntity1.setUniqueidentifier(ORCID_ID);

      assertNull(uidEntity1.getUniqueidentifiersid());
      assertNotNull(uidEntity1.getNamedentityid());

      Integer uidId1 = nedDBSvc.create( uidEntity1 );
      assertNotNull(uidId1);

      // FIND By UID (ORCID) #2
      assertEquals(i, nedQuery.findResolvedEntityByUid("ORCID", ORCID_ID, Individual.class).size());

      // UPDATE

      Uniqueidentifier savedUid = nedDBSvc.findById(uidId1, Uniqueidentifier.class);
      savedUid.setUniqueidentifier( savedUid.getUniqueidentifier() + "Z" );
      assertTrue( nedDBSvc.update(savedUid) );

      // Get another instance of same role record 

      Uniqueidentifier savedUid2 = nedDBSvc.findById(uidId1, Uniqueidentifier.class);
      assertEquals(savedUid, savedUid2);

      // Restore orcid id.

      savedUid.setUniqueidentifier(ORCID_ID);
      assertTrue( nedDBSvc.update(savedUid) );

      // FIND BY JOIN-QUERY 

      List<Uniqueidentifier> uids = nedQuery.findResolvedEntities(savedUid.getNamedentityid(), Uniqueidentifier.class);
      Uniqueidentifier uid = uids.get(0);
      assertEquals(ORCID_ID, uid.getUniqueidentifier());
    }

    // FIND ALL Roles 

    List<Uniqueidentifier> allUidsInDb = nedDBSvc.findAll(Uniqueidentifier.class);
    assertEquals(2, allUidsInDb.size());

    for (Uniqueidentifier uid : allUidsInDb) {
      Uniqueidentifier uidToDelete = new Uniqueidentifier();
      uidToDelete.setUniqueidentifiersid(uid.getUniqueidentifiersid());
      assertTrue( nedDBSvc.delete(uidToDelete) );
    }
  }

  private Integer findTypeClassStartWith(String prefix) {
    for(Typedescription typeClass : nedDBSvc.findAll(Typedescription.class)) {
      if (typeClass.getDescription().startsWith(prefix)) {
        return typeClass.getTypeid();
      }
    }
    throw new RuntimeException("No type class found which begins with " + prefix);
  }

  private Integer findTypeValueByName(Integer typeClassId, String name) {
    for(Globaltype typeValue : nedDBSvc.findAll(Globaltype.class)) {
      if (typeClassId.equals(typeValue.getTypeid()) &&
          typeValue.getShortdescription().equals(name))
      {
        return typeValue.getGlobaltypeid();
      }
    }
    throw new RuntimeException("No type value found with short description =  " + name);
  }
}
