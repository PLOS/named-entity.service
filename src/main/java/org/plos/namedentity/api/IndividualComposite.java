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

import java.util.List;

public class IndividualComposite {

  private String namedentityid;
  private String firstname;
  private String middlename;
  private String lastname;
  private String nameprefix;
  private String namesuffix;
  private String preferredlanguage;
  private String preferredcommunication;

  private RoleDTO                   role;
  private List<AddressDTO>          addresses;
  private List<EmailDTO>            emails;
  private List<PhonenumberDTO>      phonenumbers;
  private List<UniqueidentifierDTO> uniqueidentifiers;

  public String getNamedentityid() {
    return namedentityid;
  }
  public void setNamedentityid(String namedentityid) {
    this.namedentityid = namedentityid;
  }

  public String getFirstname() {
    return firstname;
  }
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getMiddlename() {
    return middlename;
  }
  public void setMiddlename(String middlename) {
    this.middlename = middlename;
  }

  public String getLastname() {
    return lastname;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getNameprefix() {
    return nameprefix;
  }
  public void setNameprefix(String nameprefix) {
    this.nameprefix = nameprefix;
  }

  public String getNamesuffix() {
    return namesuffix;
  }
  public void setNamesuffix(String namesuffix) {
    this.namesuffix = namesuffix;
  }

  public String getPreferredlanguage() {
    return preferredlanguage;
  }
  public void setPreferredlanguage(String preferredlanguage) {
    this.preferredlanguage = preferredlanguage;
  }

  public String getPreferredcommunication() {
    return preferredcommunication;
  }
  public void setPreferredcommunication(String preferredcommunication) {
    this.preferredcommunication = preferredcommunication;
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
  
  public List<UniqueidentifierDTO> getUniqueIdentifiers() {
    return uniqueidentifiers;
  }
  public void setUniqueIdentifiers(List<UniqueidentifierDTO> uniqueidentifiers) {
    this.uniqueidentifiers = uniqueidentifiers;
  }
}
