/*
 * Copyright (c) 2017 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
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
