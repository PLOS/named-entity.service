package org.plos.namedentity.api.entity;

import org.plos.namedentity.validate.Validatable;

import java.io.Serializable;

public abstract class Entity implements Validatable, Serializable {

  @Override
  public void validate() {
  }
}
