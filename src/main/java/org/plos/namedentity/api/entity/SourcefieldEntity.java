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
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SourcefieldEntity implements java.io.Serializable {

  private static final long serialVersionUID = -2013224879;

  private Integer sourcefieldid;
  private String  sourcetable;
  private String  sourcefield;

  public SourcefieldEntity() {}

  public SourcefieldEntity(
    Integer sourcefieldid,
    String  sourcetable,
    String  sourcefield
  ) {
    this.sourcefieldid = sourcefieldid;
    this.sourcetable   = sourcetable;
    this.sourcefield   = sourcefield;
  }

  public Integer getSourcefieldid() {
    return this.sourcefieldid;
  }

  public void setSourcefieldid(Integer sourcefieldid) {
    this.sourcefieldid = sourcefieldid;
  }

  public String getSourcetable() {
    return this.sourcetable;
  }

  public void setSourcetable(String sourcetable) {
    this.sourcetable = sourcetable;
  }

  public String getSourcefield() {
    return this.sourcefield;
  }

  public void setSourcefield(String sourcefield) {
    this.sourcefield = sourcefield;
  }
}
