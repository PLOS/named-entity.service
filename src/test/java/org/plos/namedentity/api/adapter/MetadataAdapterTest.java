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

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MetadataAdapterTest {

  private MetadataAdapter mdAdapter = new MetadataAdapter();

  @Test
  public void testMarshallingMetadata() throws Exception {
    final String orcidMetadata = "{\"accessToken\":\"a9d2479e-9ff4-470a-a9c2-0b4ff9391cae\","
                               + "\"refreshToken\":\"0c91a121-a6b2-4058-b731-305708159360\","
                               + "\"tokenScope\":\"/orcid-profile/read-limited\","
                               + "\"tokenExpires\":\"2034-06-02T04:48:49Z\","
                               + "\"lastModified\":\"2014-06-02T08:33:31Z\","
                               + "\"created\":\"2014-06-02T08:33:31Z\"}";

    Map<String,String> jsonMap = MetadataAdapter.parseAsMap(orcidMetadata);
    assertEquals(6, jsonMap.size());
    assertEquals("0c91a121-a6b2-4058-b731-305708159360", jsonMap.get("refreshToken"));

    AdaptedMap adaptedMap = mdAdapter.marshal(jsonMap);
    Document doc = (Document) adaptedMap.getValue();  // xml document
    Element root = doc.getDocumentElement();          // <metadata>
    /*
      <metadata>
        <accessToken>a9d2479e-9ff4-470a-a9c2-0b4ff9391cae</accessToken>
        <refreshToken>0c91a121-a6b2-4058-b731-305708159360</refreshToken>
        <tokenScope>/orcid-profile/read-limited</tokenScope>
        <tokenExpires>2034-06-02T04:48:49Z</tokenExpires>
        <lastModified>2014-06-02T08:33:31Z</lastModified>
        <created>2014-06-02T08:33:31Z</created>
      </metadata>
    */
    NodeList nodes = root.getChildNodes();
    for(int i=0; i<nodes.getLength(); i++){
      Node node = nodes.item(i);
      assertNotNull(jsonMap.get(node.getNodeName()), node.getTextContent());
    }
  }

  @Test(expected=UnsupportedOperationException.class)
  public void testUnmarshallingMetadata() throws Exception {
    mdAdapter.unmarshal(null);
  }
}
