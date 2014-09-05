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
public class Phonenumber extends Entity {

  private Integer id;
  private Integer nedid;
  private Integer typeid;
  private String  type;
  private Integer countrycodetypeid;
  private String  countrycodetype;

  private String phonenumber;
  private String extension;

  private Boolean isprimary;
  private Boolean isactive = true;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCountrycodetype() {
    return countrycodetype;
  }

  public void setCountrycodetype(String countrycodetype) {
    this.countrycodetype = countrycodetype;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getNedid() {
    return this.nedid;
  }

  public void setNedid(Integer nedid) {
    this.nedid = nedid;
  }

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
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
    if (this == o) {
      return true;
    }

    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }

    Phonenumber entity = (Phonenumber) o;
    return Objects.equals(this.id, entity.id)
        && Objects.equals(this.nedid, entity.nedid)
        && Objects.equals(this.phonenumber, entity.phonenumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nedid, phonenumber);
  }
}
