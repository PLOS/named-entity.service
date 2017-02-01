/*
 * Copyright (c) 2017 Public Library of Science
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

import org.aspectj.lang.ProceedingJoinPoint;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.spring.exception.NedExceptionTranslator;
import org.springframework.core.Ordered;
import org.springframework.dao.NonTransientDataAccessException;

public class NamedEntityValidator implements Ordered {

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
    } catch (NonTransientDataAccessException e) {
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
