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

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.oxm.json.JsonStructureSource;

import org.plos.namedentity.api.entity.Typedescription;

import static org.junit.Assert.fail;

public abstract class BaseResourceTest extends SpringContextAwareJerseyTest {

  public static final String TYPE_CLASS_URI = "/typeclasses";

  protected Integer findTypeClassIdByName(String name) throws JAXBException {
    Response response  = target(TYPE_CLASS_URI).request(MediaType.APPLICATION_JSON_TYPE).get();
    String jsonPayload = response.readEntity(String.class);

    Unmarshaller unmarshaller = jsonUnmarshaller(Typedescription.class);
    List<Typedescription> typedescriptions = unmarshalEntities(jsonPayload, Typedescription.class, unmarshaller);

    for (Typedescription typeclass : typedescriptions) {
      if (typeclass.getDescription().equals(name)) {
        return typeclass.getId();
      }
    }
    fail("Unable to find TypeClass:"+name);
    return null;
  }

  protected <T> JAXBContext jsonJaxbContext(Class<T> clazz) throws JAXBException {
    Map<String, Object> properties = new HashMap<String, Object>(2);
    properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
    properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
    return JAXBContext.newInstance(new Class[]{clazz}, properties);
  }

  protected <T> Unmarshaller jsonUnmarshaller(Class<T> clazz) throws JAXBException {
    JAXBContext jc = jsonJaxbContext(clazz);
    return jc.createUnmarshaller();
  }

  protected <T> Marshaller jsonMarshaller(Class<T> clazz) throws JAXBException {
    JAXBContext jc = jsonJaxbContext(clazz);
    return jc.createMarshaller();
  }

  protected <T> T unmarshalEntity(String json, Class<T> clazz, Unmarshaller unmarshaller)
      throws JAXBException {
    return unmarshaller.unmarshal(new StreamSource(new StringReader(json)), clazz).getValue();
  }

  @SuppressWarnings("unchecked")
  protected <T> List<T> unmarshalEntities(String json, Class<T> clazz, Unmarshaller unmarshaller)
      throws JAXBException {
    JsonReader jsonReader = Json.createReader(new StringReader(json));
    JsonArray jsonArray = jsonReader.readArray();
    JsonStructureSource arraySource = new JsonStructureSource(jsonArray);
    return (List<T>) unmarshaller.unmarshal(arraySource, clazz).getValue();
  }

  protected <T> String writeValueAsString(T t) throws JAXBException {
    Writer writer = new StringWriter();
    Marshaller marshaller = jsonMarshaller(t.getClass());
    marshaller.marshal(t, writer);
    return writer.toString();
  }
}
