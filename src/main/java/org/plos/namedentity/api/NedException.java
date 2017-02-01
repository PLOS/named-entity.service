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
package org.plos.namedentity.api;

import java.util.EnumSet;
import java.util.Set;

public class NedException extends RuntimeException {

  public enum ErrorType {

    // Server Errors

    ServerError                      (900, "Server Error"),
    DatabaseError                    (910, "Database Error"),

    // Entity/Attribute-Level Validation Errors

    EntityNotFound                   (1000, "Entity Not Found"),
    InvalidTypeClass                 (1100, "Invalid Type Class"),
    InvalidTypeValue                 (1110, "Invalid Type Value"),
    EntityWithNoPK                   (1130, "Entity with No Primary Key"),
    EntityNotDefined                 (1140, "Entity Not Defined"),
    UidValueError                    (1150, "Unique Identifier Value Error"),
    InvalidSalesforceId              (1160, "Invalid Salesforce ID"),
    InvalidOrcidId                   (1170, "Invalid ORCID ID"),
    InvalidUrl                       (1180, "Invalid URL"),
    CasIdError                       (1190, "CAS ID Error"),
    InvalidCasId                     (1195, "Invalid CAS ID"),
    FamiliarNameError                (1200, "Familiar Name Error"),
    LegalNameError                   (1210, "Legal Name Error"),
    FirstnameError                   (1220, "Firstname Error"),
    LastnameError                    (1230, "Lastname Error"),
    DisplayNameError                 (1240, "Display Name Error"),
    InvalidEmail                     (1250, "Invalid Email"),
    PhoneNumberError                 (1260, "Phone Number Error"),
    DupeEmailError                   (1270, "Duplicate Email Error"),
    DupeDisplaynameError             (1280, "Duplicate Display Name"),
    DupeLegalnameError               (1290, "Duplicate Legal Name"),
    DupeUidError                     (1300, "Duplicate Unique Identifier"),
    PasswordError                    (1310, "Password Error"),
    DigestPasswordError              (1320, "Password Storage Error"),
    TamperedPasswordError            (1330, "Tampered Password Error"),
    PasswordFormatError              (1340, "Password Format Error"),
    InvalidJsonError                 (1350, "Invalid JSON"),
    InvalidDegreeError               (1360, "Invalid Degree"),
    InvalidRelationshipError         (1370, "Invalid Relationship Error"),

    // Composite-Level Validation Errors

    NoProfileEntities(1500, "No Profile Entities for Individual Composite"),
    NoEmailEntities(1510, "No Email Entities for Individual Composite"),
    NoAuthEntity(1520, "No Auth Entity for Individual Composite"),

    // Client-side Errors

    InvalidIndividualSearchQuery(1800, "Search query requires an entity, an attribute, and a value"),
    InvalidSearchCriteria(1810, "Invalid search criteria"),
    TooManyResultsFound(1820, "Too Many Results Found"),
    InvalidInstitutionQuery(1830, "Institution query requires a substring parameter"),
    InvalidOrganizationSearchQuery(1850, "Search query requires an attribute, and a value"),
    InstitutionNotFound(1860, "Institution not found with that ID"),
    PasswordNotSpecified(1870, "Password not specified"),

    InvalidErrorType(-1, "");

    private final int    errorCode;
    private final String errorMessage;

    private ErrorType(int errorCode, String errorMessage) {
      this.errorCode = errorCode;
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
    this(errorType, null, (Throwable) null);
  }

  public NedException(ErrorType errorType, Set<String> acceptableValues) {
    this(errorType, null, acceptableValues);
  }

  public NedException(ErrorType errorType, String message, Set<String> acceptableValues) {
    this(errorType, message, (Throwable) null);
    this.acceptableValues = acceptableValues;
  }

  public NedException(ErrorType errorType, String message) {
    this(errorType, message, (Throwable) null);
  }

  public NedException(ErrorType errorType, Throwable cause) {
    this(errorType, null, cause);
  }

  public NedException(String message) {
    this(ErrorType.ServerError, message, (Throwable) null);
  }

  public NedException(ErrorType errorType, String message, Throwable cause) {
    super(cause);
    this.errorType = errorType;
    this.detailedMessage = message;
  }

  @Override
  public String getMessage() {
    StringBuilder b = new StringBuilder();

    if (errorType != null) {
      b.append(errorType.toString());
    }

    if (b.length() > 0) b.append(" ");

    if (detailedMessage != null) {
      b.append(detailedMessage);
    }

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
