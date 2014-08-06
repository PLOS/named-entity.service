package org.plos.namedentity.rest;

import org.plos.namedentity.api.entity.AddressEntity;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.OrganizationEntity;
import org.plos.namedentity.api.entity.PhonenumberEntity;
import org.plos.namedentity.api.entity.UniqueidentifierEntity;

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
  public Response createEntity(OrganizationEntity object) {

    return Response.ok(namedEntityService.createOrganization(object)).build();

    // TODO: handle runtime exception ?
  }

  @GET
  public Response getEntityList() {
    return Response.ok(new GenericEntity<List<OrganizationEntity>>
        (crudService.findAll(OrganizationEntity.class)) {
    }).build();
  }

  @GET
  @Path("/{id}")
  public Response getEntity(@PathParam("id") int nedId) {
    try {
      return Response.ok(namedEntityService.findOrganizationByNedId(nedId)).build();
    }
    catch(Exception e) {
      return serverError(e, "Find individual by id failed");
    }
  }




  // TODO: duplicated IndividualsResource.getEmailsForIndividual



  @GET
  @Path("/{id}/emails")
  public Response getEmails(@PathParam("id") int nedId) {
    try {
      return Response.ok(new GenericEntity<List<EmailEntity>>(
          namedEntityService.findEmailsByNedId(nedId, "orginzations")
          ) {}).build();
    } catch (Exception e) {
      return serverError(e, "Find emails by nedId failed");
    }
  }

  @GET
  @Path("/{id}/addresses")
  public Response getAddresses(@PathParam("id") int nedId) {
    try {
      return Response.ok(new GenericEntity<List<AddressEntity>>(
              namedEntityService.findAddressesByNedId(nedId)
          ){}).build();
    } catch(Exception e) {
      return serverError(e, "Find addresses by nedId failed");
    }
  }

  @GET
  @Path("/{id}/phonenumbers")
  public Response getPhonenumbers(@PathParam("id") int nedId) {
    try {
      return Response.ok(new GenericEntity<List<PhonenumberEntity>>(
              namedEntityService.findPhoneNumbersByNedId(nedId)
          ){}).build();
    } catch(Exception e) {
      return serverError(e, "Find phone numbers by nedId failed");
    }
  }

  @GET
  @Path("/{id}/xref")
  public Response getExternalReferences(@PathParam("id") int nedId) {
    try {
      return Response.ok(new GenericEntity<List<UniqueidentifierEntity>>(
              namedEntityService.findUniqueIdsByNedId(nedId)
          ){}).build();
    } catch(Exception e) {
      return serverError(e, "Find external references by nedId failed");
    }
  }

}
