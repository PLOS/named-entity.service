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

  private static EmailValidator emailValidator = EmailValidator.getInstance();

  private Integer id;
  private Integer nedid;

  private Integer typeid;
  private String  type;

  private String emailaddress;
  private Byte isprimary = 0;  // TODO: change Byte to Bool
  private Byte isactive  = 1;

  @Override
  public void validate() {

    if (emailaddress != null && !emailValidator.isValid(emailaddress))
      throw new NedValidationException("Email not valid");

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }

    Email entity = (Email) o;
    return Objects.equals(this.id, entity.id)
        && Objects.equals(this.nedid, entity.nedid)
        && Objects.equals(this.typeid, entity.typeid)
        && Objects.equals(this.type, entity.type)
        && Objects.equals(this.emailaddress, entity.emailaddress)
        && Objects.equals(this.isprimary, entity.isprimary)
        && Objects.equals(this.isactive, entity.isactive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, nedid, typeid, type, emailaddress, isprimary, isactive);
  }


  public java.lang.Integer getId() {
    return this.id;
  }

  public void setId(java.lang.Integer id) {
    this.id = id;
  }

  public java.lang.Integer getNedid() {
    return this.nedid;
  }

  public void setNedid(java.lang.Integer nedid) {
    this.nedid = nedid;
  }

  public java.lang.Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(java.lang.Integer typeid) {
    this.typeid = typeid;
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

  public String getType() {
      return type;
  }
  
  public void setType(String type) {
      this.type = type;
  }

}
