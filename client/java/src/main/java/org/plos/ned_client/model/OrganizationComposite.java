package org.plos.ned_client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import java.util.Date;
import org.plos.ned_client.model.Address;
import org.plos.ned_client.model.Email;
import org.plos.ned_client.model.Phonenumber;
import org.plos.ned_client.model.Uniqueidentifier;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-04T15:04:00.420-08:00")
public class OrganizationComposite   {
  
  private List<Address> addresses = new ArrayList<Address>();
  private List<Email> emails = new ArrayList<Email>();
  private List<Phonenumber> phonenumbers = new ArrayList<Phonenumber>();
  private List<Uniqueidentifier> uniqueidentifiers = new ArrayList<Uniqueidentifier>();
  private Date created = null;
  private String type = null;
  private String source = null;
  private Integer nedid = null;
  private Integer typeid = null;
  private Integer sourcetypeid = null;
  private Boolean isactive = false;
  private Date lastmodified = null;
  private String familiarname = null;
  private String legalname = null;
  private Integer maincontactid = null;


  public enum TypeNameEnum {
    INDIVIDUAL("INDIVIDUAL"),
    ORGANIZATION("ORGANIZATION"),
    INVALID_NAMEDPARTY_TYPE("INVALID_NAMEDPARTY_TYPE");

    private String value;

    TypeNameEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private TypeNameEnum typeName = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("addresses")
  public List<Address> getAddresses() {
    return addresses;
  }
  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("emails")
  public List<Email> getEmails() {
    return emails;
  }
  public void setEmails(List<Email> emails) {
    this.emails = emails;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phonenumbers")
  public List<Phonenumber> getPhonenumbers() {
    return phonenumbers;
  }
  public void setPhonenumbers(List<Phonenumber> phonenumbers) {
    this.phonenumbers = phonenumbers;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("uniqueidentifiers")
  public List<Uniqueidentifier> getUniqueidentifiers() {
    return uniqueidentifiers;
  }
  public void setUniqueidentifiers(List<Uniqueidentifier> uniqueidentifiers) {
    this.uniqueidentifiers = uniqueidentifiers;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("created")
  public Date getCreated() {
    return created;
  }
  public void setCreated(Date created) {
    this.created = created;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("type")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("source")
  public String getSource() {
    return source;
  }
  public void setSource(String source) {
    this.source = source;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("nedid")
  public Integer getNedid() {
    return nedid;
  }
  public void setNedid(Integer nedid) {
    this.nedid = nedid;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("typeid")
  public Integer getTypeid() {
    return typeid;
  }
  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("sourcetypeid")
  public Integer getSourcetypeid() {
    return sourcetypeid;
  }
  public void setSourcetypeid(Integer sourcetypeid) {
    this.sourcetypeid = sourcetypeid;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("isactive")
  public Boolean getIsactive() {
    return isactive;
  }
  public void setIsactive(Boolean isactive) {
    this.isactive = isactive;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("lastmodified")
  public Date getLastmodified() {
    return lastmodified;
  }
  public void setLastmodified(Date lastmodified) {
    this.lastmodified = lastmodified;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("familiarname")
  public String getFamiliarname() {
    return familiarname;
  }
  public void setFamiliarname(String familiarname) {
    this.familiarname = familiarname;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("legalname")
  public String getLegalname() {
    return legalname;
  }
  public void setLegalname(String legalname) {
    this.legalname = legalname;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("maincontactid")
  public Integer getMaincontactid() {
    return maincontactid;
  }
  public void setMaincontactid(Integer maincontactid) {
    this.maincontactid = maincontactid;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("typeName")
  public TypeNameEnum getTypeName() {
    return typeName;
  }
  public void setTypeName(TypeNameEnum typeName) {
    this.typeName = typeName;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrganizationComposite organizationComposite = (OrganizationComposite) o;
    return Objects.equals(addresses, organizationComposite.addresses) &&
        Objects.equals(emails, organizationComposite.emails) &&
        Objects.equals(phonenumbers, organizationComposite.phonenumbers) &&
        Objects.equals(uniqueidentifiers, organizationComposite.uniqueidentifiers) &&
        Objects.equals(created, organizationComposite.created) &&
        Objects.equals(type, organizationComposite.type) &&
        Objects.equals(source, organizationComposite.source) &&
        Objects.equals(nedid, organizationComposite.nedid) &&
        Objects.equals(typeid, organizationComposite.typeid) &&
        Objects.equals(sourcetypeid, organizationComposite.sourcetypeid) &&
        Objects.equals(isactive, organizationComposite.isactive) &&
        Objects.equals(lastmodified, organizationComposite.lastmodified) &&
        Objects.equals(familiarname, organizationComposite.familiarname) &&
        Objects.equals(legalname, organizationComposite.legalname) &&
        Objects.equals(maincontactid, organizationComposite.maincontactid) &&
        Objects.equals(typeName, organizationComposite.typeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addresses, emails, phonenumbers, uniqueidentifiers, created, type, source, nedid, typeid, sourcetypeid, isactive, lastmodified, familiarname, legalname, maincontactid, typeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrganizationComposite {\n");
    
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("    emails: ").append(toIndentedString(emails)).append("\n");
    sb.append("    phonenumbers: ").append(toIndentedString(phonenumbers)).append("\n");
    sb.append("    uniqueidentifiers: ").append(toIndentedString(uniqueidentifiers)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    nedid: ").append(toIndentedString(nedid)).append("\n");
    sb.append("    typeid: ").append(toIndentedString(typeid)).append("\n");
    sb.append("    sourcetypeid: ").append(toIndentedString(sourcetypeid)).append("\n");
    sb.append("    isactive: ").append(toIndentedString(isactive)).append("\n");
    sb.append("    lastmodified: ").append(toIndentedString(lastmodified)).append("\n");
    sb.append("    familiarname: ").append(toIndentedString(familiarname)).append("\n");
    sb.append("    legalname: ").append(toIndentedString(legalname)).append("\n");
    sb.append("    maincontactid: ").append(toIndentedString(maincontactid)).append("\n");
    sb.append("    typeName: ").append(toIndentedString(typeName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

