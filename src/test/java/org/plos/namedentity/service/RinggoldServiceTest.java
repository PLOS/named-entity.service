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
package org.plos.namedentity.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.ringgold.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.plos.namedentity.api.NedException.ErrorType.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})
public class RinggoldServiceTest {

  @Autowired
  RinggoldService ringgoldService;

  @Test
  public void testFindById() {
    Institution institution = ringgoldService.findById(5, Institution.class);
    assertEquals(Integer.valueOf(5), institution.getRecId());
    assertEquals(Integer.valueOf(25985), institution.getPCode()); 
    assertEquals("Stanford University Stanford Law School", institution.getName());
    assertEquals("Stanford", institution.getCity());
    assertEquals("CA", institution.getState());
    assertEquals("US", institution.getCountry());
    assertEquals("academic/law", institution.getType());

    try {
      ringgoldService.findById(99999, Institution.class);
    }
    catch (NedException expected) {
      assertEquals(EntityNotFound, expected.getErrorType());
    }
  }

  @Test
  public void testFindByAttribute() {
    Institution ifilter = new Institution() ; ifilter.setName("university press");

    List<Institution> institutions = ringgoldService.findByAttribute(ifilter);
    assertEquals(1, institutions.size());
    
    Institution institution = institutions.get(0);
    assertEquals(Integer.valueOf(12), institution.getRecId());
    assertEquals(Integer.valueOf(174507), institution.getPCode()); 
    assertEquals("Stanford University Press", institution.getName());
    assertEquals("Palo Alto", institution.getCity());
    assertEquals("CA", institution.getState());
    assertEquals("US", institution.getCountry());
    assertEquals("academic/corporate", institution.getType());

    ifilter.setName("bogus name not in db");
    assertEquals(0, ringgoldService.findByAttribute(ifilter).size());
  }
}
