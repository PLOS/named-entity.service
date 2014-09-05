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

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.mockito.Mockito;
import org.plos.namedentity.api.EntityNotFoundException;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Degree;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Globaltype;
import org.plos.namedentity.api.entity.Individual;
import org.plos.namedentity.api.entity.Organization;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Typedescription;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.service.CrudService;
import org.plos.namedentity.service.NamedEntityService;

import org.springframework.context.annotation.Bean;
import org.codehaus.jackson.map.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.codehaus.jackson.map.ObjectMapper;

public class TestSpringConfig {

  private static final String TEST_RESOURCE_PATH = "src/test/resources/";

  private static ObjectMapper mapper = new ObjectMapper();

  @Bean @SuppressWarnings("unchecked")
  static public CrudService crudService() {
    CrudService mockCrudService =  Mockito.mock(CrudService.class);

    mockCrudForTypes(mockCrudService);
    mockCrudForEmails(mockCrudService);
    mockCrudForAddresses(mockCrudService);
    mockCrudForRoles(mockCrudService);

    // INDIVIDUALS
    Individual individualEntity = newIndividualEntity();

    when(mockCrudService.create(isA(Individual.class))).thenReturn( individualEntity.getNamedentityid() );

    when(mockCrudService.findAll(eq(Individual.class))).thenReturn( newIndividualEntities() );

    when(mockCrudService.findById(eq(individualEntity.getNamedentityid()), eq(Individual.class))).thenReturn(individualEntity);

    return mockCrudService;
  }

  @Bean @SuppressWarnings("unchecked")
  static public NamedEntityService namedEntityService() {
    NamedEntityService mockNamedEntityService =  Mockito.mock(NamedEntityService.class);

    Individual individualEntity = newIndividualEntity();

    Organization organizationEntity = newOrganizationEntity();

    IndividualComposite individualComposite = newIndividualComposite();

    when(mockNamedEntityService.createIndividualComposite(isA(IndividualComposite.class)))
      .thenReturn(individualComposite)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockNamedEntityService.findIndividualComposite(anyInt()))
        .thenReturn( individualComposite );

    when(mockNamedEntityService.findResolvedEntity(anyInt(), eq(Individual.class)))
        .thenThrow(new EntityNotFoundException("Not found"));

    when(mockNamedEntityService.findResolvedEntity(eq(individualEntity.getNamedentityid()), eq(Individual.class)))
      .thenReturn(individualEntity);

    when(mockNamedEntityService.findResolvedEntityByUid(anyString(), anyString(), eq(Individual.class)))
      .thenReturn( newIndividualEntities() );

    when(mockNamedEntityService.findResolvedEntities(eq(individualEntity.getNamedentityid()), eq(Email.class)))
      .thenReturn( newEmailEntitiesForIndividual() );

    when(mockNamedEntityService.findResolvedEntities(eq(organizationEntity.getNamedentityid()), eq(Email.class)))
        .thenReturn( newEmailEntitiesForOrganization() );

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Degree.class)))
        .thenReturn(newDegreeEntities());

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Phonenumber.class)))
      .thenReturn( newPhonenumberEntities() );

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Uniqueidentifier.class)))
      .thenReturn( newUidEntities() );

    when(mockNamedEntityService.createOrganization(isA(Organization.class)))
        .thenReturn(organizationEntity);

    when(mockNamedEntityService.findResolvedEntity(eq(organizationEntity.getNamedentityid()), eq(Organization.class)))
        .thenReturn(organizationEntity);

    mockNamedEntityServiceForEmails(mockNamedEntityService);
    mockNamedEntityServiceForAddresses(mockNamedEntityService);
    mockNamedEntityServiceForRoles(mockNamedEntityService);

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

  static private Individual newIndividualEntity() {
    Individual entity = new Individual();
    entity.setNamedentityid(1);
    entity.setFirstname("firstname");
    entity.setMiddlename("middlename");
    entity.setLastname("lastname");
    entity.setNameprefix("Mr.");
    entity.setNamesuffix("II");
    entity.setPreferredlanguage("Mandarin");
    entity.setPreferredcommunication("Phone");
    return entity;
  }

  static private IndividualComposite newIndividualComposite() {

    IndividualComposite composite = new IndividualComposite();

    Individual entity = new Individual();
    entity.setNamedentityid(1);
    entity.setFirstname("firstname");
    entity.setMiddlename("middlename");
    entity.setLastname("lastname");
    entity.setNameprefix("Ms.");
    entity.setNamesuffix("II");
    entity.setPreferredlanguage("Mandarin");
    entity.setPreferredcommunication("Phone");

    Email emailEntity = new Email();
    emailEntity.setEmailaddress("email@internet.com");

    composite.setEmails(new ArrayList<>(Arrays.asList(emailEntity)));

    composite.setIndividual(entity);

    return composite;
  }

  static private Organization newOrganizationEntity() {
    Organization entity = new Organization();
    entity.setNamedentityid(2);
    entity.setIsactive((byte)0);
    entity.setIsvisible((byte)1);
    entity.setOrganizationlegalname("legalname");
    entity.setOrganizationfamiliarname("familiarname");
    return entity;
  }

  static private List<Email> newEmailEntitiesForIndividual() {
    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setNamedentityid(1);
    workEmail.setEmailid(1);
    workEmail.setEmailtype("Work");
    workEmail.setEmailaddress("fu.manchu.work@foo.com");
    workEmail.setIsprimary((byte)1);
    emails.add( workEmail );

    Email personalEmail = new Email();
    personalEmail.setNamedentityid(1);
    personalEmail.setEmailid(2);
    personalEmail.setEmailtype("Personal");
    personalEmail.setEmailaddress("fu.manchu.home@foo.com");
    personalEmail.setIsprimary((byte)0);
    emails.add( personalEmail );

    return emails;
  }

  static private List<Email> newEmailEntitiesForOrganization() {
    List<Email> emails = new ArrayList<>();

    Email workEmail = new Email();
    workEmail.setNamedentityid(2);
    workEmail.setEmailid(5);
    workEmail.setEmailtype("Work");
    workEmail.setEmailaddress("bill@microsoft.com");
    workEmail.setIsprimary((byte)1);
    emails.add( workEmail );

    return emails;
  }

  static private List<Degree> newDegreeEntities() {
    List<Degree> entities = new ArrayList<>();

    Degree entity = new Degree();
    entity.setDegreetype("Super Doctor");
    entities.add( entity );

    return entities;
  }

  static private List<Phonenumber> newPhonenumberEntities() {
    List<Phonenumber> phonenumbers = new ArrayList<>();

    Phonenumber officePhone = new Phonenumber();
    officePhone.setPhonenumberid(1);
    officePhone.setPhonenumbertype("Office");
    officePhone.setCountrycodetype("01");
    officePhone.setPhonenumber("123-456-7890");
    officePhone.setIsprimary(true);
    phonenumbers.add( officePhone );

    Phonenumber mobilePhone = new Phonenumber();
    officePhone.setPhonenumberid(2);
    mobilePhone.setPhonenumbertype("Mobile");
    mobilePhone.setCountrycodetype("01");
    mobilePhone.setPhonenumber("123-444-0011");
    mobilePhone.setIsprimary(false);
    phonenumbers.add( mobilePhone );

    Phonenumber homePhone = new Phonenumber();
    officePhone.setPhonenumberid(3);
    homePhone.setPhonenumbertype("Home");
    homePhone.setCountrycodetype("01");
    homePhone.setPhonenumber("123-555-6666");
    homePhone.setIsprimary(false);
    phonenumbers.add( homePhone );

    return phonenumbers;
  }

  static private List<Individual> newIndividualEntities() {
    List<Individual> individualEntities = new ArrayList<>();

    for (int i = 1; i <=3; i++) {
      Individual individual = new Individual();
      individual.setNamedentityid(i);
      individual.setFirstname("firstname"+i);
      individual.setMiddlename("middlename"+i);
      individual.setLastname("lastname"+i);
      individual.setNameprefixtypeid(i);
      individual.setNamesuffixtypeid(i);
      individual.setPreferredlanguagetypeid(i);
      individual.setPreferredcommunicationmethodtypeid(i);
      individualEntities.add( individual );
    }
    return individualEntities;
  }

  static private List<Uniqueidentifier> newUidEntities() {
    List<Uniqueidentifier> uids = new ArrayList<>();
    for (int i = 1; i <=2; i++) {
      uids.add(new Uniqueidentifier(i, 1, null, "0000-0002-9430-319"+i, "ORCID"));
    }
    return uids;
  }

  static private void mockCrudForEmails(CrudService mockCrudService) {
    //when(mockCrudService.create(isA(EmailEntity.class)))
        //.thenAnswer(new Answer<EmailEntity>() {
            //@Override
            //public EmailEntity answer(InvocationOnMock invocation) throws Throwable {
                //Object[] args = invocation.getArguments();
                ////EmailEntity entity = (EmailEntity) args[0];
                //EmailEntity entity = new EmailEntity(); 
                ////dto.setTypeid(1);
                //return entity;
            //}
        //});

    when(mockCrudService.create(isA(Email.class))).thenReturn(1);
  }

  static private void mockNamedEntityServiceForEmails(NamedEntityService mockNamedEntityService) {
    Email emailEntity = new Email();
    emailEntity.setEmailid(1);   // db assigned primary key
    emailEntity.setNamedentityid(1);
    emailEntity.setEmailaddress("foo.bar.personal@gmail.com");
    emailEntity.setIsprimary((byte)1);
    emailEntity.setIsactive((byte)1);
    emailEntity.setEmailtype("Work");

    when(mockNamedEntityService.findResolvedEntityByKey(eq(emailEntity.getEmailid()), eq(Email.class)))
        .thenReturn( emailEntity );
  }

  static private void mockCrudForAddresses(CrudService mockCrudService) {
    when(mockCrudService.create(isA(Address.class))).thenReturn(1);
  }

  static private void mockCrudForRoles(CrudService mockCrudService) {
    when(mockCrudService.create(isA(Role.class))).thenReturn(1);
  }

  static private void mockNamedEntityServiceForAddresses(NamedEntityService mockNamedEntityService) {
    try {
      String addressesJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "addresses.json")));
      Address[] addresses = mapper.readValue(addressesJson, Address[].class);

      for (int i = 0; i < addresses.length; i++) {
        addresses[i].setAddressid(i+1);   // db assigned primary key (1-based)
        addresses[i].setNamedentityid(1);
        addresses[i].setIsprimary((byte)1);
        addresses[i].setIsactive((byte)1);
      }

      when(mockNamedEntityService.findResolvedEntityByKey(eq(addresses[0].getAddressid()), eq(Address.class)))
        .thenReturn( addresses[0] );

      when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Address.class)))
        .thenReturn( Arrays.asList(addresses) );
    }
    catch (IOException e) {
      throw new RuntimeException(String.format(
        "Problem reading addresses json file. Reason: %s", e.getMessage()));
    }
  }

  static private void mockNamedEntityServiceForRoles(NamedEntityService mockNamedEntityService) {
    try {
      String rolesJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "role.json")));

      Map<String,Object> properties = new HashMap<String,Object>(1);
      properties.put("eclipselink.media-type", "application/json");
      //System.setProperty(JAXBContext.JAXB_CONTEXT_FACTORY, "org.eclipse.persistence.jaxb.JAXBContextFactory");
      //JAXBContext jc = JAXBContext.newInstance(Role.class);
      JAXBContext jc = JAXBContext.newInstance(new Class[]{ Role.class }, properties);

      Unmarshaller u = jc.createUnmarshaller();
      //u.setProperty("eclipselink.media-type", "application/json");

      Object o = u.unmarshal( new StreamSource(new StringReader(rolesJson)) );

      Role role = mapper.readValue(rolesJson, Role.class);

      role.setRoleid(1);
      role.setNamedentityid(1);

      when(mockNamedEntityService.findResolvedEntityByKey(eq(role.getRoleid()), eq(Role.class)))
        .thenReturn( role );

      List<Role> roles = new ArrayList<Role>();
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

    when(mockCrudService.findById(eq(1), eq(Typedescription.class)))
      .thenReturn(new Typedescription(1, "New Type Description", "New Type Usage"));

    List<Typedescription> typeClassList = new ArrayList<>();
    typeClassList.add(new Typedescription(1, "Type Description1", "Type Usage1"));
    typeClassList.add(new Typedescription(2, "Type Description2", "Type Usage2"));
    typeClassList.add(new Typedescription(3, "Type Description3", "Type Usage3"));

    when(mockCrudService.findAll(eq(Typedescription.class))).thenReturn(typeClassList);

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
    typeVal.setGlobaltypeid(1);
    typeVal.setTypeid(1);
    typeVal.setShortdescription("Type Value #1 Short Description");
    typeVal.setTypecode("TV1");

    when(mockCrudService.findById(eq(1), eq(Globaltype.class))).thenReturn(typeVal);

    List<Globaltype> typeValuesForTypeClass = new ArrayList<>();
    for (int i = 1; i <=5; i++) {
      typeValuesForTypeClass.add(new Globaltype(
        i, 1, "shortdesc"+i, "longdesc"+i, "typ"+i, null, null, null, null));
    }

    when(mockCrudService.findByAttribute(isA(Globaltype.class))).thenReturn(typeValuesForTypeClass);
  }
}
