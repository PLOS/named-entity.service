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

import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.OrganizationComposite;
import org.plos.namedentity.api.entity.Organization;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/organizations")
@Api("/organizations")
public class OrganizationsResource extends NedResource {

  @Override
  protected String getNamedPartyType() {
    return OrganizationComposite.typeName;
  }

  @POST
  @ApiOperation(value = "Create organization", response = OrganizationComposite.class)
  public Response createOrganization(OrganizationComposite composite,
                                     @HeaderParam("Authorization") String credentials) {
    try {
      setCreateAndLastModifiedBy(credentials,composite);
      return Response.status(Response.Status.OK).entity(
          namedEntityService.createComposite(composite, OrganizationComposite.class)).build();
    } catch (NedException e) {
      return nedError(e, "Unable to create organization");
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
          namedEntityService.findComposite(nedId, OrganizationComposite.class)).build();
    } catch (NedException e) {
      return nedError(e, "Unable to read organization composite");
    } catch (Exception e) {
      return serverError(e, "Unable to read organization composite");
    }
  }

  @GET
  @Path("/{uidType}/{uidValue}")
  @ApiOperation(value = "Read organization by UID", response = OrganizationComposite.class)
  public Response readOrganizationByUid(@PathParam("uidType") String uidType,
                                       @PathParam("uidValue") String uidValue) {
    try {

      Organization organization = namedEntityService.findResolvedEntityByUid(
          uidType, uidValue, Organization.class);

      return Response.status(Response.Status.OK).entity(
          namedEntityService.findComposite(organization.getNedid(), OrganizationComposite.class)).build();
    } catch (NedException e) {
      return nedError(e, "Find organization failed");
    } catch (Exception e) {
      return serverError(e, "Find organization failed");
    }
  }
}
