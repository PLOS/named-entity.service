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
import org.plos.namedentity.api.entity.AddressEntity;
import org.plos.namedentity.api.entity.DegreeEntity;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.OrganizationEntity;
import org.plos.namedentity.api.entity.PhonenumberEntity;
import org.plos.namedentity.api.entity.RoleEntity;
import org.plos.namedentity.api.entity.UniqueidentifierEntity;

import java.util.List;

/* -------------------------------------------------------------------------- */
/*   Named Entity Service High-Level Convenience API                          */
/* -------------------------------------------------------------------------- */

public interface NamedEntityService {

  public IndividualEntity             createIndividual       (IndividualComposite composite);
  public OrganizationEntity           createOrganization     (OrganizationEntity entity); // NOTE: this accepts an entity, not a composite
  public OrganizationEntity           findOrganizationByNedId(Integer nedId);
  public IndividualEntity             findIndividualByNedId  (Integer nedId);
  public List<IndividualEntity>       findIndividualsByUid   (String srcTypeId, String uid);
  public List<AddressEntity>          findAddressesByNedId   (Integer nedId);
  public List<EmailEntity>            findEmailsByNedId      (Integer nedId);
  public List<PhonenumberEntity>      findPhoneNumbersByNedId(Integer nedId);
  public List<RoleEntity>             findRolesByNedId       (Integer nedId);
  public List<UniqueidentifierEntity> findUniqueIdsByNedId   (Integer nedId);
  public List<DegreeEntity>           findDegreesByNedId     (Integer nedId);

  /**
   * Resolve and entity's IDs to values associated with that ID
   * @param t
   * @param <T>
   */
  public <T> void                     resolveValues          (T t);
}
