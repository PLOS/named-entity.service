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
import org.plos.namedentity.api.enums.UidTypeEnum;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.regex.Pattern;

import static org.plos.namedentity.api.NedException.ErrorType.InvalidJsonError;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidOrcidId;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidSalesforceId;
import static org.plos.namedentity.api.NedException.ErrorType.UidValueError;
import static org.plos.namedentity.validate.JsonValidator.validJson;

@XmlRootElement
public class Uniqueidentifier extends Entity {

  private Integer typeid;
  private String  type;
  private String  uniqueidentifier;
  private String  metadata;

  private static Integer salesForceLengthA = 15;
  private static Integer salesForceLengthB = 18;
  private static Pattern salesForceRegexp  = Pattern.compile("^[a-zA-Z0-9]*$");
  private static Pattern orcidRegexp  = Pattern.compile("^([\\d]{4}\\-?){3}[\\d]{3}[\\dxX]$");

  @Override
  public void validate() {

    if (uniqueidentifier == null || uniqueidentifier.length() < 1)
      throw new NedException(UidValueError, "uniqueidentifier is too short");

    if (UidTypeEnum.SALESFORCE.getName().equals(type)
        && !validateSalesforceId(uniqueidentifier))
      throw new NedException(InvalidSalesforceId, "invalid salesforce ID " + uniqueidentifier);
    else if (UidTypeEnum.ORCID.getName().equals(type)
        && !validateOrcid(uniqueidentifier))
      throw new NedException(InvalidOrcidId, "invalid ORCID " + uniqueidentifier);

    if (metadata != null) {
      String validMetadataJson = validJson(metadata);
      if (validMetadataJson == null) {
        throw new NedException(InvalidJsonError, "metadata field contains invalid JSON : "+metadata);
      }
      this.metadata = validMetadataJson;
    }
  }

  private static boolean validateSalesforceId(String salesforceId) {
    // http://developer.force.com/cookbook/recipe/validating-an-id
    return ((salesforceId.length() == salesForceLengthA
        || salesforceId.length() == salesForceLengthB)
        && salesForceRegexp.matcher(salesforceId).matches());
  }

  private static boolean validateOrcid(String id) {
    return ( orcidRegexp.matcher(id).matches() );
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

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }
}
