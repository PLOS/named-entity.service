package org.plos.namedentity.api;

import org.plos.namedentity.api.entity.AddressEntity;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.OrganizationEntity;
import org.plos.namedentity.api.entity.PhonenumberEntity;
import org.plos.namedentity.api.entity.UniqueidentifierEntity;

import java.util.List;

public class OrganizationComposite {

  private OrganizationEntity           organization;
  private List<AddressEntity>          addresses;
  private List<EmailEntity>            emails;
  private List<PhonenumberEntity>      phonenumbers;
  private List<UniqueidentifierEntity> uniqueidentifiers;

  public OrganizationComposite() {
    this.organization = new OrganizationEntity();
  }

  public Integer getNamedentityid() {
    return this.organization.getNamedentityid();
  }

  public void setNamedentityid(Integer namedentityid) {
    this.organization.setNamedentityid(namedentityid);
  }

  public Integer getOrganizationtypeid() {
    return this.organization.getOrganizationtypeid();
  }

  public void setOrganizationtypeid(Integer organizationtypeid) {
    this.organization.setOrganizationtypeid(organizationtypeid);
  }

  public String getOrganizationfamiliarname() {
    return this.organization.getOrganizationfamiliarname();
  }

  public void setOrganizationfamiliarname(String organizationfamiliarname) {
    this.organization.setOrganizationfamiliarname(organizationfamiliarname);
  }

  public String getOrganizationlegalname() {
    return this.organization.getOrganizationlegalname();
  }

  public void setOrganizationlegalname(String organizationlegalname) {
    this.organization.setOrganizationlegalname(organizationlegalname);
  }

  public Integer getOrganizationmaincontactid() {
    return this.organization.getOrganizationmaincontactid();
  }

  public void setOrganizationmaincontactid(Integer organizationmaincontactid) {
    this.organization.setOrganizationtypeid(organizationmaincontactid);
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

  public String getUrl() {
    return this.organization.getUrl();
  }

  public void setUrl(String url) {
    this.organization.setUrl(url);
  }

  public String getOrganizationtype() {
    return this.organization.getOrganizationtype();
  }

  public void setOrganizationtype(String organizationtype) {
    this.organization.setOrganizationtype(organizationtype);
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
}
