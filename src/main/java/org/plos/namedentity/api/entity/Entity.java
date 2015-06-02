package org.plos.namedentity.api.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import org.plos.namedentity.validate.Validatable;

import javax.xml.bind.annotation.XmlTransient;

import java.sql.Timestamp;

@XmlTransient
public abstract class Entity implements Validatable {

  protected Integer   id;
  protected Integer   nedid;
  protected String    source;
  protected Integer   sourcetypeid;
  protected Timestamp created;
  protected Timestamp lastmodified;

  protected Integer   createdby;
  protected String    createdbyname;

  protected Integer   lastmodifiedby;
  protected String    lastmodifiedbyname;

  // exclude creation/update timestamps and global type foreign keys
  // from comparison (equals) and identity (hashcode). by convention,
  // all global type fk's are suffixed with typeid. hmmm... would be
  // nice to exclude these via reflection or something.
  //
  // NOTE - this list is manually maintained, so if schema changes and you
  //        want new fields excluded from equals/hashcode operations,
  //        you will need to manually add attribute names to this list.

  protected static String EXCLUDED_FIELDS[] = {
    "applicationtypeid",
    "countrycodetypeid",
    "created",
    "createdby",
    "createdbyname",
    "id",
    "lastmodified",
    "lastmodifiedby",
    "lastmodifiedbyname",
    "nameprefixtypeid",
    "namesuffixtypeid",
    "nedid",
    "sourcetypeid",
    "statecodetypeid",
    "typeid"
  };

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o, EXCLUDED_FIELDS);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, EXCLUDED_FIELDS);
  }

  @Override
  public void validate() {
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
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

  @XmlTransient
  public Timestamp getCreated() {
    return this.created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  @XmlTransient
  public Integer getCreatedby() {
    return createdby;
  }
  public void setCreatedby(Integer createdby) {
    this.createdby = createdby;
  }

  @XmlTransient
  public String getCreatedbyname() {
      return createdbyname;
  }
  public void setCreatedbyname(String createdbyname) {
      this.createdbyname = createdbyname;
  }

  @XmlTransient
  public Timestamp getLastmodified() {
    return this.lastmodified;
  }

  public void setLastmodified(Timestamp lastmodified) {
    this.lastmodified = lastmodified;
  }

  @XmlTransient
  public Integer getLastmodifiedby() {
    return lastmodifiedby;
  }
  public void setLastmodifiedby(Integer lastmodifiedby) {
    this.lastmodifiedby = lastmodifiedby;
  }
  
  @XmlTransient
  public String getLastmodifiedbyname() {
      return lastmodifiedbyname;
  }
  public void setLastmodifiedbyname(String lastmodifiedbyname) {
      this.lastmodifiedbyname = lastmodifiedbyname;
  }
}
