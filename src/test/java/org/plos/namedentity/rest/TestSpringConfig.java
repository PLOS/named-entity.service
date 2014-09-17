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

import org.codehaus.jackson.map.ObjectMapper;
import org.mockito.Mockito;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.service.CrudService;
import org.plos.namedentity.service.NamedEntityService;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

public class TestSpringConfig {

  private static final String TEST_RESOURCE_PATH = "src/test/resources/";

  private static ObjectMapper mapper = new ObjectMapper();

  @Bean @SuppressWarnings("unchecked")
  static public CrudService crudService() {
    CrudService mockCrudService =  Mockito.mock(CrudService.class);

    mockCrudForTypes(mockCrudService);
    mockCrudForEmails(mockCrudService);
    mockCrudForAddresses(mockCrudService);

    // INDIVIDUALS
    Individual individualEntity = newIndividualEntity();

    when(mockCrudService.create(isA(Individual.class))).thenReturn( individualEntity.getNedid() );

    when(mockCrudService.findAll(eq(Individual.class))).thenReturn( newIndividualEntities() );

    when(mockCrudService.findById(eq(individualEntity.getNedid()), eq(Individual.class))).thenReturn(individualEntity);

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

    List<Individual> emptyIndividuals = new ArrayList<>();

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Individual.class)))
        .thenReturn(emptyIndividuals);

    when(mockNamedEntityService.findResolvedEntities(eq(individualEntity.getNedid()), eq(Individual.class)))
      .thenReturn(individualComposite.getIndividuals());

    when(mockNamedEntityService.findResolvedEntityByUid(anyString(), anyString(), eq(Individual.class)))
      .thenReturn( newIndividualEntity() );

    when(mockNamedEntityService.findResolvedEntities(eq(individualEntity.getNedid()), eq(Email.class)))
      .thenReturn( newEmailEntitiesForIndividual() );

    when(mockNamedEntityService.findResolvedEntities(eq(organizationEntity.getNedid()), eq(Email.class)))
        .thenReturn( newEmailEntitiesForOrganization() );

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Degree.class)))
        .thenReturn(newDegreeEntities());

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Phonenumber.class)))
      .thenReturn( newPhonenumberEntities() );

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Role.class)))
      .thenReturn( newRoleEntities() );

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Uniqueidentifier.class)))
      .thenReturn( newUidEntities() );

//    when(mockNamedEntityService.createOrganization(isA(Organization.class)))
//        .thenReturn(organizationEntity);
//
//    when(mockNamedEntityService.findResolvedEntity(eq(organizationEntity.getNedid()), eq(Organization.class)))
//        .thenReturn(organizationEntity);

    mockNamedEntityServiceForEmails(mockNamedEntityService);
    mockNamedEntityServiceForAddresses(mockNamedEntityService);

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
    entity.setNedid(1);
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
    entity.setNedid(1);
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

    List<Individual> individuals = new ArrayList<>();
    individuals.add(entity);

    composite.setIndividuals(individuals);

    return composite;
  }

  static private Organization newOrganizationEntity() {
    Organization entity = new Organization();
    entity.setNedid(2);
    entity.setIsactive((byte)0);
    entity.setIsvisible((byte)1);
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
    workEmail.setIsprimary((byte)1);
    emails.add( workEmail );

    Email personalEmail = new Email();
    personalEmail.setNedid(1);
    personalEmail.setId(2);
    personalEmail.setType("Personal");
    personalEmail.setEmailaddress("fu.manchu.home@foo.com");
    personalEmail.setIsprimary((byte)0);
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
    workEmail.setIsprimary((byte)1);
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
    officePhone.setIsprimary(true);
    phonenumbers.add( officePhone );

    Phonenumber mobilePhone = new Phonenumber();
    officePhone.setId(2);
    mobilePhone.setType("Mobile");
    mobilePhone.setCountrycodetype("01");
    mobilePhone.setPhonenumber("123-444-0011");
    mobilePhone.setIsprimary(false);
    phonenumbers.add( mobilePhone );

    Phonenumber homePhone = new Phonenumber();
    officePhone.setId(3);
    homePhone.setType("Home");
    homePhone.setCountrycodetype("01");
    homePhone.setPhonenumber("123-555-6666");
    homePhone.setIsprimary(false);
    phonenumbers.add( homePhone );

    return phonenumbers;
  }

  static private List<Role> newRoleEntities() {
    List<Role> roles = new ArrayList<>();

    Role author = new Role();
    author.setId(1);
    author.setType("Author");
    author.setStartdate(new Timestamp(1401408000)); // "2014-05-30"
    roles.add( author );

    return roles;
  }

  static private List<Individual> newIndividualEntities() {
    List<Individual> individualEntities = new ArrayList<>();

    for (int i = 1; i <=3; i++) {
      Individual individual = new Individual();
      individual.setNedid(i);
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

      Uniqueidentifier uid = new Uniqueidentifier();
      uid.setId(i);
      uid.setNedid(1);
      uid.setUniqueidentifier("0000-0002-9430-319"+i);
      uid.setType("ORCID");

      uids.add(uid);
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
    emailEntity.setIsprimary((byte)1);
    emailEntity.setIsactive((byte)1);
    emailEntity.setType("Work");

    when(mockNamedEntityService.findResolvedEntityByKey(eq(emailEntity.getId()), eq(Email.class)))
        .thenReturn( emailEntity );
  }

  static private void mockCrudForAddresses(CrudService mockCrudService) {
    when(mockCrudService.create(isA(Address.class))).thenReturn(1);
  }

  static private void mockNamedEntityServiceForAddresses(NamedEntityService mockNamedEntityService) {
    try {
      String addressesJson = new String(Files.readAllBytes(Paths.get(TEST_RESOURCE_PATH + "addresses.json")));
      Address[] addresses = mapper.readValue(addressesJson, Address[].class);

      for (int i = 0; i < addresses.length; i++) {
        addresses[i].setId(i + 1);   // db assigned primary key (1-based)
        addresses[i].setNedid(1);
        addresses[i].setIsprimary((byte)1);
        addresses[i].setIsactive((byte)1);
      }

      when(mockNamedEntityService.findResolvedEntityByKey(eq(addresses[0].getId()), eq(Address.class)))
        .thenReturn( addresses[0] );

      when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(Address.class)))
        .thenReturn( Arrays.asList(addresses) );
    }
    catch (IOException e) {
      throw new RuntimeException(String.format(
        "Problem reading addresses json file. Reason: %s", e.getMessage()));
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
}
