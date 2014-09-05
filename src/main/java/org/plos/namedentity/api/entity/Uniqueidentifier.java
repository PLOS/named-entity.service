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

import java.util.Objects;

/**
 * Modified JOOQ generated class(pojo=true).
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Uniqueidentifier extends Entity {

  private Integer id;
  private Integer nedid;
  private Integer typeid;
  private String  type;
  private String  uniqueidentifier;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public String getUniqueidentifier() {
    return this.uniqueidentifier;
  }

  public void setUniqueidentifier(String uniqueidentifier) {
    this.uniqueidentifier = uniqueidentifier;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }

    Uniqueidentifier entity = (Uniqueidentifier) o;
    return Objects.equals(this.id, entity.id)
        && Objects.equals(this.nedid, entity.nedid)
        && Objects.equals(this.typeid, entity.typeid)
        && Objects.equals(this.uniqueidentifier, entity.uniqueidentifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.nedid,
      this.typeid, this.uniqueidentifier);
  }
}
