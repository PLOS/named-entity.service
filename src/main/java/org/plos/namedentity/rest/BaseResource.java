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
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.ChildEntity;
import org.plos.namedentity.api.entity.ParentEntity;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.service.CrudService;
import org.plos.namedentity.service.NamedEntityService;

import javax.inject.Inject;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class BaseResource {

  protected static Logger logger = Logger.getLogger(BaseResource.class);

  protected static final Integer MAX_RESULT_COUNT = 50;

  protected static final Integer DEFAULT_RESULT_COUNT = 10000;

  @Inject
  protected CrudService crudService;

  @Inject
  protected NamedEntityService namedEntityService;

  protected <T extends ParentEntity> void checkNedIdForEntity(int nedId, Class<T> clazz) {
    if (namedEntityService.findResolvedEntities(nedId, clazz).size() == 0)
      throw new EntityNotFoundException(clazz.getSimpleName() + " not found");
  }


  protected <S extends ChildEntity, T extends ParentEntity> 
    Response createEntity(int nedId, S entity, Class<T> parent) {

    try {

      checkNedIdForEntity(nedId, parent);

      entity.setNedid(nedId);

      namedEntityService.resolveValuesToIds(entity);

      Integer pkId = crudService.create(entity);

      return Response.status(Response.Status.OK).entity(
          namedEntityService.findResolvedEntityByKey(pkId, entity.getClass())).build();
    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (NedValidationException e) {
      return validationError(e, "Unable to create " + entity.getClass().getSimpleName());
    } catch (Exception e) {
      return serverError(e, "Unable to create " + entity.getClass().getSimpleName());
    }
  }

  protected <S extends ChildEntity, T extends ParentEntity> 
    Response updateEntity(int nedId, int pkId, S entity, Class<T> parent) {

    try {

      // make sure the nedId belongs to the parent class (ie - Individual)
      checkNedIdForEntity(nedId, parent);

      entity.setNedid(nedId);

      namedEntityService.resolveValuesToIds(entity);

      crudService.update(entity);

      Entity dbEntity = namedEntityService.findResolvedEntityByKey(pkId, entity.getClass());

      return Response.status(Response.Status.OK).entity(dbEntity).build();

    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (NedValidationException e) {
      return validationError(e, "Unable to update " + entity.getClass().getSimpleName());
    } catch (Exception e) {
      return serverError(e, "Unable to update "  + entity.getClass().getSimpleName());
    }
  }

  protected <S extends ChildEntity, T extends ParentEntity> 
    Response deleteEntity(int nedId, int pkId, Class<S> child, Class<T> parent) {

    try {
      checkNedIdForEntity(nedId, parent);

      S entity = namedEntityService.findResolvedEntityByKey(pkId, child);

      crudService.delete(entity);

      return Response.status(Response.Status.NO_CONTENT).build();

    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (NedValidationException e) {
      return validationError(e, "Unable to delete " + child.getSimpleName());
    } catch (Exception e) {
      return serverError(e, "Unable to delete " + child.getSimpleName());
    }
  }


  protected <S extends ChildEntity, T extends ParentEntity> 
    Response getEntity(int nedId, int pkId, Class<S> child, Class<T> parent) {

    try {

      checkNedIdForEntity(nedId, parent);

      List<S> entities = namedEntityService.findResolvedEntities(nedId, child);

      for (ChildEntity entity : entities)
        if (entity.getId().equals(pkId))
          return Response.status(Response.Status.OK).entity(entity).build();

      return entityNotFound(child.getSimpleName() + " not found");

    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, String.format("Find % by id failed", child.getSimpleName()));
    }
  }

  /**
   * @note #1 - it doesn't appear that the glassfish test container can handle
   *            a generic type parameter in the GenericEntity, specifically this: 
   *
   *  return Response.status(Response.Status.OK).entity(
   *    new GenericEntity<List<S>>(
   *      namedEntityService.findResolvedEntities(nedId, child)){}).build();
   *
   * You'll see this error written to console when marshalling the response. 
   *
   *  SEVERE: MessageBodyWriter not found for media type=application/json,
   *          type=class java.util.ArrayList, genericType=java.util.List<S>.
   *
   * what to do? how about doing a brute-force switch on child class -- 
   * not pretty, but at least allows a generic entry-point. fix in future.
   */
  protected <S extends ChildEntity, T extends ParentEntity> 
    Response getEntities(int nedId, Class<S> child, Class<T> parent) {

    try {
      checkNedIdForEntity(nedId, parent);

      // *** GENERICS TYPE ERASURE HACK ***! see note #1.

      String cname = child.getCanonicalName();

      if (cname.equals(Address.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Address>>(
            namedEntityService.findResolvedEntities(nedId, Address.class)
          ){}).build();
      } 
      else if (cname.equals(Email.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Email>>(
            namedEntityService.findResolvedEntities(nedId, Email.class)
          ){}).build();
      } 
      else if (cname.equals(Phonenumber.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Phonenumber>>(
            namedEntityService.findResolvedEntities(nedId, Phonenumber.class)
          ){}).build();
      }
      else if (cname.equals(Role.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Role>>(
            namedEntityService.findResolvedEntities(nedId, Role.class)
          ){}).build();
      }
      else if (cname.equals(Uniqueidentifier.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
          new GenericEntity<List<Uniqueidentifier>>(
            namedEntityService.findResolvedEntities(nedId, Uniqueidentifier.class)
          ){}).build();
      }
      throw new UnsupportedOperationException("Unsupported child entity: " + child.getSimpleName());

    } catch (EntityNotFoundException e) {
      return entityNotFound(e);
    } catch (Exception e) {
      return serverError(e, String.format("Find %s by nedId failed", child.getSimpleName()));
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

}
