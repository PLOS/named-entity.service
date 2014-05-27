package org.plos.namedentity.api.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * Modified JOOQ generated class(pojo=true).
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoleEntity implements java.io.Serializable {

	private static final long serialVersionUID = 315701916;

	private java.lang.Integer  roleid;
	private java.lang.Integer  namedentityid;
	private java.lang.Integer  sourceapplicationtypeid;
	private java.lang.Integer  roletypeid;
	private java.sql.Timestamp startdate;
	private java.sql.Timestamp enddate;
	private java.sql.Timestamp created;
	private java.sql.Timestamp lastmodified;
	private java.lang.Integer  createdby;
	private java.lang.Integer  lastmodifiedby;

	public RoleEntity() {
		this(null,null,null,null,null,null,null,null,null,null);
	}

	public RoleEntity(
		java.lang.Integer  roleid,
		java.lang.Integer  namedentityid,
		java.lang.Integer  sourceapplicationtypeid,
		java.lang.Integer  roletypeid,
		java.sql.Timestamp startdate,
		java.sql.Timestamp enddate,
		java.sql.Timestamp created,
		java.sql.Timestamp lastmodified,
		java.lang.Integer  createdby,
		java.lang.Integer  lastmodifiedby
	) {
		this.roleid         = roleid;
		this.namedentityid  = namedentityid;
		this.sourceapplicationtypeid = sourceapplicationtypeid;
		this.roletypeid     = roletypeid;
		this.startdate      = startdate;
		this.enddate        = enddate;
		this.created        = (created != null ? created : new Timestamp(new Date().getTime()));
		this.lastmodified   = (lastmodified != null ? lastmodified : new Timestamp(new Date().getTime()));
		this.createdby      = createdby;
		this.lastmodifiedby = lastmodifiedby;
	}

	public java.lang.Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(java.lang.Integer roleid) {
		this.roleid = roleid;
	}

	public java.lang.Integer getNamedentityid() {
		return this.namedentityid;
	}

	public void setNamedentityid(java.lang.Integer namedentityid) {
		this.namedentityid = namedentityid;
	}

	public java.lang.Integer getSourceapplicationtypeid() {
		return this.sourceapplicationtypeid;
	}

	public void setSourceapplicationtypeid(java.lang.Integer sourceapplicationtypeid) {
		this.sourceapplicationtypeid = sourceapplicationtypeid;
	}

	public java.lang.Integer getRoletypeid() {
		return this.roletypeid;
	}

	public void setRoletypeid(java.lang.Integer roletypeid) {
		this.roletypeid = roletypeid;
	}

	public java.sql.Timestamp getStartdate() {
		return this.startdate;
	}

	public void setStartdate(java.sql.Timestamp startdate) {
		this.startdate = startdate;
	}

	public java.sql.Timestamp getEnddate() {
		return this.enddate;
	}

	public void setEnddate(java.sql.Timestamp enddate) {
		this.enddate = enddate;
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

        RoleEntity entity = (RoleEntity) o;
        return    Objects.equals(this.roleid, entity.roleid)
               && Objects.equals(this.namedentityid, entity.namedentityid)
               && Objects.equals(this.roletypeid, entity.roletypeid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleid, namedentityid, roletypeid);
    }
}
