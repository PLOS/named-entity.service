package org.plos.namedentity.rest;

import org.junit.Test;

import javax.ws.rs.core.Response;

/**
 * Created by jfinger on 12/23/15.
 */
public class QueriesResourceTest extends BaseResourceTest {

  private static final String BASE_URI = "/queries";

  @Test
  public void testAlerts() throws Exception {

    Response response = target(BASE_URI + "/alerts/weekly")
        .request().get();

    // TODO: finish this
  }
}
