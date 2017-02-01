/*
 * Copyright (c) 2017 Public Library of Science
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
import org.plos.namedentity.api.adapter.DateAdapter;
import org.plos.namedentity.api.enums.RelationshipTypeEnum;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

import static org.plos.namedentity.api.NedException.ErrorType.InvalidRelationshipError;

@XmlRootElement
public class Relationship extends Entity {

  private Integer nedidrelated;

  private Integer typeid;
  private String  type;
  private String  title;

  @XmlJavaTypeAdapter(DateAdapter.class)
  private LocalDate startdate;

  @XmlJavaTypeAdapter(DateAdapter.class)
  private LocalDate enddate;

  @Override
  public void validate() {
    if (type == null || !validateRelationType(type))
      throw new NedException(InvalidRelationshipError, "Relationship type not valid");

    if (nedidrelated == null)
      throw new NedException(InvalidRelationshipError, "Relationship nedidrelated not defined");
  }

  public Integer getNedidrelated() {
    return nedidrelated;
  }

  public void setNedidrelated(Integer nedidrelated) {
    this.nedidrelated = nedidrelated;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  public LocalDate getStartdate() {
    return this.startdate;
  }

  public void setStartdate(LocalDate startdate) {
    this.startdate = startdate;
  }

  public LocalDate getEnddate() {
    return this.enddate;
  }

  public void setEnddate(LocalDate enddate) {
    this.enddate = enddate;
  }

  public String getType() {
      return type;
  }

  public void setType(String type) {
      this.type = type;
  }

  public static boolean validateRelationType(String relType) {
    RelationshipTypeEnum relEnum = RelationshipTypeEnum.getRelationshipTypeEnum(relType);
    return (relEnum != RelationshipTypeEnum.INVALID_RELATIONSHIP_TYPE);
  }
}
