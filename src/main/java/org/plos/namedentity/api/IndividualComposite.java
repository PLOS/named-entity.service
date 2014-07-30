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
package org.plos.namedentity.api;

import org.plos.namedentity.api.dto.AddressDTO;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.dto.PhonenumberDTO;
import org.plos.namedentity.api.dto.RoleDTO;
import org.plos.namedentity.api.dto.UniqueidentifierDTO;
import org.plos.namedentity.api.entity.IndividualEntity;

import java.util.List;

public class IndividualComposite {
//
//  private String namedentityid;
//  private String firstname;
//  private String middlename;
//  private String lastname;
//  private String nameprefix;
//  private String namesuffix;
//  private String preferredlanguage;
//  private String preferredcommunication;
//
  private IndividualEntity entity;

  private RoleDTO                   role;
  private List<AddressDTO>          addresses;
  private List<EmailDTO>            emails;
  private List<PhonenumberDTO>      phonenumbers;
  private List<UniqueidentifierDTO> uniqueidentifiers;

  public IndividualComposite() {
    this.entity = new IndividualEntity();
  }

  public Integer getNamedentityid() {
    return this.entity.getNamedentityid();
  }
  public void setNamedentityid(Integer namedentityid) {
    this.entity.setNamedentityid(namedentityid);
  }

  public String getFirstname() {
    return this.entity.getFirstname();
  }
  public void setFirstname(String firstname) {
    this.entity.setFirstname(firstname);
  }

  public String getMiddlename() {
    return this.entity.getMiddlename();
  }
  public void setMiddlename(String middlename) {
    this.entity.setMiddlename(middlename);
  }

  public String getLastname() {
    return this.entity.getLastname();
  }
  public void setLastname(String lastname) {
    this.entity.setLastname(lastname);
  }

  public String getNameprefix() {
    return this.entity.getNameprefix();
  }
  public void setNameprefix(String nameprefix) {
    this.entity.setNameprefix(nameprefix);
  }

  public String getNamesuffix() {
    return this.entity.getNamesuffix();
  }
  public void setNamesuffix(String namesuffix) {
    this.entity.setNameprefix(namesuffix);
  }

  public String getPreferredlanguage() {
    return this.entity.getPreferredlanguage();
  }
  public void setPreferredlanguage(String preferredlanguage) {
    this.entity.setPreferredlanguage(preferredlanguage);
  }

  public String getPreferredcommunication() {
    return this.entity.getPreferredcommunication();
  }
  public void setPreferredcommunication(String preferredcommunication) {
    this.entity.setPreferredcommunication(preferredcommunication);
  }

  public RoleDTO getRole() {
    return role;
  }
  public void setRole(RoleDTO role) {
    this.role = role;
  }
  
  public List<AddressDTO> getAddresses() {
    return addresses;
  }
  public void setAddresses(List<AddressDTO> addresses) {
    this.addresses = addresses;
  }
  
  public List<EmailDTO> getEmails() {
    return emails;
  }
  public void setEmails(List<EmailDTO> emails) {
    this.emails = emails;
  }
  
  public List<PhonenumberDTO> getPhonenumbers() {
    return phonenumbers;
  }
  public void setPhonenumbers(List<PhonenumberDTO> phonenumbers) {
    this.phonenumbers = phonenumbers;
  }
  
  public List<UniqueidentifierDTO> getUniqueidentifiers() {
    return uniqueidentifiers;
  }
  public void setUniqueidentifiers(List<UniqueidentifierDTO> uniqueidentifiers) {
    this.uniqueidentifiers = uniqueidentifiers;
  }
}
