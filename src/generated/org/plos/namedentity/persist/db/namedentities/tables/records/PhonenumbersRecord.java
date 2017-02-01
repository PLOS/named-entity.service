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
package org.plos.namedentity.persist.db.namedentities.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;
import org.plos.namedentity.persist.db.namedentities.tables.Phonenumbers;


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
public class PhonenumbersRecord extends UpdatableRecordImpl<PhonenumbersRecord> implements Record12<Integer, Integer, Integer, Integer, String, String, Integer, Byte, Timestamp, Integer, Timestamp, Integer> {

	private static final long serialVersionUID = 499379095;

	/**
	 * Setter for <code>namedEntities.phoneNumbers.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>namedEntities.phoneNumbers.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>namedEntities.phoneNumbers.nedId</code>.
	 */
	public void setNedid(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>namedEntities.phoneNumbers.nedId</code>.
	 */
	public Integer getNedid() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>namedEntities.phoneNumbers.typeId</code>.
	 */
	public void setTypeid(Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>namedEntities.phoneNumbers.typeId</code>.
	 */
	public Integer getTypeid() {
		return (Integer) getValue(2);
	}

	/**
	 * Setter for <code>namedEntities.phoneNumbers.countryCodeTypeId</code>.
	 */
	public void setCountrycodetypeid(Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>namedEntities.phoneNumbers.countryCodeTypeId</code>.
	 */
	public Integer getCountrycodetypeid() {
		return (Integer) getValue(3);
	}

	/**
	 * Setter for <code>namedEntities.phoneNumbers.phoneNumber</code>.
	 */
	public void setPhonenumber(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>namedEntities.phoneNumbers.phoneNumber</code>.
	 */
	public String getPhonenumber() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>namedEntities.phoneNumbers.extension</code>.
	 */
	public void setExtension(String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>namedEntities.phoneNumbers.extension</code>.
	 */
	public String getExtension() {
		return (String) getValue(5);
	}

	/**
	 * Setter for <code>namedEntities.phoneNumbers.sourceTypeId</code>.
	 */
	public void setSourcetypeid(Integer value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>namedEntities.phoneNumbers.sourceTypeId</code>.
	 */
	public Integer getSourcetypeid() {
		return (Integer) getValue(6);
	}

	/**
	 * Setter for <code>namedEntities.phoneNumbers.isActive</code>.
	 */
	public void setIsactive(Byte value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>namedEntities.phoneNumbers.isActive</code>.
	 */
	public Byte getIsactive() {
		return (Byte) getValue(7);
	}

	/**
	 * Setter for <code>namedEntities.phoneNumbers.created</code>.
	 */
	public void setCreated(Timestamp value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>namedEntities.phoneNumbers.created</code>.
	 */
	public Timestamp getCreated() {
		return (Timestamp) getValue(8);
	}

	/**
	 * Setter for <code>namedEntities.phoneNumbers.createdBy</code>.
	 */
	public void setCreatedby(Integer value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>namedEntities.phoneNumbers.createdBy</code>.
	 */
	public Integer getCreatedby() {
		return (Integer) getValue(9);
	}

	/**
	 * Setter for <code>namedEntities.phoneNumbers.lastModified</code>.
	 */
	public void setLastmodified(Timestamp value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>namedEntities.phoneNumbers.lastModified</code>.
	 */
	public Timestamp getLastmodified() {
		return (Timestamp) getValue(10);
	}

	/**
	 * Setter for <code>namedEntities.phoneNumbers.lastModifiedBy</code>.
	 */
	public void setLastmodifiedby(Integer value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>namedEntities.phoneNumbers.lastModifiedBy</code>.
	 */
	public Integer getLastmodifiedby() {
		return (Integer) getValue(11);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record12 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row12<Integer, Integer, Integer, Integer, String, String, Integer, Byte, Timestamp, Integer, Timestamp, Integer> fieldsRow() {
		return (Row12) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row12<Integer, Integer, Integer, Integer, String, String, Integer, Byte, Timestamp, Integer, Timestamp, Integer> valuesRow() {
		return (Row12) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Phonenumbers.PHONENUMBERS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return Phonenumbers.PHONENUMBERS.NEDID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field3() {
		return Phonenumbers.PHONENUMBERS.TYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field4() {
		return Phonenumbers.PHONENUMBERS.COUNTRYCODETYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return Phonenumbers.PHONENUMBERS.PHONENUMBER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field6() {
		return Phonenumbers.PHONENUMBERS.EXTENSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field7() {
		return Phonenumbers.PHONENUMBERS.SOURCETYPEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Byte> field8() {
		return Phonenumbers.PHONENUMBERS.ISACTIVE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field9() {
		return Phonenumbers.PHONENUMBERS.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field10() {
		return Phonenumbers.PHONENUMBERS.CREATEDBY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field11() {
		return Phonenumbers.PHONENUMBERS.LASTMODIFIED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field12() {
		return Phonenumbers.PHONENUMBERS.LASTMODIFIEDBY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getNedid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value3() {
		return getTypeid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value4() {
		return getCountrycodetypeid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getPhonenumber();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value6() {
		return getExtension();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value7() {
		return getSourcetypeid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Byte value8() {
		return getIsactive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value9() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value10() {
		return getCreatedby();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value11() {
		return getLastmodified();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value12() {
		return getLastmodifiedby();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord value2(Integer value) {
		setNedid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord value3(Integer value) {
		setTypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord value4(Integer value) {
		setCountrycodetypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord value5(String value) {
		setPhonenumber(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord value6(String value) {
		setExtension(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord value7(Integer value) {
		setSourcetypeid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord value8(Byte value) {
		setIsactive(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord value9(Timestamp value) {
		setCreated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord value10(Integer value) {
		setCreatedby(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord value11(Timestamp value) {
		setLastmodified(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord value12(Integer value) {
		setLastmodifiedby(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhonenumbersRecord values(Integer value1, Integer value2, Integer value3, Integer value4, String value5, String value6, Integer value7, Byte value8, Timestamp value9, Integer value10, Timestamp value11, Integer value12) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		value10(value10);
		value11(value11);
		value12(value12);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached PhonenumbersRecord
	 */
	public PhonenumbersRecord() {
		super(Phonenumbers.PHONENUMBERS);
	}

	/**
	 * Create a detached, initialised PhonenumbersRecord
	 */
	public PhonenumbersRecord(Integer id, Integer nedid, Integer typeid, Integer countrycodetypeid, String phonenumber, String extension, Integer sourcetypeid, Byte isactive, Timestamp created, Integer createdby, Timestamp lastmodified, Integer lastmodifiedby) {
		super(Phonenumbers.PHONENUMBERS);

		setValue(0, id);
		setValue(1, nedid);
		setValue(2, typeid);
		setValue(3, countrycodetypeid);
		setValue(4, phonenumber);
		setValue(5, extension);
		setValue(6, sourcetypeid);
		setValue(7, isactive);
		setValue(8, created);
		setValue(9, createdby);
		setValue(10, lastmodified);
		setValue(11, lastmodifiedby);
	}
}
