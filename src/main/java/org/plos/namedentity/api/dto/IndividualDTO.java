package org.plos.namedentity.api.dto;

public class IndividualDTO {

	private Integer namedentityid;
	private String  firstname;
	private String  middlename;
	private String  lastname;
	private String  nickname;
	private String  nameprefix;
	private String  namesuffix;
	private String  displayname;
	private String  preferredlanguage;
	private String  preferredcommunication;
	//private byte[]  photoimage;
	private String  url;

	private String  uniqueidentifiertype;
	private String  uniqueidentifier;

	public IndividualDTO() {}

	public IndividualDTO(
        Integer namedentityid,
        String  firstname,
        String  middlename,
        String  lastname,
        String  nickname,
        String  nameprefix,
        String  namesuffix,
        String  displayname,
        String  preferredlanguage,
        String  preferredcommunication,
        String  url
    ) {
		this.namedentityid          = namedentityid;
		this.firstname              = firstname;
		this.middlename             = middlename;
		this.lastname               = lastname;
		this.nickname               = nickname;
		this.nameprefix             = nameprefix;
		this.namesuffix             = namesuffix;
		this.displayname            = displayname;
		this.preferredlanguage      = preferredlanguage;
		this.preferredcommunication = preferredcommunication;
		this.url                    = url;
	}
	
	public Integer getNamedentityid() {
	    return namedentityid;
	}
	public void setNamedentityid(Integer namedentityid) {
	    this.namedentityid = namedentityid;
	}
	
	public String getFirstname() {
	    return firstname;
	}
	public void setFirstname(String firstname) {
	    this.firstname = firstname;
	}
	
	public String getMiddlename() {
	    return middlename;
	}
	public void setMiddlename(String middlename) {
	    this.middlename = middlename;
	}
	
	public String getLastname() {
	    return lastname;
	}
	public void setLastname(String lastname) {
	    this.lastname = lastname;
	}
	
	public String getNickname() {
	    return nickname;
	}
	public void setNickname(String nickname) {
	    this.nickname = nickname;
	}
	
	public String getNameprefix() {
	    return nameprefix;
	}
	public void setNameprefix(String nameprefix) {
	    this.nameprefix = nameprefix;
	}
	
	public String getNamesuffix() {
	    return namesuffix;
	}
	public void setNamesuffix(String namesuffix) {
	    this.namesuffix = namesuffix;
	}
	
	public String getDisplayname() {
	    return displayname;
	}
	public void setDisplayname(String displayname) {
	    this.displayname = displayname;
	}
	
	public String getPreferredlanguage() {
	    return preferredlanguage;
	}
	public void setPreferredlanguage(String preferredlanguage) {
	    this.preferredlanguage = preferredlanguage;
	}
	
	public String getPreferredcommunication() {
	    return preferredcommunication;
	}
	public void setPreferredcommunication(String preferredcommunication) {
	    this.preferredcommunication = preferredcommunication;
	}
	
	public String getUrl() {
	    return url;
	}
	public void setUrl(String url) {
	    this.url = url;
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
