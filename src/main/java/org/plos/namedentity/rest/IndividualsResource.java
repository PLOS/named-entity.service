package org.plos.namedentity.rest;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.dto.PhonenumberDTO;
import org.plos.namedentity.api.dto.RoleDTO;
import org.plos.namedentity.api.dto.UniqueidentifierDTO;
import org.plos.namedentity.api.entity.AddressEntity;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.IndividualEntity;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/individuals")
public class IndividualsResource extends BaseResource {

  @POST
  public Response createIndividualComposite(IndividualComposite object) {
    try {
      IndividualEntity individual = namedEntityService.createIndividual(object);
      return Response.status(Response.Status.OK).entity(individual).build();
    }
    catch(NedValidationException e) {
      return validationError(e, "Unable to create individual");
    }
    catch(Exception e) {
      return serverError(e, "Unable to create individual");
    }
  }

  @GET
  public Response getAllIndividuals() {
    try {
      List<IndividualEntity> individuals = crudService.findAll(IndividualEntity.class);
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<IndividualEntity>>(individuals){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find all individuals failed");
    }
  }


  @GET
  @Path("/{id}")
  public Response getIndividual(@PathParam("id") int id) {
    try {
      IndividualEntity individual = namedEntityService.findIndividualByNedId(id);
      return Response.status(Response.Status.OK).entity(individual).build();
    }
    catch(Exception e) {
      return serverError(e, "Find individual by id failed");
    }
  }

  @GET
  @Path("/{id}/emails")
  public Response getEmailsForIndividual(@PathParam("id") int nedId) {
    try {
      List<EmailEntity> emails = namedEntityService.findEmailsByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<EmailEntity>>(emails){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find emails by nedId failed");
    }
  }

  @GET
  @Path("/{id}/addresses")
  public Response getAddressesForIndividual(@PathParam("id") int nedId) {
    try {
      List<AddressEntity> addresses = namedEntityService.findAddressesByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<AddressEntity>>(addresses){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find addresses by nedId failed");
    }
  }

  @GET
  @Path("/{id}/phonenumbers")
  public Response getPhonenumbersForIndividual(@PathParam("id") int nedId) {
    try {
      List<PhonenumberDTO> phonenumbers = namedEntityService.findPhoneNumbersByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<PhonenumberDTO>>(phonenumbers){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find phone numberse by nedId failed");
    }
  }

  @GET
  @Path("/{id}/roles")
  public Response getRolesForIndividual(@PathParam("id") int nedId) {
    try {
      List<RoleDTO> roles = namedEntityService.findRolesByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<RoleDTO>>(roles){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find roles by nedId failed");
    }
  }

  @GET
  @Path("/{id}/xref")
  public Response getExternalReferencesForIndividual(@PathParam("id") int nedId) {
    try {
      List<UniqueidentifierDTO> uids = namedEntityService.findUniqueIdsByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<UniqueidentifierDTO>>(uids){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find external references by nedId failed");
    }
  }

}
