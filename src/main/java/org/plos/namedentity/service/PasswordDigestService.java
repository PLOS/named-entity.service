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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import org.apache.log4j.Logger;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * Password digest service. Salts and hashes passwords such that they can be verified, but it is cryptographically
 * infeasible to get the password from the hash.
 *
 * @author Ryan Skonnord
 */
public class PasswordDigestService {

  protected static Logger logger = Logger.getLogger(PasswordDigestService.class);

  private static final HashFunction HASH_FUNCTION = Hashing.sha256();
  private static final int HASH_LENGTH = HASH_FUNCTION.bits() / Byte.SIZE;
  private static final int SECURE_SALT_LENGTH = HASH_LENGTH;
  private static final int LEGACY_SALT_LENGTH = 6;

  static {
    assert SECURE_SALT_LENGTH != LEGACY_SALT_LENGTH;
  }

  private static final Charset BYTE_ENCODING = Charsets.UTF_8;
  private static final BaseEncoding STRING_ENCODING = BaseEncoding.base16().lowerCase();

  private final Random random;

  public PasswordDigestService() {
    this(new SecureRandom());
  }

  @VisibleForTesting
  PasswordDigestService(Random random) {
    if (!(random instanceof SecureRandom)) {
      logger.warn("The source for random salts is not guaranteed to be secure");
    }
    this.random = Preconditions.checkNotNull(random);
  }

  private byte[] produceHash(byte[] salt, String plaintextPassword) {
    byte[] passwordBytes = plaintextPassword.getBytes(BYTE_ENCODING);
    Hasher hasher = HASH_FUNCTION.newHasher(salt.length + passwordBytes.length);
    hasher.putBytes(salt);
    hasher.putBytes(passwordBytes);
    return hasher.hash().asBytes();
  }

  public String generateDigest(String plaintextPassword) {
    byte[] salt = new byte[SECURE_SALT_LENGTH];
    random.nextBytes(salt);

    byte[] hash = produceHash(salt, plaintextPassword);

    byte[] digest = new byte[salt.length + hash.length];
    System.arraycopy(salt, 0, digest, 0, salt.length);
    System.arraycopy(hash, 0, digest, salt.length, hash.length);
    return STRING_ENCODING.encode(digest);
  }

  public boolean verifyPassword(String plaintextPassword, String digest) {
    if (digest.length() == LEGACY_SALT_LENGTH + HASH_LENGTH * 2) {
      return verifyLegacy(digest, plaintextPassword);
    }

    byte[] digestBytes = STRING_ENCODING.decode(digest);
    byte[] salt = new byte[digestBytes.length - HASH_LENGTH];
    System.arraycopy(digestBytes, 0, salt, 0, salt.length);
    byte[] providedHash = new byte[HASH_LENGTH];
    System.arraycopy(digestBytes, salt.length, providedHash, 0, providedHash.length);

    byte[] expectedHash = produceHash(salt, plaintextPassword);
    return Arrays.equals(providedHash, expectedHash);
  }

  private boolean verifyLegacy(String digest, String plaintextPassword) {
    String saltString = digest.substring(0, LEGACY_SALT_LENGTH);
    byte[] saltBytes = saltString.getBytes(BYTE_ENCODING);
    /*
     * Yes, saltBytes really is this and not STRING_ENCODING.decode(saltString). This was a bug in the legacy
     * implementation of this service, which we must reproduce in order to verify with digests that it wrote.
     *
     * The saltString will look like a hex encoding of three bytes
     *     (e.g., "6b3882" -> [0x6b, 0x38, 0x82]),
     * but we actually convert it to the salt by taking the ASCII encoding of those six digit characters
     *     (e.g., "6b3882" -> ['6', 'b', '3', '8', '8', '2'] -> [0x36, 0x62, 0x33, 0x38, 0x38, 0x32]).
     * This effectively gives only three bytes of entropy.
     */

    byte[] hashBytes = produceHash(saltBytes, plaintextPassword);
    String hashHex = STRING_ENCODING.encode(hashBytes);

    /*
     * As a mirror image of the previous bug, the legacy implementation did not store the digest by concatenating the
     * salt bytes to the hash bytes and encoding that to hexidecimal. Rather, concatenate the six hex digit characters,
     * whose ASCII encodings were the salt, to the hex representation of the hash bytes.
     */
    String expectedDigest = saltString + hashHex;
    return expectedDigest.equals(digest);
  }
}
