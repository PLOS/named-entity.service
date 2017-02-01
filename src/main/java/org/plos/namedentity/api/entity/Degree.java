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

import javax.xml.bind.annotation.XmlRootElement;

import static org.plos.namedentity.api.NedException.ErrorType.InvalidDegreeError;

@XmlRootElement
public class Degree extends Entity {

  private Integer typeid;
  private String  type;
  private String  fulltitle;

  @Override
  public void validate() {
    if ((type == null || type.length() < 1) &&
        (fulltitle == null || fulltitle.length() < 1)) {
      throw new NedException(InvalidDegreeError, 
        "a valid degree requires a type and/or full title.");
    }
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getTypeid() {
    return this.typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  public String getFulltitle() {
      return fulltitle;
  }

  public void setFulltitle(String fulltitle) {
      this.fulltitle = fulltitle;
  }
}
