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

public class UniqueidentifierDTO {

  private Integer uniqueidentifiersid;
  private Integer namedentityid;
  private String  uniqueidentifiertype;
  private String  uniqueidentifier;

  public UniqueidentifierDTO() {}

  public UniqueidentifierDTO(
    Integer uniqueidentifiersid,
    Integer namedentityid,
    String  uniqueidentifiertype,
    String  uniqueidentifier
  ) {
    this.uniqueidentifiersid  = uniqueidentifiersid;
    this.namedentityid        = namedentityid;
    this.uniqueidentifiertype = uniqueidentifiertype;
    this.uniqueidentifier     = uniqueidentifier;
  }
  
  public Integer getUniqueidentifiersid() {
    return uniqueidentifiersid;
  }
  public void setUniqueidentifiersid(Integer uniqueidentifiersid) {
    this.uniqueidentifiersid = uniqueidentifiersid;
  }
  
  public Integer getNamedentityid() {
    return namedentityid;
  }
  public void setNamedentityid(Integer namedentityid) {
    this.namedentityid = namedentityid;
  }
  
  public String getUniqueidentifiertype() {
    return uniqueidentifiertype;
  }
  public void setUniqueidentifiertype(String uniqueidentifiertype) {
    this.uniqueidentifiertype = uniqueidentifiertype;
  }
  
  public String getUniqueidentifier() {
    return uniqueidentifier;
  }
  public void setUniqueidentifier(String uniqueidentifier) {
    this.uniqueidentifier = uniqueidentifier;
  }
}
