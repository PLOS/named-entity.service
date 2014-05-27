package org.plos.namedentity.rest;

import static org.plos.namedentity.utils.EntityPojoTransformer.*;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.dto.GlobaltypeDTO;
import org.plos.namedentity.api.dto.TypedescriptionDTO;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.service.NamedEntityService;
import org.plos.namedentity.service.NamedEntityServiceHighApi;
import org.plos.namedentity.api.NedValidationException;

@Path("/ned")
public class NamedEntityResource {

    static Logger logger = Logger.getLogger(NamedEntityResource.class);

    @Inject private NamedEntityService        nedSvcLowApi; 
    @Inject private NamedEntityServiceHighApi nedSvcHighApi;

    /* ------------------ CONVENIENCE API (HIGH-LEVEL) ---------------------- */

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/individuals")
    public Response createIndividualComposite(IndividualComposite object) {
        try {
            IndividualEntity individual = nedSvcHighApi.createIndividual(object);
            return null;
            //Integer pkId = nedSvcLowApi.create(typeDescription);
            //TypedescriptionDTO dto = nedSvcLowApi.findById(pkId, TypedescriptionDTO.class);
            //return Response.status(Response.Status.OK).entity(dto).build();
        }
        catch(NedValidationException e) {
            logger.error("validation exception", e);
            return Response.status(Response.Status.BAD_REQUEST)             // 4XX (client-side)
                .entity("Unable to create Type Class. Validation failed. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
        catch(Exception e) {
            logger.error("internal error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
                .entity("Unable to create Type Class. Internal error. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
    }

    /* ---------------------- CORE API (LOW-EVEL)  -------------------------- */

    /* ---------------------------------------------------------------------- */
    /*  TYPE DESCRIPTIONS (TYPE CLASSES)                                      */
    /* ---------------------------------------------------------------------- */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/typeclasses/{id}")
    public Response getTypedescription(@PathParam("id") int id) {
        try {
            TypedescriptionEntity entity = nedSvcLowApi.findById(id, TypedescriptionEntity.class);
            return Response.status(Response.Status.OK).entity( entity ).build();
            //return Response.status(Response.Status.OK).entity( toPojo(entity) ).build();
        }
        catch(Exception e) {
            logger.error("internal error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
                .entity("Find type class by id failed. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/typeclasses")
    public Response getTypedescriptions() {
        try {
            List<TypedescriptionEntity> typeClasses = nedSvcLowApi.findAll(TypedescriptionEntity.class);
            //List<TypedescriptionDTO> typeClasses = toPojo(nedSvcLowApi.findAll(TypedescriptionEntity.class));
            return Response.status(Response.Status.OK).entity(
                new GenericEntity<List<TypedescriptionEntity>>(typeClasses){}).build();
        }
        catch(Exception e) {
            logger.error("internal error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
                .entity("Find all type classes failed. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/typeclasses")
    public Response createTypedescription(TypedescriptionDTO typeDescription) {
        try {
            Integer pkId = nedSvcLowApi.create( toEntity(typeDescription) );
            TypedescriptionEntity entity = nedSvcLowApi.findById(pkId, TypedescriptionEntity.class);
            return Response.status(Response.Status.OK).entity( entity ).build();
        }
        catch(NedValidationException e) {
            logger.error("validation exception", e);
            return Response.status(Response.Status.BAD_REQUEST)             // 4XX (client-side)
                .entity("Unable to create Type Class. Validation failed. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
        catch(Exception e) {
            logger.error("internal error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
                .entity("Unable to create Type Class. Internal error. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/typeclasses/{id}")
    public Response updateTypedescription(TypedescriptionDTO typeDescription) {
        try {
            nedSvcLowApi.update( toEntity(typeDescription) );
            TypedescriptionEntity entity = nedSvcLowApi.findById(
                typeDescription.getTypeid(), TypedescriptionEntity.class);
            return Response.status(Response.Status.OK).entity( entity ).build();
        }
        catch(NedValidationException e) {
            logger.error("validation exception", e);
            return Response.status(Response.Status.BAD_REQUEST)             // 4XX (client-side)
                .entity("Unable to update Type Class. Validation failed. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
        catch(Exception e) {
            logger.error("internal error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
                .entity("Unable to update Type Class. Internal error. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
    }

    @DELETE
    @Path("/typeclasses/{id}")
    public Response deleteTypedescription(@PathParam("id") int id) {
        try {
            TypedescriptionEntity entity = new TypedescriptionEntity();
            entity.setTypeid(id);
            nedSvcLowApi.delete(entity);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        catch(Exception e) {
            logger.error("internal error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
                .entity("Unable to delete Type Class. Internal error. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
    }

    /* ---------------------------------------------------------------------- */
    /*  TYPE VALUES (GLOBAL TYPES)                                            */
    /* ---------------------------------------------------------------------- */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/typeclasses/{typeclassid}/typevalues/{typevalueid}")
    public Response getGlobalType(@PathParam("typeclassid") int typeClassId, 
                                  @PathParam("typevalueid") int typeValueId) {
        try {
            GlobaltypeEntity entity = nedSvcLowApi.findById(typeValueId, GlobaltypeEntity.class);
            return Response.status(Response.Status.OK).entity( entity ).build();
        }
        catch(Exception e) {
            logger.error("internal error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
                .entity("Find type value by id failed. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/typeclasses/{typeclassid}/typevalues")
    public Response getGlobalTypeForTypeClass(@PathParam("typeclassid") int typeClassId) {
        try {
            GlobaltypeEntity searchCriteria = new GlobaltypeEntity();
            searchCriteria.setTypeid(typeClassId);

            List<GlobaltypeEntity> typeClasses = nedSvcLowApi.findByAttribute(searchCriteria);
            return Response.status(Response.Status.OK).entity(
                new GenericEntity<List<GlobaltypeEntity>>(typeClasses){}).build();
        }
        catch(Exception e) {
            logger.error("internal error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
                .entity("Find type classes for a type class failed. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/typeclasses/{typeclassid}/typevalues")
    public Response createGlobalType(@PathParam("typeclassid") int typeClassId, GlobaltypeDTO globalType) {
        try {
            GlobaltypeEntity entity = toEntity(globalType);
            entity.setTypeid(typeClassId);
            Integer pkId = nedSvcLowApi.create(entity);

            GlobaltypeEntity foundEntity = nedSvcLowApi.findById(pkId, GlobaltypeEntity.class);
            return Response.status(Response.Status.OK).entity( foundEntity ).build();
        }
        catch(NedValidationException e) {
            logger.error("validation exception", e);
            return Response.status(Response.Status.BAD_REQUEST)             // 4XX (client-side)
                .entity("Unable to create Type Value. Validation failed. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
        catch(Exception e) {
            logger.error("internal error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
                .entity("Unable to create Type Value. Internal error. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/typeclasses/{typeclassid}/typevalues/{typevalueid}")
    public Response updateGlobalType(@PathParam("typeclassid") int typeClassId, 
                                     @PathParam("typevalueid") int typeValueId, 
                                     GlobaltypeDTO globalType) {
        try {
            nedSvcLowApi.update( toEntity(globalType) );
            GlobaltypeEntity entity = nedSvcLowApi.findById(
                globalType.getGlobaltypeid(), GlobaltypeEntity.class);
            return Response.status(Response.Status.OK).entity( entity ).build();
        }
        catch(NedValidationException e) {
            logger.error("validation exception", e);
            return Response.status(Response.Status.BAD_REQUEST)             // 4XX (client-side)
                .entity("Unable to update Type Value. Validation failed. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
        catch(Exception e) {
            logger.error("internal error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
                .entity("Unable to update Type Value. Internal error. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
    }

    @DELETE
    @Path("/typeclasses/{typeclassid}/typevalues/{typevalueid}")
    public Response deleteGlobalType(@PathParam("typeclassid") int typeClassId, 
                                     @PathParam("typevalueid") int typeValueId) {
        try {
            GlobaltypeEntity entity = new GlobaltypeEntity();
            entity.setGlobaltypeid(typeValueId);
            nedSvcLowApi.delete(entity);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        catch(Exception e) {
            logger.error("internal error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
                .entity("Unable to delete Type Value. Internal error. Reason: " + e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
        }
    }

    public NamedEntityService getNamedEntityService() {
        return nedSvcLowApi;
    }
    public void setNamedEntityService(NamedEntityService nedSvcLowApi) {
        this.nedSvcLowApi = nedSvcLowApi;
    }

    public NamedEntityServiceHighApi getNamedEntityServiceHighApi() {
        return nedSvcHighApi;
    }
    public void setNamedEntityServiceHighApi(NamedEntityServiceHighApi nedSvcHighApi) {
        this.nedSvcHighApi = nedSvcHighApi;
    }
}
