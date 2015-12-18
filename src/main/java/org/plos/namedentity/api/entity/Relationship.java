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

import static org.plos.namedentity.api.NedException.ErrorType.*;

import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.adapter.DateAdapter;
import org.plos.namedentity.api.enums.RelationshipTypeEnum;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;
import java.util.Date;

@XmlRootElement
public class Relationship extends Entity {

  private Integer nedidrelated;

  private Integer typeid;
  private String  type;
  private String  title;

  @XmlJavaTypeAdapter(DateAdapter.class)
  private Date startdate;

  @XmlJavaTypeAdapter(DateAdapter.class)
  private Date enddate;

  @Override
  public void validate() {
    if (type == null || !validateRelationType(type))
      throw new NedException(InvalidRelationshipError, "Relationship type not valid");
  }

  public Integer getNedidrelated() {
    return nedidrelated;
  }

  public void setNedidrelated(Integer nedidrelated) {
    this.nedidrelated = nedidrelated;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  public Date getStartdate() {
    return this.startdate;
  }

  public void setStartdate(Date startdate) {
    this.startdate = startdate;
  }

  public Date getEnddate() {
    return this.enddate;
  }

  public void setEnddate(Date enddate) {
    this.enddate = enddate;
  }

  public String getType() {
      return type;
  }

  public void setType(String type) {
      this.type = type;
  }

  public static boolean validateRelationType(String relType) {
    RelationshipTypeEnum relEnum = RelationshipTypeEnum.getRelationshipTypeEnum(relType);
    return (relEnum != RelationshipTypeEnum.INVALID_RELATIONSHIP_TYPE);
  }
}
