package org.plos.namedentity.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.plos.namedentity.api.dto.AddressDTO;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.dto.GlobaltypeDTO;
import org.plos.namedentity.api.dto.IndividualDTO;
import org.plos.namedentity.api.dto.PhonenumberDTO;
import org.plos.namedentity.api.dto.RoleDTO;
import org.plos.namedentity.api.dto.TypedescriptionDTO;
import org.plos.namedentity.api.entity.IndividualEntity;

public class NamedEntityResourceTest extends SpringContextAwareJerseyTest {

    private static final String TEST_RESOURCE_PATH = "src/test/resources/";

    private static final String TYPE_CLASS_URI  = "/ned/typeclasses";
    private static final String TYPE_VALUE_URI  = TYPE_CLASS_URI + "/1/typevalues";
    private static final String INDIVIDUAL_URI  = "/ned/individuals";
    private static final String INDIV_ADDR_URI  = INDIVIDUAL_URI + "/1/addresses";
    private static final String INDIV_EMAIL_URI = INDIVIDUAL_URI + "/1/emails";
    private static final String INDIV_PHONE_URI = INDIVIDUAL_URI + "/1/phonenumbers";
    private static final String INDIV_ROLE_URI  = INDIVIDUAL_URI + "/1/roles";

    private ObjectMapper mapper;

    @Before
    public void setup() {
        this.mapper = new ObjectMapper();
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

        IndividualDTO dto = mapper.readValue(jsonPayload, IndividualDTO.class);
        assertEquals(Integer.valueOf(1), dto.getNamedentityid());
        assertEquals("firstname", dto.getFirstname());
        assertEquals("middlename", dto.getMiddlename());
        assertEquals("lastname", dto.getLastname());
        assertEquals("Mr.", dto.getNameprefix());

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

        IndividualDTO dto = mapper.readValue(jsonPayload, IndividualDTO.class);
        assertEquals(Integer.valueOf(1), dto.getNamedentityid());
        assertEquals("firstname", dto.getFirstname());
        assertEquals("middlename", dto.getMiddlename());
        assertEquals("lastname", dto.getLastname());
        assertEquals("Mr.", dto.getNameprefix());

        /* ------------------------------------------------------------------ */
        /*  FIND (ALL)                                                        */
        /* ------------------------------------------------------------------ */

        response = target(INDIVIDUAL_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());
        jsonPayload = response.readEntity(String.class);

        IndividualEntity[] individualEntityArray = mapper.readValue(jsonPayload, IndividualEntity[].class); 
        assertEquals(3, individualEntityArray.length);

        //TODO - CREATE, UPDATE, DELETE
    }

    @Test
    public void testAddressCrud() throws IOException {

        /* ------------------------------------------------------------------ */
        /*  FIND (BY ID)                                                      */
        /* ------------------------------------------------------------------ */

        Response response = target(INDIV_ADDR_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());

        String jsonPayload = response.readEntity(String.class);

        AddressDTO[] addresses = mapper.readValue(jsonPayload, AddressDTO[].class);
        assertEquals(1, addresses.length);

        AddressDTO address = addresses[0];
        assertEquals("Office", address.getAddresstype());
        assertEquals("city", address.getCity());
        assertEquals("1234567", address.getPostalcode());
        assertTrue( address.getIsprimary() );

        //TODO - CREATE, UPDATE, DELETE
    }

    @Test
    public void testEmailCrud() throws IOException {

        /* ------------------------------------------------------------------ */
        /*  FIND (BY ID)                                                      */
        /* ------------------------------------------------------------------ */

        Response response = target(INDIV_EMAIL_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());

        String jsonPayload = response.readEntity(String.class);

        EmailDTO[] emails = mapper.readValue(jsonPayload, EmailDTO[].class);
        assertEquals(2, emails.length);

        EmailDTO email = emails[0];
        assertEquals("Work", email.getEmailtype());
        assertEquals("fu.manchu.work@foo.com", email.getEmailaddress());
        assertTrue( email.getIsprimary() );

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

        PhonenumberDTO[] phonenumbers = mapper.readValue(jsonPayload, PhonenumberDTO[].class);
        assertEquals(3, phonenumbers.length);

        PhonenumberDTO officePhone = phonenumbers[0];
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

        RoleDTO[] roles = mapper.readValue(jsonPayload, RoleDTO[].class);
        assertEquals(1, roles.length);

        RoleDTO role = roles[0];
        assertEquals("Author", role.getRoletype());

        //TODO - CREATE, UPDATE, DELETE
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

        TypedescriptionDTO newTypeClass = mapper.readValue(jsonPayload, TypedescriptionDTO.class);
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

        TypedescriptionDTO foundTypeClass = mapper.readValue(jsonPayload, TypedescriptionDTO.class);
        assertEquals(Integer.valueOf(1), foundTypeClass.getTypeid());
        assertEquals(NEW_TYPE_DESC, foundTypeClass.getDescription());
        assertEquals(NEW_TYPE_USAGE, foundTypeClass.getHowused());

        /* ------------------------------------------------------------------ */
        /*  FIND (ALL)                                                        */
        /* ------------------------------------------------------------------ */

        response = target(TYPE_CLASS_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());
        jsonPayload = response.readEntity(String.class);

        TypedescriptionDTO[] typeClassArray = mapper.readValue(jsonPayload, TypedescriptionDTO[].class); 
        assertEquals(3, typeClassArray.length);

        /* ------------------------------------------------------------------ */
        /*  UPDATE                                                            */
        /* ------------------------------------------------------------------ */

        final String UPDATE_TYPE_DESC  = "Update Type Description";
        final String UPDATE_TYPE_USAGE = "Update Type Usage";

        TypedescriptionDTO updatedTypeClass = foundTypeClass;
        updatedTypeClass.setDescription(UPDATE_TYPE_DESC);
        updatedTypeClass.setHowused(UPDATE_TYPE_USAGE);

        // marshal Type description DTO object to JSON String.

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

        GlobaltypeDTO newTypeVal = mapper.readValue(jsonPayload, GlobaltypeDTO.class);
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

        GlobaltypeDTO foundTypeVal = mapper.readValue(jsonPayload, GlobaltypeDTO.class);
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

        GlobaltypeDTO[] typeValuesForTypeClassArray = mapper.readValue(jsonPayload, GlobaltypeDTO[].class); 
        assertEquals(5, typeValuesForTypeClassArray.length);
        for (int i = 0; i < 5; i++) {
            assertEquals(Integer.valueOf(i+1), typeValuesForTypeClassArray[i].getGlobaltypeid());
        }

        /* ------------------------------------------------------------------ */
        /*  UPDATE                                                            */
        /* ------------------------------------------------------------------ */

        GlobaltypeDTO updatedTypeVal = foundTypeVal;
        updatedTypeVal.setShortdescription("Update Type Value #1 Short Description");

        // marshal Type value DTO object to JSON String.

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
