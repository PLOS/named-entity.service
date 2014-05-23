package org.plos.namedentity.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.GlobaltypesDTO;
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

        Integer pkId = nedSvc.create(newType);
        assertNotNull( pkId );
        
        TypedescriptionsDTO savedType = nedSvc.findById(pkId, TypedescriptionsDTO.class);
        assertNotNull( savedType );
        assertEquals(pkId, savedType.getTypeid());

        // UPDATE

        savedType.setDescription("description2");
        assertTrue( nedSvc.update(savedType) );
        TypedescriptionsDTO savedType2 = nedSvc.findById(pkId, TypedescriptionsDTO.class);
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
        TypedescriptionsDTO typeClass1 = nedSvc.findById(1, TypedescriptionsDTO.class);
        assertNotNull(typeClass1);
        assertEquals(Integer.valueOf(1), typeClass1.getTypeid());

        // FIND All
        Collection<TypedescriptionsDTO> typeClasses = nedSvc.findAll(TypedescriptionsDTO.class);
        assertNotNull(typeClasses);
        assertTrue(typeClasses.contains(typeClass1));
    }

	@Test
    public void testGlobalTypesCRUD() {

        // CREATE

        GlobaltypesDTO newTypeVal = new GlobaltypesDTO();
        newTypeVal.setTypeid(
            nedSvc.findById(1, TypedescriptionsDTO.class).getTypeid());
        newTypeVal.setShortdescription("type value abc");
        newTypeVal.setLongdescription("longdescription");
        newTypeVal.setTypecode("abc");

        Integer pkId = nedSvc.create(newTypeVal);
        assertNotNull( pkId );
        assertNotNull(newTypeVal.getCreated());
        assertNotNull(newTypeVal.getLastmodified());
        
        GlobaltypesDTO savedTypeVal = nedSvc.findById(pkId, GlobaltypesDTO.class);
        assertNotNull( savedTypeVal );
        assertEquals(pkId, savedTypeVal.getGlobaltypeid());

        // UPDATE

        savedTypeVal.setShortdescription("abc2");
        assertTrue( nedSvc.update(savedTypeVal) );
        GlobaltypesDTO savedTypeVal2 = nedSvc.findById(pkId, GlobaltypesDTO.class);
        assertEquals(savedTypeVal, savedTypeVal2);

        // DELETE - delete type class without any children

        assertTrue( nedSvc.delete(savedTypeVal) );

        //TODO - try to delete a global type with foreign key references.

        // FIND By Id

        GlobaltypesDTO typeVal1 = nedSvc.findById(1, GlobaltypesDTO.class);
        assertNotNull(typeVal1);
        assertEquals(Integer.valueOf(1), typeVal1.getGlobaltypeid());

        // FIND All

        Collection<GlobaltypesDTO> globalTypes = nedSvc.findAll(GlobaltypesDTO.class);
        assertNotNull(globalTypes);
        assertTrue(globalTypes.contains(typeVal1));

        // FIND BY ATTRIBUTE(S)

        GlobaltypesDTO searchCriteriaDTO = new GlobaltypesDTO();
        searchCriteriaDTO.setTypeid(1);

        Collection<GlobaltypesDTO> globalTypesForTypeClass = nedSvc.findByAttribute(searchCriteriaDTO);
        assertNotNull(globalTypesForTypeClass);
        for (GlobaltypesDTO gtype : globalTypesForTypeClass) {
            assertTrue(globalTypes.contains(gtype));
        }
    }
}
