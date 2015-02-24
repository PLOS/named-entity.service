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

import static org.junit.Assert.assertEquals;
import static org.plos.namedentity.api.NedException.ErrorType.*;

import org.junit.Test;
import org.plos.namedentity.api.NedException;
import org.springframework.dao.DuplicateKeyException;

public class NedExceptionTranslatorTest {

  @Test
  public void testTranslateForDupeEmails() {
    String[] dupeEmailMessages = new String[]{
      "Duplicate entry 'todd.troyer@utsa.edu-7' for key 'emailAddress'",

      "jOOQ; SQL [insert into ...]; Duplicate entry 'todd.troyer@utsa.edu-7' for key 'emailAddress';",

      "Unique index or primary key violation: \"CONSTRAINT_INDEX_7A1A ON NAMEDENTITIES.EMAILS(EMAILADDRESS, SOURCETYPEID) VALUES ...\"",

      "jOOQ; SQL [insert into ...]; Unique index or primary key violation: \"CONSTRAINT_INDEX_7A1A " +
      "ON NAMEDENTITIES.EMAILS(EMAILADDRESS, SOURCETYPEID) VALUES ('jane.q.doe.personal@foo.com', 6, 3)\";",
    };

    for (String msg : dupeEmailMessages) {
      DuplicateKeyException dke = new DuplicateKeyException(msg);
      NedException ne = NedExceptionTranslator.translate(dke);
      assertEquals(DupeEmailError, ne.getErrorType());
    }

    DuplicateKeyException dke = new DuplicateKeyException("Not a dupe email");
    NedException ne = NedExceptionTranslator.translate(dke);
    assertEquals(DatabaseError, ne.getErrorType());
  }
}
