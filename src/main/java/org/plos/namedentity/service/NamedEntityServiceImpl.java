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

import org.apache.log4j.Logger;
import org.plos.namedentity.api.Consumer;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.*;
import org.plos.namedentity.api.enums.UidTypeEnum;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static org.plos.namedentity.api.NedException.ErrorType.ServerError;
import static org.plos.namedentity.api.entity.Individualprofile.DISPLAYNAME_MAX_LENGTH;

public class NamedEntityServiceImpl implements NamedEntityService {

  private static Logger logger = Logger.getLogger(NamedEntityServiceImpl.class);

  @Inject private NamedEntityDBService nedDBSvc;


  public <T extends Entity> T resolveValuesToIds(T t) {

    resolveCreatedAndLastmodifiedBy(t);

    if (t instanceof Individualprofile)
      resolveProfile((Individualprofile) t);
    else if (t instanceof Organization)
      resolveOrganization((Organization) t);
    else if (t instanceof Address)
      resolveAddress((Address) t);
    else if (t instanceof Phonenumber)
      resolvePhonenumber((Phonenumber) t);
    else if (t instanceof Email)
      resolveEmail((Email) t);
    else if (t instanceof Uniqueidentifier)
      resolveReference((Uniqueidentifier) t);
    else if (t instanceof Degree)
      resolveDegree((Degree) t);
    else if (t instanceof Group)
      resolveGroup((Group) t);
    else if (t instanceof Url)
      resolveUrl((Url) t);
    else if (t instanceof Auth)
      resolveAuth((Auth) t);
    else if (t instanceof Relationship)
      resolveIndividualRelationship((Relationship) t);
    else
      throw new UnsupportedOperationException("Can not resolve entity for " + t.getClass());

    return t;
  }

  private <T extends Entity> void resolveCreatedAndLastmodifiedBy(T entity) {
    if (entity.getCreatedbyname() != null) {
      entity.setCreatedby( findAppuserId(entity.getCreatedbyname()) );
    }
    if (entity.getLastmodifiedbyname() != null) {
      entity.setLastmodifiedby( findAppuserId(entity.getLastmodifiedbyname()) );
    }
  }

  private Individualprofile resolveProfile(Individualprofile entity) {

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

    return entity;
  }

  public String generateDisplayname(Individualprofile entity, Random rand) {

    final int MAX_TRIES = 100;
    final int MIN_VAL   = 100;
    final int MAX_VAL   = 999;

    // (max displayname length) - (uuid length) - (initial of firstname)
    final int MAX_LNAME_LEN = (DISPLAYNAME_MAX_LENGTH - 32 - 1);

    try {
      entity.validateFirstname() ; entity.validateLastname() ;

      // base name = first char of firstname + lastname (possibly truncated)
      StringBuilder basename = new StringBuilder();
      basename.append( Character.toLowerCase(entity.getFirstname().charAt(0)) );

      String lname = entity.getLastname().toLowerCase();
      basename.append( lname.length() > MAX_LNAME_LEN ? lname.substring(0,MAX_LNAME_LEN) : lname );

      int count = 0;

      while (true) {
        StringBuilder displayname = new StringBuilder();
        displayname.append( basename );

        if (count > 0) {
          // nextInt is exclusive at top end, so add 1 to make it inclusive
          displayname.append( rand.nextInt((MAX_VAL-MIN_VAL)+1)+MIN_VAL );
        }

        Individualprofile profileCriteria = new Individualprofile();
        profileCriteria.setDisplayname( displayname.toString() );
        List<Individualprofile> profilesResult = nedDBSvc.findByAttribute(profileCriteria);
        if (profilesResult.size() == 0) {
          return displayname.toString();   // displayname is available
        }

        if (++count == MAX_TRIES) {

          // we've exhausted generation attempts with random number. fall back
          // to initials plus uuid (w/o dashes).

          displayname.setLength(0);
          displayname.append( basename ).append("-");
          displayname.append( UUID.randomUUID().toString().replaceAll("-","") );

          logger.warn(String.format("Exhausted displayname generation with random " +
            "number for firstname:%s lastname:%s. Generating uuid-variant: %s", 
              entity.getFirstname(), entity.getLastname(), displayname.toString()));

          return displayname.toString();
        }
      }
    }
    catch (NedException e) {
      // name validation failed. abort generation.
    }
    return null;
  }

  private Organization resolveOrganization(Organization entity) {

    if (entity.getSource() != null)
      entity.setSourcetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Source Applications"), entity.getSource()));

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Organization Types"), entity.getType()));

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

  private Relationship resolveIndividualRelationship(Relationship entity) {

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Relationship Types"), entity.getType()));

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

  private Auth resolveAuth(Auth entity) {

    if (entity.getEmail() != null) {
      Email searchCriteria = new Email();
      searchCriteria.setEmailaddress( entity.getEmail() );

      searchCriteria.setSourcetypeid(nedDBSvc.findTypeValue(
        nedDBSvc.findTypeClass("Source Applications"), UidTypeEnum.AMBRA.getName()));

      List<Email> searchResult = nedDBSvc.findByAttribute(searchCriteria);
      
      if (searchResult.size() != 1) {
        throw new NedException(ServerError, String.format(
          "Expected to find one email for %s. Found %d.", 
            entity.getEmail(), searchResult.size()));
      }
      entity.setEmailid( searchResult.get(0).getId() );
    }
    return entity;
  }

  private Group resolveGroup(Group entity) {

    if (entity.getApplicationtype() != null)
      entity.setApplicationtypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("User Applications"), entity.getApplicationtype()));

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Groups"), entity.getType()));

    if (entity.getSource() != null)
      entity.setSourcetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Source Applications"), entity.getSource()));

    return entity;
  }

  private Uniqueidentifier resolveReference(Uniqueidentifier entity) {

    if (entity.getType() != null)
      entity.setTypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClassByInspection("type", entity), entity.getType()));

    if (entity.getSource() != null)
      entity.setSourcetypeid(nedDBSvc.findTypeValue(nedDBSvc.findTypeClass("Source Applications"), entity.getSource()));

    return entity;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T extends Composite> T findComposite(Integer nedId, Class<T> clazz) {

    Constructor<?> ctor = clazz.getConstructors()[0];
    try {
      T composite = (T)ctor.newInstance();

      Map<Class, List<? extends Entity>> compositeMap = composite.getAsMap();

      for (Class entityType : compositeMap.keySet())
        compositeMap.put(entityType, findResolvedEntities(nedId, entityType));

      composite.setFromMap(compositeMap);

      return composite;

    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new NedException(ServerError, String.format(
        "findComposite(): unable to assemble composite (nedid=%d, %s)", nedId, clazz.getSimpleName()));
    }
  }

  @Override @Transactional
  public  <T extends Composite> T createComposite(T composite, Class<T> clazz) {

    Integer nedId = nedDBSvc.newNamedEntityId(composite.getTypeName());

    Map<Class, List<? extends Entity>> compositeMap = composite.getAsMap();

    for (List<? extends Entity> entities : compositeMap.values()) {
      if (entities != null) {
        for (Entity entity : entities) {
          entity.setNedid(nedId);
          nedDBSvc.create(resolveValuesToIds(entity));
        }
      }
    }

    return findComposite(nedId, clazz);
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

  @Override
  public void checkNedIdForType(Integer nedId, String namedPartyType) {
    nedDBSvc.checkNedIdForType(nedId, namedPartyType);
  }
    
  public NamedEntityDBService getNamedEntityDBService() {
    return nedDBSvc;
  }
    
  public void setNamedEntityDBService(NamedEntityDBService nedDBSvc) {
    this.nedDBSvc = nedDBSvc;
  }

  private Integer findAppuserId(String username) {
    //TODO: cache app users in memory.
    Consumer filter = new Consumer();
    filter.setName(username);
    List<Consumer> consumers = nedDBSvc.findByAttribute(filter);
    if (consumers.size() == 0) {
      throw new NedException(ServerError, "Unknown application user: "+username);
    }
    return consumers.get(0).getId();
  }
}
