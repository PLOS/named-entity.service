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
package org.plos.namedentity.persist.db.namedentities.tables;


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
import org.plos.namedentity.persist.db.namedentities.Keys;
import org.plos.namedentity.persist.db.namedentities.Namedentities;
import org.plos.namedentity.persist.db.namedentities.tables.records.ConsumersRecord;


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
public class Consumers extends TableImpl<ConsumersRecord> {

	private static final long serialVersionUID = -676446261;

	/**
	 * The reference instance of <code>namedEntities.consumers</code>
	 */
	public static final Consumers CONSUMERS = new Consumers();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<ConsumersRecord> getRecordType() {
		return ConsumersRecord.class;
	}

	/**
	 * The column <code>namedEntities.consumers.id</code>.
	 */
	public final TableField<ConsumersRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.consumers.name</code>.
	 */
	public final TableField<ConsumersRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>namedEntities.consumers.password</code>.
	 */
	public final TableField<ConsumersRecord, String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>namedEntities.consumers.created</code>.
	 */
	public final TableField<ConsumersRecord, Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedEntities.consumers.lastModified</code>.
	 */
	public final TableField<ConsumersRecord, Timestamp> LASTMODIFIED = createField("lastModified", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>namedEntities.consumers</code> table reference
	 */
	public Consumers() {
		this("consumers", null);
	}

	/**
	 * Create an aliased <code>namedEntities.consumers</code> table reference
	 */
	public Consumers(String alias) {
		this(alias, CONSUMERS);
	}

	private Consumers(String alias, Table<ConsumersRecord> aliased) {
		this(alias, aliased, null);
	}

	private Consumers(String alias, Table<ConsumersRecord> aliased, Field<?>[] parameters) {
		super(alias, Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<ConsumersRecord, Integer> getIdentity() {
		return Keys.IDENTITY_CONSUMERS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<ConsumersRecord> getPrimaryKey() {
		return Keys.KEY_CONSUMERS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<ConsumersRecord>> getKeys() {
		return Arrays.<UniqueKey<ConsumersRecord>>asList(Keys.KEY_CONSUMERS_PRIMARY, Keys.KEY_CONSUMERS_NAME);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Consumers as(String alias) {
		return new Consumers(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Consumers rename(String name) {
		return new Consumers(name, null);
	}
}
