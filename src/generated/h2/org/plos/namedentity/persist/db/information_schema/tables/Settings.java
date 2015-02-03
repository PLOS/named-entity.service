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
public class Settings extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.information_schema.tables.records.SettingsRecord> {

	private static final long serialVersionUID = 924777661;

	/**
	 * The reference instance of <code>information_schema.settings</code>
	 */
	public static final org.plos.namedentity.persist.db.information_schema.tables.Settings SETTINGS = new org.plos.namedentity.persist.db.information_schema.tables.Settings();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.information_schema.tables.records.SettingsRecord> getRecordType() {
		return org.plos.namedentity.persist.db.information_schema.tables.records.SettingsRecord.class;
	}

	/**
	 * The column <code>information_schema.settings.name</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.SettingsRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>information_schema.settings.value</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.information_schema.tables.records.SettingsRecord, java.lang.String> VALUE = createField("value", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * Create a <code>information_schema.settings</code> table reference
	 */
	public Settings() {
		this("settings", null);
	}

	/**
	 * Create an aliased <code>information_schema.settings</code> table reference
	 */
	public Settings(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.information_schema.tables.Settings.SETTINGS);
	}

	private Settings(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.information_schema.tables.records.SettingsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Settings(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.information_schema.tables.records.SettingsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.information_schema.InformationSchema.INFORMATION_SCHEMA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.information_schema.tables.Settings as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.information_schema.tables.Settings(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.information_schema.tables.Settings rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.information_schema.tables.Settings(name, null);
	}
}
