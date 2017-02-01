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
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.plos.namedentity.persist.db.namedentities.Keys;
import org.plos.namedentity.persist.db.namedentities.Namedentities;
import org.plos.namedentity.persist.db.namedentities.tables.records.DegreesRecord;


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
public class Degrees extends TableImpl<DegreesRecord> {

	private static final long serialVersionUID = 214402192;

	/**
	 * The reference instance of <code>namedEntities.degrees</code>
	 */
	public static final Degrees DEGREES = new Degrees();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<DegreesRecord> getRecordType() {
		return DegreesRecord.class;
	}

	/**
	 * The column <code>namedEntities.degrees.id</code>.
	 */
	public final TableField<DegreesRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.degrees.nedId</code>.
	 */
	public final TableField<DegreesRecord, Integer> NEDID = createField("nedId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.degrees.typeId</code>.
	 */
	public final TableField<DegreesRecord, Integer> TYPEID = createField("typeId", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedEntities.degrees.fulltitle</code>.
	 */
	public final TableField<DegreesRecord, String> FULLTITLE = createField("fulltitle", org.jooq.impl.SQLDataType.VARCHAR.length(250), this, "");

	/**
	 * The column <code>namedEntities.degrees.sourceTypeId</code>.
	 */
	public final TableField<DegreesRecord, Integer> SOURCETYPEID = createField("sourceTypeId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.degrees.created</code>.
	 */
	public final TableField<DegreesRecord, Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedEntities.degrees.createdBy</code>.
	 */
	public final TableField<DegreesRecord, Integer> CREATEDBY = createField("createdBy", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.degrees.lastModified</code>.
	 */
	public final TableField<DegreesRecord, Timestamp> LASTMODIFIED = createField("lastModified", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedEntities.degrees.lastModifiedBy</code>.
	 */
	public final TableField<DegreesRecord, Integer> LASTMODIFIEDBY = createField("lastModifiedBy", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>namedEntities.degrees</code> table reference
	 */
	public Degrees() {
		this("degrees", null);
	}

	/**
	 * Create an aliased <code>namedEntities.degrees</code> table reference
	 */
	public Degrees(String alias) {
		this(alias, DEGREES);
	}

	private Degrees(String alias, Table<DegreesRecord> aliased) {
		this(alias, aliased, null);
	}

	private Degrees(String alias, Table<DegreesRecord> aliased, Field<?>[] parameters) {
		super(alias, Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<DegreesRecord, Integer> getIdentity() {
		return Keys.IDENTITY_DEGREES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<DegreesRecord> getPrimaryKey() {
		return Keys.KEY_DEGREES_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<DegreesRecord>> getKeys() {
		return Arrays.<UniqueKey<DegreesRecord>>asList(Keys.KEY_DEGREES_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<DegreesRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<DegreesRecord, ?>>asList(Keys.DEGREES_IBFK_1, Keys.DEGREES_IBFK_3, Keys.DEGREES_IBFK_2, Keys.DEGREES_IBFK_4, Keys.DEGREES_IBFK_5);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Degrees as(String alias) {
		return new Degrees(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Degrees rename(String name) {
		return new Degrees(name, null);
	}
}
