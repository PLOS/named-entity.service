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

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JsonAdapter extends XmlAdapter<AdaptedMap, Map<String,String>> {

  @Override
  public AdaptedMap marshal(Map<String,String> map) throws Exception {
    if (map == null) return null;

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document document = db.newDocument();
    Element rootElement = document.createElement("json");
    document.appendChild(rootElement);

    for (Map.Entry<String,String> entry : map.entrySet()) {
      Element mapElement = document.createElement(entry.getKey());
      mapElement.setTextContent(entry.getValue());
      rootElement.appendChild(mapElement);
    }

    AdaptedMap adaptedMap = new AdaptedMap();
    adaptedMap.setValue(document);
    return adaptedMap;
  }

  @Override
  public Map<String,String> unmarshal(AdaptedMap adaptedMap) throws Exception {
    if (adaptedMap == null || adaptedMap.getValue() == null) return null;

    Map<String, String> map = new HashMap<String, String>();
    Element rootElement = (Element) adaptedMap.getValue();
    NodeList childNodes = rootElement.getChildNodes();
    for(int x=0,size=childNodes.getLength(); x<size; x++) {
      Node childNode = childNodes.item(x);
      if(childNode.getNodeType() == Node.ELEMENT_NODE) {
        map.put(childNode.getLocalName(), childNode.getTextContent());
      }
    }
    return map;
  }

  public static Map<String,String> parseAsMap(String json) {

    JsonReader reader   = Json.createReader(new StringReader(json));
    JsonObject mdObject = reader.readObject();
    reader.close();

    Map<String,String> map = new HashMap<>();
    for (Map.Entry<String,JsonValue> entry : mdObject.entrySet()) {
      map.put(entry.getKey(), entry.getValue().toString().replace("\"",""));
    }
    return map;
  }

  public static String jsonifyMap(Map<String,String> map) {

    JsonObjectBuilder builder = Json.createObjectBuilder();

    for (Map.Entry<String,String> entry : map.entrySet()) {
      builder.add(entry.getKey(), entry.getValue());
    }

    StringWriter stWriter = new StringWriter();
    JsonWriter jsonWriter = Json.createWriter(stWriter);
    jsonWriter.writeObject(builder.build());
    jsonWriter.close();

    String jsonData = stWriter.toString();
    return jsonData;
  }

  private boolean isEmptyOrBlank(String s) {
    return s == null || s.trim().isEmpty();
  }
}
