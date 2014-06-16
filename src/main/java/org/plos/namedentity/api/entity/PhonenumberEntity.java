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
public class PhonenumberEntity implements java.io.Serializable {

  private static final long serialVersionUID = -1728456967;

  private java.lang.Integer phonenumberid;
  private java.lang.Integer namedentityid;
  private java.lang.Integer phonenumbertypeid;
  private java.lang.Integer countrycodetypeid;
  private java.lang.String  phonenumber;
  private java.lang.String  extension;
  private java.lang.Byte    isprimary;
  private java.lang.Byte    isactive;

  public PhonenumberEntity() {
    this(null,null,null,null,null,null,null,null);
  }

  public PhonenumberEntity(
    java.lang.Integer phonenumberid,
    java.lang.Integer namedentityid,
    java.lang.Integer phonenumbertypeid,
    java.lang.Integer countrycodetypeid,
    java.lang.String  phonenumber,
    java.lang.String  extension,
    java.lang.Byte    isprimary,
    java.lang.Byte    isactive
  ) {
    this.phonenumberid     = phonenumberid;
    this.namedentityid     = namedentityid;
    this.phonenumbertypeid = phonenumbertypeid;
    this.countrycodetypeid = countrycodetypeid;
    this.phonenumber       = phonenumber;
    this.extension         = extension;
    this.isprimary         = (isprimary != null ? isprimary : (byte)1);
    this.isactive          = (isactive != null ? isactive : (byte)1);
  }

  public java.lang.Integer getPhonenumberid() {
    return this.phonenumberid;
  }

  public void setPhonenumberid(java.lang.Integer phonenumberid) {
    this.phonenumberid = phonenumberid;
  }

  public java.lang.Integer getNamedentityid() {
    return this.namedentityid;
  }

  public void setNamedentityid(java.lang.Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public java.lang.Integer getPhonenumbertypeid() {
    return this.phonenumbertypeid;
  }

  public void setPhonenumbertypeid(java.lang.Integer phonenumbertypeid) {
    this.phonenumbertypeid = phonenumbertypeid;
  }

  public java.lang.Integer getCountrycodetypeid() {
    return this.countrycodetypeid;
  }

  public void setCountrycodetypeid(java.lang.Integer countrycodetypeid) {
    this.countrycodetypeid = countrycodetypeid;
  }

  public java.lang.String getPhonenumber() {
    return this.phonenumber;
  }

  public void setPhonenumber(java.lang.String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public java.lang.String getExtension() {
    return this.extension;
  }

  public void setExtension(java.lang.String extension) {
    this.extension = extension;
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

    PhonenumberEntity entity = (PhonenumberEntity) o;
    return Objects.equals(this.phonenumberid, entity.phonenumberid)
        && Objects.equals(this.namedentityid, entity.namedentityid)
        && Objects.equals(this.phonenumber, entity.phonenumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phonenumberid, namedentityid, phonenumber);
  }
}
