/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.namedentities.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.1"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrganizationsRecord extends org.jooq.impl.UpdatableRecordImpl<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord> implements org.jooq.Record10<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Byte, java.sql.Timestamp, java.sql.Timestamp> {

	private static final long serialVersionUID = -863120889;

	/**
	 * Setter for <code>NAMEDENTITIES.ORGANIZATIONS.ID</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.ORGANIZATIONS.ID</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.ORGANIZATIONS.NEDID</code>.
	 */
	public void setNedid(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.ORGANIZATIONS.NEDID</code>.
	 */
	public java.lang.Integer getNedid() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.ORGANIZATIONS.TYPEID</code>.
	 */
	public void setTypeid(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.ORGANIZATIONS.TYPEID</code>.
	 */
	public java.lang.Integer getTypeid() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.ORGANIZATIONS.FAMILIARNAME</code>.
	 */
	public void setFamiliarname(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.ORGANIZATIONS.FAMILIARNAME</code>.
	 */
	public java.lang.String getFamiliarname() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.ORGANIZATIONS.LEGALNAME</code>.
	 */
	public void setLegalname(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.ORGANIZATIONS.LEGALNAME</code>.
	 */
	public java.lang.String getLegalname() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.ORGANIZATIONS.MAINCONTACTID</code>.
	 */
	public void setMaincontactid(java.lang.Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.ORGANIZATIONS.MAINCONTACTID</code>.
	 */
	public java.lang.Integer getMaincontactid() {
		return (java.lang.Integer) getValue(5);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.ORGANIZATIONS.SOURCETYPEID</code>.
	 */
	public void setSourcetypeid(java.lang.Integer value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.ORGANIZATIONS.SOURCETYPEID</code>.
	 */
	public java.lang.Integer getSourcetypeid() {
		return (java.lang.Integer) getValue(6);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.ORGANIZATIONS.ISACTIVE</code>.
	 */
	public void setIsactive(java.lang.Byte value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.ORGANIZATIONS.ISACTIVE</code>.
	 */
	public java.lang.Byte getIsactive() {
		return (java.lang.Byte) getValue(7);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.ORGANIZATIONS.CREATED</code>.
	 */
	public void setCreated(java.sql.Timestamp value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.ORGANIZATIONS.CREATED</code>.
	 */
	public java.sql.Timestamp getCreated() {
		return (java.sql.Timestamp) getValue(8);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.ORGANIZATIONS.LASTMODIFIED</code>.
	 */
	public void setLastmodified(java.sql.Timestamp value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.ORGANIZATIONS.LASTMODIFIED</code>.
	 */
	public java.sql.Timestamp getLastmodified() {
		return (java.sql.Timestamp) getValue(9);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record10 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row10<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Byte, java.sql.Timestamp, java.sql.Timestamp> fieldsRow() {
		return (org.jooq.Row10) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row10<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Byte, java.sql.Timestamp, java.sql.Timestamp> valuesRow() {
		return (org.jooq.Row10) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.NEDID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.TYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.FAMILIARNAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.LEGALNAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field6() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.MAINCONTACTID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field7() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.SOURCETYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Byte> field8() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.ISACTIVE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field9() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field10() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.LASTMODIFIED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getNedid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getTypeid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getFamiliarname();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getLegalname();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value6() {
		return getMaincontactid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value7() {
		return getSourcetypeid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Byte value8() {
		return getIsactive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value9() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value10() {
		return getLastmodified();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value2(java.lang.Integer value) {
		setNedid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value3(java.lang.Integer value) {
		setTypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value4(java.lang.String value) {
		setFamiliarname(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value5(java.lang.String value) {
		setLegalname(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value6(java.lang.Integer value) {
		setMaincontactid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value7(java.lang.Integer value) {
		setSourcetypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value8(java.lang.Byte value) {
		setIsactive(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value9(java.sql.Timestamp value) {
		setCreated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value10(java.sql.Timestamp value) {
		setLastmodified(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.Integer value3, java.lang.String value4, java.lang.String value5, java.lang.Integer value6, java.lang.Integer value7, java.lang.Byte value8, java.sql.Timestamp value9, java.sql.Timestamp value10) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached OrganizationsRecord
	 */
	public OrganizationsRecord() {
		super(org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS);
	}

	/**
	 * Create a detached, initialised OrganizationsRecord
	 */
	public OrganizationsRecord(java.lang.Integer id, java.lang.Integer nedid, java.lang.Integer typeid, java.lang.String familiarname, java.lang.String legalname, java.lang.Integer maincontactid, java.lang.Integer sourcetypeid, java.lang.Byte isactive, java.sql.Timestamp created, java.sql.Timestamp lastmodified) {
		super(org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS);

		setValue(0, id);
		setValue(1, nedid);
		setValue(2, typeid);
		setValue(3, familiarname);
		setValue(4, legalname);
		setValue(5, maincontactid);
		setValue(6, sourcetypeid);
		setValue(7, isactive);
		setValue(8, created);
		setValue(9, lastmodified);
	}
}
