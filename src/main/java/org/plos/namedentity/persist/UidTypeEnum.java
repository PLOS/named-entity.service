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
import java.util.HashSet;
import java.util.Set;

public enum UidTypeEnum {

  RINGGOLD("Ringgold"),
  ORCID("ORCID"),
  EDITORIAL_MANAGER("Editorial Manager"),
  CAS("CAS"),
  SALESFORCE("Salesforce"),
  AMBRA("Ambra"),
  INVALID_UID_TYPE("");

  String name;

  private UidTypeEnum(String name) {
    this.name = name;
  }

  // assume uom names are unique; may not be true for symbols.
  public static UidTypeEnum getUidTypeEnum(String name) {
    for (UidTypeEnum uidtype : EnumSet.allOf(UidTypeEnum.class))
      if (uidtype.name.equals(name))
        return uidtype;
    return INVALID_UID_TYPE;
  }

  public static Set<String> getOrganizationUidTypeNames() {
    Set<String> organizationUidTypeNames = new HashSet<>();
    organizationUidTypeNames.add(RINGGOLD.getName());
    return organizationUidTypeNames;
  }

  public static Set<String> getIndividualUidTypeNames() {
    Set<String> individualUidTypeNames = new HashSet<>();
    individualUidTypeNames.add(AMBRA.getName());
    individualUidTypeNames.add(CAS.getName());
    individualUidTypeNames.add(EDITORIAL_MANAGER.getName());
    individualUidTypeNames.add(ORCID.getName());
    individualUidTypeNames.add(SALESFORCE.getName());
    return individualUidTypeNames;
  }

  public String getName() {
    return name;
  }
}
