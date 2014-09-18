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

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class Main extends ResourceConfig {

  public Main() {
    // register jax-rs components
    packages("org.plos.namedentity.rest");

    MOXyJsonProvider moxyJsonProvider = new MOXyJsonProvider();
    moxyJsonProvider.setAttributePrefix("@");
    moxyJsonProvider.setFormattedOutput(true);
    moxyJsonProvider.setIncludeRoot(false);
    moxyJsonProvider.setMarshalEmptyCollections(false);
    moxyJsonProvider.setValueWrapper("$");

    register(moxyJsonProvider);

    // set json provider properties here (moxy)
    //property(MarshallerProperties.JSON_INCLUDE_ROOT, false);
  }
}
