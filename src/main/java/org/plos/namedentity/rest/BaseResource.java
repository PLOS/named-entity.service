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

import org.apache.log4j.Logger;
import org.plos.namedentity.api.EntityNotFoundException;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.service.CrudService;
import org.plos.namedentity.service.NamedEntityService;

import javax.inject.Inject;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class BaseResource {

  static Logger logger = Logger.getLogger(BaseResource.class);

  @Inject
  protected CrudService crudService;

  @Inject
  protected NamedEntityService namedEntityService;

  /* ------------------------------------------------------------------------ */
  /*  EMAIL ENTITY                                                            */
  /* ------------------------------------------------------------------------ */

  public Response createEmail(int nedId, Email emailEntity, Class clazz) {
    try {

      namedEntityService.findResolvedEntity(nedId, clazz);

      emailEntity.setNedid(nedId);

      namedEntityService.resolveValuesToIds(emailEntity);

      Integer emailId = crudService.create(emailEntity);

      return Response.status(Response.Status.OK).entity(
          namedEntityService.findResolvedEntityByKey(emailId, Email.class)
                                                       ).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (NedValidationException e) {
      return validationError(e, "Unable to create email");
    } catch (Exception e) {
      return serverError(e, "Unable to create email");
    }
  }

  protected Response updateEmail(int nedId, int emailId,
                                 Email emailEntity, Class clazz) {
    try {

      namedEntityService.findResolvedEntity(nedId, clazz);

      emailEntity.setNedid(nedId);

      namedEntityService.resolveValuesToIds(emailEntity);

      crudService.update(emailEntity);

      emailEntity = namedEntityService.findResolvedEntityByKey(emailId, Email.class);

      return Response.status(Response.Status.OK).entity(emailEntity).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (NedValidationException e) {
      return validationError(e, "Unable to update email");
    } catch (Exception e) {
      return serverError(e, "Unable to update email");
    }
  }

  protected Response deleteEmail(int nedId, int emailId, Class clazz) {
    try {

      namedEntityService.findResolvedEntity(nedId, clazz);

      Email emailEntity = namedEntityService.findResolvedEntityByKey(emailId, Email.class);

      crudService.delete(emailEntity);

      return Response.status(Response.Status.NO_CONTENT).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (NedValidationException e) {
      return validationError(e, "Unable to delete email");
    } catch (Exception e) {
      return serverError(e, "Unable to delete email");
    }
  }

  protected Response getEmail(int nedId, int emailId, Class clazz) {

    try {

      // make sure the nedId belongs to an individual
      namedEntityService.findResolvedEntity(nedId, clazz);

      List<Email> emails = namedEntityService.findResolvedEntities(nedId, Email.class);

      for (Email email : emails)
        if (email.getId().equals(emailId))
          return Response.status(Response.Status.OK).entity(email).build();

      return entityNotFound("Email not found");

    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Find email by id failed");
    }

  }

  protected Response getEmails(int nedId, Class clazz) {
    try {

      // make sure the nedId belongs to an individual
      namedEntityService.findResolvedEntity(nedId, clazz);

      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Email>>(
              namedEntityService.findResolvedEntities(nedId, Email.class)
          ) {
          }).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Find emails by nedId failed");
    }
  }

  /* ------------------------------------------------------------------------ */
  /*  ADDRESS ENTITY                                                          */
  /* ------------------------------------------------------------------------ */

  public Response createAddress(int nedId, Address addressEntity, Class clazz) {
    try {
      // verify parent entity exists. throw a 404 if not found.
      namedEntityService.findResolvedEntity(nedId, clazz);

      addressEntity.setNedid(nedId);

      namedEntityService.resolveValuesToIds(addressEntity);

      Integer addressId = crudService.create(addressEntity);

      return Response.status(Response.Status.OK).entity(
          namedEntityService.findResolvedEntityByKey(addressId, Address.class)).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (NedValidationException e) {
      return validationError(e, "Unable to create address");
    } catch (Exception e) {
      return serverError(e, "Unable to create address");
    }
  }

  protected Response updateAddress(int nedId, int addressId,
                                   Address addressEntity, Class clazz) {
    try {
      // verify parent entity exists. throw a 404 if not found.
      namedEntityService.findResolvedEntity(nedId, clazz);

      addressEntity.setNedid(nedId);

      namedEntityService.resolveValuesToIds(addressEntity);

      crudService.update(addressEntity);

      addressEntity = namedEntityService.findResolvedEntityByKey(addressId, Address.class);

      return Response.status(Response.Status.OK).entity(addressEntity).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (NedValidationException e) {
      return validationError(e, "Unable to update address");
    } catch (Exception e) {
      return serverError(e, "Unable to update address");
    }
  }

  protected Response deleteAddress(int nedId, int addressId, Class clazz) {
    try {
      namedEntityService.findResolvedEntity(nedId, clazz);

      Address addressEntity = namedEntityService.findResolvedEntityByKey(addressId, Address.class);

      crudService.delete(addressEntity);

      return Response.status(Response.Status.NO_CONTENT).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (NedValidationException e) {
      return validationError(e, "Unable to delete address");
    } catch (Exception e) {
      return serverError(e, "Unable to delete address");
    }
  }

  protected Response getAddress(int nedId, int addressId, Class clazz) {
    try {

      // make sure the nedId belongs to an individual
      namedEntityService.findResolvedEntity(nedId, clazz);

      List<Address> addresses = namedEntityService.findResolvedEntities(nedId, Address.class);

      for (Address address : addresses)
        if (address.getId().equals(addressId))
          return Response.status(Response.Status.OK).entity(address).build();

      return entityNotFound("Address not found");

    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Find address by id failed");
    }
  }

  protected Response getAddresses(int nedId, Class clazz) {
    try {
      namedEntityService.findResolvedEntity(nedId, clazz);

      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Address>>(
              namedEntityService.findResolvedEntities(nedId, Address.class)){ }).build();

    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Find addresses by nedId failed");
    }
  }

  /* ------------------------------------------------------------------------ */
  /*  PHONE NUMBER ENTITY                                                     */
  /* ------------------------------------------------------------------------ */

  protected Response getPhonenumbers(int nedId, Class clazz) {
    try {
      namedEntityService.findResolvedEntity(nedId, clazz);

      List<Phonenumber> phonenumbers = namedEntityService.findResolvedEntities(nedId, Phonenumber.class);
      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Phonenumber>>(phonenumbers) {
          }).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Find phone numberse by nedId failed");
    }
  }

  /* ------------------------------------------------------------------------ */
  /*  UNIQUE IDENTIFIER ENTITY                                                */
  /* ------------------------------------------------------------------------ */

  protected Response getExternalReferences(int nedId, Class clazz) {
    try {
      namedEntityService.findResolvedEntity(nedId, clazz);

      return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Uniqueidentifier>>(
              namedEntityService.findResolvedEntities(nedId, Uniqueidentifier.class)
          ) {
          }).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, "Find external references by nedId failed");
    }
  }

  protected Response entityNotFound(String message) {
    logger.error("entity not found: " + message);
    return Response.status(Response.Status.NOT_FOUND)   // 404
        .entity(message)
        .type(MediaType.TEXT_PLAIN).build();
  }

  protected Response entityNotFound(EntityNotFoundException e) {
    return entityNotFound(e.getMessage());
  }

  protected Response serverError(Exception e, String message) {
    logger.error("internal error", e);
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)   // 5XX (server-side)
        .entity(message + ". Internal error. Reason: " + e.getMessage())
        .type(MediaType.TEXT_PLAIN).build();
  }

  protected Response validationError(NedValidationException e, String message) {
    logger.error("validation exception", e);
    return Response.status(Response.Status.BAD_REQUEST)             // 4XX (client-side)
        .entity(message + ". Validation failed. Reason: " + e.getMessage())
        .type(MediaType.TEXT_PLAIN).build();
  }

  protected boolean isEmptyOrBlank(String s) {
    return s == null || s.trim().isEmpty();
  }
}
