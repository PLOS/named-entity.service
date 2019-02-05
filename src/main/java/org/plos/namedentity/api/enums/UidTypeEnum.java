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
package org.plos.namedentity.api.enums;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public enum UidTypeEnum {

  RINGGOLD("Ringgold"),
  ORCID("ORCID"),
  EDITORIAL_MANAGER("Editorial Manager"),
  SALESFORCE("Salesforce"),
  AMBRA("Ambra"),
  INVALID_UID_TYPE("");

  String name;

  private UidTypeEnum(String name) {
    this.name = name;
  }

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
    individualUidTypeNames.add(EDITORIAL_MANAGER.getName());
    individualUidTypeNames.add(ORCID.getName());
    individualUidTypeNames.add(SALESFORCE.getName());
    return individualUidTypeNames;
  }

  public String getName() {
    return name;
  }
}
