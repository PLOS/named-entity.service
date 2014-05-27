package org.plos.namedentity.api.dto;

public class SubjectareasDTO {

	private java.lang.Integer subjectareaid;
	private java.lang.Integer namedentityid;
	private java.lang.Integer subjectareatypeid;

	public SubjectareasDTO() {}

	public SubjectareasDTO(
		java.lang.Integer subjectareaid,
		java.lang.Integer namedentityid,
		java.lang.Integer subjectareatypeid
	) {
		this.subjectareaid = subjectareaid;
		this.namedentityid = namedentityid;
		this.subjectareatypeid = subjectareatypeid;
	}

	public java.lang.Integer getSubjectareaid() {
		return this.subjectareaid;
	}

	public void setSubjectareaid(java.lang.Integer subjectareaid) {
		this.subjectareaid = subjectareaid;
	}

	public java.lang.Integer getNamedentityid() {
		return this.namedentityid;
	}

	public void setNamedentityid(java.lang.Integer namedentityid) {
		this.namedentityid = namedentityid;
	}

	public java.lang.Integer getSubjectareatypeid() {
		return this.subjectareatypeid;
	}

	public void setSubjectareatypeid(java.lang.Integer subjectareatypeid) {
		this.subjectareatypeid = subjectareatypeid;
	}
}
