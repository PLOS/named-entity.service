package org.plos.namedentity.api.entity;

import java.util.Objects;

/**
 * Modified JOOQ generated class(pojo=true).
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AddressEntity implements java.io.Serializable {

	private static final long serialVersionUID = 2006852801;

	private java.lang.Integer addressid;
	private java.lang.Integer namedentityid;
	private java.lang.Integer addresstypeid;
	private java.lang.String  addressline1;
	private java.lang.String  addressline2;
	private java.lang.String  addressline3;
	private java.lang.String  city;
	private java.lang.Integer statecodetypeid;
	private java.lang.Integer countrycodetypeid;
	private java.lang.String  postalcode;
	private java.lang.Integer maincontactnamedentityid;
	private java.lang.Integer latitude;
	private java.lang.Integer longitude;
	private java.lang.Byte    isprimary;
	private java.lang.Byte    isactive;

	public AddressEntity() {
        this(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
    }

	public AddressEntity(
		java.lang.Integer addressid,
		java.lang.Integer namedentityid,
		java.lang.Integer addresstypeid,
		java.lang.String  addressline1,
		java.lang.String  addressline2,
		java.lang.String  addressline3,
		java.lang.String  city,
		java.lang.Integer statecodetypeid,
		java.lang.Integer countrycodetypeid,
		java.lang.String  postalcode,
		java.lang.Integer maincontactnamedentityid,
		java.lang.Integer latitude,
		java.lang.Integer longitude,
		java.lang.Byte    isprimary,
		java.lang.Byte    isactive
	) {
		this.addressid                = addressid;
		this.namedentityid            = namedentityid;
		this.addresstypeid            = addresstypeid;
		this.addressline1             = addressline1;
		this.addressline2             = addressline2;
		this.addressline3             = addressline3;
		this.city                     = city;
		this.statecodetypeid          = statecodetypeid;
		this.countrycodetypeid        = countrycodetypeid;
		this.postalcode               = postalcode;
		this.maincontactnamedentityid = maincontactnamedentityid;
		this.latitude                 = latitude;
		this.longitude                = longitude;
		this.isprimary                = (isprimary != null ? isprimary : (byte)1);
		this.isactive                 = (isactive != null ? isactive : (byte)1);
	}

	public java.lang.Integer getAddressid() {
		return this.addressid;
	}

	public void setAddressid(java.lang.Integer addressid) {
		this.addressid = addressid;
	}

	public java.lang.Integer getNamedentityid() {
		return this.namedentityid;
	}

	public void setNamedentityid(java.lang.Integer namedentityid) {
		this.namedentityid = namedentityid;
	}

	public java.lang.Integer getAddresstypeid() {
		return this.addresstypeid;
	}

	public void setAddresstypeid(java.lang.Integer addresstypeid) {
		this.addresstypeid = addresstypeid;
	}

	public java.lang.String getAddressline1() {
		return this.addressline1;
	}

	public void setAddressline1(java.lang.String addressline1) {
		this.addressline1 = addressline1;
	}

	public java.lang.String getAddressline2() {
		return this.addressline2;
	}

	public void setAddressline2(java.lang.String addressline2) {
		this.addressline2 = addressline2;
	}

	public java.lang.String getAddressline3() {
		return this.addressline3;
	}

	public void setAddressline3(java.lang.String addressline3) {
		this.addressline3 = addressline3;
	}

	public java.lang.String getCity() {
		return this.city;
	}

	public void setCity(java.lang.String city) {
		this.city = city;
	}

	public java.lang.Integer getStatecodetypeid() {
		return this.statecodetypeid;
	}

	public void setStatecodetypeid(java.lang.Integer statecodetypeid) {
		this.statecodetypeid = statecodetypeid;
	}

	public java.lang.Integer getCountrycodetypeid() {
		return this.countrycodetypeid;
	}

	public void setCountrycodetypeid(java.lang.Integer countrycodetypeid) {
		this.countrycodetypeid = countrycodetypeid;
	}

	public java.lang.String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(java.lang.String postalcode) {
		this.postalcode = postalcode;
	}

	public java.lang.Integer getMaincontactnamedentityid() {
		return this.maincontactnamedentityid;
	}

	public void setMaincontactnamedentityid(java.lang.Integer maincontactnamedentityid) {
		this.maincontactnamedentityid = maincontactnamedentityid;
	}

	public java.lang.Integer getLatitude() {
		return this.latitude;
	}

	public void setLatitude(java.lang.Integer latitude) {
		this.latitude = latitude;
	}

	public java.lang.Integer getLongitude() {
		return this.longitude;
	}

	public void setLongitude(java.lang.Integer longitude) {
		this.longitude = longitude;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }

        if (o == null || this.getClass() != o.getClass()) { return false; }

        AddressEntity entity = (AddressEntity) o;
        return    Objects.equals(this.addressid, entity.addressid)
               && Objects.equals(this.namedentityid, entity.namedentityid)
               && Objects.equals(this.addresstypeid, entity.addresstypeid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressid, namedentityid, addresstypeid);
    }
}
