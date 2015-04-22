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

import static org.plos.namedentity.api.NedException.ErrorType.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.plos.namedentity.api.NedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

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
      assertFalse( auth.isValidDigestFormat(password) );
    }
  }

  @Test
  public void testValidDigestFormat() {
    String[] okPasswords = {
      "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef012345",
      "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef",
    };

    for (String password : okPasswords) {
      assertTrue( auth.isValidDigestFormat(password) );
    }
  }

  private void validateAuthid(Auth auth) throws Exception {
    Method m = Auth.class.getDeclaredMethod("validateAuthid", new Class[]{String.class});
    m.setAccessible(true);
    m.invoke(auth, auth.getAuthid());
  }
}
