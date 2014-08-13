package org.plos.namedentity.rest;

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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/individuals")
public class IndividualsResource extends BaseResource {

  @POST
  public Response create(IndividualEntity entity) {
    try {
      return Response.status(Response.Status.OK).entity(
          crudService.create(entity)).build();
    }
    catch(NedValidationException e) {
      return validationError(e, "Unable to create individual");
    }
    catch(Exception e) {
      return serverError(e, "Unable to create individual");
    }
  }

  @GET
  @Path("/{id}")
  public Response read(@PathParam("id") int nedId) {
    try {
      IndividualEntity individual = namedEntityService.findIndividualByNedId(nedId);
      return Response.status(Response.Status.OK).entity(individual).build();
    }
    catch(Exception e) {
      return serverError(e, "Find individual by id failed");
    }
  }

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") int nedId, IndividualEntity newEntity) {

    try {

      newEntity.setNamedentityid(nedId);

      IndividualEntity oldEntity = namedEntityService.findIndividualByNedId(nedId);

      if (oldEntity == null)
        return Response.status(Response.Status.NOT_FOUND).build();

      namedEntityService.resolveValues(newEntity);
      crudService.update(newEntity);  // TODO: make sure this completely replaces old entity

      return Response.status(Response.Status.NO_CONTENT).build();

    } catch (Exception e) {
      return serverError(e, "Unable to delete individual");
    }

  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") int nedId) {

    try {

      IndividualEntity entity = namedEntityService.findIndividualByNedId(nedId);

      if (entity == null)
        return Response.status(Response.Status.NOT_FOUND).build();

      crudService.delete(entity);

      return Response.status(Response.Status.NO_CONTENT).build();

    } catch (Exception e) {
      return serverError(e, "Unable to delete individual");
    }

  }

  @GET
  public Response list(@QueryParam("uidType") String uidType, @QueryParam("uidValue") String uidValue) {
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
  @Path("/{id}/emails")
  public Response getEmails(@PathParam("id") int nedId) {
    try {
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<EmailEntity>>(
              namedEntityService.findEmailsByNedId(nedId)
          ){}).build();
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
  public Response getAddresses(@PathParam("id") int nedId) {
    try {
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<AddressEntity>>(
              namedEntityService.findAddressesByNedId(nedId)
          ){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find addresses by nedId failed");
    }
  }

  @GET
  @Path("/{id}/phonenumbers")
  public Response getPhonenumbers(@PathParam("id") int nedId) {
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
  public Response getRolesFor(@PathParam("id") int nedId) {
    try {
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<RoleEntity>>(
              namedEntityService.findRolesByNedId(nedId)
          ){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find roles by nedId failed");
    }
  }

  @GET
  @Path("/{id}/xref")
  public Response getExternalReferences(@PathParam("id") int nedId) {
    try {
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<UniqueidentifierEntity>>(
              namedEntityService.findUniqueIdsByNedId(nedId)
          ){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find external references by nedId failed");
    }
  }

}
