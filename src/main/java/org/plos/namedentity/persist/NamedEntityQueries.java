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

import org.plos.namedentity.api.dto.AddressDTO;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.PhonenumberEntity;
import org.plos.namedentity.api.entity.RoleEntity;
import org.plos.namedentity.api.entity.UniqueidentifierEntity;

import java.util.List;

public interface NamedEntityQueries {

  IndividualEntity          findIndividualByNedId  (Integer nedId);
  List<IndividualEntity>    findIndividualsByUid   (Integer srcTypeId, String uid);
  List<AddressDTO>          findAddressesByNedId   (Integer nedId);
  List<EmailDTO>            findEmailsByNedId      (Integer nedId);
  List<PhonenumberEntity>      findPhoneNumbersByNedId(Integer nedId);
  List<RoleEntity>             findRolesByNedId       (Integer nedId);
  List<UniqueidentifierEntity> findUniqueIdsByNedId   (Integer nedId);
}
