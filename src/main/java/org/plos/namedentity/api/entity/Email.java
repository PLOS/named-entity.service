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

import static org.plos.namedentity.api.NedException.ErrorType.*;

import org.plos.namedentity.api.NedException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.regex.Pattern;

@XmlRootElement
public class Email extends Entity {

  private static final Pattern emailRegexp = Pattern.compile("^.{1,64}@.{1,63}(\\..{1,63})+$");

  private String  type;
  private Integer typeid;

  private String  emailaddress;
  private Boolean verified;
  private Boolean isactive;

  @Override
  public void validate() {

    if (emailaddress == null || !validateEmail(emailaddress))
      throw new NedException(InvalidEmail, "Email not valid");
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

  public Boolean getVerified() {
    return this.verified;
  }

  public void setVerified(Boolean verified) {
    this.verified = verified;
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

  public static boolean validateEmail(String email) {
    return ( emailRegexp.matcher(email).matches() );
  }
}
