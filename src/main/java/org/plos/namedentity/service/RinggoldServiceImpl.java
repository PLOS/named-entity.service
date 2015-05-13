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

import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;

import org.plos.namedentity.api.NedException;
import org.plos.namedentity.persist.RinggoldDBService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

public class RinggoldServiceImpl implements CrudService {

  @Inject private RinggoldDBService ringgoldDBService; 

  @Override
  public <T> T findById(Integer id, Class<T> clazz) {
    T t = ringgoldDBService.findById(id, clazz);
    if (t == null) {
      throw new NedException(EntityNotFound, String.format("No entity with id=%d (%s)", id, clazz.getSimpleName()));
    }
    return t;
  }

  @Override
  public <T> List<T> findByAttribute(T t) {
    return ringgoldDBService.findByAttribute(t);
  }

  public RinggoldDBService getRinggoldDBService() {
    return ringgoldDBService;
  }
  
  public void setRinggoldDBService(RinggoldDBService ringgoldDBService) {
    this.ringgoldDBService = ringgoldDBService;
  }

  @Override @Transactional
  public <T> Integer create(T t) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override @Transactional
  public <T> boolean update(T t) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override @Transactional
  public <T> boolean delete(T t) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public <T> List<T> findAll(Class<T> clazz, Integer offset, Integer limit) {
    throw new UnsupportedOperationException("not implemented");
  }
}
