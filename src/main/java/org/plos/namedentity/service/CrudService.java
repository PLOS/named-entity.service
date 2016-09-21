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

/* -------------------------------------------------------------------------- */
/*   Named Entity Service Low-Level Core API                                  */
/* -------------------------------------------------------------------------- */

public interface CrudService {

  <T> Integer create(T t);

  <T> boolean update(T t);

  <T> boolean delete(T t);

  <T> T findById(Integer id, Class<T> clazz);

  <T> List<T> findAll(Class<T> clazz, Integer offset, Integer limit);

  <T> List<T> findByAttribute(T t, Boolean partial);

}
