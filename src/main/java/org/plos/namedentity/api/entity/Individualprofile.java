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

import org.plos.namedentity.api.NedException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.regex.Pattern;

import static org.plos.namedentity.api.NedException.ErrorType.DisplayNameError;
import static org.plos.namedentity.api.NedException.ErrorType.FirstnameError;
import static org.plos.namedentity.api.NedException.ErrorType.LastnameError;

@XmlRootElement
public class Individualprofile extends Entity {

  public static final Integer DISPLAYNAME_MAX_LENGTH = 60;

  public static Pattern rejectedCharsDisplayName = Pattern.compile("[$&+,:;=?@#|/\\s^~`%<>{}\\[\\]\\\\]");

  private String  firstname;
  private String  middlename;
  private String  lastname;
  private String  nickname;

  private String  nameprefix;
  private Integer nameprefixtypeid;

  private String  namesuffix;
  private Integer namesuffixtypeid;

  private String displayname;

  private String biography;

  private Boolean isactive;


  @Override
  public void validate() {
    validateFirstname();
    validateLastname();
    validateDisplayname(displayname);
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

  public String getBiography() {
    return biography;
  }

  public void setBiography(String biography) {
    this.biography = biography;
  }

  public void validateFirstname() {
    if (firstname == null || firstname.length() < 1)
      throw new NedException(FirstnameError, "first name is too short");
  }

  public void validateLastname() {
    if (lastname == null || lastname.length() < 1)
      throw new NedException(LastnameError, "last name is too short");
  }

  public static void validateDisplayname(String dname) {
    if (dname == null || dname.length() < 1)
      throw new NedException(DisplayNameError, "display name is too short");

    if (dname.length() > DISPLAYNAME_MAX_LENGTH)
      throw new NedException(DisplayNameError, "display name cannot be longer then " + DISPLAYNAME_MAX_LENGTH);

    if (rejectedCharsDisplayName.matcher(dname).find())
      throw new NedException(DisplayNameError, "display name can not contain any of the following characters: $ & + , / : ; = ? @ < > # % { } | \\ ^ ~ [ ] ` or a space");
  }
}
