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

/**
 * JOOQ generated class(pojo=true).
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Audittrail extends Entity {

  private java.lang.Integer  audittrailid;
  private java.lang.Integer  sourcefieldid;
  private java.lang.Integer  rownumber;
  private java.lang.String   oldvalue;
  private java.lang.String   newvalue;
  private java.sql.Timestamp lastmodified;
  private java.lang.Integer  lastmodifiedby;

  public java.lang.Integer getAudittrailid() {
    return this.audittrailid;
  }

  public void setAudittrailid(java.lang.Integer audittrailid) {
    this.audittrailid = audittrailid;
  }

  public java.lang.Integer getSourcefieldid() {
    return this.sourcefieldid;
  }

  public void setSourcefieldid(java.lang.Integer sourcefieldid) {
    this.sourcefieldid = sourcefieldid;
  }

  public java.lang.Integer getRownumber() {
    return this.rownumber;
  }

  public void setRownumber(java.lang.Integer rownumber) {
    this.rownumber = rownumber;
  }

  public java.lang.String getOldvalue() {
    return this.oldvalue;
  }

  public void setOldvalue(java.lang.String oldvalue) {
    this.oldvalue = oldvalue;
  }

  public java.lang.String getNewvalue() {
    return this.newvalue;
  }

  public void setNewvalue(java.lang.String newvalue) {
    this.newvalue = newvalue;
  }

  public java.sql.Timestamp getLastmodified() {
    return this.lastmodified;
  }

  public void setLastmodified(java.sql.Timestamp lastmodified) {
    this.lastmodified = lastmodified;
  }

  public java.lang.Integer getLastmodifiedby() {
    return this.lastmodifiedby;
  }

  public void setLastmodifiedby(java.lang.Integer lastmodifiedby) {
    this.lastmodifiedby = lastmodifiedby;
  }
}
