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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@XmlRootElement
public class Typedescription {

  private Integer id;
  private String  description;
  private String  howused;

  private Timestamp created;
  private Timestamp lastmodified;

  private static String EXCLUDED_FIELDS[] = { "created", "lastmodified" };

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o, EXCLUDED_FIELDS);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, EXCLUDED_FIELDS);
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHowused() {
    return this.howused;
  }

  public void setHowused(String howused) {
    this.howused = howused;
  }

  public Timestamp getLastmodified() {
    return this.lastmodified;
  }

  public void setLastmodified(Timestamp lastmodified) {
    this.lastmodified = lastmodified;
  }

  public Timestamp getCreated() {
    return this.created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }
}
