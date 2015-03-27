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

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Auth extends Entity {

  private String   email;
  private Integer  emailid;
  private String   authid;
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
    "password"
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

    if (password == null || password.length() < 6) {
      throw new NedException(PasswordError, "password must be at least 6 characters.");
    }

    // hash password before storing, if not already digested.
    if (password.length() != 128) {
      password = new PasswordDigestService().generateDigest(password);
    }
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

  public java.lang.String getPassword() {
    return this.password;
  }

  public void setPassword(java.lang.String plainTextPassword) {
    this.password = plainTextPassword;
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
}
