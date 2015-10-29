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

public enum NamedPartyEnum {

  INDIVIDUAL("Individual"),
  ORGANIZATION("Organization"),
  INVALID_NAMEDPARTY_TYPE("");

  String name;

  private NamedPartyEnum(String name) {
    this.name = name;
  }

  public static NamedPartyEnum getNamedPartyEnum(String name) {
    for (NamedPartyEnum nptype : EnumSet.allOf(NamedPartyEnum.class))
      if (nptype.name.equals(name))
        return nptype;
    return INVALID_NAMEDPARTY_TYPE;
  }

  public String getName() {
    return name;
  }
}
