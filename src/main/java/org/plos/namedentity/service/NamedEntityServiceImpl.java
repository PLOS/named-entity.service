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

import java.util.List;

import javax.inject.Inject;

import org.plos.namedentity.api.NedException;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.springframework.transaction.annotation.Transactional;

public class NamedEntityServiceImpl implements NamedEntityService {

  @Inject private NamedEntityDBService namedEntityDBService; 

  @Override @Transactional
  public <T> Integer create(T t) {
    return namedEntityDBService.create(t);
  }

  @Override @Transactional
  public <T> boolean update(T t) {
    return namedEntityDBService.update(t);
  }

  @Override @Transactional
  public <T> boolean delete(T t) {
    return namedEntityDBService.delete(t);
  }

  @Override
  public <T> T findById(Integer id, Class<T> clazz) {
    T t = namedEntityDBService.findById(id, clazz);
    if (t == null) {
      throw new NedException(String.format(
        "Record not found searching by id (%s)", t.getClass().getName()));
    }
    return t;
  }

  @Override
  public <T> List<T> findAll(Class<T> clazz) {
    return namedEntityDBService.findAll(clazz);
  }

  @Override
  public <T> List<T> findByAttribute(T t) {
    return namedEntityDBService.findByAttribute(t);
  }
  
  public NamedEntityDBService getNamedEntityDBService() {
    return namedEntityDBService;
  }
  
  public void setNamedEntityDBService(NamedEntityDBService namedEntityDBService) {
    this.namedEntityDBService = namedEntityDBService;
  }
}
