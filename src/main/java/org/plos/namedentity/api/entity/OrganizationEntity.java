package org.plos.namedentity.api.entity;

/**
 * Modified JOOQ generated class(pojo=true).
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrganizationEntity implements java.io.Serializable {

	private static final long serialVersionUID = 276513267;

	private java.lang.Integer namedentityid;
	private java.lang.Integer organizationtypeid;
	private java.lang.String  organizationfamiliarname;
	private java.lang.String  organizationlegalname;
	private java.lang.Integer organizationmaincontactid;
	private java.lang.Byte    isactive;
	private java.lang.Byte    isvisible;
	private java.lang.String  url;

	public OrganizationEntity() {}

	public OrganizationEntity(
		java.lang.Integer namedentityid,
		java.lang.Integer organizationtypeid,
		java.lang.String  organizationfamiliarname,
		java.lang.String  organizationlegalname,
		java.lang.Integer organizationmaincontactid,
		java.lang.Byte    isactive,
		java.lang.Byte    isvisible,
		java.lang.String  url
	) {
		this.namedentityid = namedentityid;
		this.organizationtypeid = organizationtypeid;
		this.organizationfamiliarname = organizationfamiliarname;
		this.organizationlegalname = organizationlegalname;
		this.organizationmaincontactid = organizationmaincontactid;
		this.isactive = isactive;
		this.isvisible = isvisible;
		this.url = url;
	}

	public java.lang.Integer getNamedentityid() {
		return this.namedentityid;
	}

	public void setNamedentityid(java.lang.Integer namedentityid) {
		this.namedentityid = namedentityid;
	}

	public java.lang.Integer getOrganizationtypeid() {
		return this.organizationtypeid;
	}

	public void setOrganizationtypeid(java.lang.Integer organizationtypeid) {
		this.organizationtypeid = organizationtypeid;
	}

	public java.lang.String getOrganizationfamiliarname() {
		return this.organizationfamiliarname;
	}

	public void setOrganizationfamiliarname(java.lang.String organizationfamiliarname) {
		this.organizationfamiliarname = organizationfamiliarname;
	}

	public java.lang.String getOrganizationlegalname() {
		return this.organizationlegalname;
	}

	public void setOrganizationlegalname(java.lang.String organizationlegalname) {
		this.organizationlegalname = organizationlegalname;
	}

	public java.lang.Integer getOrganizationmaincontactid() {
		return this.organizationmaincontactid;
	}

	public void setOrganizationmaincontactid(java.lang.Integer organizationmaincontactid) {
		this.organizationmaincontactid = organizationmaincontactid;
	}

	public java.lang.Byte getIsactive() {
		return this.isactive;
	}

	public void setIsactive(java.lang.Byte isactive) {
		this.isactive = isactive;
	}

	public java.lang.Byte getIsvisible() {
		return this.isvisible;
	}

	public void setIsvisible(java.lang.Byte isvisible) {
		this.isvisible = isvisible;
	}

	public java.lang.String getUrl() {
		return this.url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}
}
