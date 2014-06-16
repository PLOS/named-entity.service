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
public class SubjectareaEntity implements java.io.Serializable {

  private static final long serialVersionUID = 169794521;

  private java.lang.Integer subjectareaid;
  private java.lang.Integer namedentityid;
  private java.lang.Integer subjectareatypeid;

  public SubjectareaEntity() {}

  public SubjectareaEntity(
    java.lang.Integer subjectareaid,
    java.lang.Integer namedentityid,
    java.lang.Integer subjectareatypeid
  ) {
    this.subjectareaid     = subjectareaid;
    this.namedentityid     = namedentityid;
    this.subjectareatypeid = subjectareatypeid;
  }

  public java.lang.Integer getSubjectareaid() {
    return this.subjectareaid;
  }

  public void setSubjectareaid(java.lang.Integer subjectareaid) {
    this.subjectareaid = subjectareaid;
  }

  public java.lang.Integer getNamedentityid() {
    return this.namedentityid;
  }

  public void setNamedentityid(java.lang.Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public java.lang.Integer getSubjectareatypeid() {
    return this.subjectareatypeid;
  }

  public void setSubjectareatypeid(java.lang.Integer subjectareatypeid) {
    this.subjectareatypeid = subjectareatypeid;
  }
}
