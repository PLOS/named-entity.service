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
public class UniqueidentifierEntity implements java.io.Serializable {

  private static final long serialVersionUID = 1900442676;

  private Integer uniqueidentifiersid;
  private Integer namedentityid;
  private String  uniqueidentifiertype;
  private Integer uniqueidentifiertypeid;
  private String  uniqueidentifier;

  public UniqueidentifierEntity() {}


  public UniqueidentifierEntity(
      Integer uniqueidentifiersid,
      Integer namedentityid,
      Integer uniqueidentifiertypeid,
      String  uniqueidentifier,
      String  uniqueidentifiertype) {

    this(uniqueidentifiersid, namedentityid, uniqueidentifiertypeid, uniqueidentifier);
    this.uniqueidentifiertype = uniqueidentifiertype;
  }

  public UniqueidentifierEntity(
    Integer uniqueidentifiersid,
    Integer namedentityid,
    Integer uniqueidentifiertypeid,
    String  uniqueidentifier
  ) {
    this.uniqueidentifiersid    = uniqueidentifiersid;
    this.namedentityid          = namedentityid;
    this.uniqueidentifiertypeid = uniqueidentifiertypeid;
    this.uniqueidentifier       = uniqueidentifier;
  }

  public String getUniqueidentifiertype() {
    return uniqueidentifiertype;
  }

  public void setUniqueidentifiertype(String uniqueidentifiertype) {
    this.uniqueidentifiertype = uniqueidentifiertype;
  }

  public Integer getUniqueidentifiersid() {
    return this.uniqueidentifiersid;
  }

  public void setUniqueidentifiersid(Integer uniqueidentifiersid) {
    this.uniqueidentifiersid = uniqueidentifiersid;
  }

  public Integer getNamedentityid() {
    return this.namedentityid;
  }

  public void setNamedentityid(Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public Integer getUniqueidentifiertypeid() {
    return this.uniqueidentifiertypeid;
  }

  public void setUniqueidentifiertypeid(Integer uniqueidentifiertypeid) {
    this.uniqueidentifiertypeid = uniqueidentifiertypeid;
  }

  public String getUniqueidentifier() {
    return this.uniqueidentifier;
  }

  public void setUniqueidentifier(String uniqueidentifier) {
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
