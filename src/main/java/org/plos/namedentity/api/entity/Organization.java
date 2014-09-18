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
import java.util.Objects;

@XmlRootElement
public class Organization extends ParentEntity {

  private Integer id;
  private Integer nedid;
  private Integer typeid;
  private String  type;
  private String  familiarname;
  private String  legalname;
  private Integer maincontactid;
  private Byte isactive  = 1;
  private Byte isvisible = 1;
  private String  source;
  private Integer sourcetypeid;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Integer getSourcetypeid() {
    return sourcetypeid;
  }

  public void setSourcetypeid(Integer sourcetypeid) {
    this.sourcetypeid = sourcetypeid;
  }

  public Integer getNedid() {
    return this.nedid;
  }

  public void setNedid(Integer nedid) {
    this.nedid = nedid;
  }

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  public String getFamiliarname() {
    return this.familiarname;
  }

  public void setFamiliarname(String familiarname) {
    this.familiarname = familiarname;
  }

  public String getLegalname() {
    return this.legalname;
  }

  public void setLegalname(String legalname) {
    this.legalname = legalname;
  }

  public Integer getMaincontactid() {
    return this.maincontactid;
  }

  public void setMaincontactid(Integer maincontactid) {
    this.maincontactid = maincontactid;
  }

  public Byte getIsactive() {
    return this.isactive;
  }

  public void setIsactive(Byte isactive) {
    this.isactive = isactive;
  }

  public Byte getIsvisible() {
    return this.isvisible;
  }

  public void setIsvisible(Byte isvisible) {
    this.isvisible = isvisible;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Organization)) return false;

    Organization that = (Organization) o;

    return Objects.equals(this.nedid, that.nedid)
        && Objects.equals(this.isactive, that.isactive)
        && Objects.equals(this.isvisible, that.isvisible)
        && Objects.equals(this.familiarname, that.familiarname)
        && Objects.equals(this.legalname, that.legalname)
        && Objects.equals(this.maincontactid, that.maincontactid)
        && Objects.equals(this.typeid, that.typeid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.nedid, this.isactive, this.isvisible,this.familiarname, this.legalname, this.maincontactid,this.typeid);
  }
}
