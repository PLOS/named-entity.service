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

import org.aspectj.lang.ProceedingJoinPoint;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.RoleEntity;
import org.springframework.core.Ordered;

public class NamedEntityValidator implements Ordered {

  // allows us to control ordering of advice (ascending priority, 1 highest)
  private int order;

  public int  getOrder()          { return order;       }
  public void setOrder(int order) { this.order = order; }

  public Object validate(ProceedingJoinPoint call) throws Throwable {
    Object returnValue;

    /* ------------------------------------------------------------------ */
    /*  BEFORE-VALIDATION                                                 */
    /* ------------------------------------------------------------------ */

    Object[] args = call.getArgs();
    if (args != null && args.length > 0 && args[0] instanceof IndividualComposite) {
      // TODO - replace with chain of business rules.
      RoleEntity role = ((IndividualComposite) args[0]).getRole();
      if (role == null) {
        throw new NedValidationException("Validation Phase 1 Failure. No ROLE defined for individual.");
      }
    }

    /* ------------------------------------------------------------------ */
    /*  METHOD EXECUTION                                                  */
    /* ------------------------------------------------------------------ */

    returnValue = call.proceed();

    /* ------------------------------------------------------------------ */
    /*  AFTER-VALIDATION                                                  */
    /* ------------------------------------------------------------------ */

    //TODO - perform after-validation here.
    //TODO - *** REMOVE DEMO HACK *** 

    if (args != null && args.length > 0 && args[0] instanceof IndividualComposite) {

      // cross table constraint, since composite cannot be encapsulated in DB schema

      if (((IndividualComposite) args[0]).getEmails() == null)
        throw new NedValidationException("Email can not be empty");


      for (EmailEntity email : ((IndividualComposite) args[0]).getEmails()) {
        if ("foo@bar.com".equals(email.getEmailaddress())) {
          throw new NedValidationException("Validation Phase 2 Failure. foo@bar.com backdoor detected !!!");
        }
      }
    }

    return returnValue;
  }
}
