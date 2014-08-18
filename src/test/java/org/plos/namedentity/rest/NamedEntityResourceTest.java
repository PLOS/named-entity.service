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

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
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
  }

  @Test
  public void testOrganizationCrud() throws Exception {

    // CREATE

    String organizationJson = "{\n" +
        "   \"organizationfamiliarname\" : \"organizationfamiliarname\",\n" +
        "   \"organizationlegalname\" : \"organizationlegalname\",\n" +
        "   \"isactive\" : 0,\n" +
        "   \"isvisible\" : true,\n" +
        "   \"url\" : \"website.com\" }";

    // Request #1. Expect success.

    Response response = target(ORGANIZATION_URI).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(organizationJson));

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    OrganizationEntity entity = mapper.readValue(jsonPayload, OrganizationEntity.class);
    assertEquals(Integer.valueOf(1), entity.getNamedentityid());
    assertEquals("familiarname", entity.getOrganizationfamiliarname());
    assertEquals("legalname", entity.getOrganizationlegalname());
    assertEquals(new Byte((byte)0), entity.getIsactive());
    assertEquals(new Byte((byte)1), entity.getIsvisible());

    // GET

    response = target(ORGANIZATION_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    entity = mapper.readValue(jsonPayload, OrganizationEntity.class);
    assertEquals(Integer.valueOf(1), entity.getNamedentityid());
    assertEquals("familiarname", entity.getOrganizationfamiliarname());
    assertEquals("legalname", entity.getOrganizationlegalname());
    assertEquals(new Byte((byte)0), entity.getIsactive());
    assertEquals(new Byte((byte)1), entity.getIsvisible());

    
    // TODO: LIST, DELETE, UPDATE
  }

  @Test
  public void testCreateIndividualComposite() throws Exception {

    String compositeIndividualJson = new String(Files.readAllBytes(
                                      Paths.get(TEST_RESOURCE_PATH + "composite-individual.json")));
  
    // Request #1. Expect success.

    Response response = target(INDIVIDUAL_URI).request(MediaType.APPLICATION_JSON_TYPE)
                          .post(Entity.json(compositeIndividualJson));

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    IndividualEntity entity = mapper.readValue(jsonPayload, IndividualEntity.class);
    assertEquals(Integer.valueOf(1), entity.getNamedentityid());
    assertEquals("firstname", entity.getFirstname());
    assertEquals("middlename", entity.getMiddlename());
    assertEquals("lastname", entity.getLastname());
    assertEquals("Mr.", entity.getNameprefix());

    // Request #2. Expect a validation exception (client-side error)

    response = target(INDIVIDUAL_URI).request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(compositeIndividualJson));

    assertEquals(400, response.getStatus());

    String textPayload = response.readEntity(String.class);
    assertTrue(textPayload.indexOf("Validation failed") >= 0);

    // Request #3. Expect a data access exception (server-side error)

    response = target(INDIVIDUAL_URI).request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(compositeIndividualJson));

    assertEquals(500, response.getStatus());

    textPayload = response.readEntity(String.class);
    assertTrue(textPayload.indexOf("Internal error") >= 0);
  }

  @Test
  public void testIndividualCrud() throws IOException {

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    Response response = target(INDIVIDUAL_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    IndividualEntity entity = mapper.readValue(jsonPayload, IndividualEntity.class);
    assertEquals(Integer.valueOf(1), entity.getNamedentityid());
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

    IndividualEntity[] individualEntityArray = mapper.readValue(jsonPayload, IndividualEntity[].class); 
    assertEquals(3, individualEntityArray.length);
  }

  @Test
  public void testAddressCrud() throws IOException {

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    Response response = target(INDIV_ADDR_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    AddressEntity[] addresses = mapper.readValue(jsonPayload, AddressEntity[].class);
    assertEquals(1, addresses.length);

    AddressEntity address = addresses[0];
    assertEquals("Office", address.getAddresstype());
    assertEquals("city", address.getCity());
    assertEquals("1234567", address.getPostalcode());
    assertTrue(address.getIsprimary() == 1);

    //TODO - CREATE, UPDATE, DELETE
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

    EmailEntity entity = mapper.readValue(jsonPayload, EmailEntity.class);
    assertEquals(Integer.valueOf(1), entity.getEmailid());
    assertEquals(Integer.valueOf(1), entity.getNamedentityid());
    assertEquals("Work", entity.getEmailtype());
    assertEquals("foo.bar.personal@gmail.com", entity.getEmailaddress());
    assertEquals(Byte.valueOf((byte)1), entity.getIsprimary());
    assertEquals(Byte.valueOf((byte)1), entity.getIsactive());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_EMAIL_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                  .post(Entity.json(mapper.writeValueAsString(entity)));

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

    EmailEntity email = mapper.readValue(jsonPayload, EmailEntity.class);
    assertNotNull( email );

    /* ------------------------------------------------------------------ */
    /*  FIND (BY NED ID)                                                  */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_EMAIL_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    EmailEntity[] emails = mapper.readValue(jsonPayload, EmailEntity[].class);
    assertEquals(2, emails.length);

    EmailEntity workEmail = emails[0];
    assertEquals("Work", workEmail.getEmailtype());
    assertEquals("fu.manchu.work@foo.com", workEmail.getEmailaddress());
    assertTrue(workEmail.getIsprimary() == 1);
  }

  @Test
  public void testDegreeCrud() throws IOException {

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    Response response = target(INDIV_DEGREE_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    DegreeEntity[] degrees = mapper.readValue(jsonPayload, DegreeEntity[].class);
    assertEquals(1, degrees.length);

    DegreeEntity degree = degrees[0];
    assertEquals("Super Doctor", degree.getDegreetype());

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

    PhonenumberEntity[] phonenumbers = mapper.readValue(jsonPayload, PhonenumberEntity[].class);
    assertEquals(3, phonenumbers.length);

    PhonenumberEntity officePhone = phonenumbers[0];
    assertEquals("Office", officePhone.getPhonenumbertype());
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

    RoleEntity[] roles = mapper.readValue(jsonPayload, RoleEntity[].class);
    assertEquals(1, roles.length);

    RoleEntity role = roles[0];
    assertEquals("Author", role.getRoletype());

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

    UniqueidentifierEntity[] xrefs = mapper.readValue(jsonPayload, UniqueidentifierEntity[].class);
    assertEquals(2, xrefs.length);

    for (UniqueidentifierEntity xref : xrefs) {
      assertEquals("ORCID", xref.getUniqueidentifiertype());
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

    IndividualEntity[] individualEntityArray = mapper.readValue(jsonPayload, IndividualEntity[].class); 
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

    TypedescriptionEntity newTypeClass = mapper.readValue(jsonPayload, TypedescriptionEntity.class);
    assertEquals(Integer.valueOf(1), newTypeClass.getTypeid());
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

    TypedescriptionEntity foundTypeClass = mapper.readValue(jsonPayload, TypedescriptionEntity.class);
    assertEquals(Integer.valueOf(1), foundTypeClass.getTypeid());
    assertEquals(NEW_TYPE_DESC, foundTypeClass.getDescription());
    assertEquals(NEW_TYPE_USAGE, foundTypeClass.getHowused());

    /* ------------------------------------------------------------------ */
    /*  FIND (ALL)                                                        */
    /* ------------------------------------------------------------------ */

    response = target(TYPE_CLASS_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());
    jsonPayload = response.readEntity(String.class);

    TypedescriptionEntity[] typeClassArray = mapper.readValue(jsonPayload, TypedescriptionEntity[].class);
    assertEquals(3, typeClassArray.length);

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    final String UPDATE_TYPE_DESC  = "Update Type Description";
    final String UPDATE_TYPE_USAGE = "Update Type Usage";

    TypedescriptionEntity updatedTypeClass = foundTypeClass;
    updatedTypeClass.setDescription(UPDATE_TYPE_DESC);
    updatedTypeClass.setHowused(UPDATE_TYPE_USAGE);

    // marshal Type description pojo object to JSON String.

    Writer writer = new StringWriter();
    mapper.writeValue(writer, updatedTypeClass);

    response = target(TYPE_CLASS_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(writer.toString()));

    assertEquals(200, response.getStatus());

    // we won't bother to inspect payload because it won't be updated (we
    // didn't mock for this). however, just trying to update and returning
    // a 200 winds through much of the code we want tested.


    // UPDATE #2 should raise a validation exception

    response = target(TYPE_CLASS_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(writer.toString()));

    assertEquals(400, response.getStatus());
    
    // UPDATE #3 should raise a server-side exception

    response = target(TYPE_CLASS_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(writer.toString()));

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

    GlobaltypeEntity newTypeVal = mapper.readValue(jsonPayload, GlobaltypeEntity.class);
    assertEquals(Integer.valueOf(1), newTypeVal.getGlobaltypeid());
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

    GlobaltypeEntity foundTypeVal = mapper.readValue(jsonPayload, GlobaltypeEntity.class);
    assertEquals(Integer.valueOf(1), foundTypeVal.getGlobaltypeid());
    assertEquals(Integer.valueOf(1), foundTypeVal.getTypeid());
    assertEquals("Type Value #1 Short Description", foundTypeVal.getShortdescription());
    assertEquals("TV1", foundTypeVal.getTypecode());

    /* ------------------------------------------------------------------ */
    /*  FIND VALUES FOR TYPE CLASS (BY ATTRIBUTE)                         */
    /* ------------------------------------------------------------------ */

    response = target(TYPE_VALUE_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());
    jsonPayload = response.readEntity(String.class);

    GlobaltypeEntity[] typeValuesForTypeClassArray = mapper.readValue(jsonPayload, GlobaltypeEntity[].class);
    assertEquals(5, typeValuesForTypeClassArray.length);
    for (int i = 0; i < 5; i++) {
      assertEquals(Integer.valueOf(i+1), typeValuesForTypeClassArray[i].getGlobaltypeid());
    }

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    GlobaltypeEntity updatedTypeVal = foundTypeVal;
    updatedTypeVal.setShortdescription("Update Type Value #1 Short Description");

    // marshal Type value pojo object to JSON String.

    Writer writer = new StringWriter();
    mapper.writeValue(writer, updatedTypeVal);

    response = target(TYPE_VALUE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(writer.toString()));

    assertEquals(200, response.getStatus());

    // we won't bother to inspect payload because it won't be updated (we
    // didn't mock for this). however, just trying to update and returning
    // a 200 winds through much of the code we want tested.


    // UPDATE #2 should raise a validation exception

    response = target(TYPE_VALUE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(writer.toString()));

    assertEquals(400, response.getStatus());
    
    // UPDATE #3 should raise a server-side exception

    response = target(TYPE_VALUE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(writer.toString()));

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
