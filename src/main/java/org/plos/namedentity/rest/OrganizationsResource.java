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
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Organization;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Uniqueidentifier;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/organizations")
@Api("/organizations")
public class OrganizationsResource extends BaseResource {

  @POST
  @ApiOperation(value = "Create", response =  Organization.class)
  public Response create(Organization object) {
    try {
      return Response.ok(namedEntityService.createOrganization(object)).build();
    } catch (NedValidationException e) {
      return validationError(e, "Unable to create organization");
    } catch (Exception e) {
      return serverError(e, "Unable to create organization");
    }
  }

  @GET
  @Path("/{nedId}")
  @ApiOperation(value = "Read", response =  Organization.class)
  public Response read(@PathParam("nedId") int nedId) {
    try {
      return Response.ok(namedEntityService.findResolvedEntity(nedId, Organization.class)).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Find organization by id failed");
    }
  }

  @GET
  @ApiOperation(value = "List")
  public Response list() {
    return Response.ok(new GenericEntity<List<Organization>>
        (crudService.findAll(Organization.class)) {
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
