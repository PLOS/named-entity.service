package org.plos.namedentity.rest;

import java.util.Collection;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import org.plos.namedentity.api.TypedescriptionsDTO;
import org.plos.namedentity.service.NamedEntityService;

import org.springframework.dao.DataAccessException;

@Path("/ned")
public class NamedEntityResource {

    static Logger logger = Logger.getLogger(NamedEntityResource.class);

    @Inject private NamedEntityService namedEntityService; 

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/typeclasses/{id}")
    public TypedescriptionsDTO getTypedescription(@PathParam("id") int id) {
        // TODO: handle exception
        TypedescriptionsDTO dto = namedEntityService.findTypedescriptionById(id);
        if (dto == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return dto;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/typeclasses")
    public Collection<TypedescriptionsDTO> getTypedescriptions() {
        // TODO: handle exception
        Collection<TypedescriptionsDTO> typeClasses = namedEntityService.getTypedescriptions();
        if (typeClasses == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return typeClasses;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/typeclasses")
    public TypedescriptionsDTO createTypedescription(TypedescriptionsDTO typeDescription) {
        // TODO: handle exception
        TypedescriptionsDTO dto = namedEntityService.create(typeDescription);
        return dto;
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/typeclasses/{id}")
    public TypedescriptionsDTO updateTypedescription(TypedescriptionsDTO typeDescription) {
        // TODO: handle exception
        TypedescriptionsDTO dto = namedEntityService.update(typeDescription);
        return dto;
    }

    @DELETE
    @Path("/typeclasses/{id}")
    public void deleteTypedescription(@PathParam("id") int id) {
        try {
            TypedescriptionsDTO dto = new TypedescriptionsDTO();
            dto.setTypeid(id);
            namedEntityService.delete(dto);
        }
        catch (DataAccessException e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
 
    public NamedEntityService getNamedEntityService() {
        return namedEntityService;
    }

    public void setNamedEntityService(NamedEntityService namedEntityService) {
        this.namedEntityService = namedEntityService;
    }
}
