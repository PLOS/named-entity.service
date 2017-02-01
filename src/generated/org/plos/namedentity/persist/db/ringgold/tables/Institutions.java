/*
 * Copyright (c) 2017 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

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
import org.plos.namedentity.persist.db.ringgold.tables.records.InstitutionsRecord;


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
public class Institutions extends TableImpl<InstitutionsRecord> {

	private static final long serialVersionUID = -453991290;

	/**
	 * The reference instance of <code>ringgold.institutions</code>
	 */
	public static final Institutions INSTITUTIONS = new Institutions();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<InstitutionsRecord> getRecordType() {
		return InstitutionsRecord.class;
	}

	/**
	 * The column <code>ringgold.institutions.rec_id</code>.
	 */
	public final TableField<InstitutionsRecord, Integer> REC_ID = createField("rec_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>ringgold.institutions.parent_ringgold_id</code>.
	 */
	public final TableField<InstitutionsRecord, Integer> PARENT_RINGGOLD_ID = createField("parent_ringgold_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.institutions.ringgold_id</code>.
	 */
	public final TableField<InstitutionsRecord, Integer> RINGGOLD_ID = createField("ringgold_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.institutions.top_ringgold_id</code>.
	 */
	public final TableField<InstitutionsRecord, Integer> TOP_RINGGOLD_ID = createField("top_ringgold_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.institutions.name</code>.
	 */
	public final TableField<InstitutionsRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.institutions.city</code>.
	 */
	public final TableField<InstitutionsRecord, String> CITY = createField("city", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.institutions.post_code</code>.
	 */
	public final TableField<InstitutionsRecord, String> POST_CODE = createField("post_code", org.jooq.impl.SQLDataType.VARCHAR.length(30), this, "");

	/**
	 * The column <code>ringgold.institutions.country</code>.
	 */
	public final TableField<InstitutionsRecord, String> COUNTRY = createField("country", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.institutions.state</code>.
	 */
	public final TableField<InstitutionsRecord, String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(25), this, "");

	/**
	 * The column <code>ringgold.institutions.type</code>.
	 */
	public final TableField<InstitutionsRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR.length(35).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>ringgold.institutions.timestamp</code>.
	 */
	public final TableField<InstitutionsRecord, Timestamp> TIMESTAMP = createField("timestamp", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>ringgold.institutions</code> table reference
	 */
	public Institutions() {
		this("institutions", null);
	}

	/**
	 * Create an aliased <code>ringgold.institutions</code> table reference
	 */
	public Institutions(String alias) {
		this(alias, INSTITUTIONS);
	}

	private Institutions(String alias, Table<InstitutionsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Institutions(String alias, Table<InstitutionsRecord> aliased, Field<?>[] parameters) {
		super(alias, Ringgold.RINGGOLD, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<InstitutionsRecord, Integer> getIdentity() {
		return Keys.IDENTITY_INSTITUTIONS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<InstitutionsRecord> getPrimaryKey() {
		return Keys.KEY_INSTITUTIONS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<InstitutionsRecord>> getKeys() {
		return Arrays.<UniqueKey<InstitutionsRecord>>asList(Keys.KEY_INSTITUTIONS_PRIMARY, Keys.KEY_INSTITUTIONS_RINGGOLD_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Institutions as(String alias) {
		return new Institutions(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Institutions rename(String name) {
		return new Institutions(name, null);
	}
}
