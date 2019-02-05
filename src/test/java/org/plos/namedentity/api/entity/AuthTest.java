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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.plos.namedentity.api.NedException.ErrorType.CasIdError;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidCasId;
import static org.plos.namedentity.api.NedException.ErrorType.PasswordFormatError;
import static org.plos.namedentity.service.PasswordDigestService.isValidDigestFormat;

public class AuthTest {

  private Auth auth = new Auth();

  @Test
  public void testInvalidCasId() throws Exception {

    // undefined cas id (null)

    try {
      auth.setAuthid(null) ; validateAuthid(auth) ; 
      fail();
    } catch (InvocationTargetException ite) {
      // Reflection wraps any exception thrown by a method.
      NedException ne = (NedException) ite.getCause();
      assertEquals(CasIdError, ne.getErrorType());
    }

    // invalid cas id's

    String[] badCasids = {
      "QKWVMXWCOYHJ8WTC9I0ABCDDDAD",
      "a2d95a5e-5c9b-eb5f-8ce8-e459759571ebX",
      "a2d95a5e-$$$$-@@@@-%%%%-e459759571eb",
    };

    String[] expectedError = {
      "can not be shorter then",
      "can not be longer then",
      "contains invalid characters"
    };

    for (int i = 0; i < badCasids.length; i++) {
      try {
        auth.setAuthid(badCasids[i]) ; validateAuthid(auth) ; 
        fail();
      } catch (InvocationTargetException ite) {
        NedException ne = (NedException) ite.getCause();
        assertEquals(InvalidCasId, ne.getErrorType());
        assertTrue(ne.getDetailedMessage().contains(expectedError[i]));
      }
    }
  }

  @Test
  public void testValidCasId() throws Exception {
    String[] okCasids = {
      "QKWVMXWCOYHJ8WTC9I0LUI2ETIFK0",
      "a2d95a5e-5c9b-eb5f-8ce8-e459759571eb",
    };

    for (String casid : okCasids) {
      auth.setAuthid(casid) ; validateAuthid(auth) ; 
    }
  }

  @Test
  public void testInvalidDigestFormat() {
    String[] badPasswords = {
      "0123456789abcdef",
      "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef01234X",
    };

    for (String password : badPasswords) {
      assertFalse(isValidDigestFormat(password));
    }
  }

  @Test
  public void testValidDigestFormat() {
    String[] okPasswords = {
      "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef012345",
      "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef",
    };

    for (String password : okPasswords) {
      assertTrue(isValidDigestFormat(password));
    }
  }

  @Test
  public void testValidPlaintextPasswordFormat() {

    Auth auth = new Auth();
    auth.setAuthid("QKWVMXWCOYHJ8WTC9I0LUI2ETIFK0");
    auth.setPassword("0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef012345");

    String[] validPasswords = {
        "1aaaaaaa",
        "qwqwe1ee",
        "aaaaaaa1",
        "1111111&"
    };

    String[] invalidPasswords = {
        "a",
        "1",
        "aaaaaaaa",
        "&&&&&&&&"
    };

    for (String password : validPasswords) {
      auth.setPlainTextPassword(password);
      auth.validate();
    }

    for (String password : invalidPasswords) {
      auth.setPlainTextPassword(password);
      try {
        auth.validate();
        fail();
      } catch (NedException e) {
        assertEquals(e.getErrorType(), PasswordFormatError);
      }
    }
  }

  private void validateAuthid(Auth auth) throws Exception {
    Method m = Auth.class.getDeclaredMethod("validateAuthid", new Class[]{String.class});
    m.setAccessible(true);
    m.invoke(auth, auth.getAuthid());
  }
}
