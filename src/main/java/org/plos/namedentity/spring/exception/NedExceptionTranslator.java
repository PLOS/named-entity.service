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

import static org.plos.namedentity.api.NedException.ErrorType.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.plos.namedentity.api.NedException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

public class NedExceptionTranslator {

  public static final String MYSQL_DUPE_EMAIL_ENTRY = "^.*Duplicate entry '(.*)' for key 'emailAddress'.*$";
  public static final String H2_DUPE_EMAIL_ENTRY    = "^.*Unique index .*violation:.*EMAILS\\(EMAILADDRESS, SOURCETYPEID\\).*$";

  private static Pattern mysqlDupeEmailPattern;
  private static Pattern h2DupeEmailPattern;

  static {
    // DOTALL - flag which indicates that "dot" matches line terminator too 
    mysqlDupeEmailPattern = Pattern.compile(MYSQL_DUPE_EMAIL_ENTRY, Pattern.DOTALL);
    h2DupeEmailPattern    = Pattern.compile(H2_DUPE_EMAIL_ENTRY, Pattern.DOTALL);
  }

  public static NedException translate(DataAccessException dae) {
    if (dae instanceof DuplicateKeyException) {
      Matcher m1 = mysqlDupeEmailPattern.matcher(dae.getMessage());
      Matcher m2 = h2DupeEmailPattern.matcher(dae.getMessage());
      if (m1.matches() || m2.matches())
        return new NedException(DupeEmailError, "Email address is already in use.", dae);
    }

    return new NedException(DatabaseError, dae.getMessage(), dae);
  }
}
