package org.plos.namedentity.api;

@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EmailsDTO implements java.io.Serializable {

	private static final long serialVersionUID = -945009318;

	private java.lang.Integer emailid;
	private java.lang.Integer namedentityid;
	private java.lang.Integer emailtypeid;
	private java.lang.String  emailaddress;
	private java.lang.Byte    isprimary;
	private java.lang.Byte    isactive;

	public EmailsDTO() {}

	public EmailsDTO(
		java.lang.Integer emailid,
		java.lang.Integer namedentityid,
		java.lang.Integer emailtypeid,
		java.lang.String  emailaddress,
		java.lang.Byte    isprimary,
		java.lang.Byte    isactive
	) {
		this.emailid = emailid;
		this.namedentityid = namedentityid;
		this.emailtypeid = emailtypeid;
		this.emailaddress = emailaddress;
		this.isprimary = isprimary;
		this.isactive = isactive;
	}

	public java.lang.Integer getEmailid() {
		return this.emailid;
	}

	public void setEmailid(java.lang.Integer emailid) {
		this.emailid = emailid;
	}

	public java.lang.Integer getNamedentityid() {
		return this.namedentityid;
	}

	public void setNamedentityid(java.lang.Integer namedentityid) {
		this.namedentityid = namedentityid;
	}

	public java.lang.Integer getEmailtypeid() {
		return this.emailtypeid;
	}

	public void setEmailtypeid(java.lang.Integer emailtypeid) {
		this.emailtypeid = emailtypeid;
	}

	public java.lang.String getEmailaddress() {
		return this.emailaddress;
	}

	public void setEmailaddress(java.lang.String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public java.lang.Byte getIsprimary() {
		return this.isprimary;
	}

	public void setIsprimary(java.lang.Byte isprimary) {
		this.isprimary = isprimary;
	}

	public java.lang.Byte getIsactive() {
		return this.isactive;
	}

	public void setIsactive(java.lang.Byte isactive) {
		this.isactive = isactive;
	}
}
