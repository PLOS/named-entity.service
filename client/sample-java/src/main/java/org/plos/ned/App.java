package org.plos.ned;

import io.swagger.client.*;
import io.swagger.client.api.*;

// run with : mvn install -DskipTests && mvn exec:java

public class App {
    public static void main( String[] args ) throws ApiException {

        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("http://dev:dev@localhost:8080");

        ServiceApi serviceApi = new ServiceApi(apiClient);

        System.out.println("apiclient basepath: " + apiClient.getBasePath());

        System.out.println(serviceApi.config());
    }
}
