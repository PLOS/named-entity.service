package org.plos.ned_client.model;

import org.plos.ned_client.StringUtil;
import java.util.Date;


import java.util.Objects;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;



@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-12-21T13:36:13.082-08:00")
public class Individualprofile   {
  
  private Integer id = null;
  private Integer nedid = null;
  private String source = null;
  private Integer sourcetypeid = null;
  private Date created = null;
  private Date lastmodified = null;
  private Integer createdby = null;
  private String createdbyname = null;
  private Integer lastmodifiedby = null;
  private String lastmodifiedbyname = null;
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
  private Boolean isactive = false;

  
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
  @JsonProperty("createdby")
  public Integer getCreatedby() {
    return createdby;
  }
  public void setCreatedby(Integer createdby) {
    this.createdby = createdby;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("createdbyname")
  public String getCreatedbyname() {
    return createdbyname;
  }
  public void setCreatedbyname(String createdbyname) {
    this.createdbyname = createdbyname;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("lastmodifiedby")
  public Integer getLastmodifiedby() {
    return lastmodifiedby;
  }
  public void setLastmodifiedby(Integer lastmodifiedby) {
    this.lastmodifiedby = lastmodifiedby;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("lastmodifiedbyname")
  public String getLastmodifiedbyname() {
    return lastmodifiedbyname;
  }
  public void setLastmodifiedbyname(String lastmodifiedbyname) {
    this.lastmodifiedbyname = lastmodifiedbyname;
  }

  
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Individualprofile individualprofile = (Individualprofile) o;
    return Objects.equals(id, individualprofile.id) &&
        Objects.equals(nedid, individualprofile.nedid) &&
        Objects.equals(source, individualprofile.source) &&
        Objects.equals(sourcetypeid, individualprofile.sourcetypeid) &&
        Objects.equals(created, individualprofile.created) &&
        Objects.equals(lastmodified, individualprofile.lastmodified) &&
        Objects.equals(createdby, individualprofile.createdby) &&
        Objects.equals(createdbyname, individualprofile.createdbyname) &&
        Objects.equals(lastmodifiedby, individualprofile.lastmodifiedby) &&
        Objects.equals(lastmodifiedbyname, individualprofile.lastmodifiedbyname) &&
        Objects.equals(firstname, individualprofile.firstname) &&
        Objects.equals(middlename, individualprofile.middlename) &&
        Objects.equals(lastname, individualprofile.lastname) &&
        Objects.equals(nickname, individualprofile.nickname) &&
        Objects.equals(nameprefix, individualprofile.nameprefix) &&
        Objects.equals(nameprefixtypeid, individualprofile.nameprefixtypeid) &&
        Objects.equals(namesuffix, individualprofile.namesuffix) &&
        Objects.equals(namesuffixtypeid, individualprofile.namesuffixtypeid) &&
        Objects.equals(displayname, individualprofile.displayname) &&
        Objects.equals(biography, individualprofile.biography) &&
        Objects.equals(isactive, individualprofile.isactive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nedid, source, sourcetypeid, created, lastmodified, createdby, createdbyname, lastmodifiedby, lastmodifiedbyname, firstname, middlename, lastname, nickname, nameprefix, nameprefixtypeid, namesuffix, namesuffixtypeid, displayname, biography, isactive);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Individualprofile {\n");
    
    sb.append("    id: ").append(StringUtil.toIndentedString(id)).append("\n");
    sb.append("    nedid: ").append(StringUtil.toIndentedString(nedid)).append("\n");
    sb.append("    source: ").append(StringUtil.toIndentedString(source)).append("\n");
    sb.append("    sourcetypeid: ").append(StringUtil.toIndentedString(sourcetypeid)).append("\n");
    sb.append("    created: ").append(StringUtil.toIndentedString(created)).append("\n");
    sb.append("    lastmodified: ").append(StringUtil.toIndentedString(lastmodified)).append("\n");
    sb.append("    createdby: ").append(StringUtil.toIndentedString(createdby)).append("\n");
    sb.append("    createdbyname: ").append(StringUtil.toIndentedString(createdbyname)).append("\n");
    sb.append("    lastmodifiedby: ").append(StringUtil.toIndentedString(lastmodifiedby)).append("\n");
    sb.append("    lastmodifiedbyname: ").append(StringUtil.toIndentedString(lastmodifiedbyname)).append("\n");
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
    sb.append("}");
    return sb.toString();
  }
}
