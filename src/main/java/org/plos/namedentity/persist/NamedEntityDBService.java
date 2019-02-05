/*
 * Copyright (c) 2014-2019 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package org.plos.namedentity.persist;

import org.plos.namedentity.api.entity.Alert;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.enums.NamedPartyEnum;

import java.util.List;

public interface NamedEntityDBService {

  Integer newNamedEntityId(NamedPartyEnum typeCode);

  Integer newNamedEntityId(NamedPartyEnum typeCode, Integer ambraId);

  List<Alert> getAlerts(String frequency);

  <T> Integer create(T t);

  <T> boolean update(T t);

  <T> boolean delete(T t);

  <T> List<T> findAll(Class<T> clazz, Integer offset, Integer limit);

  <T> T findById(Integer id, Class<T> clazz);

  <T> List<T> findByAttribute(T t, Boolean partial);

  <T extends Entity> T findResolvedEntityByUid(String srcType, String uid, Class<T> clazz);

  <T extends Entity> T findResolvedEntityByKey(Integer pk, Class<T> clazz);

  <T extends Entity> List<T> findResolvedEntities(Integer nedId, Class<T> clazz);

  Integer countGlobalTypes();

  Integer countConsumers();

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
