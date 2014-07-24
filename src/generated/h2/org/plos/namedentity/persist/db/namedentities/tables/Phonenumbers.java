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
public class Phonenumbers extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord> {

	private static final long serialVersionUID = -1357656741;

	/**
	 * The singleton instance of <code>namedentities.phonenumbers</code>
	 */
	public static final org.plos.namedentity.persist.db.namedentities.tables.Phonenumbers PHONENUMBERS = new org.plos.namedentity.persist.db.namedentities.tables.Phonenumbers();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord> getRecordType() {
		return org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord.class;
	}

	/**
	 * The column <code>namedentities.phonenumbers.phonenumberid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Integer> PHONENUMBERID = createField("phonenumberid", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedentities.phonenumbers.namedentityid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Integer> NAMEDENTITYID = createField("namedentityid", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedentities.phonenumbers.phonenumbertypeid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Integer> PHONENUMBERTYPEID = createField("phonenumbertypeid", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedentities.phonenumbers.countrycodetypeid</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Integer> COUNTRYCODETYPEID = createField("countrycodetypeid", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedentities.phonenumbers.phonenumber</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.String> PHONENUMBER = createField("phonenumber", org.jooq.impl.SQLDataType.CLOB.length(2147483647), this, "");

	/**
	 * The column <code>namedentities.phonenumbers.extension</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.String> EXTENSION = createField("extension", org.jooq.impl.SQLDataType.CLOB.length(2147483647), this, "");

	/**
	 * The column <code>namedentities.phonenumbers.isprimary</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Byte> ISPRIMARY = createField("isprimary", org.jooq.impl.SQLDataType.TINYINT, this, "");

	/**
	 * The column <code>namedentities.phonenumbers.isactive</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Byte> ISACTIVE = createField("isactive", org.jooq.impl.SQLDataType.TINYINT, this, "");

	/**
	 * Create a <code>namedentities.phonenumbers</code> table reference
	 */
	public Phonenumbers() {
		this("phonenumbers", null);
	}

	/**
	 * Create an aliased <code>namedentities.phonenumbers</code> table reference
	 */
	public Phonenumbers(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.namedentities.tables.Phonenumbers.PHONENUMBERS);
	}

	private Phonenumbers(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord> aliased) {
		this(alias, aliased, null);
	}

	private Phonenumbers(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.namedentities.Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Integer> getIdentity() {
		return org.plos.namedentity.persist.db.namedentities.Keys.IDENTITY_PHONENUMBERS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord> getPrimaryKey() {
		return org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_8;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord>>asList(org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_8);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, ?>>asList(org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_8A, org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_8A9, org.plos.namedentity.persist.db.namedentities.Keys.CONSTRAINT_8A98);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.namedentities.tables.Phonenumbers as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Phonenumbers(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.namedentities.tables.Phonenumbers rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Phonenumbers(name, null);
	}
}
