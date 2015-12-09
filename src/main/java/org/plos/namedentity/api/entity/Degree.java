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

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Degree extends Entity {

  private Integer typeid;
  private String  type;
  private String  description;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  public String getDescription() {
      return description;
  }

  public void setDescription(String description) {
      this.description = description;
  }
}
