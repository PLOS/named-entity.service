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
    final Integer NEW_INSTITUTION_RESULT_LIMIT = 5;

    Response response = target(INSTITUTIONSEARCH_URI).queryParam("substring",INSTITUTION_SUBSTRING)
                                                     .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Institution.class);
    List<Institution> institutions = unmarshalEntities(jsonPayload, Institution.class, unmarshaller);
    assertEquals(25, institutions.size());

    for (Institution institution : institutions) {
      assertTrue(institution.getName().contains(INSTITUTION_SUBSTRING));
    }

    // results should be truncated if exceed limit. test this with a smaller limit.

    Field institutionsResultLimit = RinggoldResource.class.getDeclaredField("INSTITUTIONS_RESULT_LIMIT");
    institutionsResultLimit.set(null, NEW_INSTITUTION_RESULT_LIMIT);

    response = target(INSTITUTIONSEARCH_URI).queryParam("substring",INSTITUTION_SUBSTRING)
                                            .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    jsonPayload = response.readEntity(String.class);

    institutions = unmarshalEntities(jsonPayload, Institution.class, unmarshaller);
    assertEquals(NEW_INSTITUTION_RESULT_LIMIT, Integer.valueOf(institutions.size()));
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
    assertEquals(Integer.valueOf(158423), institution.getPCode());
    assertTrue(institution.getName().toLowerCase().contains("stanford medicine"));
  }
}
