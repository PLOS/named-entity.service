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

public enum RelationshipTypeEnum {

  I_AFF_O("Individual Affiliated with Organization"),
  INVALID_RELATIONSHIP_TYPE("");

  String name;

  private RelationshipTypeEnum(String name) {
    this.name = name;
  }

  public static RelationshipTypeEnum getRelationshipTypeEnum(String name) {
    for (RelationshipTypeEnum reltype : EnumSet.allOf(RelationshipTypeEnum.class))
      if (reltype.name.equalsIgnoreCase(name))
        return reltype;
    return INVALID_RELATIONSHIP_TYPE;
  }

  public String getName() {
    return name;
  }
}
