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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JsonAdapterTest {

  private JsonAdapter jsonAdapter = new JsonAdapter();

  private final String orcidMetadata =
     "{\"accessToken\":\"a9d2479e-9ff4-470a-a9c2-0b4ff9391cae\","
    + "\"refreshToken\":\"0c91a121-a6b2-4058-b731-305708159360\","
    + "\"tokenScope\":\"/orcid-profile/read-limited\","
    + "\"tokenExpires\":\"2034-06-02T04:48:49Z\","
    + "\"lastModified\":\"2014-06-02T08:33:31Z\","
    + "\"created\":\"2014-06-02T08:33:31Z\"}";

  @Test
  public void testMarshallingMetadata() throws Exception {

    Map<String,String> jsonMap = JsonAdapter.parseAsMap(orcidMetadata);
    assertEquals(6, jsonMap.size());
    assertEquals("0c91a121-a6b2-4058-b731-305708159360", jsonMap.get("refreshToken"));

    AdaptedMap adaptedMap = jsonAdapter.marshal(jsonMap);
    Document doc = (Document) adaptedMap.getValue();  // xml document
    Element root = doc.getDocumentElement();          // <json>
    /*
      <json>
        <accessToken>a9d2479e-9ff4-470a-a9c2-0b4ff9391cae</accessToken>
        <refreshToken>0c91a121-a6b2-4058-b731-305708159360</refreshToken>
        <tokenScope>/orcid-profile/read-limited</tokenScope>
        <tokenExpires>2034-06-02T04:48:49Z</tokenExpires>
        <lastModified>2014-06-02T08:33:31Z</lastModified>
        <created>2014-06-02T08:33:31Z</created>
      </json>
    */
    NodeList nodes = root.getChildNodes();
    for(int i=0; i<nodes.getLength(); i++){
      Node node = nodes.item(i);
      assertNotNull(jsonMap.get(node.getNodeName()), node.getTextContent());
    }
  }

  @Test
  public void testJsonifyingMap() throws Exception {
    Map<String,String> jsonMap = JsonAdapter.parseAsMap(orcidMetadata);
    String json = JsonAdapter.jsonifyMap( jsonMap );
    Map<String,String> jsonMap2 = JsonAdapter.parseAsMap(json);
    assertEquals(jsonMap, jsonMap2);
  }

  @Test
  public void testUnmarshallingMetadata() throws Exception {

    // json string -> json map -> xml document
    Map<String,String> jsonMap = JsonAdapter.parseAsMap(orcidMetadata);
    AdaptedMap marshalXmlWrapper = jsonAdapter.marshal(jsonMap);
    Document marshalXmlDoc = (Document)marshalXmlWrapper.getValue();

    // JSON Adapter's unmarshalling looks up nodes by local name. This will be
    // null for nodes that are not namespace-aware. Unfortunately, the adapter's
    // marshalling creates a non-namespace document! Hmmmm... The adapter works
    // as currently written, so reluctant to change without testing. The
    // workaround for now is to clone non-namespace aware xml doc into a
    // namespace aware one.

    Document docNS = createDocumentWithNamespace( marshalXmlDoc );

    AdaptedMap unmarshalXmlWrapper = new AdaptedMap();
    unmarshalXmlWrapper.setValue( docNS.getDocumentElement() );

    // xml document -> json map
    Map<String,String> jsonMap2 = jsonAdapter.unmarshal( unmarshalXmlWrapper );
    assertEquals(jsonMap, jsonMap2);
  }

  private Document createDocumentWithNamespace(Document doc) throws Exception {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setNamespaceAware(true);
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document docNS = db.newDocument();

    Element rootElement = doc.getDocumentElement();
    Element rootNS = docNS.createElementNS(null, rootElement.getNodeName());
    docNS.appendChild(rootNS);

    NodeList childNodes = rootElement.getChildNodes();
    for(int x=0,size=childNodes.getLength(); x<size; x++) {
      Node childNode = childNodes.item(x);
      if(childNode.getNodeType() == Node.ELEMENT_NODE) {
        Element nodeNS = docNS.createElementNS(null, childNode.getNodeName());
        nodeNS.setTextContent( childNode.getTextContent() );
        rootNS.appendChild(nodeNS);
      }
    }
    return docNS;
  }
}
