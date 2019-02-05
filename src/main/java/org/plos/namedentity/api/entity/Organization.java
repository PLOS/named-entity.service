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

import static org.plos.namedentity.api.NedException.ErrorType.*;

import org.plos.namedentity.api.NedException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Organization extends Entity {

  private Integer typeid;
  private String  type;

  private String  familiarname;
  private String  legalname;
  private Integer maincontactid;
  private Boolean isactive;

  @Override
  public void validate() {

    if (familiarname == null || familiarname.length() < 1)
      throw new NedException(FamiliarNameError, "familiarname is too short");

    if (legalname == null || legalname.length() < 1)
      throw new NedException(LegalNameError, "legalname is too short");
 }

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  public String getFamiliarname() {
    return this.familiarname;
  }

  public void setFamiliarname(String familiarname) {
    this.familiarname = familiarname;
  }

  public String getLegalname() {
    return this.legalname;
  }

  public void setLegalname(String legalname) {
    this.legalname = legalname;
  }

  public Integer getMaincontactid() {
    return this.maincontactid;
  }

  public void setMaincontactid(Integer maincontactid) {
    this.maincontactid = maincontactid;
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
