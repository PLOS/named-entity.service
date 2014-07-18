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

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class TypedescriptionDTO {

  private Integer typeid;
  private String  description;
  private String  howused;

  public TypedescriptionDTO() {}

  public TypedescriptionDTO(
    java.lang.Integer typeid,
    java.lang.String  description,
    java.lang.String  howused
  ) {
    this.typeid      = typeid;
    this.description = description;
    this.howused     = howused;
  }

  public java.lang.Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(java.lang.Integer typeid) {
    this.typeid = typeid;
  }

  public java.lang.String getDescription() {
    return this.description;
  }

  public void setDescription(java.lang.String description) {
    this.description = description;
  }

  public java.lang.String getHowused() {
    return this.howused;
  }

  public void setHowused(java.lang.String howused) {
    this.howused = howused;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }

    if (o == null || this.getClass() != o.getClass()) { return false; }

    TypedescriptionDTO dto = (TypedescriptionDTO) o;
    return    Objects.equals(this.typeid, dto.typeid)
           && Objects.equals(this.description, dto.description)
           && Objects.equals(this.howused, dto.howused);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.typeid, this.description, this.howused);
  }
}
