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

	private static final long serialVersionUID = -976976226;

	/**
	 * The singleton instance of <code>namedEntities.phoneNumbers</code>
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
	 * The column <code>namedEntities.phoneNumbers.id</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.phoneNumbers.nedId</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Integer> NEDID = createField("nedId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.phoneNumbers.typeId</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Integer> TYPEID = createField("typeId", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedEntities.phoneNumbers.countryCodeTypeId</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Integer> COUNTRYCODETYPEID = createField("countryCodeTypeId", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedEntities.phoneNumbers.phoneNumber</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.String> PHONENUMBER = createField("phoneNumber", org.jooq.impl.SQLDataType.CLOB.length(65535).nullable(false), this, "");

	/**
	 * The column <code>namedEntities.phoneNumbers.extension</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.String> EXTENSION = createField("extension", org.jooq.impl.SQLDataType.CLOB.length(65535), this, "");

	/**
	 * The column <code>namedEntities.phoneNumbers.sourceTypeId</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Integer> SOURCETYPEID = createField("sourceTypeId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.phoneNumbers.isPrimary</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Byte> ISPRIMARY = createField("isPrimary", org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.phoneNumbers.isActive</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, java.lang.Byte> ISACTIVE = createField("isActive", org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "");

	/**
	 * Create a <code>namedEntities.phoneNumbers</code> table reference
	 */
	public Phonenumbers() {
		this("phoneNumbers", null);
	}

	/**
	 * Create an aliased <code>namedEntities.phoneNumbers</code> table reference
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
		return org.plos.namedentity.persist.db.namedentities.Keys.KEY_PHONENUMBERS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord>>asList(org.plos.namedentity.persist.db.namedentities.Keys.KEY_PHONENUMBERS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.PhonenumbersRecord, ?>>asList(org.plos.namedentity.persist.db.namedentities.Keys.PHONENUMBERS_IBFK_1, org.plos.namedentity.persist.db.namedentities.Keys.PHONENUMBERS_IBFK_2, org.plos.namedentity.persist.db.namedentities.Keys.PHONENUMBERS_IBFK_4, org.plos.namedentity.persist.db.namedentities.Keys.PHONENUMBERS_IBFK_3);
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
