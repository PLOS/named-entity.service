package org.plos.ned_client.model;

import org.plos.ned_client.StringUtil;
import java.util.Date;


import java.util.Objects;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;



@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-12-21T13:36:13.082-08:00")
public class Uniqueidentifier   {
  
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
  private Integer typeid = null;
  private String type = null;
  private String uniqueidentifier = null;
  private String metadata = null;

  
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
  @JsonProperty("uniqueidentifier")
  public String getUniqueidentifier() {
    return uniqueidentifier;
  }
  public void setUniqueidentifier(String uniqueidentifier) {
    this.uniqueidentifier = uniqueidentifier;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("metadata")
  public String getMetadata() {
    return metadata;
  }
  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Uniqueidentifier uniqueidentifier = (Uniqueidentifier) o;
    return Objects.equals(id, uniqueidentifier.id) &&
        Objects.equals(nedid, uniqueidentifier.nedid) &&
        Objects.equals(source, uniqueidentifier.source) &&
        Objects.equals(sourcetypeid, uniqueidentifier.sourcetypeid) &&
        Objects.equals(created, uniqueidentifier.created) &&
        Objects.equals(lastmodified, uniqueidentifier.lastmodified) &&
        Objects.equals(createdby, uniqueidentifier.createdby) &&
        Objects.equals(createdbyname, uniqueidentifier.createdbyname) &&
        Objects.equals(lastmodifiedby, uniqueidentifier.lastmodifiedby) &&
        Objects.equals(lastmodifiedbyname, uniqueidentifier.lastmodifiedbyname) &&
        Objects.equals(typeid, uniqueidentifier.typeid) &&
        Objects.equals(type, uniqueidentifier.type) &&
        Objects.equals(uniqueidentifier, uniqueidentifier.uniqueidentifier) &&
        Objects.equals(metadata, uniqueidentifier.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nedid, source, sourcetypeid, created, lastmodified, createdby, createdbyname, lastmodifiedby, lastmodifiedbyname, typeid, type, uniqueidentifier, metadata);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Uniqueidentifier {\n");
    
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
    sb.append("    typeid: ").append(StringUtil.toIndentedString(typeid)).append("\n");
    sb.append("    type: ").append(StringUtil.toIndentedString(type)).append("\n");
    sb.append("    uniqueidentifier: ").append(StringUtil.toIndentedString(uniqueidentifier)).append("\n");
    sb.append("    metadata: ").append(StringUtil.toIndentedString(metadata)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
