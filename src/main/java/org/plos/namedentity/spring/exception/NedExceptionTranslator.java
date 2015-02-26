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
package org.plos.namedentity.spring.exception;

import org.plos.namedentity.api.NedException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.regex.Pattern;

import static org.plos.namedentity.api.NedException.ErrorType.DatabaseError;
import static org.plos.namedentity.api.NedException.ErrorType.DupeDisplaynameError;
import static org.plos.namedentity.api.NedException.ErrorType.DupeEmailError;
import static org.plos.namedentity.api.NedException.ErrorType.DupeLegalnameError;
import static org.plos.namedentity.api.NedException.ErrorType.DupeUidError;

public class NedExceptionTranslator {

  private static Pattern mysqlDupeEmailPattern;
  private static Pattern h2DupeEmailPattern;

  private static Pattern mysqlDupeDisplaynamePattern;
  private static Pattern h2DupeDisplaynamePattern;

  private static Pattern mysqlDupeLegalnamePattern;
  private static Pattern h2DupeLegalnamePattern;

  private static Pattern mysqlDupeUidPattern;
  private static Pattern h2DupeUidPattern;

  static {
    // DOTALL - flag which indicates that "dot" matches line terminator too 
    mysqlDupeEmailPattern = Pattern.compile("^.*Duplicate entry '(.*)' for key 'emailAddress'.*$", Pattern.DOTALL);
    h2DupeEmailPattern = Pattern.compile("^.*Unique index .*violation:.*EMAILS\\(EMAILADDRESS, SOURCETYPEID\\).*$", Pattern.DOTALL);

    mysqlDupeDisplaynamePattern = Pattern.compile("^.*Duplicate entry '(.*)' for key 'displayName'.*$", Pattern.DOTALL);
    h2DupeDisplaynamePattern = Pattern.compile("^.*Unique index .*violation:.*INDIVIDUALPROFILES\\(DISPLAYNAME, SOURCETYPEID\\).*$", Pattern.DOTALL);

    mysqlDupeLegalnamePattern = Pattern.compile("^.*Duplicate entry '(.*)' for key 'legalName'.*$", Pattern.DOTALL);
    h2DupeLegalnamePattern = Pattern.compile("^.*Unique index .*violation:.*ORGANIZATIONS\\(LEGALNAME, SOURCETYPEID\\).*$", Pattern.DOTALL);

    mysqlDupeUidPattern = Pattern.compile("^.*Duplicate entry '(.*)' for key 'uniqueIdentifier'.*$", Pattern.DOTALL);
    h2DupeUidPattern = Pattern.compile("^.*Unique index .*violation:.*UNIQUEIDENTIFIERS\\(UNIQUEIDENTIFIER, SOURCETYPEID, TYPEID\\).*$", Pattern.DOTALL);
  }

  public static NedException translate(DataAccessException dae) {
    if (dae instanceof DataIntegrityViolationException) {
      if (mysqlDupeEmailPattern.matcher(dae.getMessage()).matches()
          || h2DupeEmailPattern.matcher(dae.getMessage()).matches())
        return new NedException(DupeEmailError, "The specified email address is already in use", dae);

      if (mysqlDupeDisplaynamePattern.matcher(dae.getMessage()).matches()
          || h2DupeDisplaynamePattern.matcher(dae.getMessage()).matches())
        return new NedException(DupeDisplaynameError, "The specified display name is already in use", dae);

      if (mysqlDupeLegalnamePattern.matcher(dae.getMessage()).matches()
          || h2DupeLegalnamePattern.matcher(dae.getMessage()).matches())
        return new NedException(DupeLegalnameError, "The specified legal name is already in use", dae);

      if (mysqlDupeUidPattern.matcher(dae.getMessage()).matches()
          || h2DupeUidPattern.matcher(dae.getMessage()).matches())
        return new NedException(DupeUidError, "The specified unique identifier is already in use", dae);
    }

    return new NedException(DatabaseError, dae.getMessage(), dae);
  }
}