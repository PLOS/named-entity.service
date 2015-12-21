package org.plos.ned_client.model;

import org.plos.ned_client.StringUtil;
import java.util.Date;


import java.util.Objects;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;



@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-12-21T13:36:13.082-08:00")
public class Typedescription   {
  
  private Integer id = null;
  private String description = null;
  private String howused = null;
  private Date created = null;
  private Date lastmodified = null;

  
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
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("howused")
  public String getHowused() {
    return howused;
  }
  public void setHowused(String howused) {
    this.howused = howused;
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Typedescription typedescription = (Typedescription) o;
    return Objects.equals(id, typedescription.id) &&
        Objects.equals(description, typedescription.description) &&
        Objects.equals(howused, typedescription.howused) &&
        Objects.equals(created, typedescription.created) &&
        Objects.equals(lastmodified, typedescription.lastmodified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, howused, created, lastmodified);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Typedescription {\n");
    
    sb.append("    id: ").append(StringUtil.toIndentedString(id)).append("\n");
    sb.append("    description: ").append(StringUtil.toIndentedString(description)).append("\n");
    sb.append("    howused: ").append(StringUtil.toIndentedString(howused)).append("\n");
    sb.append("    created: ").append(StringUtil.toIndentedString(created)).append("\n");
    sb.append("    lastmodified: ").append(StringUtil.toIndentedString(lastmodified)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
