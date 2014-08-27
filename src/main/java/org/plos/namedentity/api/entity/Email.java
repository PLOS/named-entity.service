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

import org.apache.commons.validator.routines.EmailValidator;
import org.plos.namedentity.api.NedValidationException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Modified JOOQ generated class(pojo=true).
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@XmlRootElement
public class Email extends Entity {

  private static final long serialVersionUID = -945009318;

  private static EmailValidator emailValidator = EmailValidator.getInstance();

  private Integer emailid;
  private Integer namedentityid;

  private Integer emailtypeid;
  private String  emailtype;

  private String  emailaddress;
  private Byte    isprimary = 1;  // TODO: change Byte to Bool
  private Byte    isactive = 1;

  @Override
  public void validate() {

    if (emailaddress != null && !emailValidator.isValid(emailaddress))
      throw new NedValidationException("Email not valid");

    // TODO - *** REMOVE DEMO HACK ***
    if (emailaddress != null && "foo@bar.com".equals(emailaddress)) {
      throw new NedValidationException("foo@bar.com backdoor detected !!!");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }

    if (o == null || this.getClass() != o.getClass()) { return false; }

    Email entity = (Email) o;
    return Objects.equals(this.emailid, entity.emailid)
        && Objects.equals(this.namedentityid, entity.namedentityid)
        && Objects.equals(this.emailtypeid, entity.emailtypeid)
        && Objects.equals(this.emailtype, entity.emailtype)
        && Objects.equals(this.emailaddress, entity.emailaddress)
        && Objects.equals(this.isprimary, entity.isprimary)
        && Objects.equals(this.isactive, entity.isactive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        emailid, namedentityid, emailtypeid, emailtype, emailaddress, isprimary, isactive);
  }


  public java.lang.Integer getEmailid() {
    return this.emailid;
  }

  public void setEmailid(java.lang.Integer emailid) {
    this.emailid = emailid;
  }

  public java.lang.Integer getNamedentityid() {
    return this.namedentityid;
  }

  public void setNamedentityid(java.lang.Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public java.lang.Integer getEmailtypeid() {
    return this.emailtypeid;
  }

  public void setEmailtypeid(java.lang.Integer emailtypeid) {
    this.emailtypeid = emailtypeid;
  }

  public java.lang.String getEmailaddress() {
    return this.emailaddress;
  }

  public void setEmailaddress(java.lang.String emailaddress) {
    this.emailaddress = emailaddress;
  }

  public java.lang.Byte getIsprimary() {
    return this.isprimary;
  }

  public void setIsprimary(java.lang.Byte isprimary) {
    this.isprimary = isprimary;
  }

  public java.lang.Byte getIsactive() {
    return this.isactive;
  }

  public void setIsactive(java.lang.Byte isactive) {
    this.isactive = isactive;
  }

  public String getEmailtype() {
      return emailtype;
  }
  
  public void setEmailtype(String emailtype) {
      this.emailtype = emailtype;
  }

}
