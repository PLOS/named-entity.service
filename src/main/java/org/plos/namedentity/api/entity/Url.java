package org.plos.namedentity.api.entity;

import org.apache.commons.validator.routines.UrlValidator;
import org.plos.namedentity.api.NedValidationException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Url extends Entity {

  private Integer id;
  private Integer namedentityid;
  private String  url;

  private static UrlValidator urlValidator = UrlValidator.getInstance();

  @Override
  public void validate() {
    if (url != null && !urlValidator.isValid(url))
      throw new NedValidationException("URL not valid ("+url+")");
  }

  public Integer getId() {
    return id;
  }

  public Integer getNamedentityid() {
    return namedentityid;
  }

  public void setNamedentityid(Integer namedentityid) {
    this.namedentityid = namedentityid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
