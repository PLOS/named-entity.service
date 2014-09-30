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
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.Individual;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.api.entity.Url;
import org.plos.namedentity.validate.Validatable;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

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

  private <T extends Entity> void validateEntities(List<T> entities) {
    if (entities != null)
      for (T entity : entities)
        entity.validate();
  }

  @Override
  public void validate() {

    if (individuals == null || individuals.size() == 0)
      throw new NedValidationException("Individuals can not be empty");

    validateEntities(individuals);
    validateEntities(roles);
    validateEntities(addresses);
    validateEntities(emails);
    validateEntities(phonenumbers);
    validateEntities(uniqueidentifiers);
    validateEntities(degrees);
    validateEntities(urls);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || this.getClass() != o.getClass())
      return false;

    return Objects.equals(this.hashCode(), o.hashCode());
  }

  private <T extends Entity> Integer hashSum(List<T> entities) {

    Integer sum = 0;

    if (entities != null)
      for (T entity : entities)
        sum += entity.hashCode();

    return sum;
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashSum(individuals), hashSum(roles), hashSum(addresses),
        hashSum(emails), hashSum(phonenumbers), hashSum(uniqueidentifiers),
        hashSum(degrees), hashSum(urls));
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
