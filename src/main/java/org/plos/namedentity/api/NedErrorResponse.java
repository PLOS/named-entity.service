/*
 * Copyright (c) 2014-2019 Public Library of Science
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
package org.plos.namedentity.api;

import org.plos.namedentity.api.NedException.ErrorType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Set;

@XmlRootElement
public class NedErrorResponse {

  public NedErrorResponse() { }

  public NedErrorResponse(NedException exception, String failureMsg) {
    if (exception != null) {
      ErrorType errorType = exception.getErrorType();
      if (errorType != null) {
        this.errorCode = errorType.getErrorCode();
        this.errorMsg  = errorType.getErrorMessage();
      }
      this.detailedMsg = exception.getDetailedMessage();
      this.acceptableValues = exception.getAcceptableValues();
    }
    this.failureMsg = failureMsg;
  }

  @XmlElement(name = "problem")
  public String failureMsg;

  public int errorCode;

  public String errorMsg;

  public String detailedMsg;

  public Set<String> acceptableValues;

  public Date timestamp = new Date();
}
