package org.plos.ned;

import io.swagger.client.*;
import io.swagger.client.api.*;
import io.swagger.client.auth.*;

import java.io.UnsupportedEncodingException;
import javax.xml.bind.DatatypeConverter;

// run with : mvn install -DskipTests && mvn exec:java

public class App {
    public static void main( String[] args ) throws ApiException {

        ApiClient apiclient = new ApiClient();
        apiclient.setDebugging(true);
        apiclient.setBasePath("http://localhost:8080");

        // apiclient.setUsername("dev");
        // apiclient.setPassword("dev");

        String username = "dev";
        String password = "dev";
        String str = (username == null ? "" : username) + ":" + (password == null ? "" : password);
        try {
          apiclient.addDefaultHeader("Authorization", "Basic " + DatatypeConverter.printBase64Binary(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
          throw new RuntimeException(e);
        }

        System.out.println("apiclient basepath: " + apiclient.getBasePath());

        ServiceApi serviceApi = new ServiceApi(apiclient);
        TypeclassesApi typeclassesapi = new TypeclassesApi(apiclient);

        System.out.println(serviceApi.config());

        // TODO: regenerate client so it knows this is a list
        System.out.println("typeclasss: " + typeclassesapi.list(null, null));

    }
}
