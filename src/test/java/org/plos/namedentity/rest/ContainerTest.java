/*
 * Copyright (c) 2006-2015 by Public Library of Science
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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.plos.namedentity.api.adapter.Container;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ContainerTest extends BaseResourceTest {

  private Unmarshaller unmarshaller;
  private Container    c1;

  @Before
  public void setup() throws JAXBException {
    this.unmarshaller = jsonUnmarshaller(Container.class);
    this.c1 = new Container();
  }

  @Test
  public void testMarshallingEmptyContainer() throws JAXBException {

    String json = writeValueAsString(this.c1);
    Container c2 = unmarshalEntity(json, Container.class, this.unmarshaller);

    assertEquals(this.c1.getMap(), c2.getMap());
    assertEquals(0, this.c1.getMap().size());
  }

  @Test
  public void testMarshallingContainerWithOneEntry() throws JAXBException {

    Map<String,String> map = new HashMap<>();
    map.put("valid","true");
    this.c1.setMap(map);

    // marshall pojo (-> json)
    // json (without MapAdapter) = "{"map":{"entry":[{"key":"valid","value":"true"}]}}"
    // json (with MapAdapter)    = "{"result":{"valid":"true"}}"
    String json = writeValueAsString(this.c1);

    Container c2 = unmarshalEntity(json, Container.class, this.unmarshaller);

    assertEquals(this.c1.getMap(), c2.getMap());
    assertEquals(1, this.c1.getMap().size());
  }

  @Test
  public void testMarshallingContainerWithManyEntries() throws JAXBException {

    Map<String,String> map = new HashMap<>();
    map.put("key1","value1");
    map.put("key2","value2");
    map.put("key3","value3");
    this.c1.setMap(map);

    String json = writeValueAsString(this.c1);

    Container c2 = unmarshalEntity(json, Container.class, this.unmarshaller);

    assertEquals(this.c1.getMap(), c2.getMap());
    assertEquals(3, this.c1.getMap().size());
  }
}
