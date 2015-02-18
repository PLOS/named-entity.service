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
package org.plos.namedentity.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Globaltype;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.api.entity.Organization;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Typedescription;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.plos.namedentity.api.NedException.ErrorType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})
public class NedExceptionTest {

  @Autowired
  NamedEntityService namedEntityService;  // inject so can resolve type names to ids

  @Autowired
  CrudService crudService;

  @Autowired
  NamedEntityDBService nedDBSvc;

  @Test
  public void testInvalidTypeClassException() {

    Integer     typeClassID      = null;
    Set<String> validTypeClasses = null;

    try {
      typeClassID = nedDBSvc.findTypeClass("Named Party Typos");
      fail();
    } catch (NedValidationException nve) {  // expected
      assertEquals(ErrorType.InvalidTypeClass, nve.getErrorType());

      validTypeClasses = nve.getAcceptableValues();
    }

    assertTrue(validTypeClasses.size() > 1);

    for (String typeclass : validTypeClasses) {
      // finder will thrown an exception if class not found
      assertTrue( nedDBSvc.findTypeClass(typeclass) > 0 );
    }
  }

  @Test
  public void testInvalidTypeValueException() {

    Integer     typeValueID     = null;
    Set<String> validTypeValues = null;
/*
    try {
      Integer typeClassID = nedDBSvc.findTypeClass("Named Party Types");
      fail();
    } catch (NedValidationException nve) {  // expected
      assertEquals(ErrorType.InvalidTypeClass, nve.getErrorType());

      validTypeValues = nve.getAcceptableValues();
    }
*/
  }

  //Integer findTypeValue(Integer typeClassId, String name);
}