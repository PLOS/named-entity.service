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
  public void testFindByRinggoldId() {
    Institution ifilter = new Institution() ; ifilter.setRinggoldId(10000);

    List<Institution> institutions = ringgoldService.findByAttribute(ifilter);
    assertEquals(1, institutions.size());
    assertEquals("institution-3", institutions.get(0).getName());

    ifilter.setRinggoldId(1);  // invalid ringgold id
    assertEquals(0, ringgoldService.findByAttribute(ifilter).size());
  }

  @Test
  public void testFindByInstitutionName() {
    List<Institution> institutions = ringgoldService.findByInstitutionName("smithfield");
    assertEquals(2, institutions.size());

    Institution institution = institutions.get(0);
    assertEquals(Integer.valueOf(142559), institution.getRinggoldId());
    assertEquals("Smithfield Foods Inc", institution.getName());

    assertEquals(0, ringgoldService.findByInstitutionName("bogus name not in db").size());
  }
}
