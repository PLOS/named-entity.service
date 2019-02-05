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
package org.plos.namedentity.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.plos.namedentity.api.NedErrorResponse;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.service.AuthService;
import org.plos.namedentity.service.CrudService;
import org.plos.namedentity.service.NamedEntityService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static org.plos.namedentity.api.NedException.ErrorType.ServerError;

public abstract class BaseResource {

  protected static final Logger logger = LoggerFactory.getLogger(BaseResource.class);

  protected static final Integer MAX_RESULT_COUNT = 1000;

  protected static final Integer DEFAULT_RESULT_COUNT = 50;

  @Inject protected AuthService authService;

  @Inject protected CrudService crudService;

  @Inject protected NamedEntityService namedEntityService;

  abstract protected String getNamedPartyType();

  protected Response serverError(Exception e, String message) {
    logger.error("internal error", e);

    NedErrorResponse ner = new NedErrorResponse();
    ner.failureMsg       = "Internal error";
    ner.errorCode        = ServerError.getErrorCode();
    ner.detailedMsg      = message;

    // 5XX (server-side)
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                   .entity(ner)
                   .build();
  }

  protected Response nedError(NedException e, String message) {
    logger.error("ned exception", e);

    // 4XX (client-side)
    return Response.status(Response.Status.BAD_REQUEST)
                   .entity(new NedErrorResponse(e, message))
                   .build();
  }

  protected boolean isEmptyOrBlank(String s) {
    return s == null || s.trim().isEmpty();
  }
}
