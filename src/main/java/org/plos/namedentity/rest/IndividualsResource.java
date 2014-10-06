package org.plos.namedentity.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.plos.namedentity.api.EntityNotFoundException;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Degree;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Individual;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Uniqueidentifier;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/individuals")
@Api(value="/individuals")
public class IndividualsResource extends BaseResource {

  @POST
  @ApiOperation(value = "Create individual composite", response = IndividualComposite.class)
  public Response createComposite(IndividualComposite composite) {
    try {
      return Response.status(Response.Status.OK).entity(
          namedEntityService.createIndividualComposite(composite)).build();
    } catch (NedValidationException e) {
      return validationError(e, "Unable to create individual composite");
    } catch (Exception e) {
      return serverError(e, "Unable to create individual composite");
    }
  }

  @GET
  @Path("/{nedId}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @ApiOperation(value = "Read individual composite", response = IndividualComposite.class)
  public Response readComposite(@PathParam("nedId") int nedId) {
    try {
      return Response.status(Response.Status.OK).entity(
          namedEntityService.findIndividualComposite(nedId)).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Unable to read individual composite");
    }
  }

  @GET
  @Path("/{uidType}/{uidValue}")
  @ApiOperation(value = "Read individual composite", response = IndividualComposite.class)
  public Response readCompositeByUid(@PathParam("uidType") String uidType,
                                     @PathParam("uidValue") String uidValue) {
    try {

      Individual individual = namedEntityService.findResolvedEntityByUid(
          uidType, uidValue, Individual.class);

      return Response.status(Response.Status.OK).entity(
          namedEntityService.findIndividualComposite(individual.getNedid())).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Find all individuals failed");
    }
  }

  @GET
  @ApiOperation(value = "List individual entities", response = Individual.class)
  public Response listIndividuals(
      @ApiParam(required = false) @QueryParam("offset") Integer offset,
      @ApiParam(required = false) @QueryParam("limit") Integer limit) {
    try {

      // TODO: nest/group by NedId?

      if (offset == null || offset < 0)
        offset = 0;
      if (limit == null || limit <= 0 || limit > MAX_RESULT_COUNT)
        limit = DEFAULT_RESULT_COUNT;

      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Individual>>(
              crudService.findAll(Individual.class, offset, limit)) {
          }).build();
    } catch (Exception e) {
      return serverError(e, "Find all individuals failed");
    }
  }

  /* ----------------------------------------------------------------------- */
  /*  INDIVIDUAL CRUD                                                        */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/individuals")
  @ApiOperation(value = "Add individual entity to composite", response = IndividualComposite.class)
  public Response addIndividual(@PathParam("nedId") int nedId, Individual entity) {
    return createEntity(nedId, entity, Individual.class);
  }

  @POST
  @Path("/{nedId}/individuals/{id}")
  @ApiOperation(value = "Update a single individual entity", response = Individual.class)
  public Response updateIndividual(@PathParam("nedId") int nedId,
                         @PathParam("id") int id, Individual entity) {

    return updateEntity(nedId, id, entity, Individual.class);
  }

  @DELETE
  @Path("/{nedId}/individuals/{id}")
  @ApiOperation(value = "Delete a single individual entity")
  public Response deleteIndividual(@PathParam("nedId") int nedId, @PathParam("id") int id) {
    return deleteEntity(nedId, id, Individual.class, Individual.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  EMAIL CRUD                                                             */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/emails")
  @ApiOperation(value = "Create email", response = Email.class)
  public Response createEmail(@PathParam("nedId") int nedId,
                              Email emailEntity) {
    return createEntity(nedId, emailEntity, Individual.class);
  }

  @POST
  @Path("/{nedId}/emails/{emailId}")
  @ApiOperation(value = "Update email", response = Email.class)
  public Response updateEmail(@PathParam("nedId") int nedId, 
                              @PathParam("emailId") int emailId, 
                              Email emailEntity) {
    return updateEntity(nedId, emailId, emailEntity, Individual.class);
  }

  @DELETE
  @Path("/{nedId}/emails/{emailId}")
  @ApiOperation(value = "Delete email")
  public Response deleteEmail(@PathParam("nedId") int nedId, 
                              @PathParam("emailId") int emailId) {
    return deleteEntity(nedId, emailId, Email.class, Individual.class);
  }

  @GET
  @Path("/{nedId}/emails/{emailId}")
  @ApiOperation(value = "Read email", response = Email.class)
  public Response getEmail(@PathParam("nedId") int nedId,
                           @PathParam("emailId") int emailId) {
    return getEntity(nedId, emailId, Email.class, Individual.class);
  }

  @GET
  @Path("/{nedId}/emails")
  @ApiOperation(value = "List emails", response = Email.class)
  public Response getEmails(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Email.class, Individual.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  ADDRESS CRUD                                                           */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/addresses")
  @ApiOperation(value = "Create address", response = Address.class)
  public Response createAddress(@PathParam("nedId") int nedId,
                                Address addressEntity) {
    return createEntity(nedId, addressEntity, Individual.class);
  }

  @POST
  @Path("/{nedId}/addresses/{addressId}")
  @ApiOperation(value = "Update address", response = Address.class)
  public Response updateAddress(@PathParam("nedId") int nedId, 
                                @PathParam("addressId") int addressId, 
                                Address addressEntity) {
    return updateEntity(nedId, addressId, addressEntity, Individual.class);
  }

  @DELETE
  @Path("/{nedId}/addresses/{addressId}")
  @ApiOperation(value = "Delete address")
  public Response deleteAddress(@PathParam("nedId") int nedId, 
                                @PathParam("addressId") int addressId) {
    return deleteEntity(nedId, addressId, Address.class, Individual.class);
  }

  @GET
  @Path("/{nedId}/addresses/{addressId}")
  @ApiOperation(value = "Read address", response = Address.class)
  public Response getAddress(@PathParam("nedId") int nedId,
                             @PathParam("addressId") int addressId) {
    return getEntity(nedId, addressId, Address.class, Individual.class);
  }

  @GET
  @Path("/{nedId}/addresses")
  @ApiOperation(value = "List addresses", response = Address.class)
  public Response getAddresses(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Address.class, Individual.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  PHONE NUMBER CRUD                                                      */
  /* ----------------------------------------------------------------------- */

  @GET
  @Path("/{nedId}/phonenumbers")
  @ApiOperation(value = "List phone numbers", response = Phonenumber.class)
  public Response getPhonenumbers(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Phonenumber.class, Individual.class);
  }


  /* ----------------------------------------------------------------------- */
  /*  REFERENCES CRUD                                                        */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/xref")
  @ApiOperation(value = "Create reference", response = Uniqueidentifier.class)
  public Response createReference(@PathParam("nedId") int nedId,
                                 Uniqueidentifier entity) {
    return createEntity(nedId, entity, Individual.class);
  }

  @POST
  @Path("/{nedId}/xref/{id}")
  @ApiOperation(value = "Update reference", response = Uniqueidentifier.class)
  public Response updateReference(@PathParam("nedId") int nedId,
                                  @PathParam("id") int id,
                                  Uniqueidentifier entity) {
    return updateEntity(nedId, id, entity, Individual.class);
  }

  @DELETE
  @Path("/{nedId}/xref/{id}")
  @ApiOperation(value = "Delete reference")
  public Response deleteReference(@PathParam("nedId") int nedId,
                                  @PathParam("id") int id) {
    return deleteEntity(nedId, id, Uniqueidentifier.class, Individual.class);
  }

  @GET
  @Path("/{nedId}/xref")
  @ApiOperation(value = "List references")
  public Response getExternalReferences(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Uniqueidentifier.class, Individual.class);
  }


  /* ----------------------------------------------------------------------- */
  /*  DEGREES CRUD                                                           */
  /* ----------------------------------------------------------------------- */

  @GET
  @Path("/{nedId}/degrees")
  @ApiOperation(value = "List degrees")
  public Response getDegrees(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Degree.class, Individual.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  ROLE CRUD                                                              */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/roles")
  @ApiOperation(value = "Create role", response = Role.class)
  public Response createRole(@PathParam("nedId") int nedId, Role roleEntity) {
    return createEntity(nedId, roleEntity, Individual.class);
  }

  @POST
  @Path("/{nedId}/roles/{roleId}")
  @ApiOperation(value = "Update role", response = Role.class)
  public Response updateRole(@PathParam("nedId") int nedId, 
                             @PathParam("roleId") int roleId, 
                             Role roleEntity) {
    return updateEntity(nedId, roleId, roleEntity, Individual.class);
  }

  @DELETE
  @Path("/{nedId}/roles/{roleId}")
  @ApiOperation(value = "Delete role")
  public Response deleteRole(@PathParam("nedId") int nedId, 
                             @PathParam("roleId") int roleId) {
    return deleteEntity(nedId, roleId, Role.class, Individual.class);
  }

  @GET
  @Path("/{nedId}/roles/{roleId}")
  @ApiOperation(value = "Read role", response = Role.class)
  public Response getRole(@PathParam("nedId") int nedId,
                          @PathParam("roleId") int roleId) {
    return getEntity(nedId, roleId, Role.class, Individual.class);
  }

  @GET
  @Path("/{nedId}/roles")
  @ApiOperation(value = "List roles")
  public Response getRoles(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Role.class, Individual.class);
  }
}
