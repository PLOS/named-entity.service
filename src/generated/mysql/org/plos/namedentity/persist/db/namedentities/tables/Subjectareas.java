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
public class Subjectareas extends org.jooq.impl.TableImpl<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord> {

	private static final long serialVersionUID = 2118063050;

	/**
	 * The singleton instance of <code>namedEntities.subjectAreas</code>
	 */
	public static final org.plos.namedentity.persist.db.namedentities.tables.Subjectareas SUBJECTAREAS = new org.plos.namedentity.persist.db.namedentities.tables.Subjectareas();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord> getRecordType() {
		return org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord.class;
	}

	/**
	 * The column <code>namedEntities.subjectAreas.subjectAreaId</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord, java.lang.Integer> SUBJECTAREAID = createField("subjectAreaId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.subjectAreas.namedEntityId</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord, java.lang.Integer> NAMEDENTITYID = createField("namedEntityId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.subjectAreas.subjectAreaTypeId</code>.
	 */
	public final org.jooq.TableField<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord, java.lang.Integer> SUBJECTAREATYPEID = createField("subjectAreaTypeId", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>namedEntities.subjectAreas</code> table reference
	 */
	public Subjectareas() {
		this("subjectAreas", null);
	}

	/**
	 * Create an aliased <code>namedEntities.subjectAreas</code> table reference
	 */
	public Subjectareas(java.lang.String alias) {
		this(alias, org.plos.namedentity.persist.db.namedentities.tables.Subjectareas.SUBJECTAREAS);
	}

	private Subjectareas(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord> aliased) {
		this(alias, aliased, null);
	}

	private Subjectareas(java.lang.String alias, org.jooq.Table<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.plos.namedentity.persist.db.namedentities.Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord, java.lang.Integer> getIdentity() {
		return org.plos.namedentity.persist.db.namedentities.Keys.IDENTITY_SUBJECTAREAS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord> getPrimaryKey() {
		return org.plos.namedentity.persist.db.namedentities.Keys.KEY_SUBJECTAREAS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord>>asList(org.plos.namedentity.persist.db.namedentities.Keys.KEY_SUBJECTAREAS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.plos.namedentity.persist.db.namedentities.tables.records.SubjectareasRecord, ?>>asList(org.plos.namedentity.persist.db.namedentities.Keys.SUBJECTAREAS_IBFK_1, org.plos.namedentity.persist.db.namedentities.Keys.SUBJECTAREAS_IBFK_2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.plos.namedentity.persist.db.namedentities.tables.Subjectareas as(java.lang.String alias) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Subjectareas(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.plos.namedentity.persist.db.namedentities.tables.Subjectareas rename(java.lang.String name) {
		return new org.plos.namedentity.persist.db.namedentities.tables.Subjectareas(name, null);
	}
}