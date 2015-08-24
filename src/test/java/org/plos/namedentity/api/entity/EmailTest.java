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
package org.plos.namedentity.api.entity;

import org.junit.Test;
import org.plos.namedentity.api.NedException;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class EmailTest {

  @Test
  public void testValidEmailValidation() {
    String[] validEmails = {
      "niceandsimple@example.com",
      "very.common@example.com",
      "a.little.lengthy.but.fine@dept.example.com",
      "disposable.style.email.with+symbol@example.com",
      "other.email-with-dash@example.com",
      "\"much.more unusual\"@example.com",
      "\"very.unusual.@.unusual.com\"@example.com",
      // "admin@mailserver1",   // local domain name with no TLD. this is legal.
      "#!$%&'*+-/=?^_`{}|~@example.org",
      "\"()<>[]:,;@\\\"!#$%&'*+-/=?^_`{}| ~.a\"@example.org",
      "\" \"@example.org",      // space between the quotes
      "üñîçøðé@example.com",    // unicode characters in local part
      "üñîçøðé@üñîçøðé.com",    // unicode characters in domain part
      "0123456789012345678901234567890123456789012345678901234567890123@012345678901234567890123456789012345678901234567890123456789012.com",
    };

    for (String email : validEmails) {
      assertTrue( Email.validateEmail(email) );
    }
  }

  @Test
  public void testInvalidEmailValidation() {
    String[] invalidEmails = {
      /* invalid emails which we catch */
      "Abc.example.com",    // @ must separate local and domain parts
      "abc",
      "01234567890123456789012345678901234567890123456789012345678901234@012345678901234567890123456789012345678901234567890123456789012.com",

      /* invalid emails which we don't detect ...
      "A@b@c@example.com",                      // only one @ is allowed outside quotation marks
      "a\"b(c)d,e:f;g<h>i[j\\k]l@example.com",  // special chars not allowed in local part outside of quotation marks
      "just\"not\"right@example.com",           // quoted strings must be dot separated or only element making up local-part
      "john..doe@example.com",                  // double dot before @
      "john.doe@example..com",                  // double dot after @
      " valid@foo.com",                         // leading space
      "valid@foo.com ",                         // trailing space
       */
    };

    for (String email : invalidEmails) {
      assertFalse( Email.validateEmail(email) );
    }
  }
}
