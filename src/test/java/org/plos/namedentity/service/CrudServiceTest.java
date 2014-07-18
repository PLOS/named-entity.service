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
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;
import org.plos.namedentity.api.entity.UniqueidentifierEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml"})
public class CrudServiceTest {

  @Autowired
  CrudService crudService;

  @Test
  public void testTypeDescriptionCRUD() {

    // CREATE

    TypedescriptionEntity newType = new TypedescriptionEntity();
    newType.setDescription("description");
    newType.setHowused("howused");

    Integer pkId = crudService.create(newType);
    assertNotNull( pkId );
    
    TypedescriptionEntity savedType = crudService.findById(pkId, TypedescriptionEntity.class);
    assertNotNull(savedType);
    assertEquals(pkId, savedType.getTypeid());

    // UPDATE

    savedType.setDescription("description2");
    assertTrue(crudService.update(savedType));
    TypedescriptionEntity savedType2 = crudService.findById(pkId, TypedescriptionEntity.class);
    assertEquals(savedType, savedType2);

    // DELETE - delete type class without any children

    assertTrue(crudService.delete(savedType));

    // DELETE - deleting type class with children will raise a fk constraint
    //          exception.

    try {
      TypedescriptionEntity typeClassWithKids = new TypedescriptionEntity();
      typeClassWithKids.setTypeid(1);
      crudService.delete(typeClassWithKids);
      fail();
    } catch (org.springframework.dao.DataAccessException expected) {
    }

    // FIND By Id
    TypedescriptionEntity typeClass1 = crudService.findById(1, TypedescriptionEntity.class);
    assertNotNull(typeClass1);
    assertEquals(Integer.valueOf(1), typeClass1.getTypeid());

    // FIND All
    List<TypedescriptionEntity> typeClasses = crudService.findAll(TypedescriptionEntity.class);
    assertNotNull(typeClasses);
    assertTrue(typeClasses.contains(typeClass1));
  }

  @Test
  public void testGlobalTypesCRUD() {

    // CREATE

    GlobaltypeEntity newTypeVal = new GlobaltypeEntity();
    newTypeVal.setTypeid(1);
    newTypeVal.setShortdescription("type value abc");
    newTypeVal.setLongdescription("longdescription");
    newTypeVal.setTypecode("abc");

    Integer pkId = crudService.create(newTypeVal);
    assertNotNull( pkId );
    assertNotNull(newTypeVal.getCreated());
    assertNotNull(newTypeVal.getLastmodified());
    
    GlobaltypeEntity savedTypeVal = crudService.findById(pkId, GlobaltypeEntity.class);
    assertNotNull( savedTypeVal );
    assertEquals(pkId, savedTypeVal.getGlobaltypeid());

    // UPDATE

    savedTypeVal.setShortdescription("abc2");
    assertTrue( crudService.update(savedTypeVal) );
    GlobaltypeEntity savedTypeVal2 = crudService.findById(pkId, GlobaltypeEntity.class);
    assertEquals(savedTypeVal, savedTypeVal2);

    // DELETE - delete type class without any children

    assertTrue( crudService.delete(savedTypeVal) );

    //TODO - try to delete a global type with foreign key references.

    // FIND By Id

    GlobaltypeEntity typeVal1 = crudService.findById(1, GlobaltypeEntity.class);
    assertNotNull(typeVal1);
    assertEquals(Integer.valueOf(1), typeVal1.getGlobaltypeid());

    // FIND All

    List<GlobaltypeEntity> globalTypes = crudService.findAll(GlobaltypeEntity.class);
    assertNotNull(globalTypes);
    assertTrue(globalTypes.contains(typeVal1));

    // FIND BY ATTRIBUTE(S)

    GlobaltypeEntity globalTypesearchCriteria = new GlobaltypeEntity();
    globalTypesearchCriteria.setTypeid(1);

    List<GlobaltypeEntity> globalTypesForTypeClass = crudService.findByAttribute(globalTypesearchCriteria);
    assertNotNull(globalTypesForTypeClass);
    for (GlobaltypeEntity gtype : globalTypesForTypeClass) {
      assertTrue(globalTypes.contains(gtype));
    }
  }

  @Test
  public void testEmailsCRUD() {

    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    // lookup id for "work" email type

    GlobaltypeEntity globalTypesearchCriteria = new GlobaltypeEntity();
    globalTypesearchCriteria.setTypeid(10);
    globalTypesearchCriteria.setShortdescription("Work");
    List<GlobaltypeEntity> globalTypesResult = crudService.findByAttribute(globalTypesearchCriteria);
    assertEquals(1, globalTypesResult.size());

    Integer emailTypeId = globalTypesResult.get(0).getGlobaltypeid(); 
    assertNotNull( emailTypeId );

    // create pojo

    EmailEntity newEmail = new EmailEntity();
    newEmail.setNamedentityid(1);
    newEmail.setEmailtypeid(emailTypeId);
    newEmail.setEmailaddress("walter@foo.com");

    // save record

    Integer pkId = crudService.create(newEmail);
    assertNotNull( pkId );

    EmailEntity savedEmail = crudService.findById(pkId, EmailEntity.class);
    assertNotNull( savedEmail );
    assertEquals(pkId, savedEmail.getEmailid());
    assertEquals(emailTypeId, savedEmail.getEmailtypeid());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    savedEmail.setEmailaddress("super" + savedEmail.getEmailaddress());
    assertTrue( crudService.update(savedEmail) );
    EmailEntity savedEmail2 = crudService.findById(pkId, EmailEntity.class);
    assertEquals(savedEmail, savedEmail2);

    /* ------------------------------------------------------------------ */
    /*  FINDERS                                                           */
    /* ------------------------------------------------------------------ */

    // FIND All

    List<EmailEntity> allEmails = crudService.findAll(EmailEntity.class);
    assertNotNull(allEmails);
    assertTrue(allEmails.contains(savedEmail2));

    // FIND BY ATTRIBUTE(S)

    EmailEntity emailSearchCriteria = new EmailEntity();
    emailSearchCriteria.setEmailaddress(savedEmail2.getEmailaddress());

    List<EmailEntity> foundEmails = crudService.findByAttribute(emailSearchCriteria);
    assertNotNull(foundEmails);
    assertEquals(savedEmail2.getEmailaddress(), foundEmails.get(0).getEmailaddress());

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    assertTrue( crudService.delete(savedEmail) );
  }

  @Test
  public void testExternalReferencesCRUD() {

    final String ORCID_ID1 = "0000-0001-9430-319X";

    // lookup id for orcid (using hardcoded type class 17 :))

    GlobaltypeEntity globalTypesearchCriteria = new GlobaltypeEntity();
    globalTypesearchCriteria.setTypeid(17);
    globalTypesearchCriteria.setShortdescription("ORCID");
    List<GlobaltypeEntity> globalTypesResult = crudService.findByAttribute(globalTypesearchCriteria);
    assertEquals(1, globalTypesResult.size());

    Integer orcidTypeId = globalTypesResult.get(0).getGlobaltypeid(); 
    assertNotNull( orcidTypeId );
    
    /* ------------------------------------------------------------------ */
    /*  CREATE                                                            */
    /* ------------------------------------------------------------------ */

    UniqueidentifierEntity uidEntity = new UniqueidentifierEntity();
    uidEntity.setNamedentityid(1);
    uidEntity.setUniqueidentifiertypeid(orcidTypeId);
    uidEntity.setUniqueidentifier(ORCID_ID1);

    // save record

    Integer pkId = crudService.create(uidEntity);
    assertNotNull( pkId );

    UniqueidentifierEntity savedUid = crudService.findById(pkId, UniqueidentifierEntity.class);
    assertNotNull( savedUid );
    assertEquals(pkId, savedUid.getUniqueidentifiersid());
    assertEquals(orcidTypeId, savedUid.getUniqueidentifiertypeid());

    /* ------------------------------------------------------------------ */
    /*  UPDATE                                                            */
    /* ------------------------------------------------------------------ */

    savedUid.setUniqueidentifier( savedUid.getUniqueidentifier() + "Z" );
    assertTrue( crudService.update(savedUid) );

    UniqueidentifierEntity savedUid2 = crudService.findById(pkId, UniqueidentifierEntity.class);
    assertEquals(savedUid, savedUid2);

    /* ------------------------------------------------------------------ */
    /*  FINDERS                                                           */
    /* ------------------------------------------------------------------ */

    // FIND All

    List<UniqueidentifierEntity> allUids = crudService.findAll(UniqueidentifierEntity.class);
    assertNotNull(allUids);
    assertTrue(allUids.contains(savedUid2));

    // TODO - FIND BY ATTRIBUTE(S)

    /* ------------------------------------------------------------------ */
    /*  DELETE                                                            */
    /* ------------------------------------------------------------------ */

    assertTrue( crudService.delete(savedUid) );
  }
}
