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

    InvalidTypeClass       (4000, "Invalid Type Class"),
    InvalidTypeValue       (4010, "Invalid Type Value"),
    ValidationError        (4020, "Validation Error"),
    EntityNotFound         (4030, "Entity Not Found"),
    InvalidComposite       (4040, "Invalid Composite"),
    EntityWithNoPK         (4050, "Entity with No Primary Key"),
    EntityNotDefined       (4060, "Entity Not Defined"),
    UidValueError          (4070, "Unique Identifier Value Error"),
    InvalidSalesforceId    (4080, "Invalid Salesforce ID"),
    InvalidOrcidId         (4090, "Invalid ORCID ID"),
    InvalidUrl             (4100, "Invalid URL"),
    InvalidCasId           (4110, "Invalid CAS ID"),

    FamiliarNameError      (4200, "Familiar Name Error"),
    LegalNameError         (4210, "Legal Name Error"),

    FirstnameError         (4220, "Firstname Error"),
    LastnameError          (4230, "Lastname Error"),
    DisplayNameError       (4240, "Display Name Error"),
    InvalidEmail           (4250, "Invalid Email"),
    PhoneNumberError       (4260, "Phone Number Error"),
    RequiredAttributeError (4270, "Required Attribute Error"),

    // individual entity-levl validation
    I_NoProfileEntities    (4300, "No Profile Entities for Individual Composite"),
    I_NoEmailEntities      (4310, "No Email Entities for Individual Composite"),

    ServerError            (5000, "Server Error"),
    DatabaseError          (5010, "Database Error"),

    InvalidErrorType       (-1,"");

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

  private ErrorType   errorType;          // stores type of error
  private String      detailedMessage;    // stores specific error message
  private Set<String> acceptableValues;

  public ErrorType getErrorType() {
    return errorType;
  }

  public String getDetailedMessage() {
    return detailedMessage;
  }

  public Set<String> getAcceptableValues() {
    return acceptableValues;
  }

  public NedException(ErrorType errorType) {
    this(errorType, null, (Throwable)null);
  }

  public NedException(ErrorType errorType, Set<String> acceptableValues) {
    this(errorType, null, acceptableValues);
  }

  public NedException(ErrorType errorType, String message, Set<String> acceptableValues) {
    this(errorType, message, (Throwable)null);
    this.acceptableValues = acceptableValues;
  }

  public NedException(ErrorType errorType, String message) {
    this(errorType, message, (Throwable)null);
  }

  public NedException(ErrorType errorType, Throwable cause) {
    this(errorType, null, cause);
  }

  public NedException(String message) {
    this(ErrorType.ServerError, message, (Throwable)null);
  }

  public NedException(ErrorType errorType, String message, Throwable cause) {
    super(cause);
    this.errorType = errorType;
    this.detailedMessage = message;
  }

  @Override
  public String getMessage() {
    StringBuilder b = new StringBuilder();

    if (errorType != null) { b.append(errorType.toString()); }

    if (b.length() > 0) b.append(" ");

    if (detailedMessage != null) { b.append(detailedMessage); }

    if (b.length() > 0) b.append(" ");

    if (acceptableValues != null && acceptableValues.size() > 0) {
      b.append(" Acceptable Values:");
      for (String value : acceptableValues) {
        b.append(value).append(",");
      }
      b.setLength(b.length() - 1);    // remove trailing comma
    }

    return b.toString();
  }
}
