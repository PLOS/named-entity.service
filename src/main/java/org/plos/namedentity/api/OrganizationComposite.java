package org.plos.namedentity.api;

import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Organization;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.validate.Validatable;

import java.util.List;

public class OrganizationComposite implements Validatable {

  private Organization           organization;
  private List<Address>          addresses;
  private List<Email>            emails;
  private List<Phonenumber>      phonenumbers;
  private List<Uniqueidentifier> uniqueidentifiers;

  public OrganizationComposite() {
    this.organization = new Organization();
  }

  @Override
  public void validate() {

    if (emails == null || emails.size() == 0)
      throw new NedValidationException("Emails can not be empty");

    // TODO: determine exactly which lists are required

    organization.validate();

//    for (Address address : addresses)            address.validate();
    for (Email email : emails)                   email.validate();
//    for (Phonenumber p : phonenumbers)           p.validate();
//    for (Uniqueidentifier u: uniqueidentifiers)  u.validate();

  }

  public Integer getNamedentityid() {
    return this.organization.getNedid();
  }

  public void setNamedentityid(Integer namedentityid) {
    this.organization.setNedid(namedentityid);
  }

  public Integer getOrganizationtypeid() {
    return this.organization.getTypeid();
  }

  public void setOrganizationtypeid(Integer organizationtypeid) {
    this.organization.setTypeid(organizationtypeid);
  }

  public String getOrganizationfamiliarname() {
    return this.organization.getFamiliarname();
  }

  public void setOrganizationfamiliarname(String organizationfamiliarname) {
    this.organization.setFamiliarname(organizationfamiliarname);
  }

  public String getOrganizationlegalname() {
    return this.organization.getLegalname();
  }

  public void setOrganizationlegalname(String organizationlegalname) {
    this.organization.setLegalname(organizationlegalname);
  }

  public Integer getOrganizationmaincontactid() {
    return this.organization.getMaincontactid();
  }

  public void setOrganizationmaincontactid(Integer organizationmaincontactid) {
    this.organization.setTypeid(organizationmaincontactid);
  }

  public Byte getIsactive() {
    return this.organization.getIsactive();
  }

  public void setIsactive(Byte isactive) {
    this.organization.setIsactive(isactive);
  }

  public Byte getIsvisible() {
    return this.organization.getIsvisible();
  }

  public void setIsvisible(Byte isvisible) {
    this.organization.setIsvisible(isvisible);
  }

  public String getOrganizationtype() {
    return this.organization.getType();
  }

  public void setOrganizationtype(String organizationtype) {
    this.organization.setType(organizationtype);
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
}
