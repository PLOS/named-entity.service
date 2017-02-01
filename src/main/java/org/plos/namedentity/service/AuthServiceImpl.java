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
package org.plos.namedentity.service;

import org.plos.namedentity.api.Consumer;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthServiceImpl implements AuthService {

  // credentials expected to be HTTP basic authentication ( ex: Basic YWRtaW46YWRtaW4= )
  private static Pattern httpBasicAuthRegexp = Pattern.compile("^Basic\\s+([a-zA-Z0-9+/]+={0,2})$");

  private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

  @Inject private NamedEntityDBService namedEntityDBService; 

  @Override
  public boolean authenticate(String encodedCredentials) {

    if (encodedCredentials == null) return false;

    Map<String,String> credentials = parseCredentials(encodedCredentials);
    if (credentials == null) return false;

    final String appname  = credentials.get("appname");
    final String password = credentials.get("password");

    Consumer filter = new Consumer();
    filter.setName(appname);
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

  @Override
  public Map parseCredentials(String encodedCredentials) {

    Matcher matcher = httpBasicAuthRegexp.matcher(encodedCredentials);
    if (!matcher.find()) {
      log.warn("Invalid credentials: {}", encodedCredentials);
      return null;
    }
    // group 0 matches entire string
    final String encodedUsernamePassword = matcher.group(1);

    String usernamePassword = null;
    try {
      byte[] decodedBytes = Base64.getDecoder().decode(encodedUsernamePassword);
      usernamePassword = new String(decodedBytes, "UTF-8");
    } catch (IOException e) {
      log.error("Problem decoding credentials", e);
      return null;
    }

    final StringTokenizer tokenizer = new StringTokenizer(usernamePassword,":");
    int tokenCount = tokenizer.countTokens();
    if (tokenCount != 2) {
      log.warn(String.format(
        "Unexpected count when tokenizing credentials. Expected:2 Found:%d", tokenCount));
      return null;
    }

    Map<String,String> credentials = new HashMap<String,String>();
    credentials.put("appname", tokenizer.nextToken());
    credentials.put("password", tokenizer.nextToken());
    return credentials;
  }
}
