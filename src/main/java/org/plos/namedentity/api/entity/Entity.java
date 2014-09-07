package org.plos.namedentity.api.entity;

import javax.xml.bind.annotation.XmlTransient;

import org.plos.namedentity.validate.Validatable;

import java.io.Serializable;

@XmlTransient
public abstract class Entity implements Validatable, Serializable {

  @Override
  public void validate() {
  }
}
