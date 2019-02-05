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
package org.plos.namedentity.service;

import org.plos.namedentity.api.NedException;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;

public class CrudServiceImpl implements CrudService {

  @Inject
  private NamedEntityDBService namedEntityDBService;

  @Override
  @Transactional
  public <T> Integer create(T t) {
    return namedEntityDBService.create(t);
  }

  @Override
  @Transactional
  public <T> boolean update(T t) {
    return namedEntityDBService.update(t);
  }

  @Override
  @Transactional
  public <T> boolean delete(T t) {
    return namedEntityDBService.delete(t);
  }

  @Override
  public <T> T findById(Integer id, Class<T> clazz) {
    T t = namedEntityDBService.findById(id, clazz);
    if (t == null) {
      throw new NedException(EntityNotFound, String.format("No entity with id=%d (%s)", id, clazz.getSimpleName()));
    }
    return t;
  }

  @Override
  public <T> List<T> findAll(Class<T> clazz, Integer offset, Integer limit) {
    return namedEntityDBService.findAll(clazz, offset, limit);
  }

  @Override
  public <T> List<T> findByAttribute(T t, Boolean partial) {
    return namedEntityDBService.findByAttribute(t, partial);
  }

  public NamedEntityDBService getNamedEntityDBService() {
    return namedEntityDBService;
  }

  public void setNamedEntityDBService(NamedEntityDBService namedEntityDBService) {
    this.namedEntityDBService = namedEntityDBService;
  }
}
