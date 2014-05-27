package org.plos.namedentity.api.dto;

public class PhonenumberDTO {

	private Integer phonenumberid;
	private Integer namedentityid;
	private String  phonenumbertype;
	private String  countrycodetype;
	private String  phonenumber;
	private String  extension;
	private Boolean isprimary;

	public PhonenumberDTO() {}

	public PhonenumberDTO(
		Integer phonenumberid,
		Integer namedentityid,
		String  phonenumbertype,
		String  countrycodetype,
		String  phonenumber,
		String  extension,
		Boolean isprimary
	) {
		this.phonenumberid   = phonenumberid;
		this.namedentityid   = namedentityid;
		this.phonenumbertype = phonenumbertype;
		this.countrycodetype = countrycodetype;
		this.phonenumber     = phonenumber;
		this.extension       = extension;
		this.isprimary       = isprimary;
	}
	
	public Integer getPhonenumberid() {
	    return phonenumberid;
	}
	public void setPhonenumberid(Integer phonenumberid) {
	    this.phonenumberid = phonenumberid;
	}
	
	public Integer getNamedentityid() {
	    return namedentityid;
	}
	public void setNamedentityid(Integer namedentityid) {
	    this.namedentityid = namedentityid;
	}
	
	public String getPhonenumbertype() {
	    return phonenumbertype;
	}
	public void setPhonenumbertype(String phonenumbertype) {
	    this.phonenumbertype = phonenumbertype;
	}
	
	public String getCountrycodetype() {
	    return countrycodetype;
	}
	public void setCountrycodetype(String countrycodetype) {
	    this.countrycodetype = countrycodetype;
	}
	
	public String getPhonenumber() {
	    return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
	    this.phonenumber = phonenumber;
	}
	
	public String getExtension() {
	    return extension;
	}
	public void setExtension(String extension) {
	    this.extension = extension;
	}
	
	public Boolean getIsprimary() {
	    return isprimary;
	}
	public void setIsprimary(Boolean isprimary) {
	    this.isprimary = isprimary;
	}
}
