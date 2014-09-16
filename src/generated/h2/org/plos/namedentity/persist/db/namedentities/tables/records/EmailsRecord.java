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
public class EmailsRecord extends org.jooq.impl.UpdatableRecordImpl<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord> implements org.jooq.Record7<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Byte, java.lang.Byte> {

	private static final long serialVersionUID = -2116161881;

	/**
	 * Setter for <code>NAMEDENTITIES.EMAILS.ID</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.EMAILS.ID</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.EMAILS.NEDID</code>.
	 */
	public void setNedid(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.EMAILS.NEDID</code>.
	 */
	public java.lang.Integer getNedid() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.EMAILS.TYPEID</code>.
	 */
	public void setTypeid(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.EMAILS.TYPEID</code>.
	 */
	public java.lang.Integer getTypeid() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.EMAILS.EMAILADDRESS</code>.
	 */
	public void setEmailaddress(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.EMAILS.EMAILADDRESS</code>.
	 */
	public java.lang.String getEmailaddress() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.EMAILS.SOURCETYPEID</code>.
	 */
	public void setSourcetypeid(java.lang.Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.EMAILS.SOURCETYPEID</code>.
	 */
	public java.lang.Integer getSourcetypeid() {
		return (java.lang.Integer) getValue(4);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.EMAILS.ISPRIMARY</code>.
	 */
	public void setIsprimary(java.lang.Byte value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.EMAILS.ISPRIMARY</code>.
	 */
	public java.lang.Byte getIsprimary() {
		return (java.lang.Byte) getValue(5);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.EMAILS.ISACTIVE</code>.
	 */
	public void setIsactive(java.lang.Byte value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.EMAILS.ISACTIVE</code>.
	 */
	public java.lang.Byte getIsactive() {
		return (java.lang.Byte) getValue(6);
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
	// Record7 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row7<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Byte, java.lang.Byte> fieldsRow() {
		return (org.jooq.Row7) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row7<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Byte, java.lang.Byte> valuesRow() {
		return (org.jooq.Row7) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.plos.namedentity.persist.db.namedentities.tables.Emails.EMAILS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.plos.namedentity.persist.db.namedentities.tables.Emails.EMAILS.NEDID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return org.plos.namedentity.persist.db.namedentities.tables.Emails.EMAILS.TYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.plos.namedentity.persist.db.namedentities.tables.Emails.EMAILS.EMAILADDRESS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field5() {
		return org.plos.namedentity.persist.db.namedentities.tables.Emails.EMAILS.SOURCETYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Byte> field6() {
		return org.plos.namedentity.persist.db.namedentities.tables.Emails.EMAILS.ISPRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Byte> field7() {
		return org.plos.namedentity.persist.db.namedentities.tables.Emails.EMAILS.ISACTIVE;
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
		return getEmailaddress();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value5() {
		return getSourcetypeid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Byte value6() {
		return getIsprimary();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Byte value7() {
		return getIsactive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmailsRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmailsRecord value2(java.lang.Integer value) {
		setNedid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmailsRecord value3(java.lang.Integer value) {
		setTypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmailsRecord value4(java.lang.String value) {
		setEmailaddress(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmailsRecord value5(java.lang.Integer value) {
		setSourcetypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmailsRecord value6(java.lang.Byte value) {
		setIsprimary(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmailsRecord value7(java.lang.Byte value) {
		setIsactive(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmailsRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.Integer value3, java.lang.String value4, java.lang.Integer value5, java.lang.Byte value6, java.lang.Byte value7) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached EmailsRecord
	 */
	public EmailsRecord() {
		super(org.plos.namedentity.persist.db.namedentities.tables.Emails.EMAILS);
	}

	/**
	 * Create a detached, initialised EmailsRecord
	 */
	public EmailsRecord(java.lang.Integer id, java.lang.Integer nedid, java.lang.Integer typeid, java.lang.String emailaddress, java.lang.Integer sourcetypeid, java.lang.Byte isprimary, java.lang.Byte isactive) {
		super(org.plos.namedentity.persist.db.namedentities.tables.Emails.EMAILS);

		setValue(0, id);
		setValue(1, nedid);
		setValue(2, typeid);
		setValue(3, emailaddress);
		setValue(4, sourcetypeid);
		setValue(5, isprimary);
		setValue(6, isactive);
	}
}
