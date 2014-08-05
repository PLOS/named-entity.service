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
