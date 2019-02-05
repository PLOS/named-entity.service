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
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})
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
