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

/**
 * Modified JOOQ generated class(pojo=true).
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@XmlRootElement
public class Address extends Entity {

  private static final long serialVersionUID = 2006852801;

  private Integer addressid;
  private Integer namedentityid;

  private Integer addresstypeid;
  private String  addresstype;

  private String  addressline1;
  private String  addressline2;
  private String  addressline3;
  private String  city;

  private Integer statecodetypeid;
  private String  statecodetype;

  private Integer countrycodetypeid;
  private String  countrycodetype;

  private String  postalcode;

  private Integer maincontactnamedentityid;
  // TODO - how match main contact name: display name? full name?

  private Integer latitude;
  private Integer longitude;
  private Byte    isprimary;
  private Byte    isactive;

  public Address() {
    this(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
  }

  public Address(
      Integer addressid,
      Integer namedentityid,
      Integer addresstypeid,
      String addressline1,
      String addressline2,
      String addressline3,
      String city,
      Integer statecodetypeid,
      Integer countrycodetypeid,
      String postalcode,
      Integer maincontactnamedentityid,
      Integer latitude,
      Integer longitude,
      Byte isprimary,
      Byte isactive
                ) {
    this(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
  }

  public Address(
      Integer addressid,
      Integer namedentityid,
      Integer addresstypeid,
      String addresstype,
      String addressline1,
      String addressline2,
      String addressline3,
      String city,
      Integer statecodetypeid,
      String statecodetype,
      Integer countrycodetypeid,
      String countrycodetype,
      String postalcode,
      Integer maincontactnamedentityid,
      Integer latitude,
      Integer longitude,
      Byte isprimary,
      Byte isactive
                ) {
    this.addressid                = addressid;
    this.namedentityid            = namedentityid;
    this.addresstypeid            = addresstypeid;
    this.addresstype              = addresstype;
    this.addressline1             = addressline1;
    this.addressline2             = addressline2;
    this.addressline3             = addressline3;
    this.city                     = city;
    this.statecodetypeid          = statecodetypeid;
    this.statecodetype            = statecodetype;
    this.countrycodetypeid        = countrycodetypeid;
    this.countrycodetype          = countrycodetype;
    this.postalcode               = postalcode;
    this.maincontactnamedentityid = maincontactnamedentityid;
    this.latitude                 = latitude;
    this.longitude                = longitude;
    this.isprimary                = (isprimary != null ? isprimary : (byte)1);
    this.isactive                 = (isactive != null ? isactive : (byte)1);
  }

  public java.lang.Integer getAddressid() {
    return this.addressid;
  }

  public void setAddressid(java.lang.Integer addressid) {
    this.addressid = addressid;
  }

  public java.lang.Integer getNamedentityid() {
    return this.namedentityid;
  }

  public void setNamedentityid(java.lang.Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public java.lang.Integer getAddresstypeid() {
    return this.addresstypeid;
  }

  public void setAddresstypeid(java.lang.Integer addresstypeid) {
    this.addresstypeid = addresstypeid;
  }

  public java.lang.String getAddressline1() {
    return this.addressline1;
  }

  public void setAddressline1(java.lang.String addressline1) {
    this.addressline1 = addressline1;
  }

  public java.lang.String getAddressline2() {
    return this.addressline2;
  }

  public void setAddressline2(java.lang.String addressline2) {
    this.addressline2 = addressline2;
  }

  public java.lang.String getAddressline3() {
    return this.addressline3;
  }

  public void setAddressline3(java.lang.String addressline3) {
    this.addressline3 = addressline3;
  }

  public java.lang.String getCity() {
    return this.city;
  }

  public void setCity(java.lang.String city) {
    this.city = city;
  }

  public java.lang.Integer getStatecodetypeid() {
    return this.statecodetypeid;
  }

  public void setStatecodetypeid(java.lang.Integer statecodetypeid) {
    this.statecodetypeid = statecodetypeid;
  }

  public java.lang.Integer getCountrycodetypeid() {
    return this.countrycodetypeid;
  }

  public void setCountrycodetypeid(java.lang.Integer countrycodetypeid) {
    this.countrycodetypeid = countrycodetypeid;
  }

  public java.lang.String getPostalcode() {
    return this.postalcode;
  }

  public void setPostalcode(java.lang.String postalcode) {
    this.postalcode = postalcode;
  }

  public java.lang.Integer getMaincontactnamedentityid() {
    return this.maincontactnamedentityid;
  }

  public void setMaincontactnamedentityid(java.lang.Integer maincontactnamedentityid) {
    this.maincontactnamedentityid = maincontactnamedentityid;
  }

  public java.lang.Integer getLatitude() {
    return this.latitude;
  }

  public void setLatitude(java.lang.Integer latitude) {
    this.latitude = latitude;
  }

  public java.lang.Integer getLongitude() {
    return this.longitude;
  }

  public void setLongitude(java.lang.Integer longitude) {
    this.longitude = longitude;
  }

  public java.lang.Byte getIsprimary() {
    return this.isprimary;
  }

  public void setIsprimary(java.lang.Byte isprimary) {
    this.isprimary = isprimary;
  }

  public java.lang.Byte getIsactive() {
    return this.isactive;
  }

  public void setIsactive(java.lang.Byte isactive) {
    this.isactive = isactive;
  }

  public String getAddresstype() {
      return addresstype;
  }

  public void setAddresstype(String addresstype) {
      this.addresstype = addresstype;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }

    if (o == null || this.getClass() != o.getClass()) { return false; }

    Address entity = (Address) o;
    return    Objects.equals(this.addressid, entity.addressid)
           && Objects.equals(this.namedentityid, entity.namedentityid)
           && Objects.equals(this.addresstypeid, entity.addresstypeid)
           && Objects.equals(this.addresstype, entity.addresstype)
           && Objects.equals(this.addressline1, entity.addressline1)
           && Objects.equals(this.city, entity.city)
           && Objects.equals(this.statecodetypeid, entity.statecodetypeid)
           && Objects.equals(this.statecodetype, entity.statecodetype)
           && Objects.equals(this.countrycodetypeid, entity.countrycodetypeid)
           && Objects.equals(this.countrycodetype, entity.countrycodetype);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addressid, namedentityid, addresstypeid, addressline1, 
        city, statecodetypeid, statecodetype, countrycodetypeid, countrycodetype);
  }
}
