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
      String credentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

      if (authService.authenticate(credentials)) {
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
