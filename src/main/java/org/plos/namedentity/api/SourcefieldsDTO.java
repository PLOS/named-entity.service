package org.plos.namedentity.api;

/**
 * JOOQ generated class(pojo=true). Added DTO to classname and moved to this pkg.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SourcefieldsDTO implements java.io.Serializable {

	private static final long serialVersionUID = -2013224879;

	private java.lang.Integer sourcefieldid;
	private java.lang.String  sourcetable;
	private java.lang.String  sourcefield;

	public SourcefieldsDTO() {}

	public SourcefieldsDTO(
		java.lang.Integer sourcefieldid,
		java.lang.String  sourcetable,
		java.lang.String  sourcefield
	) {
		this.sourcefieldid = sourcefieldid;
		this.sourcetable = sourcetable;
		this.sourcefield = sourcefield;
	}

	public java.lang.Integer getSourcefieldid() {
		return this.sourcefieldid;
	}

	public void setSourcefieldid(java.lang.Integer sourcefieldid) {
		this.sourcefieldid = sourcefieldid;
	}

	public java.lang.String getSourcetable() {
		return this.sourcetable;
	}

	public void setSourcetable(java.lang.String sourcetable) {
		this.sourcetable = sourcetable;
	}

	public java.lang.String getSourcefield() {
		return this.sourcefield;
	}

	public void setSourcefield(java.lang.String sourcefield) {
		this.sourcefield = sourcefield;
	}
}
