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

import java.util.EnumSet;
import java.util.Set;

public class NedException extends RuntimeException {

  public enum ErrorType {

    InvalidTypeClass(4000, "Invalid Type Class"),
    InvalidTypeValue(4001, "Invalid Type Value"),

    ServerError(5000, "Server error"),

    InvalidErrorType(-1,"");

    private final int errorCode;
    private final String message;

    private ErrorType(int errorCode, String message) {
      this.errorCode = errorCode;
      this.message   = message;
    }

    public String getMessage() {
      return message;
    }

    public int getErrorCode() {
      return errorCode;
    }

    public static ErrorType getErrorType(int errorCode) {
      for (ErrorType errortype : EnumSet.allOf(ErrorType.class))
        if (errortype.errorCode == errorCode)
          return errortype;
      return InvalidErrorType;
    }

  }

  private ErrorType errorType;

  private Set<String> acceptableValues;

  public ErrorType getErrorType() {
    return errorType;
  }

  public Set<String> getAcceptableValues() {
    return acceptableValues;
  }

  public NedException(ErrorType errorType) {
    this(errorType, null);
  }

  public NedException(ErrorType errorType, Set<String> acceptableValues) {
    super(errorType.getMessage());
    this.errorType = errorType;
    this.acceptableValues = acceptableValues;
  }

  public NedException(String message) {
    super(message);
    errorType = ErrorType.ServerError;
  }

  public NedException(String message, Throwable cause) {
    super(message, cause);
    errorType = ErrorType.ServerError;
  }
}
