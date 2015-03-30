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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AuthTest {

  private Auth auth = new Auth();

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
}
