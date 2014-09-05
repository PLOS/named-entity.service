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

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.entity.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NamedEntityResourceTest extends SpringContextAwareJerseyTest {

  private static final String TEST_RESOURCE_PATH = "src/test/resources/";

  private static final String TYPE_CLASS_URI  = "/typeclasses";
  private static final String TYPE_VALUE_URI  = TYPE_CLASS_URI + "/1/typevalues";
  private static final String INDIVIDUAL_URI  = "/individuals";
  private static final String INDIVIDUAL_COMPOSITE_URI  = "/individuals_composite";
  private static final String INDIV_ADDR_URI  = INDIVIDUAL_URI + "/1/addresses";
  private static final String INDIV_EMAIL_URI = INDIVIDUAL_URI + "/1/emails";
  private static final String INDIV_PHONE_URI = INDIVIDUAL_URI + "/1/phonenumbers";
  private static final String INDIV_ROLE_URI  = INDIVIDUAL_URI + "/1/roles";
  private static final String INDIV_XREF_URI  = INDIVIDUAL_URI + "/1/xref";
  private static final String INDIV_DEGREE_URI  = INDIVIDUAL_URI + "/1/degrees";

  private static final String ORGANIZATION_URI= "/organizations";

  private ObjectMapper mapper;

  @Before
  public void setup() {
    this.mapper = new ObjectMapper();
    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Test
  public void testOrganizationCrud() throws Exception {

    // CREATE

    String organizationJson = "{\n" +
        "   \"organizationfamiliarname\" : \"organizationfamiliarname\",\n" +
        "   \"organizationlegalname\" : \"organizationlegalname\",\n" +
        "   \"isactive\" : 0,\n" +
        "   \"isvisible\" : true,\n" +
        "   \"url\" : \"website.com\" }"; // TODO: make use of this in assertions

    // Request #1. Expect success.

    Response response = target(ORGANIZATION_URI).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(organizationJson));

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Organization entity = mapper.readValue(jsonPayload, Organization.class);
    assertEquals(Integer.valueOf(2), entity.getNedid());
    assertEquals("familiarname", entity.getFamiliarname());
    assertEquals("legalname", entity.getLegalname());
    assertEquals(new Byte((byte)0), entity.getIsactive());
    assertEquals(new Byte((byte)1), entity.getIsvisible());

    // GET

    response = target(ORGANIZATION_URI + "/2").request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    entity = mapper.readValue(jsonPayload, Organization.class);
    assertEquals(Integer.valueOf(2), entity.getNedid());
    assertEquals("familiarname", entity.getFamiliarname());
    assertEquals("legalname", entity.getLegalname());
    assertEquals(new Byte((byte)0), entity.getIsactive());
    assertEquals(new Byte((byte)1), entity.getIsvisible());

    
    // TODO: LIST, DELETE, UPDATE
  }

  @Test
  public void testCreateIndividualComposite() throws Exception {

    String compositeIndividualJson = new String(Files.readAllBytes(
                                      Paths.get(TEST_RESOURCE_PATH + "composite-individual.json")));
  
    // Request #1. Expect success.

    Response response = target(INDIVIDUAL_COMPOSITE_URI).request(MediaType.APPLICATION_JSON_TYPE)
                          .post(Entity.json(compositeIndividualJson));

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    IndividualComposite composite = mapper.readValue(jsonPayload, IndividualComposite.class);
    assertEquals(Integer.valueOf(1), composite.getNamedentityid());
    assertEquals("firstname", composite.getFirstname());
    assertEquals("middlename", composite.getMiddlename());
    assertEquals("lastname", composite.getLastname());
    assertEquals("Ms.", composite.getNameprefix());
    assertEquals("email@internet.com", composite.getEmails().get(0).getEmailaddress());

    // Request #2. Expect a validation exception (client-side error)

    response = target(INDIVIDUAL_COMPOSITE_URI).request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(compositeIndividualJson));

    assertEquals(400, response.getStatus());

    String textPayload = response.readEntity(String.class);
    assertTrue(textPayload.indexOf("Validation failed") >= 0);

    // Request #3. Expect a data access exception (server-side error)

    response = target(INDIVIDUAL_COMPOSITE_URI).request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(compositeIndividualJson));

    assertEquals(500, response.getStatus());

    textPayload = response.readEntity(String.class);
    assertTrue(textPayload.indexOf("Internal error") >= 0);
  }

  @Test
  public void testReadIndividualComposite() throws Exception {

    Response response = target(INDIVIDUAL_COMPOSITE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    IndividualComposite composite = mapper.readValue(jsonPayload, IndividualComposite.class);
    assertEquals(Integer.valueOf(1), composite.getNamedentityid());
    assertEquals("firstname", composite.getFirstname());
    assertEquals("middlename", composite.getMiddlename());
    assertEquals("lastname", composite.getLastname());
    assertEquals("Ms.", composite.getNameprefix());
    assertEquals("email@internet.com", composite.getEmails().get(0).getEmailaddress());
  }

  @Test
  public void testIndividualFinders() throws IOException {

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    Response response = target(INDIVIDUAL_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Individual entity = mapper.readValue(jsonPayload, Individual.class);
    assertEquals(Integer.valueOf(1), entity.getNedid());
    assertEquals("firstname", entity.getFirstname());
    assertEquals("middlename", entity.getMiddlename());
    assertEquals("lastname", entity.getLastname());
    assertEquals("Mr.", entity.getNameprefix());

    /* ------------------------------------------------------------------ */
    /*  FIND (ALL)                                                        */
    /* ------------------------------------------------------------------ */

    response = target(INDIVIDUAL_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());
    jsonPayload = response.readEntity(String.class);

    Individual[] individualEntityArray = mapper.readValue(jsonPayload, Individual[].class);
    assertEquals(3, individualEntityArray.length);
  }

  @Test
  public void testAddressCrud() throws IOException {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    String requestJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "address.json")));
  
    Response response = target(INDIV_ADDR_URI).request(MediaType.APPLICATION_JSON_TYPE)
                          .post(Entity.json(requestJson));

    assertEquals(200, response.getStatus());

    String responseJson = response.readEntity(String.class);

    Address entity = mapper.readValue(responseJson, Address.class);
    assertEquals(Integer.valueOf(1), entity.getId());
    assertEquals(Integer.valueOf(1), entity.getNedid());
    assertEquals("Office", entity.getType());
    assertEquals("addressline 1", entity.getAddressline1());
    assertEquals("addressline 2", entity.getAddressline2());
    assertEquals("addressline 3", entity.getAddressline3());
    assertEquals("city", entity.getCity());
    assertEquals("CA", entity.getStatecodetype());
    assertEquals("United States", entity.getCountrycodetype());
    assertEquals("12345", entity.getPostalcode());
    assertEquals(Byte.valueOf((byte)1), entity.getIsprimary());
    assertEquals(Byte.valueOf((byte)1), entity.getIsactive());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_ADDR_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(mapper.writeValueAsString(entity)));

    assertEquals(200, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_ADDR_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).delete();
    assertEquals(204, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ADDRESS ID (PK))                                         */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_ADDR_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).get();
    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    Address address = mapper.readValue(responseJson, Address.class);
    assertNotNull( address );

    /* ------------------------------------------------------------------ */
    /*  FIND (BY NED ID)                                                  */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_ADDR_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    Address[] addresses = mapper.readValue(responseJson, Address[].class);
    assertEquals(2, addresses.length);

    Address officeAddress = addresses[0];
    assertEquals("Office", officeAddress.getType());
    assertEquals("addressline 1", officeAddress.getAddressline1());
    assertEquals("CA", officeAddress.getStatecodetype());
    assertEquals("12345", officeAddress.getPostalcode());

    Address homeAddress = addresses[1];
    assertEquals("Home", homeAddress.getType());
    assertEquals("addressline 1.2", homeAddress.getAddressline1());
    assertEquals("ONT", homeAddress.getStatecodetype());
    assertEquals("M4C 1B5", homeAddress.getPostalcode());
  }

  @Test
  public void testEmailCrud() throws IOException {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    String emailsJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "emails.json")));
  
    Response response = target(INDIV_EMAIL_URI).request(MediaType.APPLICATION_JSON_TYPE)
                          .post(Entity.json(emailsJson));

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Email entity = mapper.readValue(jsonPayload, Email.class);
    assertEquals(Integer.valueOf(1), entity.getId());
    assertEquals(Integer.valueOf(1), entity.getNedid());
    assertEquals("Work", entity.getType());
    assertEquals("foo.bar.personal@gmail.com", entity.getEmailaddress());
    assertEquals(Byte.valueOf((byte)1), entity.getIsprimary());
    assertEquals(Byte.valueOf((byte)1), entity.getIsactive());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */


    String jsonString = mapper.writeValueAsString(entity);

    Entity<String> eString = Entity.json(jsonString);

    response = target(INDIV_EMAIL_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                  .post(eString);

    assertEquals(200, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_EMAIL_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).delete();
    assertEquals(204, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  FIND (BY EMAIL ID (PK))                                           */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_EMAIL_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).get();
    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    Email email = mapper.readValue(jsonPayload, Email.class);
    assertNotNull( email );

    /* ------------------------------------------------------------------ */
    /*  FIND (BY NED ID)                                                  */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_EMAIL_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    Email[] emails = mapper.readValue(jsonPayload, Email[].class);
    assertEquals(2, emails.length);

    Email workEmail = emails[0];
    assertEquals("Work", workEmail.getType());
    assertEquals("fu.manchu.work@foo.com", workEmail.getEmailaddress());
    assertTrue(workEmail.getIsprimary() == 1);


    /* ------------------------------------------------------------------ */
    /*  404 ERRORS                                                        */
    /* ------------------------------------------------------------------ */

    assertEquals(Response.Status.OK.getStatusCode(),
        target("/individuals/1")
            .request(MediaType.APPLICATION_JSON_TYPE).get().getStatus());

    assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
        target("/individuals/2")
            .request(MediaType.APPLICATION_JSON_TYPE).get().getStatus());

    // INDIVIDUAL NOT

    assertEquals(Response.Status.OK.getStatusCode(),
        target("/individuals/1/emails")
        .request(MediaType.APPLICATION_JSON_TYPE).get().getStatus());

//    assertEquals(Response.Status.OK.getStatusCode(),
//        target("/organizations/2/emails")
//            .request(MediaType.APPLICATION_JSON_TYPE).get().getStatus());

    assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
        target("/individuals/2/emails")
        .request(MediaType.APPLICATION_JSON_TYPE).get().getStatus());

    // EMAIL CROSSING ENTITIES

    assertEquals(Response.Status.OK.getStatusCode(),
        target("/individuals/1/emails/1")
        .request(MediaType.APPLICATION_JSON_TYPE).get().getStatus());

//    assertEquals(Response.Status.OK.getStatusCode(), target("/organizations/2/emails/5").request(MediaType.APPLICATION_JSON_TYPE).get().getStatus()); // TODO: add this once /organizations email CRUD is done

    assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
        target("/individuals/1/emails/5")
        .request(MediaType.APPLICATION_JSON_TYPE).get().getStatus());
  }

  @Test
  public void testDegreeCrud() throws IOException {

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    Response response = target(INDIV_DEGREE_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Degree[] degrees = mapper.readValue(jsonPayload, Degree[].class);
    assertEquals(1, degrees.length);

    Degree degree = degrees[0];
    assertEquals("Super Doctor", degree.getType());

    //TODO - CREATE, UPDATE, DELETE
  }

  @Test
  public void testPhonenumberCrud() throws IOException {

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    Response response = target(INDIV_PHONE_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Phonenumber[] phonenumbers = mapper.readValue(jsonPayload, Phonenumber[].class);
    assertEquals(3, phonenumbers.length);

    Phonenumber officePhone = phonenumbers[0];
    assertEquals("Office", officePhone.getType());
    assertEquals("123-456-7890", officePhone.getPhonenumber());

    //TODO - CREATE, UPDATE, DELETE
  }

  @Test
  public void testRoleCrud() throws IOException {

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    Response response = target(INDIV_ROLE_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Role[] roles = mapper.readValue(jsonPayload, Role[].class);
    assertEquals(1, roles.length);

    Role role = roles[0];
    assertEquals("Author", role.getType());

    //TODO - CREATE, UPDATE, DELETE
  }

  @Test
  public void testExternalReferenceLookup() throws IOException {

    /* ------------------------------------------------------------------ */
    /*  FIND EXTERNAL REFERENCES FOR INDIVIDUAL                           */
    /* ------------------------------------------------------------------ */

    Response response = target(INDIV_XREF_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Uniqueidentifier[] xrefs = mapper.readValue(jsonPayload, Uniqueidentifier[].class);
    assertEquals(2, xrefs.length);

    for (Uniqueidentifier xref : xrefs) {
      assertEquals("ORCID", xref.getType());
    }

    /* ------------------------------------------------------------------ */
    /*  FIND INDIVIDUALS BY EXTERNAL REFERENCE                            */
    /* ------------------------------------------------------------------ */

    response = target(INDIVIDUAL_URI)
                .queryParam("uidType", "ORCID")
                .queryParam("uidValue", "0000-0002-9430-3191")
                .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());
    jsonPayload = response.readEntity(String.class);

    Individual[] individualEntityArray = mapper.readValue(jsonPayload, Individual[].class);
    assertEquals(3, individualEntityArray.length);
  }

  @Test
  public void testTypeDescriptionCrud() throws IOException {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    final String NEW_TYPE_DESC  = "New Type Description";
    final String NEW_TYPE_USAGE = "New Type Usage";

    final String NEW_TYPE_JSON_PAYLOAD = "{"
            + "\"description\":\"" + NEW_TYPE_DESC  + "\","
            + "\"howused\":\""     + NEW_TYPE_USAGE + "\""
            + "}";

    // Request #1. Expect success.

    Response response = target(TYPE_CLASS_URI).request(MediaType.APPLICATION_JSON_TYPE)
                          .post(Entity.json(NEW_TYPE_JSON_PAYLOAD));

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Typedescription newTypeClass = mapper.readValue(jsonPayload, Typedescription.class);
    assertEquals(Integer.valueOf(1), newTypeClass.getId());
    assertEquals(NEW_TYPE_DESC, newTypeClass.getDescription());
    assertEquals(NEW_TYPE_USAGE, newTypeClass.getHowused());

    // Request #2. Expect a validation exception (client-side error)

    response = target(TYPE_CLASS_URI).request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(NEW_TYPE_JSON_PAYLOAD));

    assertEquals(400, response.getStatus());

    String textPayload = response.readEntity(String.class);
    assertTrue(textPayload.indexOf("Validation failed") >= 0);

    // Request #3. Expect a data access exception (server-side error)

    response = target(TYPE_CLASS_URI).request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(NEW_TYPE_JSON_PAYLOAD));

    assertEquals(500, response.getStatus());

    textPayload = response.readEntity(String.class);
    assertTrue(textPayload.indexOf("Internal error") >= 0);

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    response = target(TYPE_CLASS_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    Typedescription foundTypeClass = mapper.readValue(jsonPayload, Typedescription.class);
    assertEquals(Integer.valueOf(1), foundTypeClass.getId());
    assertEquals(NEW_TYPE_DESC, foundTypeClass.getDescription());
    assertEquals(NEW_TYPE_USAGE, foundTypeClass.getHowused());

    /* ------------------------------------------------------------------ */
    /*  FIND (ALL)                                                        */
    /* ------------------------------------------------------------------ */

    response = target(TYPE_CLASS_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());
    jsonPayload = response.readEntity(String.class);

    Typedescription[] typeClassArray = mapper.readValue(jsonPayload, Typedescription[].class);
    assertEquals(3, typeClassArray.length);

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    final String UPDATE_TYPE_DESC  = "Update Type Description";
    final String UPDATE_TYPE_USAGE = "Update Type Usage";

    Typedescription updatedTypeClass = foundTypeClass;
    updatedTypeClass.setDescription(UPDATE_TYPE_DESC);
    updatedTypeClass.setHowused(UPDATE_TYPE_USAGE);

    // marshal Type description pojo object to JSON String.

    Writer writer = new StringWriter();
    mapper.writeValue(writer, updatedTypeClass);

    response = target(TYPE_CLASS_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(writer.toString()));

    assertEquals(200, response.getStatus());

    // we won't bother to inspect payload because it won't be updated (we
    // didn't mock for this). however, just trying to update and returning
    // a 200 winds through much of the code we want tested.


    // UPDATE #2 should raise a validation exception

    response = target(TYPE_CLASS_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(writer.toString()));

    assertEquals(400, response.getStatus());
    
    // UPDATE #3 should raise a server-side exception

    response = target(TYPE_CLASS_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(writer.toString()));

    assertEquals(500, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                           */
    /* ------------------------------------------------------------------ */

    response = target(TYPE_CLASS_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).delete();
    assertEquals(204, response.getStatus());

    response = target(TYPE_CLASS_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).delete();
    assertEquals(500, response.getStatus());
  }

  @Test
  public void testIndividualsCrud() throws Exception {

    // CREATE, #1 expect success

    final String NEW_FIRSTNAME = "origfirstname";
    final String NEW_LASTNAME = "origlastname";

    final String NEW_INDIVIDUALS_JSON_PAYLOAD = "{"
        + "\"firstname\":\"" + NEW_FIRSTNAME + "\","
        + "\"lastname\":\""  + NEW_LASTNAME  + "\""
        + "}";

    Response response = target(INDIVIDUAL_URI).request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(NEW_INDIVIDUALS_JSON_PAYLOAD));

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Individual entity = mapper.readValue(jsonPayload, Individual.class);
    assertEquals(Integer.valueOf(1), entity.getNedid());
    assertEquals("firstname", entity.getFirstname());
    assertEquals("lastname", entity.getLastname());

    // READ

    response = target(INDIVIDUAL_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    jsonPayload = response.readEntity(String.class);

    entity = mapper.readValue(jsonPayload, Individual.class);
    assertEquals(Integer.valueOf(1), entity.getNedid());
    assertEquals("firstname", entity.getFirstname());
    assertEquals("lastname", entity.getLastname());

    // UPDATE

    response = target(INDIVIDUAL_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(NEW_INDIVIDUALS_JSON_PAYLOAD));

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    // DELETE

    response = target(INDIVIDUAL_URI + "/1").request().delete();
    assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
  }

  @Test
  public void testGlobalTypesCrud() throws IOException {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    final String NEW_TYPEVAL_SHORTDESC  = "New Type Val Short Description";
    final String NEW_TYPEVAL_LONGDESC   = "New Type Val Long Description";
    final String NEW_TYPEVAL_TYPECODE   = "XYZ";

    final String NEW_TYPEVAL_JSON_PAYLOAD = "{"
            + "\"shortdescription\":\"" + NEW_TYPEVAL_SHORTDESC + "\","
            + "\"longdescription\":\""  + NEW_TYPEVAL_LONGDESC  + "\","
            + "\"typecode\":\""         + NEW_TYPEVAL_TYPECODE  + "\""
            + "}";

    // Request #1. Expect success.

    Response response = target(TYPE_VALUE_URI).request(MediaType.APPLICATION_JSON_TYPE)
                          .post(Entity.json(NEW_TYPEVAL_JSON_PAYLOAD));

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Globaltype newTypeVal = mapper.readValue(jsonPayload, Globaltype.class);
    assertEquals(Integer.valueOf(1), newTypeVal.getId());
    assertEquals("Type Value #1 Short Description", newTypeVal.getShortdescription());
    assertEquals("TV1", newTypeVal.getTypecode());

    // Request #2. Expect a validation exception (client-side error)

    response = target(TYPE_VALUE_URI).request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(NEW_TYPEVAL_JSON_PAYLOAD));

    assertEquals(400, response.getStatus());

    String textPayload = response.readEntity(String.class);
    assertTrue(textPayload.indexOf("Validation failed") >= 0);

    // Request #3. Expect a data access exception (server-side error)

    response = target(TYPE_VALUE_URI).request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(NEW_TYPEVAL_JSON_PAYLOAD));

    assertEquals(500, response.getStatus());

    textPayload = response.readEntity(String.class);
    assertTrue(textPayload.indexOf("Internal error") >= 0);

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    response = target(TYPE_VALUE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    Globaltype foundTypeVal = mapper.readValue(jsonPayload, Globaltype.class);
    assertEquals(Integer.valueOf(1), foundTypeVal.getId());
    assertEquals(Integer.valueOf(1), foundTypeVal.getTypeid());
    assertEquals("Type Value #1 Short Description", foundTypeVal.getShortdescription());
    assertEquals("TV1", foundTypeVal.getTypecode());

    /* ------------------------------------------------------------------ */
    /*  FIND VALUES FOR TYPE CLASS (BY ATTRIBUTE)                         */
    /* ------------------------------------------------------------------ */

    response = target(TYPE_VALUE_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());
    jsonPayload = response.readEntity(String.class);

    Globaltype[] typeValuesForTypeClassArray = mapper.readValue(jsonPayload, Globaltype[].class);
    assertEquals(5, typeValuesForTypeClassArray.length);
    for (int i = 0; i < 5; i++) {
      assertEquals(Integer.valueOf(i+1), typeValuesForTypeClassArray[i].getId());
    }

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    Globaltype updatedTypeVal = foundTypeVal;
    updatedTypeVal.setShortdescription("Update Type Value #1 Short Description");

    // marshal Type value pojo object to JSON String.

    Writer writer = new StringWriter();
    mapper.writeValue(writer, updatedTypeVal);

    response = target(TYPE_VALUE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(writer.toString()));

    assertEquals(200, response.getStatus());

    // we won't bother to inspect payload because it won't be updated (we
    // didn't mock for this). however, just trying to update and returning
    // a 200 winds through much of the code we want tested.


    // UPDATE #2 should raise a validation exception

    response = target(TYPE_VALUE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(writer.toString()));

    assertEquals(400, response.getStatus());
    
    // UPDATE #3 should raise a server-side exception

    response = target(TYPE_VALUE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(writer.toString()));

    assertEquals(500, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                           */
    /* ------------------------------------------------------------------ */

    response = target(TYPE_VALUE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).delete();
    assertEquals(204, response.getStatus());

    response = target(TYPE_VALUE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).delete();
    assertEquals(500, response.getStatus());
  }
}
