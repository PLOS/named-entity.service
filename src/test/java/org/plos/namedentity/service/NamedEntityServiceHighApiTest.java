package org.plos.namedentity.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.dto.RoleDTO;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml"})
public class NamedEntityServiceHighApiTest {

    @Autowired NamedEntityServiceHighApi nedSvcHighApi;

	@Test
    public void testCreateIndividualWithoutRole() {
        try {
            IndividualEntity entity = nedSvcHighApi.createIndividual(new IndividualComposite());
            fail();
        }
        catch (NedValidationException expected) { }
    }

	@Test
    public void testCreateIndividualWithRole() {

        IndividualComposite composite = new IndividualComposite();
        composite.setFirstname("firstname");
        composite.setMiddlename("middlename");
        composite.setLastname("lastname");
        composite.setNameprefix("Mr.");
        composite.setNamesuffix("III");
        composite.setPreferredlanguage("English");
        composite.setPreferredcommunication("Email");

        RoleDTO author = new RoleDTO();
        author.setRoletype("Author");
        author.setStartdate("2014-05-30");
        composite.setRole(author);

        IndividualEntity entity = nedSvcHighApi.createIndividual(composite);
        assertNotNull(entity);
        assertNotNull(entity.getNamedentityid());
    }

    //TODO - add tests with address, email, and phone combinations
}
