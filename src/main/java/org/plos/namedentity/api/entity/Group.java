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

import org.plos.namedentity.api.adapter.DateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.time.LocalDate;

@XmlRootElement
public class Group extends Entity {

  private Integer typeid;
  private String  type;

  private LocalDate startdate;
  private LocalDate enddate;

  private Integer createdby;
  private Integer lastmodifiedby;

  private String  applicationtype;
  private Integer applicationtypeid;

  private Timestamp lastmodified;

  public String getApplicationtype() {
    return applicationtype;
  }

  public void setApplicationtype(String applicationtype) {
    this.applicationtype = applicationtype;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getApplicationtypeid() {
    return this.applicationtypeid;
  }

  public void setApplicationtypeid(Integer applicationtypeid) {
    this.applicationtypeid = applicationtypeid;
  }

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  @XmlJavaTypeAdapter(DateAdapter.class)
  public LocalDate getStartdate() {
    return this.startdate;
  }

  @XmlJavaTypeAdapter(DateAdapter.class)
  public void setStartdate(LocalDate startdate) {
    this.startdate = startdate;
  }

  @XmlJavaTypeAdapter(DateAdapter.class)
  public LocalDate getEnddate() {
    return this.enddate;
  }

  @XmlJavaTypeAdapter(DateAdapter.class)
  public void setEnddate(LocalDate enddate) {
    this.enddate = enddate;
  }

  public Integer getCreatedby() {
    return this.createdby;
  }

  public void setCreatedby(Integer createdby) {
    this.createdby = createdby;
  }

  // TODO: fix how this is rendering in XML
  public Integer getLastmodifiedby() {
    return this.lastmodifiedby;
  }

  public void setLastmodifiedby(Integer lastmodifiedby) {
    this.lastmodifiedby = lastmodifiedby;
  }

}
