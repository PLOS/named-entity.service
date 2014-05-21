package org.plos.namedentity.api;

/**
 * JOOQ generated class(pojo=true). Added DTO to classname and moved to this pkg.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CasDTO implements java.io.Serializable {

	private static final long serialVersionUID = -108875444;

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
