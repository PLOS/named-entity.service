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
import org.plos.namedentity.api.EntityNotFoundException;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.OrganizationComposite;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.service.NamedEntityService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/organizations")
@Api("/organizations")
public class OrganizationsResource extends BaseResource {

  @Override
  protected String getNamedPartyType() {
    return NamedEntityService.organizationType;
  }

  @POST
  @ApiOperation(value = "Create organization", response = OrganizationComposite.class)
  public Response createOrganization(OrganizationComposite composite) {
    try {
      return Response.status(Response.Status.OK).entity(
          namedEntityService.createOrganizationComposite(composite)).build();
    } catch (NedValidationException e) {
      return validationError(e, "Unable to create organization");
    } catch (Exception e) {
      return serverError(e, "Unable to create organization");
    }
  }

  @GET
  @Path("/{nedId}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @ApiOperation(value = "Read organization by Ned ID", response = OrganizationComposite.class)
  public Response readOrganization(@PathParam("nedId") int nedId) {
    try {
      namedEntityService.checkNedIdForType(nedId, getNamedPartyType());
      return Response.status(Response.Status.OK).entity(
          namedEntityService.findOrganizationComposite(nedId)).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Unable to read organization composite");
    }
  }

//  @GET
//  @ApiOperation(value = "List", response = Organization.class)
//  public Response list(@ApiParam(required = false) @QueryParam("offset") Integer offset,
//                       @ApiParam(required = false) @QueryParam("limit") Integer limit) {
//
//    if (offset == null || offset < 0)
//      offset = 0;
//    if (limit == null || limit <= 0 || limit > MAX_RESULT_COUNT)
//      limit = DEFAULT_RESULT_COUNT;
//
//    return Response.ok(new GenericEntity<List<Organization>>
//        (crudService.findAll(Organization.class, offset, limit)) {
//    }).build();
//  }

  @GET
  @Path("/{nedId}/emails")
  @ApiOperation("List emails")
  public Response getEmails(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Email.class);
  }

  @GET
  @Path("/{nedId}/addresses")
  @ApiOperation("List addresses")
  public Response getAddresses(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Address.class);
  }

  @GET
  @Path("/{nedId}/phonenumbers")
  @ApiOperation("List phone numbers")
  public Response getPhonenumbers(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Phonenumber.class);
  }

  @GET
  @Path("/{nedId}/uids")
  @ApiOperation("List UIDs")
  public Response getUids(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Uniqueidentifier.class);
  }
}
