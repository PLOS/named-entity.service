/*
 * Copyright (c) 2006-2014 by Public Library of Science
 * http://plos.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.plos.namedentity.rest;

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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.dto.AddressDTO;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.dto.GlobaltypeDTO;
import org.plos.namedentity.api.dto.IndividualDTO;
import org.plos.namedentity.api.dto.PhonenumberDTO;
import org.plos.namedentity.api.dto.RoleDTO;
import org.plos.namedentity.api.dto.TypedescriptionDTO;
import org.plos.namedentity.api.dto.UniqueidentifierDTO;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;
import org.plos.namedentity.service.NamedEntityService;
import org.plos.namedentity.service.NamedEntityServiceHighApi;
import org.plos.namedentity.utils.Transformer;

@Path("/ned")
public class NamedEntityResource {

  static Logger logger = Logger.getLogger(NamedEntityResource.class);

  @Inject private NamedEntityService        nedSvcLowApi; 
  @Inject private NamedEntityServiceHighApi nedSvcHighApi;
  @Inject private Transformer               transformer;

  /* ---------------------------------------------------------------------- */
  /*  INDIVIDUALS                                                           */
  /* ---------------------------------------------------------------------- */
  
  @POST
  @Consumes("application/json")
  @Produces("application/json")
  @Path("/individuals")
  public Response createIndividualComposite(IndividualComposite object) {
    try {
      IndividualDTO dto = nedSvcHighApi.createIndividual(object);
      return Response.status(Response.Status.OK).entity(dto).build();
    }
    catch(NedValidationException e) {
      logger.error("validation exception", e);
      return Response.status(Response.Status.BAD_REQUEST)             // 4XX (client-side)
        .entity("Unable to create individual. Validation failed. Reason: " + e.getMessage())
        .type(MediaType.TEXT_PLAIN).build();
    }
    catch(Exception e) {
      logger.error("internal error", e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
        .entity("Unable to create individual. Internal error. Reason: " + e.getMessage())
        .type(MediaType.TEXT_PLAIN).build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/individuals")
  public Response getAllIndividuals() {
    try {
      List<IndividualEntity> individuals = nedSvcLowApi.findAll(IndividualEntity.class);
      return Response.status(Response.Status.OK).entity(
        new GenericEntity<List<IndividualEntity>>(individuals){}).build();
    }
    catch(Exception e) {
      logger.error("internal error", e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
        .entity("Find all individuals failed. Reason: " + e.getMessage())
        .type(MediaType.TEXT_PLAIN).build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/individuals/{id}")
  public Response getIndividual(@PathParam("id") int id) {
    try {
      IndividualDTO dto = nedSvcHighApi.findIndividualByNedId(id);
      return Response.status(Response.Status.OK).entity(dto).build();
    }
    catch(Exception e) {
      logger.error("internal error", e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
        .entity("Find individual by id failed. Reason: " + e.getMessage())
        .type(MediaType.TEXT_PLAIN).build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/individuals/{id}/emails")
  public Response getEmailsForIndividual(@PathParam("id") int nedId) {
    try {
      List<EmailDTO> emails = nedSvcHighApi.findEmailsByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
        new GenericEntity<List<EmailDTO>>(emails){}).build();
    }
    catch(Exception e) {
      logger.error("internal error", e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
        .entity("Find emails by nedId failed. Reason: " + e.getMessage())
        .type(MediaType.TEXT_PLAIN).build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/individuals/{id}/addresses")
  public Response getAddressesForIndividual(@PathParam("id") int nedId) {
    try {
      List<AddressDTO> addresses = nedSvcHighApi.findAddressesByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
        new GenericEntity<List<AddressDTO>>(addresses){}).build();
    }
    catch(Exception e) {
      logger.error("internal error", e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
        .entity("Find addresses by nedId failed. Reason: " + e.getMessage())
        .type(MediaType.TEXT_PLAIN).build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/individuals/{id}/phonenumbers")
  public Response getPhonenumbersForIndividual(@PathParam("id") int nedId) {
    try {
      List<PhonenumberDTO> phonenumbers = nedSvcHighApi.findPhoneNumbersByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
        new GenericEntity<List<PhonenumberDTO>>(phonenumbers){}).build();
    }
    catch(Exception e) {
      logger.error("internal error", e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
        .entity("Find phone numberse by nedId failed. Reason: " + e.getMessage())
        .type(MediaType.TEXT_PLAIN).build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/individuals/{id}/roles")
  public Response getRolesForIndividual(@PathParam("id") int nedId) {
    try {
      List<RoleDTO> roles = nedSvcHighApi.findRolesByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
        new GenericEntity<List<RoleDTO>>(roles){}).build();
    }
    catch(Exception e) {
      logger.error("internal error", e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
        .entity("Find roles by nedId failed. Reason: " + e.getMessage())
        .type(MediaType.TEXT_PLAIN).build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/individuals/{id}/xref")
  public Response getExternalReferencesForIndividual(@PathParam("id") int nedId) {
    try {
      List<UniqueidentifierDTO> uids = nedSvcHighApi.findUniqueIdsByNedId(nedId);
      return Response.status(Response.Status.OK).entity(
        new GenericEntity<List<UniqueidentifierDTO>>(uids){}).build();
    }
    catch(Exception e) {
      logger.error("internal error", e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
        .entity("Find external references by nedId failed. Reason: " + e.getMessage())
        .type(MediaType.TEXT_PLAIN).build();
    }
  }

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
      Integer pkId = nedSvcLowApi.create( transformer.toEntity(typeDescription) );
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
      nedSvcLowApi.update( transformer.toEntity(typeDescription) );
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
      GlobaltypeEntity entity = transformer.toEntity(globalType);
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
      nedSvcLowApi.update( transformer.toEntity(globalType) );
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
    this.nedSvcHighApi = nedSvcHighApi;
  }
}
