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
package org.plos.namedentity.rest;

import org.apache.log4j.Logger;
import org.plos.namedentity.api.NedErrorResponse;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.service.AuthService;
import org.plos.namedentity.service.CrudService;
import org.plos.namedentity.service.NamedEntityService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static org.plos.namedentity.api.NedException.ErrorType.ServerError;

public abstract class BaseResource {

  protected static Logger logger = Logger.getLogger(BaseResource.class);

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
