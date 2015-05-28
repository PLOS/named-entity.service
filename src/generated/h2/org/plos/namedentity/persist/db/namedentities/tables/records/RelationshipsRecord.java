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
public class RelationshipsRecord extends org.jooq.impl.UpdatableRecordImpl<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord> implements org.jooq.Record12<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.sql.Date, java.sql.Date, java.sql.Timestamp, java.sql.Timestamp, java.lang.Integer, java.lang.Integer> {

	private static final long serialVersionUID = 624870827;

	/**
	 * Setter for <code>NAMEDENTITIES.RELATIONSHIPS.ID</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.RELATIONSHIPS.ID</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.RELATIONSHIPS.TYPEID</code>.
	 */
	public void setTypeid(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.RELATIONSHIPS.TYPEID</code>.
	 */
	public java.lang.Integer getTypeid() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.RELATIONSHIPS.NEDID</code>.
	 */
	public void setNedid(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.RELATIONSHIPS.NEDID</code>.
	 */
	public java.lang.Integer getNedid() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.RELATIONSHIPS.NEDIDRELATED</code>.
	 */
	public void setNedidrelated(java.lang.Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.RELATIONSHIPS.NEDIDRELATED</code>.
	 */
	public java.lang.Integer getNedidrelated() {
		return (java.lang.Integer) getValue(3);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.RELATIONSHIPS.TITLE</code>.
	 */
	public void setTitle(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.RELATIONSHIPS.TITLE</code>.
	 */
	public java.lang.String getTitle() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.RELATIONSHIPS.SOURCETYPEID</code>.
	 */
	public void setSourcetypeid(java.lang.Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.RELATIONSHIPS.SOURCETYPEID</code>.
	 */
	public java.lang.Integer getSourcetypeid() {
		return (java.lang.Integer) getValue(5);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.RELATIONSHIPS.STARTDATE</code>.
	 */
	public void setStartdate(java.sql.Date value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.RELATIONSHIPS.STARTDATE</code>.
	 */
	public java.sql.Date getStartdate() {
		return (java.sql.Date) getValue(6);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.RELATIONSHIPS.ENDDATE</code>.
	 */
	public void setEnddate(java.sql.Date value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.RELATIONSHIPS.ENDDATE</code>.
	 */
	public java.sql.Date getEnddate() {
		return (java.sql.Date) getValue(7);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.RELATIONSHIPS.CREATED</code>.
	 */
	public void setCreated(java.sql.Timestamp value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.RELATIONSHIPS.CREATED</code>.
	 */
	public java.sql.Timestamp getCreated() {
		return (java.sql.Timestamp) getValue(8);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.RELATIONSHIPS.LASTMODIFIED</code>.
	 */
	public void setLastmodified(java.sql.Timestamp value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.RELATIONSHIPS.LASTMODIFIED</code>.
	 */
	public java.sql.Timestamp getLastmodified() {
		return (java.sql.Timestamp) getValue(9);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.RELATIONSHIPS.CREATEDBY</code>.
	 */
	public void setCreatedby(java.lang.Integer value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.RELATIONSHIPS.CREATEDBY</code>.
	 */
	public java.lang.Integer getCreatedby() {
		return (java.lang.Integer) getValue(10);
	}

	/**
	 * Setter for <code>NAMEDENTITIES.RELATIONSHIPS.LASTMODIFIEDBY</code>.
	 */
	public void setLastmodifiedby(java.lang.Integer value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>NAMEDENTITIES.RELATIONSHIPS.LASTMODIFIEDBY</code>.
	 */
	public java.lang.Integer getLastmodifiedby() {
		return (java.lang.Integer) getValue(11);
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
	// Record12 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row12<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.sql.Date, java.sql.Date, java.sql.Timestamp, java.sql.Timestamp, java.lang.Integer, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row12) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row12<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.sql.Date, java.sql.Date, java.sql.Timestamp, java.sql.Timestamp, java.lang.Integer, java.lang.Integer> valuesRow() {
		return (org.jooq.Row12) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS.TYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS.NEDID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field4() {
		return org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS.NEDIDRELATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS.TITLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field6() {
		return org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS.SOURCETYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Date> field7() {
		return org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS.STARTDATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Date> field8() {
		return org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS.ENDDATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field9() {
		return org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field10() {
		return org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS.LASTMODIFIED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field11() {
		return org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS.CREATEDBY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field12() {
		return org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS.LASTMODIFIEDBY;
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
		return getTypeid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getNedid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value4() {
		return getNedidrelated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getTitle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value6() {
		return getSourcetypeid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Date value7() {
		return getStartdate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Date value8() {
		return getEnddate();
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
	public java.lang.Integer value11() {
		return getCreatedby();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value12() {
		return getLastmodifiedby();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord value2(java.lang.Integer value) {
		setTypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord value3(java.lang.Integer value) {
		setNedid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord value4(java.lang.Integer value) {
		setNedidrelated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord value5(java.lang.String value) {
		setTitle(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord value6(java.lang.Integer value) {
		setSourcetypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord value7(java.sql.Date value) {
		setStartdate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord value8(java.sql.Date value) {
		setEnddate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord value9(java.sql.Timestamp value) {
		setCreated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord value10(java.sql.Timestamp value) {
		setLastmodified(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord value11(java.lang.Integer value) {
		setCreatedby(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord value12(java.lang.Integer value) {
		setLastmodifiedby(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelationshipsRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.Integer value3, java.lang.Integer value4, java.lang.String value5, java.lang.Integer value6, java.sql.Date value7, java.sql.Date value8, java.sql.Timestamp value9, java.sql.Timestamp value10, java.lang.Integer value11, java.lang.Integer value12) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached RelationshipsRecord
	 */
	public RelationshipsRecord() {
		super(org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS);
	}

	/**
	 * Create a detached, initialised RelationshipsRecord
	 */
	public RelationshipsRecord(java.lang.Integer id, java.lang.Integer typeid, java.lang.Integer nedid, java.lang.Integer nedidrelated, java.lang.String title, java.lang.Integer sourcetypeid, java.sql.Date startdate, java.sql.Date enddate, java.sql.Timestamp created, java.sql.Timestamp lastmodified, java.lang.Integer createdby, java.lang.Integer lastmodifiedby) {
		super(org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS);

		setValue(0, id);
		setValue(1, typeid);
		setValue(2, nedid);
		setValue(3, nedidrelated);
		setValue(4, title);
		setValue(5, sourcetypeid);
		setValue(6, startdate);
		setValue(7, enddate);
		setValue(8, created);
		setValue(9, lastmodified);
		setValue(10, createdby);
		setValue(11, lastmodifiedby);
	}
}
