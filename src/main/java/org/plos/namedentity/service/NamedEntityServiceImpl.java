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
import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.plos.namedentity.persist.NamedEntityQueries;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class NamedEntityServiceImpl implements NamedEntityService {

  @Inject private NamedEntityDBService nedDBSvc;

  public <T> T resolveValues(T t) {

    if (t instanceof IndividualEntity)
      resolveIndividual((IndividualEntity) t);
    else if (t instanceof AddressEntity)
      resolveAddress((AddressEntity) t);
    else if (t instanceof PhonenumberEntity)
      resolvePhonenumber((PhonenumberEntity)t);
    else if (t instanceof EmailEntity)
      resolveEmail((EmailEntity)t);
    else if (t instanceof UniqueidentifierEntity)
      resolveReference((UniqueidentifierEntity)t);
    else if (t instanceof DegreeEntity)
      resolveDegree((DegreeEntity)t);
    else if (t instanceof RoleEntity)
      resolveRole((RoleEntity)t);
    else
      throw new UnsupportedOperationException("Can not resolve entity for " + t.getClass());

    return t;
  }

  private IndividualEntity resolveIndividual(IndividualEntity entity) {

    if (entity.getNameprefix() != null) {
      Integer prefixTypeClassId = findTypeClassStartWith("Named Party Prefixes");
      Integer prefixTypeId = findTypeValueByName(prefixTypeClassId, entity.getNameprefix());
      entity.setNameprefixtypeid(prefixTypeId);
    }

    if (entity.getNamesuffix() != null) {
      Integer suffixTypeClassId = findTypeClassStartWith("Named Party Suffixes");
      Integer suffixTypeId      = findTypeValueByName(suffixTypeClassId, entity.getNamesuffix());
      entity.setNamesuffixtypeid(suffixTypeId);
    }

    if (entity.getPreferredlanguage() != null) {
      Integer langTypeClassId = findTypeClassStartWith("Languages");
      Integer langTypeId      = findTypeValueByName(langTypeClassId, entity.getPreferredlanguage());
      entity.setPreferredlanguagetypeid(langTypeId);
    }

    if (entity.getPreferredcommunication() != null) {
      Integer commMethodsTypeClassId = findTypeClassStartWith("Communication Methods");
      Integer commMethodTypeId = findTypeValueByName(commMethodsTypeClassId, entity.getPreferredcommunication());
      entity.setPreferredcommunicationmethodtypeid(commMethodTypeId);
    }

    return entity;
  }

  private AddressEntity resolveAddress(AddressEntity entity) {

    Integer addressTypeId = findTypeValueByName(findTypeClassStartWith("Physical Address Types"), entity.getAddresstype());
    Integer countryTypeId = findTypeValueByName(findTypeClassStartWith("Country Types"), entity.getCountrycodetype());
    Integer stateCodeTypeId = findTypeValueByName(findTypeClassStartWith("State and Province Codes"), entity.getStatecodetype());

    entity.setAddresstypeid(addressTypeId);
    entity.setStatecodetypeid(stateCodeTypeId);
    entity.setCountrycodetypeid(countryTypeId);

    return entity;
  }

  private PhonenumberEntity resolvePhonenumber(PhonenumberEntity entity) {

    Integer phoneTypeId = findTypeValueByName(findTypeClassStartWith("Telephone Number Types"), entity.getPhonenumbertype());
    Integer countryCodeTypeId = findTypeValueByName(findTypeClassStartWith("Country Codes for Phone Numbers"), entity.getCountrycodetype());

    entity.setPhonenumbertypeid(phoneTypeId);
    entity.setCountrycodetypeid(countryCodeTypeId);

    return entity;
  }

  private EmailEntity resolveEmail(EmailEntity entity) {
    entity.setEmailtypeid(findTypeValueByName(findTypeClassStartWith("Email Address Types"), entity.getEmailtype()));
    return entity;
  }

  private DegreeEntity resolveDegree(DegreeEntity entity) {
    entity.setDegreetypeid(findTypeValueByName(findTypeClassStartWith("Degrees"), entity.getDegreetype()));

    return entity;
  }

  private RoleEntity resolveRole(RoleEntity entity) {
    Integer srcAppTypeId = findTypeValueByName(findTypeClassStartWith("Source Applications"), "Editorial Manager");
    Integer roleTypeId = findTypeValueByName(findTypeClassStartWith("Roles"), entity.getRoletype());

    entity.setSourceapplicationtypeid(srcAppTypeId);
    entity.setRoletypeid(roleTypeId);

    return entity;
  }

  private UniqueidentifierEntity resolveReference(UniqueidentifierEntity entity) {
    entity.setUniqueidentifiertypeid(findTypeValueByName(findTypeClassStartWith("Unique Identifier Types"), entity.getUniqueidentifiertype()));

    return entity;
  }

  @Override
  public IndividualComposite findIndividualComposite(Integer nedId) {

    IndividualComposite composite = new IndividualComposite();

    composite.setIndividual(resolveValues(findIndividualByNedId(nedId)));

    List <AddressEntity> addresses = findAddressesByNedId(nedId);
    for (AddressEntity address : addresses)
      resolveValues(address);
    composite.setAddresses(addresses);

    List<PhonenumberEntity> phonenumbers = findPhoneNumbersByNedId(nedId);
    for (PhonenumberEntity phonenumber : phonenumbers)
      resolveValues(phonenumber);
    composite.setPhonenumbers(phonenumbers);

    List<EmailEntity> emails = findEmailsByNedId(nedId);
    for (EmailEntity email : emails)
      resolveValues(email);
    composite.setEmails(emails);

    List<DegreeEntity> degrees = findDegreesByNedId(nedId);
    for (DegreeEntity degree : degrees)
      resolveValues(degree);
    composite.setDegrees(degrees);

    List<RoleEntity> roles = findRolesByNedId(nedId);
    for (RoleEntity role : roles)
      resolveValues(role);
    composite.setRoles(roles);

    List<UniqueidentifierEntity> references = findUniqueIdsByNedId(nedId);
    for (UniqueidentifierEntity uniqueidentifier : references)
      resolveValues(uniqueidentifier);
    composite.setUniqueidentifiers(references);

    return composite;
  }


  @Override @Transactional
  public IndividualComposite createIndividualComposite(IndividualComposite composite) {

    //TODO - better validation. handle null fields!

    IndividualEntity individual = composite.getIndividual();
    Integer nedId = nedDBSvc.create(resolveValues(individual));

    List<AddressEntity> addresses = composite.getAddresses();
    if (composite.getAddresses() != null) {
      for (AddressEntity address : addresses) {
        address.setNamedentityid(nedId);
        nedDBSvc.create(resolveValues(address));
      }
    }
    List<PhonenumberEntity> phonenumbers = composite.getPhonenumbers();
    if (composite.getPhonenumbers() != null) {
      for (PhonenumberEntity phonenumber : phonenumbers) {
        phonenumber.setNamedentityid(nedId);
        nedDBSvc.create(resolveValues(phonenumber));
      }
    }

    List<EmailEntity> emails = composite.getEmails();
    if (composite.getEmails() != null) {
      for (EmailEntity email : emails) {
        email.setNamedentityid(nedId);
        nedDBSvc.create(resolveValues(email));
      }
    }

    List<DegreeEntity> degrees = composite.getDegrees();
    if (degrees != null) {
      for (DegreeEntity degree : degrees) {
        degree.setNamedentityid(nedId);
        nedDBSvc.create(resolveValues(degree));
      }
    }

    List<RoleEntity> roles = composite.getRoles();
    if (roles != null) {
      for (RoleEntity role : roles) {
        role.setNamedentityid(nedId);
        role.setStartdate(new Timestamp(new Date().getTime()));
        nedDBSvc.create(resolveValues(role));
      }
    }

    List<UniqueidentifierEntity> uids = composite.getUniqueidentifiers();
    if (composite.getUniqueidentifiers() != null) {
      for (UniqueidentifierEntity uid : uids) {
        uid.setNamedentityid(nedId);
        nedDBSvc.create( resolveValues(uid) );
      }
    }

    return findIndividualComposite(nedId);
  }

  @Override
  public IndividualEntity findIndividualByNedId(Integer nedId) {
    return ((NamedEntityQueries)nedDBSvc).findIndividualByNedId(nedId);
  }

  @Override
  public List<IndividualEntity> findIndividualsByUid(String uidType, String uid) {
    Integer uidTypeId = findTypeValueByName(findTypeClassStartWith("Unique Identifier Types"), uidType);
    return ((NamedEntityQueries)nedDBSvc).findIndividualsByUid(uidTypeId, uid);
  }

  @Override @Transactional
  public OrganizationEntity createOrganization(OrganizationEntity entity) {
    return ((NamedEntityQueries)nedDBSvc).findOrganizationByNedId(nedDBSvc.create(entity));
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
  public List<DegreeEntity> findDegreesByNedId(Integer nedId) {
    return ((NamedEntityQueries)nedDBSvc).findDegreesByNedId(nedId);
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
