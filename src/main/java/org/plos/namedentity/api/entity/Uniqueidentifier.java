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

import org.plos.namedentity.persist.UidTypeEnum;
import org.plos.namedentity.api.NedValidationException;

import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Uniqueidentifier extends Entity {

  private Integer typeid;
  private String  type;
  private String  uniqueidentifier;

  @Override
  public void validate() {

    if (type == null) { throw new NedValidationException("uid type is null"); }
    if (uniqueidentifier == null) { throw new NedValidationException("uid value is null"); }

    switch (UidTypeEnum.getUidTypeEnum(type)) {
      case SALESFORCE: 
        if ( !validateSalesforceId(uniqueidentifier) )
          throw new NedValidationException("invalid salesforce id:" + uniqueidentifier);
        break;
    }

    throw new NedValidationException("unrecognized uid type:" + type);
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

  // http://developer.force.com/cookbook/recipe/validating-an-id
  private boolean validateSalesforceId(String salesforceId) {
    return ((salesforceId.length() == 15 || salesforceId.length() == 18) 
             && Pattern.matches("^[a-zA-Z0-9]*$", salesforceId));
  }
}
