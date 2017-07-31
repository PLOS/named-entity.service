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

import static org.plos.namedentity.api.NedException.ErrorType.InvalidJsonError;
import static org.plos.namedentity.api.NedException.ErrorType.AlertTypeError;
import static org.plos.namedentity.validate.JsonValidator.validJson;

/**
 * Created by jfinger on 11/5/15.
 */

@XmlRootElement
public class Alert extends Entity {

  private Integer typeid;
  private String  type;

  private Integer frequencytypeid;
  private String  frequency;

  private String name;
  private String query;

  @Override
  public void validate() {
    if (query != null) {
      String validMetadataJson = validJson(query);
      if (validMetadataJson == null) {
        throw new NedException(InvalidJsonError, "query field contains invalid JSON : " + query);
      }
      this.query = validMetadataJson;
    }
    if (typeid == null) {
      throw new NedException(AlertTypeError, "undefined alert type. this is a required field.");
    }
  }

  public Integer getTypeid() {
    return typeid;
  }

  public void setTypeid(Integer typeid) {
    this.typeid = typeid;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getFrequencytypeid() {
    return frequencytypeid;
  }

  public void setFrequencytypeid(Integer frequencytypeid) {
    this.frequencytypeid = frequencytypeid;
  }

  public String getFrequency() {
    return frequency;
  }

  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
