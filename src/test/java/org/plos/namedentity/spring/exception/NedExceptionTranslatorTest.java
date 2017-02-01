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
package org.plos.namedentity.spring.exception;

import org.junit.Test;
import org.plos.namedentity.api.NedException;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.Assert.assertEquals;
import static org.plos.namedentity.api.NedException.ErrorType.DatabaseError;
import static org.plos.namedentity.api.NedException.ErrorType.DupeDisplaynameError;
import static org.plos.namedentity.api.NedException.ErrorType.DupeEmailError;
import static org.plos.namedentity.api.NedException.ErrorType.DupeLegalnameError;
import static org.plos.namedentity.api.NedException.ErrorType.DupeUidError;

public class NedExceptionTranslatorTest {

  @Test
  public void testTranslateForDupeEmails() {
    String[] dupeMessages = new String[]{
      "Duplicate entry 'xxx@utsa.edu-7' for key 'emailAddress'",
      "jOOQ; SQL [insert into ...]; Duplicate entry 'xxx@utsa.edu-7' for key 'emailAddress';",
    };

    for (String msg : dupeMessages) {
      DataIntegrityViolationException dke = new DataIntegrityViolationException(msg);
      NedException ne = NedExceptionTranslator.translate(dke);
      assertEquals(DupeEmailError, ne.getErrorType());
    }

    DataIntegrityViolationException dke = new DataIntegrityViolationException("Not a dupe");
    NedException ne = NedExceptionTranslator.translate(dke);
    assertEquals(DatabaseError, ne.getErrorType());
  }

  @Test
  public void testTranslateForDupeDisplaynames() {

    String[] dupeMessages = new String[]{
        "jOOQ; SQL [insert into ...]; Duplicate entry 'xxx' for key 'displayName';",
    };

    for (String msg : dupeMessages) {
      DataIntegrityViolationException dke = new DataIntegrityViolationException(msg);
      NedException ne = NedExceptionTranslator.translate(dke);
      assertEquals(DupeDisplaynameError, ne.getErrorType());
    }

    DataIntegrityViolationException dke = new DataIntegrityViolationException("Not a dupe");
    NedException ne = NedExceptionTranslator.translate(dke);
    assertEquals(DatabaseError, ne.getErrorType());

  }

  @Test
  public void testTranslateForDupeLegalnames() {

    String[] dupeMessages = new String[]{
        "jOOQ; SQL [insert into ...]; Duplicate entry 'xxx' for key 'legalName';",
    };

    for (String msg : dupeMessages) {
      DataIntegrityViolationException dke = new DataIntegrityViolationException(msg);
      NedException ne = NedExceptionTranslator.translate(dke);
      assertEquals(DupeLegalnameError, ne.getErrorType());
    }

    DataIntegrityViolationException dke = new DataIntegrityViolationException("Not a dupe");
    NedException ne = NedExceptionTranslator.translate(dke);
    assertEquals(DatabaseError, ne.getErrorType());
  }

  @Test
  public void testTranslateForDupeUIDs() {

    String[] dupeMessages = new String[]{
        "jOOQ; SQL [insert into ...]; Duplicate entry 'xxx' for key 'uniqueIdentifier';",
    };

    for (String msg : dupeMessages) {
      DataIntegrityViolationException dke = new DataIntegrityViolationException(msg);
      NedException ne = NedExceptionTranslator.translate(dke);
      assertEquals(DupeUidError, ne.getErrorType());
    }

    DataIntegrityViolationException dke = new DataIntegrityViolationException("Not a dupe");
    NedException ne = NedExceptionTranslator.translate(dke);
    assertEquals(DatabaseError, ne.getErrorType());
  }

}
