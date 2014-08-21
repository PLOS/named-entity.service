package org.plos.namedentity.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.plos.namedentity.api.EntityNotFoundException;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.DegreeEntity;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.RoleEntity;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/individuals")
@Api(value="/individuals")
public class IndividualsResource extends BaseResource {

  @POST
  @ApiOperation(value = "Create", response = IndividualEntity.class)
  public Response create(IndividualEntity entity) {
    try {
      namedEntityService.resolveValuesToIds(entity);

      Integer nedId = crudService.create(entity);

      return Response.status(Response.Status.OK).entity(
          namedEntityService.findResolvedEntity(nedId, IndividualEntity.class)).build();

    } catch (NedValidationException e) {
      return validationError(e, "Unable to create individual");
    } catch (Exception e) {
      return serverError(e, "Unable to create individual");
    }
  }

  @GET
  @Path("/{nedId}")
  @ApiOperation(value = "Read", response = IndividualEntity.class)
  public Response read(@PathParam("nedId") int nedId) {
    try {
      return Response.status(Response.Status.OK).entity(namedEntityService.findResolvedEntity(nedId, IndividualEntity.class)).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Find individual by id failed");
    }
  }

  @POST
  @Path("/{nedId}")
  @ApiOperation(value = "Update", response = IndividualEntity.class)
  public Response update(@PathParam("nedId") int nedId, IndividualEntity entity) {

    try {

      entity.setNamedentityid(nedId);  // TODO: check if path var=payload for id?

      namedEntityService.resolveValuesToIds(entity);

      crudService.update(entity);

      entity = namedEntityService.findResolvedEntity(nedId, IndividualEntity.class);

      return Response.ok().entity(entity).build();

    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Unable to update individual");
    }

  }

  @DELETE
  @Path("/{nedId}")
  @ApiOperation(value = "Delete", response = IndividualEntity.class)
  public Response delete(@PathParam("nedId") int nedId) {

    try {

      IndividualEntity entity = namedEntityService.findResolvedEntity(nedId, IndividualEntity.class);

      crudService.delete(entity);

      return Response.status(Response.Status.NO_CONTENT).build();

    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Unable to delete individual");
    }

  }

  @GET
  @ApiOperation(value = "List", response = IndividualEntity.class)
  public Response list(@QueryParam("uidType") String uidType, @QueryParam("uidValue") String uidValue) {
    try {
      List<IndividualEntity> individuals = null; 
      
      if (isEmptyOrBlank(uidType) || isEmptyOrBlank(uidValue)) {
        individuals = crudService.findAll(IndividualEntity.class);
      } else {
        individuals = namedEntityService.findResolvedEntityByUid(uidType, uidValue, IndividualEntity.class);
      }
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<IndividualEntity>>(individuals){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find all individuals failed");
    }
  }


  /* ----------------------------------------------------------------------- */
  /*  EMAIL CRUD                                                             */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/emails")
  @ApiOperation(value = "Create email", response = IndividualEntity.class)
  public Response createEmail(@PathParam("nedId") int nedId,
                              EmailEntity emailEntity) {
    return createEmail(nedId, emailEntity, IndividualEntity.class);
  }

  @POST
  @Path("/{nedId}/emails/{emailId}")
  @ApiOperation(value = "Update email", response = IndividualEntity.class)
  public Response updateEmail(@PathParam("nedId") int nedId, 
                              @PathParam("emailId") int emailId, 
                              EmailEntity emailEntity) {

    return updateEmail(nedId, emailId, emailEntity, IndividualEntity.class);
  }

  @DELETE
  @Path("/{nedId}/emails/{emailId}")
  @ApiOperation(value = "Delete email", response = IndividualEntity.class)
  public Response deleteEmail(@PathParam("nedId") int nedId, 
                              @PathParam("emailId") int emailId) {
    return deleteEmail(nedId, emailId, IndividualEntity.class);
  }

  @GET
  @Path("/{nedId}/emails/{emailId}")
  @ApiOperation(value = "Read email", response = IndividualEntity.class)
  public Response getEmail(@PathParam("nedId") int nedId,
                           @PathParam("emailId") int emailId) {
    return getEmail(nedId, emailId, IndividualEntity.class);
  }

  @GET
  @Path("/{nedId}/emails")
  @ApiOperation(value = "List emails", response = IndividualEntity.class)
  public Response getEmails(@PathParam("nedId") int nedId) {
    return getEmails(nedId, IndividualEntity.class);
  }


  @GET
  @Path("/{nedId}/addresses")
  @ApiOperation(value = "List addresses", response = IndividualEntity.class)
  public Response getAddresses(@PathParam("nedId") int nedId) {
    return getAddresses(nedId, IndividualEntity.class);
  }

  @GET
  @Path("/{nedId}/phonenumbers")
  @ApiOperation(value = "List phone numbers", response = IndividualEntity.class)
  public Response getPhonenumbers(@PathParam("nedId") int nedId) {
    return getPhonenumbers(nedId, IndividualEntity.class);
  }

  @GET
  @Path("/{nedId}/xref")
  @ApiOperation(value = "List references", response = IndividualEntity.class)
  public Response getExternalReferences(@PathParam("nedId") int nedId) {
    return getExternalReferences(nedId, IndividualEntity.class);
  }

  @GET
  @Path("/{nedId}/degrees")
  @ApiOperation(value = "List degrees", response = IndividualEntity.class)
  public Response getDegrees(@PathParam("nedId") int nedId) {
    try {
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<DegreeEntity>>(
              namedEntityService.findResolvedEntities(
                  nedId, DegreeEntity.class)){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find degrees by nedId failed");
    }
  }

  @GET
  @Path("/{nedId}/roles")
  @ApiOperation(value = "List roles", response = IndividualEntity.class)
  public Response getRoles(@PathParam("nedId") int nedId) {
    try {
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<RoleEntity>>(
              namedEntityService.findResolvedEntities(nedId, RoleEntity.class)
          ){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find roles by nedId failed");
    }
  }

}
