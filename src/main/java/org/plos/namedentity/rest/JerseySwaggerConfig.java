package org.plos.namedentity.rest;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Info;
import io.swagger.models.Swagger;
import io.swagger.models.auth.BasicAuthDefinition;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class JerseySwaggerConfig extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

        Info info = new Info()
                //.version("123")
                .title("Named Entity Database API");
                //.description("Query user data here!");

        ServletContext context = config.getServletContext();
        Swagger swagger = new Swagger().info(info);

        swagger.securityDefinition("basic", new BasicAuthDefinition());

        context.setAttribute("swagger", swagger);


//        super.init(config);
//
//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setVersion("1.0.2");
//        beanConfig.setSchemes(new String[]{"http"});
//        beanConfig.setHost("localhost:8080");
//        beanConfig.setBasePath("/");
////    beanConfig.setResourcePackage("io.swagger.resources");
//        beanConfig.setScan(true);
    }

//  static {
//
//// ConfigFactory.config().setBasePath("http://www.foo.com/");
//    ApiInfo info = new ApiInfo(
//        null,//"Named Entity Database API", // title
//        null, // description
//        null, // TOS URL
//        null, // Contact
//        null, // license
//        null // license URL
//    );
//
//    ConfigFactory.config().setApiInfo(info);
//    //ConfigFactory.config().setApiVersion("v1");
//
//  }
}