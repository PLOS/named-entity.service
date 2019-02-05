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
