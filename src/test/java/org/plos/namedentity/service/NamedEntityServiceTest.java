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

import org.eclipse.core.runtime.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Degree;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Globaltype;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.api.entity.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})
public class NamedEntityServiceTest {

  @Autowired
  NamedEntityService namedEntityService;

  @Autowired
  CrudService crudService;

  @Test
  public void testCreateIndividualCompositeWithoutName() {
    // triggers phase 1 validation failure
    try {
      namedEntityService.createIndividualComposite(new IndividualComposite());
      fail();
    }
    catch (NedValidationException expected) {
    }
  }

  @Test
  public void testIndividualCompositeEquality() {
    IndividualComposite composite1 = newCompositeIndividualWithRole();

    IndividualComposite composite2 = newCompositeIndividualWithRole();

    composite2.getIndividualprofiles().get(0).setDisplayname(
        composite1.getIndividualprofiles().get(0).getDisplayname());

    assertEquals(composite1, composite2);

    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setType("Work");
    workEmail.setEmailaddress("fu.manchu.work@foo.com");
    workEmail.setSource("Editorial Manager");
    emails.add( workEmail );

    Email personalEmail = new Email();
    personalEmail.setType("Personal");
    personalEmail.setEmailaddress("fu.manchu.home@foo.com");
    personalEmail.setSource("Editorial Manager");
    emails.add( personalEmail );

    composite1.setEmails( emails );
    assertNotEquals(composite1, composite2);

    composite2.setEmails( emails );
    assertEquals(composite1, composite2);

    // list order should not affect equality
    emails = new ArrayList<>();
    emails.add( personalEmail );
    emails.add( workEmail );

    composite2.setEmails( emails );

    assertEquals(composite1, composite2);
  }

  @Test
  public void testCreateIndividualCompositeWithRole() {

    IndividualComposite composite = newCompositeIndividualWithRole();

    /* ------------------------------------------------------------------ */
    /*  EMAILS                                                            */
    /* ------------------------------------------------------------------ */

    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setType("Work");
    workEmail.setEmailaddress("fu.manchu.work@foo.com");
    workEmail.setSource("Editorial Manager");
    emails.add( workEmail );

    Email personalEmail = new Email();
    personalEmail.setType("Personal");
    personalEmail.setEmailaddress("fu.manchu.home@foo.com");
    personalEmail.setSource("Editorial Manager");
    emails.add( personalEmail );

    composite.setEmails( emails );

    /* ------------------------------------------------------------------ */
    /*  PHONE NUMBERS                                                     */
    /* ------------------------------------------------------------------ */

    List<Phonenumber> phonenumbers = new ArrayList<>();

    Phonenumber officePhone = new Phonenumber();
    officePhone.setType("Office");
    officePhone.setCountrycodetype("01");
    officePhone.setPhonenumber("123-456-7890");
    officePhone.setSource("Editorial Manager");
    phonenumbers.add( officePhone );

    Phonenumber mobilePhone = new Phonenumber();
    mobilePhone.setType("Mobile");
    mobilePhone.setCountrycodetype("01");
    mobilePhone.setPhonenumber("123-444-0011");
    mobilePhone.setSource("Editorial Manager");
    phonenumbers.add( mobilePhone );

    Phonenumber homePhone = new Phonenumber();
    homePhone.setType("Home");
    homePhone.setCountrycodetype("01");
    homePhone.setPhonenumber("123-555-6666");
    homePhone.setSource("Editorial Manager");
    phonenumbers.add( homePhone );

    composite.setPhonenumbers( phonenumbers );

    /* ------------------------------------------------------------------ */
    /*  ADDRESSES                                                         */
    /* ------------------------------------------------------------------ */

    List<Address> addresses = new ArrayList<>();

    Address officeAddress = new Address();
    officeAddress.setType("Office");
    officeAddress.setAddressline1("addressline1");
    officeAddress.setAddressline2("addressline2");
    officeAddress.setCity("city");
    officeAddress.setStatecodetype("CA");
    officeAddress.setCountrycodetype("United States of America");
    officeAddress.setPostalcode("1234567");
    officeAddress.setSource("Editorial Manager");
    addresses.add( officeAddress );

    composite.setAddresses( addresses );

    /* ------------------------------------------------------------------ */
    /*  DEGREES                                                           */
    /* ------------------------------------------------------------------ */

    List<Degree> degrees = new ArrayList<>();

    Degree degree = new Degree();
    degree.setType("MD");
    degree.setSource("Editorial Manager");
    degrees.add(degree);

    composite.setDegrees( degrees );

    /* ------------------------------------------------------------------ */
    /*  UNIQUE IDENTIFIERS                                                */
    /* ------------------------------------------------------------------ */

    Uniqueidentifier uidEntity = new Uniqueidentifier();
    uidEntity.setType("ORCID");
    uidEntity.setUniqueidentifier("0000-0001-9430-319X");
    uidEntity.setSource("Editorial Manager");

    composite.getUniqueidentifiers().add(uidEntity);

    /* ------------------------------------------------------------------ */
    /*  URLS                                                              */
    /* ------------------------------------------------------------------ */

    List<Url> urls = new ArrayList<>();
    Url url = new Url();
    url.setUrl("http://goodurl.org");
    url.setUrl("httpXX://badurl.org");
    url.setSource("Editorial Manager");
    urls.add(url);

    composite.setUrls(urls);

    Integer nedId = null;

    try {
      namedEntityService.createIndividualComposite(composite);
      fail("invalid URL was not rejected");
    } catch (NedValidationException expected) {
      // expected since url is invalid
    }

    urls = new ArrayList<>();
    url.setUrl("http://newgoodurl.org");
    url.setSource("Editorial Manager");
    urls.add(url);

    composite.setUrls(urls);

    try {
      IndividualComposite responseComposite = namedEntityService.createIndividualComposite(composite);
      assertNotNull(responseComposite);
      assertNotNull(responseComposite.getIndividualprofiles().get(0).getNedid());

      // make sure foreign keys are resolved for sub entities
      assertNotNull(responseComposite.getEmails().get(0).getId());
    }
    catch (Exception e) {
      fail(e.getMessage());
    }

    Email emailSearchCriteria = new Email();
    emailSearchCriteria.setEmailaddress("fu.manchu.work@foo.com");
    List<Email> emailSearchResult = crudService.findByAttribute(emailSearchCriteria);
    assertEquals(1, emailSearchResult.size());

    nedId = emailSearchResult.get(0).getNedid();
    assertNotNull( nedId );

    // Test "By NedId" Finders

    List<Individualprofile> entity = namedEntityService.findResolvedEntities(nedId, Individualprofile.class);
    assertEquals(1, entity.size());

    List<Address> addressesEntities = namedEntityService.findResolvedEntities(nedId, Address.class);
    assertEquals(1, addressesEntities.size());

    List<Email> emailEntities = namedEntityService.findResolvedEntities(nedId, Email.class);
    assertEquals(2, emailEntities.size());

    List<Phonenumber> phonenumberEntities = namedEntityService.findResolvedEntities(nedId, Phonenumber.class);
    assertEquals(3, phonenumberEntities.size());

    List<Role> roleEntities = namedEntityService.findResolvedEntities(nedId, Role.class);
    assertEquals(1, roleEntities.size());

    List<Uniqueidentifier> uidEntities = namedEntityService.findResolvedEntities(nedId, Uniqueidentifier.class);
    assertEquals(2, uidEntities.size());

    Individualprofile individualProfile = namedEntityService.findResolvedEntityByUid("ORCID", "0000-0001-9430-319X", Individualprofile.class);

    assertNotNull(individualProfile);

    assertEquals("firstname", individualProfile.getFirstname());
    assertEquals("lastname", individualProfile.getLastname());
  }

  private Integer findUidTypeIdByName(String typeName) {

    Globaltype globalTypesearchCriteria = new Globaltype();
    globalTypesearchCriteria.setTypeid(17);
    globalTypesearchCriteria.setShortdescription(typeName);
    List<Globaltype> globalTypesResult = crudService.findByAttribute(globalTypesearchCriteria);
    assertEquals(1, globalTypesResult.size());

    return globalTypesResult.get(0).getId();
  }

  @Test
  public void testCreateIndividualCompositeWithInvalidEmail() {

    IndividualComposite composite = newCompositeIndividualWithRole();

    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setType("Work");
    workEmail.setEmailaddress("invalid@email");
    workEmail.setSource("Editorial Manager");
    emails.add( workEmail );

    composite.setEmails( emails );

    try {
      namedEntityService.createIndividualComposite(composite);
      fail();
    }
    catch (NedValidationException expected) {
    }
    // verify entities not committed to db. we'll just check email.
    finally {
      Email emailSearchCriteria = new Email();
      emailSearchCriteria.setEmailaddress("invalid@email");
      List<Email> emailSearchResult = crudService.findByAttribute(emailSearchCriteria);
      assertEquals(0, emailSearchResult.size());
    }
  }

  @Test
  public void testCreateIndividualCompositeValidator() {

    IndividualComposite composite = newCompositeIndividualWithRole();

    try {
      namedEntityService.createIndividualComposite(composite);
      fail();
    } catch (NedValidationException expected) {
      Assert.isTrue(expected.getMessage().equals("Email entities can not be empty"));
    }

    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setType("Work");
    workEmail.setEmailaddress("valid@email.com");
    workEmail.setSource("Editorial Manager");
    emails.add( workEmail );

    composite.setEmails( emails );
    composite.setIndividualprofiles(null);

    try {
      namedEntityService.createIndividualComposite(composite);
      fail();
    } catch (NedValidationException expected) {
      Assert.isTrue(expected.getMessage().equals("Profile entities can not be empty"));
    }

    composite = newCompositeIndividualWithRole();
    composite.setEmails( emails );

    namedEntityService.createIndividualComposite(composite);
  }

  @Test
  public void testProfileEntityCrud() {

    Individualprofile individualProfile = new Individualprofile();
    individualProfile.setFirstname("");
    individualProfile.setLastname("lastname");
    individualProfile.setDisplayname("displayname_p");
    individualProfile.setNameprefix("Mr.");
    individualProfile.setNamesuffix("III");
    individualProfile.setSource("Editorial Manager");
    individualProfile.setNedid(1);

    try {
      crudService.create(namedEntityService.resolveValuesToIds(individualProfile));
      fail();
    }
    catch (NedValidationException expected) {
      System.out.println(expected.getMessage());
      // first name too short
    }

    individualProfile.setFirstname("firstname");

    Integer profileId = crudService.create( namedEntityService.resolveValuesToIds(individualProfile) );
    assertNotNull( profileId );

    Individualprofile savedEntity = namedEntityService.findResolvedEntityByKey(profileId, Individualprofile.class);
    assertNotNull( savedEntity.getFirstname() );

    // UPDATE

    individualProfile.setId(profileId);
    individualProfile.setFirstname("firstname2");

    assertTrue(crudService.update(namedEntityService.resolveValuesToIds(individualProfile)));

    // DELETE

    assertTrue( crudService.delete(individualProfile) );
  }

  @Test
  public void testEmailEntityCrud() {

    // CREATE email entity. we don't expect the email type to persist.

    Email emailEntity = new Email();
    emailEntity.setNedid(1);
    emailEntity.setType("Work");
    emailEntity.setEmailaddress("bill_1@microsoft.com");
    emailEntity.setSource("Editorial Manager");

    Integer createEmailId = crudService.create(namedEntityService.resolveValuesToIds(emailEntity));
    assertNotNull( createEmailId );

    Email savedEntity = namedEntityService.findResolvedEntityByKey(createEmailId, Email.class);
    assertNotNull( savedEntity.getType() );

    // try again but this time use type resolver. remember that type names are
    // resolved by joins when querying database -- need foreign key to get name.
    
    namedEntityService.resolveValuesToIds(emailEntity);

    emailEntity.setEmailaddress("bill_2@microsoft.com");

    Integer createEmailId2 = crudService.create(emailEntity);
    assertNotNull( createEmailId2 );

    Email savedEntity2 = namedEntityService.findResolvedEntityByKey(createEmailId2, Email.class);
    assertNotNull( savedEntity2.getType() );

    // UPDATE email entity. Scrub appropriate attributes from current instance
    // and reuse. Again, we don't expect for email type to persist.

    emailEntity.setTypeid(null);
    emailEntity.setEmailaddress("bill_3@microsoft.com");
    emailEntity.setId(createEmailId);
    assertTrue( crudService.update(emailEntity) );

    Email savedEntity3 = namedEntityService.findResolvedEntityByKey(createEmailId, Email.class);
    assertNull( savedEntity3.getType() );

    // try again with type resolver.

    namedEntityService.resolveValuesToIds(emailEntity);

    assertTrue( crudService.update(emailEntity) );

    Email savedEntity4 = namedEntityService.findResolvedEntityByKey(createEmailId, Email.class);
    assertNotNull( savedEntity4.getType() );

    // DELETE.

    assertTrue( crudService.delete(emailEntity) );

    emailEntity.setId(createEmailId2);
    assertTrue( crudService.delete(emailEntity) );
  }

  @Test
  public void testRoleEntityCrud() {

    // CREATE role entity. we don't expect the address type to persist.

    Role roleEntity = new Role();
    roleEntity.setNedid(1);
    roleEntity.setApplicationtype("Editorial Manager");
    roleEntity.setType("Academic Editor (PLOS ONE)");
    roleEntity.setStartdate( dateNow() );
    roleEntity.setLastmodified(new Timestamp(Calendar.getInstance().getTime().getTime()));
    roleEntity.setCreated(new Timestamp(Calendar.getInstance().getTime().getTime()));
    roleEntity.setSource("Editorial Manager");

    try {
      crudService.create(roleEntity);
      fail();
    }
    catch (NedValidationException expected) {
      // typeid hasn't been resolved yet, so we expect a not-null
      // constraint to be thrown
    }

    // try again but this time use type resolver. remember that type names are
    // resolved by joins when querying database -- need foreign key to get name.
    
    Integer createRoleId = crudService.create( namedEntityService.resolveValuesToIds(roleEntity) );
    assertNotNull( createRoleId );

    Role savedEntity = namedEntityService.findResolvedEntityByKey(createRoleId, Role.class);
    assertNotNull( savedEntity.getType() );

    // UPDATE role entity. Scrub appropriate attributes from current instance
    // and reuse. 

    roleEntity.setId(createRoleId); 
    roleEntity.setApplicationtypeid(null);
    roleEntity.setTypeid(null); 
    assertTrue( crudService.update(namedEntityService.resolveValuesToIds(roleEntity)) );

    Role savedEntity2 = namedEntityService.findResolvedEntityByKey(createRoleId, Role.class);
    assertNotNull( savedEntity2.getType() );

    // DELETE.

    assertTrue( crudService.delete(roleEntity) );
  }

  @Test
  public void testAddressEntityCrud() {

    // CREATE address entity. we don't expect the address type to persist.

    Address addressEntity = new Address();
    addressEntity.setNedid(1);
    addressEntity.setType("Office");
    addressEntity.setAddressline1("addressline 1");
    addressEntity.setAddressline2("addressline 2");
    addressEntity.setAddressline3("addressline 3");
    addressEntity.setCity("city");
    addressEntity.setStatecodetype("CA");
    addressEntity.setCountrycodetype("United States of America");
    addressEntity.setPostalcode("94401");
    addressEntity.setSource("Editorial Manager");

    try {
      crudService.create(addressEntity);
      fail();
    } catch (NedValidationException e) {
      // expected since the countrycodeid was not resolved
    }

    // try again but this time use type resolver. remember that type names are
    // resolved by joins when querying database -- need foreign key to get name.
    
    Integer createAddressId2 = crudService.create( namedEntityService.resolveValuesToIds(addressEntity) );
    assertNotNull( createAddressId2 );

    Address savedEntity2 = namedEntityService.findResolvedEntityByKey(createAddressId2, Address.class);
    assertNotNull( savedEntity2.getType() );

    // Test unsuccessful UPDATE of an address entity. The savedEntity2 pojo has
    // just type names without type ids (ie, types haven't been resolved to
    // id's). Trying to update this entity should fail.

    savedEntity2.setPostalcode("66666");

    try {
      crudService.update(savedEntity2);
      fail();
    } catch (NedValidationException e) {
      // expected since entity pojo has no type ids (just type names) 
    }

    // try again with type resolver.

    assertTrue( crudService.update(namedEntityService.resolveValuesToIds(savedEntity2)) );

    Address savedEntity3 = namedEntityService.findResolvedEntityByKey(createAddressId2, Address.class);
    assertEquals("66666", savedEntity3.getPostalcode());

    // DELETE.

    addressEntity.setId(createAddressId2);

    assertTrue(crudService.delete(addressEntity));

    assertFalse(crudService.delete(addressEntity));
  }

  private IndividualComposite newCompositeIndividualWithRole() {

    IndividualComposite composite = new IndividualComposite();
    Individualprofile individualProfile = new Individualprofile();
    individualProfile.setFirstname("firstname");
    individualProfile.setLastname("lastname");
    individualProfile.setDisplayname("displayname"+ UUID.randomUUID().toString());
    individualProfile.setNameprefix("Mr.");
    individualProfile.setNamesuffix("III");
    individualProfile.setSource("Editorial Manager");

    List<Individualprofile> individualProfiles = new ArrayList<>();
    individualProfiles.add(individualProfile);

    composite.setIndividualprofiles(individualProfiles);

    List<Role> roles = new ArrayList<>();
    Role author = new Role();

    author.setType("Author");
    author.setStartdate(new java.sql.Date(1401408000));  // "2014-05-30"

    author.setLastmodified(new Timestamp(Calendar.getInstance().getTime().getTime()));
    author.setCreated(new Timestamp(Calendar.getInstance().getTime().getTime()));
    author.setSource("Editorial Manager");
    roles.add(author);

    composite.setRoles(roles);

    Uniqueidentifier uid = new Uniqueidentifier();
    uid.setSource("Ambra");
    uid.setType("CAS");
    uid.setUniqueidentifier("123");

    List<Uniqueidentifier> uniqueidentifiers = new ArrayList<>();
    uniqueidentifiers.add(uid);

    composite.setUniqueidentifiers(uniqueidentifiers);

    return composite;
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
