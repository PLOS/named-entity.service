package org.plos.namedentity.api.dto;

public class AudittrailDTO {

	private java.lang.Integer  audittrailid;
	private java.lang.Integer  sourcefieldid;
	private java.lang.Integer  rownumber;
	private java.lang.String   oldvalue;
	private java.lang.String   newvalue;
	private java.sql.Timestamp lastmodified;
	private java.lang.Integer  lastmodifiedby;

	public AudittrailDTO() {}

	public AudittrailDTO(
		java.lang.Integer  audittrailid,
		java.lang.Integer  sourcefieldid,
		java.lang.Integer  rownumber,
		java.lang.String   oldvalue,
		java.lang.String   newvalue,
		java.sql.Timestamp lastmodified,
		java.lang.Integer  lastmodifiedby
	) {
		this.audittrailid = audittrailid;
		this.sourcefieldid = sourcefieldid;
		this.rownumber = rownumber;
		this.oldvalue = oldvalue;
		this.newvalue = newvalue;
		this.lastmodified = lastmodified;
		this.lastmodifiedby = lastmodifiedby;
	}

	public java.lang.Integer getAudittrailid() {
		return this.audittrailid;
	}

	public void setAudittrailid(java.lang.Integer audittrailid) {
		this.audittrailid = audittrailid;
	}

	public java.lang.Integer getSourcefieldid() {
		return this.sourcefieldid;
	}

	public void setSourcefieldid(java.lang.Integer sourcefieldid) {
		this.sourcefieldid = sourcefieldid;
	}

	public java.lang.Integer getRownumber() {
		return this.rownumber;
	}

	public void setRownumber(java.lang.Integer rownumber) {
		this.rownumber = rownumber;
	}

	public java.lang.String getOldvalue() {
		return this.oldvalue;
	}

	public void setOldvalue(java.lang.String oldvalue) {
		this.oldvalue = oldvalue;
	}

	public java.lang.String getNewvalue() {
		return this.newvalue;
	}

	public void setNewvalue(java.lang.String newvalue) {
		this.newvalue = newvalue;
	}

	public java.sql.Timestamp getLastmodified() {
		return this.lastmodified;
	}

	public void setLastmodified(java.sql.Timestamp lastmodified) {
		this.lastmodified = lastmodified;
	}

	public java.lang.Integer getLastmodifiedby() {
		return this.lastmodifiedby;
	}

	public void setLastmodifiedby(java.lang.Integer lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}
}
