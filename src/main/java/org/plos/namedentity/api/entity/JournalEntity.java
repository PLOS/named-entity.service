package org.plos.namedentity.api.entity;

/**
 * Modified JOOQ generated class(pojo=true).
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JournalEntity implements java.io.Serializable {

	private static final long serialVersionUID = 254575195;

	private java.lang.Integer journalid;
	private java.lang.Integer namedentityid;
	private java.lang.Integer journaltypeid;

	public JournalEntity() {}

	public JournalEntity(
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
