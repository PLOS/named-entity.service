package org.plos.namedentity.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.plos.namedentity.api.EntityNotFoundException;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.IndividualEntity;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/individuals_composite")
@Api("/individuals_composite")
public class IndividualsCompositeResource extends BaseResource {

  @POST
  @ApiOperation(value = "Create", response = IndividualComposite.class)
  public Response create(IndividualComposite object) {
    try {
      return Response.status(Response.Status.OK).entity(
          namedEntityService.createIndividualComposite(object)).build();
    } catch (NedValidationException e) {
      return validationError(e, "Unable to create individual composite");
    } catch (Exception e) {
      return serverError(e, "Unable to create individual composite");
    }
  }

  @GET
  @Path("/{id}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @ApiOperation(value = "Read", response = IndividualEntity.class)
  public Response read(@PathParam("id") int nedId) {
    try {
      return Response.status(Response.Status.OK).entity(
          namedEntityService.findIndividualComposite(nedId)).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Unable to read individual composite");
    }
  }

}
