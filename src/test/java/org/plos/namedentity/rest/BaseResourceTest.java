/*
 * Copyright (c) 2014-2019 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
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
