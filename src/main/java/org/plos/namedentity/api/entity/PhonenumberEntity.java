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
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PhonenumberEntity implements java.io.Serializable {

  private static final long serialVersionUID = -1728456967;

  private Integer phonenumberid;
  private Integer namedentityid;
  private Integer phonenumbertypeid;
  private Integer countrycodetypeid;
  private String  phonenumber;
  private String  extension;
  private Boolean isprimary;
  private Boolean isactive;

  private String  phonenumbertype;
  private String  countrycodetype;

  public PhonenumberEntity() {
    isactive = true;
    isprimary = true;
//    this(null,null,null,null,null,null,null,null);
  }

//  public PhonenumberEntity(
//    Integer phonenumberid,
//    Integer namedentityid,
//    Integer phonenumbertypeid,
//    Integer countrycodetypeid,
//    String  phonenumber,
//    String  extension,
//    Byte    isprimary,
//    Byte    isactive
//  ) {
//    this.phonenumberid     = phonenumberid;
//    this.namedentityid     = namedentityid;
//    this.phonenumbertypeid = phonenumbertypeid;
//    this.countrycodetypeid = countrycodetypeid;
//    this.phonenumber       = phonenumber;
//    this.extension         = extension;
//    this.isprimary         = (isprimary != null ? isprimary : (byte)1);
//    this.isactive          = (isactive != null ? isactive : (byte)1);
//  }

  public String getPhonenumbertype() {
    return phonenumbertype;
  }

  public void setPhonenumbertype(String phonenumbertype) {
    this.phonenumbertype = phonenumbertype;
  }

  public String getCountrycodetype() {
    return countrycodetype;
  }

  public void setCountrycodetype(String countrycodetype) {
    this.countrycodetype = countrycodetype;
  }

  public Integer getPhonenumberid() {
    return this.phonenumberid;
  }

  public void setPhonenumberid(Integer phonenumberid) {
    this.phonenumberid = phonenumberid;
  }

  public Integer getNamedentityid() {
    return this.namedentityid;
  }

  public void setNamedentityid(Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public Integer getPhonenumbertypeid() {
    return this.phonenumbertypeid;
  }

  public void setPhonenumbertypeid(Integer phonenumbertypeid) {
    this.phonenumbertypeid = phonenumbertypeid;
  }

  public Integer getCountrycodetypeid() {
    return this.countrycodetypeid;
  }

  public void setCountrycodetypeid(Integer countrycodetypeid) {
    this.countrycodetypeid = countrycodetypeid;
  }

  public String getPhonenumber() {
    return this.phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public String getExtension() {
    return this.extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public Boolean getIsprimary() {
    return this.isprimary;
  }

  public void setIsprimary(Boolean isprimary) {
    this.isprimary = isprimary;
  }

  public Boolean getIsactive() {
    return this.isactive;
  }

  public void setIsactive(Boolean isactive) {
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
