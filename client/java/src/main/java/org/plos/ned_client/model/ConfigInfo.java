package org.plos.ned_client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-03T13:54:47.854-08:00")
public class ConfigInfo   {
  
  private String version = null;
  private Date startime = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("version")
  public String getVersion() {
    return version;
  }
  public void setVersion(String version) {
    this.version = version;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("startime")
  public Date getStartime() {
    return startime;
  }
  public void setStartime(Date startime) {
    this.startime = startime;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigInfo configInfo = (ConfigInfo) o;
    return Objects.equals(version, configInfo.version) &&
        Objects.equals(startime, configInfo.startime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, startime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigInfo {\n");
    
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    startime: ").append(toIndentedString(startime)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

