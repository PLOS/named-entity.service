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
import java.util.Iterator;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonStructure;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.stream.JsonParsingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonValidator {

  public static boolean isValid(String json) {
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
    Map<String,String> map = new HashMap<>();

    if (isEmptyOrBlank(json)) return map;

    try {
      JSONObject jobject = new JSONObject(json);
      for (Iterator<String> iter = jobject.keys(); iter.hasNext(); ) {
        String key = iter.next();
        map.put(key, jobject.get(key).toString());
      }
    } catch (JSONException je) {
      // fall through
    }
    return map;
  }

  /**
   * Validates and coerces json-like payload into valid JSON.
   * @param json - payload to validate and transform
   * @return valid json if successful, otherwise null.
   */
  public static String validJson(String json) {
    if (isEmptyOrBlank(json)) return null;

    try {
      return new JSONObject(json).toString();
    } catch (JSONException je1) {
      try {
        return new JSONArray(json).toString();
      } catch (JSONException je2) {
        // fall through
      }
    }
    return null;
  }

  private static boolean isEmptyOrBlank(String s) {
    return s == null || s.trim().isEmpty();
  }
}
