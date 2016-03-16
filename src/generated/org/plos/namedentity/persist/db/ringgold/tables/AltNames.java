/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.ringgold.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.plos.namedentity.persist.db.ringgold.Keys;
import org.plos.namedentity.persist.db.ringgold.Ringgold;
import org.plos.namedentity.persist.db.ringgold.tables.records.AltNamesRecord;


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
public class AltNames extends TableImpl<AltNamesRecord> {

	private static final long serialVersionUID = 1585954265;

	/**
	 * The reference instance of <code>ringgold.alt_names</code>
	 */
	public static final AltNames ALT_NAMES = new AltNames();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<AltNamesRecord> getRecordType() {
		return AltNamesRecord.class;
	}

	/**
	 * The column <code>ringgold.alt_names.rec_id</code>.
	 */
	public final TableField<AltNamesRecord, Integer> REC_ID = createField("rec_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>ringgold.alt_names.ringgold_id</code>.
	 */
	public final TableField<AltNamesRecord, Integer> RINGGOLD_ID = createField("ringgold_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.alt_names.name</code>.
	 */
	public final TableField<AltNamesRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.alt_names.city</code>.
	 */
	public final TableField<AltNamesRecord, String> CITY = createField("city", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

	/**
	 * The column <code>ringgold.alt_names.country</code>.
	 */
	public final TableField<AltNamesRecord, String> COUNTRY = createField("country", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.alt_names.language</code>.
	 */
	public final TableField<AltNamesRecord, String> LANGUAGE = createField("language", org.jooq.impl.SQLDataType.VARCHAR.length(35), this, "");

	/**
	 * The column <code>ringgold.alt_names.notes</code>.
	 */
	public final TableField<AltNamesRecord, String> NOTES = createField("notes", org.jooq.impl.SQLDataType.VARCHAR.length(60), this, "");

	/**
	 * The column <code>ringgold.alt_names.timestamp</code>.
	 */
	public final TableField<AltNamesRecord, Timestamp> TIMESTAMP = createField("timestamp", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>ringgold.alt_names</code> table reference
	 */
	public AltNames() {
		this("alt_names", null);
	}

	/**
	 * Create an aliased <code>ringgold.alt_names</code> table reference
	 */
	public AltNames(String alias) {
		this(alias, ALT_NAMES);
	}

	private AltNames(String alias, Table<AltNamesRecord> aliased) {
		this(alias, aliased, null);
	}

	private AltNames(String alias, Table<AltNamesRecord> aliased, Field<?>[] parameters) {
		super(alias, Ringgold.RINGGOLD, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<AltNamesRecord, Integer> getIdentity() {
		return Keys.IDENTITY_ALT_NAMES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<AltNamesRecord> getPrimaryKey() {
		return Keys.KEY_ALT_NAMES_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<AltNamesRecord>> getKeys() {
		return Arrays.<UniqueKey<AltNamesRecord>>asList(Keys.KEY_ALT_NAMES_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AltNames as(String alias) {
		return new AltNames(alias, this);
	}

	/**
	 * Rename this table
	 */
	public AltNames rename(String name) {
		return new AltNames(name, null);
	}
}
