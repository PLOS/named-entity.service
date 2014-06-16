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

public class DegreesDTO {

  private java.lang.Integer degreeid;
  private java.lang.Integer namedentityid;
  private java.lang.Integer degreetypeid;

  public DegreesDTO() {}

  public DegreesDTO(
    java.lang.Integer degreeid,
    java.lang.Integer namedentityid,
    java.lang.Integer degreetypeid
  ) {
    this.degreeid      = degreeid;
    this.namedentityid = namedentityid;
    this.degreetypeid  = degreetypeid;
  }

  public java.lang.Integer getDegreeid() {
    return this.degreeid;
  }

  public void setDegreeid(java.lang.Integer degreeid) {
    this.degreeid = degreeid;
  }

  public java.lang.Integer getNamedentityid() {
    return this.namedentityid;
  }

  public void setNamedentityid(java.lang.Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public java.lang.Integer getDegreetypeid() {
    return this.degreetypeid;
  }

  public void setDegreetypeid(java.lang.Integer degreetypeid) {
    this.degreetypeid = degreetypeid;
  }
}
