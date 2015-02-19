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
package org.plos.namedentity.api;

import java.util.Set;

public class NedValidationException extends NedException {

  private Set<String> acceptableValues;

  public NedValidationException(String message) {
    super(message);
  }

  public NedValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public NedValidationException(ErrorType errorType) {
    super(errorType);
  }

  public NedValidationException(ErrorType errorType, String message) {
    super(errorType, message);
  }

  public NedValidationException(ErrorType errorType, Set<String> acceptableValues) {
    super(errorType);
    this.acceptableValues = acceptableValues;
  }

  public Set<String> getAcceptableValues() {
    return acceptableValues;
  }

  @Override 
  public String getMessage() {
    StringBuilder b = new StringBuilder();
    b.append(super.getMessage());

    if (acceptableValues != null && acceptableValues.size() > 0) {
      b.append(" Acceptable Values:");
      for (String value : acceptableValues) {
        b.append(value).append(",");
      }
      // remove trailing comma
      b.setLength(b.length() - 1);
    }
    return b.toString();
  }
}
