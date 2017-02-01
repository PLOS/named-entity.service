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
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.Globaltype;
import org.plos.namedentity.api.entity.Typedescription;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/typeclasses")
@Api(value = "/typeclasses", authorizations = {@Authorization(value = "basic")})
@Produces(MediaType.APPLICATION_JSON)
public class TypeclassesResource extends BaseResource {

  private static String namedPartyType = "Typeclass";

  @Override
  protected String getNamedPartyType() {
    return namedPartyType;
  }

  @GET
  @Path("/{id}")
  @ApiOperation(value = "Read global type", response = Typedescription.class)
  public Response read(@PathParam("id") int id) {
    try {
      return Response.status(Response.Status.OK).entity(
          crudService.findById(id, Typedescription.class)).build();
    } catch (NedException e) {
      return nedError(e, "Find type class by id failed");
    } catch (Exception e) {
      return serverError(e, "Find type class by id failed");
    }
  }

  @GET
  @ApiOperation(value = "List global types", response = Typedescription.class, responseContainer = "List")
  public Response list(@ApiParam(required = false) @QueryParam("offset") Integer offset,
                       @ApiParam(required = false) @QueryParam("limit") Integer limit) {
    try {

      if (offset == null || offset < 0)
        offset = 0;
      if (limit == null || limit <= 0 || limit > MAX_RESULT_COUNT)
        limit = DEFAULT_RESULT_COUNT;

      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Typedescription>>(
              crudService.findAll(Typedescription.class, offset, limit)
          ) {
          }).build();
    } catch (NedException e) {
      return nedError(e, "Find all type classes failed");
    } catch (Exception e) {
      return serverError(e, "Find all type classes failed");
    }
  }

  @POST
  @ApiOperation(value = "Create global type", response = Typedescription.class)
  public Response create(Typedescription typeDescription) {
    try {
      Integer pkId = crudService.create(typeDescription);

      return Response.status(Response.Status.OK).entity(
          crudService.findById(pkId, Typedescription.class)).build();
    } catch (NedException e) {
      return nedError(e, "Unable to create Type Class");
    } catch (Exception e) {
      return serverError(e, "Unable to create Type Class");
    }
  }

  @PUT
  @Path("/{id}")
  @ApiOperation(value = "Update global type", response = Typedescription.class)
  public Response update(Typedescription typeDescription) {
    try {
      crudService.update(typeDescription);

      return Response.status(Response.Status.OK).entity(
          crudService.findById(typeDescription.getId(),
              Typedescription.class)).build();
    } catch (NedException e) {
      return nedError(e, "Unable to update Type Class");
    } catch (Exception e) {
      return serverError(e, "Unable to update Type Class");
    }
  }

  @DELETE
  @Path("/{id}")
  @ApiOperation("Delete global type")
  public Response delete(@PathParam("id") int id) {
    try {
      Typedescription entity = new Typedescription();
      entity.setId(id);
      crudService.delete(entity);
      return Response.status(Response.Status.NO_CONTENT).build();
    }
    catch(NedException e) {
      return nedError(e, "Unable to delete Type Class");
    }
    catch (Exception e) {
      return serverError(e, "Unable to delete Type Class");
    }
  }

  /* ---------------------------------------------------------------------- */
  /*  TYPE VALUES (GLOBAL TYPES)                                            */
  /* ---------------------------------------------------------------------- */

  @GET
  @Path("/{typeclassid}/typevalues/{typevalueid}")
  @ApiOperation(value = "Read global type value", response = Globaltype.class)
  public Response getGlobalType(@PathParam("typeclassid") int typeClassId,
                                @PathParam("typevalueid") int typeValueId) {
    try {
      return Response.status(Response.Status.OK).entity(
          crudService.findById(typeValueId, Globaltype.class)).build();
    }
    catch(NedException e) {
      return nedError(e, "Find type value by id failed");
    }
    catch(Exception e) {
      return serverError(e, "Find type value by id failed");
    }
  }

  @GET
  @Path("/{typeclassid}/typevalues")
  @ApiOperation("List global type values")
  public Response getGlobalTypeForTypeClass(@PathParam("typeclassid") int typeClassId) {
    try {
      Globaltype searchCriteria = new Globaltype();
      searchCriteria.setTypeid(typeClassId);

      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Globaltype>>(
              crudService.findByAttribute(searchCriteria, false)
          ){}).build();
    }
    catch(NedException e) {
      return nedError(e, "Find type classes for a type class failed");
    }
    catch(Exception e) {
      return serverError(e, "Find type classes for a type class failed");
    }
  }

  @POST
  @Path("/{typeclassid}/typevalues")
  @ApiOperation(value = "Create global type value", response = Globaltype.class)
  public Response createGlobalType(@PathParam("typeclassid") int typeClassId, Globaltype globalType) {
    try {
      globalType.setTypeid(typeClassId);
      Integer pkId = crudService.create(globalType);

      return Response.status(Response.Status.OK).entity(
          crudService.findById(pkId, Globaltype.class)).build();
    }
    catch(NedException e) {
      return nedError(e, "Unable to create Type Value");
    }
    catch(Exception e) {
      return serverError(e, "Unable to create Type Value");
    }
  }

  @PUT
  @Path("/{typeclassid}/typevalues/{typevalueid}")
  @ApiOperation(value = "Update global type value", response = Globaltype.class)
  public Response updateGlobalType(@PathParam("typeclassid") int typeClassId,
                                   @PathParam("typevalueid") int typeValueId,
                                   Globaltype globalType) {
    try {
      // TODO: make use of path variables, since they are currently ignored

      crudService.update( globalType ); // TODO: handle 404 not_found?
      Globaltype entity = crudService.findById(
          globalType.getId(), Globaltype.class);
      return Response.status(Response.Status.OK).entity( entity ).build();
    }
    catch(NedException e) {
      return nedError(e, "Unable to update Type Value");
    }
    catch(Exception e) {
      return serverError(e, "Unable to update Type Value");
    }
  }

  @DELETE
  @Path("/{typeclassid}/typevalues/{typevalueid}")
  @ApiOperation("Delete global type value")
  public Response deleteGlobalType(@PathParam("typeclassid") int typeClassId,
                                   @PathParam("typevalueid") int typeValueId) {
    try {
      Globaltype entity = new Globaltype();
      entity.setId(typeValueId);
      crudService.delete(entity);
      return Response.status(Response.Status.NO_CONTENT).build();
    }
    catch(NedException e) {
      return nedError(e, "Unable to delete Type Value");
    }
    catch(Exception e) {
      return serverError(e, "Unable to delete Type Value");
    }
  }

}
