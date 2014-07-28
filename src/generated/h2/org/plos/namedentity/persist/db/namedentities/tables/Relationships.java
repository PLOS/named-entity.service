/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.namedentities.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Relationships extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord> {

	private static final long serialVersionUID = -1804242500;

	/**
	 * The singleton instance of <code>namedentities.relationships</code>
	 */
	public static final org.plos.namedentity.persist.db.namedentities.tables.Relationships RELATIONSHIPS = new org.plos.namedentity.persist.db.namedentities.tables.Relationships();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord> getRecordType() {
		return org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord.class;
	}

	/**
	 * The column <code>namedentities.relationships.relationshipid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, java.lang.Integer> RELATIONSHIPID = createField("relationshipid", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedentities.relationships.masternamedentityid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, java.lang.Integer> MASTERNAMEDENTITYID = createField("masternamedentityid", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedentities.relationships.childnamedentityid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, java.lang.Integer> CHILDNAMEDENTITYID = createField("childnamedentityid", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedentities.relationships.relationshiptypeid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, java.lang.Integer> RELATIONSHIPTYPEID = createField("relationshiptypeid", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedentities.relationships.startdate</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, java.sql.Timestamp> STARTDATE = createField("startdate", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

	/**
	 * The column <code>namedentities.relationships.enddate</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, java.sql.Timestamp> ENDDATE = createField("enddate", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

	/**
	 * The column <code>namedentities.relationships.created</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, java.sql.Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedentities.relationships.lastmodified</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, java.sql.Timestamp> LASTMODIFIED = createField("lastmodified", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedentities.relationships.createdby</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, java.lang.Integer> CREATEDBY = createField("createdby", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedentities.relationships.lastmodifiedby</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, java.lang.Integer> LASTMODIFIEDBY = createField("lastmodifiedby", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>namedentities.relationships</code> table reference
	 */
	public Relationships() {
		this("relationships", null);
	}

	/**
	 * Create an aliased <code>namedentities.relationships</code> table reference
	 */
	public Relationships(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.namedentities.tables.Relationships.RELATIONSHIPS);
	}

	private Relationships(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Relationships(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.namedentities.Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, java.lang.Integer> getIdentity() {
		return org.plos.namedentity.persist.db.namedentities.Keys.IDENTITY_RELATIONSHIPS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord> getPrimaryKey() {
		return org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_D5;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord>>asList(org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_D5);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.RelationshipsRecord, ?>>asList(org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_D5E);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.namedentities.tables.Relationships as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Relationships(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.namedentities.tables.Relationships rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Relationships(name, null);
	}
}