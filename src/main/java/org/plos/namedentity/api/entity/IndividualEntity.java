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
package org.plos.namedentity.api.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Modified JOOQ generated class(pojo=true).
 */
@XmlRootElement
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IndividualEntity implements java.io.Serializable {

  private static final long serialVersionUID = 2026965529;

  private Integer namedentityid;
  private String  firstname;
  private String  middlename;
  private String  lastname;
  private String  nickname;

  private String  nameprefix;
  private Integer nameprefixtypeid;

  private String  namesuffix;
  private Integer namesuffixtypeid;

  private String  displayname;

  private String  preferredlanguage;
  private Integer preferredlanguagetypeid;

  private String  preferredcommunication;
  private Integer preferredcommunicationmethodtypeid;

  private byte[]  photoimage;
  private String  url;

  public IndividualEntity() {}

  public IndividualEntity(
      Integer namedentityid,
      String  firstname,
      String  middlename,
      String  lastname,
      String  nickname,
      String  nameprefix,
      Integer nameprefixtypeid,
      String  namesuffix,
      Integer namesuffixtypeid,
      String  displayname,
      String  preferredlanguage,
      Integer preferredlanguagetypeid,
      String  preferredcommunication,
      Integer preferredcommunicationmethodtypeid,
      byte[]  photoimage,
      String  url
  ) {
    this(namedentityid,
        firstname,
        middlename,
        lastname,
        nickname,
        nameprefixtypeid,
        namesuffixtypeid,
        displayname,
        preferredlanguagetypeid,
        preferredcommunicationmethodtypeid,
        photoimage,
        url);

    this.namesuffix = namesuffix;
    this.nameprefix = nameprefix;
    this.preferredlanguage = preferredlanguage;
    this.preferredcommunication = preferredcommunication;
  }
  
  public IndividualEntity(
    Integer namedentityid,
    String  firstname,
    String  middlename,
    String  lastname,
    String  nickname,
    Integer nameprefixtypeid,
    Integer namesuffixtypeid,
    String  displayname,
    Integer preferredlanguagetypeid,
    Integer preferredcommunicationmethodtypeid,
    byte[]  photoimage,
    String  url
  ) {
    this.namedentityid = namedentityid;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.nameprefixtypeid = nameprefixtypeid;
    this.namesuffixtypeid = namesuffixtypeid;
    this.displayname = displayname;
    this.preferredlanguagetypeid = preferredlanguagetypeid;
    this.preferredcommunicationmethodtypeid = preferredcommunicationmethodtypeid;
    this.photoimage = photoimage;
    this.url = url;
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

  public Integer getNamedentityid() {
    return this.namedentityid;
  }

  public void setNamedentityid(Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public String getFirstname() {
    return this.firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getMiddlename() {
    return this.middlename;
  }

  public void setMiddlename(String middlename) {
    this.middlename = middlename;
  }

  public String getLastname() {
    return this.lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getNickname() {
    return this.nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public Integer getNameprefixtypeid() {
    return this.nameprefixtypeid;
  }

  public void setNameprefixtypeid(Integer nameprefixtypeid) {
    this.nameprefixtypeid = nameprefixtypeid;
  }

  public Integer getNamesuffixtypeid() {
    return this.namesuffixtypeid;
  }

  public void setNamesuffixtypeid(Integer namesuffixtypeid) {
    this.namesuffixtypeid = namesuffixtypeid;
  }

  public String getDisplayname() {
    return this.displayname;
  }

  public void setDisplayname(String displayname) {
    this.displayname = displayname;
  }

  public Integer getPreferredlanguagetypeid() {
    return this.preferredlanguagetypeid;
  }

  public void setPreferredlanguagetypeid(Integer preferredlanguagetypeid) {
    this.preferredlanguagetypeid = preferredlanguagetypeid;
  }

  public Integer getPreferredcommunicationmethodtypeid() {
    return this.preferredcommunicationmethodtypeid;
  }

  public void setPreferredcommunicationmethodtypeid(Integer preferredcommunicationmethodtypeid) {
    this.preferredcommunicationmethodtypeid = preferredcommunicationmethodtypeid;
  }

  public byte[] getPhotoimage() {
    return this.photoimage;
  }

  public void setPhotoimage(byte[] photoimage) {
    this.photoimage = photoimage;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }

    if (o == null || this.getClass() != o.getClass()) { return false; }

    IndividualEntity entity = (IndividualEntity) o;
    return Objects.equals(this.namedentityid, entity.namedentityid)
        && Objects.equals(this.firstname, entity.firstname)
        && Objects.equals(this.middlename, entity.middlename)
        && Objects.equals(this.lastname, entity.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(namedentityid, firstname, middlename, lastname);
  }
}
