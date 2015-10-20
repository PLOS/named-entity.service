package io.swagger.client.model;

import io.swagger.client.StringUtil;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T20:30:34.752-07:00")
public class Auth   {
  
  private String email = null;
  private Integer emailid = null;
  private String authid = null;
  private String password = null;
  private String passwordreset = null;
  private String verificationtoken = null;
  private String verified = null;
  private String isactive = null;
  private String source = null;
  private Integer nedid = null;
  private Integer sourcetypeid = null;
  private Integer id = null;

  
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
  public String getPasswordreset() {
    return passwordreset;
  }
  public void setPasswordreset(String passwordreset) {
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
  public String getVerified() {
    return verified;
  }
  public void setVerified(String verified) {
    this.verified = verified;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("isactive")
  public String getIsactive() {
    return isactive;
  }
  public void setIsactive(String isactive) {
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
    sb.append("class Auth {\n");
    
    sb.append("    email: ").append(StringUtil.toIndentedString(email)).append("\n");
    sb.append("    emailid: ").append(StringUtil.toIndentedString(emailid)).append("\n");
    sb.append("    authid: ").append(StringUtil.toIndentedString(authid)).append("\n");
    sb.append("    password: ").append(StringUtil.toIndentedString(password)).append("\n");
    sb.append("    passwordreset: ").append(StringUtil.toIndentedString(passwordreset)).append("\n");
    sb.append("    verificationtoken: ").append(StringUtil.toIndentedString(verificationtoken)).append("\n");
    sb.append("    verified: ").append(StringUtil.toIndentedString(verified)).append("\n");
    sb.append("    isactive: ").append(StringUtil.toIndentedString(isactive)).append("\n");
    sb.append("    source: ").append(StringUtil.toIndentedString(source)).append("\n");
    sb.append("    nedid: ").append(StringUtil.toIndentedString(nedid)).append("\n");
    sb.append("    sourcetypeid: ").append(StringUtil.toIndentedString(sourcetypeid)).append("\n");
    sb.append("    id: ").append(StringUtil.toIndentedString(id)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
