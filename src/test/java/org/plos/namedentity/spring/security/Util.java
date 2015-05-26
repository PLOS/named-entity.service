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
package org.plos.namedentity.spring.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Util {

  public static String hashPassword(String plaintext, int complexity) {
    return BCrypt.hashpw(plaintext, BCrypt.gensalt(complexity));
  }

  public static void main (String [] args)
  {
    // computational complexity used when generating salt (range:4-31)
    int complexity = (args.length == 2) ? Integer.parseInt(args[1]) : 4;
    System.out.println("Hashed Password = " + hashPassword(args[0], complexity));
  }
}
