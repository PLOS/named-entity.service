/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.namedentities.tables;

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
public class Sourcefields extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord> {

	private static final long serialVersionUID = -98425440;

	/**
	 * The reference instance of <code>NAMEDENTITIES.SOURCEFIELDS</code>
	 */
	public static final org.plos.namedentity.persist.db.namedentities.tables.Sourcefields SOURCEFIELDS = new org.plos.namedentity.persist.db.namedentities.tables.Sourcefields();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord> getRecordType() {
		return org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord.class;
	}

	/**
	 * The column <code>NAMEDENTITIES.SOURCEFIELDS.ID</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>NAMEDENTITIES.SOURCEFIELDS.SOURCETABLE</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord, java.lang.String> SOURCETABLE = createField("SOURCETABLE", org.jooq.impl.SQLDataType.CLOB.length(2147483647).nullable(false), this, "");

	/**
	 * The column <code>NAMEDENTITIES.SOURCEFIELDS.SOURCEFIELD</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord, java.lang.String> SOURCEFIELD = createField("SOURCEFIELD", org.jooq.impl.SQLDataType.CLOB.length(2147483647).nullable(false), this, "");

	/**
	 * The column <code>NAMEDENTITIES.SOURCEFIELDS.CREATED</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord, java.sql.Timestamp> CREATED = createField("CREATED", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>NAMEDENTITIES.SOURCEFIELDS.LASTMODIFIED</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord, java.sql.Timestamp> LASTMODIFIED = createField("LASTMODIFIED", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>NAMEDENTITIES.SOURCEFIELDS</code> table reference
	 */
	public Sourcefields() {
		this("SOURCEFIELDS", null);
	}

	/**
	 * Create an aliased <code>NAMEDENTITIES.SOURCEFIELDS</code> table reference
	 */
	public Sourcefields(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.namedentities.tables.Sourcefields.SOURCEFIELDS);
	}

	private Sourcefields(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Sourcefields(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.namedentities.Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord, java.lang.Integer> getIdentity() {
		return org.plos.namedentity.persist.db.namedentities.Keys.IDENTITY_SOURCEFIELDS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord> getPrimaryKey() {
		return org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_F;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.SourcefieldsRecord>>asList(org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_F);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.namedentities.tables.Sourcefields as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Sourcefields(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.namedentities.tables.Sourcefields rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Sourcefields(name, null);
	}
}
