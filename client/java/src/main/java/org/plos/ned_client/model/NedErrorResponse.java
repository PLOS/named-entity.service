package org.plos.ned_client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import java.util.Date;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-02-05T00:03:06.989-08:00")
public class NedErrorResponse   {
  
  private String failureMsg = null;
  private Integer errorCode = null;
  private String errorMsg = null;
  private String detailedMsg = null;
  private List<String> acceptableValues = new ArrayList<String>();
  private Date timestamp = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("failureMsg")
  public String getFailureMsg() {
    return failureMsg;
  }
  public void setFailureMsg(String failureMsg) {
    this.failureMsg = failureMsg;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("errorCode")
  public Integer getErrorCode() {
    return errorCode;
  }
  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("errorMsg")
  public String getErrorMsg() {
    return errorMsg;
  }
  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("detailedMsg")
  public String getDetailedMsg() {
    return detailedMsg;
  }
  public void setDetailedMsg(String detailedMsg) {
    this.detailedMsg = detailedMsg;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("acceptableValues")
  public List<String> getAcceptableValues() {
    return acceptableValues;
  }
  public void setAcceptableValues(List<String> acceptableValues) {
    this.acceptableValues = acceptableValues;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("timestamp")
  public Date getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NedErrorResponse nedErrorResponse = (NedErrorResponse) o;

    return true && Objects.equals(failureMsg, nedErrorResponse.failureMsg) &&
        Objects.equals(errorCode, nedErrorResponse.errorCode) &&
        Objects.equals(errorMsg, nedErrorResponse.errorMsg) &&
        Objects.equals(detailedMsg, nedErrorResponse.detailedMsg) &&
        Objects.equals(acceptableValues, nedErrorResponse.acceptableValues) &&
        Objects.equals(timestamp, nedErrorResponse.timestamp)
    ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(failureMsg, errorCode, errorMsg, detailedMsg, acceptableValues, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NedErrorResponse {\n");
    
    sb.append("    failureMsg: ").append(toIndentedString(failureMsg)).append("\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errorMsg: ").append(toIndentedString(errorMsg)).append("\n");
    sb.append("    detailedMsg: ").append(toIndentedString(detailedMsg)).append("\n");
    sb.append("    acceptableValues: ").append(toIndentedString(acceptableValues)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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

