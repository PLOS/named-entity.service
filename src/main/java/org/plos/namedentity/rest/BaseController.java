package org.plos.namedentity.rest;

import org.apache.log4j.Logger;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.service.CrudService;
import org.plos.namedentity.service.NamedEntityService;
import org.plos.namedentity.utils.Transformer;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BaseController {

  static Logger logger = Logger.getLogger(BaseController.class);

  @Inject
  protected CrudService crudService;

  @Inject
  protected NamedEntityService namedEntityService;

  @Inject
  protected Transformer transformer;

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
