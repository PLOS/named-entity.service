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
package org.plos.namedentity.persist;

import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.ringgold.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})

public class RinggoldDBServiceTest {

  @Autowired RinggoldDBService ringgoldDBService;
  @Autowired @Qualifier("ringgoldDsl") DSLContext context;

  @Test
  public void testFindById() {
    Institution institution = ringgoldDBService.findById(4959, Institution.class);
    assertEquals(Integer.valueOf(4959), institution.getRecId());
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
    assertEquals(24, institutions.size());

    ifilter.setState("CA");
    institutions = ringgoldDBService.findByAttribute(ifilter);
    assertEquals(22, institutions.size());

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
  public void testBubbleCountryToTop() throws Exception {
    Method method = RinggoldDBServiceImpl.class.getDeclaredMethod("bubbleCountryToTop", List.class);
    method.setAccessible(true);

    Institution us_inst = new Institution();
    us_inst.setCountry("US");
    Institution non_us_inst = new Institution();
    non_us_inst.setCountry("NZ");

    ArrayList<Institution> list = new ArrayList<>();
    list.add(non_us_inst);
    list.add(us_inst);

    assertEquals(list.get(0).getCountry(), "NZ");
    assertEquals(list.get(1).getCountry(), "US");

    List<Institution> new_list =  (List<Institution>) method.invoke(ringgoldDBService, list);

    assertEquals(new_list.get(0).getCountry(), "US");
    assertEquals(new_list.get(1).getCountry(), "NZ");
  }

  @Test
  public void testFindByInstitutionName() {
    List<Institution> institutions = ringgoldDBService.findByInstitutionName("Test Group1");
    assertEquals(6, institutions.size());
    for (int i = 0; i < institutions.size(); i++) {
      assertTrue( institutions.get(i).getName().contains("I00"+(i+1)) );
    }
  }

  @Test // PLT-1045
  public void testFindByInstitutionNameWithApostrophe() {
    List<Institution> institutions = ringgoldDBService.findByInstitutionName("Otago Girls' High School");
    assertEquals(1, institutions.size());
    assertEquals("Dunedin", institutions.get(0).getCity());

    institutions = ringgoldDBService.findByInstitutionName("Saint Mary's");
    assertEquals(1, institutions.size());
    assertEquals("Saint Mary's Catholic Elementary School", institutions.get(0).getName());
    assertEquals("NE", institutions.get(0).getState());
    assertEquals("US", institutions.get(0).getCountry());

    institutions = ringgoldDBService.findByInstitutionName("misrad ha'takhbura");
    assertEquals(1, institutions.size());
    assertEquals("Medinat Israel Misrad ha'takhbura ha'tashtiyot ha'leumiyot ve'ha'betikhut be'drakhim", institutions.get(0).getName());
  }

  @Test
  public void testAttributeNameConversion() throws Exception {
    String[] pojoFieldNames = {
      "city",
      "country",
      "name",
      "parentRinggoldId",
      "postCode",
      "recId",
      "ringgoldId",
      "state",
      "topRinggoldId",
      "type"
    };

    String[] expectedDbFieldNames = {
      "city",
      "country",
      "name",
      "parent_ringgold_id",
      "post_code",
      "rec_id",
      "ringgold_id",
      "state",
      "top_ringgold_id",
      "type"
    };

    Method method = RinggoldDBServiceImpl.class.getDeclaredMethod("dbFieldName", String.class);
    method.setAccessible(true);

    for (int i = 0; i < pojoFieldNames.length; i++) {
      assertEquals(expectedDbFieldNames[i], method.invoke(ringgoldDBService, pojoFieldNames[i]));
    }
  }
}
