/*
 * Copyright (c) 2017 Public Library of Science
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

package org.plos.namedentity.api;

import static org.plos.namedentity.api.NedException.ErrorType.*;

import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Composite;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.Organization;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.api.enums.NamedPartyEnum;
import org.plos.namedentity.validate.Validatable;

import javax.xml.bind.annotation.XmlTransient;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganizationComposite extends Composite implements Validatable {

  private Organization           organization = new Organization();
  private List<Address>          addresses;
  private List<Email>            emails;
  private List<Phonenumber>      phonenumbers;
  private List<Uniqueidentifier> uniqueidentifiers;

  public NamedPartyEnum getTypeName() {
    return NamedPartyEnum.ORGANIZATION;
  }

  @XmlTransient
  public Map<Class, List<? extends Entity>> readAsMap() {
    Map<Class, List<? extends Entity>> map = new HashMap<>();

    List<Organization> orgs = new ArrayList<>();
    orgs.add(organization);

    map.put(Organization.class, orgs);
    map.put(Address.class, addresses);
    map.put(Email.class, emails);
    map.put(Phonenumber.class, phonenumbers);
    map.put(Uniqueidentifier.class, uniqueidentifiers);

    return map;
  }

  @SuppressWarnings("unchecked")
  public void setFromMap(Map<Class, List<? extends Entity>> map) {

    organization = ((List<Organization>)map.get(Organization.class)).get(0);

    addresses = (List<Address>) map.get(Address.class);
    emails = (List<Email>) map.get(Email.class);
    phonenumbers = (List<Phonenumber>) map.get(Phonenumber.class);
    uniqueidentifiers = (List<Uniqueidentifier>) map.get(Uniqueidentifier.class);
  }

  @Override
  public void validate() {

    Map<Class, List<? extends Entity>> compositeMap = readAsMap();

    for (List<? extends Entity> entities : compositeMap.values()) {

      if (entities != null) {
        for (Entity entity : entities)
          entity.validate();
      }
    }

    if (organization == null)
      throw new NedException(EntityNotDefined, "Organization needs basic info");

  }

  public Integer getTypeid() {
    return organization.getTypeid();
  }

  public void setTypeid(Integer typeid) {
    organization.setTypeid(typeid);
  }

  public String getFamiliarname() {
    return organization.getFamiliarname();
  }

  public void setFamiliarname(String familiarname) {
    organization.setFamiliarname(familiarname);
  }

  public String getLegalname() {
    return organization.getLegalname();
  }

  public void setLegalname(String legalname) {
    organization.setLegalname(legalname);
  }

  public Integer getMaincontactid() {
    return organization.getMaincontactid();
  }

  public void setMaincontactid(Integer maincontactid) {
    organization.setMaincontactid(maincontactid);
  }

  public Boolean getIsactive() {
    return organization.getIsactive();
  }

  public void setIsactive(Boolean isactive) {
    organization.setIsactive(isactive);
  }

  public String getType() {
    return organization.getType();
  }

  public void setType(String type) {
    organization.setType(type);
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

  public Integer getNedid() {
    return organization.getNedid();
  }

  public void setNedid(Integer nedid) {
    organization.setNedid(nedid);
  }

  public String getSource() {
    return organization.getSource();
  }

  public void setSource(String source) {
    organization.setSource(source);
  }

  public Integer getSourcetypeid() {
    return organization.getSourcetypeid();
  }

  public void setSourcetypeid(Integer sourcetypeid) {
    organization.setSourcetypeid(sourcetypeid);
  }

  public List<Uniqueidentifier> getUniqueidentifiers() {
    return uniqueidentifiers;
  }

  public void setUniqueidentifiers(List<Uniqueidentifier> uniqueidentifiers) {
    this.uniqueidentifiers = uniqueidentifiers;
  }

  @XmlTransient
  public Timestamp getCreated() {
    return organization.getCreated();
  }

  public void setCreated(Timestamp created) {
    organization.setCreated(created);
  }

  @XmlTransient
  public Timestamp getLastmodified() {
    return organization.getLastmodified();
  }

  public void setLastmodified(Timestamp lastmodified) {
    organization.setLastmodified(lastmodified);
  }

  public void setCreatedby(String username) {
    organization.setCreatedbyname(username);
  }

  public void setLastmodifiedby(String username) {
    organization.setLastmodifiedbyname(username);
  }
}
