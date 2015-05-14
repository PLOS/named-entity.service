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

  private Integer rec_id;     // primary key
  private Integer p_code;     // parent code (aka, ringgold id)
  private Integer gp_code;    // grandparent code
  private String  name;
  private String  type;
  private String  city;
  private String  state;
  private String  country;

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
  
  public String getCountry() {
      return country;
  }
  public void setCountry(String country) {
      this.country = country;
  }
  
  @XmlElement(name = "institution-id")
  public Integer getPCode() {
      return p_code;
  }
  @XmlElement(name = "institution-id")
  public void setPCode(Integer p_code) {
      this.p_code = p_code;
  }
  
  @XmlTransient public Integer getGpCode() {
      return gp_code;
  }
  @XmlTransient public void setGpCode(Integer gp_code) {
      this.gp_code = gp_code;
  }

  @XmlTransient public Integer getRecId() {
      return rec_id;
  }
  @XmlTransient public void setRecId(Integer rec_id) {
      this.rec_id = rec_id;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
