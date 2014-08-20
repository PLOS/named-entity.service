package org.plos.namedentity.rest;

import org.plos.namedentity.api.EntityNotFoundException;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.OrganizationEntity;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/organizations")
public class OrganizationsResource extends BaseResource {

  @POST
  public Response create(OrganizationEntity object) {
    try {
      return Response.ok(namedEntityService.createOrganization(object)).build();
    } catch(NedValidationException e) {
      return validationError(e, "Unable to create individual");
    } catch(Exception e) {
      return serverError(e, "Unable to create individual");
    }
  }

  @GET
  @Path("/{nedId}")
  public Response read(@PathParam("nedId") int nedId) {
    try {
      return Response.ok(namedEntityService.findResolvedEntity(nedId, OrganizationEntity.class)).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Find organization by id failed");
    }
  }

  @GET
  public Response list() {
    return Response.ok(new GenericEntity<List<OrganizationEntity>>
        (crudService.findAll(OrganizationEntity.class)) {
    }).build();
  }

  @GET
  @Path("/{nedId}/emails")
  public Response getEmails(@PathParam("nedId") int nedId) {
    return getEmails(nedId, OrganizationEntity.class);
  }

  @GET
  @Path("/{nedId}/addresses")
  public Response getAddresses(@PathParam("nedId") int nedId) {
    return getAddresses(nedId, OrganizationEntity.class);
  }

  @GET
  @Path("/{nedId}/phonenumbers")
  public Response getPhonenumbers(@PathParam("nedId") int nedId) {
    return getPhonenumbers(nedId, OrganizationEntity.class);
  }

  @GET
  @Path("/{nedId}/xref")
  public Response getExternalReferences(@PathParam("nedId") int nedId) {
    return getExternalReferences(nedId, OrganizationEntity.class);
  }

}
