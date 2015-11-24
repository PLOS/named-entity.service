/*
 * Copyright (c) 2006-2015 by Public Library of Science
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
package org.plos.namedentity.validate;

import java.io.StringReader;

import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonStructure;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.stream.JsonParsingException;

public class JsonValidator {

  public static boolean isJSONValid(String json) {
    if ( !isEmptyOrBlank(json)) {
      try {
        JsonReader    reader  = Json.createReader(new StringReader(json));
        JsonStructure jstruct = reader.read();
        reader.close();
        return true;
      } catch (JsonParsingException e) {
        // fall through
      }
    }
    return false;
  }

  public static Map<String,String> parseJsonObjectAsMap(String json) {
    if (isEmptyOrBlank(json)) return null;

    JsonReader reader   = Json.createReader(new StringReader(json));
    JsonObject mdObject = reader.readObject();
    reader.close();

    Map<String,String> map = new HashMap<>();
    for (Map.Entry<String,JsonValue> entry : mdObject.entrySet()) {
      map.put(entry.getKey(), entry.getValue().toString());
    }
    return map;
  }

  private static boolean isEmptyOrBlank(String s) {
    return s == null || s.trim().isEmpty();
  }
}
