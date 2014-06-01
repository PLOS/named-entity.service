package org.plos.namedentity.api.dto;

public class UniqueidentifierDTO {

	private Integer uniqueidentifiersid;
	private Integer namedentityid;
	private String  uniqueidentifiertype;
	private String  uniqueidentifier;

	public UniqueidentifierDTO() {}

	public UniqueidentifierDTO(
		Integer uniqueidentifiersid,
		Integer namedentityid,
		String  uniqueidentifiertype,
		String  uniqueidentifier
	) {
		this.uniqueidentifiersid  = uniqueidentifiersid;
		this.namedentityid        = namedentityid;
		this.uniqueidentifiertype = uniqueidentifiertype;
		this.uniqueidentifier     = uniqueidentifier;
	}
	
	public Integer getUniqueidentifiersid() {
	    return uniqueidentifiersid;
	}
	public void setUniqueidentifiersid(Integer uniqueidentifiersid) {
	    this.uniqueidentifiersid = uniqueidentifiersid;
	}
	
	public Integer getNamedentityid() {
	    return namedentityid;
	}
	public void setNamedentityid(Integer namedentityid) {
	    this.namedentityid = namedentityid;
	}
	
	public String getUniqueidentifiertype() {
	    return uniqueidentifiertype;
	}
	public void setUniqueidentifiertype(String uniqueidentifiertype) {
	    this.uniqueidentifiertype = uniqueidentifiertype;
	}
	
	public String getUniqueidentifier() {
	    return uniqueidentifier;
	}
	public void setUniqueidentifier(String uniqueidentifier) {
	    this.uniqueidentifier = uniqueidentifier;
	}
}
