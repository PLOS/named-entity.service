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
public class OrganizationEntity implements java.io.Serializable {

  private static final long serialVersionUID = 276513267;

  private Integer namedentityid;
  private Integer organizationtypeid;
  private String  organizationtype;
  private String  organizationfamiliarname;
  private String  organizationlegalname;
  private Integer organizationmaincontactid;
  private Byte    isactive;
  private Byte    isvisible;
  private String  url;

  public OrganizationEntity() {
  }

  public OrganizationEntity(
      Integer namedentityid,
      Integer organizationtypeid,
      String organizationtype,
      String organizationfamiliarname,
      String organizationlegalname,
      Integer organizationmaincontactid,
      Byte isactive,
      Byte isvisible,
      String url
                           ) {
    this.namedentityid = namedentityid;
    this.organizationtypeid = organizationtypeid;
    this.organizationtype = organizationtype;
    this.organizationfamiliarname = organizationfamiliarname;
    this.organizationlegalname = organizationlegalname;
    this.organizationmaincontactid = organizationmaincontactid;
    this.isactive = isactive;
    this.isvisible = isvisible;
    this.url = url;
  }

  public java.lang.Integer getNamedentityid() {
    return this.namedentityid;
  }

  public void setNamedentityid(java.lang.Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public java.lang.Integer getOrganizationtypeid() {
    return this.organizationtypeid;
  }

  public void setOrganizationtypeid(java.lang.Integer organizationtypeid) {
    this.organizationtypeid = organizationtypeid;
  }

  public java.lang.String getOrganizationfamiliarname() {
    return this.organizationfamiliarname;
  }

  public void setOrganizationfamiliarname(java.lang.String organizationfamiliarname) {
    this.organizationfamiliarname = organizationfamiliarname;
  }

  public java.lang.String getOrganizationlegalname() {
    return this.organizationlegalname;
  }

  public void setOrganizationlegalname(java.lang.String organizationlegalname) {
    this.organizationlegalname = organizationlegalname;
  }

  public java.lang.Integer getOrganizationmaincontactid() {
    return this.organizationmaincontactid;
  }

  public void setOrganizationmaincontactid(java.lang.Integer organizationmaincontactid) {
    this.organizationmaincontactid = organizationmaincontactid;
  }

  public java.lang.Byte getIsactive() {
    return this.isactive;
  }

  public void setIsactive(java.lang.Byte isactive) {
    this.isactive = isactive;
  }

  public java.lang.Byte getIsvisible() {
    return this.isvisible;
  }

  public void setIsvisible(java.lang.Byte isvisible) {
    this.isvisible = isvisible;
  }

  public java.lang.String getUrl() {
    return this.url;
  }

  public void setUrl(java.lang.String url) {
    this.url = url;
  }

  public String getOrganizationtype() {
    return organizationtype;
  }

  public void setOrganizationtype(String organizationtype) {
    this.organizationtype = organizationtype;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OrganizationEntity)) return false;

    OrganizationEntity that = (OrganizationEntity) o;

    return Objects.equals(this.namedentityid, that.namedentityid)
        && Objects.equals(this.isactive, that.isactive)
        && Objects.equals(this.isvisible, that.isvisible)
        && Objects.equals(this.organizationfamiliarname, that.organizationfamiliarname)
        && Objects.equals(this.organizationlegalname, that.organizationlegalname)
        && Objects.equals(this.organizationmaincontactid, that.organizationmaincontactid)
        && Objects.equals(this.organizationtypeid, that.organizationtypeid)
        && Objects.equals(this.url, that.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.namedentityid, this.isactive, this.isvisible,this.organizationfamiliarname, this.organizationlegalname, this.organizationmaincontactid,this.organizationtypeid, this.url);
  }
}
