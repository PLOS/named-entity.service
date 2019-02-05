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
package org.plos.namedentity.api.adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** @author Blaise Doughan */
public class MapAdapter extends XmlAdapter<AdaptedMap, Map<String,String>> {

  @Override
  public AdaptedMap marshal(Map<String,String> map) throws Exception {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document document = db.newDocument();
    Element rootElement = document.createElement("root");
    document.appendChild(rootElement);

    for(Entry<String,String> entry : map.entrySet()) {
      Element mapElement = document.createElement(entry.getKey());
      mapElement.setTextContent(entry.getValue());
      rootElement.appendChild(mapElement);
    }

    AdaptedMap adaptedMap = new AdaptedMap();
    adaptedMap.setValue(document);
    return adaptedMap;
  }

  @Override
  public Map<String, String> unmarshal(AdaptedMap adaptedMap) throws Exception {
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
}
