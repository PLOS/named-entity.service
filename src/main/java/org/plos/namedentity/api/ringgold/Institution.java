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
package org.plos.namedentity.api.ringgold;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Institution {

  private Integer recId;
  private Integer ringgoldId;
  private Integer parentRinggoldId;
  private Integer topRinggoldId;

  private String name;
  private String city;
  private String state;
  private String postCode;
  private String country;
  private String type;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }

  public String getPostCode() {
    return postCode;
  }
  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }

  @XmlElement(name = "institution-id")
  public Integer getRinggoldId() {
    return ringgoldId;
  }
  @XmlElement(name = "institution-id")
  public void setRinggoldId(Integer ringgoldId) {
    this.ringgoldId = ringgoldId;
  }

  @XmlTransient public Integer getParentRinggoldId() {
    return parentRinggoldId;
  }
  @XmlTransient public void setParentRinggoldId(Integer parentRinggoldId) {
    this.parentRinggoldId = parentRinggoldId;
  }

  @XmlTransient public Integer getRecId() {
    return recId;
  }
  @XmlTransient public void setRecId(Integer recId) {
    this.recId = recId;
  }

  @XmlTransient public Integer getTopRinggoldId() {
    return topRinggoldId;
  }
  @XmlTransient public void setTopRinggoldId(Integer topRinggoldId) {
    this.topRinggoldId = topRinggoldId;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
