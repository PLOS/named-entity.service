package org.plos.namedentity.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class Main extends ResourceConfig {

    public Main() {
        // register jax-rs components
        packages("org.plos.namedentity.rest");
    }
}
