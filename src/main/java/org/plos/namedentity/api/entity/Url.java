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

import org.apache.commons.validator.routines.UrlValidator;
import org.plos.namedentity.api.NedValidationException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Url extends Entity {

  private Integer id;
  private Integer nedid;
  private String  url;

  private static UrlValidator urlValidator = UrlValidator.getInstance();

  @Override
  public void validate() {
    if (url != null && !urlValidator.isValid(url))
      throw new NedValidationException("URL not valid (" + url + ")");
  }

  public Integer getId() {
    return id;
  }

  public Integer getNedid() {
    return nedid;
  }

  public void setNedid(Integer nedid) {
    this.nedid = nedid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
