package org.plos.namedentity.api.entity;

import org.apache.commons.validator.routines.UrlValidator;
import org.plos.namedentity.api.NedValidationException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Url extends Entity {

  private Integer id;
  private Integer namedEntityId;
  private String  url;

  private static UrlValidator urlValidator = UrlValidator.getInstance();

  @Override
  public void validate() {
    if (url != null && !urlValidator.isValid(url))
      throw new NedValidationException("URL not valid");
  }

  public Integer getId() {
    return id;
  }

  public Integer getNamedEntityId() {
    return namedEntityId;
  }

  public void setNamedEntityId(Integer namedEntityId) {
    this.namedEntityId = namedEntityId;
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
