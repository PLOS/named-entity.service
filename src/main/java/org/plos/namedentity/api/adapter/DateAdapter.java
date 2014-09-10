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
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.bind.annotation.adapters.XmlAdapter;
 
public class DateAdapter extends XmlAdapter<String, Date> {

  private SimpleDateFormat dateFormat;

  public DateAdapter() {
    this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    this.dateFormat.setTimeZone( TimeZone.getTimeZone("UTC") );
  }
       
  @Override
  public String marshal(Date date) throws Exception {
    return dateFormat.format(date);
  }

  @Override
  public Date unmarshal(String string) throws Exception {
    return dateFormat.parse(string);
  }
}
