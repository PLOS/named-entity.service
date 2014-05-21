package org.plos.namedentity.persist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.plos.namedentity.persist.db.namedentities.Tables.GLOBALTYPES;
import static org.plos.namedentity.persist.db.namedentities.Tables.TYPEDESCRIPTIONS;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.plos.namedentity.api.JournalsDTO;
import org.plos.namedentity.api.TypedescriptionsDTO;
import org.plos.namedentity.persist.db.namedentities.tables.Globaltypes;
import org.plos.namedentity.persist.db.namedentities.tables.Typedescriptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml"})
public class NamedEntityDBServiceTest {

    @Autowired NamedEntityDBService         nedDBSvc;
    @Autowired DSLContext                   context;
    @Autowired DataSourceTransactionManager txMgr;

    @Test
    public void testTypeClassesAndTypeValues() {
        Globaltypes v = GLOBALTYPES.as("v");
        Typedescriptions t = TYPEDESCRIPTIONS.as("t"); 

        Result<Record2<String,String>> result = context
            .select(t.DESCRIPTION, v.SHORTDESCRIPTION)
            .from(t)
            .join(v).on(v.TYPEID.equal(t.TYPEID))
            .fetch();

        assertTrue(result.size() >= 76);
    }

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

    @Test
    public void testNamedEntityIdGeneration() throws Exception {

        Integer nedId1 = nedDBSvc.newNamedEntityId("Individual");
        Integer nedId2 = nedDBSvc.newNamedEntityId("Individual");
        assertTrue(nedId1.intValue() > 0);
        assertEquals(nedId2.intValue(), nedId1.intValue()+1);

        try {
            nedDBSvc.newNamedEntityId("BogusEntityType");
            fail();
        }
        catch (NullPointerException e) {
            // TODO: NamedEntityDBService should throw a uniforum runtime
            //       exception (capturing this exception as cause)
        }
    }

    @Test
    public void testFindAllOnEmptyTable() {
        List<JournalsDTO> journals = nedDBSvc.findAll(JournalsDTO.class);
        assertNotNull(journals);
        assertEquals(0, journals.size());
    }

	@Test
    public void testTypedescriptionCRUD() {

        // CREATE
        Integer newTypeClassId = nedDBSvc.create( new TypedescriptionsDTO(null, "New Type Class", "Yada yada") );
        assertNotNull(newTypeClassId);

        // UPDATE description.
        TypedescriptionsDTO dto = nedDBSvc.findById(newTypeClassId, TypedescriptionsDTO.class);
        dto.setDescription( dto.getDescription() + "2");
        assertTrue( nedDBSvc.update(dto) );

        // Get another instance of same type class
        TypedescriptionsDTO dto2 = nedDBSvc.findById(newTypeClassId, TypedescriptionsDTO.class);
        assertEquals(dto, dto2);

        // Find all type classes
        List<TypedescriptionsDTO> typeClasses = nedDBSvc.findAll(TypedescriptionsDTO.class);
        assertTrue(typeClasses.size() >= 20);

        // Try to find a type class which doesn't exist
        TypedescriptionsDTO dto3 = nedDBSvc.findById(666, TypedescriptionsDTO.class);
        assertNull(dto3);

        // DELETE. we should be able to delete newly added type class because
        // it doesn't have any values associated with it (ie, has no globaltypes).

        TypedescriptionsDTO typeDescription = new TypedescriptionsDTO();
        typeDescription.setTypeid(newTypeClassId);

        assertTrue( nedDBSvc.delete(typeDescription) );

        // However, trying to delete a type class with type values should 
        // raise a foreign key constraint exception.

        try {
            typeDescription.setTypeid(1);
            nedDBSvc.delete(typeDescription);
            fail();
        }
        catch (DataAccessException ignore) {
            // declarative transaction will rollback changes on exception
        }
    }
}
