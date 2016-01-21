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
public class DeletedIds extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord> {

	private static final long serialVersionUID = 1752262019;

	/**
	 * The reference instance of <code>ringgold.deleted_ids</code>
	 */
	public static final org.plos.namedentity.persist.db.ringgold.tables.DeletedIds DELETED_IDS = new org.plos.namedentity.persist.db.ringgold.tables.DeletedIds();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord> getRecordType() {
		return org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord.class;
	}

	/**
	 * The column <code>ringgold.deleted_ids.rec_id</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord, java.lang.Integer> REC_ID = createField("rec_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>ringgold.deleted_ids.old_ringgold_id</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord, java.lang.Integer> OLD_RINGGOLD_ID = createField("old_ringgold_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.deleted_ids.new_ringgold_id</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord, java.lang.Integer> NEW_RINGGOLD_ID = createField("new_ringgold_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.deleted_ids.details</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord, java.lang.String> DETAILS = createField("details", org.jooq.impl.SQLDataType.VARCHAR.length(150), this, "");

	/**
	 * The column <code>ringgold.deleted_ids.timestamp</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord, java.sql.Timestamp> TIMESTAMP = createField("timestamp", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>ringgold.deleted_ids</code> table reference
	 */
	public DeletedIds() {
		this("deleted_ids", null);
	}

	/**
	 * Create an aliased <code>ringgold.deleted_ids</code> table reference
	 */
	public DeletedIds(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.ringgold.tables.DeletedIds.DELETED_IDS);
	}

	private DeletedIds(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord> aliased) {
		this(alias, aliased, null);
	}

	private DeletedIds(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.ringgold.Ringgold.RINGGOLD, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord, java.lang.Integer> getIdentity() {
		return org.plos.namedentity.persist.db.ringgold.Keys.IDENTITY_DELETED_IDS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord> getPrimaryKey() {
		return org.plos.namedentity.persist.db.ringgold.Keys.KEY_DELETED_IDS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.ringgold.tables.records.DeletedIdsRecord>>asList(org.plos.namedentity.persist.db.ringgold.Keys.KEY_DELETED_IDS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.ringgold.tables.DeletedIds as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.ringgold.tables.DeletedIds(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.ringgold.tables.DeletedIds rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.ringgold.tables.DeletedIds(name, null);
	}
}
