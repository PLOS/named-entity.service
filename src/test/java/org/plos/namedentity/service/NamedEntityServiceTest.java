package org.plos.namedentity.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.TypedescriptionsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml"})
public class NamedEntityServiceTest {

    @Autowired NamedEntityService nedSvc;

    @Test
    public void testTypeDescriptionCRUD() {

        // CREATE

        TypedescriptionsDTO newType = new TypedescriptionsDTO();
        newType.setDescription("description");
        newType.setHowused("howused");

        TypedescriptionsDTO savedType = nedSvc.create(newType);
        assertNotNull( savedType );
        assertNotNull( savedType.getTypeid() );

        // UPDATE

        savedType.setDescription("description2");
        TypedescriptionsDTO savedType2 = nedSvc.update(savedType);
        assertEquals(savedType, savedType2);

        // DELETE - delete type class without any children

        assertTrue( nedSvc.delete(savedType) );

        // DELETE - deleting type class with children will raise a fk constraint
        //          exception.

        try {
            TypedescriptionsDTO typeClassWithKids = new TypedescriptionsDTO();
            typeClassWithKids.setTypeid(1);
            nedSvc.delete(typeClassWithKids);
            fail();
        } catch (org.springframework.dao.DataAccessException expected) {
        }

        // FIND By Id
        TypedescriptionsDTO typeClass1 = nedSvc.findTypedescriptionById(1);
        assertNotNull(typeClass1);
        assertEquals(Integer.valueOf(1), typeClass1.getTypeid());

        // FIND All
        Collection<TypedescriptionsDTO> typeClasses = nedSvc.getTypedescriptions();
        assertNotNull(typeClasses);
        assertTrue(typeClasses.contains(typeClass1));
    }
}
