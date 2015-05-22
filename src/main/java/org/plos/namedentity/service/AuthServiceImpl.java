/*
 * Copyright (c) 2006-2015 by Public Library of Science
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
package org.plos.namedentity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.plos.namedentity.api.NedException;
import org.plos.namedentity.persist.NamedEntityDBService;

import javax.inject.Inject;
import java.util.List;
import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

public class AuthServiceImpl implements AuthService {

  private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

  @Inject private NamedEntityDBService namedEntityDBService; 

  @Override
  public boolean authenticate(String credentials) {

    if (credentials == null) return false;

    // credentials expected to be basic authentication format.
    // example :  Basic YWRtaW46YWRtaW4=
    final String encodedUserPassword = credentials.replaceFirst("Basic ","");
    String usernameAndPassword = null;
    try {
      byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
      usernameAndPassword = new String(decodedBytes, "UTF-8");
    } catch (IOException e) {
      log.error("Problem decoding credentials", e);
      return false;
    }

    final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword,":");
    final String username = tokenizer.nextToken();
    final String password = tokenizer.nextToken();

    //boolean authenticationStatus = "admin".equals(username)
        //&& "admin".equals(password);
    //return authenticationStatus;
    return true;
  }

  public NamedEntityDBService getNamedEntityDBService() {
    return namedEntityDBService;
  }

  public void setNamedEntityDBService(NamedEntityDBService namedEntityDBService) {
    this.namedEntityDBService = namedEntityDBService;
  }
}
