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

package org.plos.namedentity.api.dto;

public class EmailDTO {

  private Integer emailid;
  private Integer namedentityid;
  private String  emailtype;
  private String  emailaddress;
  private Boolean isprimary;

  public EmailDTO() {
    }

  public EmailDTO(
    Integer emailid,
    Integer namedentityid,
    String  emailtype,
    String  emailaddress,
    Boolean isprimary
  ) {
    this.emailid       = emailid;
    this.namedentityid = namedentityid;
    this.emailtype     = emailtype;
    this.emailaddress  = emailaddress;
    this.isprimary     = isprimary;
  }
  
  public Integer getEmailid() {
    return emailid;
  }
  public void setEmailid(Integer emailid) {
    this.emailid = emailid;
  }
  
  public Integer getNamedentityid() {
    return namedentityid;
  }
  public void setNamedentityid(Integer namedentityid) {
    this.namedentityid = namedentityid;
  }
  
  public String getEmailtype() {
    return emailtype;
  }
  public void setEmailtype(String emailtype) {
    this.emailtype = emailtype;
  }
  
  public String getEmailaddress() {
    return emailaddress;
  }
  public void setEmailaddress(String emailaddress) {
    this.emailaddress = emailaddress;
  }
  
  public Boolean getIsprimary() {
    return isprimary;
  }
  public void setIsprimary(Boolean isprimary) {
    this.isprimary = isprimary;
  }
}
