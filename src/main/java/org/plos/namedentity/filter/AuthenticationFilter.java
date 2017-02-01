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

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.plos.namedentity.service.AuthService;

public class AuthenticationFilter implements javax.servlet.Filter {

  @Inject private AuthService authService;

  public static final String AUTHENTICATION_HEADER = "Authorization";

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
    throws IOException, ServletException
  {
    if (request instanceof HttpServletRequest) {
      HttpServletRequest httpServletRequest = (HttpServletRequest) request;
      String consumerCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

      if (authService.authenticate(consumerCredentials)) {
        filter.doFilter(request, response);
      } else {
        if (response instanceof HttpServletResponse) {
          HttpServletResponse httpServletResponse = (HttpServletResponse) response;
          httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
      }
    }
  }

  public AuthService getAuthService() {
    return authService;
  }

  public void setAuthService(AuthService authService) {
    this.authService = authService;
  }

  @Override
  public void destroy() {
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }
}
