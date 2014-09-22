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

import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Degree;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Individual;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.api.entity.Url;
import org.plos.namedentity.validate.Validatable;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class IndividualComposite implements Validatable {

  private List<Individual>       individuals;
  private List<Role>             roles;
  private List<Address>          addresses;
  private List<Email>            emails;
  private List<Phonenumber>      phonenumbers;
  private List<Uniqueidentifier> uniqueidentifiers;
  private List<Degree>           degrees;
  private List<Url>              urls;

  @Override
  public void validate() {

    if (roles == null || roles.size() == 0)
      throw new NedValidationException("Roles can not be empty");

    if (emails == null || emails.size() == 0)
      throw new NedValidationException("Emails can not be empty");

    if (individuals == null || individuals.size() == 0)
      throw new NedValidationException("Individuals can not be empty");


    // TODO: determine exactly which lists are required

    for (Individual individual : individuals)     individual.validate();
    for (Role role : roles)                       role.validate();
    for (Email email : emails)                    email.validate();

    if (addresses != null)
      for (Address address : addresses)           address.validate();

    if (phonenumbers != null)
      for (Phonenumber p : phonenumbers)          p.validate();

    if (uniqueidentifiers != null)
      for (Uniqueidentifier u: uniqueidentifiers) u.validate();

    if (degrees != null)
      for (Degree degree : degrees)               degree.validate();

    if (urls != null)
      for (Url url : urls)                        url.validate();

  }

  public List<Individual> getIndividuals() {
    return individuals;
  }

  public void setIndividuals(List<Individual> individuals) {
    this.individuals = individuals;
  }

  public List<Url> getUrls() {
    return urls;
  }

  public void setUrls(List<Url> urls) {
    this.urls = urls;
  }

  public List<Degree> getDegrees() {
    return degrees;
  }

  public void setDegrees(List<Degree> degrees) {
    this.degrees = degrees;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public List<Email> getEmails() {
    return emails;
  }

  public void setEmails(List<Email> emails) {
    this.emails = emails;
  }

  public List<Phonenumber> getPhonenumbers() {
    return phonenumbers;
  }

  public void setPhonenumbers(List<Phonenumber> phonenumbers) {
    this.phonenumbers = phonenumbers;
  }

  public List<Uniqueidentifier> getUniqueidentifiers() {
    return uniqueidentifiers;
  }

  public void setUniqueidentifiers(List<Uniqueidentifier> uniqueidentifiers) {
    this.uniqueidentifiers = uniqueidentifiers;
  }

}
