package org.plos.namedentity.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.dto.RoleDTO;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml"})
public class NamedEntityServiceHighApiTest {

    @Autowired NamedEntityServiceHighApi nedSvcHighApi;
    @Autowired NamedEntityService        nedSvcLowApi;

	@Test
    public void testCreateIndividualWithoutRole() {
		// triggers phase 1 validation failure
        try {
            IndividualEntity entity = nedSvcHighApi.createIndividual(new IndividualComposite());
            fail();
        }
        catch (NedValidationException expected) {
			assertTrue(expected.getMessage().indexOf("No ROLE defined") != -1);
		}
    }

	@Test
    public void testCreateIndividualWithRole() {

		IndividualComposite composite = getIndividualWithRole();

        List<EmailDTO> emails = new ArrayList<>();

		EmailDTO workEmail = new EmailDTO();
		workEmail.setEmailtype("Work");
		workEmail.setEmailaddress("fu.manchu.work@foo.com");
		workEmail.setIsprimary(true);
		emails.add( workEmail );

		EmailDTO personalEmail = new EmailDTO();
		personalEmail.setEmailtype("Personal");
		personalEmail.setEmailaddress("fu.manchu.home@foo.com");
		personalEmail.setIsprimary(true);
		emails.add( personalEmail );

        composite.setEmails( emails );

		try {
			IndividualEntity entity = nedSvcHighApi.createIndividual(composite);
			assertNotNull(entity);
			assertNotNull(entity.getNamedentityid());
		}
		catch (NedValidationException e) {
			fail();
		}
		finally {
			EmailEntity emailSearchCriteria = new EmailEntity();
			emailSearchCriteria.setEmailaddress("fu.manchu.work@foo.com");
			List<EmailEntity> emailSearchResult = nedSvcLowApi.findByAttribute(emailSearchCriteria);
			assertEquals(1, emailSearchResult.size());
		}
    }

	@Test
    public void testCreateIndividualWithPhaseTwoValidationException() {

		IndividualComposite composite = getIndividualWithRole();

        List<EmailDTO> emails = new ArrayList<>();

		EmailDTO workEmail = new EmailDTO();
		workEmail.setEmailtype("Work");
		workEmail.setEmailaddress("foo@bar.com");
		workEmail.setIsprimary(true);
		emails.add( workEmail );

        composite.setEmails( emails );

		try {
			IndividualEntity entity = nedSvcHighApi.createIndividual(composite);
			fail();
		}
		catch (NedValidationException expected) {
		}
		// verify entities not committed to db. we'll just check email.
		finally {
			EmailEntity emailSearchCriteria = new EmailEntity();
			emailSearchCriteria.setEmailaddress("foo@bar.com");
			List<EmailEntity> emailSearchResult = nedSvcLowApi.findByAttribute(emailSearchCriteria);
			assertEquals(0, emailSearchResult.size());
		}
    }

	private IndividualComposite getIndividualWithRole() {

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

		return composite;
	}

    //TODO - add tests with address, email, and phone combinations
}
