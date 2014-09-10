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

import java.util.Date;
import java.sql.Timestamp;
import java.util.Objects;

import org.plos.namedentity.api.adapter.DateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Modified JOOQ generated class(pojo=true).
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Role extends ChildEntity {

  private Integer   id;
  private Integer   nedid;

  private Integer   sourceapplicationtypeid;
  private String    sourceapplicationtype;

  private Integer   typeid;
  private String    type;

  private Date      startdate;
  private Date      enddate;

  private Timestamp created;
  private Integer   createdby;

  private Timestamp lastmodified;
  private Integer   lastmodifiedby;

  public String getSourceapplicationtype() {
    return sourceapplicationtype;
  }

  public void setSourceapplicationtype(String sourceapplicationtype) {
    this.sourceapplicationtype = sourceapplicationtype;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  public Integer getSourceapplicationtypeid() {
    return this.sourceapplicationtypeid;
  }

  public void setSourceapplicationtypeid(Integer sourceapplicationtypeid) {
    this.sourceapplicationtypeid = sourceapplicationtypeid;
  }

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  @XmlJavaTypeAdapter(DateAdapter.class)
  public Date getStartdate() {
    return this.startdate;
  }

  @XmlJavaTypeAdapter(DateAdapter.class)
  public void setStartdate(Date startdate) {
    this.startdate = startdate;
  }

  @XmlJavaTypeAdapter(DateAdapter.class)
  public Date getEnddate() {
    return this.enddate;
  }

  @XmlJavaTypeAdapter(DateAdapter.class)
  public void setEnddate(Date enddate) {
    this.enddate = enddate;
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

  // TODO: fix how this is rendering in XML
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

    Role entity = (Role) o;
    return Objects.equals(this.id, entity.id)
        && Objects.equals(this.nedid, entity.nedid)
        && Objects.equals(this.typeid, entity.typeid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nedid, typeid);
  }
}
