package org.plos.ned_client.model;

import org.plos.ned_client.StringUtil;
import org.plos.ned_client.model.Group;
import org.plos.ned_client.model.Email;
import org.plos.ned_client.model.Address;
import org.plos.ned_client.model.Degree;
import org.plos.ned_client.model.Auth;
import org.plos.ned_client.model.Relationship;
import java.util.*;
import org.plos.ned_client.model.Uniqueidentifier;
import org.plos.ned_client.model.Individualprofile;
import org.plos.ned_client.model.Phonenumber;
import org.plos.ned_client.model.Url;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-11-02T10:19:21.505-08:00")
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
  private String typeName = null;

  
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
  public String getTypeName() {
    return typeName;
  }
  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndividualComposite {\n");
    
    sb.append("    auth: ").append(StringUtil.toIndentedString(auth)).append("\n");
    sb.append("    individualprofiles: ").append(StringUtil.toIndentedString(individualprofiles)).append("\n");
    sb.append("    groups: ").append(StringUtil.toIndentedString(groups)).append("\n");
    sb.append("    addresses: ").append(StringUtil.toIndentedString(addresses)).append("\n");
    sb.append("    emails: ").append(StringUtil.toIndentedString(emails)).append("\n");
    sb.append("    phonenumbers: ").append(StringUtil.toIndentedString(phonenumbers)).append("\n");
    sb.append("    uniqueidentifiers: ").append(StringUtil.toIndentedString(uniqueidentifiers)).append("\n");
    sb.append("    degrees: ").append(StringUtil.toIndentedString(degrees)).append("\n");
    sb.append("    urls: ").append(StringUtil.toIndentedString(urls)).append("\n");
    sb.append("    relationships: ").append(StringUtil.toIndentedString(relationships)).append("\n");
    sb.append("    typeName: ").append(StringUtil.toIndentedString(typeName)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
