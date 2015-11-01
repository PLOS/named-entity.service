package org.plos.ned_client.model;

import org.plos.ned_client.StringUtil;
import java.util.*;
import java.util.Date;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-11-01T07:39:28.231-08:00")
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
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class NedErrorResponse {\n");
    
    sb.append("    failureMsg: ").append(StringUtil.toIndentedString(failureMsg)).append("\n");
    sb.append("    errorCode: ").append(StringUtil.toIndentedString(errorCode)).append("\n");
    sb.append("    errorMsg: ").append(StringUtil.toIndentedString(errorMsg)).append("\n");
    sb.append("    detailedMsg: ").append(StringUtil.toIndentedString(detailedMsg)).append("\n");
    sb.append("    acceptableValues: ").append(StringUtil.toIndentedString(acceptableValues)).append("\n");
    sb.append("    timestamp: ").append(StringUtil.toIndentedString(timestamp)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
