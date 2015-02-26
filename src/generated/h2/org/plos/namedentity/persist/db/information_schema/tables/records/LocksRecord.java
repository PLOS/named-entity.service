/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.information_schema.tables.records;

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
public class LocksRecord extends org.jooq.impl.TableRecordImpl<org.plos.namedentity.persist.db.information_schema.tables.records.LocksRecord> implements org.jooq.Record4<java.lang.String, java.lang.String, java.lang.Integer, java.lang.String> {

	private static final long serialVersionUID = -1967813437;

	/**
	 * Setter for <code>INFORMATION_SCHEMA.LOCKS.TABLE_SCHEMA</code>.
	 */
	public void setTableSchema(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>INFORMATION_SCHEMA.LOCKS.TABLE_SCHEMA</code>.
	 */
	public java.lang.String getTableSchema() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>INFORMATION_SCHEMA.LOCKS.TABLE_NAME</code>.
	 */
	public void setTableName(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>INFORMATION_SCHEMA.LOCKS.TABLE_NAME</code>.
	 */
	public java.lang.String getTableName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>INFORMATION_SCHEMA.LOCKS.SESSION_ID</code>.
	 */
	public void setSessionId(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>INFORMATION_SCHEMA.LOCKS.SESSION_ID</code>.
	 */
	public java.lang.Integer getSessionId() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>INFORMATION_SCHEMA.LOCKS.LOCK_TYPE</code>.
	 */
	public void setLockType(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>INFORMATION_SCHEMA.LOCKS.LOCK_TYPE</code>.
	 */
	public java.lang.String getLockType() {
		return (java.lang.String) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.String, java.lang.String, java.lang.Integer, java.lang.String> fieldsRow() {
		return (org.jooq.Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.String, java.lang.String, java.lang.Integer, java.lang.String> valuesRow() {
		return (org.jooq.Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return org.plos.namedentity.persist.db.information_schema.tables.Locks.LOCKS.TABLE_SCHEMA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return org.plos.namedentity.persist.db.information_schema.tables.Locks.LOCKS.TABLE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return org.plos.namedentity.persist.db.information_schema.tables.Locks.LOCKS.SESSION_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.plos.namedentity.persist.db.information_schema.tables.Locks.LOCKS.LOCK_TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getTableSchema();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getTableName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getSessionId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getLockType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocksRecord value1(java.lang.String value) {
		setTableSchema(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocksRecord value2(java.lang.String value) {
		setTableName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocksRecord value3(java.lang.Integer value) {
		setSessionId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocksRecord value4(java.lang.String value) {
		setLockType(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocksRecord values(java.lang.String value1, java.lang.String value2, java.lang.Integer value3, java.lang.String value4) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached LocksRecord
	 */
	public LocksRecord() {
		super(org.plos.namedentity.persist.db.information_schema.tables.Locks.LOCKS);
	}

	/**
	 * Create a detached, initialised LocksRecord
	 */
	public LocksRecord(java.lang.String tableSchema, java.lang.String tableName, java.lang.Integer sessionId, java.lang.String lockType) {
		super(org.plos.namedentity.persist.db.information_schema.tables.Locks.LOCKS);

		setValue(0, tableSchema);
		setValue(1, tableName);
		setValue(2, sessionId);
		setValue(3, lockType);
	}
}
