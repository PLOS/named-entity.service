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
import org.plos.namedentity.persist.NamedEntityDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidTypeClass;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidTypeValue;
import static org.plos.namedentity.api.NedException.ErrorType.ServerError;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})
public class NedExceptionServiceTest {

  @Autowired
  CrudService crudService;

  @Autowired
  NamedEntityDBService nedDBSvc;

  @Test
  public void testInvalidTypeClassException() {

    Integer     typeClassID      = null;
    Set<String> validTypeClasses = null;

    try {
      typeClassID = nedDBSvc.findTypeClass("Named Party Typos");
      fail();
    } catch (NedException nve) {  // expected
      assertEquals(InvalidTypeClass, nve.getErrorType());

      validTypeClasses = nve.getAcceptableValues();
    }

    assertTrue(validTypeClasses.size() > 1);

    for (String typeclass : validTypeClasses) {
      // finder will thrown an exception if class not found
      assertTrue( nedDBSvc.findTypeClass(typeclass) > 0 );
    }
  }

  @Test
  public void testInvalidTypeValueException() {

    Integer     typeClassID     = null;
    Integer     typeValueID     = null;
    Set<String> validTypeValues = null;

    try {
      typeClassID = nedDBSvc.findTypeClass("Named Party Types");
      typeValueID = nedDBSvc.findTypeValue(typeClassID, "Indix");
      fail();
    } catch (NedException nve) {  // expected
      assertEquals(InvalidTypeValue, nve.getErrorType());
      validTypeValues = nve.getAcceptableValues();
    }

    assertTrue(validTypeValues.size() > 1);

    for (String typevalue : validTypeValues) {
      // finder will thrown an exception if class not found
      assertTrue( nedDBSvc.findTypeValue(typeClassID, typevalue) > 0 );
    }
  }

  @Test
  public void testNedExceptionInstantiations() {
    NedException ne = new NedException(ServerError, "server error", new java.nio.BufferOverflowException());
    assertTrue( ne.getCause() instanceof java.nio.BufferOverflowException );
    assertEquals("server error", ne.getDetailedMessage());
    assertEquals(ServerError, ne.getErrorType());
    assertNull( ne.getAcceptableValues() );
  }
}
