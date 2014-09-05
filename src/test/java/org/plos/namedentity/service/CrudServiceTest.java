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
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Globaltype;
import org.plos.namedentity.api.entity.Individual;
import org.plos.namedentity.api.entity.Typedescription;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

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

  @Test
  public void testIndividualCRUD() {

    // CREATE
    Individual individual = new Individual();
    individual.setFirstname("firstname");
    individual.setLastname("lastname");
    individual.setDisplayname("displayname");
    Integer pkId = crudService.create(individual);
    assertNotNull(pkId);

    // READ
    Individual readEntity = crudService.findById(pkId, Individual.class);
    assertNotNull(readEntity);
    assertEquals("firstname", readEntity.getFirstname());
    assertEquals(null, readEntity.getMiddlename());
    assertEquals(pkId, readEntity.getNedid());

    // UPDATE
    readEntity.setMiddlename("somemiddlename");
    assertTrue(crudService.update(readEntity));
    Individual readEntity2 = crudService.findById(pkId, Individual.class);
//    assertEquals(null, readEntity.getFirstname());  // TODO: since PUT is a full replace
    assertEquals("somemiddlename", readEntity.getMiddlename());

    // DELETE
    assertTrue(crudService.delete(readEntity));
  }

  @Test
  public void testIndividualInvalidEmail() {

    Individual individual = new Individual();
    individual.setFirstname("firstname");
    individual.setLastname("lastname");
    individual.setDisplayname("displayname");
    Integer nedId = crudService.create(individual);

    // Create
    Email email = new Email();
    email.setNedid(nedId);
    email.setType("Work");
    email.setEmailaddress("bill@microsoft");

    try {
      crudService.create(email);
      fail();
    } catch (NedValidationException expected) {
    }

    // Update
    email.setEmailaddress("bill@microsoft.com");
    crudService.create(email);

    email.setEmailaddress("bill@microsoft");

    try {
      crudService.update(email);
      fail();
    } catch (NedValidationException expected) {
    }
  }

  @Test
  public void testNonNullConstraint() {

    Individual individual = new Individual();
    individual.setFirstname("firstname");
    individual.setLastname("lastname");
    individual.setDisplayname("displayname");
    Integer nedId = crudService.create(individual);

    // Create
    Email email = new Email();
    email.setNedid(nedId);
    email.setType("Work");

    try {
      crudService.create(email);
      fail();
    } catch (NedValidationException expected) {
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
    List<Typedescription> typeClasses = crudService.findAll(Typedescription.class);
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

    List<Globaltype> globalTypes = crudService.findAll(Globaltype.class);
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
    globalTypesearchCriteria.setTypeid(10);
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

    List<Email> allEmails = crudService.findAll(Email.class);
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
    newAddress.setCountrycodetype("United States");
    newAddress.setPostalcode("94401");
    //TODO - main contact not well defined.
    //newAddress.setMaincontactnamedentityid(java.lang.Integer maincontactnamedentityid);
    newAddress.setIsprimary((byte)1);
    newAddress.setIsactive((byte)1);

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

    List<Address> allAddresses = crudService.findAll(Address.class);
    assertNotNull(allAddresses);
    assertTrue(allAddresses.contains(savedAddress2));

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    assertTrue( crudService.delete(savedAddress) );
  }

  @Test
  public void testExternalReferencesCRUD() {

    final String ORCID_ID1 = "0000-0001-9430-319X";

    // lookup id for orcid (using hardcoded type class 17 :))

    Globaltype globalTypesearchCriteria = new Globaltype();
    globalTypesearchCriteria.setTypeid(17);
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
    uidEntity.setTypeid(orcidTypeId);
    uidEntity.setUniqueidentifier(ORCID_ID1);

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

    List<Uniqueidentifier> allUids = crudService.findAll(Uniqueidentifier.class);
    assertNotNull(allUids);
    assertTrue(allUids.contains(savedUid2));

    // TODO - FIND BY ATTRIBUTE(S)

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    assertTrue( crudService.delete(savedUid) );
  }
}
