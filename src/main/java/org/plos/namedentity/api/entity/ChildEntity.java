package org.plos.namedentity.api.entity;

import javax.xml.bind.annotation.XmlTransient;

// Base class for entities which have a parent. PhoneNumber and Email entities
// are examples.

@XmlTransient
public abstract class ChildEntity extends Entity {

}
