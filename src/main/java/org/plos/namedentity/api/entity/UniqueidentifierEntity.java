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
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UniqueidentifierEntity implements java.io.Serializable {

  private static final long serialVersionUID = 1900442676;

  private java.lang.Integer uniqueidentifiersid;
  private java.lang.Integer namedentityid;
  private java.lang.Integer uniqueidentifiertypeid;
  private java.lang.String  uniqueidentifier;

  public UniqueidentifierEntity() {}

  public UniqueidentifierEntity(
    java.lang.Integer uniqueidentifiersid,
    java.lang.Integer namedentityid,
    java.lang.Integer uniqueidentifiertypeid,
    java.lang.String  uniqueidentifier
  ) {
    this.uniqueidentifiersid    = uniqueidentifiersid;
    this.namedentityid          = namedentityid;
    this.uniqueidentifiertypeid = uniqueidentifiertypeid;
    this.uniqueidentifier       = uniqueidentifier;
  }

  public java.lang.Integer getUniqueidentifiersid() {
    return this.uniqueidentifiersid;
  }

  public void setUniqueidentifiersid(java.lang.Integer uniqueidentifiersid) {
    this.uniqueidentifiersid = uniqueidentifiersid;
  }

  public java.lang.Integer getNamedentityid() {
    return this.namedentityid;
  }

  public void setNamedentityid(java.lang.Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public java.lang.Integer getUniqueidentifiertypeid() {
    return this.uniqueidentifiertypeid;
  }

  public void setUniqueidentifiertypeid(java.lang.Integer uniqueidentifiertypeid) {
    this.uniqueidentifiertypeid = uniqueidentifiertypeid;
  }

  public java.lang.String getUniqueidentifier() {
    return this.uniqueidentifier;
  }

  public void setUniqueidentifier(java.lang.String uniqueidentifier) {
    this.uniqueidentifier = uniqueidentifier;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }

    if (o == null || this.getClass() != o.getClass()) { return false; }

    UniqueidentifierEntity entity = (UniqueidentifierEntity) o;
    return Objects.equals(this.uniqueidentifiersid, entity.uniqueidentifiersid)
        && Objects.equals(this.namedentityid, entity.namedentityid)
        && Objects.equals(this.uniqueidentifiertypeid, entity.uniqueidentifiertypeid)
        && Objects.equals(this.uniqueidentifier, entity.uniqueidentifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.uniqueidentifiersid, this.namedentityid, 
      this.uniqueidentifiertypeid, this.uniqueidentifier);
  }
}
