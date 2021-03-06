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
import io.swagger.annotations.Authorization;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.adapter.Container;
import org.plos.namedentity.api.entity.Alert;
import org.plos.namedentity.api.entity.Auth;
import org.plos.namedentity.api.entity.Degree;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.Group;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.api.entity.Namedentityidentifier;
import org.plos.namedentity.api.entity.Relationship;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.api.enums.NamedPartyEnum;
import org.plos.namedentity.service.PasswordDigestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidIndividualSearchQuery;
import static org.plos.namedentity.api.NedException.ErrorType.PasswordNotSpecified;
import static org.plos.namedentity.api.NedException.ErrorType.TooManyResultsFound;

@Path("individuals")
@Api(value="individuals", authorizations = {@Authorization(value = "basic")})
public class IndividualsResource extends NedResource {

  private static final Logger logger = LoggerFactory.getLogger(IndividualsResource.class);

  @Override
  protected String getNamedPartyType() {
    return NamedPartyEnum.INDIVIDUAL.getName();
  }

  @POST
  @ApiOperation(value = "Create individual", response = IndividualComposite.class)
  public Response createIndividual(IndividualComposite composite,
                                   @HeaderParam("Authorization") String authstring) {
    try {
      generateDisplaynameIfEmpty(composite);

      setCreatedAndLastModifiedBy(authstring, composite);

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
    if (profiles != null && profiles.size() > 0) {
      Individualprofile profile = profiles.get(0);
      if (isEmptyOrBlank(profile.getDisplayname())) {
        profile.setDisplayname(namedEntityService.generateDisplayname(profile, new Random()));
      }
    }
  }

  @GET
  @ApiOperation(value = "Find individual matching specified attribute.", response = IndividualComposite.class, responseContainer = "List")
  public Response findIndividuals(@QueryParam("entity")    String entity,
                                  @QueryParam("attribute") String attribute,
                                  @QueryParam("value") String value,
                                  @DefaultValue("false")
                                    @QueryParam("partial") Boolean partial  ) {

    try {
      if (isEmptyOrBlank(entity) || isEmptyOrBlank(attribute) || isEmptyOrBlank(value)) {
        throw new NedException(InvalidIndividualSearchQuery);
      }

      List<Entity> results = crudService.findByAttribute(createSearchCriteria(entity,attribute,value,IndividualComposite.class), partial);

      if (results.size() == 0)
        throw new NedException(EntityNotFound, "Individual not found");
      else if (results.size() > DEFAULT_RESULT_COUNT)
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

  @DELETE
  @Path("/{nedId}")
  @ApiOperation(value = "Delete individual", response = IndividualComposite.class)
  public Response deleteIndividual(@PathParam("nedId") int nedId) {
    try {

      namedEntityService.checkNedIdForType(nedId, getNamedPartyType());

      namedEntityService.deleteIndividual(nedId);  // TODO: validate response code

      crudService.delete(crudService.findById(nedId, Namedentityidentifier.class));

      return Response.status(Response.Status.NO_CONTENT).build();

    } catch (NedException e) {
      return nedError(e, "Unable to delete individual");
    } catch (Exception e) {
      return serverError(e, "Unable to delete individual");
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

      List<Entity> results = crudService.findByAttribute( createSearchCriteria("auth","authid",casId,IndividualComposite.class), false );

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
  @ApiOperation(value = "List profiles", response = Individualprofile.class, responseContainer = "List")
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
  @ApiOperation(value = "List degrees", response = Degree.class, responseContainer = "List")
  public Response getDegrees(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Degree.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  GROUPS CRUD                                                            */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/groups")
  @ApiOperation(value = "Create group", response = Group.class)
  public Response createGroup(@PathParam("nedId") int nedId,
                              Group groupEntity,
                              @HeaderParam("Authorization") String authstring) {
    return createEntity(nedId, groupEntity, authstring);
  }

  @PUT
  @Path("/{nedId}/groups/{groupId}")
  @ApiOperation(value = "Update group", response = Group.class)
  public Response updateGroup(@PathParam("nedId") int nedId,
                              @PathParam("groupId") int groupId,
                              @HeaderParam("Authorization") String authstring,
                              Group groupEntity) {
    return updateEntity(nedId, groupId, groupEntity, authstring);
  }

  @DELETE
  @Path("/{nedId}/groups/{groupId}")
  @ApiOperation(value = "Delete group")
  public Response deleteGroup(@PathParam("nedId")  int nedId,
                              @PathParam("groupId") int groupId,
                              @HeaderParam("Authorization") String authstring) {
    //TODO: process authstring
    return deleteEntity(nedId, groupId, Group.class);
  }

  @GET
  @Path("/{nedId}/groups/{groupId}")
  @ApiOperation(value = "Read group", response = Group.class)
  public Response getGroup(@PathParam("nedId")  int nedId,
                           @PathParam("groupId") int groupId) {
    return getEntity(nedId, groupId, Group.class);
  }

  @GET
  @Path("/{nedId}/groups")
  @ApiOperation(value = "List groups", response = Group.class, responseContainer = "List")
  public Response getGroups(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Group.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  ALERTS CRUD                                                            */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/alerts")
  @ApiOperation(value = "Create alert", response = Alert.class)
  public Response createAlert(@PathParam("nedId") int nedId,
                              Alert entity,
                              @HeaderParam("Authorization") String authstring) {
    return createEntity(nedId, entity, authstring);
  }

  @PUT
  @Path("/{nedId}/alerts/{alertId}")
  @ApiOperation(value = "Update alert", response = Alert.class)
  public Response updateAlert(@PathParam("nedId") int nedId,
                              @PathParam("alertId") int entityId,
                              @HeaderParam("Authorization") String authstring,
                              Alert entity) {
    return updateEntity(nedId, entityId, entity, authstring);
  }

  @DELETE
  @Path("/{nedId}/alerts/{alertId}")
  @ApiOperation(value = "Delete alert")
  public Response deleteAlert(@PathParam("nedId")  int nedId,
                              @PathParam("alertId") int entityId,
                              @HeaderParam("Authorization") String authstring) {
    //TODO: process authstring
    return deleteEntity(nedId, entityId, Alert.class);
  }

  @GET
  @Path("/{nedId}/alerts/{alertId}")
  @ApiOperation(value = "Read group", response = Alert.class)
  public Response getAlert(@PathParam("nedId")  int nedId,
                           @PathParam("alertId") int entityId) {
    return getEntity(nedId, entityId, Alert.class);
  }

  @GET
  @Path("/{nedId}/alerts")
  @ApiOperation(value = "List alerts", response = Alert.class, responseContainer = "List")
  public Response getAlerts(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Alert.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  RELATIONSHIP CRUD                                                      */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/relationships")
  @ApiOperation(value = "Create relationship", response = Relationship.class)
  public Response createRelationship(@PathParam("nedId") int nedId,
                                     Relationship relationshipEntity,
                                     @HeaderParam("Authorization") String authstring) {
    return createEntity(nedId, relationshipEntity, authstring);
  }

  @PUT
  @Path("/{nedId}/relationships/{relationshipId}")
  @ApiOperation(value = "Update relationship", response = Relationship.class)
  public Response updateRelationship(@PathParam("nedId")          int nedId,
                                     @PathParam("relationshipId") int relationshipId,
                                     @HeaderParam("Authorization") String authstring,
                                     Relationship relationshipEntity) {
    return updateEntity(nedId, relationshipId, relationshipEntity, authstring);
  }

  @DELETE
  @Path("/{nedId}/relationships/{relationshipId}")
  @ApiOperation(value = "Delete relationship")
  public Response deleteRelationship(@PathParam("nedId")          int nedId,
                                     @PathParam("relationshipId") int relationshipId,
                                     @HeaderParam("Authorization") String authstring) {
    //TODO: process authstring
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
  @ApiOperation(value = "List relationships", response = Relationship.class, responseContainer = "List")
  public Response getRelationships(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Relationship.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  AUTH CRUD                                                              */
  /* ----------------------------------------------------------------------- */

  @GET
  @Path("/{nedId}/auth")
  @ApiOperation(value = "List auth record(s)", response = Auth.class, responseContainer = "List")
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

  @POST
  @Path("/{nedId}/auth/checkpassword")
  @ApiOperation(value = "Validate password")
  public Response checkPassword(@PathParam("nedId") int nedId,
                                Auth authEntity) {
    try {
      if (isEmptyOrBlank(authEntity.getPlainTextPassword()))
        throw new NedException(PasswordNotSpecified);

      List<Auth> results = namedEntityService.findResolvedEntities(nedId, Auth.class);

      if (results.size() == 0)
        throw new NedException(EntityNotFound, "AuthCas record not found with ned id: " + nedId);

      Auth auth = (Auth) results.get(0);
      boolean verifyResult = new PasswordDigestService()
        .verifyPassword(authEntity.getPlainTextPassword(), auth.getPassword());

      Map<String,String> map = new HashMap();
      map.put("valid", new Boolean(verifyResult).toString());

      Container c = new Container();
      c.setMap(map);

      return Response.status(Response.Status.OK).entity(c).build();

    } catch (NedException e) {
      return nedError(e, "Check password failed");
    } catch (Exception e) {
      return serverError(e, "Check password failed");
    }
  }
}
