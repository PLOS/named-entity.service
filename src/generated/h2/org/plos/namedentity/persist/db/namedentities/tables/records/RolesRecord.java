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
public class RolesRecord extends org.jooq.impl.UpdatableRecordImpl<org.plos.namedentity.persist.db.namedentities.tables.records.RolesRecord> implements org.jooq.Record11<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.sql.Date, java.sql.Date, java.lang.Integer, java.sql.Timestamp, java.sql.Timestamp, java.lang.Integer, java.lang.Integer> {

	private static final long serialVersionUID = -2035747540;

	/**
	 * Setter for <code>namedentities.roles.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>namedentities.roles.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>namedentities.roles.nedid</code>.
	 */
	public void setNedid(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>namedentities.roles.nedid</code>.
	 */
	public java.lang.Integer getNedid() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>namedentities.roles.typeid</code>.
	 */
	public void setTypeid(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>namedentities.roles.typeid</code>.
	 */
	public java.lang.Integer getTypeid() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>namedentities.roles.applicationtypeid</code>.
	 */
	public void setApplicationtypeid(java.lang.Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>namedentities.roles.applicationtypeid</code>.
	 */
	public java.lang.Integer getApplicationtypeid() {
		return (java.lang.Integer) getValue(3);
	}

	/**
	 * Setter for <code>namedentities.roles.startdate</code>.
	 */
	public void setStartdate(java.sql.Date value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>namedentities.roles.startdate</code>.
	 */
	public java.sql.Date getStartdate() {
		return (java.sql.Date) getValue(4);
	}

	/**
	 * Setter for <code>namedentities.roles.enddate</code>.
	 */
	public void setEnddate(java.sql.Date value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>namedentities.roles.enddate</code>.
	 */
	public java.sql.Date getEnddate() {
		return (java.sql.Date) getValue(5);
	}

	/**
	 * Setter for <code>namedentities.roles.sourcetypeid</code>.
	 */
	public void setSourcetypeid(java.lang.Integer value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>namedentities.roles.sourcetypeid</code>.
	 */
	public java.lang.Integer getSourcetypeid() {
		return (java.lang.Integer) getValue(6);
	}

	/**
	 * Setter for <code>namedentities.roles.created</code>.
	 */
	public void setCreated(java.sql.Timestamp value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>namedentities.roles.created</code>.
	 */
	public java.sql.Timestamp getCreated() {
		return (java.sql.Timestamp) getValue(7);
	}

	/**
	 * Setter for <code>namedentities.roles.lastmodified</code>.
	 */
	public void setLastmodified(java.sql.Timestamp value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>namedentities.roles.lastmodified</code>.
	 */
	public java.sql.Timestamp getLastmodified() {
		return (java.sql.Timestamp) getValue(8);
	}

	/**
	 * Setter for <code>namedentities.roles.createdby</code>.
	 */
	public void setCreatedby(java.lang.Integer value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>namedentities.roles.createdby</code>.
	 */
	public java.lang.Integer getCreatedby() {
		return (java.lang.Integer) getValue(9);
	}

	/**
	 * Setter for <code>namedentities.roles.lastmodifiedby</code>.
	 */
	public void setLastmodifiedby(java.lang.Integer value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>namedentities.roles.lastmodifiedby</code>.
	 */
	public java.lang.Integer getLastmodifiedby() {
		return (java.lang.Integer) getValue(10);
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
	// Record11 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row11<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.sql.Date, java.sql.Date, java.lang.Integer, java.sql.Timestamp, java.sql.Timestamp, java.lang.Integer, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row11) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row11<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.sql.Date, java.sql.Date, java.lang.Integer, java.sql.Timestamp, java.sql.Timestamp, java.lang.Integer, java.lang.Integer> valuesRow() {
		return (org.jooq.Row11) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES.NEDID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES.TYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field4() {
		return org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES.APPLICATIONTYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Date> field5() {
		return org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES.STARTDATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Date> field6() {
		return org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES.ENDDATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field7() {
		return org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES.SOURCETYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field8() {
		return org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field9() {
		return org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES.LASTMODIFIED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field10() {
		return org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES.CREATEDBY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field11() {
		return org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES.LASTMODIFIEDBY;
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
	public java.lang.Integer value4() {
		return getApplicationtypeid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Date value5() {
		return getStartdate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Date value6() {
		return getEnddate();
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
	public java.sql.Timestamp value8() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value9() {
		return getLastmodified();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value10() {
		return getCreatedby();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value11() {
		return getLastmodifiedby();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesRecord value2(java.lang.Integer value) {
		setNedid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesRecord value3(java.lang.Integer value) {
		setTypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesRecord value4(java.lang.Integer value) {
		setApplicationtypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesRecord value5(java.sql.Date value) {
		setStartdate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesRecord value6(java.sql.Date value) {
		setEnddate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesRecord value7(java.lang.Integer value) {
		setSourcetypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesRecord value8(java.sql.Timestamp value) {
		setCreated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesRecord value9(java.sql.Timestamp value) {
		setLastmodified(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesRecord value10(java.lang.Integer value) {
		setCreatedby(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesRecord value11(java.lang.Integer value) {
		setLastmodifiedby(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.Integer value3, java.lang.Integer value4, java.sql.Date value5, java.sql.Date value6, java.lang.Integer value7, java.sql.Timestamp value8, java.sql.Timestamp value9, java.lang.Integer value10, java.lang.Integer value11) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached RolesRecord
	 */
	public RolesRecord() {
		super(org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES);
	}

	/**
	 * Create a detached, initialised RolesRecord
	 */
	public RolesRecord(java.lang.Integer id, java.lang.Integer nedid, java.lang.Integer typeid, java.lang.Integer applicationtypeid, java.sql.Date startdate, java.sql.Date enddate, java.lang.Integer sourcetypeid, java.sql.Timestamp created, java.sql.Timestamp lastmodified, java.lang.Integer createdby, java.lang.Integer lastmodifiedby) {
		super(org.plos.namedentity.persist.db.namedentities.tables.Roles.ROLES);

		setValue(0, id);
		setValue(1, nedid);
		setValue(2, typeid);
		setValue(3, applicationtypeid);
		setValue(4, startdate);
		setValue(5, enddate);
		setValue(6, sourcetypeid);
		setValue(7, created);
		setValue(8, lastmodified);
		setValue(9, createdby);
		setValue(10, lastmodifiedby);
	}
}
