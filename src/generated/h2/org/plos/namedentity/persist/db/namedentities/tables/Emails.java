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

	private static final long serialVersionUID = -1373929198;

	/**
	 * The singleton instance of <code>NAMEDENTITIES.EMAILS</code>
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
	 * The column <code>NAMEDENTITIES.EMAILS.ID</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>NAMEDENTITIES.EMAILS.NEDID</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Integer> NEDID = createField("NEDID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>NAMEDENTITIES.EMAILS.TYPEID</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Integer> TYPEID = createField("TYPEID", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>NAMEDENTITIES.EMAILS.EMAILADDRESS</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.String> EMAILADDRESS = createField("EMAILADDRESS", org.jooq.impl.SQLDataType.CLOB.length(2147483647).nullable(false), this, "");

	/**
	 * The column <code>NAMEDENTITIES.EMAILS.SOURCETYPEID</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Integer> SOURCETYPEID = createField("SOURCETYPEID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>NAMEDENTITIES.EMAILS.ISPRIMARY</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Byte> ISPRIMARY = createField("ISPRIMARY", org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "");

	/**
	 * The column <code>NAMEDENTITIES.EMAILS.ISACTIVE</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, java.lang.Byte> ISACTIVE = createField("ISACTIVE", org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "");

	/**
	 * Create a <code>NAMEDENTITIES.EMAILS</code> table reference
	 */
	public Emails() {
		this("EMAILS", null);
	}

	/**
	 * Create an aliased <code>NAMEDENTITIES.EMAILS</code> table reference
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
		return java.util.Arrays.<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.EmailsRecord, ?>>asList(org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_7A, org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_7A1A, org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_7A1);
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
