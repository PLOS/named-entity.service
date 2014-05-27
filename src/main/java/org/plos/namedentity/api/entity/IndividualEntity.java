package org.plos.namedentity.api.entity;

import java.util.Objects;

/**
 * Modified JOOQ generated class(pojo=true).
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IndividualEntity implements java.io.Serializable {

	private static final long serialVersionUID = 2026965529;

	private java.lang.Integer namedentityid;
	private java.lang.String  firstname;
	private java.lang.String  middlename;
	private java.lang.String  lastname;
	private java.lang.String  nickname;
	private java.lang.Integer nameprefixtypeid;
	private java.lang.Integer namesuffixtypeid;
	private java.lang.String  displayname;
	private java.lang.Integer preferredlanguagetypeid;
	private java.lang.Integer preferredcommunicationmethodtypeid;
	private byte[]            photoimage;
	private java.lang.String  url;

	public IndividualEntity() {}

	public IndividualEntity(
		java.lang.Integer namedentityid,
		java.lang.String  firstname,
		java.lang.String  middlename,
		java.lang.String  lastname,
		java.lang.String  nickname,
		java.lang.Integer nameprefixtypeid,
		java.lang.Integer namesuffixtypeid,
		java.lang.String  displayname,
		java.lang.Integer preferredlanguagetypeid,
		java.lang.Integer preferredcommunicationmethodtypeid,
		byte[]            photoimage,
		java.lang.String  url
	) {
		this.namedentityid = namedentityid;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.nickname = nickname;
		this.nameprefixtypeid = nameprefixtypeid;
		this.namesuffixtypeid = namesuffixtypeid;
		this.displayname = displayname;
		this.preferredlanguagetypeid = preferredlanguagetypeid;
		this.preferredcommunicationmethodtypeid = preferredcommunicationmethodtypeid;
		this.photoimage = photoimage;
		this.url = url;
	}

	public java.lang.Integer getNamedentityid() {
		return this.namedentityid;
	}

	public void setNamedentityid(java.lang.Integer namedentityid) {
		this.namedentityid = namedentityid;
	}

	public java.lang.String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(java.lang.String firstname) {
		this.firstname = firstname;
	}

	public java.lang.String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(java.lang.String middlename) {
		this.middlename = middlename;
	}

	public java.lang.String getLastname() {
		return this.lastname;
	}

	public void setLastname(java.lang.String lastname) {
		this.lastname = lastname;
	}

	public java.lang.String getNickname() {
		return this.nickname;
	}

	public void setNickname(java.lang.String nickname) {
		this.nickname = nickname;
	}

	public java.lang.Integer getNameprefixtypeid() {
		return this.nameprefixtypeid;
	}

	public void setNameprefixtypeid(java.lang.Integer nameprefixtypeid) {
		this.nameprefixtypeid = nameprefixtypeid;
	}

	public java.lang.Integer getNamesuffixtypeid() {
		return this.namesuffixtypeid;
	}

	public void setNamesuffixtypeid(java.lang.Integer namesuffixtypeid) {
		this.namesuffixtypeid = namesuffixtypeid;
	}

	public java.lang.String getDisplayname() {
		return this.displayname;
	}

	public void setDisplayname(java.lang.String displayname) {
		this.displayname = displayname;
	}

	public java.lang.Integer getPreferredlanguagetypeid() {
		return this.preferredlanguagetypeid;
	}

	public void setPreferredlanguagetypeid(java.lang.Integer preferredlanguagetypeid) {
		this.preferredlanguagetypeid = preferredlanguagetypeid;
	}

	public java.lang.Integer getPreferredcommunicationmethodtypeid() {
		return this.preferredcommunicationmethodtypeid;
	}

	public void setPreferredcommunicationmethodtypeid(java.lang.Integer preferredcommunicationmethodtypeid) {
		this.preferredcommunicationmethodtypeid = preferredcommunicationmethodtypeid;
	}

	public byte[] getPhotoimage() {
		return this.photoimage;
	}

	public void setPhotoimage(byte[] photoimage) {
		this.photoimage = photoimage;
	}

	public java.lang.String getUrl() {
		return this.url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }

        if (o == null || this.getClass() != o.getClass()) { return false; }

        IndividualEntity entity = (IndividualEntity) o;
        return    Objects.equals(this.namedentityid, entity.namedentityid)
               && Objects.equals(this.firstname, entity.firstname)
               && Objects.equals(this.middlename, entity.middlename)
               && Objects.equals(this.lastname, entity.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namedentityid, firstname, middlename, lastname);
    }
}
