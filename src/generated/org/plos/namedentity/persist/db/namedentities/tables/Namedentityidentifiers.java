/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.namedentities.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.plos.namedentity.persist.db.namedentities.Keys;
import org.plos.namedentity.persist.db.namedentities.Namedentities;
import org.plos.namedentity.persist.db.namedentities.tables.records.NamedentityidentifiersRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.3"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Namedentityidentifiers extends TableImpl<NamedentityidentifiersRecord> {

	private static final long serialVersionUID = -401235731;

	/**
	 * The reference instance of <code>namedEntities.namedEntityIdentifiers</code>
	 */
	public static final Namedentityidentifiers NAMEDENTITYIDENTIFIERS = new Namedentityidentifiers();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<NamedentityidentifiersRecord> getRecordType() {
		return NamedentityidentifiersRecord.class;
	}

	/**
	 * The column <code>namedEntities.namedEntityIdentifiers.id</code>.
	 */
	public final TableField<NamedentityidentifiersRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.namedEntityIdentifiers.typeId</code>.
	 */
	public final TableField<NamedentityidentifiersRecord, Integer> TYPEID = createField("typeId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.namedEntityIdentifiers.created</code>.
	 */
	public final TableField<NamedentityidentifiersRecord, Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedEntities.namedEntityIdentifiers.lastModified</code>.
	 */
	public final TableField<NamedentityidentifiersRecord, Timestamp> LASTMODIFIED = createField("lastModified", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedEntities.namedEntityIdentifiers.createdBy</code>.
	 */
	public final TableField<NamedentityidentifiersRecord, Integer> CREATEDBY = createField("createdBy", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedEntities.namedEntityIdentifiers.lastModifiedBy</code>.
	 */
	public final TableField<NamedentityidentifiersRecord, Integer> LASTMODIFIEDBY = createField("lastModifiedBy", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>namedEntities.namedEntityIdentifiers</code> table reference
	 */
	public Namedentityidentifiers() {
		this("namedEntityIdentifiers", null);
	}

	/**
	 * Create an aliased <code>namedEntities.namedEntityIdentifiers</code> table reference
	 */
	public Namedentityidentifiers(String alias) {
		this(alias, NAMEDENTITYIDENTIFIERS);
	}

	private Namedentityidentifiers(String alias, Table<NamedentityidentifiersRecord> aliased) {
		this(alias, aliased, null);
	}

	private Namedentityidentifiers(String alias, Table<NamedentityidentifiersRecord> aliased, Field<?>[] parameters) {
		super(alias, Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<NamedentityidentifiersRecord, Integer> getIdentity() {
		return Keys.IDENTITY_NAMEDENTITYIDENTIFIERS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<NamedentityidentifiersRecord> getPrimaryKey() {
		return Keys.KEY_NAMEDENTITYIDENTIFIERS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<NamedentityidentifiersRecord>> getKeys() {
		return Arrays.<UniqueKey<NamedentityidentifiersRecord>>asList(Keys.KEY_NAMEDENTITYIDENTIFIERS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<NamedentityidentifiersRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<NamedentityidentifiersRecord, ?>>asList(Keys.NAMEDENTITYIDENTIFIERS_IBFK_1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Namedentityidentifiers as(String alias) {
		return new Namedentityidentifiers(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Namedentityidentifiers rename(String name) {
		return new Namedentityidentifiers(name, null);
	}
}
