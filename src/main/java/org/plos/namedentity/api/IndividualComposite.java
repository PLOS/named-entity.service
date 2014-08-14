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

import org.plos.namedentity.api.entity.AddressEntity;
import org.plos.namedentity.api.entity.DegreeEntity;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.PhonenumberEntity;
import org.plos.namedentity.api.entity.RoleEntity;
import org.plos.namedentity.api.entity.UniqueidentifierEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
public class IndividualComposite {

  private IndividualEntity             individual;
  private List<RoleEntity>             roles;
  private List<AddressEntity>          addresses;
  private List<EmailEntity>            emails;
  private List<PhonenumberEntity>      phonenumbers;
  private List<UniqueidentifierEntity> uniqueidentifiers;
  private List<DegreeEntity>           degrees;

  public IndividualComposite() {
    this.individual = new IndividualEntity();
  }

  public List<DegreeEntity> getDegrees() {
    return degrees;
  }

  public void setDegrees(List<DegreeEntity> degrees) {
    this.degrees = degrees;
  }

  @XmlTransient
  public IndividualEntity getIndividual() {
    return individual;
  }

  public void setIndividual(IndividualEntity individual) {
    this.individual = individual;
  }

  public Integer getNamedentityid() {
    return this.individual.getNamedentityid();
  }

  public void setNamedentityid(Integer namedentityid) {
    this.individual.setNamedentityid(namedentityid);
  }

  public String getFirstname() {
    return this.individual.getFirstname();
  }

  public void setFirstname(String firstname) {
    this.individual.setFirstname(firstname);
  }

  public String getMiddlename() {
    return this.individual.getMiddlename();
  }

  public void setMiddlename(String middlename) {
    this.individual.setMiddlename(middlename);
  }

  public String getLastname() {
    return this.individual.getLastname();
  }

  public void setLastname(String lastname) {
    this.individual.setLastname(lastname);
  }

  public String getNameprefix() {
    return this.individual.getNameprefix();
  }

  public void setNameprefix(String nameprefix) {
    this.individual.setNameprefix(nameprefix);
  }

  public String getNamesuffix() {
    return this.individual.getNamesuffix();
  }

  public void setNamesuffix(String namesuffix) {
    this.individual.setNamesuffix(namesuffix);
  }

  public String getPreferredlanguage() {
    return this.individual.getPreferredlanguage();
  }

  public void setPreferredlanguage(String preferredlanguage) {
    this.individual.setPreferredlanguage(preferredlanguage);
  }

  public String getPreferredcommunication() {
    return this.individual.getPreferredcommunication();
  }

  public void setPreferredcommunication(String preferredcommunication) {
    this.individual.setPreferredcommunication(preferredcommunication);
  }

  public List<RoleEntity> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleEntity> roles) {
    this.roles = roles;
  }

  public List<AddressEntity> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<AddressEntity> addresses) {
    this.addresses = addresses;
  }

  public List<EmailEntity> getEmails() {
    return emails;
  }

  public void setEmails(List<EmailEntity> emails) {
    this.emails = emails;
  }

  public List<PhonenumberEntity> getPhonenumbers() {
    return phonenumbers;
  }

  public void setPhonenumbers(List<PhonenumberEntity> phonenumbers) {
    this.phonenumbers = phonenumbers;
  }

  public List<UniqueidentifierEntity> getUniqueidentifiers() {
    return uniqueidentifiers;
  }

  public void setUniqueidentifiers(List<UniqueidentifierEntity> uniqueidentifiers) {
    this.uniqueidentifiers = uniqueidentifiers;
  }
}
