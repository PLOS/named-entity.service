package io.swagger.client.model;

import io.swagger.client.StringUtil;
import io.swagger.client.model.Email;
import io.swagger.client.model.Address;
import java.util.*;
import io.swagger.client.model.Uniqueidentifier;
import io.swagger.client.model.Phonenumber;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-20T11:32:47.715-07:00")
public class OrganizationComposite   {
  
  private List<Address> addresses = new ArrayList<Address>();
  private List<Email> emails = new ArrayList<Email>();
  private List<Phonenumber> phonenumbers = new ArrayList<Phonenumber>();
  private List<Uniqueidentifier> uniqueidentifiers = new ArrayList<Uniqueidentifier>();
  private String typeName = null;
  private String type = null;
  private String source = null;
  private Integer nedid = null;
  private Boolean isactive = null;
  private Integer sourcetypeid = null;
  private Integer typeid = null;
  private String familiarname = null;
  private String legalname = null;
  private Integer maincontactid = null;

  
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
  @JsonProperty("typeName")
  public String getTypeName() {
    return typeName;
  }
  public void setTypeName(String typeName) {
    this.typeName = typeName;
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

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrganizationComposite {\n");
    
    sb.append("    addresses: ").append(StringUtil.toIndentedString(addresses)).append("\n");
    sb.append("    emails: ").append(StringUtil.toIndentedString(emails)).append("\n");
    sb.append("    phonenumbers: ").append(StringUtil.toIndentedString(phonenumbers)).append("\n");
    sb.append("    uniqueidentifiers: ").append(StringUtil.toIndentedString(uniqueidentifiers)).append("\n");
    sb.append("    typeName: ").append(StringUtil.toIndentedString(typeName)).append("\n");
    sb.append("    type: ").append(StringUtil.toIndentedString(type)).append("\n");
    sb.append("    source: ").append(StringUtil.toIndentedString(source)).append("\n");
    sb.append("    nedid: ").append(StringUtil.toIndentedString(nedid)).append("\n");
    sb.append("    isactive: ").append(StringUtil.toIndentedString(isactive)).append("\n");
    sb.append("    sourcetypeid: ").append(StringUtil.toIndentedString(sourcetypeid)).append("\n");
    sb.append("    typeid: ").append(StringUtil.toIndentedString(typeid)).append("\n");
    sb.append("    familiarname: ").append(StringUtil.toIndentedString(familiarname)).append("\n");
    sb.append("    legalname: ").append(StringUtil.toIndentedString(legalname)).append("\n");
    sb.append("    maincontactid: ").append(StringUtil.toIndentedString(maincontactid)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
