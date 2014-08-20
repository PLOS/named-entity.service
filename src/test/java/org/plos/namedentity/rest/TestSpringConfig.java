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

import org.mockito.Mockito;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.service.CrudService;
import org.plos.namedentity.service.NamedEntityService;
import org.springframework.context.annotation.Bean;

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

  @Bean @SuppressWarnings("unchecked")
  static public CrudService crudService() {
    CrudService mockCrudService =  Mockito.mock(CrudService.class);

    mockCrudForTypes(mockCrudService);
    mockCrudForEmails(mockCrudService);

    // INDIVIDUALS 

    when(mockCrudService.findAll(eq(IndividualEntity.class))).thenReturn( newIndividualEntities() );

    when(mockCrudService.findById(eq(1), eq(IndividualEntity.class))).thenReturn( newIndividualEntity() );

    return mockCrudService;
  }

  @Bean @SuppressWarnings("unchecked")
  static public NamedEntityService namedEntityService() {
    NamedEntityService mockNamedEntityService =  Mockito.mock(NamedEntityService.class);

    IndividualEntity individualEntity = newIndividualEntity();

    IndividualComposite individualComposite = newIndividualComposite();

    when(mockNamedEntityService.createIndividualComposite(isA(IndividualComposite.class)))
      .thenReturn(individualComposite)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockNamedEntityService.findIndividualComposite(anyInt()))
        .thenReturn( individualComposite );

    when(mockNamedEntityService.findResolvedEntity(anyInt(), eq(IndividualEntity.class)))
      .thenReturn( individualEntity );

    when(mockNamedEntityService.findResolvedEntityByUid(anyString(), anyString(), eq(IndividualEntity.class)))
      .thenReturn( newIndividualEntities() );

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(AddressEntity.class)))
      .thenReturn( newAddressEntities() );

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(EmailEntity.class)))
      .thenReturn( newEmailEntities() );

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(DegreeEntity.class)))
        .thenReturn(newDegreeEntities());

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(PhonenumberEntity.class)))
      .thenReturn( newPhonenumberEntities() );

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(RoleEntity.class)))
      .thenReturn( newRoleEntities() );

    when(mockNamedEntityService.findResolvedEntities(anyInt(), eq(UniqueidentifierEntity.class)))
      .thenReturn( newUidEntities() );

    OrganizationEntity organizationEntity = newOrganizationEntity();

    when(mockNamedEntityService.createOrganization(isA(OrganizationEntity.class)))
        .thenReturn(organizationEntity);

    when(mockNamedEntityService.findResolvedEntity(anyInt(), eq(OrganizationEntity.class)))
        .thenReturn( organizationEntity );

    mockNamedEntityServiceForEmails(mockNamedEntityService);

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

  static private IndividualEntity newIndividualEntity() {
    IndividualEntity entity = new IndividualEntity();
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

    IndividualEntity entity = new IndividualEntity();
    entity.setNamedentityid(1);
    entity.setFirstname("firstname");
    entity.setMiddlename("middlename");
    entity.setLastname("lastname");
    entity.setNameprefix("Ms.");
    entity.setNamesuffix("II");
    entity.setPreferredlanguage("Mandarin");
    entity.setPreferredcommunication("Phone");

    EmailEntity emailEntity = new EmailEntity();
    emailEntity.setEmailaddress("email@internet.com");

    composite.setEmails(new ArrayList<>(Arrays.asList(emailEntity)));

    composite.setIndividual(entity);

    return composite;
  }

  static private OrganizationEntity newOrganizationEntity() {
    OrganizationEntity entity = new OrganizationEntity();
    entity.setNamedentityid(1);
    entity.setIsactive((byte)0);
    entity.setIsvisible((byte)1);
    entity.setOrganizationlegalname("legalname");
    entity.setOrganizationfamiliarname("familiarname");
    return entity;
  }

  static private List<AddressEntity> newAddressEntities() {
    List<AddressEntity> addresses = new ArrayList<>();

    AddressEntity address = new AddressEntity();
    address.setAddresstype("Office");
    address.setAddressline1("addressline1");
    address.setAddressline2("addressline2");
    address.setCity("city");
    address.setStatecodetype("CA");
    address.setCountrycodetype("United States");
    address.setPostalcode("1234567");
    address.setIsprimary((byte)1);
    addresses.add( address );

    return addresses;
  }

  static private List<EmailEntity> newEmailEntities() {
    List<EmailEntity> emails = new ArrayList<>();

    EmailEntity workEmail = new EmailEntity();
    workEmail.setEmailid(1);
    workEmail.setEmailtype("Work");
    workEmail.setEmailaddress("fu.manchu.work@foo.com");
    workEmail.setIsprimary((byte)1);
    emails.add( workEmail );

    EmailEntity personalEmail = new EmailEntity();
    workEmail.setEmailid(1);
    personalEmail.setEmailtype("Personal");
    personalEmail.setEmailaddress("fu.manchu.home@foo.com");
    personalEmail.setIsprimary((byte)0);
    emails.add( personalEmail );

    return emails;
  }

  static private List<DegreeEntity> newDegreeEntities() {
    List<DegreeEntity> entities = new ArrayList<>();

    DegreeEntity entity = new DegreeEntity();
    entity.setDegreetype("Super Doctor");
    entities.add( entity );

    return entities;
  }

  static private List<PhonenumberEntity> newPhonenumberEntities() {
    List<PhonenumberEntity> phonenumbers = new ArrayList<>();

    PhonenumberEntity officePhone = new PhonenumberEntity();
    officePhone.setPhonenumberid(1);
    officePhone.setPhonenumbertype("Office");
    officePhone.setCountrycodetype("01");
    officePhone.setPhonenumber("123-456-7890");
    officePhone.setIsprimary(true);
    phonenumbers.add( officePhone );

    PhonenumberEntity mobilePhone = new PhonenumberEntity();
    officePhone.setPhonenumberid(2);
    mobilePhone.setPhonenumbertype("Mobile");
    mobilePhone.setCountrycodetype("01");
    mobilePhone.setPhonenumber("123-444-0011");
    mobilePhone.setIsprimary(false);
    phonenumbers.add( mobilePhone );

    PhonenumberEntity homePhone = new PhonenumberEntity();
    officePhone.setPhonenumberid(3);
    homePhone.setPhonenumbertype("Home");
    homePhone.setCountrycodetype("01");
    homePhone.setPhonenumber("123-555-6666");
    homePhone.setIsprimary(false);
    phonenumbers.add( homePhone );

    return phonenumbers;
  }

  static private List<RoleEntity> newRoleEntities() {
    List<RoleEntity> roles = new ArrayList<>();

    RoleEntity author = new RoleEntity();
    author.setRoleid(1);
    author.setRoletype("Author");
    author.setStartdate(new Timestamp(1401408000)); // "2014-05-30"
    roles.add( author );

    return roles;
  }

  static private List<IndividualEntity> newIndividualEntities() {
    List<IndividualEntity> individualEntities = new ArrayList<>();

    for (int i = 1; i <=3; i++) {
      IndividualEntity individual = new IndividualEntity();
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

  static private List<UniqueidentifierEntity> newUidEntities() {
    List<UniqueidentifierEntity> uids = new ArrayList<>();
    for (int i = 1; i <=2; i++) {
      uids.add(new UniqueidentifierEntity(i, 1, null, "0000-0002-9430-319"+i, "ORCID"));
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

    when(mockCrudService.create(isA(EmailEntity.class))).thenReturn(1);
  }

  static private void mockNamedEntityServiceForEmails(NamedEntityService mockNamedEntityService) {
    EmailEntity emailEntity = new EmailEntity();
    emailEntity.setEmailid(1);   // db assigned primary key
    emailEntity.setNamedentityid(1);
    emailEntity.setEmailaddress("foo.bar.personal@gmail.com");
    emailEntity.setIsprimary((byte)1);
    emailEntity.setIsactive((byte)1);
    emailEntity.setEmailtype("Work");

    when(mockNamedEntityService.findResolvedEntityByKey(anyInt(), eq(EmailEntity.class)))
        .thenReturn( emailEntity );
  }

  @SuppressWarnings("unchecked")
  static private void mockCrudForTypes(CrudService mockCrudService) {

    // TYPE DESCRIPTIONS (TYPE CLASSES)

    when(mockCrudService.create(isA(TypedescriptionEntity.class)))
      .thenReturn(1)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockCrudService.update(isA(TypedescriptionEntity.class)))
      .thenReturn(true)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockCrudService.delete(isA(TypedescriptionEntity.class)))
      .thenReturn(true)
        .thenThrow(RuntimeException.class);

    when(mockCrudService.findById(eq(1), eq(TypedescriptionEntity.class)))
      .thenReturn(new TypedescriptionEntity(1, "New Type Description", "New Type Usage"));

    List<TypedescriptionEntity> typeClassList = new ArrayList<>();
    typeClassList.add(new TypedescriptionEntity(1, "Type Description1", "Type Usage1"));
    typeClassList.add(new TypedescriptionEntity(2, "Type Description2", "Type Usage2"));
    typeClassList.add(new TypedescriptionEntity(3, "Type Description3", "Type Usage3"));

    when(mockCrudService.findAll(eq(TypedescriptionEntity.class))).thenReturn(typeClassList);

    // TYPE VALUES (GLOBAL TYPES)

    when(mockCrudService.create(isA(GlobaltypeEntity.class)))
      .thenReturn(1)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockCrudService.update(isA(GlobaltypeEntity.class)))
      .thenReturn(true)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockCrudService.delete(isA(GlobaltypeEntity.class)))
      .thenReturn(true)
        .thenThrow(RuntimeException.class);

    GlobaltypeEntity typeVal = new GlobaltypeEntity();
    typeVal.setGlobaltypeid(1);
    typeVal.setTypeid(1);
    typeVal.setShortdescription("Type Value #1 Short Description");
    typeVal.setTypecode("TV1");

    when(mockCrudService.findById(eq(1), eq(GlobaltypeEntity.class))).thenReturn(typeVal);

    List<GlobaltypeEntity> typeValuesForTypeClass = new ArrayList<>();
    for (int i = 1; i <=5; i++) {
      typeValuesForTypeClass.add(new GlobaltypeEntity(
        i, 1, "shortdesc"+i, "longdesc"+i, "typ"+i, null, null, null, null));
    }

    when(mockCrudService.findByAttribute(isA(GlobaltypeEntity.class))).thenReturn(typeValuesForTypeClass);
  }
}
