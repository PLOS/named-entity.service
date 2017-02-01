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
  private static Pattern mysqlDupeDisplaynamePattern;
  private static Pattern mysqlDupeLegalnamePattern;
  private static Pattern mysqlDupeUidPattern;

  static {
    // DOTALL - flag which indicates that "dot" matches line terminator too 
    mysqlDupeEmailPattern       = Pattern.compile("^.*Duplicate entry '(.*)' for key 'emailAddress'.*$", Pattern.DOTALL);
    mysqlDupeDisplaynamePattern = Pattern.compile("^.*Duplicate entry '(.*)' for key 'displayName'.*$", Pattern.DOTALL);
    mysqlDupeLegalnamePattern   = Pattern.compile("^.*Duplicate entry '(.*)' for key 'legalName'.*$", Pattern.DOTALL);
    mysqlDupeUidPattern         = Pattern.compile("^.*Duplicate entry '(.*)' for key 'uniqueIdentifier'.*$", Pattern.DOTALL);
  }

  public static NedException translate(DataAccessException dae) {
    if (dae instanceof DataIntegrityViolationException) {
      if (mysqlDupeEmailPattern.matcher(dae.getMessage()).matches())
        return new NedException(DupeEmailError, "The specified email address is already in use", dae);

      if (mysqlDupeDisplaynamePattern.matcher(dae.getMessage()).matches())
        return new NedException(DupeDisplaynameError, "The specified display name is already in use", dae);

      if (mysqlDupeLegalnamePattern.matcher(dae.getMessage()).matches())
        return new NedException(DupeLegalnameError, "The specified legal name is already in use", dae);

      if (mysqlDupeUidPattern.matcher(dae.getMessage()).matches())
        return new NedException(DupeUidError, "The specified unique identifier is already in use", dae);
    }

    return new NedException(DatabaseError, dae.getMessage(), dae);
  }
}
