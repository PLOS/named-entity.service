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
package org.plos.namedentity.persist;

import org.plos.namedentity.api.entity.Entity;

import java.util.List;

public interface NamedEntityDBService {

  //TODO - replace typecode string with enum?
  Integer newNamedEntityId(String typeCode);

  <T> Integer create(T t);

  <T> boolean update(T t);

  <T> boolean delete(T t);

  <T> List<T> findAll(Class<T> clazz, Integer offset, Integer limit);

  <T> T findById(Integer id, Class<T> clazz);

  <T> List<T> findByAttribute(T t);

  <T extends Entity> T findResolvedEntityByUid(String srcType, String uid, Class<T> clazz);

  <T extends Entity> T findResolvedEntityByKey(Integer pk, Class<T> clazz);

  <T extends Entity> List<T> findResolvedEntities(Integer nedId, Class<T> clazz);

  /**
   * Finds type class for a type field in entity.
   *
   * @param typename - type field in entity pojo (attribute name)
   * @param t        - entity instance
   *
   * @return primary key of type class for type field in entity.
   */
  <T extends Entity> Integer findTypeClassByInspection(String typename, T t);

  Integer findTypeClass(String description);

  Integer findTypeValue(Integer typeClassId, String name);

  void checkNedIdForType(Integer nedId, String namedPartyType);

  <T extends Entity> void validate(T t);
}
