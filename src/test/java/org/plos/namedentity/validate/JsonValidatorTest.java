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
package org.plos.namedentity.validate;

import static org.plos.namedentity.validate.JsonValidator.parseJsonObjectAsMap;
import static org.plos.namedentity.validate.JsonValidator.validJson;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

public class JsonValidatorTest {

  @Test
  public void testInvalidJson() {
    String[] badJsons = {
      null,
      "",
      "\"x\":1"
    };
    for (String badjson : badJsons) {
      assertFalse(badjson, JsonValidator.isValid(badjson));
      assertNull(badjson, validJson(badjson));
    }
  }

  @Test
  public void testInvalidButCorrectibleJson() {
    String[] badJsons = {
      "{'x':'Z'}",          // single quoted strings
      "{\"x\":1,\"y\":2,}", // extra comma before closing brace (})
      "[abc,def]"           // strings not quoted
    };
    for (String badjson : badJsons) {
      assertFalse(badjson, JsonValidator.isValid(badjson));
      assertNotNull(validJson(badjson));
    }
  }

  @Test
  public void testEmptyJson() {
    assertNotNull(JsonValidator.isValid("{}"));
    assertNotNull(JsonValidator.isValid("[]"));
  }

  @Test
  public void testOrcidTokenJson() {
    String orcidOAuthJson = 
      "{\"accessToken\":\"a9d2479e-9ff4-470a-a9c2-0b4ff9391cae\"," +
      "\"refreshToken\":\"0c91a121-a6b2-4058-b731-305708159360\"," +
      "\"tokenScope\":\"/orcid-profile/read-limited\","            +
      "\"tokenExpires\":\"2034-06-02T04:48:49Z\","                 +
      "\"lastModified\":\"2014-06-02T08:33:31Z\","                 +
      "\"created\":\"2014-06-02T08:33:31Z\"}";

    assertTrue(orcidOAuthJson, JsonValidator.isValid(orcidOAuthJson));

    String validOrcidOAuthJson = validJson(orcidOAuthJson);
    assertNotNull(validOrcidOAuthJson);

    assertEquals(parseJsonObjectAsMap(orcidOAuthJson),
                 parseJsonObjectAsMap(validOrcidOAuthJson));
  }

  @Test
  public void testSolrQueryJson() {
    String solrQueryJson =
      "{\"query\":\"*:*\",\"unformattedQuery\":\"\",\"volume\":\"\","          +
      "\"eLocationId\":\"\",\"id\":\"\",\"filterSubjects\":[],"                +
      "\"filterSubjectsDisjunction\":[\"Ecology\",\"Conservation science\","   +
      "\"Environmental impacts\",\"Environmental protection\",\"Habitats\","   +
      "\"Environmental management\",\"Sustainability science\","               +
      "\"Population biology\",\"Species interactions\",\"Zoology\"],"          +
      "\"filterAuthors\":[],\"filterKeyword\":\"\",\"filterArticleTypes\":[]," +
      "\"filterJournals\":[\"PLoSONE\"],\"sortKey\":\"\",\"sortValue\":\"\","  +
      "\"startPage\":0,\"pageSize\":0,\"resultView\":\"\"}";

    assertTrue(solrQueryJson, JsonValidator.isValid(solrQueryJson));

    String validSolrQueryJson = validJson(solrQueryJson);
    assertNotNull(validSolrQueryJson);

    assertEquals(parseJsonObjectAsMap(solrQueryJson),
                 parseJsonObjectAsMap(validSolrQueryJson));
  }
}
