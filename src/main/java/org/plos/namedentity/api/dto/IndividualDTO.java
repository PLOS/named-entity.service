/*
 * Copyright (c) 2006-2014 by Public Library of Science
 * http://plos.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.plos.namedentity.api.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IndividualDTO {

  private Integer namedentityid;
  private String  firstname;
  private String  middlename;
  private String  lastname;
  private String  nickname;
  private String  nameprefix;
  private String  namesuffix;
  private String  displayname;
  private String  preferredlanguage;
  private String  preferredcommunication;
  //private byte[]  photoimage;
  private String  url;

  private String  uniqueidentifiertype;
  private String  uniqueidentifier;

  public IndividualDTO() {}

  public IndividualDTO(
    Integer namedentityid,
    String  firstname,
    String  middlename,
    String  lastname,
    String  nickname,
    String  nameprefix,
    String  namesuffix,
    String  displayname,
    String  preferredlanguage,
    String  preferredcommunication,
    String  url
  ) {
    this.namedentityid          = namedentityid;
    this.firstname              = firstname;
    this.middlename             = middlename;
    this.lastname               = lastname;
    this.nickname               = nickname;
    this.nameprefix             = nameprefix;
    this.namesuffix             = namesuffix;
    this.displayname            = displayname;
    this.preferredlanguage      = preferredlanguage;
    this.preferredcommunication = preferredcommunication;
    this.url                    = url;
  }
  
  public Integer getNamedentityid() {
    return namedentityid;
  }
  public void setNamedentityid(Integer namedentityid) {
    this.namedentityid = namedentityid;
  }
  
  public String getFirstname() {
    return firstname;
  }
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }
  
  public String getMiddlename() {
    return middlename;
  }
  public void setMiddlename(String middlename) {
    this.middlename = middlename;
  }
  
  public String getLastname() {
    return lastname;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
  
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  
  public String getNameprefix() {
    return nameprefix;
  }
  public void setNameprefix(String nameprefix) {
    this.nameprefix = nameprefix;
  }
  
  public String getNamesuffix() {
    return namesuffix;
  }
  public void setNamesuffix(String namesuffix) {
    this.namesuffix = namesuffix;
  }
  
  public String getDisplayname() {
    return displayname;
  }
  public void setDisplayname(String displayname) {
    this.displayname = displayname;
  }
  
  public String getPreferredlanguage() {
    return preferredlanguage;
  }
  public void setPreferredlanguage(String preferredlanguage) {
    this.preferredlanguage = preferredlanguage;
  }
  
  public String getPreferredcommunication() {
    return preferredcommunication;
  }
  public void setPreferredcommunication(String preferredcommunication) {
    this.preferredcommunication = preferredcommunication;
  }
  
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getUniqueidentifiertype() {
    return uniqueidentifiertype;
  }
  public void setUniqueidentifiertype(String uniqueidentifiertype) {
    this.uniqueidentifiertype = uniqueidentifiertype;
  }
  
  public String getUniqueidentifier() {
    return uniqueidentifier;
  }
  public void setUniqueidentifier(String uniqueidentifier) {
    this.uniqueidentifier = uniqueidentifier;
  }
}
