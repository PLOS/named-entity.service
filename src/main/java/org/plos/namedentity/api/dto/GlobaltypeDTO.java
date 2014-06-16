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
package org.plos.namedentity.api.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class GlobaltypeDTO {

  private java.lang.Integer  globaltypeid;
  private java.lang.Integer  typeid;
  private java.lang.String   shortdescription;
  private java.lang.String   longdescription;
  private java.lang.String   typecode;
  private java.sql.Timestamp created;
  private java.sql.Timestamp lastmodified;
  private java.lang.Integer  createdby;
  private java.lang.Integer  lastmodifiedby;

  public GlobaltypeDTO() { }

  public GlobaltypeDTO(
    java.lang.Integer  globaltypeid,
    java.lang.Integer  typeid,
    java.lang.String   shortdescription,
    java.lang.String   longdescription,
    java.lang.String   typecode,
    java.sql.Timestamp created,
    java.sql.Timestamp lastmodified,
    java.lang.Integer  createdby,
    java.lang.Integer  lastmodifiedby
  ) {
    this.globaltypeid     = globaltypeid;
    this.typeid           = typeid;
    this.shortdescription = shortdescription;
    this.longdescription  = longdescription;
    this.typecode         = typecode;
    this.created          = created;
    this.lastmodified     = lastmodified;
    this.createdby        = createdby;
    this.lastmodifiedby   = lastmodifiedby;
  }

  public java.lang.Integer getGlobaltypeid() {
    return this.globaltypeid;
  }

  public void setGlobaltypeid(java.lang.Integer globaltypeid) {
    this.globaltypeid = globaltypeid;
  }

  public java.lang.Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(java.lang.Integer typeid) {
    this.typeid = typeid;
  }

  public java.lang.String getShortdescription() {
    return this.shortdescription;
  }

  public void setShortdescription(java.lang.String shortdescription) {
    this.shortdescription = shortdescription;
  }

  public java.lang.String getLongdescription() {
    return this.longdescription;
  }

  public void setLongdescription(java.lang.String longdescription) {
    this.longdescription = longdescription;
  }

  public java.lang.String getTypecode() {
    return this.typecode;
  }

  public void setTypecode(java.lang.String typecode) {
    this.typecode = typecode;
  }

  public java.sql.Timestamp getCreated() {
    return this.created;
  }

  public void setCreated(java.sql.Timestamp created) {
    this.created = created;
  }

  public java.sql.Timestamp getLastmodified() {
    return this.lastmodified;
  }

  public void setLastmodified(java.sql.Timestamp lastmodified) {
    this.lastmodified = lastmodified;
  }

  public java.lang.Integer getCreatedby() {
    return this.createdby;
  }

  public void setCreatedby(java.lang.Integer createdby) {
    this.createdby = createdby;
  }

  public java.lang.Integer getLastmodifiedby() {
    return this.lastmodifiedby;
  }

  public void setLastmodifiedby(java.lang.Integer lastmodifiedby) {
    this.lastmodifiedby = lastmodifiedby;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }

    if (o == null || this.getClass() != o.getClass()) { return false; }

    GlobaltypeDTO dto = (GlobaltypeDTO) o;
    return Objects.equals(this.globaltypeid, dto.globaltypeid)
        && Objects.equals(this.typeid, dto.typeid)
        && Objects.equals(this.shortdescription, dto.shortdescription)
        && Objects.equals(this.longdescription, dto.longdescription)
        && Objects.equals(this.typecode, dto.typecode)
        && Objects.equals(this.created, dto.created)
        && Objects.equals(this.lastmodified, dto.lastmodified)
        && Objects.equals(this.createdby, dto.createdby)
        && Objects.equals(this.lastmodifiedby, dto.lastmodifiedby);
  }

  @Override
  public int hashCode() {
    return Objects.hash(globaltypeid, typeid, shortdescription, longdescription, 
            typecode, created, lastmodified, createdby, lastmodifiedby);
  }
}
