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
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.OrganizationEntity;

import java.util.List;

/* -------------------------------------------------------------------------- */
/*   Named Entity Service High-Level Convenience API                          */
/* -------------------------------------------------------------------------- */

public interface NamedEntityService {

  public IndividualComposite          createIndividualComposite (IndividualComposite composite);
  public OrganizationEntity           createOrganization     (OrganizationEntity entity);
  public List<IndividualEntity>       findIndividualsByUid   (String srcTypeId, String uid);

  public IndividualComposite          findIndividualComposite(Integer nedId);

  public <T> T findResolvedEntity(Integer nedId, Class<T> clazz);
  public <T> List<T> findResolvedEntities(Integer nedId, Class<T> clazz);

  /**
   * Resolve and entity's values to foreign keys
   * @param t
   * @param <T>
   */
  public <T> T resolveValuesToIds(T t);
}
