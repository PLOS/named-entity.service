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

import java.util.Objects;

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
  private Boolean isactive = true;
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }

    Address entity = (Address) o;
    return Objects.equals(this.type, entity.type)
        && Objects.equals(this.addressline1, entity.addressline1)
        && Objects.equals(this.addressline2, entity.addressline2)
        && Objects.equals(this.addressline3, entity.addressline3)
        && Objects.equals(this.city, entity.city)
        && Objects.equals(this.statecodetype, entity.statecodetype)
        && Objects.equals(this.countrycodetype, entity.countrycodetype)
        && Objects.equals(this.postalcode, entity.postalcode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, addressline1, addressline2, addressline3,
        city, statecodetype, countrycodetype, postalcode);
  }
  
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
