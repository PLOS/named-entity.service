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
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.AddressEntity;
import org.plos.namedentity.api.entity.DegreeEntity;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.OrganizationEntity;
import org.plos.namedentity.api.entity.PhonenumberEntity;
import org.plos.namedentity.api.entity.RoleEntity;
import org.plos.namedentity.api.entity.UniqueidentifierEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
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
  public void testCreateIndividualWithoutRole() {
    // triggers phase 1 validation failure
    try {
      namedEntityService.createIndividualComposite(new IndividualComposite());
      fail();
    }
    catch (NedValidationException expected) {
      assertTrue(expected.getMessage().indexOf("No ROLE defined") != -1);
    }
  }

  @Test
  public void testCreateOrganization() {
    OrganizationEntity inputEntity = new OrganizationEntity();
    inputEntity.setOrganizationfamiliarname("familiarname");
    inputEntity.setOrganizationlegalname("legalname");
    inputEntity.setIsactive((byte) 1);
    inputEntity.setIsvisible((byte) 0);

    try {
      OrganizationEntity outputEntity = namedEntityService.createOrganization(inputEntity);
      assertNotNull(outputEntity);
      assertNotNull(outputEntity.getNamedentityid());
      assertTrue(inputEntity.equals(outputEntity));
    }
    catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testCreateIndividualCompositeWithRole() {

    IndividualComposite composite = newCompositeIndividualWithRole();

    /* ------------------------------------------------------------------ */
    /*  EMAILS                                                            */
    /* ------------------------------------------------------------------ */

    List<EmailEntity> emails = new ArrayList<>();

    EmailEntity workEmail = new EmailEntity();
    workEmail.setEmailtype("Work");
    workEmail.setEmailaddress("fu.manchu.work@foo.com");
    workEmail.setIsprimary((byte)1);
    emails.add( workEmail );

    EmailEntity personalEmail = new EmailEntity();
    personalEmail.setEmailtype("Personal");
    personalEmail.setEmailaddress("fu.manchu.home@foo.com");
    personalEmail.setIsprimary((byte)0);
    emails.add( personalEmail );

    composite.setEmails( emails );

    /* ------------------------------------------------------------------ */
    /*  PHONE NUMBERS                                                     */
    /* ------------------------------------------------------------------ */

    List<PhonenumberEntity> phonenumbers = new ArrayList<>();

    PhonenumberEntity officePhone = new PhonenumberEntity();
    officePhone.setPhonenumbertype("Office");
    officePhone.setCountrycodetype("01");
    officePhone.setPhonenumber("123-456-7890");
    officePhone.setIsprimary(true);
    phonenumbers.add( officePhone );

    PhonenumberEntity mobilePhone = new PhonenumberEntity();
    mobilePhone.setPhonenumbertype("Mobile");
    mobilePhone.setCountrycodetype("01");
    mobilePhone.setPhonenumber("123-444-0011");
    mobilePhone.setIsprimary(false);
    phonenumbers.add( mobilePhone );

    PhonenumberEntity homePhone = new PhonenumberEntity();
    homePhone.setPhonenumbertype("Home");
    homePhone.setCountrycodetype("01");
    homePhone.setPhonenumber("123-555-6666");
    homePhone.setIsprimary(false);
    phonenumbers.add( homePhone );

    composite.setPhonenumbers( phonenumbers );

    /* ------------------------------------------------------------------ */
    /*  ADDRESSES                                                         */
    /* ------------------------------------------------------------------ */

    List<AddressEntity> addresses = new ArrayList<>();

    AddressEntity officeAddress = new AddressEntity();
    officeAddress.setAddresstype("Office");
    officeAddress.setAddressline1("addressline1");
    officeAddress.setAddressline2("addressline2");
    officeAddress.setCity("city");
    officeAddress.setStatecodetype("CA");
    officeAddress.setCountrycodetype("United States");
    officeAddress.setPostalcode("1234567");
    officeAddress.setIsprimary((byte)1);
    addresses.add( officeAddress );

    composite.setAddresses( addresses );

    /* ------------------------------------------------------------------ */
    /*  DEGREES                                                           */
    /* ------------------------------------------------------------------ */

    List<DegreeEntity> degrees = new ArrayList<>();

    DegreeEntity degree = new DegreeEntity();
    degree.setDegreetype("MD");
    degrees.add(degree);

    composite.setDegrees( degrees );

    /* ------------------------------------------------------------------ */
    /*  UNIQUE IDENTIFIERS                                                */
    /* ------------------------------------------------------------------ */

    List<UniqueidentifierEntity> uids = new ArrayList<>();

    UniqueidentifierEntity uidEntity = new UniqueidentifierEntity();
    uidEntity.setUniqueidentifiertype("ORCID");
    uidEntity.setUniqueidentifier("0000-0001-9430-319X");
    uids.add( uidEntity );

    composite.setUniqueidentifiers( uids );

    Integer nedId = null;
    try {
      IndividualComposite responseComposite = namedEntityService.createIndividualComposite(composite);
      assertNotNull(responseComposite);
      assertNotNull(responseComposite.getNamedentityid());

      // make sure foreign keys are resolved for sub entities
      assertNotNull(responseComposite.getEmails().get(0).getEmailid());

    }
    catch (NedValidationException e) {
      fail();
    }
    finally {
      EmailEntity emailSearchCriteria = new EmailEntity();
      emailSearchCriteria.setEmailaddress("fu.manchu.work@foo.com");
      List<EmailEntity> emailSearchResult = crudService.findByAttribute(emailSearchCriteria);
      assertEquals(1, emailSearchResult.size());

      nedId = emailSearchResult.get(0).getNamedentityid();
      assertNotNull( nedId );
    }

    // Test "By NedId" Finders

    IndividualEntity entity = namedEntityService.findResolvedEntity(nedId, IndividualEntity.class);
    assertNotNull( entity );

    List<AddressEntity> addressesEntities = namedEntityService.findResolvedEntities(nedId, AddressEntity.class);
    assertEquals(1, addressesEntities.size());

    List<EmailEntity> emailEntities = namedEntityService.findResolvedEntities(nedId, EmailEntity.class);
    assertEquals(2, emailEntities.size());

    List<PhonenumberEntity> phonenumberEntities = namedEntityService.findResolvedEntities(nedId, PhonenumberEntity.class);
    assertEquals(3, phonenumberEntities.size());

    List<RoleEntity> roleEntities = namedEntityService.findResolvedEntities(nedId, RoleEntity.class);
    assertEquals(1, roleEntities.size());

    List<UniqueidentifierEntity> uidEntities = namedEntityService.findResolvedEntities(nedId, UniqueidentifierEntity.class);
    assertEquals(1, uidEntities.size());

    List<IndividualEntity> individuals = namedEntityService.findResolvedEntityByUid("ORCID", "0000-0001-9430-319X", IndividualEntity.class);
    assertEquals(1, individuals.size());

    IndividualEntity individual = individuals.get(0);
    assertEquals("firstname", individual.getFirstname());
    assertEquals("lastname", individual.getLastname());
  }

  private Integer findUidTypeIdByName(String typeName) {

    GlobaltypeEntity globalTypesearchCriteria = new GlobaltypeEntity();
    globalTypesearchCriteria.setTypeid(17);
    globalTypesearchCriteria.setShortdescription(typeName);
    List<GlobaltypeEntity> globalTypesResult = crudService.findByAttribute(globalTypesearchCriteria);
    assertEquals(1, globalTypesResult.size());

    return globalTypesResult.get(0).getGlobaltypeid(); 
  }

  @Test
  public void testCreateIndividualWithPhaseTwoValidationException() {

    IndividualComposite composite = newCompositeIndividualWithRole();

    List<EmailEntity> emails = new ArrayList<>();

    EmailEntity workEmail = new EmailEntity();
    workEmail.setEmailtype("Work");
    workEmail.setEmailaddress("foo@bar.com");
    workEmail.setIsprimary((byte)1);
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
      EmailEntity emailSearchCriteria = new EmailEntity();
      emailSearchCriteria.setEmailaddress("foo@bar.com");
      List<EmailEntity> emailSearchResult = crudService.findByAttribute(emailSearchCriteria);
      assertEquals(0, emailSearchResult.size());
    }
  }

  @Test
  public void testEmailEntityCrud() {

    // CREATE email entity. we don't expect the email type to persist.

    EmailEntity emailEntity = new EmailEntity();
    emailEntity.setNamedentityid(1);
    emailEntity.setEmailtype("Work");
    emailEntity.setEmailaddress("foo@bar.com");
    emailEntity.setIsprimary((byte)1);

    Integer createEmailId = crudService.create(emailEntity);
    assertNotNull( createEmailId );

    EmailEntity savedEntity = namedEntityService.findResolvedEntityByKey(createEmailId, EmailEntity.class);
    assertNull( savedEntity.getEmailtype() );

    // try again but this time use type resolver. remember that type names are
    // resolved by joins when querying database -- need foreign key to get name.
    
    namedEntityService.resolveValuesToIds(emailEntity);

    Integer createEmailId2 = crudService.create(emailEntity);
    assertNotNull( createEmailId2 );

    EmailEntity savedEntity2 = namedEntityService.findResolvedEntityByKey(createEmailId2, EmailEntity.class);
    assertNotNull( savedEntity2.getEmailtype() );

    // UPDATE email entity. Scrub appropriate attributes from current instance
    // and reuse. Again, we don't expect for email type to persist.

    emailEntity.setEmailtypeid(null); emailEntity.setEmailid(createEmailId);
    assertTrue( crudService.update(emailEntity) );

    EmailEntity savedEntity3 = namedEntityService.findResolvedEntityByKey(createEmailId, EmailEntity.class);
    assertNull( savedEntity3.getEmailtype() );

    // try again with type resolver.

    namedEntityService.resolveValuesToIds(emailEntity);

    assertTrue( crudService.update(emailEntity) );

    EmailEntity savedEntity4 = namedEntityService.findResolvedEntityByKey(createEmailId, EmailEntity.class);
    assertNotNull( savedEntity4.getEmailtype() );

    // DELETE.

    assertTrue( crudService.delete(emailEntity) );

    emailEntity.setEmailid(createEmailId2);
    assertTrue( crudService.delete(emailEntity) );
  }

  @Test
  public void testAddressEntityCrud() {

    // CREATE address entity. we don't expect the address type to persist.

    AddressEntity addressEntity = new AddressEntity();
    addressEntity.setNamedentityid(1);
    addressEntity.setAddresstype("Office");
    addressEntity.setAddressline1("addressline 1");
    addressEntity.setAddressline2("addressline 2");
    addressEntity.setAddressline3("addressline 3");
    addressEntity.setCity("city");
    addressEntity.setStatecodetype("CA");
    addressEntity.setCountrycodetype("United States");
    addressEntity.setPostalcode("94401");

    Integer createAddressId = crudService.create(addressEntity);
    assertNotNull( createAddressId );

    AddressEntity savedEntity = namedEntityService.findResolvedEntityByKey(createAddressId, AddressEntity.class);
    assertNull( savedEntity.getAddresstype() );

    // try again but this time use type resolver. remember that type names are
    // resolved by joins when querying database -- need foreign key to get name.
    
    Integer createAddressId2 = crudService.create( namedEntityService.resolveValuesToIds(addressEntity) );
    assertNotNull( createAddressId2 );

    AddressEntity savedEntity2 = namedEntityService.findResolvedEntityByKey(createAddressId2, AddressEntity.class);
    assertNotNull( savedEntity2.getAddresstype() );

    // UPDATE address entity. Scrub appropriate attributes from current instance
    // and reuse. Again, we don't expect for address type to persist.

    addressEntity.setAddressid(createAddressId); 
    addressEntity.setAddresstypeid(null); 
    addressEntity.setStatecodetypeid(null); 
    addressEntity.setCountrycodetypeid(null); 
    assertTrue( crudService.update(addressEntity) );

    AddressEntity savedEntity3 = namedEntityService.findResolvedEntityByKey(createAddressId, AddressEntity.class);
    assertNull( savedEntity3.getAddresstype() );

    // try again with type resolver.

    assertTrue( crudService.update(namedEntityService.resolveValuesToIds(addressEntity)) );

    AddressEntity savedEntity4 = namedEntityService.findResolvedEntityByKey(createAddressId, AddressEntity.class);
    assertNotNull( savedEntity4.getAddresstype() );

    // DELETE.

    assertTrue( crudService.delete(addressEntity) );

    addressEntity.setAddressid(createAddressId2);
    assertTrue( crudService.delete(addressEntity) );
  }

  private IndividualComposite newCompositeIndividualWithRole() {

    IndividualComposite composite = new IndividualComposite();
    composite.setFirstname("firstname");
    composite.setMiddlename("middlename");
    composite.setLastname("lastname");
    composite.setNameprefix("Mr.");
    composite.setNamesuffix("III");
    composite.setPreferredlanguage("English");
    composite.setPreferredcommunication("Email");

    List<RoleEntity> roles = new ArrayList<>();
    RoleEntity author = new RoleEntity();
    author.setRoletype("Author");
    author.setStartdate(new Timestamp(1401408000));  // "2014-05-30"
    roles.add(author);

    composite.setRoles(roles);

    return composite;
  }

  //TODO - add tests with address, email, and phone combinations
}
