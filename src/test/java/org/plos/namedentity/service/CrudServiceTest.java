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
package org.plos.namedentity.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Auth;
import org.plos.namedentity.api.entity.Degree;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.Globaltype;
import org.plos.namedentity.api.entity.Group;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.api.entity.Organization;
import org.plos.namedentity.api.entity.Typedescription;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.plos.namedentity.api.enums.NamedPartyEnum.*;
import static org.plos.namedentity.api.NedException.ErrorType.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})
public class CrudServiceTest {

  @Autowired
  NamedEntityService namedEntityService;  // inject so can resolve type names to ids

  @Autowired
  CrudService crudService;

  @Autowired
  NamedEntityDBService nedDBSvc;

  @Test
  public void testIndividualCRUD() {

    // CREATE
    Individualprofile individualProfile = _(new Individualprofile());
    individualProfile.setNedid(1);
    individualProfile.setFirstname("firstname");
    individualProfile.setLastname("lastname");
    individualProfile.setDisplayname("displayname_i_cs");
    individualProfile.setSource("Editorial Manager");

    Integer pkId = crudService.create(namedEntityService.resolveValuesToIds(individualProfile));
    assertNotNull(pkId);

    // READ
    Individualprofile readEntity = crudService.findById(pkId, Individualprofile.class);
    assertNotNull(readEntity);
    assertEquals("firstname", readEntity.getFirstname());
    assertEquals(null, readEntity.getMiddlename());
    assertEquals(pkId, readEntity.getId());

    // UPDATE
    readEntity.setMiddlename("somemiddlename");
    assertTrue(crudService.update(readEntity));
    Individualprofile readEntity2 = crudService.findById(pkId, Individualprofile.class);
//    assertEquals(null, readEntity.getFirstname());  // TODO: since PUT is a full replace
    assertEquals("somemiddlename", readEntity.getMiddlename());

    // DELETE
    assertTrue(crudService.delete(readEntity));
  }

  @Test
  public void testOrganizationCRUD() {

    // CREATE

    Integer nedId = nedDBSvc.newNamedEntityId(INDIVIDUAL);

    Organization organization = _(new Organization());
    organization.setNedid(nedId);
    organization.setLegalname("legal name"+ UUID.randomUUID().toString());
    organization.setFamiliarname("familiar name");
    organization.setSource("Ambra");

    namedEntityService.resolveValuesToIds(organization);

    Integer pkId = crudService.create(namedEntityService.resolveValuesToIds(organization));
    assertNotNull(pkId);

    // READ

    Organization readEntity = crudService.findById(pkId, Organization.class);
    assertNotNull(readEntity);
    assertEquals(organization.getLegalname(), readEntity.getLegalname());
    assertEquals(organization.getFamiliarname(), readEntity.getFamiliarname());
    assertEquals(pkId, readEntity.getId());

    // UPDATE

    readEntity.setFamiliarname("familiar name 2");
    assertTrue(crudService.update(readEntity));
    Organization readEntity2 = crudService.findById(pkId, Organization.class);
    assertEquals(readEntity.getFamiliarname(), readEntity2.getFamiliarname());

    // DELETE

    assertTrue(crudService.delete(readEntity));
  }

  @Test
  public void testIndividualInvalidEmail() {

    Integer nedId = nedDBSvc.newNamedEntityId(INDIVIDUAL);

    Individualprofile individualProfile = _(new Individualprofile());
    individualProfile.setNedid(nedId);
    individualProfile.setFirstname("firstname");
    individualProfile.setLastname("lastname");
    individualProfile.setDisplayname("displayname_e");
    individualProfile.setSource("Editorial Manager");
    namedEntityService.resolveValuesToIds(individualProfile);

    crudService.create(individualProfile);

    // Create
    Email email = _(new Email());
    email.setNedid(nedId);
    email.setType("Work");
    email.setEmailaddress("bill@microsoft");
    email.setSource("Editorial Manager");
    namedEntityService.resolveValuesToIds(email);

    try {
      crudService.create(email);
      fail();
    } catch (NedException expected) {
    }

    // Update
    email.setEmailaddress("bill@microsoft.com");
    crudService.create(email);

    email.setEmailaddress("bill@microsoft");

    try {
      crudService.update(email);
      fail();
    } catch (NedException expected) {
    }
  }

  @Test
  public void testNonNullConstraint() {

    Integer nedId = nedDBSvc.newNamedEntityId(INDIVIDUAL);

    Individualprofile individualProfile = _(new Individualprofile());
    individualProfile.setNedid(nedId);
    individualProfile.setFirstname("firstname");
    individualProfile.setLastname("lastname");
    individualProfile.setDisplayname("displayname_nn");
    individualProfile.setSource("Editorial Manager");
    namedEntityService.resolveValuesToIds(individualProfile);
    crudService.create(individualProfile);

    // Create
    Email email = new Email();
    email.setNedid(nedId);
    email.setType("Work");
    email.setSource("Editorial Manager");

    try {
      crudService.create(email);
      fail();
    } catch (NedException expected) {
    }
  }

  @Test
  public void testTypeDescriptionCRUD() {

    // CREATE

    Typedescription newType = new Typedescription();
    newType.setDescription("description");
    newType.setHowused("howused");

    Integer pkId = crudService.create(newType);
    assertNotNull(pkId);

    Typedescription savedType = crudService.findById(pkId, Typedescription.class);
    assertNotNull(savedType);
    assertEquals(pkId, savedType.getId());

    // UPDATE

    savedType.setDescription("description2");
    assertTrue(crudService.update(savedType));
    Typedescription savedType2 = crudService.findById(pkId, Typedescription.class);
    assertEquals(savedType, savedType2);

    // DELETE - delete type class without any children

    assertTrue(crudService.delete(savedType));

    // DELETE - deleting type class with children will raise a fk constraint
    //          exception.

    try {
      Typedescription typeClassWithKids = new Typedescription();
      typeClassWithKids.setId(1);
      crudService.delete(typeClassWithKids);
      fail();
    } catch (org.springframework.dao.DataAccessException expected) {
    }

    // FIND By Id
    Typedescription typeClass1 = crudService.findById(1, Typedescription.class);
    assertNotNull(typeClass1);
    assertEquals(Integer.valueOf(1), typeClass1.getId());

    // FIND All
    List<Typedescription> typeClasses = crudService.findAll(Typedescription.class, 0, Integer.MAX_VALUE);
    assertNotNull(typeClasses);
    assertTrue(typeClasses.contains(typeClass1));
  }

  @Test
  public void testGlobalTypesCRUD() {

    // CREATE

    Globaltype newTypeVal = new Globaltype();
    newTypeVal.setTypeid(1);
    newTypeVal.setShortdescription("type value abc");
    newTypeVal.setLongdescription("longdescription");
    newTypeVal.setTypecode("abc");
    newTypeVal.setCreated(new Timestamp(Calendar.getInstance().getTime().getTime()));
    newTypeVal.setLastmodified(new Timestamp(Calendar.getInstance().getTime().getTime()));

    Integer pkId = crudService.create(newTypeVal);
    assertNotNull( pkId );
    assertNotNull(newTypeVal.getCreated());
    assertNotNull(newTypeVal.getLastmodified());
    
    Globaltype savedTypeVal = crudService.findById(pkId, Globaltype.class);
    assertNotNull( savedTypeVal );
    assertEquals(pkId, savedTypeVal.getId());

    // UPDATE

    savedTypeVal.setShortdescription("abc2");
    assertTrue( crudService.update(savedTypeVal) );

    Globaltype savedTypeVal2 = crudService.findById(pkId, Globaltype.class);
    assertEquals(savedTypeVal, savedTypeVal2);

    // DELETE - delete type class without any children

    assertTrue( crudService.delete(savedTypeVal) );

    //TODO - try to delete a global type with foreign key references.

    // FIND By Id

    Globaltype typeVal1 = crudService.findById(1, Globaltype.class);
    assertNotNull(typeVal1);
    assertEquals(Integer.valueOf(1), typeVal1.getId());

    // FIND All

    List<Globaltype> globalTypes = crudService.findAll(Globaltype.class, 0, Integer.MAX_VALUE);
    assertNotNull(globalTypes);
    assertTrue(globalTypes.contains(typeVal1));

    // FIND BY ATTRIBUTE(S)

    Globaltype globalTypesearchCriteria = new Globaltype();
    globalTypesearchCriteria.setTypeid(1);

    List<Globaltype> globalTypesForTypeClass = crudService.findByAttribute(globalTypesearchCriteria, false);
    assertNotNull(globalTypesForTypeClass);
    for (Globaltype gtype : globalTypesForTypeClass) {
      assertTrue(globalTypes.contains(gtype));
    }
  }

  @Test
  public void testEmailsCRUD() {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    // lookup id for "work" email type

    Globaltype globalTypesearchCriteria = new Globaltype();
    globalTypesearchCriteria.setTypeid( nedDBSvc.findTypeClass("Email Address Types") );
    globalTypesearchCriteria.setShortdescription("Work");
    List<Globaltype> globalTypesResult = crudService.findByAttribute(globalTypesearchCriteria, false);
    assertEquals(1, globalTypesResult.size());

    Integer emailTypeId = globalTypesResult.get(0).getId();
    assertNotNull( emailTypeId );

    // create pojo

    Email newEmail = _(new Email());
    newEmail.setNedid(1);
    newEmail.setTypeid(emailTypeId);
    newEmail.setEmailaddress("walter@foo.com");
    newEmail.setSource("Editorial Manager");
    namedEntityService.resolveValuesToIds(newEmail);

    // save record

    Integer pkId = crudService.create(newEmail);
    assertNotNull( pkId );

    Email savedEmail = crudService.findById(pkId, Email.class);
    assertNotNull( savedEmail );
    assertEquals(pkId, savedEmail.getId());
    assertEquals(emailTypeId, savedEmail.getTypeid());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    savedEmail.setEmailaddress("super" + savedEmail.getEmailaddress());
    assertTrue( crudService.update(savedEmail) );
    Email savedEmail2 = crudService.findById(pkId, Email.class);
    assertEquals(savedEmail, savedEmail2);

    /* ------------------------------------------------------------------ */
    /*  FINDERS                                                           */
    /* ------------------------------------------------------------------ */

    // FIND All

    List<Email> allEmails = crudService.findAll(Email.class, 0, Integer.MAX_VALUE);
    assertNotNull(allEmails);
    assertTrue(allEmails.contains(savedEmail2));

    // FIND BY ATTRIBUTE(S)

    Email emailSearchCriteria = new Email();
    emailSearchCriteria.setEmailaddress(savedEmail2.getEmailaddress());

    List<Email> foundEmails = crudService.findByAttribute(emailSearchCriteria, false);
    assertNotNull(foundEmails);
    assertEquals(savedEmail2.getEmailaddress(), foundEmails.get(0).getEmailaddress());
    
    
    

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    assertTrue( crudService.delete(savedEmail) );
  }
  
  @Test
  public void testEmailMatchWithPartialFlag() {
	  
	  List<Email> allEmailsInDB = crudService.findAll(Email.class, 0, Integer.MAX_VALUE);
	  String firstEmailAddressFromDB = allEmailsInDB.get(0).getEmailaddress();
	  
	  if(firstEmailAddressFromDB.length() > 0){
		    String splittedEmailAddress = firstEmailAddressFromDB.split("@")[0];
		    Boolean partialFlag = true ;
		  
		  //Case 1: partialFlag boolean value is "true" and emailaddress attribute contain exact email address.

		    Email emailSearchByAddress = new Email();
		    emailSearchByAddress.setEmailaddress(firstEmailAddressFromDB);
		    List<Email> foundEmails = crudService.findByAttribute(emailSearchByAddress, partialFlag);
		    assertNotNull(foundEmails);
		    
		  //Case 2: partialFlag boolean value is "true" and emailaddress attribute contain some string(not exact email address), To test wild card scenario.

		    emailSearchByAddress.setEmailaddress(splittedEmailAddress);
		    foundEmails = crudService.findByAttribute(emailSearchByAddress, partialFlag);
		    assertNotNull(foundEmails);
		    
          //Case 3: partialFlag boolean value is "false" and emailaddress attribute contain exact email address.

		    partialFlag = false;
		    emailSearchByAddress.setEmailaddress(firstEmailAddressFromDB);
		    foundEmails = crudService.findByAttribute(emailSearchByAddress, partialFlag);
		    assertNotNull(foundEmails);
		    
		  //Case 4: partialFlag boolean value is "false" and emailaddress attribute contain some string(not exact email address).
		  //        To test wild card scenario. No matched found in this scenario , so it should return empty value.

		    emailSearchByAddress.setEmailaddress(splittedEmailAddress);
			foundEmails = crudService.findByAttribute(emailSearchByAddress, partialFlag);
			assertEquals(0, foundEmails.size());   
	  }
  }

  @Test
  public void testGroupsCRUD() {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    Group newGroup = _(new Group());
    newGroup.setNedid(1);
    newGroup.setApplicationtype("Named Party DB");
    newGroup.setType("NED Admin");
    newGroup.setStartdate(LocalDate.now());
    newGroup.setCreated(new Timestamp(Calendar.getInstance().getTime().getTime()));
    newGroup.setLastmodified(new Timestamp(Calendar.getInstance().getTime().getTime()));
    newGroup.setSource("Ambra");

    // save record

    Integer pkId = crudService.create( namedEntityService.resolveValuesToIds(newGroup) );
    assertNotNull( pkId );

    Group savedGroup = crudService.findById(pkId, Group.class);
    assertNotNull( savedGroup );
    assertEquals(pkId, savedGroup.getId());
    assertNotNull( savedGroup.getApplicationtypeid() );
    assertNotNull( savedGroup.getTypeid() );

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    savedGroup.setEnddate(LocalDate.now());
    assertTrue( crudService.update(savedGroup) );
    Group savedGroup2 = crudService.findById(pkId, Group.class);
    assertEquals(savedGroup, savedGroup2);

    /* ------------------------------------------------------------------ */
    /*  FINDERS                                                           */
    /* ------------------------------------------------------------------ */

    List<Group> allGroups = crudService.findAll(Group.class, 0, Integer.MAX_VALUE);
    assertNotNull(allGroups);
    assertTrue(allGroups.contains(savedGroup2));

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    assertTrue( crudService.delete(savedGroup) );
  }

  @Test
  public void testAddressesCRUD() {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    Address newAddress = _(new Address());
    newAddress.setNedid(1);
    newAddress.setType("Office");
    newAddress.setAddressline1("addressline 1");
    newAddress.setAddressline2("addressline 2");
    newAddress.setAddressline3("addressline 3");
    newAddress.setCity("city");
    newAddress.setStatecodetype("CA");
    newAddress.setCountrycodetype("United States of America");
    newAddress.setPostalcode("94401");
    //TODO - main contact not well defined.
    //newAddress.setMaincontactnamedentityid(java.lang.Integer maincontactnamedentityid);
    newAddress.setIsactive(true);
    newAddress.setSource("Editorial Manager");

    // save record

    Integer pkId = crudService.create( namedEntityService.resolveValuesToIds(newAddress) );
    assertNotNull( pkId );

    Address savedAddress = crudService.findById(pkId, Address.class);
    assertNotNull( savedAddress );
    assertEquals(pkId, savedAddress.getId());
    assertNotNull( savedAddress.getTypeid() );
    assertNotNull( savedAddress.getStatecodetypeid() );

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    savedAddress.setAddressline1("update." + savedAddress.getAddressline1());
    assertTrue( crudService.update(savedAddress) );

    Address savedAddress2 = crudService.findById(pkId, Address.class);
    assertEquals(savedAddress, savedAddress2);

    /* ------------------------------------------------------------------ */
    /*  FINDERS                                                           */
    /* ------------------------------------------------------------------ */

    List<Address> allAddresses = crudService.findAll(Address.class, 0, Integer.MAX_VALUE);
    assertNotNull(allAddresses);
    assertTrue(allAddresses.contains(savedAddress2));

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    assertTrue( crudService.delete(savedAddress) );
  }

  @Test
  public void testDegreeCRUD() {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    // try to create a degree without a title or type

    try {
      Degree newDegree = _(new Degree());
      newDegree.setNedid(1);
      newDegree.setSource("Ambra");
      namedEntityService.resolveValuesToIds(newDegree);
      crudService.create(newDegree);
      fail();
    } catch (NedException expected) {
      assertEquals(InvalidDegreeError, expected.getErrorType());
    }

    // create valid degree

    Degree newDegree = _(new Degree());
    newDegree.setNedid(1);
    newDegree.setType("Masters");
    newDegree.setSource("Ambra");
    namedEntityService.resolveValuesToIds(newDegree);

    // save record

    Integer pkId = crudService.create(newDegree);
    assertNotNull( pkId );

    Degree savedDegree = crudService.findById(pkId, Degree.class);
    assertNotNull( savedDegree );
    assertEquals(pkId, savedDegree.getId());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    final String degreeDesc = "M.S. Computer Science "+UUID.randomUUID().toString();

    savedDegree.setFulltitle(degreeDesc);
    assertTrue( crudService.update(savedDegree) );
    Degree savedDegree2 = crudService.findById(pkId, Degree.class);
    assertEquals(savedDegree, savedDegree2);

    // try to update degree w/o a title or type

    try {
      savedDegree.setFulltitle(null);
      savedDegree.setType(null);
      crudService.update(savedDegree);
      fail();
    } catch (NedException expected) {
      assertEquals(InvalidDegreeError, expected.getErrorType());
    }

    /* ------------------------------------------------------------------ */
    /*  FINDERS                                                           */
    /* ------------------------------------------------------------------ */

    // FIND All

    List<Degree> allDegrees = crudService.findAll(Degree.class, 0, Integer.MAX_VALUE);
    assertNotNull(allDegrees);
    assertTrue(allDegrees.contains(savedDegree2));

    // FIND BY ATTRIBUTE(S)

    Degree degreeSearchCriteria = new Degree();
    degreeSearchCriteria.setFulltitle(degreeDesc);

    List<Degree> foundDegrees = crudService.findByAttribute(degreeSearchCriteria, false);
    assertNotNull(foundDegrees);
    assertEquals(savedDegree2.getFulltitle(), foundDegrees.get(0).getFulltitle());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    assertTrue( crudService.delete(savedDegree) );
  }


  @Test
  public void testUniqueIdentifiersCRUD() {

    final String ORCID_ID1 = "0000-0001-9430-005X";

    // lookup id for orcid

    Globaltype globalTypesearchCriteria = new Globaltype();
    globalTypesearchCriteria.setTypeid( nedDBSvc.findTypeClass("UID Individual Types") );
    globalTypesearchCriteria.setShortdescription("ORCID");
    List<Globaltype> globalTypesResult = crudService.findByAttribute(globalTypesearchCriteria, false);
    assertEquals(1, globalTypesResult.size());

    Integer orcidTypeId = globalTypesResult.get(0).getId();
    assertNotNull( orcidTypeId );
    
    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    Uniqueidentifier uidEntity = _(new Uniqueidentifier());
    uidEntity.setNedid(1);
    uidEntity.setType("ORCID");
    uidEntity.setUniqueidentifier(ORCID_ID1);
    uidEntity.setSource("Editorial Manager");
    namedEntityService.resolveValuesToIds(uidEntity);

    // save record

    Integer pkId = crudService.create(uidEntity);
    assertNotNull( pkId );

    Uniqueidentifier savedUid = crudService.findById(pkId, Uniqueidentifier.class);
    assertNotNull( savedUid );
    assertEquals(pkId, savedUid.getId());
    assertEquals(orcidTypeId, savedUid.getTypeid());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    savedUid.setUniqueidentifier( savedUid.getUniqueidentifier() + "Z" );
    assertTrue( crudService.update(savedUid) );

    Uniqueidentifier savedUid2 = crudService.findById(pkId, Uniqueidentifier.class);
    assertEquals(savedUid, savedUid2);

    /* ------------------------------------------------------------------ */
    /*  FINDERS                                                           */
    /* ------------------------------------------------------------------ */

    // FIND All

    List<Uniqueidentifier> allUids = crudService.findAll(Uniqueidentifier.class, 0, Integer.MAX_VALUE);
    assertNotNull(allUids);
    assertTrue(allUids.contains(savedUid2));

    // TODO - FIND BY ATTRIBUTE(S)

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    assertTrue( crudService.delete(savedUid) );
  }

  @Test
  public void testAuthCasCRUD() {

    final String PASSWORD = "super_secret_password1";

    Auth authEntity = _(new Auth());
    String authId = authEntity.getAuthid();
    assertNotNull(authId);
    assertEquals((32+4), authId.length());

    // try to save auth record with null password fields

    try { 
      crudService.create(authEntity);
      fail();
    } catch (NedException expected) {
      assertEquals(PasswordError, expected.getErrorType());
    }

    // try to save auth record with invalid input password

    String[] invalidInputPasswords = { "", "123" };

    for (String badPassword : invalidInputPasswords) {
      try { 
        authEntity.setPlainTextPassword(badPassword);
        crudService.create(authEntity);
        fail();
      } catch (NedException expected) {
        assertEquals(PasswordFormatError, expected.getErrorType());
      }
    }

    // fill in auth record attributes and try again (happy path)

    Integer nedId = nedDBSvc.newNamedEntityId(INDIVIDUAL);

    Email email = _(new Email());
    email.setNedid(nedId);
    email.setType("Work");
    email.setEmailaddress(UUID.randomUUID().toString()+"@foo.com");
    email.setSource("Editorial Manager");
    namedEntityService.resolveValuesToIds(email);

    Integer emailId = crudService.create(email);
    assertNotNull( emailId );

    authEntity.setEmailid(emailId);
    authEntity.setNedid(nedId);

    authEntity.setPlainTextPassword(PASSWORD);
    assertTrue(new PasswordDigestService().verifyPassword(PASSWORD, authEntity.getPassword()));

    Integer authEntityId = crudService.create(authEntity);
    assertNotNull( authEntityId );
    Auth savedAuth = crudService.findById(authEntityId, Auth.class);

    assertEquals(authEntity.getPassword(), savedAuth.getPassword());
    assertTrue(new PasswordDigestService().verifyPassword(PASSWORD, savedAuth.getPassword()));

    // verify auth validation (invalid hash length)

    try { 
      authEntity.setPlainTextPassword(PASSWORD);
      authEntity.setPassword( authEntity.getPassword()+"0" );
      crudService.create(authEntity);
      fail();
    } catch (NedException expected) {
      assertEquals(DigestPasswordError, expected.getErrorType());
    }

    // verify auth validation (tampered hash)

    try { 
      authEntity.setPlainTextPassword(PASSWORD);
      authEntity.setPassword( modifyHashPassword(authEntity.getPassword()) );
      crudService.create(authEntity);
      fail();
    } catch (NedException expected) {
      assertEquals(TamperedPasswordError, expected.getErrorType());
    }
  }

  private String modifyHashPassword(String password) {
    // modify secure password preserving hex representation
    char[] chars = password.toCharArray();
    int hexValue = Integer.parseInt(String.valueOf(chars[0]), 16);

    // get next hex with rollover
    String nextHex = Integer.toHexString( (hexValue+1) % 16 );
    chars[0] = nextHex.charAt(0);
    return new String(chars);
  }

  // decorator function to endow entity with core attribute values, such as createdby.
  private <S extends Entity>
  S _(S entity) {
    entity.setCreatedby(1);
    entity.setLastmodifiedby(1);
    return entity;
  }
}
