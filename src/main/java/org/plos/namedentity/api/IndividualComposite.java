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

import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.validate.Validatable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.plos.namedentity.api.NedException.ErrorType.NoAuthEntity;
import static org.plos.namedentity.api.NedException.ErrorType.NoEmailEntities;
import static org.plos.namedentity.api.NedException.ErrorType.NoProfileEntities;

@XmlRootElement
public class IndividualComposite extends Composite implements Validatable {

  @XmlElement(name = "credentials")
  private List<Auth>              auth;

  private List<Individualprofile> individualprofiles;
  private List<Group>             groups;
  private List<Address>           addresses;
  private List<Email>             emails;
  private List<Phonenumber>       phonenumbers;
  private List<Uniqueidentifier>  uniqueidentifiers;
  private List<Degree>            degrees;
  private List<Url>               urls;
  private List<Relationship>      relationships;

  public static String typeName = "Individual";

  public String getTypeName() {
    return typeName;
  }

  @XmlTransient
  public Map<Class, List<? extends Entity>> getAsMap() {

    // use a linked map to preserve insertion order. this will ensure
    // that email inserts before auth record during composite creation
    // (auth record has a foreign key to email record)

    Map<Class, List<? extends Entity>> map = new LinkedHashMap<>();

    map.put(Individualprofile.class, individualprofiles);
    map.put(Group.class, groups);
    map.put(Address.class, addresses);
    map.put(Email.class, emails);
    map.put(Phonenumber.class, phonenumbers);
    map.put(Uniqueidentifier.class, uniqueidentifiers);
    map.put(Degree.class, degrees);
    map.put(Url.class, urls);
    map.put(Auth.class, auth);
    map.put(Relationship.class, relationships);

    return map;
  }

  @SuppressWarnings("unchecked")
  public void setFromMap(Map<Class, List<? extends Entity>> map) {
    individualprofiles = (List<Individualprofile>) map.get(Individualprofile.class);
    groups             = (List<Group>) map.get(Group.class);
    addresses          = (List<Address>) map.get(Address.class);
    emails             = (List<Email>) map.get(Email.class);
    phonenumbers       = (List<Phonenumber>) map.get(Phonenumber.class);
    uniqueidentifiers  = (List<Uniqueidentifier>) map.get(Uniqueidentifier.class);
    degrees            = (List<Degree>) map.get(Degree.class);
    urls               = (List<Url>) map.get(Url.class);
    auth               = (List<Auth>) map.get(Auth.class);
    relationships      = (List<Relationship>) map.get(Relationship.class);
  }

  @Override
  public void validate() {

    Map<Class, List<? extends Entity>> compositeMap = getAsMap();

    for (List<? extends Entity> entities : compositeMap.values()) {

      if (entities != null) {
        for (Entity entity : entities)
          entity.validate();
      }
    }

    if (individualprofiles == null || individualprofiles.size() == 0)
      throw new NedException(NoProfileEntities, "Profile entities can not be empty");

    if (emails == null || emails.size() == 0)
      throw new NedException(NoEmailEntities, "Email entities can not be empty");

    if (auth == null || auth.size() == 0)
      throw new NedException(NoAuthEntity, "User credentials can not be empty");
  }

  public List<Auth> getAuth() {
    return auth;
  }

  public void setAuth(List<Auth> auth) {
    this.auth = auth;
  }

  public List<Individualprofile> getIndividualprofiles() {
    return individualprofiles;
  }

  public void setIndividualprofiles(List<Individualprofile> individualprofiles) {
    this.individualprofiles = individualprofiles;
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

  public List<Group> getGroups() {
    return groups;
  }

  public void setGroups(List<Group> groups) {
    this.groups = groups;
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

  public List<Relationship> getRelationships() {
    return relationships;
  }

  public void setRelationships(List<Relationship> relationships) {
    this.relationships = relationships;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    Map<Class, List<? extends Entity>> compositeMap = getAsMap();
    for (List<? extends Entity> entities : compositeMap.values()) {
      if (entities != null) {
        for (Entity entity : entities)
          sb.append(entity).append("\n");
      }
    }
    return sb.toString();
  }
}
