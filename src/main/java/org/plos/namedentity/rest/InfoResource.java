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
import org.plos.namedentity.api.ConfigInfo;
import org.plos.namedentity.api.NedErrorResponse;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.service.InfoService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/service")
@Api("/service")
@Produces(MediaType.APPLICATION_JSON)
public class InfoResource {

  @Inject
  private InfoService infoService;

  // TODO: add endpoint to show basic Individual counts etc.

  @GET
  @Path("/config")
  @ApiOperation(value = "Config info", response = ConfigInfo.class)
  public Response config() {
    return Response.ok(infoService.getConfig()).build();
  }

  @GET
  @Path("/errorcodes")
  @ApiOperation(value = "List possible error codes", response = NedErrorResponse.class, responseContainer = "List")
  public Response errorcodes() {

    List<NedErrorResponse> list = new ArrayList<>();

    for (NedException.ErrorType error : NedException.ErrorType.values()) {
      NedErrorResponse r = new NedErrorResponse();
      r.errorCode = error.getErrorCode();
      r.errorMsg = error.getErrorMessage();
      list.add(r);
    }

    return Response.ok(new GenericEntity<List<NedErrorResponse>>(list){}).build();
  }

}
