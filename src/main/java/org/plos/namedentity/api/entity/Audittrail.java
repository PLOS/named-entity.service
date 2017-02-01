/*
 * Copyright (c) 2017 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
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
