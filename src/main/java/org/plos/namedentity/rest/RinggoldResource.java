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

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.plos.namedentity.api.ringgold.Institution;
import org.plos.namedentity.api.NedErrorResponse;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.service.RinggoldService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidInstitutionQuery;
import static org.plos.namedentity.api.NedException.ErrorType.ServerError;
import static org.plos.namedentity.api.NedException.ErrorType.TooManyResultsFound;

@Path("/institutionsearch")
@Api("/institutionsearch")
public class RinggoldResource {

  static Integer INSTITUTIONS_RESULT_LIMIT = 100;

  private static Logger logger = Logger.getLogger(RinggoldResource.class);

  @Inject protected RinggoldService ringgoldService;

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @ApiOperation(value = "Find institution(s) by name (wildcard search).")
  public Response findInstitutionsByName(@QueryParam("substring") String name) {
    try {
      if (isEmptyOrBlank(name)) {
        throw new NedException(InvalidInstitutionQuery, 
          "Institution search requires a partial name to match against (defined in substring parameter)");
      }

      Institution ifilter = new Institution() ; ifilter.setName(name);

      List<Institution> results = ringgoldService.findByAttribute(ifilter);

      if (results.size() == 0)
        throw new NedException(EntityNotFound, "Institution not found");
      else if (results.size() > INSTITUTIONS_RESULT_LIMIT)
        results = results.subList(0, INSTITUTIONS_RESULT_LIMIT);

      return Response.status(Response.Status.OK).entity(
        new GenericEntity<List<Institution>>(results){}).build();

    } catch (NedException e) {
      return nedError(e, "findInstitutionsByName() failed");
    } catch (Exception e) {
      return serverError(e, "findInstitutionsByName() failed");
    }
  }

  private Response serverError(Exception e, String message) {
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

  private Response nedError(NedException e, String message) {
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
