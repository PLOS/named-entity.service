package org.plos.namedentity.api.entity;

// Base class for top-level entities which aren't dependent on another entity
// (ie, don't have a parent). Individual and Organization entities are examples.

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class ParentEntity extends Entity {

}
