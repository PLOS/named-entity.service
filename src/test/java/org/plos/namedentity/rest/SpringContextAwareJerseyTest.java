/*
 * Copyright (c) 2006-2014 by Public Library of Science
 * http://plos.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.plos.namedentity.rest;

import org.plos.namedentity.spring.config.ContextConfig;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.AfterClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class SpringContextAwareJerseyTest extends JerseyTest {

  //private static AnnotationConfigApplicationContext context;
  private static ClassPathXmlApplicationContext context;

  @Override
  protected javax.ws.rs.core.Application configure() {
    //context = new AnnotationConfigApplicationContext(TestSpringConfig.class);
    String[] springFiles = { "spring-beans.xml", "spring-beans.test.xml" };
    context = new ClassPathXmlApplicationContext(springFiles);
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
