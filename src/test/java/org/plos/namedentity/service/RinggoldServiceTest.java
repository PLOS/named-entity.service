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
    Institution institution = ringgoldService.findById(22737, Institution.class);
    assertEquals(Integer.valueOf(22737), institution.getRecId());
    assertEquals(Integer.valueOf(25985), institution.getRinggoldId()); 
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
    assertEquals(Integer.valueOf(161839), institution.getRecId());
    assertEquals(Integer.valueOf(174507), institution.getRinggoldId()); 
    assertEquals("Stanford University Press", institution.getName());
    assertEquals("Palo Alto", institution.getCity());
    assertEquals("CA", institution.getState());
    assertEquals("US", institution.getCountry());
    assertEquals("academic/corporate", institution.getType());

    ifilter.setName("bogus name not in db");
    assertEquals(0, ringgoldService.findByAttribute(ifilter).size());
  }

  @Test
  public void testFindByInstitutionName() {
    List<Institution> institutions = ringgoldService.findByInstitutionName("Test Group1");
    assertEquals(6, institutions.size());

    Institution institution = institutions.get(0);
    assertEquals(Integer.valueOf(1000001), institution.getRecId());
    assertEquals(Integer.valueOf(1000001), institution.getRinggoldId());
    assertEquals("Test Group1 I001", institution.getName());
    assertEquals("San Francisco", institution.getCity());
    assertEquals("CA", institution.getState());
    assertEquals("US", institution.getCountry());
    assertEquals("academic/medsch", institution.getType());

    assertEquals(0, ringgoldService.findByInstitutionName("bogus name not in db").size());
  }
}
