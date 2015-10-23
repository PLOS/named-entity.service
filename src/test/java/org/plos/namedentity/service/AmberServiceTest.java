package org.plos.namedentity.service;

import org.ambraproject.models.UserProfile;
import org.ambraproject.service.user.UserService;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Auth;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})
public class AmberServiceTest {

  @Autowired
  NamedEntityService namedEntityService;  // inject so can resolve type names to ids

  @Autowired
  NamedEntityDBService nedDBSvc;

  @Autowired
  CrudService crudService;

  @Autowired
  AmbraService ambraService;

  @Autowired
  private UserService userService;

  private static final String TEST_RESOURCE_PATH = "src/test/resources/";


  private <T> JAXBContext jsonJaxbContext(Class<T> clazz) throws JAXBException {
    Map<String, Object> properties = new HashMap<>(2);
    properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
    properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
    return JAXBContext.newInstance(new Class[]{clazz}, properties);
  }

  private <T> Unmarshaller jsonUnmarshaller(Class<T> clazz) throws JAXBException {
    JAXBContext jc = jsonJaxbContext(clazz);
    return jc.createUnmarshaller();
  }

  private <T> T unmarshalEntity(String json, Class<T> clazz, Unmarshaller unmarshaller)
      throws JAXBException {
    return unmarshaller.unmarshal(new StreamSource(new StringReader(json)), clazz).getValue();
  }

  private IndividualComposite loadComposite(String filename) throws Exception {

    Unmarshaller unmarshaller = jsonUnmarshaller(IndividualComposite.class);
    return unmarshalEntity(
        new String(Files.readAllBytes(
            Paths.get(TEST_RESOURCE_PATH + filename))),
        IndividualComposite.class, unmarshaller);
  }

  private IndividualComposite getNew() throws Exception {

    IndividualComposite composite = loadComposite("composite-individual.json");

    String rand = UUID.randomUUID().toString();

    Auth auth = new Auth();
    auth.setAuthid(rand);
    auth.setEmail(rand + "@test.test");
    composite.getAuth().add(auth);

    composite.getEmails().get(0).setEmailaddress(rand + "@test.test");
    composite.getIndividualprofiles().get(0).setDisplayname(rand);

    return composite;

  }

  private void assertEqual(IndividualComposite ned, UserProfile ambra) {

    assertEqual(ned.getIndividualprofiles().get(0), ambra);

    assertEqual(ned.getEmails().get(0), ambra);

    if (ned.getAddresses() != null)
      assertEqual(ned.getAddresses().get(0), ambra);

    if (ned.getUrls() != null)
      assertEquals(ned.getUrls().get(0).getUrl(), ambra.getHomePage());

  }

  private void assertEqual(Address ned, UserProfile ambra) {
    assertEquals(ned.getCity(), ambra.getCity());
    assertEquals(ned.getCountrycodetype(), ambra.getCountry());
    assertEquals(ned.getAddressline1() + "\n" +
        ned.getAddressline2() + "\n" +
        ned.getAddressline3(), ambra.getPostalAddress());
  }

  private void assertEqual(Email ned, UserProfile ambra) {
    assertEquals(ned.getEmailaddress(), ambra.getEmail());
  }

  private void assertEqual(Individualprofile ned, UserProfile ambra) {

    String realName = ned.getFirstname();

    if (realName == null)
      realName = ned.getLastname();
    else if (ned.getLastname() != null)
      realName += " " + ned.getLastname();

    assertEquals(ned.getDisplayname(), ambra.getDisplayName());
    assertEquals(ned.getFirstname(), ambra.getGivenNames());
    assertEquals(ned.getLastname(), ambra.getSurname());
    assertEquals(realName, ambra.getRealName());
    assertEquals(ned.getBiography(), ambra.getBiography());
    assertEquals(ned.getNameprefix(), ambra.getTitle());

  }

  @Test
  public void testCreateInAmbra() throws Exception {

    IndividualComposite composite = getNew();

    Long ambraId = ambraService.createUser(composite);

    assertTrue(ambraId > 0);

    UserProfile userProfile = userService.getUser(ambraId);

    assertEqual(composite, userProfile);

  }

  @Test
  public void testCreateInAmbraAndNed() throws Exception {

    IndividualComposite composite = getNew();

    int nedIdResponse = namedEntityService.createComposite(
        composite, IndividualComposite.class).getEmails().get(0).getNedid();

    IndividualComposite compositeFetched = namedEntityService.findComposite(
        nedIdResponse, IndividualComposite.class);

    UserProfile userProfile = userService.getUser(new Long(nedIdResponse));

    assertEqual(compositeFetched, userProfile);

  }


  @Test
  public void testUpdateInAmbra() throws Exception {

    IndividualComposite composite = getNew();

    Long ambraId = ambraService.createUser(composite);

    Email email = composite.getEmails().get(0);
    email.setEmailaddress("email2@foo.com");
    ambraService.update(email);

    Address address = composite.getAddresses().get(0);
    address.setCity("new city");
    ambraService.update(address);

    UserProfile userProfile = userService.getUser(ambraId);

    assertEqual(email, userProfile);
    assertEqual(address, userProfile);
  }

  @Test
  public void testUpdateRollbackNed() throws Exception {

    IndividualComposite composite = getNew();

    // invalid email should trigger NED validation error

    Email email = namedEntityService.createComposite(
        composite, IndividualComposite.class).getEmails().get(0);

    String origEmail = email.getEmailaddress();

    email.setEmailaddress("invalid@email");

//    setCreatedAndLastModifiedBy(authHeader,entity,false);
//    entity.setId(pkId);
//    entity.setNedid(nedId);
//    namedEntityService.resolveValuesToIds(entity);

    try {
      crudService.update(email);
      fail();
    } catch (NedException expected) {
    }

    UserProfile userProfile = userService.getUser(new Long(email.getNedid()));

    IndividualComposite compositeOut = namedEntityService.findComposite(
        email.getNedid(), IndividualComposite.class);

    assertEqual(compositeOut, userProfile);

    assertEquals(origEmail, userProfile.getEmail());
    assertEquals(composite.getEmails().get(0).getEmailaddress(),
        userProfile.getEmail());

  }

  @Test
  public void testUpdateRollbackAmbra() throws Exception {

    IndividualComposite composite = getNew();

    // TODO: allow NED to validate, but force Ambra to fail somehow

  }

  @Test
  public void testCreateRollbackNed() throws Exception {

    IndividualComposite composite = getNew();

    composite.getEmails().get(0).setEmailaddress("invalid2@email");

    String authId = composite.getAuth().get(0).getAuthid();

    try {
      namedEntityService.createComposite(composite, IndividualComposite.class);
      fail();
    } catch (NedException expected) {
    }

    // make sure it did not get inserted in NED
    assertNull(crudService.findByAttribute(composite.getEmails().get(0)));

    // make sure it did not get inserted in Ambra

    assertNull(userService.getUserByAuthId(authId));

  }

  @Test
  public void testCreateRollbackAmbra() throws Exception {

    IndividualComposite composite = getNew();

    // TODO: allow NED to validate, but force Ambra to fail somehow
  }

}
