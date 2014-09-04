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
import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

public class NamedEntityServiceImpl implements NamedEntityService {

  @Inject private NamedEntityDBService nedDBSvc;

  public <T extends Entity> T resolveValuesToIds(T t) {

    if (t instanceof Individual)
      resolveIndividual((Individual) t);
    else if (t instanceof Address)
      resolveAddress((Address) t);
    else if (t instanceof Phonenumber)
      resolvePhonenumber((Phonenumber)t);
    else if (t instanceof Email)
      resolveEmail((Email)t);
    else if (t instanceof Uniqueidentifier)
      resolveReference((Uniqueidentifier)t);
    else if (t instanceof Degree)
      resolveDegree((Degree)t);
    else if (t instanceof Role)
      resolveRole((Role)t);
    else
      throw new UnsupportedOperationException("Can not resolve entity for " + t.getClass());

    return t;
  }

  private Individual resolveIndividual(Individual entity) {

    if (entity.getNameprefix() != null) {
      Integer prefixTypeClassId = nedDBSvc.findTypeClass("Named Party Prefixes");
      Integer prefixTypeId =nedDBSvc.findTypeValue(prefixTypeClassId, entity.getNameprefix());
      entity.setNameprefixtypeid(prefixTypeId);
    }

    if (entity.getNamesuffix() != null) {
      Integer suffixTypeClassId = nedDBSvc.findTypeClass("Named Party Suffixes");
      Integer suffixTypeId      = nedDBSvc.findTypeValue(suffixTypeClassId, entity.getNamesuffix());
      entity.setNamesuffixtypeid(suffixTypeId);
    }

    if (entity.getPreferredlanguage() != null) {
      Integer langTypeClassId = nedDBSvc.findTypeClass("Languages");
      Integer langTypeId      = nedDBSvc.findTypeValue(langTypeClassId, entity.getPreferredlanguage());
      entity.setPreferredlanguagetypeid(langTypeId);
    }

    if (entity.getPreferredcommunication() != null) {
      Integer commMethodsTypeClassId = nedDBSvc.findTypeClass("Communication Methods");
      Integer commMethodTypeId = nedDBSvc.findTypeValue(commMethodsTypeClassId, entity.getPreferredcommunication());
      entity.setPreferredcommunicationmethodtypeid(commMethodTypeId);
    }

    return entity;
  }

  private Address resolveAddress(Address entity) {

    if (entity.getAddresstype() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Physical Address Types"), entity.getAddresstype()));

    if (entity.getCountrycodetype() != null)
      entity.setCountrycodetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Country Types"), entity.getCountrycodetype()));

    if (entity.getStatecodetype() != null)
      entity.setStatecodetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("State and Province Codes"), entity.getStatecodetype()));

    return entity;
  }

  private Phonenumber resolvePhonenumber(Phonenumber entity) {

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Telephone Number Types"), entity.getType()));

    if (entity.getCountrycodetype() != null)
      entity.setCountrycodetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Country Codes for Phone Numbers"), entity.getCountrycodetype()));

    return entity;
  }

  private Email resolveEmail(Email entity) {

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Email Address Types"), entity.getType()));

    return entity;
  }

  private Degree resolveDegree(Degree entity) {

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Degrees"), entity.getType()));

    return entity;
  }

  private Role resolveRole(Role entity) {

    if (entity.getSourceapplicationtype() != null)
      entity.setSourceapplicationtypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Source Applications"), entity.getSourceapplicationtype()));

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Roles"), entity.getType()));

    return entity;
  }

  private Uniqueidentifier resolveReference(Uniqueidentifier entity) {

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Unique Identifier Types"), entity.getType()));

    return entity;
  }

  @Override
  public IndividualComposite findIndividualComposite(Integer nedId) {

    IndividualComposite composite = new IndividualComposite();

    composite.setIndividual(findResolvedEntity(nedId, Individual.class));

    composite.setAddresses(findResolvedEntities(nedId, Address.class));

    composite.setPhonenumbers(findResolvedEntities(nedId, Phonenumber.class));

    composite.setEmails(findResolvedEntities(nedId, Email.class));

    composite.setDegrees(findResolvedEntities(nedId, Degree.class));

    composite.setUrls(findResolvedEntities(nedId, Url.class));

    composite.setRoles(findResolvedEntities(nedId, Role.class));

    composite.setUniqueidentifiers(findResolvedEntities(nedId, Uniqueidentifier.class));

    return composite;
  }

  @Override @Transactional
  public IndividualComposite createIndividualComposite(IndividualComposite composite) {

    //TODO - better validation. handle null fields!

    Individual individual = composite.getIndividual();
    Integer nedId = nedDBSvc.create(resolveIndividual(individual));

    List<Address> addresses = composite.getAddresses();
    if (composite.getAddresses() != null) {
      for (Address address : addresses) {
        address.setNedid(nedId);
        nedDBSvc.create(resolveAddress(address));
      }
    }
    List<Phonenumber> phonenumbers = composite.getPhonenumbers();
    if (composite.getPhonenumbers() != null) {
      for (Phonenumber phonenumber : phonenumbers) {
        phonenumber.setNedid(nedId);
        nedDBSvc.create(resolvePhonenumber(phonenumber));
      }
    }

    List<Email> emails = composite.getEmails();
    if (composite.getEmails() != null) {
      for (Email email : emails) {
        email.setNedid(nedId);
        nedDBSvc.create(resolveEmail(email));
      }
    }

    List<Degree> degrees = composite.getDegrees();
    if (degrees != null) {
      for (Degree degree : degrees) {
        degree.setNedid(nedId);
        nedDBSvc.create(resolveDegree(degree));
      }
    }

    List<Url> urls = composite.getUrls();
    if (urls != null) {
      for (Url url : urls) {
        url.setNedid(nedId);
        nedDBSvc.create(url);
      }
    }

    List<Role> roles = composite.getRoles();
    if (roles != null) {
      for (Role role : roles) {
        role.setNedid(nedId);
        nedDBSvc.create(resolveRole(role));
      }
    }

    List<Uniqueidentifier> uids = composite.getUniqueidentifiers();
    if (composite.getUniqueidentifiers() != null) {
      for (Uniqueidentifier uid : uids) {
        uid.setNedid(nedId);
        nedDBSvc.create(resolveReference(uid));
      }
    }

    return findIndividualComposite(nedId);
  }

  @Override
  public <T extends Entity> T findResolvedEntity(Integer nedId, Class<T> clazz) {
    return nedDBSvc.findResolvedEntity(nedId, clazz);
  }

  @Override
  public <T extends Entity> List<T> findResolvedEntities(Integer nedId, Class<T> clazz) {
    return nedDBSvc.findResolvedEntities(nedId, clazz);
  }

  @Override
  public <T extends Entity> T findResolvedEntityByKey(Integer pk, Class<T> clazz) {
    return nedDBSvc.findResolvedEntityByKey(pk, clazz);
  }

  @Override
  public <T extends Entity> List<T> findResolvedEntityByUid(String srcType, String uid, Class<T> clazz) {
    return nedDBSvc.findResolvedEntityByUid(srcType, uid, clazz);
  }

  @Override @Transactional
  public Organization createOrganization(Organization entity) {
    return nedDBSvc.findResolvedEntity(nedDBSvc.create(entity), Organization.class);
  }
    
  public NamedEntityDBService getNamedEntityDBService() {
    return nedDBSvc;
  }
    
  public void setNamedEntityDBService(NamedEntityDBService nedDBSvc) {
    this.nedDBSvc = nedDBSvc;
  }
}
