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
import java.util.HashMap;
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

  @Override
  public IndividualComposite findIndividualComposite(Integer nedId) {

    IndividualComposite composite = new IndividualComposite();

    composite.setIndividuals(findResolvedEntities(nedId, Individual.class));
    composite.setAddresses(findResolvedEntities(nedId, Address.class));
    composite.setPhonenumbers(findResolvedEntities(nedId, Phonenumber.class));
    composite.setEmails(findResolvedEntities(nedId, Email.class));
    composite.setDegrees(findResolvedEntities(nedId, Degree.class));
    composite.setUrls(findResolvedEntities(nedId, Url.class));
    composite.setRoles(findResolvedEntities(nedId, Role.class));
    composite.setUniqueidentifiers(findResolvedEntities(nedId, Uniqueidentifier.class));

    return composite;
  }

  /**
   * Pushes the changes from newEntities to oldEntities. In the end, for each source
   * oldEntities should be the same when this function is done as newEntities was
   * when the function started.
   *
   * @param newEntities
   * @param oldEntities
   * @param nedId
   * @param <T>
   */
  private <T extends Entity> void syncEntities(List<T> newEntities, List<T> oldEntities, Integer nedId) {

    // set the nedIds since they are needed at insertion time
    for (Entity entity : newEntities)
      entity.setNedid(nedId);

    // map: source-> map of entity checksum to pointer to entities
    Map<Integer, Map<Integer, Integer>> newEntityMap = new HashMap<>();
    Map<Integer, Map<Integer, Integer>> oldEntityMap = new HashMap<>();

    // populate the maps
    for (int p = 0; p<newEntities.size(); p++) {

      T entity = newEntities.get(p);
      Integer source = entity.getSourcetypeid();

      if (!oldEntityMap.containsKey(source))
        oldEntityMap.put(source, new HashMap<Integer, Integer>());

      oldEntityMap.get(source).put(entity.hashCode(), p);
    }

    for (int p = 0; p<newEntities.size(); p++) {

      T entity = newEntities.get(p);
      Integer source = entity.getSourcetypeid();

      if (!newEntityMap.containsKey(source))
        newEntityMap.put(source, new HashMap<Integer, Integer>());

      newEntityMap.get(source).put(entity.hashCode(), p);
    }

    // step through every source in the new dataset
    for (Integer sourceId : newEntityMap.keySet()) {

      Map<Integer, Integer> newChecksums = newEntityMap.get(sourceId);

      // handle new sources as inserts
      if (!oldEntityMap.containsKey(sourceId)) {

        for (Map.Entry<Integer, Integer> n : newChecksums.entrySet())
          nedDBSvc.create(resolveValuesToIds(newEntities.get(n.getValue())));

        newEntityMap.remove(sourceId);

      } else { // for existing sources, compare the lists

        Map<Integer, Integer> oldChecksums = oldEntityMap.get(sourceId);

        for (Map.Entry<Integer, Integer> n : newChecksums.entrySet()) {

          // if the entries match, do nothing
          if (oldChecksums.containsKey(n.getKey()))
            oldChecksums.remove(n.getKey());
          else // else insert
            nedDBSvc.create(resolveValuesToIds(newEntities.get(n.getValue())));

          newChecksums.remove(n.getKey());    // can I do this in the loop?
        }

        // items left in the old list should now be deleted
        for (Map.Entry<Integer, Integer> o : newChecksums.entrySet())
          nedDBSvc.delete(newEntities.get(o.getValue()));

      }

    }
  }

  public IndividualComposite updateFrom(IndividualComposite newComp, Integer nedId) {

    // TODO: handle various match criteria (ie - email)

    IndividualComposite origComp = findIndividualComposite(nedId);

    syncEntities(newComp.getIndividuals(), origComp.getIndividuals(), nedId);
    syncEntities(newComp.getRoles(), origComp.getRoles(), nedId);
    syncEntities(newComp.getAddresses(), origComp.getAddresses(), nedId);
    syncEntities(newComp.getEmails(), origComp.getEmails(), nedId);
    syncEntities(newComp.getPhonenumbers(), origComp.getPhonenumbers(), nedId);
    syncEntities(newComp.getUniqueidentifiers(), origComp.getUniqueidentifiers(), nedId);
    syncEntities(newComp.getDegrees(), origComp.getDegrees(), nedId);
    syncEntities(newComp.getUrls(), origComp.getUrls(), nedId);

    return findIndividualComposite(nedId);
  }

  private <T extends Entity> void addToEntities(List<T> entities, Integer nedId) {

    if (entities != null) {
      for (Entity entity : entities) {
        entity.setNedid(nedId);
        nedDBSvc.create(resolveValuesToIds(entity));
      }
    }
  }

  @Override @Transactional
  public IndividualComposite addToComposite(IndividualComposite composite, Integer nedId) {

    if (nedId == null)
      nedId = nedDBSvc.newNamedEntityId("Individual");

    addToEntities(composite.getIndividuals(), nedId);
    addToEntities(composite.getAddresses(), nedId);
    addToEntities(composite.getPhonenumbers(), nedId);
    addToEntities(composite.getEmails(), nedId);
    addToEntities(composite.getDegrees(), nedId);
    addToEntities(composite.getUrls(), nedId);
    addToEntities(composite.getRoles(), nedId);
    addToEntities(composite.getUniqueidentifiers(), nedId);

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
