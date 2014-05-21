package org.plos.namedentity.api;

/**
 * JOOQ generated class(pojo=true). Added DTO to classname and moved to this pkg.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PhonenumbersDTO implements java.io.Serializable {

	private static final long serialVersionUID = -1728456967;

	private java.lang.Integer phonenumberid;
	private java.lang.Integer namedentityid;
	private java.lang.Integer phonenumbertypeid;
	private java.lang.Integer countrycodetypeid;
	private java.lang.String  phonenumber;
	private java.lang.String  extension;
	private java.lang.Byte    isprimary;
	private java.lang.Byte    isactive;

	public PhonenumbersDTO() {}

	public PhonenumbersDTO(
		java.lang.Integer phonenumberid,
		java.lang.Integer namedentityid,
		java.lang.Integer phonenumbertypeid,
		java.lang.Integer countrycodetypeid,
		java.lang.String  phonenumber,
		java.lang.String  extension,
		java.lang.Byte    isprimary,
		java.lang.Byte    isactive
	) {
		this.phonenumberid = phonenumberid;
		this.namedentityid = namedentityid;
		this.phonenumbertypeid = phonenumbertypeid;
		this.countrycodetypeid = countrycodetypeid;
		this.phonenumber = phonenumber;
		this.extension = extension;
		this.isprimary = isprimary;
		this.isactive = isactive;
	}

	public java.lang.Integer getPhonenumberid() {
		return this.phonenumberid;
	}

	public void setPhonenumberid(java.lang.Integer phonenumberid) {
		this.phonenumberid = phonenumberid;
	}

	public java.lang.Integer getNamedentityid() {
		return this.namedentityid;
	}

	public void setNamedentityid(java.lang.Integer namedentityid) {
		this.namedentityid = namedentityid;
	}

	public java.lang.Integer getPhonenumbertypeid() {
		return this.phonenumbertypeid;
	}

	public void setPhonenumbertypeid(java.lang.Integer phonenumbertypeid) {
		this.phonenumbertypeid = phonenumbertypeid;
	}

	public java.lang.Integer getCountrycodetypeid() {
		return this.countrycodetypeid;
	}

	public void setCountrycodetypeid(java.lang.Integer countrycodetypeid) {
		this.countrycodetypeid = countrycodetypeid;
	}

	public java.lang.String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(java.lang.String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public java.lang.String getExtension() {
		return this.extension;
	}

	public void setExtension(java.lang.String extension) {
		this.extension = extension;
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
