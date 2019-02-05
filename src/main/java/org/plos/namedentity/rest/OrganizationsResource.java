/*
 * Copyright (c) 2014-2019 Public Library of Science
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
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.OrganizationComposite;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.Organization;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.api.enums.UidTypeEnum;
import org.plos.namedentity.api.ringgold.Institution;
import org.plos.namedentity.api.enums.NamedPartyEnum;
import org.plos.namedentity.service.RinggoldService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;
import static org.plos.namedentity.api.NedException.ErrorType.InstitutionNotFound;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidOrganizationSearchQuery;
import static org.plos.namedentity.api.NedException.ErrorType.TooManyResultsFound;


@Path("/organizations")
@Api("/organizations")
public class OrganizationsResource extends NedResource {

  @Inject
  protected RinggoldService ringgoldService;

  @Override
  protected String getNamedPartyType() {
    return NamedPartyEnum.ORGANIZATION.getName();
  }

  @POST
  @ApiOperation(value = "Create organization", response = OrganizationComposite.class)
  public Response createOrganization(OrganizationComposite composite,
                                     @HeaderParam("Authorization") String authstring) {
    try {
      setCreatedAndLastModifiedBy(authstring, composite);

      List<Uniqueidentifier> uids = composite.getUniqueidentifiers();

      if (uids != null)
        uids.stream().filter(uid -> uid.getType().equalsIgnoreCase("Ringgold")).findFirst().ifPresent(
            uid -> {
              Integer ringgold_id = Integer.parseInt(uid.getUniqueidentifier());

              Institution ifilter = new Institution();
              ifilter.setRinggoldId(ringgold_id);

              List<Institution> ringgoldResults = ringgoldService.findByAttribute(ifilter);

              // if Ringgold ID is not found in DB, error out
              if (ringgoldResults.size() == 0)
                throw new NedException(InstitutionNotFound);

              // replace the name in the input with name from Ringgold
              Institution ins = ringgoldResults.get(0);

              composite.setLegalname(ins.getName());
              composite.setFamiliarname(ins.getName());

              // look to see if an org already references this Ringgold ID
              try {
                composite.setNedid(namedEntityService.findResolvedEntityByUid(UidTypeEnum.RINGGOLD.getName(), uid.getUniqueidentifier(), Organization.class).getNedid());
              }
              catch (NedException e) {
                // if org is not found, no big deal
              }

              // TODO: populate address here or leave it to a mysql view for reporting

            });

      // if the org exists in NED already, just return it
      if (composite.getNedid() != null)
        return Response.status(Response.Status.OK).entity(
          namedEntityService.findComposite(composite.getNedid(), OrganizationComposite.class)).build();

      List<Entity> results = crudService.findByAttribute(createSearchCriteria("organization", "legalname", composite.getLegalname(), OrganizationComposite.class), false);

      // if the org does not exist by name, insert into DB
      if (results.size() == 0)
        return Response.status(Response.Status.OK).entity(
            namedEntityService.createComposite(composite, OrganizationComposite.class)).build();

      // else return the already existing org
      return Response.status(Response.Status.OK).entity(
          namedEntityService.findComposite(results.get(0).getNedid(), OrganizationComposite.class)).build();

    } catch (NedException e) {
      return nedError(e, "Unable to create organization");
    } catch (Exception e) {
      return serverError(e, "Unable to create organization");
    }
  }

  @GET
  @ApiOperation(value = "Find organizations matching specified attribute.",
          response = OrganizationComposite.class, responseContainer = "List")
  public Response findOrganizations(@QueryParam("attribute") String attribute,
                                    @QueryParam("value") String value) {

    try {
      if (isEmptyOrBlank(attribute) || isEmptyOrBlank(value)) {
        throw new NedException(InvalidOrganizationSearchQuery);
      }

      List<Entity> results = crudService.findByAttribute(createSearchCriteria("organization", attribute, value, OrganizationComposite.class), false);

      if (results.size() == 0)
        throw new NedException(EntityNotFound, "Organization not found");
      else if (results.size() > DEFAULT_RESULT_COUNT)
        throw new NedException(TooManyResultsFound);

      // entity records may refer to the same individual. we can filter these out
      // by adding ned id's to a set.

      Set<Integer> nedids = new HashSet<>();
      for (Entity e : results) {
        nedids.add(e.getNedid());
      }

      // lookup composites for ned id's

      List<OrganizationComposite> composites = new ArrayList<>();
      for (Integer nedid : nedids) {
        composites.add(namedEntityService.findComposite(nedid, OrganizationComposite.class));
      }

      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<OrganizationComposite>>(composites){}).build();

    } catch (NedException e) {
      return nedError(e, "findOrganizations() failed");
    } catch (Exception e) {
      return serverError(e, "findOrganizations() failed");
    }
  }

  @GET
  @Path("/{nedId}")
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
  public Response readOrganizationByUid(@PathParam("uidType")  String uidType,
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
