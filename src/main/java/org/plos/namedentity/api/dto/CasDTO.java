package org.plos.namedentity.api.dto;

public class CasDTO {

	private java.lang.Integer casid;
	private java.lang.Integer namedentityid;
	private java.lang.Integer sourceapplicationtypeid;

	public CasDTO() {}

	public CasDTO(
		java.lang.Integer casid,
		java.lang.Integer namedentityid,
		java.lang.Integer sourceapplicationtypeid
	) {
		this.casid = casid;
		this.namedentityid = namedentityid;
		this.sourceapplicationtypeid = sourceapplicationtypeid;
	}

	public java.lang.Integer getCasid() {
		return this.casid;
	}

	public void setCasid(java.lang.Integer casid) {
		this.casid = casid;
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
}
