package org.plos.namedentity.api.entity;

import org.plos.namedentity.validate.Validatable;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class Entity implements Validatable {

  protected Integer id;
  protected Integer nedid;
  protected String  source;
  protected Integer sourcetypeid;

  @Override
  public void validate() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getNedid() {
    return nedid;
  }

  public void setNedid(Integer nedid) {
    this.nedid = nedid;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Integer getSourcetypeid() {
    return sourcetypeid;
  }

  public void setSourcetypeid(Integer sourcetypeid) {
    this.sourcetypeid = sourcetypeid;
  }

}
