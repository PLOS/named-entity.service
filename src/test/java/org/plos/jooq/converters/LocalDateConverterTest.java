/*
 * Copyright (c) 2006-2016 by Public Library of Science
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
package org.plos.jooq.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class LocalDateConverterTest {

  private LocalDateConverter converter = new LocalDateConverter();

  @Test
  public void testSqlDateToLocalDateConversion() {
    LocalDate localdate = LocalDate.of(2016, 02, 15);
    Date sqlDate = Date.valueOf( localdate );
    assertEquals(localdate, converter.from(sqlDate));
  }

  @Test
  public void testLocalDateToSqlDateConversion() {
    LocalDate localdate = LocalDate.of(2016, 02, 15);
    Date sqlDate = Date.valueOf( localdate );
    assertEquals(sqlDate, converter.to(localdate));
  }
}
