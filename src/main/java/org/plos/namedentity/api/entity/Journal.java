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
public class Journal extends Entity {

  private static final long serialVersionUID = 254575195;

  private java.lang.Integer journalid;
  private java.lang.Integer namedentityid;
  private java.lang.Integer journaltypeid;

  public Journal() {}

  public Journal(
      java.lang.Integer journalid,
      java.lang.Integer namedentityid,
      java.lang.Integer journaltypeid
                ) {
    this.journalid     = journalid;
    this.namedentityid = namedentityid;
    this.journaltypeid = journaltypeid;
  }

  public java.lang.Integer getJournalid() {
    return this.journalid;
  }

  public void setJournalid(java.lang.Integer journalid) {
    this.journalid = journalid;
  }

  public java.lang.Integer getNamedentityid() {
    return this.namedentityid;
  }

  public void setNamedentityid(java.lang.Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public java.lang.Integer getJournaltypeid() {
    return this.journaltypeid;
  }

  public void setJournaltypeid(java.lang.Integer journaltypeid) {
    this.journaltypeid = journaltypeid;
  }
}
