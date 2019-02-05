/*
 * Copyright (c) 2014-2019 Public Library of Science
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

import org.junit.Assert;
import org.junit.Test;
import org.plos.namedentity.api.NedErrorResponse;
import org.plos.namedentity.api.entity.Alert;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.Unmarshaller;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidTypeValue;

/**
 * Created by jfinger on 12/23/15.
 */
public class QueriesResourceTest extends BaseResourceTest {

  private static final String BASE_URI = "/queries";

  @Test
  public void testAlerts() throws Exception {

    Unmarshaller unmarshaller = jsonUnmarshaller(Alert.class);

    // empty query

    Response response = target(BASE_URI + "/alerts")
        .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String jsonPayload = response.readEntity(String.class);
    System.out.println(jsonPayload);

    List<Alert> results = unmarshalEntities(jsonPayload, Alert.class, unmarshaller);
    Assert.assertEquals(2, results.size());
    Assert.assertTrue(jsonPayload.contains("alert two"));


    // frequency param with no results

    response = target(BASE_URI + "/alerts").queryParam("frequency", "monthly")
        .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    results = unmarshalEntities(response.readEntity(String.class), Alert.class, unmarshaller);
    Assert.assertEquals(0, results.size());


    // bad frequency

    response = target(BASE_URI + "/alerts").queryParam("frequency", "fortnightly")
        .request(MediaType.APPLICATION_JSON_TYPE).get();

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    jsonPayload = response.readEntity(String.class);

    NedErrorResponse ner = unmarshalEntity(jsonPayload, NedErrorResponse.class,
        jsonUnmarshaller(NedErrorResponse.class));

    assertEquals(InvalidTypeValue.getErrorCode(), ner.errorCode);


//    // journal query
//
//    response = target(BASE_URI + "/alerts").queryParam("journal", "PLOS Biology")
//        .request(MediaType.APPLICATION_JSON_TYPE).get();
//
//    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//    results = unmarshalEntities(response.readEntity(String.class), Alert.class, unmarshaller);
//    Assert.assertEquals(2, results.size());


//    // journal query two
//
//    response = target(BASE_URI + "/alerts").queryParam("frequency", "weekly").queryParam("journal", "PLOS ONE")
//        .request(MediaType.APPLICATION_JSON_TYPE).get();
//
//    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//    results = unmarshalEntities(response.readEntity(String.class), Alert.class, unmarshaller);
//    Assert.assertEquals(1, results.size());
  }
}
