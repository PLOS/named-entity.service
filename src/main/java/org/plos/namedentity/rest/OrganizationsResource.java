package org.plos.namedentity.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
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
@Api("/organizations")
public class OrganizationsResource extends BaseResource {

  @POST
  @ApiOperation(value = "Create", response =  OrganizationEntity.class)
  public Response create(OrganizationEntity object) {
    try {
      return Response.ok(namedEntityService.createOrganization(object)).build();
    } catch (NedValidationException e) {
      return validationError(e, "Unable to create organization");
    } catch (Exception e) {
      return serverError(e, "Unable to create organization");
    }
  }

  @GET
  @Path("/{nedId}")
  @ApiOperation(value = "Read", response =  OrganizationEntity.class)
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
  @ApiOperation(value = "List")
  public Response list() {
    return Response.ok(new GenericEntity<List<OrganizationEntity>>
        (crudService.findAll(OrganizationEntity.class)) {
    }).build();
  }

  @GET
  @Path("/{nedId}/emails")
  @ApiOperation("List emails")
  public Response getEmails(@PathParam("nedId") int nedId) {
    return getEmails(nedId, OrganizationEntity.class);
  }

  @GET
  @Path("/{nedId}/addresses")
  @ApiOperation("List addresses")
  public Response getAddresses(@PathParam("nedId") int nedId) {
    return getAddresses(nedId, OrganizationEntity.class);
  }

  @GET
  @Path("/{nedId}/phonenumbers")
  @ApiOperation("List phone numbers")
  public Response getPhonenumbers(@PathParam("nedId") int nedId) {
    return getPhonenumbers(nedId, OrganizationEntity.class);
  }

  @GET
  @Path("/{nedId}/xref")
  @ApiOperation("List references")
  public Response getExternalReferences(@PathParam("nedId") int nedId) {
    return getExternalReferences(nedId, OrganizationEntity.class);
  }

}
