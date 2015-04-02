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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import org.plos.namedentity.api.NedException;

import org.plos.namedentity.service.PasswordDigestService;

import java.util.UUID;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Auth extends Entity {

  private static final int PASSWORD_DIGEST_LENGTH        = 128;
  private static final int LEGACY_PASSWORD_DIGEST_LENGTH = 70;

  private static final int CASID_MIN_LENGTH = 28;
  private static final int CASID_MAX_LENGTH = 36;

  private static final Pattern casRegex = Pattern.compile("^[A-Za-z0-9-]+$");
  private static final Pattern passwordDigestRegexp = Pattern.compile("^[0-9a-f]+$");

  private String   email;
  private Integer  emailid;
  private String   authid;

  // plain text password is a transient attribute used to generate the secure
  // password and in marshalling from json to pojo entity (not stored in db)
  private String   plainTextPassword;

  // digested password (salt + hashed password) stored in db
  private String   password;

  private Byte     passwordreset;
  private String   verificationtoken;
  private Byte     verified;
  private Byte     isactive;

  public Auth() {
    this.authid = UUID.randomUUID().toString();  // cas id
  }

  private static String EXCLUDED_FIELDS[] = {
    "authid",
    "emailid",
    "isactive",
    "password",
    "plainTextPassword"
  };

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o, concat(Entity.EXCLUDED_FIELDS, Auth.EXCLUDED_FIELDS));
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, concat(Entity.EXCLUDED_FIELDS, Auth.EXCLUDED_FIELDS));
  }

  @Override
  public void validate() {
    validateAuthid(this.authid);
    validatePlainTextPassword(this.plainTextPassword);
    validatePasswordDigest(this.password);
  }

  public java.lang.Integer getEmailid() {
    return this.emailid;
  }

  public void setEmailid(java.lang.Integer emailid) {
    this.emailid = emailid;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public java.lang.String getAuthid() {
    return this.authid;
  }

  public void setAuthid(java.lang.String authid) {
    this.authid = authid;
  }

  @XmlElement(name = "password")
  public String getPlainTextPassword() { return this.plainTextPassword; }

  @XmlElement(name = "password")
  public void setPlainTextPassword(String plainTextPassword) {
    this.plainTextPassword = plainTextPassword;
    if (plainTextPassword != null) {
      setPassword(new PasswordDigestService().generateDigest(plainTextPassword));
    }
  }

  public String getPassword() {
    return this.password;
  }

  // setter called from 1. plaintext setter, 2. jooq populating pojo from db,
  // and 3. etl migrating ambra password.
  @XmlElement(name = "password_digest")
  public void setPassword(String hashedPassword) {
    this.password = hashedPassword;
  }

  public java.lang.Byte getPasswordreset() {
    return this.passwordreset;
  }

  public void setPasswordreset(java.lang.Byte passwordreset) {
    this.passwordreset = passwordreset;
  }

  public java.lang.String getVerificationtoken() {
    return this.verificationtoken;
  }

  public void setVerificationtoken(java.lang.String verificationtoken) {
    this.verificationtoken = verificationtoken;
  }

  public java.lang.Byte getVerified() {
    return this.verified;
  }

  public void setVerified(java.lang.Byte verified) {
    this.verified = verified;
  }

  public java.lang.Byte getIsactive() {
    return this.isactive;
  }

  public void setIsactive(java.lang.Byte isactive) {
    this.isactive = isactive;
  }

  private String[] concat(String[] a, String[] b) {
    String[] c = new String[a.length + b.length];
    System.arraycopy(a, 0, c, 0, a.length);
    System.arraycopy(b, 0, c, a.length, b.length);
    return c;
  }

  private void validateAuthid(String casid) {
    if (casid == null) {
      throw new NedException(CasIdError, "undefined cas id. this is a required field.");
    }

    if (casid.length() < CASID_MIN_LENGTH)
      throw new NedException(InvalidCasId, "CAS ID can not be shorter then " + CASID_MIN_LENGTH + " characters");

    if (casid.length() > CASID_MAX_LENGTH)
      throw new NedException(InvalidCasId, "CAS ID can not be longer then " + CASID_MAX_LENGTH + " characters");

    if (!casRegex.matcher(casid).matches())
      throw new NedException(InvalidCasId, "CAS ID contains invalid characters");
  }

  private void validatePlainTextPassword(String password) {
    // only validate if not null
    if (password != null && password.length() < 6) {
      throw new NedException(PasswordLengthError, "password must be at least 6 characters.");
    }
  }

  private void validatePasswordDigest(String password) {
    if (password == null) {
      throw new NedException(PasswordError, "undefined password. this is a required field.");
    }
    if ( !isValidDigestFormat(password) ) {
      throw new NedException(DigestPasswordError, String.format(
        "invalid encoded password (length:%d)", password.length()));
    }
    if (plainTextPassword != null) {
      boolean verifyResult = new PasswordDigestService().verifyPassword(plainTextPassword, password);
      if (!verifyResult) {
        throw new NedException(TamperedPasswordError, "secure password doesn't match input password");
      }
    }
  }

  boolean isValidDigestFormat(String password) {
    if (passwordDigestRegexp.matcher(password).matches() &&
        (password.length() == PASSWORD_DIGEST_LENGTH || 
         password.length() == LEGACY_PASSWORD_DIGEST_LENGTH)) {
      return true;
    }
    return false;
  }
}
