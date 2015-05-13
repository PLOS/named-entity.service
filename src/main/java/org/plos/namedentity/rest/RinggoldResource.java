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
import org.plos.namedentity.api.ringgold.Institution;
import org.plos.namedentity.api.NedException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidSearchQuery;
import static org.plos.namedentity.api.NedException.ErrorType.TooManyResultsFound;

@Path("/institutionsearch")
@Api("/institutionsearch")
public class RinggoldResource extends BaseResource {

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @ApiOperation(value = "Find institution(s) by name (wildcard search).")
  public Response findInstitutionsByName(@QueryParam("substring") String name) {
    try {
      if (isEmptyOrBlank(name)) {
        throw new NedException(InvalidSearchQuery);
      }

      Institution ifilter = new Institution();
      ifilter.setName(name);

      List<Institution> results = crudService.findByAttribute(ifilter);

      if (results.size() == 0)
        throw new NedException(EntityNotFound, "Institution not found");
      else if (results.size() > 100)
        throw new NedException(TooManyResultsFound);

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
