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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.time.LocalDate;

public class DateAdapterTest {

  private DateAdapter dateAdapter = new DateAdapter();

  @Test
  public void testMarshallingDate() throws Exception {
    assertEquals("2014-10-25", dateAdapter.marshal(LocalDate.of(2014,10,25)));
  }

  @Test
  public void testUnmarshallingDate() throws Exception {
		assertEquals(LocalDate.of(2014,10,25), dateAdapter.unmarshal("2014-10-25"));
  }
}
