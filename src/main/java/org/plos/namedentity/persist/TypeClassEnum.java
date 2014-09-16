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
package org.plos.namedentity.persist;

import java.util.EnumSet;

public enum TypeClassEnum {

  COMMUNICATION_METHODS("Communication Methods"),
  COUNTRY_CODES("Country Types"),
  COUNTRY_CODE_FOR_PHONE("Country Codes for Phone Numbers"),
  EMAIL_TYPES("Email Address Types"),
  NAMED_ENTITY_TYPES("Named Party Types"),
  NAMED_ENTITY_PREFIX("Named Party Prefixes"),
  PHYSICAL_ADDRESS_TYPES("Physical Address Types"),
  ROLES("Roles"),
  USER_APPLICATIONS("User Applications"),
  STATES_AND_PROVINCES("State and Province Codes"),
  TELEPHONE_TYPES("Telephone Number Types"),
  UNIQUE_IDENTIFIERS("Unique Identifier Types"),
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
