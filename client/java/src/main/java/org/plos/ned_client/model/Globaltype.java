package org.plos.ned_client.model;

import org.plos.ned_client.StringUtil;
import java.util.Date;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-12-07T17:01:32.713-08:00")
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
