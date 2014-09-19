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
import com.wordnik.swagger.annotations.ApiParam;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Organization;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Uniqueidentifier;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/organizations")
@Api("/organizations")
public class OrganizationsResource extends BaseResource {

  @GET
  @ApiOperation(value = "List", response = Organization.class)
  public Response list(@ApiParam(required = false) @QueryParam("offset") Integer offset,
                       @ApiParam(required = false) @QueryParam("limit") Integer limit) {

    if (offset == null || offset < 0)
      offset = 0;
    if (limit == null || limit <= 0 || limit > MAX_RESULT_COUNT)
      limit = DEFAULT_RESULT_COUNT;
    
    return Response.ok(new GenericEntity<List<Organization>>
        (crudService.findAll(Organization.class, offset, limit)) {
    }).build();
  }

  @GET
  @Path("/{nedId}/emails")
  @ApiOperation("List emails")
  public Response getEmails(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Email.class, Organization.class);
  }

  @GET
  @Path("/{nedId}/addresses")
  @ApiOperation("List addresses")
  public Response getAddresses(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Address.class, Organization.class);
  }

  @GET
  @Path("/{nedId}/phonenumbers")
  @ApiOperation("List phone numbers")
  public Response getPhonenumbers(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Phonenumber.class, Organization.class);
  }

  @GET
  @Path("/{nedId}/xref")
  @ApiOperation("List references")
  public Response getExternalReferences(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Uniqueidentifier.class, Organization.class);
  }
}
