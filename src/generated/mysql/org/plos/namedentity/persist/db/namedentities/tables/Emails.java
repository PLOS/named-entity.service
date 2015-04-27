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
public class Emails extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord> {

	private static final long serialVersionUID = 1000626878;

	/**
	 * The reference instance of <code>namedEntities.emails</code>
	 */
	public static final org.plos.namedentity.persist.db.namedentities.tables.Emails EMAILS = new org.plos.namedentity.persist.db.namedentities.tables.Emails();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord> getRecordType() {
		return org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord.class;
	}

	/**
	 * The column <code>namedEntities.emails.id</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.emails.nedId</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Integer> NEDID = createField("nedId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.emails.typeId</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Integer> TYPEID = createField("typeId", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedEntities.emails.emailAddress</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.String> EMAILADDRESS = createField("emailAddress", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>namedEntities.emails.sourceTypeId</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Integer> SOURCETYPEID = createField("sourceTypeId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.emails.verified</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Byte> VERIFIED = createField("verified", org.jooq.impl.SQLDataType.TINYINT.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedEntities.emails.isActive</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Byte> ISACTIVE = createField("isActive", org.jooq.impl.SQLDataType.TINYINT.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedEntities.emails.created</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.sql.Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedEntities.emails.lastModified</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.sql.Timestamp> LASTMODIFIED = createField("lastModified", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>namedEntities.emails</code> table reference
	 */
	public Emails() {
		this("emails", null);
	}

	/**
	 * Create an aliased <code>namedEntities.emails</code> table reference
	 */
	public Emails(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.namedentities.tables.Emails.EMAILS);
	}

	private Emails(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Emails(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.namedentities.Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Integer> getIdentity() {
		return org.plos.namedentity.persist.db.namedentities.Keys.IDENTITY_EMAILS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord> getPrimaryKey() {
		return org.plos.namedentity.persist.db.namedentities.Keys.KEY_EMAILS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord>>asList(org.plos.namedentity.persist.db.namedentities.Keys.KEY_EMAILS_PRIMARY, org.plos.namedentity.persist.db.namedentities.Keys.KEY_EMAILS_EMAILADDRESS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, ?>>asList(org.plos.namedentity.persist.db.namedentities.Keys.EMAILS_IBFK_1, org.plos.namedentity.persist.db.namedentities.Keys.EMAILS_IBFK_3, org.plos.namedentity.persist.db.namedentities.Keys.EMAILS_IBFK_2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.namedentities.tables.Emails as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Emails(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.namedentities.tables.Emails rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Emails(name, null);
	}
}
