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
public class UniqueidentifiersRecord extends org.jooq.impl.UpdatableRecordImpl<org.plos.namedentity.persist.db.namedentities.tables.records.UniqueidentifiersRecord> implements org.jooq.Record7<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.sql.Timestamp, java.sql.Timestamp> {

	private static final long serialVersionUID = -1304491961;

	/**
	 * Setter for <code>namedentities.uniqueidentifiers.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>namedentities.uniqueidentifiers.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>namedentities.uniqueidentifiers.nedid</code>.
	 */
	public void setNedid(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>namedentities.uniqueidentifiers.nedid</code>.
	 */
	public java.lang.Integer getNedid() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>namedentities.uniqueidentifiers.typeid</code>.
	 */
	public void setTypeid(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>namedentities.uniqueidentifiers.typeid</code>.
	 */
	public java.lang.Integer getTypeid() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>namedentities.uniqueidentifiers.uniqueidentifier</code>.
	 */
	public void setUniqueidentifier(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>namedentities.uniqueidentifiers.uniqueidentifier</code>.
	 */
	public java.lang.String getUniqueidentifier() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>namedentities.uniqueidentifiers.sourcetypeid</code>.
	 */
	public void setSourcetypeid(java.lang.Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>namedentities.uniqueidentifiers.sourcetypeid</code>.
	 */
	public java.lang.Integer getSourcetypeid() {
		return (java.lang.Integer) getValue(4);
	}

	/**
	 * Setter for <code>namedentities.uniqueidentifiers.created</code>.
	 */
	public void setCreated(java.sql.Timestamp value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>namedentities.uniqueidentifiers.created</code>.
	 */
	public java.sql.Timestamp getCreated() {
		return (java.sql.Timestamp) getValue(5);
	}

	/**
	 * Setter for <code>namedentities.uniqueidentifiers.lastmodified</code>.
	 */
	public void setLastmodified(java.sql.Timestamp value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>namedentities.uniqueidentifiers.lastmodified</code>.
	 */
	public java.sql.Timestamp getLastmodified() {
		return (java.sql.Timestamp) getValue(6);
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
	public org.jooq.Row7<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.sql.Timestamp, java.sql.Timestamp> fieldsRow() {
		return (org.jooq.Row7) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row7<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.sql.Timestamp, java.sql.Timestamp> valuesRow() {
		return (org.jooq.Row7) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.plos.namedentity.persist.db.namedentities.tables.Uniqueidentifiers.UNIQUEIDENTIFIERS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.plos.namedentity.persist.db.namedentities.tables.Uniqueidentifiers.UNIQUEIDENTIFIERS.NEDID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return org.plos.namedentity.persist.db.namedentities.tables.Uniqueidentifiers.UNIQUEIDENTIFIERS.TYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.plos.namedentity.persist.db.namedentities.tables.Uniqueidentifiers.UNIQUEIDENTIFIERS.UNIQUEIDENTIFIER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field5() {
		return org.plos.namedentity.persist.db.namedentities.tables.Uniqueidentifiers.UNIQUEIDENTIFIERS.SOURCETYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field6() {
		return org.plos.namedentity.persist.db.namedentities.tables.Uniqueidentifiers.UNIQUEIDENTIFIERS.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field7() {
		return org.plos.namedentity.persist.db.namedentities.tables.Uniqueidentifiers.UNIQUEIDENTIFIERS.LASTMODIFIED;
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
		return getUniqueidentifier();
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
	public java.sql.Timestamp value6() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value7() {
		return getLastmodified();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueidentifiersRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueidentifiersRecord value2(java.lang.Integer value) {
		setNedid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueidentifiersRecord value3(java.lang.Integer value) {
		setTypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueidentifiersRecord value4(java.lang.String value) {
		setUniqueidentifier(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueidentifiersRecord value5(java.lang.Integer value) {
		setSourcetypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueidentifiersRecord value6(java.sql.Timestamp value) {
		setCreated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueidentifiersRecord value7(java.sql.Timestamp value) {
		setLastmodified(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueidentifiersRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.Integer value3, java.lang.String value4, java.lang.Integer value5, java.sql.Timestamp value6, java.sql.Timestamp value7) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached UniqueidentifiersRecord
	 */
	public UniqueidentifiersRecord() {
		super(org.plos.namedentity.persist.db.namedentities.tables.Uniqueidentifiers.UNIQUEIDENTIFIERS);
	}

	/**
	 * Create a detached, initialised UniqueidentifiersRecord
	 */
	public UniqueidentifiersRecord(java.lang.Integer id, java.lang.Integer nedid, java.lang.Integer typeid, java.lang.String uniqueidentifier, java.lang.Integer sourcetypeid, java.sql.Timestamp created, java.sql.Timestamp lastmodified) {
		super(org.plos.namedentity.persist.db.namedentities.tables.Uniqueidentifiers.UNIQUEIDENTIFIERS);

		setValue(0, id);
		setValue(1, nedid);
		setValue(2, typeid);
		setValue(3, uniqueidentifier);
		setValue(4, sourcetypeid);
		setValue(5, created);
		setValue(6, lastmodified);
	}
}
