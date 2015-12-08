package org.plos.ned_client.model;

import org.plos.ned_client.StringUtil;
import java.util.Date;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-12-07T17:01:32.713-08:00")
public class Address   {
  
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
  private String addressline1 = null;
  private String addressline2 = null;
  private String addressline3 = null;
  private String city = null;
  private Integer statecodetypeid = null;
  private String statecodetype = null;
  private Integer countrycodetypeid = null;
  private String countrycodetype = null;
  private String postalcode = null;
  private Integer maincontactnamedentityid = null;
  private Integer latitude = null;
  private Integer longitude = null;
  private Boolean isactive = false;

  
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
  @JsonProperty("addressline1")
  public String getAddressline1() {
    return addressline1;
  }
  public void setAddressline1(String addressline1) {
    this.addressline1 = addressline1;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("addressline2")
  public String getAddressline2() {
    return addressline2;
  }
  public void setAddressline2(String addressline2) {
    this.addressline2 = addressline2;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("addressline3")
  public String getAddressline3() {
    return addressline3;
  }
  public void setAddressline3(String addressline3) {
    this.addressline3 = addressline3;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("city")
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("statecodetypeid")
  public Integer getStatecodetypeid() {
    return statecodetypeid;
  }
  public void setStatecodetypeid(Integer statecodetypeid) {
    this.statecodetypeid = statecodetypeid;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("statecodetype")
  public String getStatecodetype() {
    return statecodetype;
  }
  public void setStatecodetype(String statecodetype) {
    this.statecodetype = statecodetype;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("countrycodetypeid")
  public Integer getCountrycodetypeid() {
    return countrycodetypeid;
  }
  public void setCountrycodetypeid(Integer countrycodetypeid) {
    this.countrycodetypeid = countrycodetypeid;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("countrycodetype")
  public String getCountrycodetype() {
    return countrycodetype;
  }
  public void setCountrycodetype(String countrycodetype) {
    this.countrycodetype = countrycodetype;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("postalcode")
  public String getPostalcode() {
    return postalcode;
  }
  public void setPostalcode(String postalcode) {
    this.postalcode = postalcode;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("maincontactnamedentityid")
  public Integer getMaincontactnamedentityid() {
    return maincontactnamedentityid;
  }
  public void setMaincontactnamedentityid(Integer maincontactnamedentityid) {
    this.maincontactnamedentityid = maincontactnamedentityid;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("latitude")
  public Integer getLatitude() {
    return latitude;
  }
  public void setLatitude(Integer latitude) {
    this.latitude = latitude;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("longitude")
  public Integer getLongitude() {
    return longitude;
  }
  public void setLongitude(Integer longitude) {
    this.longitude = longitude;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("isactive")
  public Boolean getIsactive() {
    return isactive;
  }
  public void setIsactive(Boolean isactive) {
    this.isactive = isactive;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    
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
    sb.append("    addressline1: ").append(StringUtil.toIndentedString(addressline1)).append("\n");
    sb.append("    addressline2: ").append(StringUtil.toIndentedString(addressline2)).append("\n");
    sb.append("    addressline3: ").append(StringUtil.toIndentedString(addressline3)).append("\n");
    sb.append("    city: ").append(StringUtil.toIndentedString(city)).append("\n");
    sb.append("    statecodetypeid: ").append(StringUtil.toIndentedString(statecodetypeid)).append("\n");
    sb.append("    statecodetype: ").append(StringUtil.toIndentedString(statecodetype)).append("\n");
    sb.append("    countrycodetypeid: ").append(StringUtil.toIndentedString(countrycodetypeid)).append("\n");
    sb.append("    countrycodetype: ").append(StringUtil.toIndentedString(countrycodetype)).append("\n");
    sb.append("    postalcode: ").append(StringUtil.toIndentedString(postalcode)).append("\n");
    sb.append("    maincontactnamedentityid: ").append(StringUtil.toIndentedString(maincontactnamedentityid)).append("\n");
    sb.append("    latitude: ").append(StringUtil.toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(StringUtil.toIndentedString(longitude)).append("\n");
    sb.append("    isactive: ").append(StringUtil.toIndentedString(isactive)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
