package org.plos.namedentity.api.entity;

/**
 * Modified JOOQ generated class(pojo=true).
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DegreeEntity implements java.io.Serializable {

	private static final long serialVersionUID = -1999999966;

	private java.lang.Integer degreeid;
	private java.lang.Integer namedentityid;
	private java.lang.Integer degreetypeid;

	public DegreeEntity() {}

	public DegreeEntity(
		java.lang.Integer degreeid,
		java.lang.Integer namedentityid,
		java.lang.Integer degreetypeid
	) {
		this.degreeid = degreeid;
		this.namedentityid = namedentityid;
		this.degreetypeid = degreetypeid;
	}

	public java.lang.Integer getDegreeid() {
		return this.degreeid;
	}

	public void setDegreeid(java.lang.Integer degreeid) {
		this.degreeid = degreeid;
	}

	public java.lang.Integer getNamedentityid() {
		return this.namedentityid;
	}

	public void setNamedentityid(java.lang.Integer namedentityid) {
		this.namedentityid = namedentityid;
	}

	public java.lang.Integer getDegreetypeid() {
		return this.degreetypeid;
	}

	public void setDegreetypeid(java.lang.Integer degreetypeid) {
		this.degreetypeid = degreetypeid;
	}
}