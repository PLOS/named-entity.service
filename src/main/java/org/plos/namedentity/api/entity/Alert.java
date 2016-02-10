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

import org.plos.namedentity.api.NedException;

import javax.xml.bind.annotation.XmlRootElement;

import static org.plos.namedentity.api.NedException.ErrorType.InvalidJsonError;
import static org.plos.namedentity.validate.JsonValidator.validJson;

/**
 * Created by jfinger on 11/5/15.
 */

@XmlRootElement
public class Alert extends Entity {

  private Integer frequencytypeid;
  private String  frequency;

  private String name;
  private String query;

  @Override
  public void validate() {
    if (query != null) {
      String validMetadataJson = validJson(query);
      if (validMetadataJson == null) {
        throw new NedException(InvalidJsonError, "query field contains invalid JSON : " + query);
      }
      this.query = validMetadataJson;
    }
  }

  public Integer getFrequencytypeid() {
    return frequencytypeid;
  }

  public void setFrequencytypeid(Integer frequencytypeid) {
    this.frequencytypeid = frequencytypeid;
  }

  public String getFrequency() {
    return frequency;
  }

  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
