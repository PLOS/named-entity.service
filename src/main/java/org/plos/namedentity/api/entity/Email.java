/*
 * Copyright (c) 2014-2019 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package org.plos.namedentity.api.entity;

import org.plos.namedentity.api.NedException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.regex.Pattern;

import static org.plos.namedentity.api.NedException.ErrorType.InvalidEmail;

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
