/*
 * Copyright (c) 2017 Public Library of Science
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

    // marshall pojo (-> json = "{"map":{"root":{"valid":"true"}}}" )
    String json = writeValueAsString(this.c1);

    // unmarshaller will extract the correct payload ignoring "root" and
    // and outer "map" keys (map, a reflected attribute). however, still
    // assert their presence here, so that test will fail if these change 
    // for some reason (change will likely break clients too).

    assertTrue( json.indexOf("{\"map\":{\"root\":") != -1);

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
