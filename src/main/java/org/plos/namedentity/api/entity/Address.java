/*
 * Copyright (c) 2014-2019 Public Library of Science
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
package org.plos.namedentity.api.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address extends Entity {

  private Integer typeid;
  private String  type;

  private String addressline1;
  private String addressline2;
  private String addressline3;
  private String city;

  private Integer statecodetypeid;
  private String  statecodetype;

  private Integer countrycodetypeid;
  private String  countrycodetype;

  private String postalcode;

  private Integer maincontactnamedentityid;
  // TODO - how match main contact name: display name? full name?

  private Integer latitude;
  private Integer longitude;
  private Boolean isactive;

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  public String getAddressline1() {
    return this.addressline1;
  }

  public void setAddressline1(String addressline1) {
    this.addressline1 = addressline1;
  }

  public String getAddressline2() {
    return this.addressline2;
  }

  public void setAddressline2(String addressline2) {
    this.addressline2 = addressline2;
  }

  public String getAddressline3() {
    return this.addressline3;
  }

  public void setAddressline3(String addressline3) {
    this.addressline3 = addressline3;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Integer getStatecodetypeid() {
    return this.statecodetypeid;
  }

  public void setStatecodetypeid(Integer statecodetypeid) {
    this.statecodetypeid = statecodetypeid;
  }

  public Integer getCountrycodetypeid() {
    return this.countrycodetypeid;
  }

  public void setCountrycodetypeid(Integer countrycodetypeid) {
    this.countrycodetypeid = countrycodetypeid;
  }

  public String getPostalcode() {
    return this.postalcode;
  }

  public void setPostalcode(String postalcode) {
    this.postalcode = postalcode;
  }

  public Integer getMaincontactnamedentityid() {
    return this.maincontactnamedentityid;
  }

  public void setMaincontactnamedentityid(Integer maincontactnamedentityid) {
    this.maincontactnamedentityid = maincontactnamedentityid;
  }

  public Integer getLatitude() {
    return this.latitude;
  }

  public void setLatitude(Integer latitude) {
    this.latitude = latitude;
  }

  public Integer getLongitude() {
    return this.longitude;
  }

  public void setLongitude(Integer longitude) {
    this.longitude = longitude;
  }

  public Boolean getIsactive() {
    return this.isactive;
  }

  public void setIsactive(Boolean isactive) {
    this.isactive = isactive;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getStatecodetype() {
    return statecodetype;
  }

  public void setStatecodetype(String statecodetype) {
    this.statecodetype = statecodetype;
  }

  public String getCountrycodetype() {
    return countrycodetype;
  }

  public void setCountrycodetype(String countrycodetype) {
    this.countrycodetype = countrycodetype;
  }
}
