package org.plos.ned;

import io.swagger.client.*;
import io.swagger.client.api.*;

// run with : mvn install -DskipTests && mvn exec:java

public class App {
    public static void main( String[] args ) throws ApiException {

        ApiClient apiclient = new ApiClient();
        apiclient.setDebugging(true);
        apiclient.setBasePath("http://localhost:8080");
        // apiclient.setUsername("dev");
        // apiclient.setPassword("dev");

        // TODO: generate this header from user/password
        apiclient.addDefaultHeader("Authorization", "Basic ZGV2OmRldg==");

        System.out.println("apiclient basepath: " + apiclient.getBasePath());

        ServiceApi serviceApi = new ServiceApi(apiclient);
        TypeclassesApi typeclassesapi = new TypeclassesApi(apiclient);

        System.out.println(serviceApi.config());

        // TODO: regenerate client so it knows this is a list
        System.out.println("typeclasss: " + typeclassesapi.list(null, null).size());

    }
}
