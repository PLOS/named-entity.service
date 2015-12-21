package org.plos.ned_client.model;

import org.plos.ned_client.StringUtil;
import java.util.Date;


import java.util.Objects;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;



@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-12-21T13:36:13.082-08:00")
public class Relationship   {
  
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
  private Integer nedidrelated = null;
  private Integer typeid = null;
  private String type = null;
  private String title = null;
  private Date startdate = null;
  private Date enddate = null;

  
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
  @JsonProperty("nedidrelated")
  public Integer getNedidrelated() {
    return nedidrelated;
  }
  public void setNedidrelated(Integer nedidrelated) {
    this.nedidrelated = nedidrelated;
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
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Relationship relationship = (Relationship) o;
    return Objects.equals(id, relationship.id) &&
        Objects.equals(nedid, relationship.nedid) &&
        Objects.equals(source, relationship.source) &&
        Objects.equals(sourcetypeid, relationship.sourcetypeid) &&
        Objects.equals(created, relationship.created) &&
        Objects.equals(lastmodified, relationship.lastmodified) &&
        Objects.equals(createdby, relationship.createdby) &&
        Objects.equals(createdbyname, relationship.createdbyname) &&
        Objects.equals(lastmodifiedby, relationship.lastmodifiedby) &&
        Objects.equals(lastmodifiedbyname, relationship.lastmodifiedbyname) &&
        Objects.equals(nedidrelated, relationship.nedidrelated) &&
        Objects.equals(typeid, relationship.typeid) &&
        Objects.equals(type, relationship.type) &&
        Objects.equals(title, relationship.title) &&
        Objects.equals(startdate, relationship.startdate) &&
        Objects.equals(enddate, relationship.enddate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nedid, source, sourcetypeid, created, lastmodified, createdby, createdbyname, lastmodifiedby, lastmodifiedbyname, nedidrelated, typeid, type, title, startdate, enddate);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Relationship {\n");
    
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
    sb.append("    nedidrelated: ").append(StringUtil.toIndentedString(nedidrelated)).append("\n");
    sb.append("    typeid: ").append(StringUtil.toIndentedString(typeid)).append("\n");
    sb.append("    type: ").append(StringUtil.toIndentedString(type)).append("\n");
    sb.append("    title: ").append(StringUtil.toIndentedString(title)).append("\n");
    sb.append("    startdate: ").append(StringUtil.toIndentedString(startdate)).append("\n");
    sb.append("    enddate: ").append(StringUtil.toIndentedString(enddate)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
