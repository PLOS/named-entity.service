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
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.json.XML;
import org.json.XMLTokener;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import javax.xml.transform.stream.*;

public class MetadataDomHandlerTest {

  private static final String XML_DECL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  private static final String METADATA_START_TAG = "<metadata>";
  private static final String METADATA_END_TAG   = "</metadata>";

  private MetadataDomHandler handler = new MetadataDomHandler();

  @Test
  public void testTagName() {
    assertEquals("metadata", handler.getTagName());
    assertEquals(METADATA_START_TAG, handler.getStartTag());
    assertEquals(METADATA_END_TAG, handler.getEndTag());
  }

  @Test
  public void testUnmarshallingNoContent() {
    StreamResult streamresult = Mockito.mock(StreamResult.class, RETURNS_DEEP_STUBS);

    String[] payloads = {
      XML_DECL+"<metadata/>",
      XML_DECL+METADATA_START_TAG+"{}"+METADATA_END_TAG
    };

    for (String payload : payloads) {
      when(streamresult.getWriter().toString()).thenReturn(payload);
      assertNull( handler.getElement(streamresult) );
    }
  }

  @Test
  public void testUnmarshallingContentOneProperty() {
    StreamResult streamresult = Mockito.mock(StreamResult.class, RETURNS_DEEP_STUBS);

    final String XML_CONTENT = "<x>1</x>";

    String[] payloads = {
      XML_DECL+"<metadata x=\"1\">"+XML_CONTENT+METADATA_END_TAG,
      XML_DECL+METADATA_START_TAG+"{ \"x\":\"1\" }"+METADATA_END_TAG
    };

    for (String payload : payloads) {
      when(streamresult.getWriter().toString()).thenReturn(payload);
      assertEquals(XML_CONTENT, handler.getElement(streamresult));
    }
  }

  @Test
  public void testUnmarshallingContentTwoProperties() {
    StreamResult streamresult = Mockito.mock(StreamResult.class, RETURNS_DEEP_STUBS);

    final String XML_CONTENT = "<x>1</x><y>2</y>";

    String[] payloads = {
      XML_DECL+"<metadata x=\"1\" y=\"2\">"+XML_CONTENT+METADATA_END_TAG,
      XML_DECL+METADATA_START_TAG+"{ \"x\":\"1\",\"y\":\"2\" }"+METADATA_END_TAG
    };

    for (String payload : payloads) {
      when(streamresult.getWriter().toString()).thenReturn(payload);
      assertEquals(XML_CONTENT, handler.getElement(streamresult));
    }
  }

  @Test
  public void testUnmarshallingOrcidTokenContent() {
    StreamResult streamresult = Mockito.mock(StreamResult.class, RETURNS_DEEP_STUBS);

    final String XML_CONTENT = "<accessToken>a9d2479e-9ff4-470a-a9c2-0b4ff9391cae</accessToken>"
      + "<refreshToken>0c91a121-a6b2-4058-b731-305708159360</refreshToken>"
      + "<tokenScope>/orcid-profile/read-limited</tokenScope>"
      + "<tokenExpires>2034-06-02T04:48:49Z</tokenExpires>"
      + "<lastModified>2014-06-02T08:33:31Z</lastModified>"
      + "<created>2014-06-02T08:33:31Z</created>";

    final String METADATA_START_TAG_WITH_ATTRIBUTES = "<metadata"
      + " \"accessToken\"=\"a9d2479e-9ff4-470a-a9c2-0b4ff9391cae\""
      + " \"refreshToken\"=\"0c91a121-a6b2-4058-b731-305708159360\""
      + " \"tokenScope\"=\"/orcid-profile/read-limited\""
      + " \"tokenExpires\"=\"2034-06-02T04:48:49Z\""
      + " \"lastModified\"=\"2014-06-02T08:33:31Z\""
      + " \"created\"=\"2014-06-02T08:33:31Z\">";

    String[] payloads = {
      XML_DECL + METADATA_START_TAG_WITH_ATTRIBUTES + XML_CONTENT + METADATA_END_TAG,
      XML_DECL + METADATA_START_TAG + XML.toJSONObject(XML_CONTENT).toString() + METADATA_END_TAG
    };

    for (String payload : payloads) {
      when(streamresult.getWriter().toString()).thenReturn(payload);
      assertEquals(parseXml(XML_CONTENT),
                   parseXml(handler.getElement(streamresult)));
    }
  }

  private Set<String> parseXml(String xml) {
    Set<String> elements = new HashSet<>();

    XMLTokener tokener = new XMLTokener(xml);
    Object token = tokener.nextContent();
    while (token != null) {
      String content = token.toString();
      if ("<".equals(content) || content.startsWith("/")) {
        token = tokener.nextContent();
        continue;
      }
      elements.add(content);
      token = tokener.nextContent();
    }
    return elements;
  }
}
