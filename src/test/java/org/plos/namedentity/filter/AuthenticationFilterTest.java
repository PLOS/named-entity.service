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
package org.plos.namedentity.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-beans.xml","/spring-beans.test.xml"})
public class AuthenticationFilterTest {

  @Autowired
  AuthService authService;

  HttpServletRequest   httpServletRequest;
  HttpServletResponse  httpServletResponse;
  FilterChain          filterChain;
  AuthenticationFilter authFilter = new AuthenticationFilter();

  @Before
  public void setup() {
    httpServletRequest  = mock(HttpServletRequest.class);
    httpServletResponse = mock(HttpServletResponse.class);
    filterChain         = mock(FilterChain.class);
    authFilter = new AuthenticationFilter();
    authFilter.setAuthService(authService);
  }

  @Test
  public void testFilterWithNoAuthHeader() throws IOException, ServletException {
    authFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
    verify(httpServletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
  }

  @Test
  public void testFilterInvalidCredentials() throws IOException, ServletException {
    when(httpServletRequest.getHeader(AuthenticationFilter.AUTHENTICATION_HEADER))
      .thenReturn("Basic "+encode("tahi:blah"));

    authFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
    verify(httpServletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
  }

  @Test
  public void testFilterValidCredentials() throws IOException, ServletException {
    when(httpServletRequest.getHeader(AuthenticationFilter.AUTHENTICATION_HEADER))
      .thenReturn("Basic "+encode("tahi:tahi"));

    authFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
    verify(filterChain).doFilter(any(HttpServletRequest.class),any(HttpServletResponse.class));
  }

  private String encode(String plaintext) {
    return Base64.getEncoder().encodeToString(plaintext.getBytes(StandardCharsets.UTF_8));
  }
}
