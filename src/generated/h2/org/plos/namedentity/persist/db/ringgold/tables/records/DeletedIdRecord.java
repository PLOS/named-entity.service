/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.ringgold.tables.records;

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
public class DeletedIdRecord extends org.jooq.impl.UpdatableRecordImpl<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdRecord> implements org.jooq.Record5<java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.sql.Timestamp> {

	private static final long serialVersionUID = -525675071;

	/**
	 * Setter for <code>RINGGOLD.DELETED_ID.ID__NO</code>.
	 */
	public void setId_No(java.lang.Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>RINGGOLD.DELETED_ID.ID__NO</code>.
	 */
	public java.lang.Long getId_No() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>RINGGOLD.DELETED_ID.OLD_ID</code>.
	 */
	public void setOldId(java.lang.Long value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>RINGGOLD.DELETED_ID.OLD_ID</code>.
	 */
	public java.lang.Long getOldId() {
		return (java.lang.Long) getValue(1);
	}

	/**
	 * Setter for <code>RINGGOLD.DELETED_ID.NEW_ID</code>.
	 */
	public void setNewId(java.lang.Long value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>RINGGOLD.DELETED_ID.NEW_ID</code>.
	 */
	public java.lang.Long getNewId() {
		return (java.lang.Long) getValue(2);
	}

	/**
	 * Setter for <code>RINGGOLD.DELETED_ID.DETAILS</code>.
	 */
	public void setDetails(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>RINGGOLD.DELETED_ID.DETAILS</code>.
	 */
	public java.lang.String getDetails() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>RINGGOLD.DELETED_ID.TIMESTAMP</code>.
	 */
	public void setTimestamp(java.sql.Timestamp value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>RINGGOLD.DELETED_ID.TIMESTAMP</code>.
	 */
	public java.sql.Timestamp getTimestamp() {
		return (java.sql.Timestamp) getValue(4);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Long> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record5 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row5<java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.sql.Timestamp> fieldsRow() {
		return (org.jooq.Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row5<java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.sql.Timestamp> valuesRow() {
		return (org.jooq.Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return org.plos.namedentity.persist.db.ringgold.tables.DeletedId.DELETED_ID.ID__NO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field2() {
		return org.plos.namedentity.persist.db.ringgold.tables.DeletedId.DELETED_ID.OLD_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field3() {
		return org.plos.namedentity.persist.db.ringgold.tables.DeletedId.DELETED_ID.NEW_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.plos.namedentity.persist.db.ringgold.tables.DeletedId.DELETED_ID.DETAILS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field5() {
		return org.plos.namedentity.persist.db.ringgold.tables.DeletedId.DELETED_ID.TIMESTAMP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value1() {
		return getId_No();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value2() {
		return getOldId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value3() {
		return getNewId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getDetails();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value5() {
		return getTimestamp();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeletedIdRecord value1(java.lang.Long value) {
		setId_No(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeletedIdRecord value2(java.lang.Long value) {
		setOldId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeletedIdRecord value3(java.lang.Long value) {
		setNewId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeletedIdRecord value4(java.lang.String value) {
		setDetails(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeletedIdRecord value5(java.sql.Timestamp value) {
		setTimestamp(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeletedIdRecord values(java.lang.Long value1, java.lang.Long value2, java.lang.Long value3, java.lang.String value4, java.sql.Timestamp value5) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached DeletedIdRecord
	 */
	public DeletedIdRecord() {
		super(org.plos.namedentity.persist.db.ringgold.tables.DeletedId.DELETED_ID);
	}

	/**
	 * Create a detached, initialised DeletedIdRecord
	 */
	public DeletedIdRecord(java.lang.Long id_No, java.lang.Long oldId, java.lang.Long newId, java.lang.String details, java.sql.Timestamp timestamp) {
		super(org.plos.namedentity.persist.db.ringgold.tables.DeletedId.DELETED_ID);

		setValue(0, id_No);
		setValue(1, oldId);
		setValue(2, newId);
		setValue(3, details);
		setValue(4, timestamp);
	}
}
