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

/**
 * Modified JOOQ generated class(pojo=true).
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@XmlRootElement
public class Globaltype extends Entity {

  private static final long serialVersionUID = 541426103;

  private Integer  globaltypeid;
  private Integer  typeid;
  private String   shortdescription;
  private String   longdescription;
  private String   typecode;
  private Timestamp created;// = new Timestamp(new Date().getTime());
  private Timestamp lastmodified;// = new Timestamp(new Date().getTime());
  private Integer  createdby;
  private Integer  lastmodifiedby;

  public Integer getGlobaltypeid() {
    return this.globaltypeid;
  }

  public void setGlobaltypeid(Integer globaltypeid) {
    this.globaltypeid = globaltypeid;
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
    if (this == o) { return true; }

    if (o == null || this.getClass() != o.getClass()) { return false; }

    Globaltype entity = (Globaltype) o;
    return Objects.equals(this.globaltypeid, entity.globaltypeid)
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
    return Objects.hash(globaltypeid, typeid, shortdescription, longdescription, 
      typecode, created, lastmodified, createdby, lastmodifiedby);
  }
}
