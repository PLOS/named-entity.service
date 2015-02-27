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
package org.plos.namedentity.validate;

import static org.plos.namedentity.api.NedException.ErrorType.*;

import org.apache.log4j.Logger;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.persist.NamedEntityDBService;

import javax.inject.Inject;

public class EntityPostValidator{ 

  @Inject private NamedEntityDBService namedEntityDBService; 

  protected static Logger logger = Logger.getLogger(EntityPostValidator.class);

  public void validate(Object o) {
    int x = 1;
  }
}
