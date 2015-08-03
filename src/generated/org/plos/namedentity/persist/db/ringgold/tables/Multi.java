/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.ringgold.tables;

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
public class Multi extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord> {

	private static final long serialVersionUID = -111866992;

	/**
	 * The reference instance of <code>ringgold.multi</code>
	 */
	public static final org.plos.namedentity.persist.db.ringgold.tables.Multi MULTI = new org.plos.namedentity.persist.db.ringgold.tables.Multi();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord> getRecordType() {
		return org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord.class;
	}

	/**
	 * The column <code>ringgold.multi.Rec_ID</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord, java.lang.Integer> REC_ID = createField("Rec_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>ringgold.multi.GP_Code</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord, java.lang.Long> GP_CODE = createField("GP_Code", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.multi.P_Code</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord, java.lang.Long> P_CODE = createField("P_Code", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.multi.Class</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord, java.lang.String> CLASS = createField("Class", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.multi.Number</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord, java.lang.Short> NUMBER = createField("Number", org.jooq.impl.SQLDataType.SMALLINT, this, "");

	/**
	 * The column <code>ringgold.multi.Timestamp</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord, java.sql.Timestamp> TIMESTAMP = createField("Timestamp", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>ringgold.multi</code> table reference
	 */
	public Multi() {
		this("multi", null);
	}

	/**
	 * Create an aliased <code>ringgold.multi</code> table reference
	 */
	public Multi(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.ringgold.tables.Multi.MULTI);
	}

	private Multi(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord> aliased) {
		this(alias, aliased, null);
	}

	private Multi(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.ringgold.Ringgold.RINGGOLD, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord, java.lang.Integer> getIdentity() {
		return org.plos.namedentity.persist.db.ringgold.Keys.IDENTITY_MULTI;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord> getPrimaryKey() {
		return org.plos.namedentity.persist.db.ringgold.Keys.KEY_MULTI_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.ringgold.tables.records.MultiRecord>>asList(org.plos.namedentity.persist.db.ringgold.Keys.KEY_MULTI_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.ringgold.tables.Multi as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.ringgold.tables.Multi(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.ringgold.tables.Multi rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.ringgold.tables.Multi(name, null);
	}
}