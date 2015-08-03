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
public class AuthcasRecord extends org.jooq.impl.UpdatableRecordImpl<org.plos.namedentity.persist.db.namedentities.tables.records.AuthcasRecord> implements org.jooq.Record13<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Byte, java.lang.String, java.lang.Byte, java.lang.Byte, java.sql.Timestamp, java.lang.Integer, java.sql.Timestamp, java.lang.Integer> {

	private static final long serialVersionUID = -215059274;

	/**
	 * Setter for <code>namedEntities.authCas.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>namedEntities.authCas.nedId</code>.
	 */
	public void setNedid(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.nedId</code>.
	 */
	public java.lang.Integer getNedid() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>namedEntities.authCas.emailId</code>.
	 */
	public void setEmailid(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.emailId</code>.
	 */
	public java.lang.Integer getEmailid() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>namedEntities.authCas.authId</code>.
	 */
	public void setAuthid(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.authId</code>.
	 */
	public java.lang.String getAuthid() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>namedEntities.authCas.password</code>.
	 */
	public void setPassword(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.password</code>.
	 */
	public java.lang.String getPassword() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>namedEntities.authCas.passwordReset</code>.
	 */
	public void setPasswordreset(java.lang.Byte value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.passwordReset</code>.
	 */
	public java.lang.Byte getPasswordreset() {
		return (java.lang.Byte) getValue(5);
	}

	/**
	 * Setter for <code>namedEntities.authCas.verificationToken</code>.
	 */
	public void setVerificationtoken(java.lang.String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.verificationToken</code>.
	 */
	public java.lang.String getVerificationtoken() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>namedEntities.authCas.verified</code>.
	 */
	public void setVerified(java.lang.Byte value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.verified</code>.
	 */
	public java.lang.Byte getVerified() {
		return (java.lang.Byte) getValue(7);
	}

	/**
	 * Setter for <code>namedEntities.authCas.isActive</code>.
	 */
	public void setIsactive(java.lang.Byte value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.isActive</code>.
	 */
	public java.lang.Byte getIsactive() {
		return (java.lang.Byte) getValue(8);
	}

	/**
	 * Setter for <code>namedEntities.authCas.created</code>.
	 */
	public void setCreated(java.sql.Timestamp value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.created</code>.
	 */
	public java.sql.Timestamp getCreated() {
		return (java.sql.Timestamp) getValue(9);
	}

	/**
	 * Setter for <code>namedEntities.authCas.createdBy</code>.
	 */
	public void setCreatedby(java.lang.Integer value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.createdBy</code>.
	 */
	public java.lang.Integer getCreatedby() {
		return (java.lang.Integer) getValue(10);
	}

	/**
	 * Setter for <code>namedEntities.authCas.lastModified</code>.
	 */
	public void setLastmodified(java.sql.Timestamp value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.lastModified</code>.
	 */
	public java.sql.Timestamp getLastmodified() {
		return (java.sql.Timestamp) getValue(11);
	}

	/**
	 * Setter for <code>namedEntities.authCas.lastModifiedBy</code>.
	 */
	public void setLastmodifiedby(java.lang.Integer value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>namedEntities.authCas.lastModifiedBy</code>.
	 */
	public java.lang.Integer getLastmodifiedby() {
		return (java.lang.Integer) getValue(12);
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
	// Record13 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row13<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Byte, java.lang.String, java.lang.Byte, java.lang.Byte, java.sql.Timestamp, java.lang.Integer, java.sql.Timestamp, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row13) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row13<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Byte, java.lang.String, java.lang.Byte, java.lang.Byte, java.sql.Timestamp, java.lang.Integer, java.sql.Timestamp, java.lang.Integer> valuesRow() {
		return (org.jooq.Row13) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.NEDID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.EMAILID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.AUTHID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.PASSWORD;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Byte> field6() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.PASSWORDRESET;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field7() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.VERIFICATIONTOKEN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Byte> field8() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.VERIFIED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Byte> field9() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.ISACTIVE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field10() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field11() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.CREATEDBY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field12() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.LASTMODIFIED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field13() {
		return org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS.LASTMODIFIEDBY;
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
		return getEmailid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getAuthid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getPassword();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Byte value6() {
		return getPasswordreset();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value7() {
		return getVerificationtoken();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Byte value8() {
		return getVerified();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Byte value9() {
		return getIsactive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value10() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value11() {
		return getCreatedby();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value12() {
		return getLastmodified();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value13() {
		return getLastmodifiedby();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value2(java.lang.Integer value) {
		setNedid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value3(java.lang.Integer value) {
		setEmailid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value4(java.lang.String value) {
		setAuthid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value5(java.lang.String value) {
		setPassword(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value6(java.lang.Byte value) {
		setPasswordreset(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value7(java.lang.String value) {
		setVerificationtoken(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value8(java.lang.Byte value) {
		setVerified(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value9(java.lang.Byte value) {
		setIsactive(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value10(java.sql.Timestamp value) {
		setCreated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value11(java.lang.Integer value) {
		setCreatedby(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value12(java.sql.Timestamp value) {
		setLastmodified(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord value13(java.lang.Integer value) {
		setLastmodifiedby(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthcasRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.Integer value3, java.lang.String value4, java.lang.String value5, java.lang.Byte value6, java.lang.String value7, java.lang.Byte value8, java.lang.Byte value9, java.sql.Timestamp value10, java.lang.Integer value11, java.sql.Timestamp value12, java.lang.Integer value13) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached AuthcasRecord
	 */
	public AuthcasRecord() {
		super(org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS);
	}

	/**
	 * Create a detached, initialised AuthcasRecord
	 */
	public AuthcasRecord(java.lang.Integer id, java.lang.Integer nedid, java.lang.Integer emailid, java.lang.String authid, java.lang.String password, java.lang.Byte passwordreset, java.lang.String verificationtoken, java.lang.Byte verified, java.lang.Byte isactive, java.sql.Timestamp created, java.lang.Integer createdby, java.sql.Timestamp lastmodified, java.lang.Integer lastmodifiedby) {
		super(org.plos.namedentity.persist.db.namedentities.tables.Authcas.AUTHCAS);

		setValue(0, id);
		setValue(1, nedid);
		setValue(2, emailid);
		setValue(3, authid);
		setValue(4, password);
		setValue(5, passwordreset);
		setValue(6, verificationtoken);
		setValue(7, verified);
		setValue(8, isactive);
		setValue(9, created);
		setValue(10, createdby);
		setValue(11, lastmodified);
		setValue(12, lastmodifiedby);
	}
}