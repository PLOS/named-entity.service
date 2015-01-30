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

import org.plos.namedentity.api.adapter.DateAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@XmlRootElement
public class Role extends Entity {

  private Integer typeid;
  private String  type;

  private Date startdate;
  private Date enddate;

  private Integer createdby;
  private Integer lastmodifiedby;

  private String  applicationtype;
  private Integer applicationtypeid;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || this.getClass() != o.getClass())
      return false;

    Role entity = (Role) o;
    return Objects.equals(this.applicationtype, entity.applicationtype)
        && Objects.equals(this.type, entity.type)
        && Objects.equals(this.startdate, entity.startdate)
        && Objects.equals(this.enddate, entity.enddate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationtype, type, startdate, enddate);
  }

  private Timestamp lastmodified;


  public String getApplicationtype() {
    return applicationtype;
  }

  public void setApplicationtype(String applicationtype) {
    this.applicationtype = applicationtype;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getApplicationtypeid() {
    return this.applicationtypeid;
  }

  public void setApplicationtypeid(Integer applicationtypeid) {
    this.applicationtypeid = applicationtypeid;
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

}
