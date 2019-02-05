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
