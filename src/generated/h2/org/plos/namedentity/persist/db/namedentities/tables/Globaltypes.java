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
public class Globaltypes extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord> {

	private static final long serialVersionUID = 1640697371;

	/**
	 * The reference instance of <code>NAMEDENTITIES.GLOBALTYPES</code>
	 */
	public static final org.plos.namedentity.persist.db.namedentities.tables.Globaltypes GLOBALTYPES = new org.plos.namedentity.persist.db.namedentities.tables.Globaltypes();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord> getRecordType() {
		return org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord.class;
	}

	/**
	 * The column <code>NAMEDENTITIES.GLOBALTYPES.ID</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>NAMEDENTITIES.GLOBALTYPES.TYPEID</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord, java.lang.Integer> TYPEID = createField("TYPEID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>NAMEDENTITIES.GLOBALTYPES.SHORTDESCRIPTION</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord, java.lang.String> SHORTDESCRIPTION = createField("SHORTDESCRIPTION", org.jooq.impl.SQLDataType.CLOB.length(2147483647).nullable(false), this, "");

	/**
	 * The column <code>NAMEDENTITIES.GLOBALTYPES.LONGDESCRIPTION</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord, java.lang.String> LONGDESCRIPTION = createField("LONGDESCRIPTION", org.jooq.impl.SQLDataType.CLOB.length(2147483647), this, "");

	/**
	 * The column <code>NAMEDENTITIES.GLOBALTYPES.TYPECODE</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord, java.lang.String> TYPECODE = createField("TYPECODE", org.jooq.impl.SQLDataType.CLOB.length(2147483647).nullable(false), this, "");

	/**
	 * The column <code>NAMEDENTITIES.GLOBALTYPES.CREATED</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord, java.sql.Timestamp> CREATED = createField("CREATED", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>NAMEDENTITIES.GLOBALTYPES.LASTMODIFIED</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord, java.sql.Timestamp> LASTMODIFIED = createField("LASTMODIFIED", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>NAMEDENTITIES.GLOBALTYPES.CREATEDBY</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord, java.lang.Integer> CREATEDBY = createField("CREATEDBY", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>NAMEDENTITIES.GLOBALTYPES.LASTMODIFIEDBY</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord, java.lang.Integer> LASTMODIFIEDBY = createField("LASTMODIFIEDBY", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>NAMEDENTITIES.GLOBALTYPES</code> table reference
	 */
	public Globaltypes() {
		this("GLOBALTYPES", null);
	}

	/**
	 * Create an aliased <code>NAMEDENTITIES.GLOBALTYPES</code> table reference
	 */
	public Globaltypes(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.namedentities.tables.Globaltypes.GLOBALTYPES);
	}

	private Globaltypes(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord> aliased) {
		this(alias, aliased, null);
	}

	private Globaltypes(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.namedentities.Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord, java.lang.Integer> getIdentity() {
		return org.plos.namedentity.persist.db.namedentities.Keys.IDENTITY_GLOBALTYPES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord> getPrimaryKey() {
		return org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_4;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord>>asList(org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_4);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.GlobaltypesRecord, ?>>asList(org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_4F);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.namedentities.tables.Globaltypes as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Globaltypes(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.namedentities.tables.Globaltypes rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Globaltypes(name, null);
	}
}
