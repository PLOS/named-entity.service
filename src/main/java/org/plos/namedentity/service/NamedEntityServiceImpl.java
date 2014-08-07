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
package org.plos.namedentity.service;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.AddressEntity;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.OrganizationEntity;
import org.plos.namedentity.api.entity.PhonenumberEntity;
import org.plos.namedentity.api.entity.RoleEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;
import org.plos.namedentity.api.entity.UniqueidentifierEntity;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.plos.namedentity.persist.NamedEntityQueries;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class NamedEntityServiceImpl implements NamedEntityService {

  @Inject private NamedEntityDBService nedDBSvc;

  @Override @Transactional
  public IndividualEntity createIndividual(IndividualComposite composite) {
    //TODO - better validation. handle null fields!

    Integer nedId = nedDBSvc.newNamedEntityId("Individual");

    /* ------------------------------------------------------------------ */
    /*  INDIVIDUAL                                                        */
    /* ------------------------------------------------------------------ */

    IndividualEntity individual = new IndividualEntity();
    individual.setNamedentityid(nedId);
    individual.setFirstname(composite.getFirstname());
    individual.setMiddlename(composite.getMiddlename());
    individual.setLastname(composite.getLastname());

    if (composite.getNameprefix() != null) {
      Integer prefixTypeClassId = findTypeClassStartWith("Named Party Prefixes");
      Integer prefixTypeId      = findTypeValueByName(prefixTypeClassId, composite.getNameprefix());
      individual.setNameprefixtypeid(prefixTypeId);
    }

    if (composite.getNamesuffix() != null) {
      Integer suffixTypeClassId = findTypeClassStartWith("Named Party Suffixes");
      Integer suffixTypeId      = findTypeValueByName(suffixTypeClassId, composite.getNamesuffix());
      individual.setNamesuffixtypeid(suffixTypeId);
    }

    if (composite.getPreferredlanguage() != null) {
      Integer langTypeClassId = findTypeClassStartWith("Languages");
      Integer langTypeId      = findTypeValueByName(langTypeClassId, composite.getPreferredlanguage());
      individual.setPreferredlanguagetypeid(langTypeId);
    }

    if (composite.getPreferredcommunication() != null) {
      Integer commMethodsTypeClassId = findTypeClassStartWith("Communication Methods");
      Integer commMethodTypeId = findTypeValueByName(commMethodsTypeClassId, composite.getPreferredcommunication());
      individual.setPreferredcommunicationmethodtypeid(commMethodTypeId);
    }

    nedDBSvc.create( individual );

    /* ------------------------------------------------------------------ */
    /*  ADDRESSES                                                         */
    /* ------------------------------------------------------------------ */

    Integer addressTypeClassId   = findTypeClassStartWith("Physical Address Types");
    Integer countryTypeClassId   = findTypeClassStartWith("Country Types");
    Integer stateCodeTypeClassId = findTypeClassStartWith("State and Province Codes");

    //TODO - move to transformer
    if (composite.getAddresses() != null) {
      for (AddressEntity address : composite.getAddresses()) {
        Integer addressTypeId   = findTypeValueByName(addressTypeClassId, address.getAddresstype());
        Integer countryTypeId   = findTypeValueByName(countryTypeClassId, address.getCountrycodetype());
        Integer stateCodeTypeId = findTypeValueByName(stateCodeTypeClassId, address.getStatecodetype());

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setNamedentityid(nedId);
        addressEntity.setAddresstypeid(addressTypeId);
        addressEntity.setAddressline1(address.getAddressline1());
        addressEntity.setAddressline2(address.getAddressline2());
        addressEntity.setAddressline3(address.getAddressline3());
        addressEntity.setCity(address.getCity());
        addressEntity.setStatecodetypeid(stateCodeTypeId);
        addressEntity.setCountrycodetypeid(countryTypeId);
        addressEntity.setPostalcode(address.getPostalcode());
        addressEntity.setIsprimary(1 == address.getIsprimary() ? (byte)1 : (byte)0);

        nedDBSvc.create( addressEntity );
      }
    }

    /* ------------------------------------------------------------------ */
    /*  PHONE NUMBERS                                                     */
    /* ------------------------------------------------------------------ */

    Integer phoneTypeClassId       = findTypeClassStartWith("Telephone Number Types");
    Integer countryCodeTypeClassId = findTypeClassStartWith("Country Codes for Phone Numbers");

    if (composite.getPhonenumbers() != null) {
      for (PhonenumberEntity phonenumber : composite.getPhonenumbers()) {
        Integer phoneTypeId       = findTypeValueByName(phoneTypeClassId, phonenumber.getPhonenumbertype());
        Integer countryCodeTypeId = findTypeValueByName(countryCodeTypeClassId, phonenumber.getCountrycodetype());

        PhonenumberEntity phoneEntity = new PhonenumberEntity();
        phoneEntity.setNamedentityid(nedId);
        phoneEntity.setPhonenumbertypeid(phoneTypeId);
        phoneEntity.setCountrycodetypeid(countryCodeTypeId);
        phoneEntity.setPhonenumber(phonenumber.getPhonenumber());
        phoneEntity.setExtension(phonenumber.getExtension());
        phoneEntity.setIsprimary(phonenumber.getIsprimary());

        nedDBSvc.create( phoneEntity );
      }
    }

    /* ------------------------------------------------------------------ */
    /*  EMAILS                                                            */
    /* ------------------------------------------------------------------ */

    Integer emailTypeClassId = findTypeClassStartWith("Email Address Types");

    if (composite.getEmails() != null) {
      for (EmailEntity email : composite.getEmails()) {
        Integer emailTypeId = findTypeValueByName(emailTypeClassId, email.getEmailtype());

        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setNamedentityid(nedId);
        emailEntity.setEmailtypeid(emailTypeId);
        emailEntity.setEmailaddress(email.getEmailaddress());
        emailEntity.setIsprimary(1 == email.getIsprimary() ? (byte)1 : (byte)0);

        nedDBSvc.create( emailEntity );
      }
    }

    /* ------------------------------------------------------------------ */
    /*  ROLE                                                              */
    /* ------------------------------------------------------------------ */

    Integer srcAppTypeClassId = findTypeClassStartWith("Source Applications");
    Integer roleTypeClassId   = findTypeClassStartWith("Roles");

    RoleEntity role = composite.getRole();
    if (role != null) {
      Integer srcAppTypeId = findTypeValueByName(srcAppTypeClassId, "Editorial Manager");
      Integer roleTypeId   = findTypeValueByName(roleTypeClassId, role.getRoletype());

      RoleEntity roleEntity = new RoleEntity();
      roleEntity.setNamedentityid(nedId);
      roleEntity.setSourceapplicationtypeid(srcAppTypeId);
      roleEntity.setRoletypeid(roleTypeId);
      roleEntity.setStartdate(new Timestamp(new Date().getTime()));

      nedDBSvc.create( roleEntity );
    }

    /* ------------------------------------------------------------------ */
    /*  EXTERNAL REFERENCES                                               */
    /* ------------------------------------------------------------------ */

    Integer uidTypeClassId = findTypeClassStartWith("Unique Identifier Types");

    if (composite.getUniqueidentifiers() != null) {
      for (UniqueidentifierEntity uidEntity : composite.getUniqueidentifiers()) {
        Integer uidTypeId = findTypeValueByName(uidTypeClassId, uidEntity.getUniqueidentifiertype());

        UniqueidentifierEntity uid = new UniqueidentifierEntity();
        uid.setNamedentityid(nedId);
        uid.setUniqueidentifiertypeid(uidTypeId);
        uid.setUniqueidentifier(uidEntity.getUniqueidentifier());

        nedDBSvc.create( uid );
      }
    }

    return ((NamedEntityQueries)nedDBSvc).findIndividualByNedId(nedId);
    //return nedDBSvc.findById(nedId, IndividualEntity.class);
  }

  @Override
  public IndividualEntity findIndividualByNedId(Integer nedId) {
    return ((NamedEntityQueries)nedDBSvc).findIndividualByNedId(nedId);
  }

  @Override
  public List<IndividualEntity> findIndividualsByUid(Integer srcTypeId, String uid) {
    return ((NamedEntityQueries)nedDBSvc).findIndividualsByUid(srcTypeId, uid);
  }

  @Override @Transactional
  public OrganizationEntity createOrganization(OrganizationEntity entity) {
    Integer nedId = nedDBSvc.newNamedEntityId("Organization");
    entity.setNamedentityid(nedId);
    nedDBSvc.create(entity);
    return ((NamedEntityQueries)nedDBSvc).findOrganizationByNedId(nedId);
  }

  @Override
  public OrganizationEntity findOrganizationByNedId(Integer nedId) {
    return ((NamedEntityQueries)nedDBSvc).findOrganizationByNedId(nedId);
  }


  @Override
  public List<AddressEntity> findAddressesByNedId(Integer nedId) {
    return ((NamedEntityQueries)nedDBSvc).findAddressesByNedId(nedId);
  }

  @Override
  public List<EmailEntity> findEmailsByNedId(Integer nedId) {
    return ((NamedEntityQueries)nedDBSvc).findEmailsByNedId(nedId);
  }

  @Override
  public List<PhonenumberEntity> findPhoneNumbersByNedId(Integer nedId) {
    return ((NamedEntityQueries)nedDBSvc).findPhoneNumbersByNedId(nedId);
  }

  @Override
  public List<RoleEntity> findRolesByNedId(Integer nedId) {
    return ((NamedEntityQueries)nedDBSvc).findRolesByNedId(nedId);
  }

  @Override
  public List<UniqueidentifierEntity> findUniqueIdsByNedId(Integer nedId) {
    return ((NamedEntityQueries)nedDBSvc).findUniqueIdsByNedId(nedId);
  }
    
  public NamedEntityDBService getNamedEntityDBService() {
    return nedDBSvc;
  }
    
  public void setNamedEntityDBService(NamedEntityDBService nedDBSvc) {
    this.nedDBSvc = nedDBSvc;
  }

  //TODO - cache type classes and values.
   
  private Integer findTypeClassStartWith(String prefix) {
    for(TypedescriptionEntity typeClass : nedDBSvc.findAll(TypedescriptionEntity.class)) {
      if (typeClass.getDescription().startsWith(prefix)) {
        return typeClass.getTypeid();
      }
    }
    throw new NedValidationException("No type class found which begins with " + prefix);
  }

  private Integer findTypeValueByName(Integer typeClassId, String name) {
    for(GlobaltypeEntity typeValue : nedDBSvc.findAll(GlobaltypeEntity.class)) {
      if (typeClassId.equals(typeValue.getTypeid()) &&
          typeValue.getShortdescription().equalsIgnoreCase(name))
      {
        return typeValue.getGlobaltypeid();
      }
    }
    throw new NedValidationException("No type value found with short description =  " + name);
  }
}
