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

import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.ringgold.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.plos.namedentity.api.NedException.ErrorType.ServerError;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})
public class RinggoldDBServiceTest {

  @Autowired RinggoldDBService ringgoldDBSvc;
  @Autowired DSLContext        context;

  @Test
  public void testRinggoldFinders() {
    // Find all type classes
    List<Institution> institutions = ringgoldDBSvc.findAll(Institution.class, 0, Integer.MAX_VALUE);
    assertTrue(institutions.size() > 0);

for (Institution i : institutions) {
  System.out.println(i);
}

/*
    // CREATE
    Typedescription typedescription = new Typedescription();
    typedescription.setDescription("New Type Class");
    typedescription.setHowused("Yada yada");

    Integer newTypeClassId = ringgoldDBSvc.create( typedescription );
    assertNotNull(newTypeClassId);

    // UPDATE description.
    Typedescription entity = ringgoldDBSvc.findById(newTypeClassId, Typedescription.class);
    entity.setDescription( entity.getDescription() + "2");
    assertTrue( ringgoldDBSvc.update(entity) );

    // Get another instance of same type class
    Typedescription entity2 = ringgoldDBSvc.findById(newTypeClassId, Typedescription.class);
    assertEquals(entity, entity2);

    // Find all type classes
    List<Typedescription> typeClasses = ringgoldDBSvc.findAll(Typedescription.class, 0, Integer.MAX_VALUE);
    assertTrue(typeClasses.size() >= 20);

    // Try to find a type class which doesn't exist
    Typedescription entity3 = ringgoldDBSvc.findById(666, Typedescription.class);
    assertNull(entity3);

    // DELETE. we should be able to delete newly added type class because
    // it doesn't have any values associated with it (ie, has no globaltypes).

    Typedescription typeDescription = new Typedescription();
    typeDescription.setId(newTypeClassId);

    assertTrue( ringgoldDBSvc.delete(typeDescription) );

    // However, trying to delete a type class with type values should 
    // raise a foreign key constraint exception.

    try {
      typeDescription.setId(1);
      ringgoldDBSvc.delete(typeDescription);
      fail();
    }
    catch (DataAccessException ignore) {
      // declarative transaction will rollback changes on exception
    }
*/
  }
}
