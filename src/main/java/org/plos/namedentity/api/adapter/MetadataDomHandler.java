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
package org.plos.namedentity.api.adapter;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.transform.stream.StreamResult;

public class MetadataDomHandler extends BaseDomHandler {

  // MOXY JAXB UNMARSHALLING code must be doing some kind of reflective stuff,
  // because if we remove either createUnmarshaller() or getElement() methods, 
  // both of which are implemented in the parent class, unmarshalling fails 
  // (unable to pinpoint exactly why).

  @Override
  public StreamResult createUnmarshaller(ValidationEventHandler errorHandler) {
    return super.createUnmarshaller(errorHandler);
  }

  @Override
  public String getElement(StreamResult rt) {
    return super.getElement(rt);
  }

  @Override
  protected String getTagName() {
    return "metadata";
  }
}
