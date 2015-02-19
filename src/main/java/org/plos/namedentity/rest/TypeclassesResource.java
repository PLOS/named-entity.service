package org.plos.namedentity.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.Globaltype;
import org.plos.namedentity.api.entity.Typedescription;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/typeclasses")
@Api("/typeclasses")
public class TypeclassesResource extends BaseResource {

  private static String namedPartyType = "Typeclass";

  @Override
  protected String getNamedPartyType() {
    return namedPartyType;
  }

  @GET
  @Path("/{id}")
  @ApiOperation(value = "Read", response = Typedescription.class)
  public Response read(@PathParam("id") int id) {
    try {
      return Response.status(Response.Status.OK).entity(
          crudService.findById(id, Typedescription.class)).build();
    } catch (Exception e) {
      return serverError(e, "Find type class by id failed");
    }
  }

  @GET
  @ApiOperation(value = "List", response = Typedescription.class)
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
    } catch (Exception e) {
      return serverError(e, "Find all type classes failed");
    }
  }

  @POST
  @ApiOperation(value = "Create", response = Typedescription.class)
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
  @ApiOperation(value = "Update", response = Typedescription.class)
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
  @ApiOperation("Delete")
  public Response delete(@PathParam("id") int id) {
    try {
      Typedescription entity = new Typedescription();
      entity.setId(id);
      crudService.delete(entity);
      return Response.status(Response.Status.NO_CONTENT).build();
    } catch (Exception e) {
      return serverError(e, "Unable to delete Type Class");
    }
  }

  /* ---------------------------------------------------------------------- */
  /*  TYPE VALUES (GLOBAL TYPES)                                            */
  /* ---------------------------------------------------------------------- */

  @GET
  @Path("/{typeclassid}/typevalues/{typevalueid}")
  @ApiOperation(value = "Read global type", response = Globaltype.class)
  public Response getGlobalType(@PathParam("typeclassid") int typeClassId,
                                @PathParam("typevalueid") int typeValueId) {
    try {
      return Response.status(Response.Status.OK).entity(
          crudService.findById(typeValueId, Globaltype.class)).build();
    }
    catch(Exception e) {
      return serverError(e, "Find type value by id failed");
    }
  }

  @GET
  @Path("/{typeclassid}/typevalues")
  @ApiOperation("List global types")
  public Response getGlobalTypeForTypeClass(@PathParam("typeclassid") int typeClassId) {
    try {
      Globaltype searchCriteria = new Globaltype();
      searchCriteria.setTypeid(typeClassId);

      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Globaltype>>(
              crudService.findByAttribute(searchCriteria)
          ){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find type classes for a type class failed");
    }
  }

  @POST
  @Path("/{typeclassid}/typevalues")
  @ApiOperation(value = "Create global type", response = Globaltype.class)
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
  @ApiOperation(value = "Update global type", response = Globaltype.class)
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
  @ApiOperation("Delete global type")
  public Response deleteGlobalType(@PathParam("typeclassid") int typeClassId,
                                   @PathParam("typevalueid") int typeValueId) {
    try {
      Globaltype entity = new Globaltype();
      entity.setId(typeValueId);
      crudService.delete(entity);
      return Response.status(Response.Status.NO_CONTENT).build();
    }
    catch(Exception e) {
      return serverError(e, "Unable to delete Type Value");
    }
  }

}
