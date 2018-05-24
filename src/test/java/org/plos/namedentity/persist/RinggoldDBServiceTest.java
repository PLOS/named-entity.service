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
import static java.lang.Integer.valueOf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})

public class RinggoldDBServiceTest {

  @Autowired RinggoldDBService ringgoldDBService;
  @Autowired @Qualifier("ringgoldDsl") DSLContext context;

  @Test
  public void testFindByRinggoldId() {
    Institution ifilter = new Institution();
    ifilter.setRinggoldId(137977);
    List<Institution> institutions = ringgoldDBService.findByAttribute(ifilter);
    assertEquals(1, institutions.size());
    assertEquals( "Johnston County School District Smithfield", institutions.get(0).getName());

    Institution ifilter2 = new Institution();
    ifilter.setRinggoldId(1);   // invalid ringgold id
    assertEquals(0, ringgoldDBService.findByAttribute(ifilter).size());
  }

  @Test
  public void testFindByInstitutionName() {
    List<Institution> institutions = ringgoldDBService.findByInstitutionName("smith");
    assertEquals(9, institutions.size());
    assertEquals(valueOf(142559), institutions.get(0).getRinggoldId());
    assertEquals(valueOf(6476), institutions.get(institutions.size()-1).getRinggoldId());
  }

  @Test // PLT-1045
  public void testFindByInstitutionNameWithApostrophe() {
    List<Institution> institutions = ringgoldDBService.findByInstitutionName("Paul Smith's");
    assertEquals(1, institutions.size());
    assertEquals(valueOf(6476), institutions.get(0).getRinggoldId());
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
