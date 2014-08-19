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
import java.util.List;

public class NamedEntityServiceImpl implements NamedEntityService {

  @Inject private NamedEntityDBService nedDBSvc;

  public <T> T resolveValuesToIds(T t) {

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

    if (entity.getAddresstype() != null)
      entity.setAddresstypeid(findTypeValueByName(findTypeClassStartWith("Physical Address Types"), entity.getAddresstype()));

    if (entity.getCountrycodetype() != null)
      entity.setCountrycodetypeid(findTypeValueByName(findTypeClassStartWith("Country Types"), entity.getCountrycodetype()));

    if (entity.getStatecodetype() != null)
      entity.setStatecodetypeid(findTypeValueByName(findTypeClassStartWith("State and Province Codes"), entity.getStatecodetype()));

    return entity;
  }

  private PhonenumberEntity resolvePhonenumber(PhonenumberEntity entity) {

    if (entity.getPhonenumbertype() != null)
      entity.setPhonenumbertypeid(findTypeValueByName(findTypeClassStartWith("Telephone Number Types"), entity.getPhonenumbertype()));

    if (entity.getCountrycodetype() != null)
      entity.setCountrycodetypeid(findTypeValueByName(findTypeClassStartWith("Country Codes for Phone Numbers"), entity.getCountrycodetype()));

    return entity;
  }

  private EmailEntity resolveEmail(EmailEntity entity) {

    if (entity.getEmailtype() != null)
      entity.setEmailtypeid(findTypeValueByName(findTypeClassStartWith("Email Address Types"), entity.getEmailtype()));

    return entity;
  }

  private DegreeEntity resolveDegree(DegreeEntity entity) {

    if (entity.getDegreetype() != null)
      entity.setDegreetypeid(findTypeValueByName(findTypeClassStartWith("Degrees"), entity.getDegreetype()));

    return entity;
  }

  private RoleEntity resolveRole(RoleEntity entity) {

    if (entity.getSourceapplicationtype() != null)
      entity.setSourceapplicationtypeid(findTypeValueByName(findTypeClassStartWith("Source Applications"), entity.getSourceapplicationtype()));

    if (entity.getRoletype() != null)
      entity.setRoletypeid(findTypeValueByName(findTypeClassStartWith("Roles"), entity.getRoletype()));

    return entity;
  }

  private UniqueidentifierEntity resolveReference(UniqueidentifierEntity entity) {

    if (entity.getUniqueidentifiertype() != null)
      entity.setUniqueidentifiertypeid(findTypeValueByName(findTypeClassStartWith("Unique Identifier Types"), entity.getUniqueidentifiertype()));

    return entity;
  }

  @Override
  public IndividualComposite findIndividualComposite(Integer nedId) {

    IndividualComposite composite = new IndividualComposite();

    composite.setIndividual(findResolvedEntity(nedId, IndividualEntity.class));

    composite.setAddresses(findResolvedEntities(nedId, AddressEntity.class));

    composite.setPhonenumbers(findResolvedEntities(nedId, PhonenumberEntity.class));

    composite.setEmails(findResolvedEntities(nedId, EmailEntity.class));

    composite.setDegrees(findResolvedEntities(nedId, DegreeEntity.class));

    composite.setRoles(findResolvedEntities(nedId, RoleEntity.class));

    composite.setUniqueidentifiers(findResolvedEntities(nedId, UniqueidentifierEntity.class));

    return composite;
  }

  @Override @Transactional
  public IndividualComposite createIndividualComposite(IndividualComposite composite) {

    //TODO - better validation. handle null fields!

    IndividualEntity individual = composite.getIndividual();
    Integer nedId = nedDBSvc.create(resolveIndividual(individual));

    List<AddressEntity> addresses = composite.getAddresses();
    if (composite.getAddresses() != null) {
      for (AddressEntity address : addresses) {
        address.setNamedentityid(nedId);
        nedDBSvc.create(resolveAddress(address));
      }
    }
    List<PhonenumberEntity> phonenumbers = composite.getPhonenumbers();
    if (composite.getPhonenumbers() != null) {
      for (PhonenumberEntity phonenumber : phonenumbers) {
        phonenumber.setNamedentityid(nedId);
        nedDBSvc.create(resolvePhonenumber(phonenumber));
      }
    }

    List<EmailEntity> emails = composite.getEmails();
    if (composite.getEmails() != null) {
      for (EmailEntity email : emails) {
        email.setNamedentityid(nedId);
        nedDBSvc.create(resolveEmail(email));
      }
    }

    List<DegreeEntity> degrees = composite.getDegrees();
    if (degrees != null) {
      for (DegreeEntity degree : degrees) {
        degree.setNamedentityid(nedId);
        nedDBSvc.create(resolveDegree(degree));
      }
    }

    List<RoleEntity> roles = composite.getRoles();
    if (roles != null) {
      for (RoleEntity role : roles) {
        role.setNamedentityid(nedId);
        nedDBSvc.create(resolveRole(role));
      }
    }

    List<UniqueidentifierEntity> uids = composite.getUniqueidentifiers();
    if (composite.getUniqueidentifiers() != null) {
      for (UniqueidentifierEntity uid : uids) {
        uid.setNamedentityid(nedId);
        nedDBSvc.create(resolveReference(uid));
      }
    }

    return findIndividualComposite(nedId);
  }

  @Override
  public <T> T findResolvedEntity(Integer nedId, Class<T> clazz) {
    return ((NamedEntityQueries)nedDBSvc).findResolvedEntity(nedId, clazz);
  }

  @Override
  public <T> List<T> findResolvedEntities(Integer nedId, Class<T> clazz) {
    return ((NamedEntityQueries)nedDBSvc).findResolvedEntities(nedId, clazz);
  }

  @Override
  public <T> T findResolvedEntityByKey(Integer pk, Class<T> clazz) {
    return ((NamedEntityQueries)nedDBSvc).findResolvedEntityByKey(pk, clazz);
  }

  @Override
  public <T> List<T> findResolvedEntityByUid(String srcType, String uid, Class<T> clazz) {
    return ((NamedEntityQueries)nedDBSvc).findResolvedEntityByUid(srcType, uid, clazz);
  }

  @Override @Transactional
  public OrganizationEntity createOrganization(OrganizationEntity entity) {
    return ((NamedEntityQueries)nedDBSvc).findResolvedEntity(nedDBSvc.create(entity), OrganizationEntity.class);
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
