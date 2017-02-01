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
package org.plos.namedentity.rest;

import org.junit.Test;

import org.plos.namedentity.api.ringgold.Institution;
import org.plos.namedentity.api.NedErrorResponse;
import org.plos.namedentity.api.NedException;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.Unmarshaller;

import java.lang.reflect.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.plos.namedentity.api.NedException.ErrorType.*;

public class RinggoldResourceTest extends BaseResourceTest {

  private static final String INSTITUTIONSEARCH_URI = "/institutionsearch";

  @Test
  public void testBadInstitutionSearchUrl() throws Exception {

    WebTarget[] badInstitutionSearchUrls = {
      target(INSTITUTIONSEARCH_URI),
      target(INSTITUTIONSEARCH_URI).queryParam("boguskey","bogusvalue")
    };

    for (WebTarget target : badInstitutionSearchUrls) {
      Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();

      assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

      String jsonPayload = response.readEntity(String.class);

      NedErrorResponse ner = unmarshalEntity(jsonPayload, NedErrorResponse.class, 
                                            jsonUnmarshaller(NedErrorResponse.class));

      assertEquals(InvalidInstitutionQuery.getErrorCode(), ner.errorCode);
      assertEquals(InvalidInstitutionQuery.getErrorMessage(), ner.errorMsg);
    }
  }

  @Test
  public void testInstitutionFindNone() throws Exception {

    Response response = target(INSTITUTIONSEARCH_URI).queryParam("substring","stanford typo doh")
                                                     .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Institution.class);
    List<Institution> institutions = unmarshalEntities(jsonPayload, Institution.class, unmarshaller);
    assertEquals(0, institutions.size());
  }

  @Test
  public void testInstitutionFindMany() throws Exception {

    final String  INSTITUTION_SUBSTRING = "Stanford U";

    Response response = target(INSTITUTIONSEARCH_URI).queryParam("substring",INSTITUTION_SUBSTRING)
                                                     .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Institution.class);
    List<Institution> institutions = unmarshalEntities(jsonPayload, Institution.class, unmarshaller);
    assertEquals(24, institutions.size());

    for (Institution institution : institutions) {
      assertTrue(institution.getName().contains(INSTITUTION_SUBSTRING));
    }
  }

  @Test
  public void testInstitutionFindOne() throws Exception {

    final String SUBSTRING = "STANFORD MediCine"; // lookup should be case insensitive

    Response response = target(INSTITUTIONSEARCH_URI).queryParam("substring",SUBSTRING)
                                                     .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);
    // ringgold id (aka, p_code) should render in json as institution id.
    assertTrue(jsonPayload.contains("\"institution-id\""));

    Unmarshaller unmarshaller = jsonUnmarshaller(Institution.class);
    List<Institution> institutions = unmarshalEntities(jsonPayload, Institution.class, unmarshaller);
    assertEquals(1, institutions.size());

    Institution institution = institutions.get(0);
    assertEquals(Integer.valueOf(158423), institution.getRinggoldId());
    assertTrue(institution.getName().toLowerCase().contains("stanford medicine"));
  }

  @Test
  public void testInstitutionFindOrdering() throws Exception {

    final String  INSTITUTION_SUBSTRING = "Test Group1";

    Response response = target(INSTITUTIONSEARCH_URI).queryParam("substring",INSTITUTION_SUBSTRING)
                                                     .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Institution.class);
    List<Institution> institutions = unmarshalEntities(jsonPayload, Institution.class, unmarshaller);
    assertEquals(6, institutions.size());

    for (int i = 0; i < institutions.size(); i++) {
      assertTrue( institutions.get(i).getName().contains("I00"+(i+1)) );
    }
  }
}
