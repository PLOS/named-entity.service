package org.plos.namedentity.rest;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.model.ApiInfo;

import javax.servlet.http.HttpServlet;

public class JerseySwaggerConfig extends HttpServlet {

  static {

// ConfigFactory.config().setBasePath("http://www.foo.com/");
    ApiInfo info = new ApiInfo(
        null,//"Named Entity Database API", // title
        null, // description
        null, // TOS URL
        null, // Contact
        null, // license
        null // license URL
    );

    ConfigFactory.config().setApiInfo(info);
    //ConfigFactory.config().setApiVersion("v1");

  }
}
