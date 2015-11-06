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

import java.io.StringReader;
import org.plos.namedentity.api.entity.Url;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

import javax.xml.bind.annotation.adapters.XmlAdapter;

//public class JsonAdapter extends XmlAdapter<String,String> {
public class JsonAdapter extends XmlAdapter<List<Url>,String> {

  //private DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");

  @Override
  //public String marshal(String jsonstr) throws Exception {
  public List<Url> marshal(String jsonstr) throws Exception {
    if (jsonstr == null) return null;

    //JsonReader reader = Json.createReader(new StringReader(jsonstr));
    //JsonStructure jstruct = reader.read();
    //JsonArray array = reader.readArray();
    //reader.close();
    List<Url> l = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      l.add(new Url());
    }
    return l;

    //return array;
  }

  @Override
  public String unmarshal(List<Url> list) throws Exception {
    throw new UnsupportedOperationException("YO");
    //if (datestr == null) return null;

    //LocalDate dt = dtf.parseLocalDate(datestr);
    //return dt.toDate();
  }
}
