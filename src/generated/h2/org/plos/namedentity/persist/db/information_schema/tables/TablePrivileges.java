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
public class TablePrivileges extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.information_schema.tables.records.TablePrivilegesRecord> {

	private static final long serialVersionUID = 1535782508;

	/**
	 * The reference instance of <code>information_schema.table_privileges</code>
	 */
	public static final org.plos.namedentity.persist.db.information_schema.tables.TablePrivileges TABLE_PRIVILEGES = new org.plos.namedentity.persist.db.information_schema.tables.TablePrivileges();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.information_schema.tables.records.TablePrivilegesRecord> getRecordType() {
		return org.plos.namedentity.persist.db.information_schema.tables.records.TablePrivilegesRecord.class;
	}

	/**
	 * The column <code>information_schema.table_privileges.grantor</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.TablePrivilegesRecord, java.lang.String> GRANTOR = createField("grantor", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>information_schema.table_privileges.grantee</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.TablePrivilegesRecord, java.lang.String> GRANTEE = createField("grantee", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>information_schema.table_privileges.table_catalog</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.TablePrivilegesRecord, java.lang.String> TABLE_CATALOG = createField("table_catalog", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>information_schema.table_privileges.table_schema</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.TablePrivilegesRecord, java.lang.String> TABLE_SCHEMA = createField("table_schema", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>information_schema.table_privileges.table_name</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.TablePrivilegesRecord, java.lang.String> TABLE_NAME = createField("table_name", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>information_schema.table_privileges.privilege_type</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.TablePrivilegesRecord, java.lang.String> PRIVILEGE_TYPE = createField("privilege_type", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>information_schema.table_privileges.is_grantable</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.TablePrivilegesRecord, java.lang.String> IS_GRANTABLE = createField("is_grantable", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * Create a <code>information_schema.table_privileges</code> table reference
	 */
	public TablePrivileges() {
		this("table_privileges", null);
	}

	/**
	 * Create an aliased <code>information_schema.table_privileges</code> table reference
	 */
	public TablePrivileges(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.information_schema.tables.TablePrivileges.TABLE_PRIVILEGES);
	}

	private TablePrivileges(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.information_schema.tables.records.TablePrivilegesRecord> aliased) {
		this(alias, aliased, null);
	}

	private TablePrivileges(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.information_schema.tables.records.TablePrivilegesRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.information_schema.InformationSchema.INFORMATION_SCHEMA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.information_schema.tables.TablePrivileges as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.information_schema.tables.TablePrivileges(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.information_schema.tables.TablePrivileges rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.information_schema.tables.TablePrivileges(name, null);
	}
}
