package org.plos.namedentity.api.entity;

import javax.xml.bind.annotation.XmlTransient;

// Base class for entities which have a parent. PhoneNumber and Email entities
// are examples.

public abstract class ChildEntity extends Entity {

  @XmlTransient
  public Integer getPrimaryid() {
    // TODO - won't need to do instanceof after all entities has same primary
    // key name. until then throw an exception if we haven't handled it.
    if (this instanceof Email) {
      return ((Email)this).getEmailid();
    }
    else if (this instanceof Address) {
      return ((Address)this).getAddressid();
    }
    throw new UnsupportedOperationException(
      "Unable to lookup primary key for " + this.getClass().getSimpleName());
  }

  public abstract void setNamedentityid(Integer namedentityid);
}
