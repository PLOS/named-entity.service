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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordDigestServiceTest {

  private PasswordDigestService passwordDigestService;
  private Random random;

  @Before
  public void setup() {
    this.passwordDigestService = new PasswordDigestService(new Random(0));
    this.random = new Random(0);
  }

  @Test
  public void testHashingShouldGiveDifferentReturnValue() {

    for (int count = 0; count < 100; count++) {
      final String originalPassword = generatePassword();
      final String digestPassword   = passwordDigestService.generateDigest(originalPassword);
      assertFalse(originalPassword.equalsIgnoreCase(digestPassword));
      assertTrue(passwordDigestService.verifyPassword(originalPassword, digestPassword));
    }
  }

  @Test
  public void testVerifyPasswordInLegacyDigestFormat() {
    final String expected = "6584abbf44d354572af470f6de0d48c11d595968636b75b38006e5a60043b6641aeba7";
    final String password = "fedoraAdmin";
    assertTrue(passwordDigestService.verifyPassword(password, expected));
  }

  @Test
  public void testVerificationShouldFailForWrongPassword() {

    for (int count = 0; count < 100; count++) {
      final String originalPassword = generatePassword();
      final String digestPassword   = passwordDigestService.generateDigest(originalPassword);
      assertFalse(originalPassword.equalsIgnoreCase(digestPassword));
      assertFalse(passwordDigestService.verifyPassword(originalPassword + "1", digestPassword));
    }
  }

  @Test
  public void testHashingOfSameStringShouldGiveDifferentResult() throws InterruptedException {

    for (int count = 0; count < 100; count++) {
      final String originalPassword = generatePassword();
      final String digestPassword1  = passwordDigestService.generateDigest(originalPassword);
      Thread.sleep(40);
      final String digestPassword2  = passwordDigestService.generateDigest(originalPassword);
      assertFalse(digestPassword1.equalsIgnoreCase(digestPassword2));
      assertTrue(passwordDigestService.verifyPassword(originalPassword, digestPassword1));
      assertTrue(passwordDigestService.verifyPassword(originalPassword, digestPassword2));
    }
  }

  private String generatePassword() {
    final StringBuilder sb = new StringBuilder();
    for (int length = 1; length < random.nextInt(20); length++) {
      final char ch = (char) (64 + random.nextInt(60));
      sb.append(ch);
    }
    return sb.toString();
  }
}
