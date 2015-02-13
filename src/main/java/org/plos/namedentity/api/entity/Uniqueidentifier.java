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

import org.plos.namedentity.api.NedValidationException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.regex.Pattern;

@XmlRootElement
public class Uniqueidentifier extends Entity {

  private Integer typeid;
  private String  type;
  private String  uniqueidentifier;

  private static Integer casMinLen = 28;
  private static Integer casMaxLen = 36;
  private static Pattern casRegex = Pattern.compile("^[A-Za-z0-9-]+$");

  @Override
  public void validate() {

    if (uniqueidentifier == null || uniqueidentifier.length() < 1)
      throw new NedValidationException("uniqueidentifier is too short");

    if (type != null && type.equals("CAS"))
      validateCas();

  }

  private void validateCas() {

    if (uniqueidentifier.length() < casMinLen)
      throw new NedValidationException("CAS ID can not be shorter then " + casMinLen + " characters");

    if (uniqueidentifier.length() > casMaxLen)
      throw new NedValidationException("CAS ID can not be longer then " + casMaxLen + " characters");

    if (!casRegex.matcher(uniqueidentifier).matches())
      throw new NedValidationException("CAS ID contains invalid characters");
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

  public String getUniqueidentifier() {
    return this.uniqueidentifier;
  }

  public void setUniqueidentifier(String uniqueidentifier) {
    this.uniqueidentifier = uniqueidentifier;
  }

}
