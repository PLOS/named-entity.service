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
