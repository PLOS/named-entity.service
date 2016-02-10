package org.plos.namedentity.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.Alert;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by jfinger on 12/21/15.
 */
@Path("queries")
@Api(value="queries", authorizations = {@Authorization(value = "basic")})
@Produces(MediaType.APPLICATION_JSON)
public class QueriesResource extends BaseResource {

  @GET
  @Path("/alerts")
  @ApiOperation(value="Get a list of search alerts by type", response=Alert.class, responseContainer = "List")
  public Response getAlerts(@QueryParam("frequency") String frequency) {
    try {
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Alert>>(
              namedEntityService.getAlerts(frequency)
          ) {
          }).build();
    } catch (NedException e) {
      return nedError(e, "Fetching alerts failed");
    } catch (Exception e) {
      return serverError(e, "Fetching alerts failed");
    }

  }

  protected String getNamedPartyType() {
    throw new UnsupportedOperationException("not implemented");
  }
}
