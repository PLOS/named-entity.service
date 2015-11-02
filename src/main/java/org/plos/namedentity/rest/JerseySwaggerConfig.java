package org.plos.namedentity.rest;

import io.swagger.models.Swagger;
import io.swagger.models.auth.BasicAuthDefinition;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class JerseySwaggerConfig extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

        ServletContext context = config.getServletContext();
        Swagger swagger = new Swagger();

        swagger.securityDefinition("basic", new BasicAuthDefinition());

        context.setAttribute("swagger", swagger);
    }
}
