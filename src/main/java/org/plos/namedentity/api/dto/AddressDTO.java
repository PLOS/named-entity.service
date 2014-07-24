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
package org.plos.namedentity.api.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddressDTO implements java.io.Serializable {

  private Integer addressid;
  private Integer namedentityid;
  private String  addresstype;
  private String  addressline1;
  private String  addressline2;
  private String  addressline3;
  private String  city;
  private String  statecodetype;
  private String  countrycodetype;
  private String  postalcode;
  private Boolean isprimary;

  public AddressDTO() {}

  public AddressDTO(
    Integer addressid,
    Integer namedentityid,
    String  addresstype,
    String  addressline1,
    String  addressline2,
    String  addressline3,
    String  city,
    String  statecodetype,
    String  countrycodetype,
    String  postalcode,
    Boolean isprimary
  ) {
    this.addressid       = addressid;
    this.namedentityid   = namedentityid;
    this.addresstype     = addresstype;
    this.addressline1    = addressline1;
    this.addressline2    = addressline2;
    this.addressline3    = addressline3;
    this.city            = city;
    this.statecodetype   = statecodetype;
    this.countrycodetype = countrycodetype;
    this.postalcode      = postalcode;
    this.isprimary       = isprimary;
  }
  
  public Integer getAddressid() {
    return addressid;
  }
  public void setAddressid(Integer addressid) {
    this.addressid = addressid;
  }
  
  public Integer getNamedentityid() {
    return namedentityid;
  }
  public void setNamedentityid(Integer namedentityid) {
    this.namedentityid = namedentityid;
  }
  
  public String getAddresstype() {
    return addresstype;
  }
  public void setAddresstype(String addresstype) {
    this.addresstype = addresstype;
  }
  
  public String getAddressline1() {
    return addressline1;
  }
  public void setAddressline1(String addressline1) {
    this.addressline1 = addressline1;
  }
  
  public String getAddressline2() {
    return addressline2;
  }
  public void setAddressline2(String addressline2) {
      this.addressline2 = addressline2;
  }
  
  public String getAddressline3() {
    return addressline3;
  }
  public void setAddressline3(String addressline3) {
    this.addressline3 = addressline3;
  }
  
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
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
  
  public String getPostalcode() {
    return postalcode;
  }
  public void setPostalcode(String postalcode) {
    this.postalcode = postalcode;
  }
  
  public Boolean getIsprimary() {
    return isprimary;
  }
  public void setIsprimary(Boolean isprimary) {
    this.isprimary = isprimary;
  }
}
