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
import org.plos.namedentity.api.dto.AddressDTO;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.dto.IndividualDTO;
import org.plos.namedentity.api.dto.PhonenumberDTO;
import org.plos.namedentity.api.dto.RoleDTO;
import org.plos.namedentity.api.dto.UniqueidentifierDTO;

import java.util.List;

/* -------------------------------------------------------------------------- */
/*   Named Entity Service High-Level Convenience API                          */
/* -------------------------------------------------------------------------- */

public interface NamedEntityService {

  public IndividualDTO             createIndividual       (IndividualComposite composite);

  public IndividualDTO             findIndividualByNedId  (Integer nedId);
  public List<IndividualDTO>       findIndividualsByUid   (Integer srcTypeId, String uid);
  public List<AddressDTO>          findAddressesByNedId   (Integer nedId);
  public List<EmailDTO>            findEmailsByNedId      (Integer nedId);
  public List<PhonenumberDTO>      findPhoneNumbersByNedId(Integer nedId);
  public List<RoleDTO>             findRolesByNedId       (Integer nedId);
  public List<UniqueidentifierDTO> findUniqueIdsByNedId   (Integer nedId);
}
