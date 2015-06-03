package org.plos.namedentity.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.Auth;
import org.plos.namedentity.api.entity.Composite;
import org.plos.namedentity.api.entity.Degree;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.api.entity.Relationship;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.service.AuthServiceImpl;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidSearchQuery;
import static org.plos.namedentity.api.NedException.ErrorType.TooManyResultsFound;

@Path("/individuals")
@Api(value="/individuals")
public class IndividualsResource extends NedResource {

  @Override
  protected String getNamedPartyType() {
    return IndividualComposite.typeName;
  }

  @POST
  @ApiOperation(value = "Create individual", response = IndividualComposite.class)
  public Response createIndividual(IndividualComposite composite,
                                   @HeaderParam("Authorization") String authstring) {
    try {
      generateDisplaynameIfEmpty(composite);

      setCreatedAndLastModifiedBy(authstring,composite);

      return Response.status(Response.Status.OK).entity(
          namedEntityService.createComposite(composite, IndividualComposite.class)).build();
    } catch (NedException e) {
      return nedError(e, "Unable to create individual");
    } catch (Exception e) {
      return serverError(e, "Unable to create individual");
    }
  }

  private void generateDisplaynameIfEmpty(IndividualComposite composite) {
    List<Individualprofile> profiles = composite.getIndividualprofiles();
    if (profiles != null & profiles.size() > 0) {
      Individualprofile profile = profiles.get(0);
      if ( isEmptyOrBlank(profile.getDisplayname()) ) {
        profile.setDisplayname( namedEntityService.generateDisplayname(profile, new Random()) );
      }
    }
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @ApiOperation(value = "Find individual matching specified attribute.")
  public Response findIndividuals(@QueryParam("entity")    String entity,
                                  @QueryParam("attribute") String attribute,
                                  @QueryParam("value")     String value) {
    try {
      if (isEmptyOrBlank(entity) || isEmptyOrBlank(attribute) || isEmptyOrBlank(value)) {
        throw new NedException(InvalidSearchQuery);
      }

      List<Entity> results = crudService.findByAttribute( createSearchCriteria(entity,attribute,value,IndividualComposite.class) );

      if (results.size() == 0)
        throw new NedException(EntityNotFound, "Individual not found");
      else if (results.size() > 10)
        throw new NedException(TooManyResultsFound);

      // entity records may refer to the same individual. we can filter these out
      // by adding ned id's to a set.

      Set<Integer> nedids = new HashSet<>();
      for (Entity e : results) { nedids.add(e.getNedid()); }

      // lookup composites for ned id's

      List<IndividualComposite> composites = new ArrayList<>();
      for (Integer nedid : nedids) {
        composites.add(namedEntityService.findComposite(nedid, IndividualComposite.class));
      }

      return Response.status(Response.Status.OK).entity(
        new GenericEntity<List<IndividualComposite>>(composites){}).build();

    } catch (NedException e) {
      return nedError(e, "findIndividuals() failed");
    } catch (Exception e) {
      return serverError(e, "findIndividuals() failed");
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
    } catch (NedException e) {
      return nedError(e, "Unable to read individual composite");
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
    } catch (NedException e) {
      return nedError(e, "Find individual failed");
    } catch (Exception e) {
      return serverError(e, "Find individual failed");
    }
  }

  @GET
  @Path("/CAS/{casId}")
  @ApiOperation(value = "Read individual by CAS ID", response = IndividualComposite.class)
  public Response readIndividualByCasId(@PathParam("casId") String casId) {
    try {

      List<Entity> results = crudService.findByAttribute( createSearchCriteria("auth","authid",casId,IndividualComposite.class) );

      if (results.size() == 0)
        throw new NedException(EntityNotFound, "Individual not found with CAS id: " + casId);

      Auth auth = (Auth) results.get(0);

      return Response.status(Response.Status.OK).entity(
          namedEntityService.findComposite(auth.getNedid(), IndividualComposite.class)).build();
    } catch (NedException e) {
      return nedError(e, "Find individual by CAS id failed");
    } catch (Exception e) {
      return serverError(e, "Find individual by CAS id failed");
    }
  }
  
  /* ----------------------------------------------------------------------- */
  /*  PROFILE CRUD                                                           */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/individualprofiles")
  @ApiOperation(value = "Add profile", response = Individualprofile.class)
  public Response addProfile(@PathParam("nedId") int nedId,
                             Individualprofile entity,
                             @HeaderParam("Authorization") String authstring) {
    return createEntity(nedId, entity, authstring);
  }

  @PUT
  @Path("/{nedId}/individualprofiles/{profileId}")
  @ApiOperation(value = "Update a profile", response = Individualprofile.class)
  public Response updateProfile(@PathParam("nedId")     int nedId,
                                @PathParam("profileId") int profileId,
                                @HeaderParam("Authorization") String authstring,
                                Individualprofile entity) {
    return updateEntity(nedId, profileId, entity, authstring);
  }

  @DELETE
  @Path("/{nedId}/individualprofiles/{profileId}")
  @ApiOperation(value = "Delete a profile")
  public Response deleteProfile(@PathParam("nedId")     int nedId,
                                @PathParam("profileId") int profileId,
                                @HeaderParam("Authorization") String authstring) {
    //TODO: process authstring

    if (((List)(getEntities(nedId, Individualprofile.class).getEntity())).size() == 1)
      return nedError(new NedException("Profile entities cannot be empty"), "Unable to delete profile");

    return deleteEntity(nedId, profileId, Individualprofile.class);
  }

  @GET
  @Path("/{nedId}/individualprofiles/{profileId}")
  @ApiOperation(value = "Read profile", response = Individualprofile.class)
  public Response getProfile(@PathParam("nedId")     int nedId,
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
                            @PathParam("id")    int id,
                            @HeaderParam("Authorization") String authstring) {
    //TODO: process authstring
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
  public Response createRole(@PathParam("nedId") int nedId,
                             Role roleEntity,
                             @HeaderParam("Authorization") String authstring) {
    return createEntity(nedId, roleEntity, authstring);
  }

  @PUT
  @Path("/{nedId}/roles/{roleId}")
  @ApiOperation(value = "Update role", response = Role.class)
  public Response updateRole(@PathParam("nedId") int nedId,
                             @PathParam("roleId") int roleId,
                             @HeaderParam("Authorization") String authstring,
                             Role roleEntity) {
    return updateEntity(nedId, roleId, roleEntity, authstring);
  }

  @DELETE
  @Path("/{nedId}/roles/{roleId}")
  @ApiOperation(value = "Delete role")
  public Response deleteRole(@PathParam("nedId")  int nedId,
                             @PathParam("roleId") int roleId,
                             @HeaderParam("Authorization") String authstring) {
    //TODO: process authstring
    return deleteEntity(nedId, roleId, Role.class);
  }

  @GET
  @Path("/{nedId}/roles/{roleId}")
  @ApiOperation(value = "Read role", response = Role.class)
  public Response getRole(@PathParam("nedId")  int nedId,
                          @PathParam("roleId") int roleId) {
    return getEntity(nedId, roleId, Role.class);
  }

  @GET
  @Path("/{nedId}/roles")
  @ApiOperation(value = "List roles")
  public Response getRoles(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Role.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  RELATIONSHIP CRUD                                                      */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/relationships")
  @ApiOperation(value = "Create relationship", response = Relationship.class)
  public Response createRelationship(@PathParam("nedId") int nedId, Relationship relationshipEntity) {
    return createEntity(nedId, relationshipEntity);
  }

  @PUT
  @Path("/{nedId}/relationships/{relationshipId}")
  @ApiOperation(value = "Update relationship", response = Relationship.class)
  public Response updateRelationship(@PathParam("nedId")          int nedId,
                                     @PathParam("relationshipId") int relationshipId,
                                     Relationship relationshipEntity) {
    return updateEntity(nedId, relationshipId, relationshipEntity);
  }

  @DELETE
  @Path("/{nedId}/relationships/{relationshipId}")
  @ApiOperation(value = "Delete relationship")
  public Response deleteRelationship(@PathParam("nedId")          int nedId,
                                     @PathParam("relationshipId") int relationshipId) {
    return deleteEntity(nedId, relationshipId, Relationship.class);
  }

  @GET
  @Path("/{nedId}/relationships/{relationshipId}")
  @ApiOperation(value = "Read relationship", response = Relationship.class)
  public Response getRelationship(@PathParam("nedId")          int nedId,
                                  @PathParam("relationshipId") int relationshipId) {
    return getEntity(nedId, relationshipId, Relationship.class);
  }

  @GET
  @Path("/{nedId}/relationships")
  @ApiOperation(value = "List relationships")
  public Response getRelationship(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Relationship.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  AUTH CRUD                                                              */
  /* ----------------------------------------------------------------------- */

  @GET
  @Path("/{nedId}/auth")
  @ApiOperation(value = "List auth record(s)")
  public Response getAuthRecord(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Auth.class);
  }

  @PUT
  @Path("/{nedId}/auth/{authId}")
  @ApiOperation(value = "Update auth record", response = Auth.class)
  public Response updateAuthRecord(@PathParam("nedId")  int nedId,
                                   @PathParam("authId") int authId,
                                   @HeaderParam("Authorization") String authstring,
                                   Auth authEntity) {
    return updateEntity(nedId, authId, authEntity, authstring);
  }
}
