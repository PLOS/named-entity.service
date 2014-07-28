/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.namedentities.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Emails extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord> {

	private static final long serialVersionUID = -163528700;

	/**
	 * The singleton instance of <code>namedentities.emails</code>
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
	 * The column <code>namedentities.emails.emailid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Integer> EMAILID = createField("emailid", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedentities.emails.namedentityid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Integer> NAMEDENTITYID = createField("namedentityid", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedentities.emails.emailtypeid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Integer> EMAILTYPEID = createField("emailtypeid", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedentities.emails.emailaddress</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.String> EMAILADDRESS = createField("emailaddress", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>namedentities.emails.isprimary</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Byte> ISPRIMARY = createField("isprimary", org.jooq.impl.SQLDataType.TINYINT, this, "");

	/**
	 * The column <code>namedentities.emails.isactive</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Byte> ISACTIVE = createField("isactive", org.jooq.impl.SQLDataType.TINYINT, this, "");

	/**
	 * Create a <code>namedentities.emails</code> table reference
	 */
	public Emails() {
		this("emails", null);
	}

	/**
	 * Create an aliased <code>namedentities.emails</code> table reference
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
		return org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_7;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord>>asList(org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_7);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, ?>>asList(org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_7A, org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_7A1);
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