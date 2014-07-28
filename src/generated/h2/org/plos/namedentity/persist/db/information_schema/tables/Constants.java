/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.information_schema.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Constants extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.information_schema.tables.records.ConstantsRecord> {

	private static final long serialVersionUID = 677280142;

	/**
	 * The singleton instance of <code>information_schema.constants</code>
	 */
	public static final org.plos.namedentity.persist.db.information_schema.tables.Constants CONSTANTS = new org.plos.namedentity.persist.db.information_schema.tables.Constants();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.information_schema.tables.records.ConstantsRecord> getRecordType() {
		return org.plos.namedentity.persist.db.information_schema.tables.records.ConstantsRecord.class;
	}

	/**
	 * The column <code>information_schema.constants.constant_catalog</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.ConstantsRecord, java.lang.String> CONSTANT_CATALOG = createField("constant_catalog", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>information_schema.constants.constant_schema</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.ConstantsRecord, java.lang.String> CONSTANT_SCHEMA = createField("constant_schema", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>information_schema.constants.constant_name</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.ConstantsRecord, java.lang.String> CONSTANT_NAME = createField("constant_name", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>information_schema.constants.data_type</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.ConstantsRecord, java.lang.Integer> DATA_TYPE = createField("data_type", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>information_schema.constants.remarks</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.ConstantsRecord, java.lang.String> REMARKS = createField("remarks", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>information_schema.constants.sql</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.ConstantsRecord, java.lang.String> SQL = createField("sql", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>information_schema.constants.id</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.ConstantsRecord, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>information_schema.constants</code> table reference
	 */
	public Constants() {
		this("constants", null);
	}

	/**
	 * Create an aliased <code>information_schema.constants</code> table reference
	 */
	public Constants(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.information_schema.tables.Constants.CONSTANTS);
	}

	private Constants(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.information_schema.tables.records.ConstantsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Constants(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.information_schema.tables.records.ConstantsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.information_schema.InformationSchema.INFORMATION_SCHEMA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.information_schema.tables.Constants as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.information_schema.tables.Constants(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.information_schema.tables.Constants rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.information_schema.tables.Constants(name, null);
	}
}