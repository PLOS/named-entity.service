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

/**
 * Modified JOOQ generated class(pojo=true).
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@XmlRootElement
public class TypedescriptionEntity implements java.io.Serializable {

  private static final long serialVersionUID = -356395330;

  private Integer typeid;
  private String  description;
  private String  howused;

  public TypedescriptionEntity() {}

  public TypedescriptionEntity(
    Integer typeid,
    String  description,
    String  howused
  ) {
    this.typeid      = typeid;
    this.description = description;
    this.howused     = howused;
  }

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHowused() {
    return this.howused;
  }

  public void setHowused(String howused) {
    this.howused = howused;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }

    if (o == null || this.getClass() != o.getClass()) { return false; }

    TypedescriptionEntity entity = (TypedescriptionEntity) o;
    return Objects.equals(this.typeid, entity.typeid)
        && Objects.equals(this.description, entity.description)
        && Objects.equals(this.howused, entity.howused);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.typeid, this.description, this.howused);
  }
}
