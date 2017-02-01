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
package org.plos.namedentity.api.enums;

import java.util.EnumSet;

public enum TypeClassEnum {

  COMMUNICATION_METHODS("Communication Methods"),
  COUNTRY_CODES("Country Types"),
  COUNTRY_CODE_FOR_PHONE("Country Codes for Phone Numbers"),
  DEGREES("Degrees"),
  EMAIL_TYPES("Email Address Types"),
  GROUPS("Groups"),
  JOURNAL_TYPES("Journal Types"),
  LANGUAGES("Languages"),
  NAMED_ENTITY_PREFIXES("Named Party Prefixes"),
  NAMED_ENTITY_SUFFIXES("Named Party Suffixes"),
  NAMED_ENTITY_TYPES("Named Party Types"),
  ORGANIZATION_TYPES("Organization Types"),
  PHYSICAL_ADDRESS_TYPES("Physical Address Types"),
  RELATIONSHIP_TYPES("Relationship Types"),
  SOURCE_APPLICATIONS("Source Applications"),
  STATES_AND_PROVINCES("State and Province Codes"),
  SUBJECT_AREAS("Subject Areas"),
  TELEPHONE_TYPES("Telephone Number Types"),
  UID_INDIVIDUAL_TYPES("UID Individual Types"),
  UID_ORGANIZATION_TYPES("UID Organization Types"),
  USER_APPLICATIONS("User Applications"),
  ALERT_TYPES("Alert Types"),
  ALERT_FREQUENCY("Alert Frequency"),
  INVALID_TYPE_CLASS("");

  String name;

  private TypeClassEnum(String name) {
    this.name = name;
  }

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
