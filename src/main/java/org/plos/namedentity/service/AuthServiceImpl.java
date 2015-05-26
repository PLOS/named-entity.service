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

import org.plos.namedentity.api.Consumer;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthServiceImpl implements AuthService {

  // credentials expected to be HTTP basic authentication ( ex: Basic YWRtaW46YWRtaW4= )
  private static Pattern httpBasicAuthRegexp = Pattern.compile("^Basic\\s+([a-zA-Z0-9+/]+={0,2})$");

  private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

  @Inject private NamedEntityDBService namedEntityDBService; 

  @Override
  public boolean authenticate(String credentials) {

    if (credentials == null) return false;

    Matcher matcher = httpBasicAuthRegexp.matcher(credentials);
    if (!matcher.find()) {
      log.warn("Invalid credentials: " + credentials);
      return false;
    }
    // group 0 matches entire string
    final String encodedUsernamePassword = matcher.group(1);

    String usernamePassword = null;
    try {
      byte[] decodedBytes = Base64.getDecoder().decode(encodedUsernamePassword);
      usernamePassword = new String(decodedBytes, "UTF-8");
    } catch (IOException e) {
      log.error("Problem decoding credentials", e);
      return false;
    }

    final StringTokenizer tokenizer = new StringTokenizer(usernamePassword,":");
    int tokenCount = tokenizer.countTokens();
    if (tokenCount != 2) {
      log.warn(String.format(
        "Unexpected count when tokenizing credentials. Expected:2 Found:%d", tokenCount));
      return false;
    }

    final String username = tokenizer.nextToken();
    final String password = tokenizer.nextToken();

    Consumer filter = new Consumer();
    filter.setName(username);
    List<Consumer> consumers = namedEntityDBService.findByAttribute(filter);
    if (consumers.size() == 0) {
      return false; // user not found
    }
    return (BCrypt.checkpw(password, consumers.get(0).getPassword()));
  }

  public NamedEntityDBService getNamedEntityDBService() {
    return namedEntityDBService;
  }

  public void setNamedEntityDBService(NamedEntityDBService namedEntityDBService) {
    this.namedEntityDBService = namedEntityDBService;
  }
}
