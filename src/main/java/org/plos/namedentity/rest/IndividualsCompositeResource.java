package org.plos.namedentity.rest;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/individuals_composite")
public class IndividualsCompositeResource extends BaseResource {

  @POST
  public Response create(IndividualComposite object) {
    try {
      return Response.status(Response.Status.OK).entity(namedEntityService.createIndividualComposite(object)).build();
    } catch (NedValidationException e) {
      return validationError(e, "Unable to create individual");
    } catch (Exception e) {
      return serverError(e, "Unable to create individual");
    }
  }

  @GET
  @Path("/{id}")
  public Response read(@PathParam("id") int nedId) {
    try {
      return Response.status(Response.Status.OK).entity(namedEntityService.findIndividualComposite(nedId)).build();
    } catch (Exception e) {
      return serverError(e, "Unable to create individual");
    }
  }

}
