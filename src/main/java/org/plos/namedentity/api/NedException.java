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

public class NedException extends RuntimeException {

  public enum ErrorType {

    InvalidTypeClass(4000, "Invalid Type Class"),
    InvalidTypeValue(4001, "Invalid Type Value"),
    EntityNotFound  (4004, "Entity Not Found"),

    ServerError     (5000, "Server Error"),

    InvalidErrorType(-1,"");

    private final int    errorCode;
    private final String errorMessage;

    private ErrorType(int errorCode, String errorMessage) {
      this.errorCode    = errorCode;
      this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
      return errorMessage;
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

    public String toString() {
      StringBuilder b = new StringBuilder();
      b.append("ErrorCode:").append(errorCode).append(" - ");
      b.append(errorMessage).append(".");
      return b.toString();
    }
  }

  private ErrorType errorType;    // stores type of error
  private String    message;      // stores specific error message

  public ErrorType getErrorType() {
    return errorType;
  }

  public NedException(ErrorType errorType) {
    this.errorType = errorType;
  }

  public NedException(ErrorType errorType, String message) {
    this(errorType);
    this.message = message;
  }

  public NedException(String message) {
    this(ErrorType.ServerError, message);
  }

  public NedException(String message, Throwable cause) {
    this(ErrorType.ServerError, concat(message,cause));
  }

  @Override
  public String getMessage() {
    StringBuilder b = new StringBuilder();
    if (this.errorType != null) {
      b.append(errorType.toString());
    }
    if (this.message != null) {
      if (errorType != null) b.append(" ");
      b.append(this.message);
    }
    return b.toString();
  }

  private static String concat(String m, Throwable t) {
    StringBuilder b = new StringBuilder();
    b.append(m);
    if (t != null) {
      b.append(". ").append(t.toString()).append(".");
    }
    return b.toString();
  }
}
