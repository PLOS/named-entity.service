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
package org.plos.namedentity.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/ambra-spring-beans.xml","/spring-beans.test.xml","/ambra-spring-beans.test.xml"})
public class AuthServiceTest {

  @Autowired
  AuthService authService;

  @Test
  public void testInvalidCredentials() {
    assertFalse(authService.authenticate(null));
    String[] inputForms = { "", "user", ":", ":passwd", "user:", "user:passwd", "tahi:abcx" };
    for (String plaintext : inputForms) {
      String encodedtext = encode(plaintext);
      assertFalse(authService.authenticate(encodedtext));
      assertFalse(authService.authenticate("Basic " + encodedtext));
    }
  }

  @Test
  public void testValidCredentials() {
    String[] inputForms = { "test:test" };
    for (String plaintext : inputForms) {
      assertTrue(authService.authenticate("Basic "+encode(plaintext)));
    }
  }

  private String encode(String plaintext) {
    return Base64.getEncoder().encodeToString(plaintext.getBytes(StandardCharsets.UTF_8));
  }

  private String decode(String encodedtext) {
    byte[] decodedBytes = Base64.getDecoder().decode(encodedtext);
    return new String(decodedBytes);
  }
}
