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

import java.util.List;

public interface NamedEntityDBService {

  //TODO - replace typecode string with enum?
  public Integer newNamedEntityId(String typeCode);

  public <T> Integer create(T t);
  public <T> boolean update(T t);
  public <T> boolean delete(T t);

  public <T> List<T> findAll(Class<T> clazz);
  public <T> T       findById(Integer id, Class<T> clazz);
  public <T> List<T> findByAttribute(T t);
}
