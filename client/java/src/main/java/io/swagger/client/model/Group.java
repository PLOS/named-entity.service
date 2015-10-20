package io.swagger.client.model;

import io.swagger.client.StringUtil;
import java.util.Date;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-20T11:32:47.715-07:00")
public class Group   {
  
  private Integer typeid = null;
  private String type = null;
  private Date startdate = null;
  private Date enddate = null;
  private Integer createdby = null;
  private Integer lastmodifiedby = null;
  private String applicationtype = null;
  private Integer applicationtypeid = null;
  private Integer id = null;
  private String source = null;
  private Integer nedid = null;
  private Integer sourcetypeid = null;

  
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
  @JsonProperty("startdate")
  public Date getStartdate() {
    return startdate;
  }
  public void setStartdate(Date startdate) {
    this.startdate = startdate;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("enddate")
  public Date getEnddate() {
    return enddate;
  }
  public void setEnddate(Date enddate) {
    this.enddate = enddate;
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
  @JsonProperty("applicationtype")
  public String getApplicationtype() {
    return applicationtype;
  }
  public void setApplicationtype(String applicationtype) {
    this.applicationtype = applicationtype;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("applicationtypeid")
  public Integer getApplicationtypeid() {
    return applicationtypeid;
  }
  public void setApplicationtypeid(Integer applicationtypeid) {
    this.applicationtypeid = applicationtypeid;
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

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Group {\n");
    
    sb.append("    typeid: ").append(StringUtil.toIndentedString(typeid)).append("\n");
    sb.append("    type: ").append(StringUtil.toIndentedString(type)).append("\n");
    sb.append("    startdate: ").append(StringUtil.toIndentedString(startdate)).append("\n");
    sb.append("    enddate: ").append(StringUtil.toIndentedString(enddate)).append("\n");
    sb.append("    createdby: ").append(StringUtil.toIndentedString(createdby)).append("\n");
    sb.append("    lastmodifiedby: ").append(StringUtil.toIndentedString(lastmodifiedby)).append("\n");
    sb.append("    applicationtype: ").append(StringUtil.toIndentedString(applicationtype)).append("\n");
    sb.append("    applicationtypeid: ").append(StringUtil.toIndentedString(applicationtypeid)).append("\n");
    sb.append("    id: ").append(StringUtil.toIndentedString(id)).append("\n");
    sb.append("    source: ").append(StringUtil.toIndentedString(source)).append("\n");
    sb.append("    nedid: ").append(StringUtil.toIndentedString(nedid)).append("\n");
    sb.append("    sourcetypeid: ").append(StringUtil.toIndentedString(sourcetypeid)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
