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

import static org.junit.Assert.fail;
import static org.plos.namedentity.persist.UidTypeEnum.*;

import org.plos.namedentity.api.NedValidationException;

import org.junit.Test;

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
      } catch (NedValidationException expected) { }
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
