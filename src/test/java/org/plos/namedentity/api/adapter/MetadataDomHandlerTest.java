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

import org.junit.Test;
import org.mockito.Mockito;

import javax.xml.transform.stream.*;

public class MetadataDomHandlerTest {

  private static final String XML_DECL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

  private MetadataDomHandler handler = new MetadataDomHandler();

  @Test
  public void testTagName() {
    assertEquals("metadata", handler.getTagName());
    assertEquals("<metadata>", handler.getStartTag());
    assertEquals("</metadata>", handler.getEndTag());
  }

  @Test
  public void testUnmarshallingNoContent() {
    StreamResult streamresult = Mockito.mock(StreamResult.class, RETURNS_DEEP_STUBS);
    when(streamresult.getWriter().toString()).thenReturn(XML_DECL+"<metadata/>");

    assertNull( handler.getElement(streamresult) );
  }

  @Test
  public void testUnmarshallingContent() {
    StreamResult streamresult = Mockito.mock(StreamResult.class, RETURNS_DEEP_STUBS);
    when(streamresult.getWriter().toString()).thenReturn(
      XML_DECL+"<metadata x=\"1\" y=\"2\"><x>1</x><y>2</y></metadata>");

    assertEquals("<x>1</x><y>2</y>", handler.getElement(streamresult));
  }
}
