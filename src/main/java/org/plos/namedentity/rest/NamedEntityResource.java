package org.plos.namedentity.rest;

import java.util.Collection;
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

import org.plos.namedentity.api.GlobaltypesDTO;
import org.plos.namedentity.api.TypedescriptionsDTO;
import org.plos.namedentity.service.NamedEntityService;
import org.plos.namedentity.api.NedValidationException;

@Path("/ned")
public class NamedEntityResource {

    static Logger logger = Logger.getLogger(NamedEntityResource.class);

    @Inject private NamedEntityService namedEntityService; 

    /* ---------------------------------------------------------------------- */
    /*  TYPE DESCRIPTIONS (TYPE CLASSES)                                      */
    /* ---------------------------------------------------------------------- */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/typeclasses/{id}")
    public Response getTypedescription(@PathParam("id") int id) {
        try {
            TypedescriptionsDTO dto = namedEntityService.findById(id, TypedescriptionsDTO.class);
            return Response.status(Response.Status.OK).entity(dto).build();
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
            Collection<TypedescriptionsDTO> typeClasses = namedEntityService.findAll(TypedescriptionsDTO.class);
            return Response.status(Response.Status.OK).entity(
                new GenericEntity<Collection<TypedescriptionsDTO>>(typeClasses){}).build();
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
    public Response createTypedescription(TypedescriptionsDTO typeDescription) {
        try {
            Integer pkId = namedEntityService.create(typeDescription);
            TypedescriptionsDTO dto = namedEntityService.findById(pkId, TypedescriptionsDTO.class);
            return Response.status(Response.Status.OK).entity(dto).build();
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
    public Response updateTypedescription(TypedescriptionsDTO typeDescription) {
        try {
            namedEntityService.update(typeDescription);
            TypedescriptionsDTO dto = namedEntityService.findById(
                typeDescription.getTypeid(), TypedescriptionsDTO.class);
            return Response.status(Response.Status.OK).entity(dto).build();
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
            TypedescriptionsDTO dto = new TypedescriptionsDTO();
            dto.setTypeid(id);
            namedEntityService.delete(dto);
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
            GlobaltypesDTO dto = namedEntityService.findById(typeValueId, GlobaltypesDTO.class);
            return Response.status(Response.Status.OK).entity(dto).build();
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
            GlobaltypesDTO searchCriteriaDTO = new GlobaltypesDTO();
            searchCriteriaDTO.setTypeid(typeClassId);

            Collection<GlobaltypesDTO> typeClasses = namedEntityService.findByAttribute(searchCriteriaDTO);
            return Response.status(Response.Status.OK).entity(
                new GenericEntity<Collection<GlobaltypesDTO>>(typeClasses){}).build();
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
    public Response createGlobalType(@PathParam("typeclassid") int typeClassId, GlobaltypesDTO globalType) {
        try {
            globalType.setTypeid(typeClassId);
            Integer pkId = namedEntityService.create(globalType);
            GlobaltypesDTO dto = namedEntityService.findById(pkId, GlobaltypesDTO.class);
            return Response.status(Response.Status.OK).entity(dto).build();
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
                                     GlobaltypesDTO globalType) {
        try {
            namedEntityService.update(globalType);
            GlobaltypesDTO dto = namedEntityService.findById(
                globalType.getGlobaltypeid(), GlobaltypesDTO.class);
            return Response.status(Response.Status.OK).entity(dto).build();
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
            GlobaltypesDTO dto = new GlobaltypesDTO();
            dto.setGlobaltypeid(typeValueId);
            namedEntityService.delete(dto);
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
        return namedEntityService;
    }

    public void setNamedEntityService(NamedEntityService namedEntityService) {
        this.namedEntityService = namedEntityService;
    }
}
