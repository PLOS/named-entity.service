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
package org.plos.namedentity.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
