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
package org.plos.namedentity.rest;

import org.junit.Before;
import org.junit.Test;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedErrorResponse;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.OrganizationComposite;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Auth;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Globaltype;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.api.entity.Organization;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Typedescription;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.api.enums.UidTypeEnum;
import org.plos.namedentity.service.NamedEntityService;
import org.plos.namedentity.service.PasswordDigestService;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import static org.plos.namedentity.api.NedException.ErrorType.DupeEmailError;
import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidSearchQuery;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidSearchCriteria;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidTypeValue;

public class NamedEntityResourceTest extends BaseResourceTest {

  private static final String TEST_RESOURCE_PATH = "src/test/resources/";
  private static final String TYPE_CLASS_URI     = "/typeclasses";
  private static final String INDIVIDUAL_URI     = "/individuals";
  private static final String ORGANIZATION_URI   = "/organizations";

  private static IndividualComposite individualComposite = null;

  private static Integer nedIndividualId   = null;
  private static Integer nedOrganizationId = null;

  @Before
  public void setup() throws Exception {

    synchronized (NamedEntityResourceTest.class) {
      if (nedIndividualId == null) {
        String compositeIndividualJson = new String(Files.readAllBytes(
            Paths.get(TEST_RESOURCE_PATH + "composite-individual.json")));

        Response response = target(INDIVIDUAL_URI).request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.json(compositeIndividualJson));

        assertEquals(200, response.getStatus());

        String jsonPayload = response.readEntity(String.class);

        Unmarshaller unmarshaller = jsonUnmarshaller(IndividualComposite.class);
        IndividualComposite composite = unmarshalEntity(jsonPayload, IndividualComposite.class, unmarshaller);
        Individualprofile individualProfile = composite.getIndividualprofiles().get(0);
        assertNotNull(individualProfile.getNedid());

        nedIndividualId     = individualProfile.getNedid();
        individualComposite = composite;
      }

      if (nedOrganizationId == null) {
        String compositeJson = new String(Files.readAllBytes(
            Paths.get(TEST_RESOURCE_PATH + "composite-organization.json")));

        Response response = target(ORGANIZATION_URI).request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.json(compositeJson));

        assertEquals(200, response.getStatus());

        String jsonPayload = response.readEntity(String.class);

        Unmarshaller unmarshaller = jsonUnmarshaller(OrganizationComposite.class);
        OrganizationComposite composite = unmarshalEntity(jsonPayload, OrganizationComposite.class, unmarshaller);

        assertNotNull(composite.getNedid());

        nedOrganizationId = composite.getNedid();
      }
    }
  }

  @Test
  public void testFindIndividuals() throws Exception {

    /* ---------------------------------------------------------------------- */
    /*  BAD SEARCH QUERY URL                                                  */
    /* ---------------------------------------------------------------------- */

    WebTarget[] badFindIndividualUrls = {
      target(INDIVIDUAL_URI),
      target(INDIVIDUAL_URI).queryParam("entitykey","entityvalue"),
      target(INDIVIDUAL_URI).queryParam("attributekey","attributevalue").queryParam("valuekey","value")
    };

    for (WebTarget target : badFindIndividualUrls) {
      Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();

      assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

      String jsonPayload = response.readEntity(String.class);

      NedErrorResponse ner = unmarshalEntity(jsonPayload, NedErrorResponse.class, 
                                            jsonUnmarshaller(NedErrorResponse.class));

      assertEquals(InvalidSearchQuery.getErrorCode(), ner.errorCode);
      assertEquals(InvalidSearchQuery.getErrorMessage(), ner.errorMsg);
    }

    /* ---------------------------------------------------------------------- */
    /*  FIND BY EMAILADDRESS                                                  */
    /* ---------------------------------------------------------------------- */

    Response response = target(INDIVIDUAL_URI).queryParam("entity","email")
                                              .queryParam("attribute","emailaddress")
                                              .queryParam("value","jane.q.doe.work@foo.com")
                                              .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(IndividualComposite.class);
    List<IndividualComposite> composites = unmarshalEntities(jsonPayload, IndividualComposite.class, unmarshaller);
    assertEquals(1, composites.size());

    for (IndividualComposite composite : composites) {
      boolean foundEmailMatch = false;
      for (Email email : composite.getEmails()) {
        if ("jane.q.doe.work@foo.com".equals(email.getEmailaddress())) {
          foundEmailMatch = true;
        }
      }
      assertTrue(foundEmailMatch);
    }

    /* ---------------------------------------------------------------------- */
    /*  FIND BY EMAILADDRESS (MULTIPLE RECORDS - SAME EMAIL, DIFF SOURCES)    */
    /* ---------------------------------------------------------------------- */

    String compositeJsonTemplate = new String(Files.readAllBytes(
        Paths.get(TEST_RESOURCE_PATH + "composite-individual.template.json")));

    response = target(INDIVIDUAL_URI).request(MediaType.APPLICATION_JSON_TYPE)
      .post(Entity.json(String.format(compositeJsonTemplate, 
        UUID.randomUUID(), "jane.q.doe.work@foo.com", "Editorial Manager", UUID.randomUUID())));

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    response = target(INDIVIDUAL_URI).queryParam("entity","email")
                                     .queryParam("attribute","emailaddress")
                                     .queryParam("value","jane.q.doe.work@foo.com")
                                     .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    jsonPayload = response.readEntity(String.class);

    composites = unmarshalEntities(jsonPayload, IndividualComposite.class, unmarshaller);
    assertEquals(2, composites.size());

    for (IndividualComposite composite : composites) {
      boolean foundEmailMatch = false;
      for (Email email : composite.getEmails()) {
        if ("jane.q.doe.work@foo.com".equals(email.getEmailaddress())) {
          foundEmailMatch = true;
        }
      }
      assertTrue(foundEmailMatch);
    }

    /* ---------------------------------------------------------------------- */
    /*  FIND BY DISPLAYNAME                                                   */
    /* ---------------------------------------------------------------------- */

    response = target(INDIVIDUAL_URI).queryParam("entity","individualprofile")
                                     .queryParam("attribute","displayname")
                                     .queryParam("value","jdoe")
                                     .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    jsonPayload = response.readEntity(String.class);

    composites = unmarshalEntities(jsonPayload, IndividualComposite.class, unmarshaller);

    for (IndividualComposite composite : composites) {
      boolean foundDisplaynameMatch = false;
      for (Individualprofile profile : composite.getIndividualprofiles()) {
        if ("jdoe".equals(profile.getDisplayname())) {
          foundDisplaynameMatch = true;
        }
      }
      assertTrue(foundDisplaynameMatch);
    }

    /* ---------------------------------------------------------------------- */
    /*  FIND BY DISPLAYNAME (UNICODE)                                         */
    /* ---------------------------------------------------------------------- */
    /*
     *    Position   CodePoint     Description             Bytes (13 bytes)
     *    --------   ---------     ----------------------  -----------------
     *    1          U+2EC4        Cjk Radical West Two    3 bytes: e2 bb 84
     *    2          U+2DBA        Ethiopic Syllable Cchi  3 bytes: e2 b6 ba
     *    3          U+2375        Omega                   3 bytes: e2 8d b5
     *    4          U+0034        Digit Four (4)          1 byte : 34
     *    5          U+004D        Captial M               1 byte : 4d
     *    6          U+00C2        Latin A w/ Cirumflex    2 bytes: c3 82
     */
    String unicodeDisplayname = "⻄ⶺ⍵4MÂ";

    response = target(INDIVIDUAL_URI).request(MediaType.APPLICATION_JSON_TYPE)
      .post(Entity.json(String.format(compositeJsonTemplate, 
        unicodeDisplayname, UUID.randomUUID()+"@foo.com", "Ambra", UUID.randomUUID())));

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    response = target(INDIVIDUAL_URI).queryParam("entity","individualprofile")
                                     .queryParam("attribute","displayname")
                                     .queryParam("value", unicodeDisplayname)
                                     .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    jsonPayload = response.readEntity(String.class);

    composites = unmarshalEntities(jsonPayload, IndividualComposite.class, unmarshaller);

    for (IndividualComposite composite : composites) {
      boolean foundDisplaynameMatch = false;
      for (Individualprofile profile : composite.getIndividualprofiles()) {
        if (unicodeDisplayname.equals(profile.getDisplayname())) {
          foundDisplaynameMatch = true;
        }
      }
      assertTrue(foundDisplaynameMatch);
    }

    /* ---------------------------------------------------------------------- */
    /*  FIND BY DISPLAYNAME (400 - ENTITY NOT FOUND)                          */
    /* ---------------------------------------------------------------------- */

    response = target(INDIVIDUAL_URI).queryParam("entity","individualprofile")
                                     .queryParam("attribute","displayname")
                                     .queryParam("value","bogusdisplayname")
                                     .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

    jsonPayload = response.readEntity(String.class);

    NedErrorResponse ner = unmarshalEntity(jsonPayload, NedErrorResponse.class, 
                                           jsonUnmarshaller(NedErrorResponse.class));

    assertEquals(EntityNotFound.getErrorCode(), ner.errorCode);
  }

  @Test
  public void testCompositeFinders() throws Exception {

    Unmarshaller unmarshaller = jsonUnmarshaller(IndividualComposite.class);
    IndividualComposite composite_io = unmarshalEntity(
        new String(Files.readAllBytes(
        Paths.get(TEST_RESOURCE_PATH + "composite-individual.json"))),
        IndividualComposite.class, unmarshaller);

    unmarshaller = jsonUnmarshaller(OrganizationComposite.class);
    OrganizationComposite composite_oo = unmarshalEntity(
        new String(Files.readAllBytes(
            Paths.get(TEST_RESOURCE_PATH + "composite-organization.json"))),
        OrganizationComposite.class, unmarshaller);


    /* ------------------------------------------------------------------ */
    /*  FIND INDIVIDUAL BY NED ID                                         */
    /* ------------------------------------------------------------------ */

    Response response = target(INDIVIDUAL_URI + "/" + nedIndividualId)
        .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    unmarshaller = jsonUnmarshaller(IndividualComposite.class);
    IndividualComposite composite_in = unmarshalEntity(jsonPayload, IndividualComposite.class, unmarshaller);

    Individualprofile individualProfile = composite_in.getIndividualprofiles().get(0);
    assertNotNull(individualProfile.getNedid());

    assertEquals(composite_in, composite_io);

    /* ------------------------------------------------------------------ */
    /*  FIND INDIVIDUAL BY GUID                                           */
    /* ------------------------------------------------------------------ */

    response = target(INDIVIDUAL_URI + "/Editorial Manager/PONE-579386")
        .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    jsonPayload = response.readEntity(String.class);

    IndividualComposite composite_ig = unmarshalEntity(jsonPayload, IndividualComposite.class, unmarshaller);

    assertEquals(composite_ig, composite_in);

    assertEquals(composite_ig, composite_io);

    /* ------------------------------------------------------------------ */
    /*  FIND ORGANIZATION BY NED ID                                       */
    /* ------------------------------------------------------------------ */

    response = target(ORGANIZATION_URI + "/" + nedOrganizationId)
        .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    jsonPayload = response.readEntity(String.class);

    unmarshaller = jsonUnmarshaller(OrganizationComposite.class);
    OrganizationComposite composite_on = unmarshalEntity(jsonPayload, OrganizationComposite.class, unmarshaller);

    assertNotNull(composite_on.getNedid());

    assertEquals(composite_on, composite_oo);

    /* ------------------------------------------------------------------ */
    /*  FIND ORGANIZATION BY GUID                                         */
    /* ------------------------------------------------------------------ */

    response = target(ORGANIZATION_URI + "/Ringgold/123")
        .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    jsonPayload = response.readEntity(String.class);

    OrganizationComposite composite_og = unmarshalEntity(jsonPayload, OrganizationComposite.class, unmarshaller);

    assertEquals(composite_og, composite_on);

    assertEquals(composite_og, composite_oo);
  }

  @Test
  public void testIndividualProfileCRUD() throws Exception {

    String profilesURI = String.format("%s/%d/individualprofiles", INDIVIDUAL_URI, nedIndividualId);

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    String requestJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "individualprofile.json")));

    Response response = target(profilesURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(requestJson));

    assertEquals(200, response.getStatus());

    String responseJson = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Individualprofile.class);
    Individualprofile profile = unmarshalEntity(responseJson, Individualprofile.class, unmarshaller);

    String profileURI = profilesURI + "/" + profile.getId();

    /* ------------------------------------------------------------------ */
    /*  FIND (BY PROFILE ID (PK))                                         */
    /* ------------------------------------------------------------------ */

    response = target(profileURI).request(MediaType.APPLICATION_JSON_TYPE).get();
    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    Individualprofile profile2 = unmarshalEntity(responseJson, Individualprofile.class, unmarshaller);
    assertEquals(profile, profile2);

    /* ------------------------------------------------------------------ */
    /*  FIND (BY NED ID)                                                  */
    /* ------------------------------------------------------------------ */

    response = target(profilesURI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    List<Individualprofile> profiles = unmarshalEntities(responseJson, Individualprofile.class, unmarshaller);
    assertEquals(2, profiles.size());

    Individualprofile profile0 = profiles.get(0);
    assertEquals("Mrs.", profile0.getNameprefix());
    assertEquals("Jane", profile0.getFirstname());
    assertEquals("Q", profile0.getMiddlename());
    assertEquals("Doe", profile0.getLastname());
    assertEquals("III", profile0.getNamesuffix());
    assertEquals("Ambra", profile0.getSource());

    Individualprofile profile1 = profiles.get(1);
    assertEquals("Jane", profile1.getFirstname());
    assertEquals("Shmoe", profile1.getLastname());
    assertEquals("Janie", profile1.getNickname());
    assertEquals("10bogus郑超Gebækaaaمن", profile1.getDisplayname());
    assertEquals("Ambra", profile1.getSource());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    profile.setLastname("Flanders");

    // marshall pojo to json
    String updateIndividualProfileJson = writeValueAsString(profile);

    response = target(profileURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.json(updateIndividualProfileJson));

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Individualprofile updatedIndividualprofile = unmarshalEntity(jsonPayload, 
        Individualprofile.class, jsonUnmarshaller(Individualprofile.class));

    assertEquals("Flanders", updatedIndividualprofile.getLastname());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    assertEquals(Response.Status.NO_CONTENT.getStatusCode(),
      target(profileURI).request().delete().getStatus());
  }

  @Test
  public void testAddressCrud() throws IOException, JAXBException {

    String addressesURI = String.format("%s/%d/addresses", INDIVIDUAL_URI, nedIndividualId);

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    String requestJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "address.json")));

    Response response = target(addressesURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(requestJson));

    assertEquals(200, response.getStatus());

    String responseJson = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Address.class);
    Address address = unmarshalEntity(responseJson, Address.class, unmarshaller);
    assertNotNull(address);

    String addressURI = addressesURI + "/" + address.getId();

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ADDRESS ID (PK))                                         */
    /* ------------------------------------------------------------------ */

    response = target(addressURI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    Address foundAddress = unmarshalEntity(responseJson, Address.class, unmarshaller);
    assertEquals(address, foundAddress);

    /* ------------------------------------------------------------------ */
    /*  FIND (BY NED ID)                                                  */
    /* ------------------------------------------------------------------ */

    response = target(addressesURI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    List<Address> addresses = unmarshalEntities(responseJson, Address.class, unmarshaller);
    assertEquals(2, addresses.size());

    Address address0 = addresses.get(0);
    assertEquals("Office", address0.getType());
    assertEquals("123 Maple Street", address0.getAddressline1());
    assertEquals("CA", address0.getStatecodetype());
    assertEquals("United States of America", address0.getCountrycodetype());
    assertEquals("94501", address0.getPostalcode());

    Address address1 = addresses.get(1);
    assertEquals("Home", address1.getType());
    assertEquals("addressline 1", address1.getAddressline1());
    assertEquals("ONT", address1.getStatecodetype());
    assertEquals("Canada", address1.getCountrycodetype());
    assertEquals("M4C 1B5", address1.getPostalcode());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(addressURI).request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.json(writeValueAsString(foundAddress)));

    assertEquals(200, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(addressURI).request(MediaType.APPLICATION_JSON_TYPE).delete();
    assertEquals(204, response.getStatus());
  }

  @Test
  public void testRoleCrud() throws IOException, JAXBException {

    String rolesURI = String.format("%s/%d/roles", INDIVIDUAL_URI, nedIndividualId);

    Calendar cal = new GregorianCalendar();   // Jun 30, 2014 00:00:00 local time
    cal.set(2014, (6 - 1), 30, 0, 0, 0);      // month is 0-based, so subtract 1
    cal.set(Calendar.MILLISECOND, 0);
    Date START_DATE = cal.getTime();

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    String requestJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "role.json")));

    Response response = target(rolesURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(requestJson));

    assertEquals(200, response.getStatus());

    String responseJson = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Role.class);
    Role role = unmarshalEntity(responseJson, Role.class, unmarshaller);

    assertTrue( role.getId() > 0 );
    assertEquals(nedIndividualId, role.getNedid());
    assertEquals("Editorial Manager", role.getApplicationtype());
    assertEquals("Academic Editor (PLOS ONE)", role.getType());

    assertEquals(START_DATE, role.getStartdate());

    String roleURI = rolesURI + "/" + role.getId();

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ROLE ID (PK))                                            */
    /* ------------------------------------------------------------------ */

    response = target(roleURI).request(MediaType.APPLICATION_JSON_TYPE).get();
    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    Role foundRole = unmarshalEntity(responseJson, Role.class, unmarshaller);
    assertEquals(role, foundRole);

    /* ------------------------------------------------------------------ */
    /*  FIND (BY NED ID)                                                  */
    /* ------------------------------------------------------------------ */

    response = target(rolesURI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    List<Role> roles = unmarshalEntities(responseJson, Role.class, unmarshaller);
    assertEquals(3, roles.size());

    Role role0 = roles.get(0);
    assertTrue(role0.getId() > 0);
    assertEquals(nedIndividualId, role0.getNedid());
    assertEquals("Author", role0.getType());

    Role role1 = roles.get(1);
    assertTrue(role1.getId() > 0);
    assertEquals(nedIndividualId, role1.getNedid());
    assertEquals("Editorial Manager", role1.getApplicationtype());
    assertEquals("Co-Author", role1.getType());

    Role role2 = roles.get(2);
    assertTrue(role2.getId() > 0);
    assertEquals(nedIndividualId, role2.getNedid());
    assertEquals("Editorial Manager", role2.getApplicationtype());
    assertEquals("Academic Editor (PLOS ONE)", role2.getType());
    assertEquals(START_DATE, role2.getStartdate());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(roleURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.json(writeValueAsString(role)));

    assertEquals(200, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(roleURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .delete();

    assertEquals(204, response.getStatus());
  }

  @Test
  public void testEmailCrud() throws IOException, JAXBException {

    String emailsURI = String.format("%s/%d/emails", INDIVIDUAL_URI, nedIndividualId);

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    String emailJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "email.json")));

    Response response = target(emailsURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(emailJson));

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Email.class);
    Email email = unmarshalEntity(jsonPayload, Email.class, unmarshaller);

    assertTrue(email.getId() > 0);
    assertEquals(nedIndividualId, email.getNedid());
    assertEquals("jane.q.doe.personal@foo.com", email.getEmailaddress());
    assertEquals(true, email.getIsactive());

    String emailURI = emailsURI + "/" + email.getId();

    // create an email using an existing name (will raise of unique constraint violation)

    response = target(emailsURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(emailJson));

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

    jsonPayload = response.readEntity(String.class);

    NedErrorResponse ner = unmarshalEntity(jsonPayload, NedErrorResponse.class, 
                                           jsonUnmarshaller(NedErrorResponse.class));

    assertEquals(DupeEmailError.getErrorCode(), ner.errorCode);
    assertEquals(DupeEmailError.getErrorMessage(), ner.errorMsg);

    // test creation with invalid email type

    String badEmailJson = new String(Files.readAllBytes(Paths.get(
                                     TEST_RESOURCE_PATH + "email.invalid-type.json")));

    response = target(emailsURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(badEmailJson));

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

    jsonPayload = response.readEntity(String.class);

    ner = unmarshalEntity(jsonPayload, NedErrorResponse.class, jsonUnmarshaller(NedErrorResponse.class));

    Set<String> expectedEmailTypeValues = new HashSet<String>();
    for (String emailtype : new String[]{ "Work","Personal" }) {
      expectedEmailTypeValues.add(emailtype);
    }

    assertEquals(InvalidTypeValue.getErrorCode(), ner.errorCode);

    assertNotNull(ner.acceptableValues);

    assertTrue( expectedEmailTypeValues.containsAll(ner.acceptableValues) );
    assertTrue( ner.acceptableValues.containsAll(expectedEmailTypeValues) );

    /* ------------------------------------------------------------------ */
    /*  FIND (BY EMAIL ID (PK))                                           */
    /* ------------------------------------------------------------------ */

    response = target(emailURI).request(MediaType.APPLICATION_JSON_TYPE).get();
    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    Email foundEmail = unmarshalEntity(jsonPayload, Email.class, unmarshaller);
    assertEquals(email, foundEmail);

    /* ------------------------------------------------------------------ */
    /*  FIND (BY NED ID)                                                  */
    /* ------------------------------------------------------------------ */

    response = target(emailsURI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    List<Email> emails = unmarshalEntities(jsonPayload, Email.class, unmarshaller);
    assertEquals(2, emails.size());

    Email email0 = emails.get(0);
    assertEquals("Work", email0.getType());
    assertEquals("jane.q.doe.work@foo.com", email0.getEmailaddress());
    assertEquals("Ambra", email0.getSource());
    assertEquals(true, email0.getIsactive());

    Email email1 = emails.get(1);
    assertEquals("Personal", email1.getType());
    assertEquals("jane.q.doe.personal@foo.com", email1.getEmailaddress());
    assertEquals("Ambra", email1.getSource());
    assertEquals(true, email1.getIsactive());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(emailURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.json(writeValueAsString(email)));

    assertEquals(200, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(emailURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .delete();

    assertEquals(204, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  4XX ERRORS                                                        */
    /* ------------------------------------------------------------------ */

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
        target(INDIVIDUAL_URI + "/-1/emails")
            .request(MediaType.APPLICATION_JSON_TYPE).get().getStatus());

    // EMAIL CROSSING ENTITY TYPES

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
        target(INDIVIDUAL_URI + "/"+ nedOrganizationId +"/emails")
            .request(MediaType.APPLICATION_JSON_TYPE).get().getStatus());

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
        target(ORGANIZATION_URI + "/"+ nedIndividualId +"/emails/" + email0.getId())
            .request(MediaType.APPLICATION_JSON_TYPE).get().getStatus());

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
        target(ORGANIZATION_URI + "/"+ nedOrganizationId +"/emails/" + email0.getId())
            .request(MediaType.APPLICATION_JSON_TYPE).get().getStatus());
  }

  @Test
  public void testUniqueIdentifiersCRUD() throws IOException, JAXBException {

    String uidsURI = String.format("%s/%d/uids", INDIVIDUAL_URI, nedIndividualId);

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    String requestJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "uid.ambra.json")));

    Response response = target(uidsURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(requestJson));

    assertEquals(200, response.getStatus());

    String responseJson = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Uniqueidentifier.class);
    Uniqueidentifier uid = unmarshalEntity(responseJson, Uniqueidentifier.class, unmarshaller);
    assertNotNull(uid);

    String uidURI = uidsURI + "/" + uid.getId();

    /* ------------------------------------------------------------------ */
    /*  FIND (BY UID ID (PK))                                             */
    /* ------------------------------------------------------------------ */

    response = target(uidURI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    Uniqueidentifier foundUid = unmarshalEntity(responseJson, Uniqueidentifier.class, unmarshaller);
    assertEquals(uid, foundUid);

    /* ------------------------------------------------------------------ */
    /*  FIND UIDs FOR INDIVIDUAL (BY NED ID)                              */
    /* ------------------------------------------------------------------ */

    response = target(uidsURI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    List<Uniqueidentifier> uids = unmarshalEntities(responseJson, Uniqueidentifier.class,
        jsonUnmarshaller(Uniqueidentifier.class));
    assertEquals(6, uids.size());

    /* ------------------------------------------------------------------ */
    /*  FIND INDIVIDUAL BY UID                                            */
    /* ------------------------------------------------------------------ */

    String[] uidTypes = new String[] {
      "ORCID", "Editorial Manager", "CAS", "Salesforce", "Ambra"
    };

    String[] uidValues = new String[] {
      "0000-0002-9430-000X",
      "PONE-579386",
      "3BBFE34C8EEF46FEA1E0DA3339DF1EC9",
      "001U0000008Qlfj",
      "421649"
    };

    for (int i = 0; i < uidTypes.length; i++) {
      response = target(INDIVIDUAL_URI+"/"+uidTypes[i]+"/"+uidValues[i])
          .request(MediaType.APPLICATION_JSON_TYPE).get();

      assertEquals(200, response.getStatus());
      responseJson = response.readEntity(String.class);

      IndividualComposite individualComposite = unmarshalEntity(responseJson, 
        IndividualComposite.class, jsonUnmarshaller(IndividualComposite.class));

      assertNotNull(individualComposite);
    }

    // invalid uid lookup
    response = target(INDIVIDUAL_URI + "/BOGUS_TYPE/BOGUS_VALUE")
        .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(uidURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.json(writeValueAsString(foundUid)));

    assertEquals(200, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(uidURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .delete();

    assertEquals(204, response.getStatus());
  }

  @Test
  public void testInvalidUidTypeForEntity() throws IOException, JAXBException {

    /* ------------------------------------------------------------------ */
    /*  INDIVIDUAL                                                        */
    /* ------------------------------------------------------------------ */

    String individualUidsURI = String.format("%s/%d/uids", INDIVIDUAL_URI, nedIndividualId);

    String requestJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "uid.ringgold.json")));

    Response response = target(individualUidsURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(requestJson));

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

    String responseJson = response.readEntity(String.class);

    NedErrorResponse ner = unmarshalEntity(responseJson, NedErrorResponse.class, 
                                           jsonUnmarshaller(NedErrorResponse.class));

    assertEquals(InvalidTypeValue.getErrorCode(), ner.errorCode);
    assertNotNull( ner.acceptableValues );
    assertTrue( ner.acceptableValues.equals(UidTypeEnum.getIndividualUidTypeNames()) );
    assertTrue( UidTypeEnum.getIndividualUidTypeNames().equals(ner.acceptableValues) );

    /* ------------------------------------------------------------------ */
    /*  ORGANIZATION                                                      */
    /* ------------------------------------------------------------------ */

    String organizationUidsURI = String.format("%s/%d/uids", ORGANIZATION_URI, nedOrganizationId);

    requestJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "uid.ambra.json")));

    response = target(organizationUidsURI)
      .request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(requestJson));

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

    responseJson = response.readEntity(String.class);

    ner = unmarshalEntity(responseJson, NedErrorResponse.class, 
                          jsonUnmarshaller(NedErrorResponse.class));

    assertEquals(InvalidTypeValue.getErrorCode(), ner.errorCode);

    assertTrue( ner.acceptableValues.equals(UidTypeEnum.getOrganizationUidTypeNames()) );
    assertTrue( UidTypeEnum.getOrganizationUidTypeNames().equals(ner.acceptableValues) );
  }

  @Test
  public void testTypeDescriptionCrud() throws IOException, JAXBException {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    final String NEW_TYPE_DESC  = "New Type Class";
    final String NEW_TYPE_USAGE = "New Type Usage";

    String typeClassJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "typeclass.json")));

    Response response = target(TYPE_CLASS_URI).request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(typeClassJson));

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Typedescription.class);
    Typedescription newTypeClass = unmarshalEntity(jsonPayload, Typedescription.class, unmarshaller);

    Integer typeClassId = newTypeClass.getId();

    assertTrue( typeClassId > 0 );
    assertEquals(NEW_TYPE_DESC, newTypeClass.getDescription());
    assertEquals(NEW_TYPE_USAGE, newTypeClass.getHowused());

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    response = target(TYPE_CLASS_URI+"/"+typeClassId).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    Typedescription foundTypeClass = unmarshalEntity(jsonPayload, Typedescription.class, unmarshaller);

    assertEquals(typeClassId, foundTypeClass.getId());
    assertEquals(NEW_TYPE_DESC, foundTypeClass.getDescription());
    assertEquals(NEW_TYPE_USAGE, foundTypeClass.getHowused());

    // test lookup by invalid type class id

    response = target(TYPE_CLASS_URI+"/0").request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

    jsonPayload = response.readEntity(String.class);

    NedErrorResponse ner = unmarshalEntity(jsonPayload, NedErrorResponse.class, 
                                           jsonUnmarshaller(NedErrorResponse.class));

    assertEquals(EntityNotFound.getErrorCode(), ner.errorCode);

    /* ------------------------------------------------------------------ */
    /*  FIND (ALL)                                                        */
    /* ------------------------------------------------------------------ */

    response = target(TYPE_CLASS_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());
    jsonPayload = response.readEntity(String.class);

    List<Typedescription> typedescriptions = unmarshalEntities(
        jsonPayload, Typedescription.class, unmarshaller);

    assertTrue( typedescriptions.size() > 1 );

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    final String UPDATE_TYPE_DESC  = "Update Type Description";
    final String UPDATE_TYPE_USAGE = "Update Type Usage";

    Typedescription updateTypeClass = foundTypeClass;
    updateTypeClass.setDescription(UPDATE_TYPE_DESC);
    updateTypeClass.setHowused(UPDATE_TYPE_USAGE);

    // marshal Type description pojo object to JSON String.

    String jsonUpdateTypeClass = writeValueAsString(updateTypeClass);

    response = target(TYPE_CLASS_URI+"/"+typeClassId).request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.json(jsonUpdateTypeClass));

    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    Typedescription updatedTypeClass = unmarshalEntity(jsonPayload, Typedescription.class, unmarshaller);

    assertEquals(typeClassId, updatedTypeClass.getId());
    assertEquals(UPDATE_TYPE_DESC, updatedTypeClass.getDescription());
    assertEquals(UPDATE_TYPE_USAGE, updatedTypeClass.getHowused());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                           */
    /* ------------------------------------------------------------------ */

    response = target(TYPE_CLASS_URI+"/"+typeClassId).request(MediaType.APPLICATION_JSON_TYPE).delete();
    assertEquals(204, response.getStatus());
  }

  @Test
  public void testGlobalTypesCrud() throws IOException, JAXBException {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    final String NEW_TYPE_VAL_SDESC = "New Type Value Short Description";
    final String NEW_TYPE_VAL_LDESC = "New Type Value Long Description";
    final String NEW_TYPE_VAL_CODE  = "XYZ";

    String globalTypeJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "globaltype.json")));

    Integer typeClassId = findTypeClassIdByName("Roles");

    String typeValueUri = String.format("%s/%s/typevalues", TYPE_CLASS_URI, typeClassId);

    Response response = target(typeValueUri).request(MediaType.APPLICATION_JSON_TYPE)
                          .post(Entity.json(globalTypeJson));

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Globaltype.class);
    Globaltype newTypeValue   = unmarshalEntity(jsonPayload, Globaltype.class, unmarshaller);

    Integer newTypeValueId = newTypeValue.getId();

    assertTrue( newTypeValueId > 0 );
    assertEquals(NEW_TYPE_VAL_SDESC, newTypeValue.getShortdescription());
    assertEquals(NEW_TYPE_VAL_CODE, newTypeValue.getTypecode());

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    response = target(typeValueUri+"/"+newTypeValueId)
                  .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    Globaltype foundTypeValue = unmarshalEntity(jsonPayload, Globaltype.class, unmarshaller);

    assertEquals(newTypeValueId, foundTypeValue.getId());
    assertEquals(typeClassId, foundTypeValue.getTypeid());
    assertEquals(NEW_TYPE_VAL_SDESC, foundTypeValue.getShortdescription());
    assertEquals(NEW_TYPE_VAL_CODE, foundTypeValue.getTypecode());

    /* ------------------------------------------------------------------ */
    /*  FIND VALUES FOR TYPE CLASS (BY ATTRIBUTE)                         */
    /* ------------------------------------------------------------------ */

    response = target(typeValueUri).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());
    jsonPayload = response.readEntity(String.class);

    List<Globaltype> typevaluesfortypeclass = unmarshalEntities(jsonPayload, Globaltype.class, unmarshaller);
    assertTrue(typevaluesfortypeclass.size() > 2);

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    final String UPDATE_TYPE_VAL_SDESC = "Updated Type Value Short Description";

    Globaltype updateTypeValue = foundTypeValue;
    updateTypeValue.setShortdescription(UPDATE_TYPE_VAL_SDESC);

    // marshal Type value pojo object to JSON String.

    String updateTypeValueJson = writeValueAsString(updateTypeValue);

    response = target(typeValueUri+"/"+newTypeValueId).request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.json(updateTypeValueJson));

    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    Globaltype updatedTypeValue = unmarshalEntity(jsonPayload, Globaltype.class, unmarshaller);

    assertEquals(newTypeValueId, updatedTypeValue.getId());
    assertEquals(typeClassId, updatedTypeValue.getTypeid());
    assertEquals(UPDATE_TYPE_VAL_SDESC, updatedTypeValue.getShortdescription());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(typeValueUri+"/"+newTypeValueId).request(MediaType.APPLICATION_JSON_TYPE).delete();
    assertEquals(204, response.getStatus());
  }

  @Test
  public void testCreateSearchCriteriaForIndividual() {

    // Instantiate resource and manually inject dependency

    IndividualsResource resource = new IndividualsResource();
    resource.namedEntityService = (NamedEntityService) SpringContextAwareJerseyTest.getApplicationContext().getBean("namedEntityService");

    // Invalid Classname (entity)

    try {
      resource.createSearchCriteria("bogus","attribute","value",IndividualComposite.class);
      fail();
    } catch (NedException expected) {
      assertEquals(InvalidSearchCriteria, expected.getErrorType());
      assertTrue( expected.getDetailedMessage().contains("Verify entity name") );
    }

    // Invalid Attribute

    try {
      resource.createSearchCriteria("email","attribute","value",IndividualComposite.class);
      fail();
    } catch (NedException expected) {
      assertEquals(InvalidSearchCriteria, expected.getErrorType());
      assertTrue( expected.getDetailedMessage().contains("Verify attribute name") );
    }

    // Invalid Entity For Composite 

    try {
      resource.createSearchCriteria("organization","legalname","abc inc.",IndividualComposite.class);
      fail();
    } catch (NedException expected) {
      assertEquals(InvalidSearchCriteria, expected.getErrorType());
      assertTrue( expected.getDetailedMessage().matches("^Invalid entity \\(.*\\) for composite type \\(.*\\)$") );
    }

    Email email = (Email) resource.createSearchCriteria("email", "emailaddress", "foo@bar.com",IndividualComposite.class);
    assertEquals("foo@bar.com", email.getEmailaddress());

    Individualprofile profile = (Individualprofile) resource.createSearchCriteria("individualprofile", "displayname", "fumanchu",IndividualComposite.class);
    assertEquals("fumanchu", profile.getDisplayname());

    Address address = (Address) resource.createSearchCriteria("address", "countrycodetype", "Albania",IndividualComposite.class);
    assertNotNull(address.getCountrycodetypeid());
    assertNull( address.getCountrycodetype() );
    assertTrue( address.getCountrycodetypeid() > 0 );
  }

  @Test
  public void testCreateSearchCriteriaForOrganization() {

    // Instantiate resource and manually inject dependency

    OrganizationsResource resource = new OrganizationsResource();
    resource.namedEntityService = (NamedEntityService) SpringContextAwareJerseyTest.getApplicationContext().getBean("namedEntityService");

    // Invalid Classname (entity)

    try {
      resource.createSearchCriteria("bogus","attribute","value",OrganizationComposite.class);
      fail();
    } catch (NedException expected) {
      assertEquals(InvalidSearchCriteria, expected.getErrorType());
      assertTrue( expected.getDetailedMessage().contains("Verify entity name") );
    }

    // Invalid Attribute

    try {
      resource.createSearchCriteria("email","attribute","value",OrganizationComposite.class);
      fail();
    } catch (NedException expected) {
      assertEquals(InvalidSearchCriteria, expected.getErrorType());
      assertTrue( expected.getDetailedMessage().contains("Verify attribute name") );
    }

    // Invalid Entity For Composite 

    try {
      resource.createSearchCriteria("individualprofile","displayname","superfunky",OrganizationComposite.class);
      fail();
    } catch (NedException expected) {
      assertEquals(InvalidSearchCriteria, expected.getErrorType());
      assertTrue( expected.getDetailedMessage().matches("^Invalid entity \\(.*\\) for composite type \\(.*\\)$") );
    }

    Organization org = (Organization) resource.createSearchCriteria("organization", "legalname", "acme corp.",OrganizationComposite.class);
    assertEquals("acme corp.", org.getLegalname());

    Email email = (Email) resource.createSearchCriteria("email", "emailaddress", "foo@bar.com",OrganizationComposite.class);
    assertEquals("foo@bar.com", email.getEmailaddress());
  }

  @Test
  public void testAuthEndpoint() throws Exception {

    final String authURI  = String.format("%s/%d/auth", INDIVIDUAL_URI, nedIndividualId);
    final String PASSWORD = "super_secret_password";

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    Integer emailid     = individualComposite.getEmails().get(0).getId();
    String emailaddress = individualComposite.getEmails().get(0).getEmailaddress();

    String authJsonTemplate = new String(Files.readAllBytes(
      Paths.get(TEST_RESOURCE_PATH + "auth.template.json")));

    Response response = target(authURI).request(MediaType.APPLICATION_JSON_TYPE)
      .post(Entity.json(String.format(authJsonTemplate, 
        nedIndividualId, emailid, PASSWORD)));

    assertEquals(200, response.getStatus());

    String responseJson = response.readEntity(String.class);

    Auth auth = unmarshalEntity(responseJson, Auth.class, jsonUnmarshaller(Auth.class));

    assertTrue( auth.getId() > 0 );
    assertTrue(new PasswordDigestService().verifyPassword(PASSWORD, auth.getPassword()));
    assertEquals(36, auth.getAuthid().length());
    assertEquals(nedIndividualId, auth.getNedid());
    assertEquals(emailaddress, auth.getEmail());
    assertTrue( auth.getIsactive().equals((byte)1) );
    assertEquals(emailid, auth.getEmailid());
    assertEquals(emailaddress, auth.getEmail());

    int x = 1;
  }
}
