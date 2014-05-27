package org.plos.namedentity.api.dto;

public class JournalsDTO {

	private java.lang.Integer journalid;
	private java.lang.Integer namedentityid;
	private java.lang.Integer journaltypeid;

	public JournalsDTO() {}

	public JournalsDTO(
		java.lang.Integer journalid,
		java.lang.Integer namedentityid,
		java.lang.Integer journaltypeid
	) {
		this.journalid = journalid;
		this.namedentityid = namedentityid;
		this.journaltypeid = journaltypeid;
	}

	public java.lang.Integer getJournalid() {
		return this.journalid;
	}

	public void setJournalid(java.lang.Integer journalid) {
		this.journalid = journalid;
	}

	public java.lang.Integer getNamedentityid() {
		return this.namedentityid;
	}

	public void setNamedentityid(java.lang.Integer namedentityid) {
		this.namedentityid = namedentityid;
	}

	public java.lang.Integer getJournaltypeid() {
		return this.journaltypeid;
	}

	public void setJournaltypeid(java.lang.Integer journaltypeid) {
		this.journaltypeid = journaltypeid;
	}
}
