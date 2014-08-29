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

/**
 * Modified JOOQ generated class(pojo=true).
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@XmlRootElement
public class Degree extends Entity {

  private static final long serialVersionUID = -1999999966;

  private Integer degreeid;
  private Integer namedentityid;
  private Integer degreetypeid;
  private String  degreetype;

  public String getDegreetype() {
    return degreetype;
  }

  public void setDegreetype(String degreetype) {
    this.degreetype = degreetype;
  }

  public Integer getDegreeid() {
    return this.degreeid;
  }

  public void setDegreeid(Integer degreeid) {
    this.degreeid = degreeid;
  }

  public Integer getNamedentityid() {
    return this.namedentityid;
  }

  public void setNamedentityid(Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public Integer getDegreetypeid() {
    return this.degreetypeid;
  }

  public void setDegreetypeid(Integer degreetypeid) {
    this.degreetypeid = degreetypeid;
  }
}
