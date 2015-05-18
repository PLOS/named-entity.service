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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})
public class RinggoldDBServiceTest {

  @Autowired RinggoldDBService ringgoldDBSvc;
  @Autowired DSLContext        context;

  @Test
  public void testFindById() {
    Institution institution = ringgoldDBSvc.findById(1, Institution.class);
    assertEquals(Integer.valueOf(1), institution.getRecId());
    assertEquals("Stanford University", institution.getName());
    assertEquals("Stanford", institution.getCity());
    assertEquals("CA", institution.getState());
    assertEquals("US", institution.getCountry());
    assertEquals("academic", institution.getType());

    assertNull( ringgoldDBSvc.findById(9999, Institution.class) );
  }

  @Test
  public void testFindByAttribute() {
    Institution ifilter = new Institution();
    ifilter.setName("stanford");
    List<Institution> institutions = ringgoldDBSvc.findByAttribute(ifilter);
    assertEquals(30, institutions.size());

    ifilter.setState("CA");
    institutions = ringgoldDBSvc.findByAttribute(ifilter);
    assertEquals(27, institutions.size());

    ifilter.setType("academic/earth");
    institutions = ringgoldDBSvc.findByAttribute(ifilter);
    assertEquals(3, institutions.size());

    ifilter.setState("VOODOO");
    institutions = ringgoldDBSvc.findByAttribute(ifilter);
    assertEquals(0, institutions.size());

    Institution ifilter2 = new Institution();
    ifilter2.setName("STANFORD Medicine");
    institutions = ringgoldDBSvc.findByAttribute(ifilter2);
    assertEquals(1, institutions.size());
  }
}
