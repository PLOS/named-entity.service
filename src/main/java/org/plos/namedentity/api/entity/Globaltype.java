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

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Objects;

@XmlRootElement
public class Globaltype extends Entity {

  private Integer   id;
  private Integer   typeid;
  private String    shortdescription;
  private String    longdescription;
  private String    typecode;
  private Timestamp created;
  private Timestamp lastmodified;
  private Integer   createdby;
  private Integer   lastmodifiedby;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  public String getShortdescription() {
    return this.shortdescription;
  }

  public void setShortdescription(String shortdescription) {
    this.shortdescription = shortdescription;
  }

  public String getLongdescription() {
    return this.longdescription;
  }

  public void setLongdescription(String longdescription) {
    this.longdescription = longdescription;
  }

  public String getTypecode() {
    return this.typecode;
  }

  public void setTypecode(String typecode) {
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

  public Integer getCreatedby() {
    return this.createdby;
  }

  public void setCreatedby(Integer createdby) {
    this.createdby = createdby;
  }

  public Integer getLastmodifiedby() {
    return this.lastmodifiedby;
  }

  public void setLastmodifiedby(Integer lastmodifiedby) {
    this.lastmodifiedby = lastmodifiedby;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }

    Globaltype entity = (Globaltype) o;
    return Objects.equals(this.id, entity.id)
        && Objects.equals(this.typeid, entity.typeid)
        && Objects.equals(this.shortdescription, entity.shortdescription)
        && Objects.equals(this.longdescription, entity.longdescription)
        && Objects.equals(this.typecode, entity.typecode)
        && Objects.equals(this.created, entity.created)
        && Objects.equals(this.lastmodified, entity.lastmodified)
        && Objects.equals(this.createdby, entity.createdby)
        && Objects.equals(this.lastmodifiedby, entity.lastmodifiedby);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typeid, shortdescription, longdescription,
        typecode, created, lastmodified, createdby, lastmodifiedby);
  }
}
