/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.information_schema.tables;

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
public class Locks extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.information_schema.tables.records.LocksRecord> {

	private static final long serialVersionUID = 712072185;

	/**
	 * The reference instance of <code>INFORMATION_SCHEMA.LOCKS</code>
	 */
	public static final org.plos.namedentity.persist.db.information_schema.tables.Locks LOCKS = new org.plos.namedentity.persist.db.information_schema.tables.Locks();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.information_schema.tables.records.LocksRecord> getRecordType() {
		return org.plos.namedentity.persist.db.information_schema.tables.records.LocksRecord.class;
	}

	/**
	 * The column <code>INFORMATION_SCHEMA.LOCKS.TABLE_SCHEMA</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.LocksRecord, java.lang.String> TABLE_SCHEMA = createField("TABLE_SCHEMA", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>INFORMATION_SCHEMA.LOCKS.TABLE_NAME</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.LocksRecord, java.lang.String> TABLE_NAME = createField("TABLE_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>INFORMATION_SCHEMA.LOCKS.SESSION_ID</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.LocksRecord, java.lang.Integer> SESSION_ID = createField("SESSION_ID", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>INFORMATION_SCHEMA.LOCKS.LOCK_TYPE</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.LocksRecord, java.lang.String> LOCK_TYPE = createField("LOCK_TYPE", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * Create a <code>INFORMATION_SCHEMA.LOCKS</code> table reference
	 */
	public Locks() {
		this("LOCKS", null);
	}

	/**
	 * Create an aliased <code>INFORMATION_SCHEMA.LOCKS</code> table reference
	 */
	public Locks(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.information_schema.tables.Locks.LOCKS);
	}

	private Locks(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.information_schema.tables.records.LocksRecord> aliased) {
		this(alias, aliased, null);
	}

	private Locks(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.information_schema.tables.records.LocksRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.information_schema.InformationSchema.INFORMATION_SCHEMA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.information_schema.tables.Locks as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.information_schema.tables.Locks(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.information_schema.tables.Locks rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.information_schema.tables.Locks(name, null);
	}
}
