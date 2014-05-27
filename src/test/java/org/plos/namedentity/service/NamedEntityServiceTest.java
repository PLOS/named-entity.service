package org.plos.namedentity.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml"})
public class NamedEntityServiceTest {

    @Autowired NamedEntityService nedSvc;
/*
    @Test
    public void testExplicitTransactions() {
        boolean rollback = false;

        TransactionStatus tx = txMgr.getTransaction(new DefaultTransactionDefinition());
        try {
            // This should raise a unique constraint violation exception 
            context.insertInto(TYPEDESCRIPTIONS)
                   .set(TYPEDESCRIPTIONS.TYPEID, 1)
                   .set(TYPEDESCRIPTIONS.DESCRIPTION, "Type Class (dupe)")
                   .execute();

            Assert.fail();
        }
        // catch constraint and roll back transaction
        catch (DataAccessException e) {
            txMgr.rollback(tx);
            rollback = true;
        }

        assertTrue(rollback);
    }
*/
	@Test
    public void testTypeDescriptionCRUD() {

        // CREATE

        TypedescriptionEntity newType = new TypedescriptionEntity();
        newType.setDescription("description");
        newType.setHowused("howused");

        Integer pkId = nedSvc.create(newType);
        assertNotNull( pkId );
        
        TypedescriptionEntity savedType = nedSvc.findById(pkId, TypedescriptionEntity.class);
        assertNotNull( savedType );
        assertEquals(pkId, savedType.getTypeid());

        // UPDATE

        savedType.setDescription("description2");
        assertTrue( nedSvc.update(savedType) );
        TypedescriptionEntity savedType2 = nedSvc.findById(pkId, TypedescriptionEntity.class);
        assertEquals(savedType, savedType2);

        // DELETE - delete type class without any children

        assertTrue( nedSvc.delete(savedType) );

        // DELETE - deleting type class with children will raise a fk constraint
        //          exception.

        try {
            TypedescriptionEntity typeClassWithKids = new TypedescriptionEntity();
            typeClassWithKids.setTypeid(1);
            nedSvc.delete(typeClassWithKids);
            fail();
        } catch (org.springframework.dao.DataAccessException expected) {
        }

        // FIND By Id
        TypedescriptionEntity typeClass1 = nedSvc.findById(1, TypedescriptionEntity.class);
        assertNotNull(typeClass1);
        assertEquals(Integer.valueOf(1), typeClass1.getTypeid());

        // FIND All
        List<TypedescriptionEntity> typeClasses = nedSvc.findAll(TypedescriptionEntity.class);
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

        Integer pkId = nedSvc.create(newTypeVal);
        assertNotNull( pkId );
        assertNotNull(newTypeVal.getCreated());
        assertNotNull(newTypeVal.getLastmodified());
        
        GlobaltypeEntity savedTypeVal = nedSvc.findById(pkId, GlobaltypeEntity.class);
        assertNotNull( savedTypeVal );
        assertEquals(pkId, savedTypeVal.getGlobaltypeid());

        // UPDATE

        savedTypeVal.setShortdescription("abc2");
        assertTrue( nedSvc.update(savedTypeVal) );
        GlobaltypeEntity savedTypeVal2 = nedSvc.findById(pkId, GlobaltypeEntity.class);
        assertEquals(savedTypeVal, savedTypeVal2);

        // DELETE - delete type class without any children

        assertTrue( nedSvc.delete(savedTypeVal) );

        //TODO - try to delete a global type with foreign key references.

        // FIND By Id

        GlobaltypeEntity typeVal1 = nedSvc.findById(1, GlobaltypeEntity.class);
        assertNotNull(typeVal1);
        assertEquals(Integer.valueOf(1), typeVal1.getGlobaltypeid());

        // FIND All

        List<GlobaltypeEntity> globalTypes = nedSvc.findAll(GlobaltypeEntity.class);
        assertNotNull(globalTypes);
        assertTrue(globalTypes.contains(typeVal1));

        // FIND BY ATTRIBUTE(S)

        GlobaltypeEntity globalTypesearchCriteria = new GlobaltypeEntity();
        globalTypesearchCriteria.setTypeid(1);

        List<GlobaltypeEntity> globalTypesForTypeClass = nedSvc.findByAttribute(globalTypesearchCriteria);
        assertNotNull(globalTypesForTypeClass);
        for (GlobaltypeEntity gtype : globalTypesForTypeClass) {
            assertTrue(globalTypes.contains(gtype));
        }
    }

	//@Test
    public void testEmailsCRUD() {

        /* ------------------------------------------------------------------ */
        /*  CREATE                                                            */
        /* ------------------------------------------------------------------ */

        // lookup id for "work" email type

        GlobaltypeEntity globalTypesearchCriteria = new GlobaltypeEntity();
        globalTypesearchCriteria.setTypeid(10);
        globalTypesearchCriteria.setShortdescription("Work");
		List<GlobaltypeEntity> globalTypesResult = nedSvc.findByAttribute(globalTypesearchCriteria);
		assertEquals(1, globalTypesResult.size());

		Integer emailTypeId = globalTypesResult.get(0).getGlobaltypeid(); 
		assertNotNull( emailTypeId );

        // create pojo

		EmailEntity newEmail = new EmailEntity();
		newEmail.setNamedentityid(1);
		newEmail.setEmailtypeid(emailTypeId);
		newEmail.setEmailaddress("walter@foo.com");

        // save record

		Integer pkId = nedSvc.create(newEmail);
		assertNotNull( pkId );

		EmailEntity savedEmail = nedSvc.findById(pkId, EmailEntity.class);
		assertNotNull( savedEmail );
		assertEquals(pkId, savedEmail.getEmailid());
		assertEquals(emailTypeId, savedEmail.getEmailtypeid());

        /* ------------------------------------------------------------------ */
        /*  UPDATE                                                            */
        /* ------------------------------------------------------------------ */

        savedEmail.setEmailaddress("super" + savedEmail.getEmailaddress());
        assertTrue( nedSvc.update(savedEmail) );
        EmailEntity savedEmail2 = nedSvc.findById(pkId, EmailEntity.class);
        assertEquals(savedEmail, savedEmail2);

        /* ------------------------------------------------------------------ */
        /*  FINDERS                                                           */
        /* ------------------------------------------------------------------ */

        // FIND By Id

        EmailEntity email1 = nedSvc.findById(1, EmailEntity.class);
        assertNotNull(email1);
        assertEquals(Integer.valueOf(1), email1.getEmailid());

        // FIND All

        List<EmailEntity> allEmails = nedSvc.findAll(EmailEntity.class);
        assertNotNull(allEmails);
        assertTrue(allEmails.contains(email1));

        // FIND BY ATTRIBUTE(S)

        EmailEntity emailSearchCriteria = new EmailEntity();
        emailSearchCriteria.setEmailaddress(email1.getEmailaddress());

        List<EmailEntity> foundEmails = nedSvc.findByAttribute(emailSearchCriteria);
        assertNotNull(foundEmails);
        assertEquals(email1.getEmailaddress(), foundEmails.get(0).getEmailaddress());

        /* ------------------------------------------------------------------ */
        /*  DELETE                                                            */
        /* ------------------------------------------------------------------ */

        assertTrue( nedSvc.delete(savedEmail) );
    }
}
