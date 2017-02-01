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
import io.swagger.annotations.Authorization;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.Alert;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by jfinger on 12/21/15.
 */
@Path("queries")
@Api(value="queries", authorizations = {@Authorization(value = "basic")})
@Produces(MediaType.APPLICATION_JSON)
public class QueriesResource extends BaseResource {

  @GET
  @Path("/alerts")
  @ApiOperation(value="Get a list of search alerts by type", response=Alert.class, responseContainer = "List")
  public Response getAlerts(@QueryParam("frequency") String frequency) {
    try {
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Alert>>(
              namedEntityService.getAlerts(frequency)
          ) {
          }).build();
    } catch (NedException e) {
      return nedError(e, "Fetching alerts failed");
    } catch (Exception e) {
      return serverError(e, "Fetching alerts failed");
    }

  }

  protected String getNamedPartyType() {
    throw new UnsupportedOperationException("not implemented");
  }
}
