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
package org.plos.namedentity.api.enums;

import java.util.EnumSet;

public enum TypeClassEnum {

  COMMUNICATION_METHODS("Communication Methods"),
  COUNTRY_CODES("Country Types"),
  COUNTRY_CODE_FOR_PHONE("Country Codes for Phone Numbers"),
  DEGREES("Degrees"),
  EMAIL_TYPES("Email Address Types"),
  JOURNAL_TYPES("Journal Types"),
  KNOWLEDGE_BASE_GROUPS("Knowledge Base Groups"),
  LANGUAGES("Languages"),
  NAMED_ENTITY_PREFIX("Named Party Prefixes"),
  NAMED_ENTITY_TYPES("Named Party Types"),
  NED_ROLES("NED Roles"),
  ORGANIZATION_TYPES("Organization Types"),
  PHYSICAL_ADDRESS_TYPES("Physical Address Types"),
  RELATIONSHIP_TYPES("Relationship Types"),
  SOURCE_APPLICATIONS("Source Applications"),
  STATES_AND_PROVINCES("State and Province Codes"),
  SUBJECT_AREAS("Subject Areas"),
  TELEPHONE_TYPES("Telephone Number Types"),
  UID_INDIVIDUAL_TYPES("UID Individual Types"),
  UID_ORGANIZATION_TYPES("UID Organization Types"),
  INVALID_TYPE_CLASS("");

  String name;

  private TypeClassEnum(String name) {
    this.name = name;
  }

  // assume uom names are unique; may not be true for symbols.
  public static TypeClassEnum getTypeClassEnum(String name) {
    for (TypeClassEnum typeclass : EnumSet.allOf(TypeClassEnum.class))
      if (typeclass.name.equals(name))
        return typeclass;
    return INVALID_TYPE_CLASS;
  }

  public String getName() {
    return name;
  }
}
