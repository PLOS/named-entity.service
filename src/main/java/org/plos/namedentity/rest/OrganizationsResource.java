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
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.OrganizationComposite;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.Organization;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.api.enums.UidTypeEnum;
import org.plos.namedentity.api.ringgold.Institution;
import org.plos.namedentity.service.RinggoldService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
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
    return OrganizationComposite.typeName;
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
              Integer ringgold_pcode = Integer.parseInt(uid.getUniqueidentifier());

              Institution ifilter = new Institution();
              ifilter.setPCode(ringgold_pcode);

              List<Institution> ringgoldResults = ringgoldService.findByAttribute(ifilter);

              if (ringgoldResults.size() == 0)
                throw new NedException(InstitutionNotFound);

              for (Institution ins : ringgoldResults) {
                composite.setLegalname(ins.getName());
                composite.setFamiliarname(ins.getName());

                // TODO: search orgs for ringgold uid and return


                try {
                  composite.setNedid(namedEntityService.findResolvedEntityByUid(UidTypeEnum.RINGGOLD.getName(), uid.getUniqueidentifier(), Organization.class).getNedid());
                }
                catch (NedException e) {
                  // if it is not found, dont bother setting a value
                  ;
                }


                // TODO: populate address here or leave it to a mysql view
              }
            });




      // TODO: handle the case where the ringgold ID is in the UIDs table, but is not associated with an existing NEDID? wait. is this possible?





      if (composite.getNedid()  != null)
        return Response.status(Response.Status.OK).entity(
          namedEntityService.findComposite(composite.getNedid(), OrganizationComposite.class)).build();

      List<Entity> results = crudService.findByAttribute(createSearchCriteria("organization", "legalname", composite.getLegalname(), OrganizationComposite.class));

      if (results.size() == 0)
        return Response.status(Response.Status.OK).entity(
            namedEntityService.createComposite(composite, OrganizationComposite.class)).build();

      return Response.status(Response.Status.OK).entity(
          namedEntityService.findComposite(results.get(0).getNedid(), OrganizationComposite.class)).build();

    } catch (NedException e) {
      return nedError(e, "Unable to create organization");
    } catch (Exception e) {
      return serverError(e, "Unable to create organization");
    }
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @ApiOperation(value = "Find organizations matching specified attribute.")
  public Response findOrganizations(@QueryParam("attribute") String attribute,
                                    @QueryParam("value") String value) {

    try {
      if (isEmptyOrBlank(attribute) || isEmptyOrBlank(value)) {
        throw new NedException(InvalidOrganizationSearchQuery);
      }

      List<Entity> results = crudService.findByAttribute(createSearchCriteria("organization", attribute, value, OrganizationComposite.class));

      if (results.size() == 0)
        throw new NedException(EntityNotFound, "Organization not found");
      else if (results.size() > 10)
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
