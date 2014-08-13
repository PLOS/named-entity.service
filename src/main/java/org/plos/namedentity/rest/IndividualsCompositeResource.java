package org.plos.namedentity.rest;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.IndividualEntity;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/individuals_composite")
public class IndividualsCompositeResource extends BaseResource {

  @POST
  public Response create(IndividualComposite object) {
    try {
      IndividualEntity individual = namedEntityService.createIndividual(object);
      return Response.status(Response.Status.OK).entity(individual).build();  // TODO: why not return the composite here? with nested composite NED IDs
    }
    catch(NedValidationException e) {
      return validationError(e, "Unable to create individual");
    }
    catch(Exception e) {
      return serverError(e, "Unable to create individual");
    }
  }

}
