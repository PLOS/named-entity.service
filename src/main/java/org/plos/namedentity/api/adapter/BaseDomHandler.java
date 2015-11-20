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
      //String xml = getStartTag() + n.trim() + getEndTag();
      String xml = getStartTag() + "<accessToken>a9d2479e-9ff4-470a-a9c2-0b4ff9391cae</accessToken><x>1</x>" + getEndTag();
//| 2337 |   957 |    529 | 0000-0002-7680-7527 |            7 | {"accessToken":"a9d2479e-9ff4-470a-a9c2-0b4ff9391cae","refreshToken":"0c91a121-a6b2-4058-b731-305708159360","tokenScope":"/orcid-profile/read-limited","tokenExpires":"2034-06-02T04:48:49Z","lastModified":"2014-06-02T08:33:31Z","created":"2014-06-02T08:33:31Z"} | 2015-10-15 
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
