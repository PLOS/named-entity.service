package org.plos.ned_client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import org.plos.ned_client.model.Address;
import org.plos.ned_client.model.Auth;
import org.plos.ned_client.model.Degree;
import org.plos.ned_client.model.Email;
import org.plos.ned_client.model.Group;
import org.plos.ned_client.model.Individualprofile;
import org.plos.ned_client.model.Phonenumber;
import org.plos.ned_client.model.Relationship;
import org.plos.ned_client.model.Uniqueidentifier;
import org.plos.ned_client.model.Url;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-03T13:54:47.854-08:00")
public class IndividualComposite   {
  
  private List<Auth> auth = new ArrayList<Auth>();
  private List<Individualprofile> individualprofiles = new ArrayList<Individualprofile>();
  private List<Group> groups = new ArrayList<Group>();
  private List<Address> addresses = new ArrayList<Address>();
  private List<Email> emails = new ArrayList<Email>();
  private List<Phonenumber> phonenumbers = new ArrayList<Phonenumber>();
  private List<Uniqueidentifier> uniqueidentifiers = new ArrayList<Uniqueidentifier>();
  private List<Degree> degrees = new ArrayList<Degree>();
  private List<Url> urls = new ArrayList<Url>();
  private List<Relationship> relationships = new ArrayList<Relationship>();


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
  @JsonProperty("auth")
  public List<Auth> getAuth() {
    return auth;
  }
  public void setAuth(List<Auth> auth) {
    this.auth = auth;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("individualprofiles")
  public List<Individualprofile> getIndividualprofiles() {
    return individualprofiles;
  }
  public void setIndividualprofiles(List<Individualprofile> individualprofiles) {
    this.individualprofiles = individualprofiles;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("groups")
  public List<Group> getGroups() {
    return groups;
  }
  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }

  
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
  @JsonProperty("degrees")
  public List<Degree> getDegrees() {
    return degrees;
  }
  public void setDegrees(List<Degree> degrees) {
    this.degrees = degrees;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("urls")
  public List<Url> getUrls() {
    return urls;
  }
  public void setUrls(List<Url> urls) {
    this.urls = urls;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("relationships")
  public List<Relationship> getRelationships() {
    return relationships;
  }
  public void setRelationships(List<Relationship> relationships) {
    this.relationships = relationships;
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
    IndividualComposite individualComposite = (IndividualComposite) o;
    return Objects.equals(auth, individualComposite.auth) &&
        Objects.equals(individualprofiles, individualComposite.individualprofiles) &&
        Objects.equals(groups, individualComposite.groups) &&
        Objects.equals(addresses, individualComposite.addresses) &&
        Objects.equals(emails, individualComposite.emails) &&
        Objects.equals(phonenumbers, individualComposite.phonenumbers) &&
        Objects.equals(uniqueidentifiers, individualComposite.uniqueidentifiers) &&
        Objects.equals(degrees, individualComposite.degrees) &&
        Objects.equals(urls, individualComposite.urls) &&
        Objects.equals(relationships, individualComposite.relationships) &&
        Objects.equals(typeName, individualComposite.typeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(auth, individualprofiles, groups, addresses, emails, phonenumbers, uniqueidentifiers, degrees, urls, relationships, typeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndividualComposite {\n");
    
    sb.append("    auth: ").append(toIndentedString(auth)).append("\n");
    sb.append("    individualprofiles: ").append(toIndentedString(individualprofiles)).append("\n");
    sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("    emails: ").append(toIndentedString(emails)).append("\n");
    sb.append("    phonenumbers: ").append(toIndentedString(phonenumbers)).append("\n");
    sb.append("    uniqueidentifiers: ").append(toIndentedString(uniqueidentifiers)).append("\n");
    sb.append("    degrees: ").append(toIndentedString(degrees)).append("\n");
    sb.append("    urls: ").append(toIndentedString(urls)).append("\n");
    sb.append("    relationships: ").append(toIndentedString(relationships)).append("\n");
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

