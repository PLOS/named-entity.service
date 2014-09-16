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
public class Urls extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord> {

	private static final long serialVersionUID = 701324841;

	/**
	 * The singleton instance of <code>namedEntities.urls</code>
	 */
	public static final org.plos.namedentity.persist.db.namedentities.tables.Urls URLS = new org.plos.namedentity.persist.db.namedentities.tables.Urls();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord> getRecordType() {
		return org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord.class;
	}

	/**
	 * The column <code>namedEntities.urls.id</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.urls.nedId</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord, java.lang.Integer> NEDID = createField("nedId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.urls.url</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord, java.lang.String> URL = createField("url", org.jooq.impl.SQLDataType.CLOB.length(65535).nullable(false), this, "");

	/**
	 * The column <code>namedEntities.urls.sourceTypeId</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord, java.lang.Integer> SOURCETYPEID = createField("sourceTypeId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>namedEntities.urls</code> table reference
	 */
	public Urls() {
		this("urls", null);
	}

	/**
	 * Create an aliased <code>namedEntities.urls</code> table reference
	 */
	public Urls(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.namedentities.tables.Urls.URLS);
	}

	private Urls(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Urls(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.namedentities.Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord, java.lang.Integer> getIdentity() {
		return org.plos.namedentity.persist.db.namedentities.Keys.IDENTITY_URLS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord> getPrimaryKey() {
		return org.plos.namedentity.persist.db.namedentities.Keys.KEY_URLS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord>>asList(org.plos.namedentity.persist.db.namedentities.Keys.KEY_URLS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.UrlsRecord, ?>>asList(org.plos.namedentity.persist.db.namedentities.Keys.URLS_IBFK_2, org.plos.namedentity.persist.db.namedentities.Keys.URLS_IBFK_1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.namedentities.tables.Urls as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Urls(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.namedentities.tables.Urls rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Urls(name, null);
	}
}
