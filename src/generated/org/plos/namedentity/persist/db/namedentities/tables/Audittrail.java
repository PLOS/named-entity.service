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
import org.plos.namedentity.persist.db.namedentities.tables.records.AudittrailRecord;


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
public class Audittrail extends TableImpl<AudittrailRecord> {

	private static final long serialVersionUID = 325608559;

	/**
	 * The reference instance of <code>namedEntities.auditTrail</code>
	 */
	public static final Audittrail AUDITTRAIL = new Audittrail();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<AudittrailRecord> getRecordType() {
		return AudittrailRecord.class;
	}

	/**
	 * The column <code>namedEntities.auditTrail.id</code>.
	 */
	public final TableField<AudittrailRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.auditTrail.sourceFieldId</code>.
	 */
	public final TableField<AudittrailRecord, Integer> SOURCEFIELDID = createField("sourceFieldId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.auditTrail.rowNumber</code>.
	 */
	public final TableField<AudittrailRecord, Integer> ROWNUMBER = createField("rowNumber", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedEntities.auditTrail.oldValue</code>.
	 */
	public final TableField<AudittrailRecord, String> OLDVALUE = createField("oldValue", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.auditTrail.newValue</code>.
	 */
	public final TableField<AudittrailRecord, String> NEWVALUE = createField("newValue", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * The column <code>namedEntities.auditTrail.created</code>.
	 */
	public final TableField<AudittrailRecord, Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedEntities.auditTrail.createdBy</code>.
	 */
	public final TableField<AudittrailRecord, Integer> CREATEDBY = createField("createdBy", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>namedEntities.auditTrail.lastModified</code>.
	 */
	public final TableField<AudittrailRecord, Timestamp> LASTMODIFIED = createField("lastModified", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>namedEntities.auditTrail.lastModifiedBy</code>.
	 */
	public final TableField<AudittrailRecord, Integer> LASTMODIFIEDBY = createField("lastModifiedBy", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>namedEntities.auditTrail</code> table reference
	 */
	public Audittrail() {
		this("auditTrail", null);
	}

	/**
	 * Create an aliased <code>namedEntities.auditTrail</code> table reference
	 */
	public Audittrail(String alias) {
		this(alias, AUDITTRAIL);
	}

	private Audittrail(String alias, Table<AudittrailRecord> aliased) {
		this(alias, aliased, null);
	}

	private Audittrail(String alias, Table<AudittrailRecord> aliased, Field<?>[] parameters) {
		super(alias, Namedentities.NAMEDENTITIES, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<AudittrailRecord, Integer> getIdentity() {
		return Keys.IDENTITY_AUDITTRAIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<AudittrailRecord> getPrimaryKey() {
		return Keys.KEY_AUDITTRAIL_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<AudittrailRecord>> getKeys() {
		return Arrays.<UniqueKey<AudittrailRecord>>asList(Keys.KEY_AUDITTRAIL_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<AudittrailRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<AudittrailRecord, ?>>asList(Keys.AUDITTRAIL_IBFK_1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Audittrail as(String alias) {
		return new Audittrail(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Audittrail rename(String name) {
		return new Audittrail(name, null);
	}
}
