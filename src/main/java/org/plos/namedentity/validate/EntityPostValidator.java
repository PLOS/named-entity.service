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
package org.plos.namedentity.validate;

import static org.plos.namedentity.api.NedException.ErrorType.*;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.OrganizationComposite;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.persist.NamedEntityDBService;

import javax.inject.Inject;

public class EntityPostValidator{ 

  @Inject private NamedEntityDBService namedEntityDBService; 

  public void validate(Object o) {
    if (o instanceof Entity) {
      namedEntityDBService.validate((Entity)o);
    }
    else if (o instanceof IndividualComposite) {
      for (Uniqueidentifier uid : ((IndividualComposite)o).getUniqueidentifiers()) {
        namedEntityDBService.validate(uid);
      }
      //TODO - validate other entities
    }
    else if (o instanceof OrganizationComposite) {
      for (Uniqueidentifier uid : ((OrganizationComposite)o).getUniqueidentifiers()) {
        namedEntityDBService.validate(uid);
      }
      //TODO - validate other entities
    }
  }

  public NamedEntityDBService getNamedEntityDBService() {
    return namedEntityDBService;
  }

  public void setNamedEntityDBService(NamedEntityDBService namedEntityDBService) {
    this.namedEntityDBService = namedEntityDBService;
  }
}
