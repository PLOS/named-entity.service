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

import java.io.*;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public abstract class BaseDomHandler implements DomHandler<String, StreamResult> {

  @Override
  public StreamResult createUnmarshaller(ValidationEventHandler errorHandler) {
    return new StreamResult(new StringWriter());
  }

  @Override
  public String getElement(StreamResult rt) {

    String xml = getTagContent(rt.getWriter().toString());

    try {
      // content may be json (less common), so try to parse it as such.
      // if succeeds, update xml representation with parsed result.
      xml = XML.toString(new JSONObject(xml));
    }
    catch (org.json.JSONException e) {
      // not valid json. assume original content is valid xml.
    }
    // return value stored in database
    return isEmptyOrBlank(xml) ? null : xml;
  }

  @Override
  public Source marshal(String n, ValidationEventHandler errorHandler) {
    try {
      String xml = getStartTag() + n.trim() + getEndTag();
      StringReader xmlReader = new StringReader(xml);
      return new StreamSource(xmlReader);
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected String getTagContent(String xml) {

    // tag content will either be xml or a stringified json representation.
    // in either case, we'll extract and return content "as is".
    //
    // Note that JAXB unmarshals payload into xml attributes and child nodes.
    // We ignore the attributes and process the nodes.
    //
    //   <?xml version="1.0" encoding="UTF-8"?><metadata>{ "x":"1","y":"2" }</metadata>"
    //   <?xml version="1.0" encoding="UTF-8"?><metadata x="1" y="2"><x>1</x><y>2</y></metadata>"

    // look for first greater than (>) character after start of tag.
    // assume greater than in content is escaped (ie, &gt;)

    int beginIndex = xml.indexOf(">", xml.indexOf("<"+getTagName())) + 1;

    // end tag won't be found if xml element has no content (ex: <metadata/>)
    int endIndex = xml.indexOf(getEndTag());

    return (endIndex != -1) ? xml.substring(beginIndex, endIndex) : "";
  }


  protected String getTagName() {
    throw new UnsupportedOperationException("Tag name not defined!");
  }
  protected String getStartTag() {
    return "<"+getTagName()+">";
  }
  protected String getEndTag() {
    return "</"+getTagName()+">";
  }

  protected boolean isEmptyOrBlank(String s) {
    return s == null || s.trim().isEmpty();
  }
}
