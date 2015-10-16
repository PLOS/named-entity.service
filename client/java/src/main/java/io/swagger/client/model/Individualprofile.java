package io.swagger.client.model;

import io.swagger.client.StringUtil;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-16T10:46:01.311-07:00")
public class Individualprofile   {
  
  private String firstname = null;
  private String middlename = null;
  private String lastname = null;
  private String nickname = null;
  private String nameprefix = null;
  private Integer nameprefixtypeid = null;
  private String namesuffix = null;
  private Integer namesuffixtypeid = null;
  private String displayname = null;
  private String biography = null;
  private Boolean isactive = null;
  private String source = null;
  private Integer nedid = null;
  private Integer sourcetypeid = null;
  private Integer id = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("firstname")
  public String getFirstname() {
    return firstname;
  }
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("middlename")
  public String getMiddlename() {
    return middlename;
  }
  public void setMiddlename(String middlename) {
    this.middlename = middlename;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("lastname")
  public String getLastname() {
    return lastname;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("nickname")
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("nameprefix")
  public String getNameprefix() {
    return nameprefix;
  }
  public void setNameprefix(String nameprefix) {
    this.nameprefix = nameprefix;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("nameprefixtypeid")
  public Integer getNameprefixtypeid() {
    return nameprefixtypeid;
  }
  public void setNameprefixtypeid(Integer nameprefixtypeid) {
    this.nameprefixtypeid = nameprefixtypeid;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("namesuffix")
  public String getNamesuffix() {
    return namesuffix;
  }
  public void setNamesuffix(String namesuffix) {
    this.namesuffix = namesuffix;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("namesuffixtypeid")
  public Integer getNamesuffixtypeid() {
    return namesuffixtypeid;
  }
  public void setNamesuffixtypeid(Integer namesuffixtypeid) {
    this.namesuffixtypeid = namesuffixtypeid;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("displayname")
  public String getDisplayname() {
    return displayname;
  }
  public void setDisplayname(String displayname) {
    this.displayname = displayname;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("biography")
  public String getBiography() {
    return biography;
  }
  public void setBiography(String biography) {
    this.biography = biography;
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
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Individualprofile {\n");
    
    sb.append("    firstname: ").append(StringUtil.toIndentedString(firstname)).append("\n");
    sb.append("    middlename: ").append(StringUtil.toIndentedString(middlename)).append("\n");
    sb.append("    lastname: ").append(StringUtil.toIndentedString(lastname)).append("\n");
    sb.append("    nickname: ").append(StringUtil.toIndentedString(nickname)).append("\n");
    sb.append("    nameprefix: ").append(StringUtil.toIndentedString(nameprefix)).append("\n");
    sb.append("    nameprefixtypeid: ").append(StringUtil.toIndentedString(nameprefixtypeid)).append("\n");
    sb.append("    namesuffix: ").append(StringUtil.toIndentedString(namesuffix)).append("\n");
    sb.append("    namesuffixtypeid: ").append(StringUtil.toIndentedString(namesuffixtypeid)).append("\n");
    sb.append("    displayname: ").append(StringUtil.toIndentedString(displayname)).append("\n");
    sb.append("    biography: ").append(StringUtil.toIndentedString(biography)).append("\n");
    sb.append("    isactive: ").append(StringUtil.toIndentedString(isactive)).append("\n");
    sb.append("    source: ").append(StringUtil.toIndentedString(source)).append("\n");
    sb.append("    nedid: ").append(StringUtil.toIndentedString(nedid)).append("\n");
    sb.append("    sourcetypeid: ").append(StringUtil.toIndentedString(sourcetypeid)).append("\n");
    sb.append("    id: ").append(StringUtil.toIndentedString(id)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
