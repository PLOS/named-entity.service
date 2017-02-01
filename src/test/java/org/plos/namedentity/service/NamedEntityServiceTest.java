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
package org.plos.namedentity.service;

import org.eclipse.core.runtime.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.OrganizationComposite;
import org.plos.namedentity.api.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidTypeValue;

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
      namedEntityService.createComposite(new IndividualComposite(), IndividualComposite.class);
      fail();
    }
    catch (NedException expected) {
    }
  }

  @Test
  public void testIndividualCompositeEquality() {
    IndividualComposite composite1 = newCompositeIndividualWithGroup();

    IndividualComposite composite2 = newCompositeIndividualWithGroup();

    // patch randomized attributes in objects so that they'll pass equality check

    composite2.getIndividualprofiles().get(0).setDisplayname(
        composite1.getIndividualprofiles().get(0).getDisplayname());

    assertEquals(composite1, composite2);

    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setType("Work");
    workEmail.setEmailaddress("fu.manchu.work@foo.com");
    workEmail.setSource("Editorial Manager");
    emails.add(_(workEmail));

    Email personalEmail = new Email();
    personalEmail.setType("Personal");
    personalEmail.setEmailaddress("fu.manchu.home@foo.com");
    personalEmail.setSource("Editorial Manager");
    emails.add(_(personalEmail));

    composite1.setEmails( emails );
    assertNotEquals(composite1, composite2);

    composite2.setEmails( emails );
    assertEquals(composite1, composite2);

    // list order should not affect equality
    emails = new ArrayList<>();
    emails.add(_(personalEmail));
    emails.add(_(workEmail));

    composite2.setEmails( emails );

    assertEquals(composite1, composite2);
  }

  @Test
  public void testCreateIndividualWithOrgRelationship() {

    // org composite with type names (unresolved)
    OrganizationComposite orgComposite = newOrganizationComposite();

    // save and read org from database (resolved composite)
    OrganizationComposite savedOrgComposite = namedEntityService.createComposite(orgComposite, OrganizationComposite.class);

    // individual composite with type names (unresovled)
    IndividualComposite individualComposite = newIndividualComposite();

    // define relationship. explicitly set nedid of org. nedid of individual
    // will be set during composite creation.

    Relationship relationship = new Relationship();
    relationship.setType("Individual Affiliated with Organization");
    relationship.setNedidrelated(savedOrgComposite.getNedid());
    relationship.setSource("Ambra");

    List<Relationship> relationships = new ArrayList<>();
    relationships.add(_(relationship));
    individualComposite.setRelationships(relationships);

    IndividualComposite savedIndividualComposite = namedEntityService.createComposite(individualComposite, IndividualComposite.class);

    List<Relationship> savedIndividualRelationships = savedIndividualComposite.getRelationships();
    assertEquals(1, savedIndividualRelationships.size());
    assertNotNull( savedIndividualComposite.getEmails().get(0).getNedid() );
    assertEquals( savedOrgComposite.getNedid(), savedIndividualRelationships.get(0).getNedidrelated() );
    assertEquals("Individual Affiliated with Organization", savedIndividualRelationships.get(0).getType());
  }

  @Test
  public void testRelationshipEntityCrud() {

    /* ---------------------------------------------------------------------- */
    /*  CREATE Relationship #1                                                */
    /* ---------------------------------------------------------------------- */

    Relationship relationshipEntity = new Relationship();
    relationshipEntity.setNedid(1);        /* seeded individual   */
    relationshipEntity.setNedidrelated(2); /* seeded organization */
    relationshipEntity.setType("Individual Affiliated with Organization");
    relationshipEntity.setStartdate(LocalDate.now());
    relationshipEntity.setSource("Ambra");

    try {
      crudService.create(_(relationshipEntity));
      fail();
    }
    catch (NedException expected) {
      // typeid hasn't been resolved yet, so we expect a not-null
      // constraint to be thrown
    }

    // try again but this time use type resolver. remember that type names are
    // resolved by joins when querying database -- need foreign key to get name.

    Integer relationshipId1 = crudService.create( namedEntityService.resolveValuesToIds(_(relationshipEntity)) );
    assertNotNull( relationshipId1 );

    Relationship savedEntity1 = namedEntityService.findResolvedEntityByKey(relationshipId1, Relationship.class);
    assertEquals("Individual Affiliated with Organization", savedEntity1.getType());

    // UPDATE relationship entity.

    LocalDate enddate = LocalDate.now();
    savedEntity1.setEnddate(enddate);
    assertTrue( crudService.update(namedEntityService.resolveValuesToIds(_(savedEntity1))) );

    savedEntity1 = namedEntityService.findResolvedEntityByKey(relationshipId1, Relationship.class);
    assertEquals(enddate, savedEntity1.getEnddate());

    /* ---------------------------------------------------------------------- */
    /*  CREATE Relationship #2                                                */
    /* ---------------------------------------------------------------------- */

    relationshipEntity = new Relationship();
    relationshipEntity.setNedid(1);        /* seeded individual   */
    relationshipEntity.setNedidrelated(2); /* seeded organization */
    relationshipEntity.setType("Individual Affiliated with Organization");
    relationshipEntity.setStartdate(LocalDate.now());
    relationshipEntity.setSource("Ambra");

    Integer relationshipId2 = crudService.create( namedEntityService.resolveValuesToIds(_(relationshipEntity)) );
    assertNotNull( relationshipId2 );

    // Lookup by nedid should return all relationships entity participates in.

    List<Relationship> allRelationshipsForEntity = namedEntityService.findResolvedEntities(1, Relationship.class);
    assertEquals(2, allRelationshipsForEntity.size());

    // DELETE

    for (Relationship r : allRelationshipsForEntity) {
      assertTrue( crudService.delete(r) );
    }
  }

  @Test
  public void testCreateOrganizationComposite() {

    OrganizationComposite composite = newOrganizationComposite();

    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setType("Work");
    workEmail.setEmailaddress("fu.manchu.work@foo.com");
    workEmail.setSource("Editorial Manager");
    emails.add(_(workEmail));

    composite.setEmails( emails );

    List<Uniqueidentifier> uniqueidentifiers = new ArrayList<>();

    Uniqueidentifier uidEntity = new Uniqueidentifier();
    uidEntity.setType("Ringgold");
    uidEntity.setUniqueidentifier("1234");
    uidEntity.setSource("Ambra");

    uniqueidentifiers.add(_(uidEntity));

    composite.setUniqueidentifiers(uniqueidentifiers);


    String legalName = composite.getLegalname();
    composite.setLegalname("");

    try {
      namedEntityService.createComposite(composite, OrganizationComposite.class);
      fail("invalid legal name was not rejected");
    } catch (NedException e) {
      // expected
    }

    composite.setLegalname(legalName);

    OrganizationComposite responseComposite = namedEntityService.createComposite(composite, OrganizationComposite.class);

    assertNotNull(responseComposite);
    assertNotNull(responseComposite.getNedid());

    OrganizationComposite foundComposite = namedEntityService.findComposite(responseComposite.getNedid(), OrganizationComposite.class);

    assert(foundComposite.getLegalname().equals(responseComposite.getLegalname()));

  }

  @Test
  public void testCreateIndividualCompositeWithGroup() {

    IndividualComposite composite = newCompositeIndividualWithGroup();

    /* ------------------------------------------------------------------ */
    /*  EMAILS                                                            */
    /* ------------------------------------------------------------------ */

    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setType("Work");
    workEmail.setEmailaddress("fu.manchu.work@foo.com");
    workEmail.setSource("Ambra");
//    workEmail.setNedid(12345);
    emails.add(_(workEmail));

    Email personalEmail = new Email();
    personalEmail.setType("Personal");
    personalEmail.setEmailaddress("fu.manchu.home@foo.com");
    personalEmail.setSource("Editorial Manager");
    emails.add(_(personalEmail));

    composite.setEmails( emails );

    /* ------------------------------------------------------------------ */
    /*  AUTH                                                              */
    /* ------------------------------------------------------------------ */

    List<Auth> auths = new ArrayList<>();

    Auth auth = new Auth();
    auth.setEmail(workEmail.getEmailaddress());
    auth.setPlainTextPassword("password123");
    auths.add(_(auth));

    composite.setAuth( auths );

    /* ------------------------------------------------------------------ */
    /*  PHONE NUMBERS                                                     */
    /* ------------------------------------------------------------------ */

    List<Phonenumber> phonenumbers = new ArrayList<>();

    Phonenumber officePhone = new Phonenumber();
    officePhone.setType("Office");
    officePhone.setCountrycodetype("1");
    officePhone.setPhonenumber("123-456-7890");
    officePhone.setSource("Editorial Manager");
    phonenumbers.add(_(officePhone));

    Phonenumber mobilePhone = new Phonenumber();
    mobilePhone.setType("Mobile");
    mobilePhone.setCountrycodetype("1");
    mobilePhone.setPhonenumber("123-444-0011");
    mobilePhone.setSource("Editorial Manager");
    phonenumbers.add(_(mobilePhone));

    Phonenumber homePhone = new Phonenumber();
    homePhone.setType("Home");
    homePhone.setCountrycodetype("1");
    homePhone.setPhonenumber("123-555-6666");
    homePhone.setSource("Editorial Manager");
    phonenumbers.add(_(homePhone));

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
    addresses.add(_(officeAddress));

    composite.setAddresses( addresses );

    /* ------------------------------------------------------------------ */
    /*  DEGREES                                                           */
    /* ------------------------------------------------------------------ */

    List<Degree> degrees = new ArrayList<>();

    Degree degree = new Degree();
    degree.setType("Masters");
    degree.setSource("Editorial Manager");
    degrees.add(_(degree));

    composite.setDegrees( degrees );

    /* ------------------------------------------------------------------ */
    /*  UNIQUE IDENTIFIERS                                                */
    /* ------------------------------------------------------------------ */

    List<Uniqueidentifier> uids = new ArrayList<>();

    Uniqueidentifier uid = new Uniqueidentifier();
    uid.setType("ORCID");
    uid.setUniqueidentifier("0000-0001-9430-001X");
    uid.setSource("Editorial Manager");
    uids.add(_(uid));

    composite.setUniqueidentifiers( uids );

    /* ------------------------------------------------------------------ */
    /*  URLS                                                              */
    /* ------------------------------------------------------------------ */

    List<Url> urls = new ArrayList<>();
    Url url = new Url();
    url.setUrl("http://goodurl.org");
    url.setUrl("httpXX://badurl.org");
    url.setSource("Editorial Manager");
    urls.add(_(url));

    composite.setUrls(urls);

    Integer nedId = null;

    try {
      namedEntityService.createComposite(composite, IndividualComposite.class);
      fail("invalid URL was not rejected");
    } catch (NedException expected) {
      // expected since url is invalid
    }

    urls = new ArrayList<>();
    url.setUrl("http://newgoodurl.org");
    url.setSource("Editorial Manager");
    urls.add(_(url));

    composite.setUrls(urls);

    try {
      IndividualComposite responseComposite = namedEntityService.createComposite(composite, IndividualComposite.class);
      assertNotNull(responseComposite);

      // the NED ID can be set in the first email of the composite
//      assertEquals(responseComposite.getIndividualprofiles().get(0).getNedid().longValue(), 12345);

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

    List<Group> groupEntities = namedEntityService.findResolvedEntities(nedId, Group.class);
    assertEquals(1, groupEntities.size());

    List<Uniqueidentifier> uidEntities = namedEntityService.findResolvedEntities(nedId, Uniqueidentifier.class);
    assertEquals(1, uidEntities.size());

    Individualprofile individualProfile = namedEntityService.findResolvedEntityByUid("ORCID", "0000-0001-9430-001X", Individualprofile.class);

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
  public void testDeleteIndividual() {

    IndividualComposite composite = newCompositeIndividualWithGroup();

    List<Email> emails = new ArrayList<>();
    Email workEmail = new Email();
    workEmail.setType("Work");
    workEmail.setEmailaddress("fu.manchu.work555@foo.com");
    workEmail.setSource("Ambra");
//    workEmail.setNedid(555);  // not setting ID, so ambra gets the insert
    emails.add(_(workEmail));
    composite.setEmails( emails );

    List<Auth> auths = new ArrayList<>();
    Auth auth = new Auth();
    auth.setEmail(workEmail.getEmailaddress());
    auth.setPlainTextPassword("password123");
    auths.add(_(auth));
    composite.setAuth( auths );

    IndividualComposite compositeOut = namedEntityService.createComposite(composite, IndividualComposite.class);

    Integer nedId = compositeOut.getEmails().get(0).getNedid();

    namedEntityService.deleteIndividual(nedId);
  }

  @Test
  public void testCreateIndividualCompositeWithInvalidEmail() {

    IndividualComposite composite = newCompositeIndividualWithGroup();

    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setType("Work");
    workEmail.setEmailaddress("invalid@email");
    workEmail.setSource("Editorial Manager");
    emails.add(_(workEmail));

    composite.setEmails( emails );

    try {
      namedEntityService.createComposite(composite, IndividualComposite.class);
      fail();
    }
    catch (NedException expected) {
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

    IndividualComposite composite = newCompositeIndividualWithGroup();

    try {
      namedEntityService.createComposite(composite, IndividualComposite.class);
      fail();
    } catch (NedException expected) {
      Assert.isTrue(expected.getMessage().contains("Email entities can not be empty"));
    }

    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setType("Work");
    workEmail.setEmailaddress("valid-"+UUID.randomUUID().toString()+"@email.com");
    workEmail.setSource("Ambra");
    emails.add(_(workEmail));

    composite.setEmails( emails );
    composite.setIndividualprofiles(null);

    try {
      namedEntityService.createComposite(composite, IndividualComposite.class);
      fail();
    } catch (NedException expected) {
      Assert.isTrue(expected.getMessage().contains("Profile entities can not be empty"));
    }

    composite = newCompositeIndividualWithGroup();
    composite.setEmails( emails );

    try {
      namedEntityService.createComposite(composite, IndividualComposite.class);
      fail();
    } catch (NedException expected) {
      Assert.isTrue(expected.getMessage().contains("User credentials can not be empty"));
    }

    composite = newCompositeIndividualWithGroup();
    composite.setEmails( emails );

    List<Auth> auths = new ArrayList<>();
    Auth auth = new Auth();
    auth.setEmail(workEmail.getEmailaddress());
    auth.setPlainTextPassword("password123");
    auths.add(_(auth));

    composite.setAuth( auths );

    namedEntityService.createComposite(composite, IndividualComposite.class);
  }

  @Test
  public void testProfileDisplaynameGenerationWithUuid() throws Exception {

    String[] displaynames = {
      "flastname",    // basename
      "flastname100"  // a displayname that will collide with generated names
    };

    Individualprofile profile = new Individualprofile();

    for (String displayname : displaynames) {
      profile.setFirstname("firstname");
      profile.setLastname("lastname");
      profile.setDisplayname(displayname);
      profile.setNameprefix("Mr.");
      profile.setNamesuffix("III");
      profile.setSource("Editorial Manager");
      profile.setNedid(1);

      Integer profileId = crudService.create( namedEntityService.resolveValuesToIds(_(profile)) );
      assertNotNull( profileId );

      Individualprofile savedEntity = namedEntityService.findResolvedEntityByKey(profileId, Individualprofile.class);
      assertEquals(displayname, savedEntity.getDisplayname());
    }

    // mock Random so that it will return the same value every time. this will
    // ensure that the same displayname gets generated everytime it's called
    // which will collide with the displayname inserted above. this will exercise 
    // the entire range check (ie, 100 random numbers in the range 100-999) and 
    // fall back to generating a name with initials plus uuid.

    Random mockRandom = Mockito.mock(Random.class);
    when( mockRandom.nextInt(anyInt()) ).thenReturn(0);

    profile.setDisplayname( namedEntityService.generateDisplayname(profile,mockRandom) );
    // first check was against basename without random number.
    verify(mockRandom, times(99)).nextInt(anyInt());

    Integer profileId = crudService.create( namedEntityService.resolveValuesToIds(_(profile)) );
    assertNotNull( profileId );

    Individualprofile savedEntity = namedEntityService.findResolvedEntityByKey(profileId, Individualprofile.class);

    // retrieve displayname which should be initials plus uuid (final effort)
    // ex: "fl-0d6576197f1d4f8b9b612456a151906a"

    String displayname = savedEntity.getDisplayname();
    assertTrue( displayname.startsWith("flastname-") );
    assertEquals((10+32), displayname.length());
  }

  @Test
  public void testProfileDisplaynameGeneration() throws Exception {

    Individualprofile profile = new Individualprofile();
    assertTrue( namedEntityService.generateDisplayname(profile, new Random()).matches("plosuser") );

    profile.setFirstname("firstname");
    assertTrue( namedEntityService.generateDisplayname(profile, new Random()).matches("plosuser") );

    // define profile entity. displayname will be generated during creation.

    for (int i = 0; i < 2; i++) {

      profile = new Individualprofile();
      profile.setFirstname("Firstname");
      profile.setLastname("Abcdefghijklmnop   &  qrstuvwxyz0123456789");
      profile.setNameprefix("Mr.");
      profile.setNamesuffix("III");
      profile.setSource("Editorial Manager");
      profile.setNedid(1);

      assertNull( profile.getDisplayname() );
      profile.setDisplayname( namedEntityService.generateDisplayname(profile, new Random()) );
      assertNotNull( profile.getDisplayname() );

      Integer profileId = crudService.create( namedEntityService.resolveValuesToIds(_(profile)) );
      assertNotNull( profileId );

      Individualprofile savedEntity = namedEntityService.findResolvedEntityByKey(profileId, Individualprofile.class);
      String displayname = savedEntity.getDisplayname();
      assertNotNull( displayname );

      if (i == 0) {   // displayname should be basename w/o random number
        assertTrue( displayname.equals("fabcdefghijklmnopqrstuvwxyz0") );
      } else {
        assertTrue( displayname.matches("fabcdefghijklmnopqrstuvwxyz0[1-9][0-9][0-9]") );
      }
    }
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
      crudService.create(namedEntityService.resolveValuesToIds(_(individualProfile)));
      fail();
    }
    catch (NedException expected) {
      System.out.println(expected.getMessage());
      // first name too short
    }

    individualProfile.setFirstname("firstname");

    Integer profileId = crudService.create( namedEntityService.resolveValuesToIds(_(individualProfile)) );
    assertNotNull( profileId );

    Individualprofile savedEntity = namedEntityService.findResolvedEntityByKey(profileId, Individualprofile.class);
    assertNotNull( savedEntity.getFirstname() );

    // UPDATE

    individualProfile.setId(profileId);
    individualProfile.setFirstname("firstname2");

    assertTrue(crudService.update(namedEntityService.resolveValuesToIds(_(individualProfile,false))));

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

    Integer createEmailId = crudService.create(namedEntityService.resolveValuesToIds(_(emailEntity)));
    assertNotNull( createEmailId );

    Email savedEntity = namedEntityService.findResolvedEntityByKey(createEmailId, Email.class);
    assertNotNull( savedEntity.getType() );

    // try again but this time use type resolver. remember that type names are
    // resolved by joins when querying database -- need foreign key to get name.
    
    namedEntityService.resolveValuesToIds(emailEntity);

    emailEntity.setEmailaddress("bill_2@microsoft.com");

    Integer createEmailId2 = crudService.create(_(emailEntity));
    assertNotNull( createEmailId2 );

    Email savedEntity2 = namedEntityService.findResolvedEntityByKey(createEmailId2, Email.class);
    assertNotNull( savedEntity2.getType() );

    // UPDATE email entity. Scrub appropriate attributes from current instance
    // and reuse. Again, we don't expect for email type to persist.

    emailEntity.setTypeid(null);
    emailEntity.setEmailaddress("bill_3@microsoft.com");
    emailEntity.setId(createEmailId);
    assertTrue( crudService.update(_(emailEntity,false)) );

    Email savedEntity3 = namedEntityService.findResolvedEntityByKey(createEmailId, Email.class);
    assertNull( savedEntity3.getType() );

    // try again with type resolver.

    namedEntityService.resolveValuesToIds(emailEntity);

    assertTrue( crudService.update(_(emailEntity,false)) );

    Email savedEntity4 = namedEntityService.findResolvedEntityByKey(createEmailId, Email.class);
    assertNotNull( savedEntity4.getType() );

    // DELETE.

    assertTrue( crudService.delete(emailEntity) );

    emailEntity.setId(createEmailId2);
    assertTrue( crudService.delete(emailEntity) );
  }

  @Test
  public void testGroupEntityCrud() {

    // CREATE group entity. we don't expect the type names to persist.

    Group groupEntity = new Group();
    groupEntity.setNedid(1);
    groupEntity.setApplicationtype("Named Party DB");
    groupEntity.setType("NED Admin");
    groupEntity.setStartdate(LocalDate.now());
    groupEntity.setLastmodified(new Timestamp(Calendar.getInstance().getTime().getTime()));
    groupEntity.setCreated(new Timestamp(Calendar.getInstance().getTime().getTime()));
    groupEntity.setSource("Ambra");

    try {
      crudService.create(_(groupEntity));
      fail();
    }
    catch (NedException expected) {
      // typeid hasn't been resolved yet, so we expect a not-null
      // constraint to be thrown
    }

    // try again but this time use type resolver. remember that type names are
    // resolved by joins when querying database -- need foreign key to get name.
    
    Integer createGroupId = crudService.create( namedEntityService.resolveValuesToIds(_(groupEntity)) );
    assertNotNull( createGroupId );

    Group savedEntity = namedEntityService.findResolvedEntityByKey(createGroupId, Group.class);
    assertNotNull( savedEntity.getType() );

    // UPDATE group entity. Scrub appropriate attributes from current instance
    // and reuse. 

    groupEntity.setId(createGroupId); 
    groupEntity.setApplicationtypeid(null);
    groupEntity.setTypeid(null); 
    assertTrue( crudService.update(namedEntityService.resolveValuesToIds(_(groupEntity,false))) );

    Group savedEntity2 = namedEntityService.findResolvedEntityByKey(createGroupId, Group.class);
    assertNotNull( savedEntity2.getType() );

    // DELETE.

    assertTrue( crudService.delete(groupEntity) );
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
      crudService.create(_(addressEntity));
      fail();
    } catch (NedException e) {
      // expected since the countrycodeid was not resolved
    }

    // try again but this time use type resolver. remember that type names are
    // resolved by joins when querying database -- need foreign key to get name.
    
    Integer createAddressId2 = crudService.create( namedEntityService.resolveValuesToIds(_(addressEntity)) );
    assertNotNull( createAddressId2 );

    Address savedEntity2 = namedEntityService.findResolvedEntityByKey(createAddressId2, Address.class);
    assertNotNull( savedEntity2.getType() );

    // Test unsuccessful UPDATE of an address entity. The savedEntity2 pojo has
    // just type names without type ids (ie, types haven't been resolved to
    // id's). Trying to update this entity should fail.

    savedEntity2.setPostalcode("66666");

    try {
      crudService.update(_(savedEntity2,false));
      fail();
    } catch (NedException e) {
      // expected since entity pojo has no type ids (just type names) 
    }

    // try again with type resolver.

    assertTrue( crudService.update(namedEntityService.resolveValuesToIds(_(savedEntity2,false))) );

    Address savedEntity3 = namedEntityService.findResolvedEntityByKey(createAddressId2, Address.class);
    assertEquals("66666", savedEntity3.getPostalcode());

    // DELETE.

    addressEntity.setId(createAddressId2);

    assertTrue(crudService.delete(addressEntity));

    assertFalse(crudService.delete(addressEntity));
  }

  private IndividualComposite newCompositeIndividualWithGroup() {

    IndividualComposite composite = new IndividualComposite();
    Individualprofile individualProfile = new Individualprofile();
    individualProfile.setFirstname("firstname");
    individualProfile.setLastname("lastname");
    individualProfile.setDisplayname("displayname"+ UUID.randomUUID().toString());
    individualProfile.setNameprefix("Mr.");
    individualProfile.setNamesuffix("III");
    individualProfile.setSource("Editorial Manager");

    List<Individualprofile> individualProfiles = new ArrayList<>();
    individualProfiles.add(_(individualProfile));

    composite.setIndividualprofiles(individualProfiles);

    List<Group> groups = new ArrayList<>();
    Group group = new Group();

    group.setType("NED Admin");
    group.setStartdate(LocalDate.of(2016, 5, 30));

    group.setLastmodified(new Timestamp(Calendar.getInstance().getTime().getTime()));
    group.setCreated(new Timestamp(Calendar.getInstance().getTime().getTime()));
    group.setSource("Ambra");
    groups.add(_(group));

    composite.setGroups(groups);

    return composite;
  }

  @Test
  public void testInvalidUidTypeForIndividual() {

    // Composite #1 (Valid)

    IndividualComposite composite1 = newIndividualComposite();
    IndividualComposite savedComposite1 = namedEntityService.createComposite(composite1, IndividualComposite.class);

    Uniqueidentifier uid1 = savedComposite1.getUniqueidentifiers().get(0);
    Integer uidId1 = uid1.getId();
    Integer nedId1 = uid1.getNedid();

    // Composite #2 (Invalid UID Type, Not Saved, Rolled Back)

    IndividualComposite composite2 = newIndividualComposite();

    List<Uniqueidentifier> uids = composite2.getUniqueidentifiers();

    Uniqueidentifier uid = new Uniqueidentifier();
    uid.setSource("Ambra");
    uid.setType("Ringgold");
    uid.setUniqueidentifier(UUID.randomUUID().toString());
    uids.add(_(uid));

    composite2.setUniqueidentifiers(uids);

    try {
      namedEntityService.createComposite(composite2, IndividualComposite.class);
    } catch (NedException expected) {
      assertEquals(InvalidTypeValue, expected.getErrorType());
    }

    // Composite #3 (Valid)

    IndividualComposite composite3 = newIndividualComposite();
    IndividualComposite savedComposite3 = namedEntityService.createComposite(composite3, IndividualComposite.class);

    Uniqueidentifier uid3 = savedComposite3.getUniqueidentifiers().get(0);
    Integer uidId3 = uid3.getId();
    Integer nedId3 = uid3.getNedid();

    // for composite #2, generated ids should have been burned up when the 
    // transaction was rolled back (ie, hole exists in sequences). verify this.

    Namedentityidentifier nei = crudService.findById(nedId1, Namedentityidentifier.class);
    assertEquals(nedId1, nei.getId());
  
    try {
      crudService.findById((nedId1+1), Namedentityidentifier.class);
    } catch (NedException expected) {
      assertEquals(EntityNotFound, expected.getErrorType());
    }

    nei = crudService.findById(nedId3, Namedentityidentifier.class);
    assertEquals(nedId3, nei.getId());

    assertTrue((nedId3 - nedId1) == 2);
    // uid insertion is non-deterministic ? difference could be 2 or 3.
    assertTrue((uidId3 - uidId1) >= 2);
  }

  @Test
  public void testInvalidUidTypeForOrganization() {

    // Composite #1 (Valid)

    OrganizationComposite composite1 = newOrganizationComposite();
    OrganizationComposite savedComposite1 = namedEntityService.createComposite(composite1, OrganizationComposite.class);

    Integer nedId1 = savedComposite1.getNedid();

    // Composite #2 (Invalid UID Type, Not Saved, Rolled Back)

    OrganizationComposite composite2 = newOrganizationComposite();

    Uniqueidentifier uid = new Uniqueidentifier();
    uid.setSource("Ambra");
    uid.setType("CAS");
    uid.setUniqueidentifier(UUID.randomUUID().toString());

    List<Uniqueidentifier> uids = new ArrayList<>() ; uids.add(_(uid));

    composite2.setUniqueidentifiers(uids);

    try {
      namedEntityService.createComposite(composite2, OrganizationComposite.class);
    } catch (NedException expected) {
      assertEquals(InvalidTypeValue, expected.getErrorType());
    }

    // Composite #3 (Valid)

    OrganizationComposite composite3 = newOrganizationComposite();
    OrganizationComposite savedComposite3 = namedEntityService.createComposite(composite3, OrganizationComposite.class);

    Integer nedId3 = savedComposite3.getNedid();

    // for composite #2, generated ids should have been burned up when the 
    // transaction was rolled back (ie, hole exists in sequences). verify this.

    Namedentityidentifier nei = crudService.findById(nedId1, Namedentityidentifier.class);
    assertEquals(nedId1, nei.getId());

    try {
      crudService.findById((nedId1+1), Namedentityidentifier.class);
    } catch (NedException expected) {
      assertEquals(EntityNotFound, expected.getErrorType());
    }

    nei = crudService.findById(nedId3, Namedentityidentifier.class);
    assertEquals(nedId3, nei.getId());

    assertTrue( (nedId3-nedId1) == 2 );
  }

  private IndividualComposite newIndividualComposite() {
    IndividualComposite composite = new IndividualComposite();

    /* ---------------------------------------------------------------------- */
    /*  PROFILES                                                              */
    /* ---------------------------------------------------------------------- */

    Individualprofile individualProfile = new Individualprofile();
    individualProfile.setFirstname("firstname");
    individualProfile.setLastname("lastname");
    individualProfile.setDisplayname("displayname"+ UUID.randomUUID().toString());
    individualProfile.setSource("Editorial Manager");

    List<Individualprofile> individualProfiles = new ArrayList<>();
    individualProfiles.add(_(individualProfile));

    composite.setIndividualprofiles(individualProfiles);

    /* ---------------------------------------------------------------------- */
    /*  EMAILS                                                                */
    /* ---------------------------------------------------------------------- */

    Email email = new Email();
    email.setType("Personal");
    email.setEmailaddress(UUID.randomUUID().toString()+"@foo.com");
    email.setSource("Ambra");

    List<Email> emails = new ArrayList<>();
    emails.add(_(email));

    composite.setEmails(emails);

    /* ---------------------------------------------------------------------- */
    /*  AUTH                                                                  */
    /* ---------------------------------------------------------------------- */

    Auth auth = new Auth();
    auth.setEmail(email.getEmailaddress());
    auth.setPlainTextPassword("password123");

    List<Auth> auths = new ArrayList<>();
    auths.add(_(auth));

    composite.setAuth(auths);

    /* ---------------------------------------------------------------------- */
    /*  UNIQUE IDENTIFIERS                                                    */
    /* ---------------------------------------------------------------------- */

    Uniqueidentifier uid = new Uniqueidentifier();
    uid.setSource("Ambra");
    uid.setType("Ambra");
    uid.setUniqueidentifier(String.valueOf(new Date().getTime()));

    List<Uniqueidentifier> uniqueidentifiers = new ArrayList<>();
    uniqueidentifiers.add(_(uid));

    composite.setUniqueidentifiers(uniqueidentifiers);

    // return minimal valid individual composite

    return composite;
  }

  private OrganizationComposite newOrganizationComposite() {
    OrganizationComposite composite = new OrganizationComposite();

    composite.setLegalname("legal name"+UUID.randomUUID().toString());
    composite.setFamiliarname("familiar name");
    composite.setSource("Ambra");
    composite.setCreatedby("test");
    composite.setLastmodifiedby("test");

    return composite;
  }

  // decorator to stamp created and last modified by fields
  private <S extends Entity> S _(S entity) {
    return _(entity,true);
  }
  private <S extends Entity>
  S _(S entity, boolean create) {
    entity.setCreatedbyname( create?"test":null );
    entity.setLastmodifiedbyname("test");
    return entity;
  }
}
