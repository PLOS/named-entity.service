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

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.spring.exception.NedExceptionTranslator;
import org.springframework.core.Ordered;
import org.springframework.dao.DataIntegrityViolationException;

public class NamedEntityValidator implements Ordered {

  protected static Logger logger = Logger.getLogger(NamedEntityValidator.class);

  // allows us to control ordering of advice (ascending priority, 1 highest)
  private int order;

  private EntityPostValidator entityPostValidator;

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public void setEntityPostValidator(EntityPostValidator entityPostValidator) {
    this.entityPostValidator = entityPostValidator;
  }

  public Object validate(ProceedingJoinPoint call) throws Throwable {

    /* ------------------------------------------------------------------ */
    /*  BEFORE-VALIDATION                                                 */
    /* ------------------------------------------------------------------ */

    Object[] args = call.getArgs();

    if (args[0] instanceof Validatable)
      ((Validatable) args[0]).validate();

    /* ------------------------------------------------------------------ */
    /*  METHOD EXECUTION                                                  */
    /* ------------------------------------------------------------------ */

    Object returnValue;

    try {
      returnValue = call.proceed();
    } catch (DataIntegrityViolationException e) {
      throw NedExceptionTranslator.translate(e);
    }

    /* ------------------------------------------------------------------ */
    /*  AFTER-VALIDATION                                                  */
    /* ------------------------------------------------------------------ */

    // an exception will be thrown if post validation fails
    entityPostValidator.validate(returnValue);

    return returnValue;
  }
}
