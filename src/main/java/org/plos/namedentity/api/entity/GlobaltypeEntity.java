package org.plos.namedentity.api.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * Modified JOOQ generated class(pojo=true).
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GlobaltypeEntity implements java.io.Serializable {

	private static final long serialVersionUID = 541426103;

	private java.lang.Integer  globaltypeid;
	private java.lang.Integer  typeid;
	private java.lang.String   shortdescription;
	private java.lang.String   longdescription;
	private java.lang.String   typecode;
	private java.sql.Timestamp created;
	private java.sql.Timestamp lastmodified;
	private java.lang.Integer  createdby;
	private java.lang.Integer  lastmodifiedby;

	public GlobaltypeEntity() {
        this(null,null,null,null,null,null,null,null,null);
    }

	public GlobaltypeEntity(
		java.lang.Integer  globaltypeid,
		java.lang.Integer  typeid,
		java.lang.String   shortdescription,
		java.lang.String   longdescription,
		java.lang.String   typecode,
		java.sql.Timestamp created,
		java.sql.Timestamp lastmodified,
		java.lang.Integer  createdby,
		java.lang.Integer  lastmodifiedby
	) {
		this.globaltypeid     = globaltypeid;
		this.typeid           = typeid;
		this.shortdescription = shortdescription;
		this.longdescription  = longdescription;
		this.typecode         = typecode;

		this.created          = (created != null ? created : new Timestamp(new Date().getTime()));
		this.lastmodified     = (lastmodified != null ? lastmodified : new Timestamp(new Date().getTime()));

		this.createdby        = createdby;
		this.lastmodifiedby   = lastmodifiedby;
	}

	public java.lang.Integer getGlobaltypeid() {
		return this.globaltypeid;
	}

	public void setGlobaltypeid(java.lang.Integer globaltypeid) {
		this.globaltypeid = globaltypeid;
	}

	public java.lang.Integer getTypeid() {
		return this.typeid;
	}

	public void setTypeid(java.lang.Integer typeid) {
		this.typeid = typeid;
	}

	public java.lang.String getShortdescription() {
		return this.shortdescription;
	}

	public void setShortdescription(java.lang.String shortdescription) {
		this.shortdescription = shortdescription;
	}

	public java.lang.String getLongdescription() {
		return this.longdescription;
	}

	public void setLongdescription(java.lang.String longdescription) {
		this.longdescription = longdescription;
	}

	public java.lang.String getTypecode() {
		return this.typecode;
	}

	public void setTypecode(java.lang.String typecode) {
		this.typecode = typecode;
	}

	public java.sql.Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(java.sql.Timestamp created) {
		this.created = created;
	}

	public java.sql.Timestamp getLastmodified() {
		return this.lastmodified;
	}

	public void setLastmodified(java.sql.Timestamp lastmodified) {
		this.lastmodified = lastmodified;
	}

	public java.lang.Integer getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(java.lang.Integer createdby) {
		this.createdby = createdby;
	}

	public java.lang.Integer getLastmodifiedby() {
		return this.lastmodifiedby;
	}

	public void setLastmodifiedby(java.lang.Integer lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }

        if (o == null || this.getClass() != o.getClass()) { return false; }

        GlobaltypeEntity entity = (GlobaltypeEntity) o;
        return Objects.equals(this.globaltypeid, entity.globaltypeid)
            && Objects.equals(this.typeid, entity.typeid)
            && Objects.equals(this.shortdescription, entity.shortdescription)
            && Objects.equals(this.longdescription, entity.longdescription)
            && Objects.equals(this.typecode, entity.typecode)
            && Objects.equals(this.created, entity.created)
            && Objects.equals(this.lastmodified, entity.lastmodified)
            && Objects.equals(this.createdby, entity.createdby)
            && Objects.equals(this.lastmodifiedby, entity.lastmodifiedby);
    }

    @Override
    public int hashCode() {
        return Objects.hash(globaltypeid, typeid, shortdescription, longdescription, 
            typecode, created, lastmodified, createdby, lastmodifiedby);
    }
}
