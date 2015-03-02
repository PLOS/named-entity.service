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
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Globaltype;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.api.entity.Organization;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Typedescription;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

    Integer nedId = nedDBSvc.newNamedEntityId("Individual");

    // CREATE
    Individualprofile individualProfile = new Individualprofile();
    individualProfile.setNedid(nedId);
    individualProfile.setFirstname("firstname");
    individualProfile.setLastname("lastname");
    individualProfile.setDisplayname("displayname_i");
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

    Integer nedId = nedDBSvc.newNamedEntityId("Individual");

    Organization organization = new Organization();
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

    Integer nedId = nedDBSvc.newNamedEntityId("Individual");

    Individualprofile individualProfile = new Individualprofile();
    individualProfile.setNedid(nedId);
    individualProfile.setFirstname("firstname");
    individualProfile.setLastname("lastname");
    individualProfile.setDisplayname("displayname_e");
    individualProfile.setSource("Editorial Manager");
    namedEntityService.resolveValuesToIds(individualProfile);

    crudService.create(individualProfile);

    // Create
    Email email = new Email();
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

    Integer nedId = nedDBSvc.newNamedEntityId("Individual");

    Individualprofile individualProfile = new Individualprofile();
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

    List<Globaltype> globalTypesForTypeClass = crudService.findByAttribute(globalTypesearchCriteria);
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
    List<Globaltype> globalTypesResult = crudService.findByAttribute(globalTypesearchCriteria);
    assertEquals(1, globalTypesResult.size());

    Integer emailTypeId = globalTypesResult.get(0).getId();
    assertNotNull( emailTypeId );

    // create pojo

    Email newEmail = new Email();
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

    List<Email> foundEmails = crudService.findByAttribute(emailSearchCriteria);
    assertNotNull(foundEmails);
    assertEquals(savedEmail2.getEmailaddress(), foundEmails.get(0).getEmailaddress());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    assertTrue( crudService.delete(savedEmail) );
  }

  @Test
  public void testRolesCRUD() {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    Role newRole = new Role();
    newRole.setNedid(1);
    newRole.setApplicationtype("Editorial Manager");
    newRole.setType("Academic Editor (PLOS ONE)");
    newRole.setStartdate( dateNow() );
    newRole.setCreated(new Timestamp(Calendar.getInstance().getTime().getTime()));
    newRole.setLastmodified(new Timestamp(Calendar.getInstance().getTime().getTime()));
    newRole.setSource("Editorial Manager");

    // save record

    Integer pkId = crudService.create( namedEntityService.resolveValuesToIds(newRole) );
    assertNotNull( pkId );

    Role savedRole = crudService.findById(pkId, Role.class);
    assertNotNull( savedRole );
    assertEquals(pkId, savedRole.getId());
    assertNotNull( savedRole.getApplicationtypeid() );
    assertNotNull( savedRole.getTypeid() );

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    savedRole.setEnddate( dateNow() );
    assertTrue( crudService.update(savedRole) );
    Role savedRole2 = crudService.findById(pkId, Role.class);
    assertEquals(savedRole, savedRole2);

    /* ------------------------------------------------------------------ */
    /*  FINDERS                                                           */
    /* ------------------------------------------------------------------ */

    List<Role> allRoles = crudService.findAll(Role.class, 0, Integer.MAX_VALUE);
    assertNotNull(allRoles);
    assertTrue(allRoles.contains(savedRole2));

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    assertTrue( crudService.delete(savedRole) );
  }

  @Test
  public void testAddressesCRUD() {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    Address newAddress = new Address();
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
  public void testUniqueIdentifiersCRUD() {

    final String ORCID_ID1 = "0000-0001-9430-005X";

    // lookup id for orcid

    Globaltype globalTypesearchCriteria = new Globaltype();
    globalTypesearchCriteria.setTypeid( nedDBSvc.findTypeClass("Unique Identifier Types") );
    globalTypesearchCriteria.setShortdescription("ORCID");
    List<Globaltype> globalTypesResult = crudService.findByAttribute(globalTypesearchCriteria);
    assertEquals(1, globalTypesResult.size());

    Integer orcidTypeId = globalTypesResult.get(0).getId();
    assertNotNull( orcidTypeId );
    
    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    Uniqueidentifier uidEntity = new Uniqueidentifier();
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
