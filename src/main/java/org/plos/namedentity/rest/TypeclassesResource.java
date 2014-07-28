package org.plos.namedentity.rest;

import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.dto.GlobaltypeDTO;
import org.plos.namedentity.api.dto.TypedescriptionDTO;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;
import org.plos.namedentity.service.CrudService;
import org.plos.namedentity.service.NamedEntityService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/typeclasses")
public class TypeclassesResource extends BaseController{

  @GET
  @Path("/{id}")
  public Response getTypedescription(@PathParam("id") int id) {
    try {
      TypedescriptionEntity entity = crudService.findById(id, TypedescriptionEntity.class);
      return Response.status(Response.Status.OK).entity( entity ).build();
      //return Response.status(Response.Status.OK).entity( toPojo(entity) ).build();
    }
    catch(Exception e) {
      return serverError(e, "Find type class by id failed");
    }
  }

  @GET
  public Response getTypedescriptions() {
    try {
      List<TypedescriptionEntity> typeClasses = crudService.findAll(TypedescriptionEntity.class);
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<TypedescriptionEntity>>(typeClasses){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find all type classes failed");
    }
  }

  @POST
  public Response createTypedescription(TypedescriptionDTO typeDescription) {
    try {
      Integer pkId = crudService.create( transformer.toEntity(typeDescription) );
      TypedescriptionEntity entity = crudService.findById(pkId, TypedescriptionEntity.class);
      return Response.status(Response.Status.OK).entity( entity ).build();
    }
    catch(NedValidationException e) {
      return validationError(e, "Unable to create Type Class");
    }
    catch(Exception e) {
      return serverError(e, "Unable to create Type Class");
    }
  }

  @PUT
  @Path("/{id}")
  public Response updateTypedescription(TypedescriptionDTO typeDescription) {
    try {
      crudService.update( transformer.toEntity(typeDescription) );
      TypedescriptionEntity entity = crudService.findById(
          typeDescription.getTypeid(), TypedescriptionEntity.class);
      return Response.status(Response.Status.OK).entity( entity ).build();
    }
    catch(NedValidationException e) {
      return validationError(e, "Unable to update Type Class");
    }
    catch(Exception e) {
      return serverError(e, "Unable to update Type Class");
    }
  }

  @DELETE
  @Path("/{id}")
  public Response deleteTypedescription(@PathParam("id") int id) {
    try {
      TypedescriptionEntity entity = new TypedescriptionEntity();
      entity.setTypeid(id);
      crudService.delete(entity);
      return Response.status(Response.Status.NO_CONTENT).build();
    }
    catch(Exception e) {
      return serverError(e, "Unable to delete Type Class");
    }
  }

  /* ---------------------------------------------------------------------- */
  /*  TYPE VALUES (GLOBAL TYPES)                                            */
  /* ---------------------------------------------------------------------- */

  @GET
  @Path("/{typeclassid}/typevalues/{typevalueid}")
  public Response getGlobalType(@PathParam("typeclassid") int typeClassId,
                                @PathParam("typevalueid") int typeValueId) {
    try {
      GlobaltypeEntity entity = crudService.findById(typeValueId, GlobaltypeEntity.class);
      return Response.status(Response.Status.OK).entity( entity ).build();
    }
    catch(Exception e) {
      return serverError(e, "Find type value by id failed");
    }
  }

  @GET
  @Path("/{typeclassid}/typevalues")
  public Response getGlobalTypeForTypeClass(@PathParam("typeclassid") int typeClassId) {
    try {
      GlobaltypeEntity searchCriteria = new GlobaltypeEntity();
      searchCriteria.setTypeid(typeClassId);

      List<GlobaltypeEntity> typeClasses = crudService.findByAttribute(searchCriteria);
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<GlobaltypeEntity>>(typeClasses){}).build();
    }
    catch(Exception e) {
      return serverError(e, "Find type classes for a type class failed");
    }
  }

  @POST
  @Path("/{typeclassid}/typevalues")
  public Response createGlobalType(@PathParam("typeclassid") int typeClassId, GlobaltypeDTO globalType) {
    try {
      GlobaltypeEntity entity = transformer.toEntity(globalType);
      entity.setTypeid(typeClassId);
      Integer pkId = crudService.create(entity);

      GlobaltypeEntity foundEntity = crudService.findById(pkId, GlobaltypeEntity.class);
      return Response.status(Response.Status.OK).entity( foundEntity ).build();
    }
    catch(NedValidationException e) {
      return validationError(e, "Unable to create Type Value");
    }
    catch(Exception e) {
      return serverError(e, "Unable to create Type Value");
    }
  }

  @PUT
  @Path("/{typeclassid}/typevalues/{typevalueid}")
  public Response updateGlobalType(@PathParam("typeclassid") int typeClassId,
                                   @PathParam("typevalueid") int typeValueId,
                                   GlobaltypeDTO globalType) {
    try {
      crudService.update( transformer.toEntity(globalType) );
      GlobaltypeEntity entity = crudService.findById(
          globalType.getGlobaltypeid(), GlobaltypeEntity.class);
      return Response.status(Response.Status.OK).entity( entity ).build();
    }
    catch(NedValidationException e) {
      return validationError(e, "Unable to update Type Value");
    }
    catch(Exception e) {
      return serverError(e, "Unable to update Type Value");
    }
  }

  @DELETE
  @Path("/{typeclassid}/typevalues/{typevalueid}")
  public Response deleteGlobalType(@PathParam("typeclassid") int typeClassId,
                                   @PathParam("typevalueid") int typeValueId) {
    try {
      GlobaltypeEntity entity = new GlobaltypeEntity();
      entity.setGlobaltypeid(typeValueId);
      crudService.delete(entity);
      return Response.status(Response.Status.NO_CONTENT).build();
    }
    catch(Exception e) {
      return serverError(e, "Unable to delete Type Value");
    }
  }

  public CrudService getNamedEntityService() {
    return crudService;
  }
  public void setNamedEntityService(CrudService nedSvcLowApi) {
    this.crudService = nedSvcLowApi;
  }

  public NamedEntityService getNamedEntityServiceHighApi() {
    return namedEntityService;
  }
  public void setNamedEntityServiceHighApi(NamedEntityService nedSvcHighApi) {
    this.namedEntityService = nedSvcHighApi;
    this.namedEntityService = nedSvcHighApi;
  }
}