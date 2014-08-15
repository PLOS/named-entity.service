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
    List<JournalEntity> journals = nedDBSvc.findAll(JournalEntity.class);
    assertNotNull(journals);
    assertEquals(0, journals.size());
  }

  @Test
  public void testTypedescriptionCRUD() {

    // CREATE
    Integer newTypeClassId = nedDBSvc.create( new TypedescriptionEntity(null, "New Type Class", "Yada yada") );
    assertNotNull(newTypeClassId);

    // UPDATE description.
    TypedescriptionEntity entity = nedDBSvc.findById(newTypeClassId, TypedescriptionEntity.class);
    entity.setDescription( entity.getDescription() + "2");
    assertTrue( nedDBSvc.update(entity) );

    // Get another instance of same type class
    TypedescriptionEntity entity2 = nedDBSvc.findById(newTypeClassId, TypedescriptionEntity.class);
    assertEquals(entity, entity2);

    // Find all type classes
    List<TypedescriptionEntity> typeClasses = nedDBSvc.findAll(TypedescriptionEntity.class);
    assertTrue(typeClasses.size() >= 20);

    // Try to find a type class which doesn't exist
    TypedescriptionEntity entity3 = nedDBSvc.findById(666, TypedescriptionEntity.class);
    assertNull(entity3);

    // DELETE. we should be able to delete newly added type class because
    // it doesn't have any values associated with it (ie, has no globaltypes).

    TypedescriptionEntity typeDescription = new TypedescriptionEntity();
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

    GlobaltypeEntity newGlobaltype = new GlobaltypeEntity();
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
    GlobaltypeEntity entity = nedDBSvc.findById(newGlobalTypeId, GlobaltypeEntity.class);
    entity.setShortdescription( entity.getShortdescription() + "2");
    assertTrue( nedDBSvc.update(entity) );

    // Get another instance of same type value
    GlobaltypeEntity entity2 = nedDBSvc.findById(newGlobalTypeId, GlobaltypeEntity.class);
    assertEquals(entity, entity2);

    // Find all global types 
    List<GlobaltypeEntity> globalTypes = nedDBSvc.findAll(GlobaltypeEntity.class);
    assertTrue(globalTypes.size() >= 75);

    // Find global types for a type class
    GlobaltypeEntity typeClassFilter = new GlobaltypeEntity();
    typeClassFilter.setTypeid(1);
    List<GlobaltypeEntity> globalTypesForTypeClass = nedDBSvc.findByAttribute(typeClassFilter);
    assertEquals(3, globalTypesForTypeClass.size());

    // Try to find a global type which doesn't exist
    GlobaltypeEntity entity3 = nedDBSvc.findById(666, GlobaltypeEntity.class);
    assertNull(entity3);

    // DELETE
    GlobaltypeEntity typeValueToDelete = new GlobaltypeEntity();
    typeValueToDelete.setGlobaltypeid(newGlobalTypeId);

    assertTrue( nedDBSvc.delete(typeValueToDelete) );

    // TODO: add foreign key constraint voilation test for globalTypes.
  }

  @Test
  public void testEmailsCRUD() {

    Integer emailTypeClassId = findTypeClassStartWith("Email Address Types");

    // CREATE Work Email

    EmailEntity workEmail = new EmailEntity();
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

    EmailEntity savedWorkEmail = nedDBSvc.findById(workEmailId, EmailEntity.class);
    savedWorkEmail.setEmailaddress("super." + savedWorkEmail.getEmailaddress());
    savedWorkEmail.setIsactive((byte)0);
    assertTrue( nedDBSvc.update(savedWorkEmail) );

    // Get another instance of same email record 

    EmailEntity savedWorkEmail2 = nedDBSvc.findById(workEmailId, EmailEntity.class);
    assertEquals(savedWorkEmail, savedWorkEmail2);

    // CREATE Home Email

    EmailEntity homeEmail = new EmailEntity();
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

    List<EmailEntity> allEmailsInDB = nedDBSvc.findAll(EmailEntity.class);
    assertTrue( allEmailsInDB.size() >= 2 );
              
    // FIND BY ATTRIBUTE (Lookup email by address)

    EmailEntity emailSearchByAddress = new EmailEntity();
    emailSearchByAddress.setEmailaddress("super.walter.work@foo.com");
    List<EmailEntity> foundEmails = nedDBSvc.findByAttribute(emailSearchByAddress);
    assertEquals(1, foundEmails.size());
    assertEquals("super.walter.work@foo.com", foundEmails.get(0).getEmailaddress());

    // FIND BY ATTRIBUTE (Lookup email addresses for an individual)

    EmailEntity emailSearchByIndividual = new EmailEntity();
    emailSearchByIndividual.setNamedentityid(1);
    List<EmailEntity> foundEmails2 = nedDBSvc.findByAttribute(emailSearchByIndividual);
    assertEquals(2, foundEmails2.size());

    for (EmailEntity email : allEmailsInDB) {
      assertTrue( allEmailsInDB.contains(email) );
    }

    // FIND BY JOIN-QUERY 

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;
    List<EmailEntity> emails = nedQuery.findResolvedEntities(foundEmails2.get(0).getNamedentityid(), EmailEntity.class);
    assertTrue( emails.size() > 0 );

    // Try to find an email which doesn't exist

    EmailEntity entity3 = nedDBSvc.findById(666, EmailEntity.class);
    assertNull(entity3);

    // DELETE

    EmailEntity emailToDelete = new EmailEntity();
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

    IndividualEntity individual = new IndividualEntity();
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
    
    IndividualEntity savedIndividual = nedDBSvc.findById(individualId, IndividualEntity.class);
    savedIndividual.setMiddlename("chuck");
    assertTrue( nedDBSvc.update(savedIndividual) );

    // Get another instance of same individual record
    IndividualEntity savedIndividualAfterUpdate = nedDBSvc.findById(individualId, IndividualEntity.class);
    assertEquals(savedIndividual, savedIndividualAfterUpdate);

    // FIND ALL Email Records 

    List<IndividualEntity> allIndividualsInDB = nedDBSvc.findAll(IndividualEntity.class);
    assertTrue(allIndividualsInDB.size() > 0);

    // FIND BY JOIN-QUERY 

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;
    IndividualEntity entity = nedQuery.findResolvedEntity(individualId, IndividualEntity.class);
    assertNotNull( entity );
    assertEquals("firstname", entity.getFirstname());
    assertEquals("Mr.", entity.getNameprefix());
    assertEquals("II", entity.getNamesuffix());
    assertEquals("Italian", entity.getPreferredlanguage());
    assertEquals("Email", entity.getPreferredcommunication());

    // DELETE

    IndividualEntity individualToDelete = new IndividualEntity();
    individualToDelete.setNamedentityid(individualId);

    assertTrue( nedDBSvc.delete(individualToDelete) );
  }


  @Test
  public void testOrganizationCRUD() {

    // CREATE

    Integer nedId = nedDBSvc.newNamedEntityId("Organization");

    Integer organizationTypeId = findTypeValueByName(findTypeClassStartWith("Organization Types"), "University");

    OrganizationEntity organization = new OrganizationEntity();
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
    OrganizationEntity savedEntity = nedDBSvc.findById(organizationId, OrganizationEntity.class);
    savedEntity.setOrganizationlegalname("legalname2");
    assertTrue(nedDBSvc.update(savedEntity));

    // Get another instance of same individual record
    OrganizationEntity savedEntity2 = nedDBSvc.findById(organizationId, OrganizationEntity.class);
    assertEquals(savedEntity, savedEntity2);

    // FIND ALL Email Records

    List<OrganizationEntity> allEntitiesInDB = nedDBSvc.findAll(OrganizationEntity.class);
    assertTrue(allEntitiesInDB.size() > 0);

    // FIND entity with

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;

    OrganizationEntity entity = nedQuery.findResolvedEntity(organizationId, OrganizationEntity.class);
    assertNotNull( entity );

    entity.setOrganizationtypeid(organizationTypeId);
    assertEquals(entity, savedEntity2);

    // DELETE

    OrganizationEntity entityToDelete = new OrganizationEntity();
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

    PhonenumberEntity mobilePhone = new PhonenumberEntity();
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

    PhonenumberEntity savedMobilePhone = nedDBSvc.findById(mobilePhoneId, PhonenumberEntity.class);
    savedMobilePhone.setExtension("xxx");
    assertTrue( nedDBSvc.update(savedMobilePhone) );

    // Get another instance of same email record 

    PhonenumberEntity savedMobilePhone2 = nedDBSvc.findById(mobilePhoneId, PhonenumberEntity.class);
    assertEquals(savedMobilePhone, savedMobilePhone2);

    // CREATE (Office Phone)

    PhonenumberEntity officePhone = new PhonenumberEntity();
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

    List<PhonenumberEntity> allPhonenumbersInDb = nedDBSvc.findAll(PhonenumberEntity.class);
    assertTrue( allPhonenumbersInDb.size() >= 2 );
            
    //TODO : FIND BY ATTRIBUTE (Lookup email by phone number)

    // FIND BY ATTRIBUTE (Lookup phone numbers for an individual)

    PhonenumberEntity phoneSearchByIndividual = new PhonenumberEntity();
    phoneSearchByIndividual.setNamedentityid(1);
    List<PhonenumberEntity> foundPhones = nedDBSvc.findByAttribute(phoneSearchByIndividual);
    assertEquals(2, foundPhones.size());

    for (PhonenumberEntity phone : foundPhones) {
      assertTrue( allPhonenumbersInDb.contains(phone) );
    }

    // FIND BY JOIN-QUERY 

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;
    List<PhonenumberEntity> phonenumbers = nedQuery.findResolvedEntities(foundPhones.get(0).getNamedentityid(), PhonenumberEntity.class);
    assertTrue( phonenumbers.size() > 0 );

    // DELETE

    PhonenumberEntity phoneToDelete = new PhonenumberEntity();

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

    AddressEntity address = new AddressEntity();
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

    AddressEntity savedAddress = nedDBSvc.findById(addressId, AddressEntity.class);
    savedAddress.setAddressline2("updated addressline2");
    assertTrue( nedDBSvc.update(savedAddress) );

    // Get another instance of same address record 

    AddressEntity savedAddress2 = nedDBSvc.findById(addressId, AddressEntity.class);
    assertEquals(savedAddress, savedAddress2);

    // FIND ALL Phone Numbers 

    List<AddressEntity> allAddressesInDb = nedDBSvc.findAll(AddressEntity.class);
    assertTrue( allAddressesInDb.size() > 0 );

    // FIND BY JOIN-QUERY 

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;
    List<AddressEntity> addresses = nedQuery.findResolvedEntities(savedAddress.getNamedentityid(), AddressEntity.class);
    assertTrue( addresses.size() > 0 );
            
    //TODO : FIND BY ATTRIBUTE

    // DELETE

    AddressEntity addressToDelete = new AddressEntity();

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

    RoleEntity authorRole = new RoleEntity();
    authorRole.setNamedentityid(1);
    authorRole.setSourceapplicationtypeid(srcAppTypeId);
    authorRole.setRoletypeid(roleTypeId);
    authorRole.setStartdate(new Timestamp(new Date().getTime()));

    assertNull(authorRole.getRoleid());
    assertNotNull(authorRole.getNamedentityid());

    Integer authorId = nedDBSvc.create( authorRole );
    assertNotNull(authorId);

    // UPDATE

    RoleEntity savedRole = nedDBSvc.findById(authorId, RoleEntity.class);
    savedRole.setEnddate(new Timestamp(new Date().getTime()));
    assertTrue( nedDBSvc.update(savedRole) );

    // Get another instance of same role record 

    RoleEntity savedRole2 = nedDBSvc.findById(authorId, RoleEntity.class);
    assertEquals(savedRole, savedRole2);

    // FIND ALL Roles 

    List<RoleEntity> allRolesInDb = nedDBSvc.findAll(RoleEntity.class);
    assertTrue( allRolesInDb.size() > 0 );

    // FIND BY JOIN-QUERY 

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;
    List<RoleEntity> roles = nedQuery.findResolvedEntities(savedRole.getNamedentityid(), RoleEntity.class);
    RoleEntity role = roles.get(0);
    assertEquals("Author", role.getRoletype());
              
    // DELETE

    RoleEntity roleToDelete = new RoleEntity();
    roleToDelete.setRoleid(authorId);
    assertTrue( nedDBSvc.delete(roleToDelete) );
  }

  @Test
  public void testUniqueIdentifiersCRUD() {

    final String ORCID_ID = "0000-0001-9430-319X";

    Integer uidTypeClassId = findTypeClassStartWith("Unique Identifier Types");
    Integer orcidTypeId    = findTypeValueByName(uidTypeClassId, "ORCID"); assertNotNull(orcidTypeId);

    // FIND Individuals with an ORCID id. There should be none. 

    NamedEntityQueries nedQuery = (NamedEntityQueries) nedDBSvc;
    List<IndividualEntity> peopleWithOrcidId = nedQuery.findIndividualsByUid(orcidTypeId, ORCID_ID);
    assertEquals(0, peopleWithOrcidId.size());

    // Create two individuals with the same Orcid#
  
    for (int i = 1; i <= 2; i++) {

      IndividualEntity individual = new IndividualEntity();
      individual.setFirstname("firstname");
      individual.setMiddlename("middlename");
      individual.setLastname("lastname");
      Integer individualId = nedDBSvc.create( individual );
      assertNotNull(individualId);

      UniqueidentifierEntity uidEntity1 = new UniqueidentifierEntity();
      uidEntity1.setNamedentityid(individualId);
      uidEntity1.setUniqueidentifiertypeid(orcidTypeId);
      uidEntity1.setUniqueidentifier(ORCID_ID);

      assertNull(uidEntity1.getUniqueidentifiersid());
      assertNotNull(uidEntity1.getNamedentityid());

      Integer uidId1 = nedDBSvc.create( uidEntity1 );
      assertNotNull(uidId1);

      // FIND By UID (ORCID) #2
      assertEquals(i, nedQuery.findIndividualsByUid(orcidTypeId, ORCID_ID).size());

      // UPDATE

      UniqueidentifierEntity savedUid = nedDBSvc.findById(uidId1, UniqueidentifierEntity.class);
      savedUid.setUniqueidentifier( savedUid.getUniqueidentifier() + "Z" );
      assertTrue( nedDBSvc.update(savedUid) );

      // Get another instance of same role record 

      UniqueidentifierEntity savedUid2 = nedDBSvc.findById(uidId1, UniqueidentifierEntity.class);
      assertEquals(savedUid, savedUid2);

      // Restore orcid id.

      savedUid.setUniqueidentifier(ORCID_ID);
      assertTrue( nedDBSvc.update(savedUid) );

      // FIND BY JOIN-QUERY 

      List<UniqueidentifierEntity> uids = nedQuery.findResolvedEntities(savedUid.getNamedentityid(), UniqueidentifierEntity.class);
      UniqueidentifierEntity uid = uids.get(0);
      assertEquals(ORCID_ID, uid.getUniqueidentifier());
    }

    // FIND ALL Roles 

    List<UniqueidentifierEntity> allUidsInDb = nedDBSvc.findAll(UniqueidentifierEntity.class);
    assertEquals(2, allUidsInDb.size());

    for (UniqueidentifierEntity uid : allUidsInDb) {
      UniqueidentifierEntity uidToDelete = new UniqueidentifierEntity();
      uidToDelete.setUniqueidentifiersid(uid.getUniqueidentifiersid());
      assertTrue( nedDBSvc.delete(uidToDelete) );
    }
  }

  private Integer findTypeClassStartWith(String prefix) {
    for(TypedescriptionEntity typeClass : nedDBSvc.findAll(TypedescriptionEntity.class)) {
      if (typeClass.getDescription().startsWith(prefix)) {
        return typeClass.getTypeid();
      }
    }
    throw new RuntimeException("No type class found which begins with " + prefix);
  }

  private Integer findTypeValueByName(Integer typeClassId, String name) {
    for(GlobaltypeEntity typeValue : nedDBSvc.findAll(GlobaltypeEntity.class)) {
      if (typeClassId.equals(typeValue.getTypeid()) &&
          typeValue.getShortdescription().equals(name))
      {
        return typeValue.getGlobaltypeid();
      }
    }
    throw new RuntimeException("No type value found with short description =  " + name);
  }
}
