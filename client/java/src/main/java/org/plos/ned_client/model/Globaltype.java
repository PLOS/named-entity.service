package org.plos.ned_client.model;

import org.plos.ned_client.StringUtil;
import java.util.Date;


import java.util.Objects;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;



@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-12-21T13:36:13.082-08:00")
public class Globaltype   {
  
  private Integer id = null;
  private Integer typeid = null;
  private String shortdescription = null;
  private String longdescription = null;
  private String typecode = null;
  private Date created = null;
  private Date lastmodified = null;
  private Integer createdby = null;
  private Integer lastmodifiedby = null;

  
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
  @JsonProperty("shortdescription")
  public String getShortdescription() {
    return shortdescription;
  }
  public void setShortdescription(String shortdescription) {
    this.shortdescription = shortdescription;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("longdescription")
  public String getLongdescription() {
    return longdescription;
  }
  public void setLongdescription(String longdescription) {
    this.longdescription = longdescription;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("typecode")
  public String getTypecode() {
    return typecode;
  }
  public void setTypecode(String typecode) {
    this.typecode = typecode;
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
  @JsonProperty("lastmodifiedby")
  public Integer getLastmodifiedby() {
    return lastmodifiedby;
  }
  public void setLastmodifiedby(Integer lastmodifiedby) {
    this.lastmodifiedby = lastmodifiedby;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Globaltype globaltype = (Globaltype) o;
    return Objects.equals(id, globaltype.id) &&
        Objects.equals(typeid, globaltype.typeid) &&
        Objects.equals(shortdescription, globaltype.shortdescription) &&
        Objects.equals(longdescription, globaltype.longdescription) &&
        Objects.equals(typecode, globaltype.typecode) &&
        Objects.equals(created, globaltype.created) &&
        Objects.equals(lastmodified, globaltype.lastmodified) &&
        Objects.equals(createdby, globaltype.createdby) &&
        Objects.equals(lastmodifiedby, globaltype.lastmodifiedby);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typeid, shortdescription, longdescription, typecode, created, lastmodified, createdby, lastmodifiedby);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Globaltype {\n");
    
    sb.append("    id: ").append(StringUtil.toIndentedString(id)).append("\n");
    sb.append("    typeid: ").append(StringUtil.toIndentedString(typeid)).append("\n");
    sb.append("    shortdescription: ").append(StringUtil.toIndentedString(shortdescription)).append("\n");
    sb.append("    longdescription: ").append(StringUtil.toIndentedString(longdescription)).append("\n");
    sb.append("    typecode: ").append(StringUtil.toIndentedString(typecode)).append("\n");
    sb.append("    created: ").append(StringUtil.toIndentedString(created)).append("\n");
    sb.append("    lastmodified: ").append(StringUtil.toIndentedString(lastmodified)).append("\n");
    sb.append("    createdby: ").append(StringUtil.toIndentedString(createdby)).append("\n");
    sb.append("    lastmodifiedby: ").append(StringUtil.toIndentedString(lastmodifiedby)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
