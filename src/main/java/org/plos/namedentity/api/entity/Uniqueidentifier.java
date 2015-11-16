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

import org.eclipse.persistence.oxm.annotations.XmlPath;

import org.plos.namedentity.api.adapter.JsonAdapter;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.enums.UidTypeEnum;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static org.plos.namedentity.api.NedException.ErrorType.InvalidOrcidId;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidSalesforceId;
import static org.plos.namedentity.api.NedException.ErrorType.UidValueError;

/*
  Here's a brief explanation of the annotations used in this pojo. Some of the
  complexity is because the pojo is used in more than one context. In a
  nutshell, JOOQ calls the metadata's getter when constructing update SQL, and
  the setter when populating pojo from a sql result. On the other hand, JAXB
  calls the accessors marked with the XmlElement "metadata" annotation. There
  are two representations of metadata. One is a string of the json metadata that
  is stored in the database; the other representation is a map of the parsed
  json metadata. The map is used by JAXB to render the JSON object.

  Here is a high-level sequence diagram of the marshalling.

    MARSHALLING [JSON <- POJO] (ex: Reading Metadata From Database)

      // JOOQ reflects on pojo and calls setter

      Uniqueidentifier.setMetadata()

      // JAXB calls getter on "metadata" property

      Uniqueidentifier.getMetadataMap()

      // JAXB marshals metadata from map of strings to json.

      org.plos.namedentity.api.adapter.MetadataAdapter.marshal()


    UNMARSHALLING [JSON -> POJO] (ex: Invoking API To Update UID Record)

      // caller invokes update uid endpoint. if metadata is defined, it is expected
      // to have a transient root, such as "json", but can be anything. root isn't
      // persisted to the database.

      org.plos.namedentity.api.adapter.MetadataAdapter.unmarshal()

      // JAXB calls setter on "metadata" property.

      Uniqueidentifier.setMetadataMap()

      // JOOQ calls metadata getter to retrieve value to update in database.

      Uniqueidentifier.getMetadata()

      // NED-API reads updated composite from database (ie, marshals. see above)
*/

@XmlRootElement
public class Uniqueidentifier extends Entity {

  private Integer typeid;
  private String  type;
  private String  uniqueidentifier;

  private String metadata;
  private Map<String,String> metadataMap;

  private static Integer salesForceLengthA = 15;
  private static Integer salesForceLengthB = 18;
  private static Pattern salesForceRegexp  = Pattern.compile("^[a-zA-Z0-9]*$");
  private static Pattern orcidRegexp  = Pattern.compile("^([\\d]{4}\\-?){3}[\\d]{3}[\\dxX]$");

  public Uniqueidentifier() {
    metadataMap = new HashMap<String, String>();
  }

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

  @XmlElement(name = "metadata")
  @XmlJavaTypeAdapter(JsonAdapter.class)
  public Map<String,String> getMetadataMap() {
    return this.metadataMap;
  }

  @XmlElement(name = "metadata")
  @XmlJavaTypeAdapter(JsonAdapter.class)
  public void setMetadataMap(Map<String,String> metadataMap) {
    this.metadataMap = metadataMap;
    if (metadataMap != null) {
      this.metadata = JsonAdapter.jsonifyMap(metadataMap);
    }
  }

  @XmlTransient
  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
    if (metadata != null) {
      this.metadataMap = JsonAdapter.parseAsMap(metadata);
    }
  }
}
