/*
 * Copyright (c) 2017 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
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
