package org.plos.ned_client.model;

import org.plos.ned_client.StringUtil;
import java.util.Date;


import java.util.Objects;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;



@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-12-21T13:36:13.082-08:00")
public class Auth   {
  
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
  private String email = null;
  private Integer emailid = null;
  private String authid = null;
  private String plainTextPassword = null;
  private String password = null;
  private Boolean passwordreset = false;
  private String verificationtoken = null;
  private Boolean verified = false;
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
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("emailid")
  public Integer getEmailid() {
    return emailid;
  }
  public void setEmailid(Integer emailid) {
    this.emailid = emailid;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("authid")
  public String getAuthid() {
    return authid;
  }
  public void setAuthid(String authid) {
    this.authid = authid;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("plainTextPassword")
  public String getPlainTextPassword() {
    return plainTextPassword;
  }
  public void setPlainTextPassword(String plainTextPassword) {
    this.plainTextPassword = plainTextPassword;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("passwordreset")
  public Boolean getPasswordreset() {
    return passwordreset;
  }
  public void setPasswordreset(Boolean passwordreset) {
    this.passwordreset = passwordreset;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("verificationtoken")
  public String getVerificationtoken() {
    return verificationtoken;
  }
  public void setVerificationtoken(String verificationtoken) {
    this.verificationtoken = verificationtoken;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("verified")
  public Boolean getVerified() {
    return verified;
  }
  public void setVerified(Boolean verified) {
    this.verified = verified;
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
    Auth auth = (Auth) o;
    return Objects.equals(id, auth.id) &&
        Objects.equals(nedid, auth.nedid) &&
        Objects.equals(source, auth.source) &&
        Objects.equals(sourcetypeid, auth.sourcetypeid) &&
        Objects.equals(created, auth.created) &&
        Objects.equals(lastmodified, auth.lastmodified) &&
        Objects.equals(createdby, auth.createdby) &&
        Objects.equals(createdbyname, auth.createdbyname) &&
        Objects.equals(lastmodifiedby, auth.lastmodifiedby) &&
        Objects.equals(lastmodifiedbyname, auth.lastmodifiedbyname) &&
        Objects.equals(email, auth.email) &&
        Objects.equals(emailid, auth.emailid) &&
        Objects.equals(authid, auth.authid) &&
        Objects.equals(plainTextPassword, auth.plainTextPassword) &&
        Objects.equals(password, auth.password) &&
        Objects.equals(passwordreset, auth.passwordreset) &&
        Objects.equals(verificationtoken, auth.verificationtoken) &&
        Objects.equals(verified, auth.verified) &&
        Objects.equals(isactive, auth.isactive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nedid, source, sourcetypeid, created, lastmodified, createdby, createdbyname, lastmodifiedby, lastmodifiedbyname, email, emailid, authid, plainTextPassword, password, passwordreset, verificationtoken, verified, isactive);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Auth {\n");
    
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
    sb.append("    email: ").append(StringUtil.toIndentedString(email)).append("\n");
    sb.append("    emailid: ").append(StringUtil.toIndentedString(emailid)).append("\n");
    sb.append("    authid: ").append(StringUtil.toIndentedString(authid)).append("\n");
    sb.append("    plainTextPassword: ").append(StringUtil.toIndentedString(plainTextPassword)).append("\n");
    sb.append("    password: ").append(StringUtil.toIndentedString(password)).append("\n");
    sb.append("    passwordreset: ").append(StringUtil.toIndentedString(passwordreset)).append("\n");
    sb.append("    verificationtoken: ").append(StringUtil.toIndentedString(verificationtoken)).append("\n");
    sb.append("    verified: ").append(StringUtil.toIndentedString(verified)).append("\n");
    sb.append("    isactive: ").append(StringUtil.toIndentedString(isactive)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
