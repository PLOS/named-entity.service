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

public class RelationshipsDTO {

  private java.lang.Integer  relationshipid;
  private java.lang.Integer  masternamedentityid;
  private java.lang.Integer  childnamedentityid;
  private java.lang.Integer  relationshiptypeid;
  private java.sql.Timestamp startdate;
  private java.sql.Timestamp enddate;
  private java.sql.Timestamp created;
  private java.sql.Timestamp lastmodified;
  private java.lang.Integer  createdby;
  private java.lang.Integer  lastmodifiedby;

  public RelationshipsDTO() {}

  public RelationshipsDTO(
    java.lang.Integer  relationshipid,
    java.lang.Integer  masternamedentityid,
    java.lang.Integer  childnamedentityid,
    java.lang.Integer  relationshiptypeid,
    java.sql.Timestamp startdate,
    java.sql.Timestamp enddate,
    java.sql.Timestamp created,
    java.sql.Timestamp lastmodified,
    java.lang.Integer  createdby,
    java.lang.Integer  lastmodifiedby
  ) {
    this.relationshipid      = relationshipid;
    this.masternamedentityid = masternamedentityid;
    this.childnamedentityid  = childnamedentityid;
    this.relationshiptypeid  = relationshiptypeid;
    this.startdate           = startdate;
    this.enddate             = enddate;
    this.created             = created;
    this.lastmodified        = lastmodified;
    this.createdby           = createdby;
    this.lastmodifiedby      = lastmodifiedby;
  }

  public java.lang.Integer getRelationshipid() {
    return this.relationshipid;
  }

  public void setRelationshipid(java.lang.Integer relationshipid) {
    this.relationshipid = relationshipid;
  }

  public java.lang.Integer getMasternamedentityid() {
    return this.masternamedentityid;
  }

  public void setMasternamedentityid(java.lang.Integer masternamedentityid) {
    this.masternamedentityid = masternamedentityid;
  }

  public java.lang.Integer getChildnamedentityid() {
    return this.childnamedentityid;
  }

  public void setChildnamedentityid(java.lang.Integer childnamedentityid) {
    this.childnamedentityid = childnamedentityid;
  }

  public java.lang.Integer getRelationshiptypeid() {
    return this.relationshiptypeid;
  }

  public void setRelationshiptypeid(java.lang.Integer relationshiptypeid) {
    this.relationshiptypeid = relationshiptypeid;
  }

  public java.sql.Timestamp getStartdate() {
    return this.startdate;
  }

  public void setStartdate(java.sql.Timestamp startdate) {
    this.startdate = startdate;
  }

  public java.sql.Timestamp getEnddate() {
    return this.enddate;
  }

  public void setEnddate(java.sql.Timestamp enddate) {
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
}
