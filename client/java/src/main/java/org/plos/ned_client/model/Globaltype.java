package org.plos.ned_client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-02-05T00:03:06.989-08:00")
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
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Globaltype globaltype = (Globaltype) o;

    return true && Objects.equals(id, globaltype.id) &&
        Objects.equals(typeid, globaltype.typeid) &&
        Objects.equals(shortdescription, globaltype.shortdescription) &&
        Objects.equals(longdescription, globaltype.longdescription) &&
        Objects.equals(typecode, globaltype.typecode) &&
        Objects.equals(created, globaltype.created) &&
        Objects.equals(lastmodified, globaltype.lastmodified) &&
        Objects.equals(createdby, globaltype.createdby) &&
        Objects.equals(lastmodifiedby, globaltype.lastmodifiedby)
    ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typeid, shortdescription, longdescription, typecode, created, lastmodified, createdby, lastmodifiedby);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Globaltype {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    typeid: ").append(toIndentedString(typeid)).append("\n");
    sb.append("    shortdescription: ").append(toIndentedString(shortdescription)).append("\n");
    sb.append("    longdescription: ").append(toIndentedString(longdescription)).append("\n");
    sb.append("    typecode: ").append(toIndentedString(typecode)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    lastmodified: ").append(toIndentedString(lastmodified)).append("\n");
    sb.append("    createdby: ").append(toIndentedString(createdby)).append("\n");
    sb.append("    lastmodifiedby: ").append(toIndentedString(lastmodifiedby)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

