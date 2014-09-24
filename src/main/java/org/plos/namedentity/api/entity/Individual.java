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

@XmlRootElement
public class Individual extends ParentEntity {

  private String  firstname;
  private String  middlename;
  private String  lastname;
  private String  nickname;

  private String  nameprefix;
  private Integer nameprefixtypeid;

  private String  namesuffix;
  private Integer namesuffixtypeid;

  private String displayname;

  private String  preferredlanguage;
  private Integer preferredlanguagetypeid;

  private String  preferredcommunication;
  private Integer preferredcommunicationmethodtypeid;

  private byte[] photoimage;

  private Boolean isactive = true;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }

    Individual entity = (Individual) o;
    return Objects.equals(this.firstname, entity.firstname)
        && Objects.equals(this.middlename, entity.middlename)
        && Objects.equals(this.lastname, entity.lastname)
        && Objects.equals(this.nickname, entity.nickname)
        && Objects.equals(this.nameprefix, entity.nameprefix)
        && Objects.equals(this.namesuffix, entity.namesuffix)
        && Objects.equals(this.displayname, entity.displayname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, middlename, lastname, nickname,
        nameprefix, namesuffix, displayname);
  }

  public Boolean getIsactive() {
    return isactive;
  }

  public void setIsactive(Boolean isactive) {
    this.isactive = isactive;
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

}
