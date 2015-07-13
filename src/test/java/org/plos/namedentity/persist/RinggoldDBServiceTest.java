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
package org.plos.namedentity.persist;

import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.ringgold.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.lang.reflect.Method;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})

public class RinggoldDBServiceTest {

  @Autowired RinggoldDBService ringgoldDBService;
  @Autowired DSLContext        context;

  @Test
  public void testFindById() {
    Institution institution = ringgoldDBService.findById(1, Institution.class);
    assertEquals(Integer.valueOf(1), institution.getRecId());
    assertEquals("Stanford University", institution.getName());
    assertEquals("Stanford", institution.getCity());
    assertEquals("CA", institution.getState());
    assertEquals("US", institution.getCountry());
    assertEquals("academic", institution.getType());

    assertNull( ringgoldDBService.findById(9999, Institution.class) );
  }

  @Test
  public void testFindByAttribute() {
    Institution ifilter = new Institution();
    ifilter.setName("Stanford U");
    List<Institution> institutions = ringgoldDBService.findByAttribute(ifilter);
    assertEquals(25, institutions.size());

    ifilter.setState("CA");
    institutions = ringgoldDBService.findByAttribute(ifilter);
    assertEquals(23, institutions.size());

    ifilter.setType("academic/earth");
    institutions = ringgoldDBService.findByAttribute(ifilter);
    assertEquals(3, institutions.size());

    ifilter.setState("VOODOO");
    institutions = ringgoldDBService.findByAttribute(ifilter);
    assertEquals(0, institutions.size());

    Institution ifilter2 = new Institution();
    ifilter2.setName("STANFORD Medicine");
    institutions = ringgoldDBService.findByAttribute(ifilter2);
    assertEquals(1, institutions.size());
  }

  @Test
  public void testInstitutionNameWhereCondition() throws Exception {
    Method method = RinggoldDBServiceImpl.class.getDeclaredMethod("institutionNameWhereCondition", String.class, String.class);
    method.setAccessible(true);

    String result = (String) method.invoke(ringgoldDBService, "name", "foo" ); //when one string
    assertTrue(result.contains("REGEX"));

    result = (String) method.invoke(ringgoldDBService, "name", "foo bar" ); //when 2+ strings
    assertTrue(result.contains("LIKE"));
  }
}
