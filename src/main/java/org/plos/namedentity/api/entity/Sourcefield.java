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

public class Sourcefield extends Entity {

  private Integer id;
  private String  sourcetable;
  private String  sourcefield;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
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
