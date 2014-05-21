package org.plos.namedentity.api;

/**
 * JOOQ generated class(pojo=true). Added DTO to classname and moved to this pkg.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GlobaltypesDTO implements java.io.Serializable {

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

	public GlobaltypesDTO() {}

	public GlobaltypesDTO(
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
		this.globaltypeid = globaltypeid;
		this.typeid = typeid;
		this.shortdescription = shortdescription;
		this.longdescription = longdescription;
		this.typecode = typecode;
		this.created = created;
		this.lastmodified = lastmodified;
		this.createdby = createdby;
		this.lastmodifiedby = lastmodifiedby;
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
}
