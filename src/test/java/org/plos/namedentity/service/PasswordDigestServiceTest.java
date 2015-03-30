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
