/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.namedentities.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrganizationsRecord extends org.jooq.impl.UpdatableRecordImpl<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord> implements org.jooq.Record8<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Byte, java.lang.Byte, java.lang.String> {

	private static final long serialVersionUID = 1570418822;

	/**
	 * Setter for <code>namedentities.organizations.namedentityid</code>.
	 */
	public void setNamedentityid(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>namedentities.organizations.namedentityid</code>.
	 */
	public java.lang.Integer getNamedentityid() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>namedentities.organizations.organizationtypeid</code>.
	 */
	public void setOrganizationtypeid(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>namedentities.organizations.organizationtypeid</code>.
	 */
	public java.lang.Integer getOrganizationtypeid() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>namedentities.organizations.organizationfamiliarname</code>.
	 */
	public void setOrganizationfamiliarname(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>namedentities.organizations.organizationfamiliarname</code>.
	 */
	public java.lang.String getOrganizationfamiliarname() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>namedentities.organizations.organizationlegalname</code>.
	 */
	public void setOrganizationlegalname(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>namedentities.organizations.organizationlegalname</code>.
	 */
	public java.lang.String getOrganizationlegalname() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>namedentities.organizations.organizationmaincontactid</code>.
	 */
	public void setOrganizationmaincontactid(java.lang.Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>namedentities.organizations.organizationmaincontactid</code>.
	 */
	public java.lang.Integer getOrganizationmaincontactid() {
		return (java.lang.Integer) getValue(4);
	}

	/**
	 * Setter for <code>namedentities.organizations.isactive</code>.
	 */
	public void setIsactive(java.lang.Byte value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>namedentities.organizations.isactive</code>.
	 */
	public java.lang.Byte getIsactive() {
		return (java.lang.Byte) getValue(5);
	}

	/**
	 * Setter for <code>namedentities.organizations.isvisible</code>.
	 */
	public void setIsvisible(java.lang.Byte value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>namedentities.organizations.isvisible</code>.
	 */
	public java.lang.Byte getIsvisible() {
		return (java.lang.Byte) getValue(6);
	}

	/**
	 * Setter for <code>namedentities.organizations.url</code>.
	 */
	public void setUrl(java.lang.String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>namedentities.organizations.url</code>.
	 */
	public java.lang.String getUrl() {
		return (java.lang.String) getValue(7);
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
	// Record8 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row8<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Byte, java.lang.Byte, java.lang.String> fieldsRow() {
		return (org.jooq.Row8) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row8<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Byte, java.lang.Byte, java.lang.String> valuesRow() {
		return (org.jooq.Row8) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.NAMEDENTITYID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.ORGANIZATIONTYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.ORGANIZATIONFAMILIARNAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.ORGANIZATIONLEGALNAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field5() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.ORGANIZATIONMAINCONTACTID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Byte> field6() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.ISACTIVE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Byte> field7() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.ISVISIBLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field8() {
		return org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS.URL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getNamedentityid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getOrganizationtypeid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getOrganizationfamiliarname();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getOrganizationlegalname();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value5() {
		return getOrganizationmaincontactid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Byte value6() {
		return getIsactive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Byte value7() {
		return getIsvisible();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value8() {
		return getUrl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value1(java.lang.Integer value) {
		setNamedentityid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value2(java.lang.Integer value) {
		setOrganizationtypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value3(java.lang.String value) {
		setOrganizationfamiliarname(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value4(java.lang.String value) {
		setOrganizationlegalname(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value5(java.lang.Integer value) {
		setOrganizationmaincontactid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value6(java.lang.Byte value) {
		setIsactive(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value7(java.lang.Byte value) {
		setIsvisible(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord value8(java.lang.String value) {
		setUrl(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationsRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.String value3, java.lang.String value4, java.lang.Integer value5, java.lang.Byte value6, java.lang.Byte value7, java.lang.String value8) {
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
	public OrganizationsRecord(java.lang.Integer namedentityid, java.lang.Integer organizationtypeid, java.lang.String organizationfamiliarname, java.lang.String organizationlegalname, java.lang.Integer organizationmaincontactid, java.lang.Byte isactive, java.lang.Byte isvisible, java.lang.String url) {
		super(org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS);

		setValue(0, namedentityid);
		setValue(1, organizationtypeid);
		setValue(2, organizationfamiliarname);
		setValue(3, organizationlegalname);
		setValue(4, organizationmaincontactid);
		setValue(5, isactive);
		setValue(6, isvisible);
		setValue(7, url);
	}
}
