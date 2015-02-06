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

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.oxm.json.JsonStructureSource;
import org.junit.Test;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Degree;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Globaltype;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Typedescription;
import org.plos.namedentity.api.entity.Uniqueidentifier;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class NamedEntityResourceTest extends SpringContextAwareJerseyTest {

  private static final String TEST_RESOURCE_PATH = "src/test/resources/";

  private static final String TYPE_CLASS_URI = "/typeclasses";
  private static final String TYPE_VALUE_URI = TYPE_CLASS_URI + "/1/typevalues";
  private static final String INDIVIDUAL_URI = "/individuals";

  private static final String INDIV_ADDR_URI   = INDIVIDUAL_URI + "/1/addresses";
  private static final String INDIV_EMAIL_URI  = INDIVIDUAL_URI + "/1/emails";
  private static final String INDIV_PHONE_URI  = INDIVIDUAL_URI + "/1/phonenumbers";
  private static final String INDIV_ROLE_URI   = INDIVIDUAL_URI + "/1/roles";
  private static final String INDIV_UID_URI    = INDIVIDUAL_URI + "/1/uids";
  private static final String INDIV_DEGREE_URI = INDIVIDUAL_URI + "/1/degrees";

  @Test
  public void testCreateIndividualComposite() throws Exception {

    String compositeIndividualJson = new String(Files.readAllBytes(
        Paths.get(TEST_RESOURCE_PATH + "composite-individual.json")));

    Response response = target(INDIVIDUAL_URI).request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(compositeIndividualJson));

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(IndividualComposite.class);
    IndividualComposite composite = unmarshalEntity(jsonPayload, IndividualComposite.class, unmarshaller);

    Individualprofile individualProfile = composite.getIndividualprofiles().get(0);
    assertTrue( individualProfile.getNedid() > 0 );
    assertEquals("Jane", individualProfile.getFirstname());
    assertEquals("Q", individualProfile.getMiddlename());
    assertEquals("Doe", individualProfile.getLastname());
    assertEquals("Mrs.", individualProfile.getNameprefix());
    assertEquals("jane.q.doe.personal@foo.com", composite.getEmails().get(0).getEmailaddress());
  }

  //@Test
  public void testReadIndividualCompositeByNedId() throws Exception {

    Response response = target(INDIVIDUAL_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(IndividualComposite.class);
    IndividualComposite composite = unmarshalEntity(jsonPayload, IndividualComposite.class, unmarshaller);

    Individualprofile individualProfile = composite.getIndividualprofiles().get(0);
    assertEquals(Integer.valueOf(1), individualProfile.getNedid());
    assertEquals("firstname", individualProfile.getFirstname());
    assertEquals("middlename", individualProfile.getMiddlename());
    assertEquals("lastname", individualProfile.getLastname());
    assertEquals("Ms.", individualProfile.getNameprefix());
    assertEquals("email@internet.com", composite.getEmails().get(0).getEmailaddress());
  }

  //@Test
  public void testReadIndividualCompositeByUid() throws Exception {

    Response response = target(INDIVIDUAL_URI + "/Editorial Manager/PONE-579386").request(MediaType.APPLICATION_JSON_TYPE).get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(IndividualComposite.class);
    IndividualComposite composite = unmarshalEntity(jsonPayload, IndividualComposite.class, unmarshaller);

    Individualprofile individualProfile = composite.getIndividualprofiles().get(0);
    assertEquals(Integer.valueOf(1), individualProfile.getNedid());
    assertEquals("firstname", individualProfile.getFirstname());
    assertEquals("middlename", individualProfile.getMiddlename());
    assertEquals("lastname", individualProfile.getLastname());
    assertEquals("Ms.", individualProfile.getNameprefix());
    assertEquals("email@internet.com", composite.getEmails().get(0).getEmailaddress());
  }

  //@Test
  public void testIndividualUpdate() throws Exception {

    final String NEW_FIRSTNAME = "origfirstname";
    final String NEW_LASTNAME = "origlastname";

    final String NEW_INDIVIDUALS_JSON_PAYLOAD = "{"
        + "\"firstname\":\"" + NEW_FIRSTNAME + "\","
        + "\"lastname\":\"" + NEW_LASTNAME + "\""
        + "}";

    Response response = target(INDIVIDUAL_URI + "/1/individualprofiles/1").request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.json(NEW_INDIVIDUALS_JSON_PAYLOAD));

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

  }

  //@Test
  public void testIndividualDelete() {
    assertEquals(Response.Status.NO_CONTENT.getStatusCode(),
        target(INDIVIDUAL_URI + "/1/individualprofiles/1").request().delete().getStatus());
  }

  //@Test
  public void testAddressCrud() throws IOException, JAXBException {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    String requestJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "address.json")));

    Response response = target(INDIV_ADDR_URI).request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(requestJson));

    assertEquals(200, response.getStatus());

    String responseJson = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Address.class);

    Address entity = unmarshalEntity(responseJson, Address.class, unmarshaller);

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
    assertEquals(true, entity.getIsactive());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_ADDR_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.json(writeValueAsString(entity)));

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

    Address address = unmarshalEntity(responseJson, Address.class, unmarshaller);
    assertNotNull(address);

    /* ------------------------------------------------------------------ */
    /*  FIND (BY NED ID)                                                  */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_ADDR_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    List<Address> addresses = unmarshalEntities(responseJson, Address.class, unmarshaller);
    assertEquals(2, addresses.size());

    Address officeAddress = addresses.get(0);
    assertEquals("Office", officeAddress.getType());
    assertEquals("addressline 1", officeAddress.getAddressline1());
    assertEquals("CA", officeAddress.getStatecodetype());
    assertEquals("12345", officeAddress.getPostalcode());

    Address homeAddress = addresses.get(1);
    assertEquals("Home", homeAddress.getType());
    assertEquals("addressline 1.2", homeAddress.getAddressline1());
    assertEquals("ONT", homeAddress.getStatecodetype());
    assertEquals("M4C 1B5", homeAddress.getPostalcode());
  }

  //@Test
  public void testRoleCrud() throws IOException, JAXBException {

    Calendar cal = new GregorianCalendar();   // Jun 30, 2014 00:00:00 local time
    cal.set(2014, (6 - 1), 30, 0, 0, 0);      // month is 0-based, so subtract 1
    cal.set(Calendar.MILLISECOND, 0);
    Date START_DATE = cal.getTime();

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    String requestJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "role.json")));

    Response response = target(INDIV_ROLE_URI).request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(requestJson));

    assertEquals(200, response.getStatus());

    String responseJson = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Role.class);
    Role entity = unmarshalEntity(responseJson, Role.class, unmarshaller);

    assertEquals(Integer.valueOf(1), entity.getId());
    assertEquals(Integer.valueOf(1), entity.getNedid());
    assertEquals("Editorial Manager", entity.getApplicationtype());
    assertEquals("Academic Editor (PLOS ONE)", entity.getType());

    assertEquals(START_DATE, entity.getStartdate());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_ROLE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.json(writeValueAsString(entity)));

    assertEquals(200, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_ROLE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).delete();
    assertEquals(204, response.getStatus());

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ROLE ID (PK))                                            */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_ROLE_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).get();
    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    Role role = unmarshalEntity(responseJson, Role.class, unmarshaller);
    assertNotNull(role);

    /* ------------------------------------------------------------------ */
    /*  FIND (BY NED ID)                                                  */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_ROLE_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    responseJson = response.readEntity(String.class);

    List<Role> roles = unmarshalEntities(responseJson, Role.class, unmarshaller);
    assertEquals(1, roles.size());

    role = roles.get(0);
    assertEquals(Integer.valueOf(1), role.getId());
    assertEquals(Integer.valueOf(1), role.getNedid());
    assertEquals("Editorial Manager", role.getApplicationtype());
    assertEquals("Academic Editor (PLOS ONE)", role.getType());
    assertEquals(START_DATE, role.getStartdate());
  }

  //@Test
  public void testEmailCrud() throws IOException, JAXBException {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    String emailsJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "emails.json")));

    Response response = target(INDIV_EMAIL_URI).request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.json(emailsJson));

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Email.class);
    Email entity = unmarshalEntity(jsonPayload, Email.class, unmarshaller);

    assertEquals(Integer.valueOf(1), entity.getId());
    assertEquals(Integer.valueOf(1), entity.getNedid());
    assertEquals("Work", entity.getType());
    assertEquals("foo.bar.personal@gmail.com", entity.getEmailaddress());
    assertEquals(true, entity.getIsactive());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_EMAIL_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.json(writeValueAsString(entity)));

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

    Email email = unmarshalEntity(jsonPayload, Email.class, unmarshaller);
    assertNotNull(email);

    /* ------------------------------------------------------------------ */
    /*  FIND (BY NED ID)                                                  */
    /* ------------------------------------------------------------------ */

    response = target(INDIV_EMAIL_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    jsonPayload = response.readEntity(String.class);

    List<Email> emails = unmarshalEntities(jsonPayload, Email.class, unmarshaller);
    assertEquals(2, emails.size());

    Email workEmail = emails.get(0);
    assertEquals("Work", workEmail.getType());
    assertEquals("fu.manchu.work@foo.com", workEmail.getEmailaddress());

    /* ------------------------------------------------------------------ */
    /*  404 ERRORS                                                        */
    /* ------------------------------------------------------------------ */

    assertEquals(Response.Status.OK.getStatusCode(),
        target("/individuals/1/emails")
            .request(MediaType.APPLICATION_JSON_TYPE).get().getStatus());

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

  //@Test
  public void testDegreeCrud() throws IOException, JAXBException {

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    Response response = target(INDIV_DEGREE_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Degree.class);
    List<Degree> degrees = unmarshalEntities(jsonPayload, Degree.class, unmarshaller);
    assertEquals(1, degrees.size());

    Degree degree = degrees.get(0);
    assertEquals("Super Doctor", degree.getType());

    //TODO - CREATE, UPDATE, DELETE
  }

  //@Test
  public void testPhonenumberCrud() throws IOException, JAXBException {

    /* ------------------------------------------------------------------ */
    /*  FIND (BY ID)                                                      */
    /* ------------------------------------------------------------------ */

    Response response = target(INDIV_PHONE_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Phonenumber.class);
    List<Phonenumber> phonenumbers = unmarshalEntities(jsonPayload, Phonenumber.class, unmarshaller);
    assertEquals(3, phonenumbers.size());

    Phonenumber officePhone = phonenumbers.get(0);
    assertEquals("Office", officePhone.getType());
    assertEquals("123-456-7890", officePhone.getPhonenumber());

    //TODO - CREATE, UPDATE, DELETE
  }

  //@Test
  public void testExternalReferenceLookup() throws IOException, JAXBException {

    /* ------------------------------------------------------------------ */
    /*  FIND EXTERNAL REFERENCES FOR INDIVIDUAL                           */
    /* ------------------------------------------------------------------ */

    Response response = target(INDIV_UID_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(200, response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    List<Uniqueidentifier> uids = unmarshalEntities(jsonPayload, Uniqueidentifier.class,
        jsonUnmarshaller(Uniqueidentifier.class));
    assertEquals(5, uids.size());

    /* ------------------------------------------------------------------ */
    /*  FIND INDIVIDUAL BY EXTERNAL REFERENCE                             */
    /* ------------------------------------------------------------------ */

    String[] uidTypes = new String[] {
      "ORCID", "Editorial Manager", "CAS", "Salesforce", "Ambra"
    };

    String[] uidValues = new String[] {
      "0000-0002-9430-319X",
      "PONE-579386",
      "3BBFE34C8EEF46FEA1E0DA3339DF1EC9",
      "001U0000008Qlfj",
      "421649"
    };

    for (int i = 0; i < uidTypes.length; i++) {
      response = target(INDIVIDUAL_URI + "/"+uidTypes[i]+"/"+uidValues[i])
          .request(MediaType.APPLICATION_JSON_TYPE).get();

      assertEquals(200, response.getStatus());
      jsonPayload = response.readEntity(String.class);

      IndividualComposite individualComposite = unmarshalEntity(jsonPayload, 
        IndividualComposite.class, jsonUnmarshaller(IndividualComposite.class));

      assertNotNull(individualComposite);
    }

    // invalid uid lookup
    response = target(INDIVIDUAL_URI + "/BOGUS_TYPE/BOGUS_VALUE")
        .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(404, response.getStatus());
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
    /*  DELETE                                                           */
    /* ------------------------------------------------------------------ */

    response = target(typeValueUri+"/"+newTypeValueId).request(MediaType.APPLICATION_JSON_TYPE).delete();
    assertEquals(204, response.getStatus());
  }

  private Integer findTypeClassIdByName(String name) throws JAXBException {
    Response response  = target(TYPE_CLASS_URI).request(MediaType.APPLICATION_JSON_TYPE).get();
    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Typedescription.class);
    List<Typedescription> typedescriptions = unmarshalEntities(jsonPayload, Typedescription.class, unmarshaller);

    for (Typedescription typeclass : typedescriptions) {
      if (typeclass.getDescription().equals(name)) {
        return typeclass.getId();
      }
    }
    fail("Unable to find TypeClass:"+name);
    return null;
  }

  private <T> JAXBContext jsonJaxbContext(Class<T> clazz) throws JAXBException {
    Map<String, Object> properties = new HashMap<String, Object>(2);
    properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
    properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
    return JAXBContext.newInstance(new Class[]{clazz}, properties);
  }

  private <T> Unmarshaller jsonUnmarshaller(Class<T> clazz) throws JAXBException {
    JAXBContext jc = jsonJaxbContext(clazz);
    return jc.createUnmarshaller();
  }

  private <T> Marshaller jsonMarshaller(Class<T> clazz) throws JAXBException {
    JAXBContext jc = jsonJaxbContext(clazz);
    return jc.createMarshaller();
  }

  private <T> T unmarshalEntity(String json, Class<T> clazz, Unmarshaller unmarshaller)
      throws JAXBException {
    return unmarshaller.unmarshal(new StreamSource(new StringReader(json)), clazz).getValue();
  }

  @SuppressWarnings("unchecked")
  private <T> List<T> unmarshalEntities(String json, Class<T> clazz, Unmarshaller unmarshaller)
      throws JAXBException {
    JsonReader jsonReader = Json.createReader(new StringReader(json));
    JsonArray jsonArray = jsonReader.readArray();
    JsonStructureSource arraySource = new JsonStructureSource(jsonArray);
    return (List<T>) unmarshaller.unmarshal(arraySource, clazz).getValue();
  }

  private <T> String writeValueAsString(T t) throws JAXBException {
    Writer writer = new StringWriter();
    Marshaller marshaller = jsonMarshaller(t.getClass());
    marshaller.marshal(t, writer);
    return writer.toString();
  }
}
