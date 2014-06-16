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

import java.util.Objects;

/**
 * Modified JOOQ generated class(pojo=true).
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EmailEntity implements java.io.Serializable {

  private static final long serialVersionUID = -945009318;

  private java.lang.Integer emailid;
  private java.lang.Integer namedentityid;
  private java.lang.Integer emailtypeid;
  private java.lang.String  emailaddress;
  private java.lang.Byte    isprimary;
  private java.lang.Byte    isactive;

  public EmailEntity() {
        this(null,null,null,null,null,null); 
    }

  public EmailEntity(
    java.lang.Integer emailid,
    java.lang.Integer namedentityid,
    java.lang.Integer emailtypeid,
    java.lang.String  emailaddress,
    java.lang.Byte    isprimary,
    java.lang.Byte    isactive
  ) {
    this.emailid       = emailid;
    this.namedentityid = namedentityid;
    this.emailtypeid   = emailtypeid;
    this.emailaddress  = emailaddress;
    this.isprimary     = (isprimary != null ? isprimary : 1);
    this.isactive      = (isactive != null ? isactive : 1);
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

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }

    if (o == null || this.getClass() != o.getClass()) { return false; }

    EmailEntity entity = (EmailEntity) o;
    return Objects.equals(this.emailid, entity.emailid)
        && Objects.equals(this.namedentityid, entity.namedentityid)
        && Objects.equals(this.emailtypeid, entity.emailtypeid)
        && Objects.equals(this.emailaddress, entity.emailaddress)
        && Objects.equals(this.isprimary, entity.isprimary)
        && Objects.equals(this.isactive, entity.isactive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      emailid, namedentityid, emailtypeid, emailaddress, isprimary, isactive);
  }
}
