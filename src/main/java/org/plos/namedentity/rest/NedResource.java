/*
 * Copyright (c) 2014-2019 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package org.plos.namedentity.rest;

import io.swagger.annotations.ApiOperation;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import static org.plos.namedentity.api.NedException.ErrorType.EntityNotFound;
import static org.plos.namedentity.api.NedException.ErrorType.InvalidSearchCriteria;

@Produces(MediaType.APPLICATION_JSON)
public abstract class NedResource extends BaseResource {

  /* ----------------------------------------------------------------------- */
  /*  EMAIL CRUD                                                             */
  /* ----------------------------------------------------------------------- */

  @POST
  @Path("/{nedId}/emails")
  @ApiOperation(value = "Create email", response = Email.class)
  public Response createEmail(@PathParam("nedId") int nedId,
                              Email emailEntity,
                              @HeaderParam("Authorization") String authstring) {

    return createEntity(nedId, emailEntity, authstring);
  }

  @PUT
  @Path("/{nedId}/emails/{emailId}")
  @ApiOperation(value = "Update email", response = Email.class)
  public Response updateEmail(@PathParam("nedId") int nedId,
                              @PathParam("emailId") int emailId,
                              Email emailEntity,
                              @HeaderParam("Authorization") String authstring) {
    return updateEntity(nedId, emailId, emailEntity, authstring);
  }

  @DELETE
  @Path("/{nedId}/emails/{emailId}")
  @ApiOperation(value = "Delete email")
  public Response deleteEmail(@PathParam("nedId") int nedId,
                              @PathParam("emailId") int emailId,
                              @HeaderParam("Authorization") String authstring) {
    //TODO: process authstring

    if (((List) (getEntities(nedId, Email.class).getEntity())).size() == 1)
      return nedError(new NedException("Email entities cannot be empty"), "Unable to delete email");

    return deleteEntity(nedId, emailId, Email.class);
  }

  @GET
  @Path("/{nedId}/emails/{emailId}")
  @ApiOperation(value = "Read email", response = Email.class)
  public Response getEmail(@PathParam("nedId")   int nedId,
                           @PathParam("emailId") int emailId) {
    return getEntity(nedId, emailId, Email.class);
  }

  @GET
  @Path("/{nedId}/emails")
  @ApiOperation(value = "List emails", response = Email.class, responseContainer = "List")
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
                                Address addressEntity,
                                @HeaderParam("Authorization") String authstring) {
    return createEntity(nedId, addressEntity, authstring);
  }

  @PUT
  @Path("/{nedId}/addresses/{addressId}")
  @ApiOperation(value = "Update address", response = Address.class)
  public Response updateAddress(@PathParam("nedId")     int nedId,
                                @PathParam("addressId") int addressId,
                                Address addressEntity,
                                @HeaderParam("Authorization") String authstring) {
    return updateEntity(nedId, addressId, addressEntity, authstring);
  }

  @DELETE
  @Path("/{nedId}/addresses/{addressId}")
  @ApiOperation(value = "Delete address", response = Address.class, responseContainer = "List")
  public Response deleteAddress(@PathParam("nedId")     int nedId,
                                @PathParam("addressId") int addressId,
                                @HeaderParam("Authorization") String authstring) {
    //TODO: process authstring
    return deleteEntity(nedId, addressId, Address.class);
  }

  @GET
  @Path("/{nedId}/addresses/{addressId}")
  @ApiOperation(value = "Read address", response = Address.class)
  public Response getAddress(@PathParam("nedId")     int nedId,
                             @PathParam("addressId") int addressId) {
    return getEntity(nedId, addressId, Address.class);
  }

  @GET
  @Path("/{nedId}/addresses")
  @ApiOperation(value = "List addresses", response = Address.class, responseContainer = "List")
  public Response getAddresses(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Address.class);
  }

  /* ----------------------------------------------------------------------- */
  /*  PHONE NUMBER CRUD                                                      */
  /* ----------------------------------------------------------------------- */

  @GET
  @Path("/{nedId}/phonenumbers")
  @ApiOperation(value = "List phone numbers", response = Phonenumber.class, responseContainer = "List")
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
                            Uniqueidentifier entity,
                            @HeaderParam("Authorization") String authstring) {
    return createEntity(nedId, entity, authstring);
  }

  @PUT
  @Path("/{nedId}/uids/{id}")
  @ApiOperation(value = "Update UID", response = Uniqueidentifier.class)
  public Response updateUid(@PathParam("nedId") int nedId,
                            @PathParam("id")    int id,
                            @HeaderParam("Authorization") String authstring,
                            Uniqueidentifier entity) {
    return updateEntity(nedId, id, entity, authstring);
  }

  @GET
  @Path("/{nedId}/uids/{id}")
  @ApiOperation(value = "Read uid", response = Uniqueidentifier.class)
  public Response getUid(@PathParam("nedId") int nedId,
                         @PathParam("id")    int id) {
    return getEntity(nedId, id, Uniqueidentifier.class);
  }

  @GET
  @Path("/{nedId}/uids")
  @ApiOperation(value = "List UIDs", response = Uniqueidentifier.class, responseContainer = "List")
  public Response getUids(@PathParam("nedId") int nedId) {
    return getEntities(nedId, Uniqueidentifier.class);
  }

  /*      GENERIC OPERATIONS              */

  protected <S extends Entity>
  Response createEntity(int nedId, S entity, String authHeader) {
    try {
      namedEntityService.checkNedIdForType(nedId, getNamedPartyType());

      setCreatedAndLastModifiedBy(authHeader,entity,true);

      entity.setNedid(nedId);

      namedEntityService.resolveValuesToIds(entity);

      Integer pkId = crudService.create(entity);

      return Response.status(Response.Status.OK).entity(
          namedEntityService.findResolvedEntityByKey(pkId, entity.getClass())).build();

    } catch (NedException e) {
      return nedError(e, "Unable to create " + entity.getClass().getSimpleName());
    } catch (Exception e) {
      return serverError(e, "Unable to create " + entity.getClass().getSimpleName());
    }
  }

  protected <S extends Entity>
  Response updateEntity(int nedId, int pkId, S entity, String authHeader) {

    try {

      namedEntityService.checkNedIdForType(nedId, getNamedPartyType());

      setCreatedAndLastModifiedBy(authHeader,entity,false);

      entity.setId(pkId);
      entity.setNedid(nedId);

      namedEntityService.resolveValuesToIds(entity);

      crudService.update(entity);

      Entity dbEntity = namedEntityService.findResolvedEntityByKey(pkId, entity.getClass());

      return Response.status(Response.Status.OK).entity(dbEntity).build();

    } catch (NedException e) {
      return nedError(e, "Unable to update " + entity.getClass().getSimpleName());
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

    } catch (NedException e) {
      return nedError(e, "Unable to delete " + child.getSimpleName());
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

      throw new NedException(EntityNotFound, String.format("%s (id=%d)", child.getSimpleName(), pkId));

    } catch (NedException e) {
      return nedError(e, "Find by id failed");

    } catch (Exception e) {
      return serverError(e, String.format("Find %s by id failed (nedId=%d, pkId=%d)", 
        child.getSimpleName(), nedId, pkId));
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
      else if (cname.equals(Alert.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
            new GenericEntity<List<Alert>>(
                namedEntityService.findResolvedEntities(nedId, Alert.class)
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
      else if (cname.equals(Relationship.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
            new GenericEntity<List<Relationship>>(
                namedEntityService.findResolvedEntities(nedId, Relationship.class)
            ){}).build();
      }
      else if (cname.equals(Group.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
            new GenericEntity<List<Group>>(
                namedEntityService.findResolvedEntities(nedId, Group.class)
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
      else if (cname.equals(Auth.class.getCanonicalName())) {
        return Response.status(Response.Status.OK).entity(
            new GenericEntity<List<Auth>>(
                namedEntityService.findResolvedEntities(nedId, Auth.class)
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
      return nedError(e, String.format("Find %s by nedId failed", child.getSimpleName()));
    } catch (Exception e) {
      return serverError(e, String.format("Find %s by nedId failed", child.getSimpleName()));
    }
  }

  protected <T extends Composite>
  Entity createSearchCriteria(String entity, String attribute, String value, Class<T> compositeClass) {

    String capitalizedEntity = Character.toUpperCase(entity.charAt(0)) + entity.substring(1);

    Entity searchEntity = null;

    try {
      Class entityClass = Class.forName("org.plos.namedentity.api.entity." + capitalizedEntity);
      searchEntity = (Entity) entityClass.newInstance();

      // we were able to instantiate entity. verify is a member of composite.

      boolean validEntityForComposite = false;

      for (Field f : compositeClass.getDeclaredFields()) {
        Class<?> compositeMemberClass = null;

        if (List.class.isAssignableFrom(f.getType())) {
          ParameterizedType paramType = (ParameterizedType) f.getGenericType();
          compositeMemberClass = (Class<?>) paramType.getActualTypeArguments()[0];
        } else {
          compositeMemberClass = f.getType();
        }

        if (compositeMemberClass.getCanonicalName().equals(entityClass.getCanonicalName())) {
          validEntityForComposite = true ; break ;
        }
      }

      if (!validEntityForComposite) {
        throw new NedException(InvalidSearchCriteria, String.format("Invalid entity (%s) for composite type (%s)",
          entityClass.getSimpleName(), compositeClass.getSimpleName()));
      }

      Field field = entityClass.getDeclaredField(attribute);
      field.setAccessible(true);
      field.set(searchEntity, value);

      namedEntityService.resolveValuesToIds(searchEntity);

      if (attribute.endsWith("type"))
        field.set(searchEntity, null);
    }
    catch (ClassNotFoundException e) {
      throw new NedException(InvalidSearchCriteria, "Verify entity name: "+entity);
    }
    catch (NoSuchFieldException e) {
      throw new NedException(InvalidSearchCriteria, "Verify attribute name: "+attribute);
    }
    catch (NedException e) {
      throw e;
    }
    catch (Exception e) {
      throw new NedException(InvalidSearchCriteria);
    }
    return searchEntity;
  }

  protected <S extends Entity>
  void setCreatedAndLastModifiedBy(String authHeader, S entity, boolean create) {
    Map<String,String> consumer = authService.parseCredentials(authHeader);
    if (create) {
      entity.setCreatedbyname(consumer.get("appname"));
    }
    entity.setLastmodifiedbyname(consumer.get("appname"));
  }

  protected <T extends Composite>
  void setCreatedAndLastModifiedBy(String authHeader, T composite) {
    Map<String,String> consumer = authService.parseCredentials(authHeader);

    Map<Class, List<? extends Entity>> compositeMap = composite.readAsMap();

    for (List<? extends Entity> entities : compositeMap.values()) {
      if (entities != null) {
        for (Entity entity : entities) {
          entity.setCreatedbyname(consumer.get("appname"));
          entity.setLastmodifiedbyname(consumer.get("appname"));
        }
      }
    }
  }
}
