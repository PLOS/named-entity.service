package org.plos.namedentity.rest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.plos.namedentity.api.TypedescriptionsDTO;

public class NamedEntityResourceTest extends SpringContextAwareJerseyTest {

    private static final String TYPE_CLASS_URI = "/ned/typeclasses";

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

        Response response = target(TYPE_CLASS_URI).request(MediaType.APPLICATION_JSON_TYPE)
                                .post(Entity.json(NEW_TYPE_JSON_PAYLOAD));

        assertEquals(200, response.getStatus());

        String jsonPayload = response.readEntity(String.class);

        TypedescriptionsDTO newTypeClass = mapper.readValue(jsonPayload, TypedescriptionsDTO.class);
        assertEquals(Integer.valueOf(1), newTypeClass.getTypeid());
        assertEquals(NEW_TYPE_DESC, newTypeClass.getDescription());
        assertEquals(NEW_TYPE_USAGE, newTypeClass.getHowused());

        /* ------------------------------------------------------------------ */
        /*  FIND                                                              */
        /* ------------------------------------------------------------------ */
        
        response = target(TYPE_CLASS_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());

        jsonPayload = response.readEntity(String.class);

        TypedescriptionsDTO foundTypeClass = mapper.readValue(jsonPayload, TypedescriptionsDTO.class);
        assertEquals(Integer.valueOf(1), foundTypeClass.getTypeid());
        assertEquals(NEW_TYPE_DESC, foundTypeClass.getDescription());
        assertEquals(NEW_TYPE_USAGE, foundTypeClass.getHowused());

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

        jsonPayload = response.readEntity(String.class);

        TypedescriptionsDTO updatedTypeClass2 = mapper.readValue(jsonPayload, TypedescriptionsDTO.class);
        assertEquals(updatedTypeClass, updatedTypeClass2);

        /* ------------------------------------------------------------------ */
        /*  DELETE                                                           */
        /* ------------------------------------------------------------------ */

        response = target(TYPE_CLASS_URI + "/1").request(MediaType.APPLICATION_JSON_TYPE).delete();

        assertEquals(204, response.getStatus());
    }
}
