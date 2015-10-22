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
import org.plos.namedentity.api.ConfigInfo;
import org.plos.namedentity.api.NedErrorResponse;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.service.InfoService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/service")
@Api("/service")
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
