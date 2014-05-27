package org.plos.namedentity.api.dto;

public class EmailDTO {

	private Integer emailid;
	private Integer namedentityid;
	private String  emailtype;
	private String  emailaddress;
	private Boolean isprimary;

	public EmailDTO() {
    }

	public EmailDTO(
		Integer emailid,
		Integer namedentityid,
		String  emailtype,
		String  emailaddress,
		Boolean isprimary
	) {
		this.emailid       = emailid;
		this.namedentityid = namedentityid;
		this.emailtype     = emailtype;
		this.emailaddress  = emailaddress;
		this.isprimary     = isprimary;
	}
	
	public Integer getEmailid() {
	    return emailid;
	}
	public void setEmailid(Integer emailid) {
	    this.emailid = emailid;
	}
	
	public Integer getNamedentityid() {
	    return namedentityid;
	}
	public void setNamedentityid(Integer namedentityid) {
	    this.namedentityid = namedentityid;
	}
	
	public String getEmailtype() {
	    return emailtype;
	}
	public void setEmailtype(String emailtype) {
	    this.emailtype = emailtype;
	}
	
	public String getEmailaddress() {
	    return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
	    this.emailaddress = emailaddress;
	}
	
	public Boolean getIsprimary() {
	    return isprimary;
	}
	public void setIsprimary(Boolean isprimary) {
	    this.isprimary = isprimary;
	}
}
