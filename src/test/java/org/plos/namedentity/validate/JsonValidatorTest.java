/*
 * Copyright (c) 2006-2015 by Public Library of Science
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
package org.plos.namedentity.validate;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/ambra-spring-beans.xml","/spring-beans.test.xml","/ambra-spring-beans.test.xml"})
public class JsonValidatorTest {

  @Autowired JsonValidator jsonValidator;

  @Test
  public void testInvalidJson() {
    String[] badJsons = {
      null,
      "",
      "\"x\":1",
      "{'x':'Z'}",          // single quoted strings
      "{\"x\":1,\"y\":2,}", // extra comma before closing brace (})
      "[abc,def]"           // strings not quoted
    };
    for (String badjson : badJsons) {
      assertFalse(badjson, jsonValidator.isJSONValid(badjson));
    }
  }

  @Test
  public void testEmptyJson() {
    assertNotNull(jsonValidator.isJSONValid("{}"));
    assertNotNull(jsonValidator.isJSONValid("[]"));
  }

  @Test
  public void testOrcidTokenJson() {
    assertTrue(jsonValidator.isJSONValid(
      "{\"accessToken\":\"a9d2479e-9ff4-470a-a9c2-0b4ff9391cae\"," +
      "\"refreshToken\":\"0c91a121-a6b2-4058-b731-305708159360\"," +
      "\"tokenScope\":\"/orcid-profile/read-limited\","            +
      "\"tokenExpires\":\"2034-06-02T04:48:49Z\","                 +
      "\"lastModified\":\"2014-06-02T08:33:31Z\","                 +
      "\"created\":\"2014-06-02T08:33:31Z\"}"
      ));
  }

  @Test
  public void testSolrQueryJson() {
    assertTrue(jsonValidator.isJSONValid(
      "{\"query\":\"*:*\",\"unformattedQuery\":\"\",\"volume\":\"\","          +
      "\"eLocationId\":\"\",\"id\":\"\",\"filterSubjects\":[],"                +
      "\"filterSubjectsDisjunction\":[\"Ecology\",\"Conservation science\","   +
      "\"Environmental impacts\",\"Environmental protection\",\"Habitats\","   +
      "\"Environmental management\",\"Sustainability science\","               +
      "\"Population biology\",\"Species interactions\",\"Zoology\"],"          +
      "\"filterAuthors\":[],\"filterKeyword\":\"\",\"filterArticleTypes\":[]," +
      "\"filterJournals\":[\"PLoSONE\"],\"sortKey\":\"\",\"sortValue\":\"\","  +
      "\"startPage\":0,\"pageSize\":0,\"resultView\":\"\"}"
      ));
  }
}
