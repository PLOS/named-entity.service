package org.plos.namedentity.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.plos.namedentity.api.EntityNotFoundException;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.Degree;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Uniqueidentifier;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/individuals")
@Api(value="/individuals")
public class IndividualsResource extends NedResource {

  @Override
  protected String getNamedPartyType() {
    return IndividualComposite.typeName;
  }

  @POST
  @ApiOperation(value = "Create individual", response = IndividualComposite.class)
  public Response createIndividual(IndividualComposite composite) {
    try {
      return Response.status(Response.Status.OK).entity(
          namedEntityService.createComposite(composite, IndividualComposite.class)).build();
    } catch (NedValidationException e) {
      return validationError(e, "Unable to create individual");
    } catch (Exception e) {
      return serverError(e, "Unable to create individual");
    }
  }

  @GET
  @Path("/{nedId}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @ApiOperation(value = "Read individual by Ned ID", response = IndividualComposite.class)
  public Response readIndividual(@PathParam("nedId") int nedId) {
    try {
      namedEntityService.checkNedIdForType(nedId, getNamedPartyType());
      return Response.status(Response.Status.OK).entity(
          namedEntityService.findComposite(nedId, IndividualComposite.class)).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Unable to read individual composite");
    }
  }

  @GET
  @Path("/{uidType}/{uidValue}")
  @ApiOperation(value = "Read individual by UID", response = IndividualComposite.class)
  public Response readIndividualByUid(@PathParam("uidType") String uidType,
                                     @PathParam("uidValue") String uidValue) {
    try {

      Individualprofile individualProfile = namedEntityService.findResolvedEntityByUid(
          uidType, uidValue, Individualprofile.class);

      return Response.status(Response.Status.OK).entity(
          namedEntityService.findComposite(individualProfile.getNedid(), IndividualComposite.class)).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Find individual failed");
    }
  }

  @GET
  @Path("/displayname/{displayName}")
  @ApiOperation(value = "Read individual by display name", response = IndividualComposite.class)
  public Response readIndividualByDisplayName
      (@PathParam("displayName") String displayName) {
    try {

      Individualprofile p = new Individualprofile();
      p.setDisplayname(displayName);

      List<Individualprofile> results = crudService.findByAttribute(p);

      if (results.size() == 0)
        throw new EntityNotFoundException("Individual");

      return Response.status(Response.Status.OK).entity(
          namedEntityService.findComposite(results.get(0).getNedid(), IndividualComposite.class)).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Find individual failed");
    }
  }

  /* ----------------------------------------------------------------------- */
  /*  PROFILE CRUD                                                              */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/individualprofiles")
  @ApiOperation(value = "Add profile", response = Individualprofile.class)
  public Response addProfile(@PathParam("nedId") int nedId, Individualprofile entity) {
    return createEntity(nedId, entity);
  }

  @PUT
  @Path("/{nedId}/individualprofiles/{profileId}")
  @ApiOperation(value = "Update a profile", response = Individualprofile.class)
  public Response updateProfile(@PathParam("nedId") int nedId,
                                @PathParam("profileId") int profileId,
                                Individualprofile entity) {

    return updateEntity(nedId, profileId, entity);
  }

  @DELETE
  @Path("/{nedId}/individualprofiles/{profileId}")
  @ApiOperation(value = "Delete a profile")
  public Response deleteProfile(@PathParam("nedId") int nedId,
                                @PathParam("profileId") int profileId) {

    if (((List)(getEntities(nedId, Individualprofile.class).getEntity())).size() == 1)
      return validationError(new NedValidationException("Profile entities cannot be empty"), "Unable to delete profile");

    return deleteEntity(nedId, profileId, Individualprofile.class);
  }

  @GET
  @Path("/{nedId}/individualprofiles/{profileId}")
  @ApiOperation(value = "Read profile", response = Individualprofile.class)
  public Response getProfile(@PathParam("nedId") int nedId,
                             @PathParam("profileId") int profileId) {
    return getEntity(nedId, profileId, Individualprofile.class);
  }

  @GET
  @Path("/{nedId}/individualprofiles")
  @ApiOperation(value = "List profiles", response = Individualprofile.class)
  public Response getProfiles(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Individualprofile.class);
  }


  /* ----------------------------------------------------------------------- */
  /*  UIDS                                                                   */
  /* ----------------------------------------------------------------------- */

  @DELETE
  @Path("/{nedId}/uids/{id}")
  @ApiOperation(value = "Delete UID")
  public Response deleteUid(@PathParam("nedId") int nedId,
                            @PathParam("id") int id) {

    int casCount = 0;

    int entityLocation = -1;

    List<Uniqueidentifier> uids = ((List<Uniqueidentifier>)(getEntities(nedId, Uniqueidentifier.class).getEntity()));

    for (int i=0; i<uids.size(); i++) {
      Uniqueidentifier uid = uids.get(i);
      if (uid.getType().equals("CAS")) {
        casCount++;
        if (uid.getId() == id)
          entityLocation = i;
      }
    }

    if (entityLocation != -1 && casCount == 1)
      return validationError(new NedValidationException("Can not remove last CAS ID"), "Unable to Uniqueidentifier");

    return deleteEntity(nedId, id, Uniqueidentifier.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  DEGREES CRUD                                                           */
  /* ----------------------------------------------------------------------- */

  @GET
  @Path("/{nedId}/degrees")
  @ApiOperation(value = "List degrees")
  public Response getDegrees(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Degree.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  ROLE CRUD                                                              */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/roles")
  @ApiOperation(value = "Create role", response = Role.class)
  public Response createRole(@PathParam("nedId") int nedId, Role roleEntity) {
    return createEntity(nedId, roleEntity);
  }

  @PUT
  @Path("/{nedId}/roles/{roleId}")
  @ApiOperation(value = "Update role", response = Role.class)
  public Response updateRole(@PathParam("nedId") int nedId, 
                             @PathParam("roleId") int roleId, 
                             Role roleEntity) {
    return updateEntity(nedId, roleId, roleEntity);
  }

  @DELETE
  @Path("/{nedId}/roles/{roleId}")
  @ApiOperation(value = "Delete role")
  public Response deleteRole(@PathParam("nedId") int nedId, 
                             @PathParam("roleId") int roleId) {
    return deleteEntity(nedId, roleId, Role.class);
  }

  @GET
  @Path("/{nedId}/roles/{roleId}")
  @ApiOperation(value = "Read role", response = Role.class)
  public Response getRole(@PathParam("nedId") int nedId,
                          @PathParam("roleId") int roleId) {
    return getEntity(nedId, roleId, Role.class);
  }

  @GET
  @Path("/{nedId}/roles")
  @ApiOperation(value = "List roles")
  public Response getRoles(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Role.class);
  }
}
