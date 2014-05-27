package org.plos.namedentity.api.dto;

public class RoleDTO {

	private Integer roleid;
	private Integer namedentityid;
	private String  sourceapplication;
	private String  roletype;
	private String  startdate;
	private String  enddate;

	public RoleDTO() {}

	public RoleDTO(
		Integer roleid,
		Integer namedentityid,
		String  sourceapplication,
		String  roletype,
		String  startdate,
		String  enddate
	) {
		this.roleid            = roleid;
		this.namedentityid     = namedentityid;
		this.sourceapplication = sourceapplication;
		this.roletype          = roletype;
		this.startdate         = startdate;
		this.enddate           = enddate;
	}

	public java.lang.Integer getRoleid() {
		return this.roleid;
	}
	public void setRoleid(java.lang.Integer roleid) {
		this.roleid = roleid;
	}

	public java.lang.Integer getNamedentityid() {
		return this.namedentityid;
	}
	public void setNamedentityid(java.lang.Integer namedentityid) {
		this.namedentityid = namedentityid;
	}
	
	public String getSourceapplication() {
	    return sourceapplication;
	}
	public void setSourceapplication(String sourceapplication) {
	    this.sourceapplication = sourceapplication;
	}
	
	public String getRoletype() {
	    return roletype;
	}
	public void setRoletype(String roletype) {
	    this.roletype = roletype;
	}
	
	public String getStartdate() {
	    return startdate;
	}
	public void setStartdate(String startdate) {
	    this.startdate = startdate;
	}
	
	public String getEnddate() {
	    return enddate;
	}
	public void setEnddate(String enddate) {
	    this.enddate = enddate;
	}
}
