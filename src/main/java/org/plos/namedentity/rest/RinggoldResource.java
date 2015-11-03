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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.plos.namedentity.api.ringgold.Institution;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.ringgold.Institution;
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

import static org.plos.namedentity.api.NedException.ErrorType.InvalidInstitutionQuery;

@Path("/institutionsearch")
@Api("/institutionsearch")
@Produces(MediaType.APPLICATION_JSON)
public class RinggoldResource extends BaseResource {

  @Inject protected RinggoldService ringgoldService;

  @GET
  @ApiOperation(value = "Find institution(s) by name fragment (wildcard search).")
  public Response findInstitutionsByName(@QueryParam("substring") String search_string) {
    try {
      if (isEmptyOrBlank(search_string)) {
        throw new NedException(InvalidInstitutionQuery, 
          "Institution search requires a partial name to match against (defined in substring parameter)");
      }

      List<Institution> results = ringgoldService.findByInstitutionName(search_string);

      return Response.status(Response.Status.OK).entity(
        new GenericEntity<List<Institution>>(results){}).build();

    } catch (NedException e) {
      return nedError(e, "findInstitutionsByName() failed");
    } catch (Exception e) {
      return serverError(e, "findInstitutionsByName() failed");
    }
  }

  protected String getNamedPartyType() {
    throw new UnsupportedOperationException("not implemented");
  }
}
