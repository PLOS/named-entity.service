package org.plos.namedentity.api;

@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UniqueidentifiersDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1900442676;

	private java.lang.Integer uniqueidentifiersid;
	private java.lang.Integer namedentityid;
	private java.lang.Integer uniqueidentifiertypeid;
	private java.lang.String  uniqueidentifier;

	public UniqueidentifiersDTO() {}

	public UniqueidentifiersDTO(
		java.lang.Integer uniqueidentifiersid,
		java.lang.Integer namedentityid,
		java.lang.Integer uniqueidentifiertypeid,
		java.lang.String  uniqueidentifier
	) {
		this.uniqueidentifiersid = uniqueidentifiersid;
		this.namedentityid = namedentityid;
		this.uniqueidentifiertypeid = uniqueidentifiertypeid;
		this.uniqueidentifier = uniqueidentifier;
	}

	public java.lang.Integer getUniqueidentifiersid() {
		return this.uniqueidentifiersid;
	}

	public void setUniqueidentifiersid(java.lang.Integer uniqueidentifiersid) {
		this.uniqueidentifiersid = uniqueidentifiersid;
	}

	public java.lang.Integer getNamedentityid() {
		return this.namedentityid;
	}

	public void setNamedentityid(java.lang.Integer namedentityid) {
		this.namedentityid = namedentityid;
	}

	public java.lang.Integer getUniqueidentifiertypeid() {
		return this.uniqueidentifiertypeid;
	}

	public void setUniqueidentifiertypeid(java.lang.Integer uniqueidentifiertypeid) {
		this.uniqueidentifiertypeid = uniqueidentifiertypeid;
	}

	public java.lang.String getUniqueidentifier() {
		return this.uniqueidentifier;
	}

	public void setUniqueidentifier(java.lang.String uniqueidentifier) {
		this.uniqueidentifier = uniqueidentifier;
	}
}
