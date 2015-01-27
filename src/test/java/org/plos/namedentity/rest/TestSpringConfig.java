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
package org.plos.namedentity.rest;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.oxm.json.JsonStructureSource;
import org.mockito.Mockito;
import org.plos.namedentity.api.EntityNotFoundException;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.service.CrudService;
import org.plos.namedentity.service.NamedEntityService;
import org.springframework.context.annotation.Bean;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class TestSpringConfig {

  private static final String TEST_RESOURCE_PATH = "src/test/resources/";

  @Bean @SuppressWarnings("unchecked")
  static public CrudService crudService() {
    CrudService mockCrudService =  Mockito.mock(CrudService.class);

    mockCrudForTypes(mockCrudService);
    mockCrudForEmails(mockCrudService);
    mockCrudForAddresses(mockCrudService);
    mockCrudForRoles(mockCrudService);
    mockCrudForProfiles(mockCrudService);

    return mockCrudService;
  }

  @Bean @SuppressWarnings("unchecked")
  static public NamedEntityService namedEntityService() {
    NamedEntityService mockNamedEntityService =  Mockito.mock(NamedEntityService.class);

    Individualprofile individualProfileEntity = newIndividualEntity();

    Organization organizationEntity = newOrganizationEntity();

    IndividualComposite individualComposite = newIndividualComposite();

    when(mockNamedEntityService.createIndividualComposite(isA(IndividualComposite.class)))
      .thenReturn(individualComposite)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockNamedEntityService.findIndividualComposite(anyInt()))
        .thenReturn( individualComposite );

    List<Individualprofile> emptyIndividualProfiles = new ArrayList<>();

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Individualprofile.class)))
        .thenReturn(emptyIndividualProfiles);

    doNothing().when(mockNamedEntityService).checkNedIdForType(eq(individualProfileEntity.getNedid()), anyString());

    doThrow(new EntityNotFoundException("expected")).when(mockNamedEntityService).checkNedIdForType(eq(2), anyString());

    when(mockNamedEntityService.findResolvedEntities(eq(individualProfileEntity.getNedid()), eq(Email.class)))
      .thenReturn(newEmailEntitiesForIndividual());

    when(mockNamedEntityService.findResolvedEntities(eq(organizationEntity.getNedid()), eq(Email.class)))
        .thenReturn( newEmailEntitiesForOrganization() );

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Degree.class)))
        .thenReturn(newDegreeEntities());

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Phonenumber.class)))
      .thenReturn( newPhonenumberEntities() );

    mockNamedEntityServiceForEmails(mockNamedEntityService);
    mockNamedEntityServiceForAddresses(mockNamedEntityService);
    mockNamedEntityServiceForRoles(mockNamedEntityService);
    mockNamedEntityServiceForUniqueIdentifiers(mockNamedEntityService);

    return mockNamedEntityService;
  }

  @Bean
  static public IndividualsResource individualsResource() {
    return new IndividualsResource();
  }

  @Bean
  static public TypeclassesResource typeclassesResource() {
    return new TypeclassesResource();
  }

  static private Individualprofile newIndividualEntity() {
    Individualprofile entity = new Individualprofile();
    entity.setNedid(1);
    entity.setFirstname("firstname");
    entity.setMiddlename("middlename");
    entity.setLastname("lastname");
    entity.setNameprefix("Mr.");
    entity.setNamesuffix("II");
    return entity;
  }

  static private IndividualComposite newIndividualComposite() {

    IndividualComposite composite = new IndividualComposite();

    Individualprofile entity = new Individualprofile();
    entity.setNedid(1);
    entity.setFirstname("firstname");
    entity.setMiddlename("middlename");
    entity.setLastname("lastname");
    entity.setNameprefix("Ms.");
    entity.setNamesuffix("II");

    Email emailEntity = new Email();
    emailEntity.setEmailaddress("email@internet.com");

    composite.setEmails(new ArrayList<>(Arrays.asList(emailEntity)));

    List<Individualprofile> individualProfiles = new ArrayList<>();
    individualProfiles.add(entity);

    composite.setIndividualprofiles(individualProfiles);

    return composite;
  }

  static private Organization newOrganizationEntity() {
    Organization entity = new Organization();
    entity.setNedid(2);
    entity.setIsactive(false);
    entity.setLegalname("legalname");
    entity.setFamiliarname("familiarname");
    return entity;
  }

  static private List<Email> newEmailEntitiesForIndividual() {
    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setNedid(1);
    workEmail.setId(1);
    workEmail.setType("Work");
    workEmail.setEmailaddress("fu.manchu.work@foo.com");
    emails.add( workEmail );

    Email personalEmail = new Email();
    personalEmail.setNedid(1);
    personalEmail.setId(2);
    personalEmail.setType("Personal");
    personalEmail.setEmailaddress("fu.manchu.home@foo.com");
    emails.add( personalEmail );

    return emails;
  }

  static private List<Email> newEmailEntitiesForOrganization() {
    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setNedid(2);
    workEmail.setId(5);
    workEmail.setType("Work");
    workEmail.setEmailaddress("bill@microsoft.com");
    emails.add( workEmail );

    return emails;
  }

  static private List<Degree> newDegreeEntities() {
    List<Degree> entities = new ArrayList<>();

    Degree entity = new Degree();
    entity.setType("Super Doctor");
    entities.add( entity );

    return entities;
  }

  static private List<Phonenumber> newPhonenumberEntities() {
    List<Phonenumber> phonenumbers = new ArrayList<>();

    Phonenumber officePhone = new Phonenumber();
    officePhone.setId(1);
    officePhone.setType("Office");
    officePhone.setCountrycodetype("01");
    officePhone.setPhonenumber("123-456-7890");
    phonenumbers.add( officePhone );

    Phonenumber mobilePhone = new Phonenumber();
    officePhone.setId(2);
    mobilePhone.setType("Mobile");
    mobilePhone.setCountrycodetype("01");
    mobilePhone.setPhonenumber("123-444-0011");
    phonenumbers.add( mobilePhone );

    Phonenumber homePhone = new Phonenumber();
    officePhone.setId(3);
    homePhone.setType("Home");
    homePhone.setCountrycodetype("01");
    homePhone.setPhonenumber("123-555-6666");
    phonenumbers.add( homePhone );

    return phonenumbers;
  }

  static private List<Individualprofile> newIndividualEntities() {
    List<Individualprofile> individualProfileEntities = new ArrayList<>();

    for (int i = 1; i <=3; i++) {
      Individualprofile individualProfile = new Individualprofile();
      individualProfile.setNedid(i);
      individualProfile.setFirstname("firstname"+i);
      individualProfile.setMiddlename("middlename"+i);
      individualProfile.setLastname("lastname"+i);
      individualProfile.setNameprefixtypeid(i);
      individualProfile.setNamesuffixtypeid(i);
      individualProfileEntities.add(individualProfile);
    }
    return individualProfileEntities;
  }

  static private void mockCrudForEmails(CrudService mockCrudService) {
    //when(mockCrudService.create(isA(EmailEntity.class)))
        //.thenAnswer(new Answer<EmailEntity>() {
            //@Override
            //public EmailEntity answer(InvocationOnMock invocation) throws Throwable {
                //Object[] args = invocation.getArguments();
                ////EmailEntity entity = (EmailEntity) args[0];
                //EmailEntity entity = new EmailEntity(); 
                ////dto.setId(1);
                //return entity;
            //}
        //});

    when(mockCrudService.create(isA(Email.class))).thenReturn(1);
  }

  static private void mockNamedEntityServiceForEmails(NamedEntityService mockNamedEntityService) {
    Email emailEntity = new Email();
    emailEntity.setId(1);   // db assigned primary key
    emailEntity.setNedid(1);
    emailEntity.setEmailaddress("foo.bar.personal@gmail.com");
    emailEntity.setIsactive(true);
    emailEntity.setType("Work");

    when(mockNamedEntityService.findResolvedEntityByKey(eq(emailEntity.getId()), eq(Email.class)))
        .thenReturn( emailEntity );
  }

  static private void mockCrudForAddresses(CrudService mockCrudService) {
    when(mockCrudService.create(isA(Address.class))).thenReturn(1);
  }

  static private void mockCrudForRoles(CrudService mockCrudService) {
    when(mockCrudService.create(isA(Role.class))).thenReturn(1);
  }

  static private void mockCrudForProfiles(CrudService mockCrudService) {
    Individualprofile individualProfileEntity = newIndividualEntity();
    when(mockCrudService.create(isA(Individualprofile.class))).thenReturn( individualProfileEntity.getNedid() );
    when(mockCrudService.findAll(eq(Individualprofile.class), eq(0), anyInt())).thenReturn( newIndividualEntities() );
    when(mockCrudService.findById(eq(individualProfileEntity.getNedid()), eq(Individualprofile.class))).thenReturn(individualProfileEntity);
  }

  @SuppressWarnings("unchecked")
  static private void mockNamedEntityServiceForAddresses(NamedEntityService mockNamedEntityService) {
    try {
      String addressesJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "addresses.json")));

      JAXBContext jc = jsonJaxbContext(Address.class);
      Unmarshaller unmarshaller = jc.createUnmarshaller();

      // parse json
      JsonReader jsonReader = Json.createReader(new StringReader(addressesJson));

      // unmarshal root level json array
      JsonArray addressesArray = jsonReader.readArray();
      JsonStructureSource arraySource = new JsonStructureSource(addressesArray);

      List<Address> addresses = (List<Address>) unmarshaller.unmarshal(arraySource, Address.class).getValue();

      for (int i = 0; i < addresses.size(); i++) {
        Address address = addresses.get(i);
        address.setId(i+1);   // db assigned primary key (1-based)
        address.setNedid(1);
        address.setIsactive(true);
      }

      when(mockNamedEntityService.findResolvedEntityByKey(eq(addresses.get(0).getId()), eq(Address.class)))
        .thenReturn( addresses.get(0) );

      when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Address.class)))
        .thenReturn( addresses );
    }
    catch (JAXBException je) {
      throw new RuntimeException(je);
    }
    catch (IOException e) {
      throw new RuntimeException(String.format(
        "Problem reading addresses json file. Reason: %s", e.getMessage()));
    }
  }

  static private void mockNamedEntityServiceForRoles(NamedEntityService mockNamedEntityService) {
    try {
      String rolesJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "role.json")));

      JAXBContext jc = jsonJaxbContext(Role.class);
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      Role role = unmarshaller.unmarshal(new StreamSource(new StringReader(rolesJson)), Role.class).getValue();

      role.setId(1);
      role.setNedid(1);
      role.setApplicationtype("Editorial Manager");

      when(mockNamedEntityService.findResolvedEntityByKey(eq(role.getId()), eq(Role.class)))
        .thenReturn( role );

      List<Role> roles = new ArrayList<>();
      roles.add(role);

      when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Role.class)))
        .thenReturn( roles );
    }
    catch (JAXBException je) {
      throw new RuntimeException(je);
    }
    catch (IOException e) {
      throw new RuntimeException(String.format(
        "Problem reading role json file. Reason: %s", e.getMessage()));
    }
  }

  static private void mockNamedEntityServiceForUniqueIdentifiers(NamedEntityService mockNamedEntityService) {
    try {
      String uidsJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "uids.json")));

      JAXBContext jc = jsonJaxbContext(Uniqueidentifier.class);
      Unmarshaller unmarshaller = jc.createUnmarshaller();

      // parse json
      JsonReader jsonReader = Json.createReader(new StringReader(uidsJson));

      // unmarshal root level json array
      JsonArray uidsArray = jsonReader.readArray();
      JsonStructureSource arraySource = new JsonStructureSource(uidsArray);

      List<Uniqueidentifier> uids = (List<Uniqueidentifier>) unmarshaller.unmarshal(arraySource, Uniqueidentifier.class).getValue();

      // finding mock entity by uid is a bit fragile. basically, the last match wins.
      // we throw an exception unless are able to match a type and value read in
      // from the json file.

      when(mockNamedEntityService.findResolvedEntityByUid(anyString(), anyString(), eq(Individualprofile.class)))
        .thenThrow(new EntityNotFoundException(""));

      for (int i = 0; i < uids.size(); i++) {
        Uniqueidentifier uid = uids.get(i);
        uid.setId(i+1);   // db assigned primary key (1-based)

        when(mockNamedEntityService.findResolvedEntityByUid(eq(uid.getType()), eq(uid.getUniqueidentifier()), eq(Individualprofile.class)))
          .thenReturn( newIndividualEntity() );
      }

      when(mockNamedEntityService.findResolvedEntityByKey(eq(uids.get(0).getId()), eq(Uniqueidentifier.class)))
        .thenReturn( uids.get(0) );

      when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Uniqueidentifier.class)))
        .thenReturn( uids );

    }
    catch (JAXBException je) {
      throw new RuntimeException(je);
    }
    catch (IOException e) {
      throw new RuntimeException(String.format(
        "Problem reading unique identifiers json file. Reason: %s", e.getMessage()));
    }
  }

  @SuppressWarnings("unchecked")
  static private void mockCrudForTypes(CrudService mockCrudService) {

    // TYPE DESCRIPTIONS (TYPE CLASSES)

    when(mockCrudService.create(isA(Typedescription.class)))
      .thenReturn(1)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockCrudService.update(isA(Typedescription.class)))
      .thenReturn(true)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockCrudService.delete(isA(Typedescription.class)))
      .thenReturn(true)
        .thenThrow(RuntimeException.class);

    Typedescription typedescription = new Typedescription();
    typedescription.setId(1);
    typedescription.setDescription("New Type Description");
    typedescription.setHowused("New Type Usage");

    when(mockCrudService.findById(eq(1), eq(Typedescription.class)))
      .thenReturn(typedescription);

    List<Typedescription> typeClassList = new ArrayList<>();

    for (int i = 1; i <=3; i++) {
      Typedescription td = new Typedescription();
      td.setId(i);
      td.setDescription("Type Description" + i);
      td.setHowused("Type Usage" + i);
      typeClassList.add(td);
    }

    when(mockCrudService.findAll(eq(Typedescription.class), eq(0),  anyInt())).thenReturn(typeClassList);

    // TYPE VALUES (GLOBAL TYPES)

    when(mockCrudService.create(isA(Globaltype.class)))
      .thenReturn(1)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockCrudService.update(isA(Globaltype.class)))
      .thenReturn(true)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockCrudService.delete(isA(Globaltype.class)))
      .thenReturn(true)
        .thenThrow(RuntimeException.class);

    Globaltype typeVal = new Globaltype();
    typeVal.setId(1);
    typeVal.setTypeid(1);
    typeVal.setShortdescription("Type Value #1 Short Description");
    typeVal.setTypecode("TV1");

    when(mockCrudService.findById(eq(1), eq(Globaltype.class))).thenReturn(typeVal);

    List<Globaltype> typeValuesForTypeClass = new ArrayList<>();
    for (int i = 1; i <=5; i++) {
      Globaltype globaltype = new Globaltype();

      globaltype.setId(i);
      globaltype.setTypeid(1);
      globaltype.setShortdescription("shortdesc"+i);
      globaltype.setLongdescription("longdesc"+i);
      globaltype.setTypecode("typ"+i);

      typeValuesForTypeClass.add(globaltype);
    }

    when(mockCrudService.findByAttribute(isA(Globaltype.class))).thenReturn(typeValuesForTypeClass);
  }

  static private <T> JAXBContext jsonJaxbContext(Class<T> clazz) throws JAXBException {
    Map<String,Object> properties = new HashMap<String,Object>(2);
    properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
    properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false); 
    return JAXBContext.newInstance(new Class[] {clazz}, properties);
  }
}
