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

public abstract class BaseDomHandler implements DomHandler<String, StreamResult> {

  // TODO: flushing/closing writer instance doesn't clear buffer. workaround 
  // by instantiating new writer.
  //private StringWriter xmlWriter = new StringWriter();

  @Override
  public StreamResult createUnmarshaller(ValidationEventHandler errorHandler) {
    //return new StreamResult(xmlWriter);
    return new StreamResult(new StringWriter());
  }

  @Override
  public String getElement(StreamResult rt) {

    // json properties are stored as attributes in the root element, and also as
    // child nodes. we'll process the latter for now. sample playload:
    //
    // JSON : "metadata" : { "x":"1","y":"2" }
    // <?xml version="1.0" encoding="UTF-8"?><metadata x="1" y="2"><x>1</x><y>2</y></metadata>"

    String xml = rt.getWriter().toString();

    // look for first greater than (>) character after start of tag. assume
    // greater than in content is escaped (ie, &gt;)

    int beginIndex = xml.indexOf(">", xml.indexOf("<"+getTagName())) + 1;

    // endtag won't be found if xml element has no content (ex: <metadata/>)
    int endIndex = xml.indexOf(getEndTag());

    // return value will be stored in database
    return (endIndex != -1) ? xml.substring(beginIndex, endIndex) : null;
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

  protected String getTagName() {
    throw new UnsupportedOperationException("Tag name not defined!");
  }
  protected String getStartTag() {
    return "<"+getTagName()+">";
  }
  protected String getEndTag() {
    return "</"+getTagName()+">";
  }
}
