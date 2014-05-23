package org.plos.namedentity.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.plos.namedentity.api.GlobaltypesDTO;
import org.plos.namedentity.api.TypedescriptionsDTO;

public class NamedEntityResourceTest extends SpringContextAwareJerseyTest {

    private static final String TYPE_CLASS_URI = "/ned/typeclasses";
    private static final String TYPE_VALUE_URI = TYPE_CLASS_URI + "/1/typevalues"; 

    private ObjectMapper mapper;

    @Before
    public void setup() {
        this.mapper = new ObjectMapper();
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

        TypedescriptionsDTO newTypeClass = mapper.readValue(jsonPayload, TypedescriptionsDTO.class);
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

        TypedescriptionsDTO foundTypeClass = mapper.readValue(jsonPayload, TypedescriptionsDTO.class);
        assertEquals(Integer.valueOf(1), foundTypeClass.getTypeid());
        assertEquals(NEW_TYPE_DESC, foundTypeClass.getDescription());
        assertEquals(NEW_TYPE_USAGE, foundTypeClass.getHowused());

        /* ------------------------------------------------------------------ */
        /*  FIND (ALL)                                                        */
        /* ------------------------------------------------------------------ */

        response = target(TYPE_CLASS_URI).request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());
        jsonPayload = response.readEntity(String.class);

        TypedescriptionsDTO[] typeClassArray = mapper.readValue(jsonPayload, TypedescriptionsDTO[].class); 
        assertEquals(3, typeClassArray.length);

        /* ------------------------------------------------------------------ */
        /*  UPDATE                                                            */
        /* ------------------------------------------------------------------ */

        final String UPDATE_TYPE_DESC  = "Update Type Description";
        final String UPDATE_TYPE_USAGE = "Update Type Usage";

        TypedescriptionsDTO updatedTypeClass = foundTypeClass;
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

        GlobaltypesDTO newTypeVal = mapper.readValue(jsonPayload, GlobaltypesDTO.class);
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

        GlobaltypesDTO foundTypeVal = mapper.readValue(jsonPayload, GlobaltypesDTO.class);
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

        GlobaltypesDTO[] typeValuesForTypeClassArray = mapper.readValue(jsonPayload, GlobaltypesDTO[].class); 
        assertEquals(5, typeValuesForTypeClassArray.length);
        for (int i = 0; i < 5; i++) {
            assertEquals(Integer.valueOf(i+1), typeValuesForTypeClassArray[i].getGlobaltypeid());
        }

        /* ------------------------------------------------------------------ */
        /*  UPDATE                                                            */
        /* ------------------------------------------------------------------ */

        GlobaltypesDTO updatedTypeVal = foundTypeVal;
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
