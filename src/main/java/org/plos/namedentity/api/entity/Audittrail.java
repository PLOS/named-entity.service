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

import java.sql.Timestamp;

public class Audittrail {

  private Integer   id;
  private Integer   sourcefieldid;
  private Integer   rownumber;
  private String    oldvalue;
  private String    newvalue;
  private Timestamp created;
  private Timestamp lastmodified;
  private Integer   lastmodifiedby;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getSourcefieldid() {
    return this.sourcefieldid;
  }

  public void setSourcefieldid(Integer sourcefieldid) {
    this.sourcefieldid = sourcefieldid;
  }

  public Integer getRownumber() {
    return this.rownumber;
  }

  public void setRownumber(Integer rownumber) {
    this.rownumber = rownumber;
  }

  public String getOldvalue() {
    return this.oldvalue;
  }

  public void setOldvalue(String oldvalue) {
    this.oldvalue = oldvalue;
  }

  public String getNewvalue() {
    return this.newvalue;
  }

  public void setNewvalue(String newvalue) {
    this.newvalue = newvalue;
  }

  public java.sql.Timestamp getLastmodified() {
    return this.lastmodified;
  }

  public void setLastmodified(java.sql.Timestamp lastmodified) {
    this.lastmodified = lastmodified;
  }

  public Integer getLastmodifiedby() {
    return this.lastmodifiedby;
  }

  public void setLastmodifiedby(Integer lastmodifiedby) {
    this.lastmodifiedby = lastmodifiedby;
  }

  public Timestamp getCreated() {
    return this.created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }
}
