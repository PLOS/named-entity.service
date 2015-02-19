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

import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;

import com.wordnik.swagger.annotations.ApiOperation;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Degree;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.api.entity.Phonenumber;
import org.plos.namedentity.api.entity.Role;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.api.entity.Url;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

public abstract class NedResource extends BaseResource {

  /* ----------------------------------------------------------------------- */
  /*  EMAIL CRUD                                                             */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/emails")
  @ApiOperation(value = "Create email", response = Email.class)
  public Response createEmail(@PathParam("nedId") int nedId,
                              Email emailEntity) {
    return createEntity(nedId, emailEntity);
  }

  @PUT
  @Path("/{nedId}/emails/{emailId}")
  @ApiOperation(value = "Update email", response = Email.class)
  public Response updateEmail(@PathParam("nedId") int nedId,
                              @PathParam("emailId") int emailId,
                              Email emailEntity) {
    return updateEntity(nedId, emailId, emailEntity);
  }

  @DELETE
  @Path("/{nedId}/emails/{emailId}")
  @ApiOperation(value = "Delete email")
  public Response deleteEmail(@PathParam("nedId") int nedId,
                              @PathParam("emailId") int emailId) {

    if (((List)(getEntities(nedId, Email.class).getEntity())).size() == 1)
      return validationError(new NedValidationException("Email entities cannot be empty"), "Unable to delete email");

    return deleteEntity(nedId, emailId, Email.class);
  }

  @GET
  @Path("/{nedId}/emails/{emailId}")
  @ApiOperation(value = "Read email", response = Email.class)
  public Response getEmail(@PathParam("nedId") int nedId,
                           @PathParam("emailId") int emailId) {
    return getEntity(nedId, emailId, Email.class);
  }

  @GET
  @Path("/{nedId}/emails")
  @ApiOperation(value = "List emails", response = Email.class)
  public Response getEmails(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Email.class);
  }


  /* ----------------------------------------------------------------------- */
  /*  ADDRESS CRUD                                                           */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/addresses")
  @ApiOperation(value = "Create address", response = Address.class)
  public Response createAddress(@PathParam("nedId") int nedId,
                                Address addressEntity) {
    return createEntity(nedId, addressEntity);
  }

  @PUT
  @Path("/{nedId}/addresses/{addressId}")
  @ApiOperation(value = "Update address", response = Address.class)
  public Response updateAddress(@PathParam("nedId") int nedId,
                                @PathParam("addressId") int addressId,
                                Address addressEntity) {
    return updateEntity(nedId, addressId, addressEntity);
  }

  @DELETE
  @Path("/{nedId}/addresses/{addressId}")
  @ApiOperation(value = "Delete address")
  public Response deleteAddress(@PathParam("nedId") int nedId,
                                @PathParam("addressId") int addressId) {
    return deleteEntity(nedId, addressId, Address.class);
  }

  @GET
  @Path("/{nedId}/addresses/{addressId}")
  @ApiOperation(value = "Read address", response = Address.class)
  public Response getAddress(@PathParam("nedId") int nedId,
                             @PathParam("addressId") int addressId) {
    return getEntity(nedId, addressId, Address.class);
  }

  @GET
  @Path("/{nedId}/addresses")
  @ApiOperation(value = "List addresses", response = Address.class)
  public Response getAddresses(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Address.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  PHONE NUMBER CRUD                                                      */
  /* ----------------------------------------------------------------------- */

  @GET
  @Path("/{nedId}/phonenumbers")
  @ApiOperation(value = "List phone numbers", response = Phonenumber.class)
  public Response getPhonenumbers(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Phonenumber.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  UIDS CRUD                                                              */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/uids")
  @ApiOperation(value = "Create UID", response = Uniqueidentifier.class)
  public Response createUid(@PathParam("nedId") int nedId,
                            Uniqueidentifier entity) {
    return createEntity(nedId, entity);
  }

  @PUT
  @Path("/{nedId}/uids/{id}")
  @ApiOperation(value = "Update UID", response = Uniqueidentifier.class)
  public Response updateUid(@PathParam("nedId") int nedId,
                            @PathParam("id") int id,
                            Uniqueidentifier entity) {
    return updateEntity(nedId, id, entity);
  }

  @GET
  @Path("/{nedId}/uids/{id}")
  @ApiOperation(value = "Read uid", response = Uniqueidentifier.class)
  public Response getUid(@PathParam("nedId") int nedId,
                         @PathParam("id") int id) {
    return getEntity(nedId, id, Uniqueidentifier.class);
  }

  @GET
  @Path("/{nedId}/uids")
  @ApiOperation(value = "List UIDs")
  public Response getUids(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Uniqueidentifier.class);
  }

  /*      GENERIC OPERATIONS              */

  protected <S extends Entity>
  Response createEntity(int nedId, S entity) {

    try {

      namedEntityService.checkNedIdForType(nedId, getNamedPartyType());

      entity.setNedid(nedId);

      namedEntityService.resolveValuesToIds(entity);

      Integer pkId = crudService.create(entity);

      return Response.status(Response.Status.OK).entity(
          namedEntityService.findResolvedEntityByKey(pkId, entity.getClass())).build();

    } catch (NedValidationException e) {
      return validationError(e, "Unable to create " + entity.getClass().getSimpleName());
    } catch (NedException e) {
      if (EntityNotFound.equals(e.getErrorType())) { return entityNotFound(e); }
      throw e;
    } catch (Exception e) {
      return serverError(e, "Unable to create " + entity.getClass().getSimpleName());
    }
  }

  protected <S extends Entity>
  Response updateEntity(int nedId, int pkId, S entity) {

    try {

      namedEntityService.checkNedIdForType(nedId, getNamedPartyType());

      entity.setId(pkId);
      entity.setNedid(nedId);

      namedEntityService.resolveValuesToIds(entity);

      crudService.update(entity);

      Entity dbEntity = namedEntityService.findResolvedEntityByKey(pkId, entity.getClass());

      return Response.status(Response.Status.OK).entity(dbEntity).build();

    } catch (NedValidationException e) {
      return validationError(e, "Unable to update " + entity.getClass().getSimpleName());
    } catch (NedException e) {
      if (EntityNotFound.equals(e.getErrorType())) { return entityNotFound(e); }
      throw e;
    } catch (Exception e) {
      return serverError(e, "Unable to update "  + entity.getClass().getSimpleName());
    }
  }

  protected <S extends Entity>
  Response deleteEntity(int nedId, int pkId, Class<S> child) {

    try {
      namedEntityService.checkNedIdForType(nedId, getNamedPartyType());

      S entity = namedEntityService.findResolvedEntityByKey(pkId, child);

      crudService.delete(entity);  // TODO: validate response code

      return Response.status(Response.Status.NO_CONTENT).build();

    } catch (NedValidationException e) {
      return validationError(e, "Unable to delete " + child.getSimpleName());
    } catch (NedException e) {
      if (EntityNotFound.equals(e.getErrorType())) { return entityNotFound(e); }
      throw e;
    } catch (Exception e) {
      return serverError(e, "Unable to delete " + child.getSimpleName());
    }
  }

  protected <S extends Entity>
  Response getEntity(int nedId, int pkId, Class<S> child) {

    try {

      namedEntityService.checkNedIdForType(nedId, getNamedPartyType());

      List<S> entities = namedEntityService.findResolvedEntities(nedId, child);

      for (Entity entity : entities)
        if (entity.getId().equals(pkId))
          return Response.status(Response.Status.OK).entity(entity).build();

      return entityNotFound(child.getSimpleName() + " " + pkId);

    } catch (NedException e) {
      if (EntityNotFound.equals(e.getErrorType())) { return entityNotFound(e); }
      throw e;
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
  protected <S extends Entity>
  Response getEntities(int nedId, Class<S> child) {

    try {
      namedEntityService.checkNedIdForType(nedId, getNamedPartyType());

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
      else if (cname.equals(Degree.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
            new GenericEntity<List<Degree>>(
                namedEntityService.findResolvedEntities(nedId, Degree.class)
            ){}).build();
      }
      else if (cname.equals(Url.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
            new GenericEntity<List<Url>>(
                namedEntityService.findResolvedEntities(nedId, Url.class)
            ){}).build();
      }
      else if (cname.equals(Uniqueidentifier.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
            new GenericEntity<List<Uniqueidentifier>>(
                namedEntityService.findResolvedEntities(nedId, Uniqueidentifier.class)
            ){}).build();
      } else if (cname.equals(Individualprofile.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
            new GenericEntity<List<Individualprofile>>(
                namedEntityService.findResolvedEntities(nedId, Individualprofile.class)
            ){}).build();
      }
      throw new UnsupportedOperationException("Unsupported child entity: " + child.getSimpleName());

    } catch (NedException e) {
      if (EntityNotFound.equals(e.getErrorType())) { return entityNotFound(e); }
      throw e;
    } catch (Exception e) {
      return serverError(e, String.format("Find %s by nedId failed", child.getSimpleName()));
    }
  }
}
