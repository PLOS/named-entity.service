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

import org.apache.commons.validator.routines.EmailValidator;
import org.plos.namedentity.api.NedValidationException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class Email extends Entity {

  private static EmailValidator emailValidator = EmailValidator.getInstance();

  private String  type;
  private Integer typeid;

  private String emailaddress;
  private Boolean isactive  = true;

  @Override
  public void validate() {

    if (emailaddress != null && !emailValidator.isValid(emailaddress))
      throw new NedValidationException("Email not valid");

  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || this.getClass() != o.getClass())
      return false;

    Email entity = (Email) o;
    return Objects.equals(this.type, entity.type)
        && Objects.equals(this.emailaddress, entity.emailaddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, emailaddress);
  }

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(java.lang.Integer typeid) {
    this.typeid = typeid;
  }

  public String getEmailaddress() {
    return this.emailaddress;
  }

  public void setEmailaddress(java.lang.String emailaddress) {
    this.emailaddress = emailaddress;
  }

  public Boolean getIsactive() {
    return this.isactive;
  }

  public void setIsactive(Boolean isactive) {
    this.isactive = isactive;
  }

  public String getType() {
      return type;
  }
  
  public void setType(String type) {
      this.type = type;
  }

}
