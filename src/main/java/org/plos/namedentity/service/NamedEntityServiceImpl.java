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

import org.plos.namedentity.api.EntityNotFoundException;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Degree;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.Individual;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.api.entity.Url;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

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
    else if (t instanceof Url)
      resolveUrl((Url)t);
    else
      throw new UnsupportedOperationException("Can not resolve entity for " + t.getClass());

    return t;
  }

  private Individual resolveIndividual(Individual entity) {

    if (entity.getSource() != null)
      entity.setSourcetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Source Applications"), entity.getSource()));

    if (entity.getNameprefix() != null) {
      Integer prefixTypeClassId = nedDBSvc.findTypeClass("Named Party Prefixes");
      Integer prefixTypeId = nedDBSvc.findTypeValue(prefixTypeClassId, entity.getNameprefix());
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

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Physical Address Types"), entity.getType()));

    if (entity.getSource() != null)
      entity.setSourcetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Source Applications"), entity.getSource()));

    if (entity.getCountrycodetype() != null)
      entity.setCountrycodetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Country Types"), entity.getCountrycodetype()));

    if (entity.getStatecodetype() != null)
      entity.setStatecodetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("State and Province Codes"), entity.getStatecodetype()));

    return entity;
  }

  private Phonenumber resolvePhonenumber(Phonenumber entity) {

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Telephone Number Types"), entity.getType()));

    if (entity.getSource() != null)
      entity.setSourcetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Source Applications"), entity.getSource()));

    if (entity.getCountrycodetype() != null)
      entity.setCountrycodetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Country Codes for Phone Numbers"), entity.getCountrycodetype()));

    return entity;
  }

  private Email resolveEmail(Email entity) {

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Email Address Types"), entity.getType()));

    if (entity.getSource() != null)
      entity.setSourcetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Source Applications"), entity.getSource()));

    return entity;
  }

  private Degree resolveDegree(Degree entity) {

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Degrees"), entity.getType()));

    if (entity.getSource() != null)
      entity.setSourcetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Source Applications"), entity.getSource()));

    return entity;
  }

  private Url resolveUrl(Url entity) {

    if (entity.getSource() != null)
      entity.setSourcetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Source Applications"), entity.getSource()));

    return entity;
  }

  private Role resolveRole(Role entity) {

    if (entity.getApplicationtype() != null)
      entity.setApplicationtypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("User Applications"), entity.getApplicationtype()));

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Roles"), entity.getType()));

    if (entity.getSource() != null)
      entity.setSourcetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Source Applications"), entity.getSource()));

    return entity;
  }

  private Uniqueidentifier resolveReference(Uniqueidentifier entity) {

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Unique Identifier Types"), entity.getType()));

    if (entity.getSource() != null)
      entity.setSourcetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Source Applications"), entity.getSource()));

    return entity;
  }

  @SuppressWarnings("unchecked")
  @Override
  public IndividualComposite findIndividualComposite(Integer nedId) {

    IndividualComposite composite = new IndividualComposite();

    Map<Class, List<? extends Entity>> compositeMap = composite.getAsMap();

    for (Class entityType : compositeMap.keySet())
      compositeMap.put(entityType, findResolvedEntities(nedId, entityType));

    composite.setFromMap(compositeMap);

    if (composite.getIndividuals().size() == 0)
      throw new EntityNotFoundException("Individual not found");

    return composite;
  }

  @Override @Transactional
  public IndividualComposite createIndividualComposite(IndividualComposite composite) {

    Integer nedId = nedDBSvc.newNamedEntityId("Individual");

    Map<Class, List<? extends Entity>> compositeMap = composite.getAsMap();

    for (List<? extends Entity> entities : compositeMap.values()) {

      if (entities != null) {
        for (Entity entity : entities) {
          entity.setNedid(nedId);
          nedDBSvc.create(resolveValuesToIds(entity));
        }
      }

    }

    return findIndividualComposite(nedId);
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
  public <T extends Entity> T findResolvedEntityByUid(String srcType, String uid, Class<T> clazz) {
    return nedDBSvc.findResolvedEntityByUid(srcType, uid, clazz);
  }
    
  public NamedEntityDBService getNamedEntityDBService() {
    return nedDBSvc;
  }
    
  public void setNamedEntityDBService(NamedEntityDBService nedDBSvc) {
    this.nedDBSvc = nedDBSvc;
  }
}
