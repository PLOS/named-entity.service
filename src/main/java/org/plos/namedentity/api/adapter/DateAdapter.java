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

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateAdapter extends XmlAdapter<String, Date> {

  private DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");

  @Override
  public String marshal(Date date) throws Exception {
    if (date == null) return null;

    LocalDate localDate = new LocalDate(date);
    return dtf.print(localDate);
  }

  @Override
  public Date unmarshal(String datestr) throws Exception {
    if (datestr == null) return null;

    LocalDate dt = dtf.parseLocalDate(datestr);
    return dt.toDate();
  }
}
