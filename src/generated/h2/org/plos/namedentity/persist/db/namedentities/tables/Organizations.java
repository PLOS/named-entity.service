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
public class Organizations extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord> {

	private static final long serialVersionUID = 1194255818;

	/**
	 * The reference instance of <code>namedentities.organizations</code>
	 */
	public static final org.plos.namedentity.persist.db.namedentities.tables.Organizations ORGANIZATIONS = new org.plos.namedentity.persist.db.namedentities.tables.Organizations();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord> getRecordType() {
		return org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord.class;
	}

	/**
	 * The column <code>namedentities.organizations.id</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedentities.organizations.nedid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, java.lang.Integer> NEDID = createField("nedid", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedentities.organizations.typeid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, java.lang.Integer> TYPEID = createField("typeid", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedentities.organizations.familiarname</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, java.lang.String> FAMILIARNAME = createField("familiarname", org.jooq.impl.SQLDataType.CLOB.length(2147483647).nullable(false), this, "");

	/**
	 * The column <code>namedentities.organizations.legalname</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, java.lang.String> LEGALNAME = createField("legalname", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>namedentities.organizations.maincontactid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, java.lang.Integer> MAINCONTACTID = createField("maincontactid", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedentities.organizations.sourcetypeid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, java.lang.Integer> SOURCETYPEID = createField("sourcetypeid", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedentities.organizations.isactive</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, java.lang.Byte> ISACTIVE = createField("isactive", org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "");

	/**
	 * The column <code>namedentities.organizations.created</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, java.sql.Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedentities.organizations.lastmodified</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, java.sql.Timestamp> LASTMODIFIED = createField("lastmodified", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>namedentities.organizations</code> table reference
	 */
	public Organizations() {
		this("organizations", null);
	}

	/**
	 * Create an aliased <code>namedentities.organizations</code> table reference
	 */
	public Organizations(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.namedentities.tables.Organizations.ORGANIZATIONS);
	}

	private Organizations(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Organizations(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.namedentities.Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, java.lang.Integer> getIdentity() {
		return org.plos.namedentity.persist.db.namedentities.Keys.IDENTITY_ORGANIZATIONS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord> getPrimaryKey() {
		return org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_3C;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord>>asList(org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_3C, org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_3C16D18);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.OrganizationsRecord, ?>>asList(org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_3C1, org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_3C16, org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_3C16D);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.namedentities.tables.Organizations as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Organizations(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.namedentities.tables.Organizations rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Organizations(name, null);
	}
}
