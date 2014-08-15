package org.plos.namedentity.rest;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.AddressEntity;
import org.plos.namedentity.api.entity.DegreeEntity;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.PhonenumberEntity;
import org.plos.namedentity.api.entity.RoleEntity;
import org.plos.namedentity.api.entity.UniqueidentifierEntity;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/individuals")
public class IndividualsResource extends BaseResource {

  @POST
  public Response createIndividualComposite(IndividualComposite object) {
    try {
      IndividualEntity individual = namedEntityService.createIndividual(object);
      return Response.status(Response.Status.OK).entity(individual).build();  // TODO: why not return the composite here?
    }
    catch(NedValidationException e) {
      return validationError(e, "Unable to create individual");
    }
    catch(Exception e) {
      return serverError(e, "Unable to create individual");
    }
  }

  @GET
  public Response getAllIndividuals(@QueryParam("uidType") String uidType, @QueryParam("uidValue") String uidValue) {
    try {
      List<IndividualEntity> individuals = null; 
      
      if (isEmptyOrBlank(uidType) || isEmptyOrBlank(uidValue)) {
        individuals = crudService.findAll(IndividualEntity.class);
      } else {
        individuals = namedEntityService.findIndividualsByUid(uidType, uidValue);
      }
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<IndividualEntity>>(individuals){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find all individuals failed");
    }
  }

  @GET
  @Path("/{id}")
  public Response getIndividual(@PathParam("id") int nedId) {
    try {
      IndividualEntity individual = namedEntityService.findIndividualByNedId(nedId);
      return Response.status(Response.Status.OK).entity(individual).build();
    }
    catch(Exception e) {
      return serverError(e, "Find individual by id failed");
    }
  }

  /* ----------------------------------------------------------------------- */
  /*  EMAIL CRUD                                                             */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/emails")
  public Response createEmail(@PathParam("nedId") int nedId, EmailEntity emailEntity) {
    try {
      emailEntity.setNamedentityid(nedId);
      // TODO: resolve entity here
      Integer emailId = crudService.create(emailEntity);
      EmailEntity savedEntity = namedEntityService.findEmailByPrimaryKey(emailId);
      return Response.status(Response.Status.OK).entity(savedEntity).build();
    }
    catch(NedValidationException e) {
      return validationError(e, "Unable to create email");
    }
    catch(Exception e) {
      return serverError(e, "Unable to create email");
    }
  }

  @POST
  @Path("/{nedId}/emails/{emailId}")
  public Response updateEmail(@PathParam("nedId") int nedId, 
                              @PathParam("emailId") int emailId, 
                              EmailEntity emailEntity) {
    try {
      emailEntity.setNamedentityid(nedId);
      // TODO: resolve entity here
      crudService.update(emailEntity);
      EmailEntity savedEntity = namedEntityService.findEmailByPrimaryKey(emailEntity.getEmailid());
      return Response.status(Response.Status.OK).entity(savedEntity).build();
    }
    catch(NedValidationException e) {
      return validationError(e, "Unable to update email");
    }
    catch(Exception e) {
      return serverError(e, "Unable to update email");
    }
  }

  @DELETE
  @Path("/{nedId}/emails/{emailId}")
  public Response updateEmail(@PathParam("nedId") int nedId, 
                              @PathParam("emailId") int emailId) {
    try {
      EmailEntity emailEntity = namedEntityService.findEmailByPrimaryKey(emailId);
      crudService.delete(emailEntity);
      return Response.status(Response.Status.NO_CONTENT).build();
    }
    catch(NedValidationException e) {
      return validationError(e, "Unable to delete email");
    }
    catch(Exception e) {
      return serverError(e, "Unable to delete email");
    }
  }

  @GET
  @Path("/{nedId}/emails/{emailId}")
  public Response getEmail(@PathParam("nedId") int nedId, @PathParam("emailId") int emailId) {
    try {
      EmailEntity emailEntity = namedEntityService.findEmailByPrimaryKey(emailId);
      return Response.status(Response.Status.OK).entity(emailEntity).build();
    }
    catch(Exception e) {
      return serverError(e, "Find email by id failed");
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
  @Path("/{id}/degrees")
  public Response getDegrees(@PathParam("id") int nedId) {
    try {
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<DegreeEntity>>(
              namedEntityService.findDegreesByNedId(nedId)){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find degrees by nedId failed");
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
      List<PhonenumberEntity> phonenumbers = namedEntityService.findPhoneNumbersByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<PhonenumberEntity>>(phonenumbers){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find phone numberse by nedId failed");
    }
  }

  @GET
  @Path("/{id}/roles")
  public Response getRolesForIndividual(@PathParam("id") int nedId) {
    try {
      List<RoleEntity> roles = namedEntityService.findRolesByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<RoleEntity>>(roles){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find roles by nedId failed");
    }
  }

  @GET
  @Path("/{id}/xref")
  public Response getExternalReferencesForIndividual(@PathParam("id") int nedId) {
    try {
      List<UniqueidentifierEntity> uids = namedEntityService.findUniqueIdsByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<UniqueidentifierEntity>>(uids){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find external references by nedId failed");
    }
  }

}
