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

import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.OrganizationEntity;

import java.util.List;

public interface NamedEntityQueries {

  List<IndividualEntity>       findIndividualsByUid   (Integer srcTypeId, String uid);
  List<OrganizationEntity>     findOrganizationsByUid (String srcType, String uid);
  <T> T findResolvedEntity(Integer nedId, Class<T> clazz);
  <T> List<T> findResolvedEntities(Integer nedId, Class<T> clazz);
}
