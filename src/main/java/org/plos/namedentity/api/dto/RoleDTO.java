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

public class RoleDTO {

  private Integer roleid;
  private Integer namedentityid;
  private String  sourceapplication;
  private String  roletype;
  private String  startdate;
  private String  enddate;

  public RoleDTO() {}

  public RoleDTO(
    Integer roleid,
    Integer namedentityid,
    String  sourceapplication,
    String  roletype,
    String  startdate,
    String  enddate
  ) {
    this.roleid            = roleid;
    this.namedentityid     = namedentityid;
    this.sourceapplication = sourceapplication;
    this.roletype          = roletype;
    this.startdate         = startdate;
    this.enddate           = enddate;
  }

  public java.lang.Integer getRoleid() {
    return this.roleid;
  }
  public void setRoleid(java.lang.Integer roleid) {
    this.roleid = roleid;
  }

  public java.lang.Integer getNamedentityid() {
    return this.namedentityid;
  }
  public void setNamedentityid(java.lang.Integer namedentityid) {
    this.namedentityid = namedentityid;
  }
  
  public String getSourceapplication() {
    return sourceapplication;
  }
  public void setSourceapplication(String sourceapplication) {
    this.sourceapplication = sourceapplication;
  }
  
  public String getRoletype() {
    return roletype;
  }
  public void setRoletype(String roletype) {
    this.roletype = roletype;
  }
  
  public String getStartdate() {
    return startdate;
  }
  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }
  
  public String getEnddate() {
    return enddate;
  }
  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }
}
