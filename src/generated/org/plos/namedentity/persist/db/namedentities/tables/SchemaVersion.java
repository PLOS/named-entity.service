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
public class SchemaVersion extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord> {

	private static final long serialVersionUID = -3028034;

	/**
	 * The reference instance of <code>namedEntities.schema_version</code>
	 */
	public static final org.plos.namedentity.persist.db.namedentities.tables.SchemaVersion SCHEMA_VERSION = new org.plos.namedentity.persist.db.namedentities.tables.SchemaVersion();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord> getRecordType() {
		return org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord.class;
	}

	/**
	 * The column <code>namedEntities.schema_version.version_rank</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord, java.lang.Integer> VERSION_RANK = createField("version_rank", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.schema_version.installed_rank</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord, java.lang.Integer> INSTALLED_RANK = createField("installed_rank", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.schema_version.version</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord, java.lang.String> VERSION = createField("version", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

	/**
	 * The column <code>namedEntities.schema_version.description</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(200).nullable(false), this, "");

	/**
	 * The column <code>namedEntities.schema_version.type</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord, java.lang.String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false), this, "");

	/**
	 * The column <code>namedEntities.schema_version.script</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord, java.lang.String> SCRIPT = createField("script", org.jooq.impl.SQLDataType.VARCHAR.length(1000).nullable(false), this, "");

	/**
	 * The column <code>namedEntities.schema_version.checksum</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord, java.lang.Integer> CHECKSUM = createField("checksum", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedEntities.schema_version.installed_by</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord, java.lang.String> INSTALLED_BY = createField("installed_by", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

	/**
	 * The column <code>namedEntities.schema_version.installed_on</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord, java.sql.Timestamp> INSTALLED_ON = createField("installed_on", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedEntities.schema_version.execution_time</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord, java.lang.Integer> EXECUTION_TIME = createField("execution_time", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.schema_version.success</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord, java.lang.Byte> SUCCESS = createField("success", org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "");

	/**
	 * Create a <code>namedEntities.schema_version</code> table reference
	 */
	public SchemaVersion() {
		this("schema_version", null);
	}

	/**
	 * Create an aliased <code>namedEntities.schema_version</code> table reference
	 */
	public SchemaVersion(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.namedentities.tables.SchemaVersion.SCHEMA_VERSION);
	}

	private SchemaVersion(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord> aliased) {
		this(alias, aliased, null);
	}

	private SchemaVersion(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.namedentities.Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord> getPrimaryKey() {
		return org.plos.namedentity.persist.db.namedentities.Keys.KEY_SCHEMA_VERSION_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.SchemaVersionRecord>>asList(org.plos.namedentity.persist.db.namedentities.Keys.KEY_SCHEMA_VERSION_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.namedentities.tables.SchemaVersion as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.namedentities.tables.SchemaVersion(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.namedentities.tables.SchemaVersion rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.namedentities.tables.SchemaVersion(name, null);
	}
}