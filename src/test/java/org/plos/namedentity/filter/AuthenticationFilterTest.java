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
package org.plos.namedentity.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.plos.namedentity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
      .thenReturn("Basic "+encode("test:test"));

    authFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
    verify(filterChain).doFilter(any(HttpServletRequest.class),any(HttpServletResponse.class));
  }

  private String encode(String plaintext) {
    return Base64.getEncoder().encodeToString(plaintext.getBytes(StandardCharsets.UTF_8));
  }
}
