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
package org.plos.namedentity.spring.config;

import org.mockito.Mockito;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.dto.PhonenumberDTO;
import org.plos.namedentity.api.dto.RoleDTO;
import org.plos.namedentity.api.dto.UniqueidentifierDTO;
import org.plos.namedentity.api.entity.AddressEntity;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;
import org.plos.namedentity.rest.IndividualsResource;
import org.plos.namedentity.rest.TypeclassesResource;
import org.plos.namedentity.service.CrudService;
import org.plos.namedentity.service.NamedEntityService;
import org.plos.namedentity.utils.EntityPojoTransformer;
import org.plos.namedentity.utils.Transformer;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

public class TestSpringConfig {

  @Bean @SuppressWarnings("unchecked")
  static public CrudService namedEntityService() {
    CrudService mockNamedEntityService =  Mockito.mock(CrudService.class);

    when(mockNamedEntityService.create(any()))
      .thenReturn(Integer.valueOf(1))
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockNamedEntityService.update(any()))
      .thenReturn(true)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockNamedEntityService.delete(any()))
      .thenReturn(true)
        .thenThrow(RuntimeException.class);

    // TYPE DESCRIPTIONS (TYPE CLASSES)

    when(mockNamedEntityService.findById(eq(1), eq(TypedescriptionEntity.class)))
      .thenReturn(new TypedescriptionEntity(1, "New Type Description", "New Type Usage"));

    List<TypedescriptionEntity> typeClassList = new ArrayList<>();
    typeClassList.add(new TypedescriptionEntity(1, "Type Description1", "Type Usage1"));
    typeClassList.add(new TypedescriptionEntity(2, "Type Description2", "Type Usage2"));
    typeClassList.add(new TypedescriptionEntity(3, "Type Description3", "Type Usage3"));

    when(mockNamedEntityService.findAll(eq(TypedescriptionEntity.class))).thenReturn(typeClassList);

    // TYPE VALUES (GLOBAL TYPES)

    GlobaltypeEntity typeVal = new GlobaltypeEntity();
    typeVal.setGlobaltypeid(1);
    typeVal.setTypeid(1);
    typeVal.setShortdescription("Type Value #1 Short Description");
    typeVal.setTypecode("TV1");

    when(mockNamedEntityService.findById(eq(1), eq(GlobaltypeEntity.class))).thenReturn(typeVal);

    List<GlobaltypeEntity> typeValuesForTypeClass = new ArrayList<>();
    for (int i = 1; i <=5; i++) {
      typeValuesForTypeClass.add(new GlobaltypeEntity(
        i, 1, "shortdesc"+i, "longdesc"+i, "typ"+i, null, null, null, null));
    }

    when(mockNamedEntityService.findByAttribute(isA(GlobaltypeEntity.class))).thenReturn(typeValuesForTypeClass);

    // INDIVIDUALS 

    when(mockNamedEntityService.findAll(eq(IndividualEntity.class))).thenReturn( newIndividualEntities() );

    return mockNamedEntityService;
  }

  @Bean @SuppressWarnings("unchecked")
  static public NamedEntityService namedEntityServiceHighApi() {
    NamedEntityService mockNamedEntityService =  Mockito.mock(NamedEntityService.class);

    IndividualEntity individualDto = newIndividualEntity();

    when(mockNamedEntityService.createIndividual(isA(IndividualComposite.class)))
      .thenReturn(individualDto)
        .thenThrow(NedValidationException.class)
          .thenThrow(RuntimeException.class);

    when(mockNamedEntityService.findIndividualByNedId(anyInt()))
      .thenReturn( individualDto );

    when(mockNamedEntityService.findAddressesByNedId(anyInt()))
      .thenReturn( newAddressEntities() );

    when(mockNamedEntityService.findEmailsByNedId(anyInt()))
      .thenReturn( newEmailsDto() );

    when(mockNamedEntityService.findPhoneNumbersByNedId(anyInt()))
      .thenReturn( newPhonenumbersDto() );

    when(mockNamedEntityService.findRolesByNedId(anyInt()))
      .thenReturn( newRolesDto() );

    when(mockNamedEntityService.findUniqueIdsByNedId(anyInt()))
      .thenReturn( newUidsDto() );

    return mockNamedEntityService;
  }

  @Bean 
  static public Transformer transformer() {
    return new EntityPojoTransformer();
  }

//  @Bean
//  static public NamedEntityResource namedEntityResource() {
//    NamedEntityResource nedResource = new NamedEntityResource();
//    //nedResource.setNamedEntityService(namedEntityService());
//    return nedResource;
//  }

  @Bean
  static public IndividualsResource individualsController() {
    return new IndividualsResource();
  }

  @Bean
  static public TypeclassesResource typeclassesController() {
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

  static private List<EmailDTO> newEmailsDto() {
    List<EmailDTO> emails = new ArrayList<>();

    EmailDTO workEmail = new EmailDTO();
    workEmail.setEmailtype("Work");
    workEmail.setEmailaddress("fu.manchu.work@foo.com");
    workEmail.setIsprimary(true);
    emails.add( workEmail );

    EmailDTO personalEmail = new EmailDTO();
    personalEmail.setEmailtype("Personal");
    personalEmail.setEmailaddress("fu.manchu.home@foo.com");
    personalEmail.setIsprimary(false);
    emails.add( personalEmail );

    return emails;
  }

  static private List<PhonenumberDTO> newPhonenumbersDto() {
    List<PhonenumberDTO> phonenumbers = new ArrayList<>();

    PhonenumberDTO officePhone = new PhonenumberDTO();
    officePhone.setPhonenumbertype("Office");
    officePhone.setCountrycodetype("01");
    officePhone.setPhonenumber("123-456-7890");
    officePhone.setIsprimary(true);
    phonenumbers.add( officePhone );

    PhonenumberDTO mobilePhone = new PhonenumberDTO();
    mobilePhone.setPhonenumbertype("Mobile");
    mobilePhone.setCountrycodetype("01");
    mobilePhone.setPhonenumber("123-444-0011");
    mobilePhone.setIsprimary(false);
    phonenumbers.add( mobilePhone );

    PhonenumberDTO homePhone = new PhonenumberDTO();
    homePhone.setPhonenumbertype("Home");
    homePhone.setCountrycodetype("01");
    homePhone.setPhonenumber("123-555-6666");
    homePhone.setIsprimary(false);
    phonenumbers.add( homePhone );

    return phonenumbers;
  }

  static private List<RoleDTO> newRolesDto() {
    List<RoleDTO> roles = new ArrayList<>();

    RoleDTO author = new RoleDTO();
    author.setRoletype("Author");
    author.setStartdate("2014-05-30");
    roles.add( author );

    return roles;
  }

  static private List<IndividualEntity> newIndividualEntities() {
    List<IndividualEntity> individualEntities = new ArrayList<>();

    for (int i = 1; i <=3; i++) {
      IndividualEntity individual = new IndividualEntity();
      individual.setNamedentityid(1);
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

  static private List<UniqueidentifierDTO> newUidsDto() {
    List<UniqueidentifierDTO> uids = new ArrayList<>();
    for (int i = 1; i <=2; i++) {
      uids.add(new UniqueidentifierDTO(null, 1, "ORCID", "0000-0002-9430-319"+i));
    }
    return uids;
  }
}
