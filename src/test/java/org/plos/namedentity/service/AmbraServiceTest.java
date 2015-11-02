package org.plos.namedentity.service;

import org.ambraproject.models.UserProfile;
import org.ambraproject.service.user.DuplicateUserException;
import org.ambraproject.service.user.UserRegistrationService;
import org.ambraproject.service.user.UserService;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Auth;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/ambra-spring-beans.xml","/spring-beans.test.xml","/ambra-spring-beans.test.xml"})
public class AmbraServiceTest {

  @Autowired
  NamedEntityService namedEntityService;  // inject so can resolve type names to ids

  @Autowired
  NamedEntityDBService nedDBSvc;

  @Autowired
  CrudService crudService;

  @Autowired
  AmbraService ambraService;

  @Autowired
  UserService userService;

  @Autowired
  UserRegistrationService userRegistrationService;


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

  private IndividualComposite loadComposite(String filename) throws Throwable {

    Unmarshaller unmarshaller = jsonUnmarshaller(IndividualComposite.class);
    return unmarshalEntity(
        new String(Files.readAllBytes(
            Paths.get(TEST_RESOURCE_PATH + filename))),
        IndividualComposite.class, unmarshaller);
  }

  private IndividualComposite getNew() throws Throwable {

    IndividualComposite composite = loadComposite("composite-individual.json");

    // cleanse composite

    composite.getUniqueidentifiers().clear();
    composite.getEmails().remove(1);

    MethodHandles.Lookup lookup = MethodHandles.lookup();

    Map<Class, List<? extends Entity>> compositeMap = composite.readAsMap();

    Iterator<Map.Entry<Class, List<? extends Entity>>> entries = compositeMap.entrySet().iterator();
    while (entries.hasNext()) {
      Map.Entry<Class, List<? extends Entity>> entry = entries.next();
      List<? extends Entity> entities = entry.getValue();
      if (entities != null) {
        MethodHandle createdByMH = lookup.findVirtual(entry.getKey(), 
            "setCreatedbyname", MethodType.methodType(void.class, String.class));
        MethodHandle lastmodifiedByMH = lookup.findVirtual(entry.getKey(),
            "setLastmodifiedbyname", MethodType.methodType(void.class, String.class));
        for (Entity entity : entities) {
          createdByMH.invoke(entity, "test");
          lastmodifiedByMH.invoke(entity, "test");
        }
      }
    }

    String rand = UUID.randomUUID().toString();

    Auth auth = composite.getAuth().get(0);
    auth.setEmail(rand + "@test.test");

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
  public void testCreateInAmbra() throws Throwable {

    IndividualComposite composite = getNew();

    Long ambraId = ambraService.createUser(composite);

    assertTrue(ambraId > 0);

    UserProfile userProfile = userService.getUser(ambraId);

    assertEqual(composite, userProfile);

  }

  @Test
  public void testCreateInAmbraAndNed() throws Throwable {

    IndividualComposite composite = getNew();

    int nedIdResponse = namedEntityService.createComposite(
        composite, IndividualComposite.class).getEmails().get(0).getNedid();

    IndividualComposite compositeFetched = namedEntityService.findComposite(
        nedIdResponse, IndividualComposite.class);

    UserProfile userProfile = userService.getUser(new Long(nedIdResponse));

    assertEqual(compositeFetched, userProfile);

  }

  @Test
  public void testUpdateInAmbra() throws Throwable {

    IndividualComposite composite = getNew();

    IndividualComposite dbComposite = namedEntityService.createComposite(
        composite, IndividualComposite.class);

    Email email = dbComposite.getEmails().get(0);
    email.setEmailaddress("email2@foo.com");
    ambraService.update(email);

    Address address = dbComposite.getAddresses().get(0);
    address.setCity("new city");
    ambraService.update(address);

    UserProfile userProfile = userService.getUser(new Long(email.getNedid()));

    assertEqual(email, userProfile);
    assertEqual(address, userProfile);
  }

  @Test
  public void testUpdateInAmbraAndNed() throws Throwable {
    IndividualComposite composite = getNew();

    Email email = namedEntityService.createComposite(
        composite, IndividualComposite.class).getEmails().get(0);

    email.setEmailaddress("valid@email.com");

    crudService.update( namedEntityService.resolveValuesToIds(email) );

    UserProfile userProfile = userService.getUser(new Long(email.getNedid()));

    IndividualComposite compositeOut = namedEntityService.findComposite(
        email.getNedid(), IndividualComposite.class);

    assertEqual(compositeOut, userProfile);

    assertEquals(compositeOut.getEmails().get(0).getEmailaddress(),
        userProfile.getEmail());

    assertEquals(userProfile.getEmail(), email.getEmailaddress());
  }


  @Test
  public void testUpdateRollbackNed() throws Throwable {

    IndividualComposite composite = getNew();

    // invalid email should trigger NED validation error

    Email email = namedEntityService.createComposite(
        composite, IndividualComposite.class).getEmails().get(0);

    String origEmail = email.getEmailaddress();

    email.setEmailaddress("invalid@email");

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
  public void testUpdateRollbackAmbra() throws Throwable {

    IndividualComposite composite = getNew();

    String authId = composite.getAuth().get(0).getAuthid();

    // remove Auth, which should cause the Ambra update to fail, but allow NED to work
    composite.setAuth(new ArrayList<>());

    try {
      namedEntityService.createComposite(composite, IndividualComposite.class);
      fail();
    } catch (NedException expected) {
    }

    // make sure it did not get inserted in NED
    Email filter = new Email();
    filter.setEmailaddress(composite.getEmails().get(0).getEmailaddress());
    assertEquals(0, crudService.findByAttribute(filter).size());

    // make sure it did not get inserted in Ambra
    assertNull(userService.getUserByAuthId(authId));
  }

  @Test
  public void testCreateRollbackNed() throws Throwable {

    IndividualComposite composite = getNew();

    composite.getEmails().get(0).setEmailaddress("invalid2@email");

    String authId = composite.getAuth().get(0).getAuthid();

    try {
      namedEntityService.createComposite(composite, IndividualComposite.class);
      fail();
    } catch (NedException expected) {
    }

    // make sure it did not get inserted in NED
    Email filter = new Email();
    filter.setEmailaddress(composite.getEmails().get(0).getEmailaddress());
    assertEquals(0, crudService.findByAttribute(filter).size());

    // make sure it did not get inserted in Ambra
    assertNull(userService.getUserByAuthId(authId));
  }

  @Test
  public void testCreateRollbackAmbra() throws Throwable {

    // inject mock Ambra service into NED service.

    AmbraService mockAmbraSvc = Mockito.mock(AmbraService.class);
    when(mockAmbraSvc.createUser(any(IndividualComposite.class)))
        .thenThrow(DuplicateUserException.class);

    NamedEntityServiceImpl namedEntityService = new NamedEntityServiceImpl();
    namedEntityService.setNamedEntityDBService(nedDBSvc);
    namedEntityService.setAmbraService(mockAmbraSvc);

    IndividualComposite composite = getNew();

    String authId = composite.getAuth().get(0).getAuthid();

    try {
      namedEntityService.createComposite(composite, IndividualComposite.class);
      fail();
    } catch (Exception expected) {
    }

    // make sure it did not get inserted in NED
    Email filter = new Email();
    filter.setEmailaddress(composite.getEmails().get(0).getEmailaddress());
    assertEquals(0, crudService.findByAttribute(filter).size());

    // make sure it did not get inserted in Ambra
    assertNull(userService.getUserByAuthId(authId));
  }
}
