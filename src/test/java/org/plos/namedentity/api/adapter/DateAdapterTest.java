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

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateAdapterTest {

  private DateAdapter dateAdapter = new DateAdapter();

  @Test
  public void testMarshallingDate() throws Exception {
    // simulate retrieving date datatype from database which has no time
    // component. this is represented as a midnight GMT epoch time.

    Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    //(year, month(0=jan), date, hour24, minute)
    cal.set(2014, 9, 25, 0, 0, 0);

    java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());

    //assertEquals("2014-10-25", dateAdapter.marshal(date));
  }

  @Test
  public void testUnmarshallingDate() throws Exception {

    Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    //(year, month(0=jan), date, hour24, minute)
    cal.set(2014, 9, 25, 0, 0, 0);
    cal.set(Calendar.MILLISECOND, 0);

//    assertEquals(new java.util.Date(cal.getTimeInMillis()),
//                 dateAdapter.unmarshal("2014-10-25"));
  }
}
