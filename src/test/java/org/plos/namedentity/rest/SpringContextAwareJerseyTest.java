package org.plos.namedentity.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.AfterClass;
import org.plos.namedentity.spring.config.TestSpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class SpringContextAwareJerseyTest extends JerseyTest {

    private static AnnotationConfigApplicationContext context;

    @Override
    protected javax.ws.rs.core.Application configure() {
        context = new AnnotationConfigApplicationContext(TestSpringConfig.class);
        ResourceConfig config = new Main().property("contextConfig", context);
        return config;
    }

    @AfterClass
    public static void afterClass() throws Exception {
        // since we are manually creating the beans above,
        // we need to close the context explicitly for PreDestory
        context.close();
    }
}
