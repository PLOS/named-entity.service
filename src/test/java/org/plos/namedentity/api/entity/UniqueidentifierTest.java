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

import org.junit.Test;
import org.plos.namedentity.api.NedException;

import static org.junit.Assert.fail;
import static org.plos.namedentity.api.enums.UidTypeEnum.ORCID;
import static org.plos.namedentity.api.enums.UidTypeEnum.SALESFORCE;

public class UniqueidentifierTest {

  @Test
  public void testSalesforceIdValidation() {

    Uniqueidentifier[] badSalesforceIds = {
      uid(null,null),
      uid(SALESFORCE.getName(),null),
      uid("BadType",""),
      sfuid("12345"),
      sfuid("12345678901234@"),
      sfuid("!23456789012345678"),
    };

    for (Uniqueidentifier uid : badSalesforceIds) {
      try { 
        uid.validate();
        fail();
      } catch (NedException expected) { }
    }

    Uniqueidentifier[] okSalesforceIds = {
      sfuid("012345678912345"),
      sfuid("abcdefghijklmno"),
      sfuid("ABCDEFGHIJKLMNO"),
      sfuid("001U0000008Q9oV"),
      sfuid("001U0000008Qfva"),
      sfuid("001U0000008QTMX"),
      sfuid("001U0000008PbFk"),
      sfuid("001U0000008PI7V"),
    };

    for (Uniqueidentifier uid : okSalesforceIds) {
      uid.validate();
    }
  }

  @Test
  public void testOrcidValidation() {

    Uniqueidentifier[] badIds = {
        uid(null, null),
        uid(ORCID.getName(), null),
        uid("BadType",""),
        uid(ORCID.getName(), "0000-0001-9430-001"),
        uid(ORCID.getName(), "0000-0001-9430-!@12"),
        uid(ORCID.getName(), "0000-0001-9430-001a"),
        uid(ORCID.getName(), "0000-X001-9430-001a")
    };

    Uniqueidentifier[] goodIds = {
        uid(ORCID.getName(), "0000-0001-9430-001X"),
        uid(ORCID.getName(), "0000-0001-9430-0012"),
        uid(ORCID.getName(), "000000019430001X"),
        uid(ORCID.getName(), "000000019430001x")
    };

    for (Uniqueidentifier uid : badIds) {
      try {
        uid.validate();
        fail();
      } catch (NedException expected) { }
    }

    for (Uniqueidentifier uid : goodIds) {
      uid.validate();
    }

  }

  private Uniqueidentifier sfuid(String uidValue) {
    return uid(SALESFORCE.getName(), uidValue);
  }

  private Uniqueidentifier uid(String type, String uidValue) {
    Uniqueidentifier uid = new Uniqueidentifier();
    uid.setType(type);
    uid.setUniqueidentifier(uidValue);
    return uid;
  }
}
