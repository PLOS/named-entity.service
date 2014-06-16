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

/**
 * Modified JOOQ generated class(pojo=true).
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SourcefieldEntity implements java.io.Serializable {

  private static final long serialVersionUID = -2013224879;

  private java.lang.Integer sourcefieldid;
  private java.lang.String  sourcetable;
  private java.lang.String  sourcefield;

  public SourcefieldEntity() {}

  public SourcefieldEntity(
    java.lang.Integer sourcefieldid,
    java.lang.String  sourcetable,
    java.lang.String  sourcefield
  ) {
    this.sourcefieldid = sourcefieldid;
    this.sourcetable   = sourcetable;
    this.sourcefield   = sourcefield;
  }

  public java.lang.Integer getSourcefieldid() {
    return this.sourcefieldid;
  }

  public void setSourcefieldid(java.lang.Integer sourcefieldid) {
    this.sourcefieldid = sourcefieldid;
  }

  public java.lang.String getSourcetable() {
    return this.sourcetable;
  }

  public void setSourcetable(java.lang.String sourcetable) {
    this.sourcetable = sourcetable;
  }

  public java.lang.String getSourcefield() {
    return this.sourcefield;
  }

  public void setSourcefield(java.lang.String sourcefield) {
    this.sourcefield = sourcefield;
  }
}
