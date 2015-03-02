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
package org.plos.namedentity.api;

import org.plos.namedentity.api.NedException.ErrorType;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

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

  @XmlElement(name="problem")
  public String failureMsg;

  public int errorCode;

  public String errorMsg;

  public String detailedMsg;

  public Set<String> acceptableValues;
}
