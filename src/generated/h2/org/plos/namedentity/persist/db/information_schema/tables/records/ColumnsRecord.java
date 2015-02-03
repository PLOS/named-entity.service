/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.information_schema.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.1"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ColumnsRecord extends org.jooq.impl.TableRecordImpl<org.plos.namedentity.persist.db.information_schema.tables.records.ColumnsRecord> {

	private static final long serialVersionUID = 189992578;

	/**
	 * Setter for <code>information_schema.columns.table_catalog</code>.
	 */
	public void setTableCatalog(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>information_schema.columns.table_catalog</code>.
	 */
	public java.lang.String getTableCatalog() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>information_schema.columns.table_schema</code>.
	 */
	public void setTableSchema(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>information_schema.columns.table_schema</code>.
	 */
	public java.lang.String getTableSchema() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>information_schema.columns.table_name</code>.
	 */
	public void setTableName(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>information_schema.columns.table_name</code>.
	 */
	public java.lang.String getTableName() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>information_schema.columns.column_name</code>.
	 */
	public void setColumnName(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>information_schema.columns.column_name</code>.
	 */
	public java.lang.String getColumnName() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>information_schema.columns.ordinal_position</code>.
	 */
	public void setOrdinalPosition(java.lang.Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>information_schema.columns.ordinal_position</code>.
	 */
	public java.lang.Integer getOrdinalPosition() {
		return (java.lang.Integer) getValue(4);
	}

	/**
	 * Setter for <code>information_schema.columns.column_default</code>.
	 */
	public void setColumnDefault(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>information_schema.columns.column_default</code>.
	 */
	public java.lang.String getColumnDefault() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>information_schema.columns.is_nullable</code>.
	 */
	public void setIsNullable(java.lang.String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>information_schema.columns.is_nullable</code>.
	 */
	public java.lang.String getIsNullable() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>information_schema.columns.data_type</code>.
	 */
	public void setDataType(java.lang.Integer value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>information_schema.columns.data_type</code>.
	 */
	public java.lang.Integer getDataType() {
		return (java.lang.Integer) getValue(7);
	}

	/**
	 * Setter for <code>information_schema.columns.character_maximum_length</code>.
	 */
	public void setCharacterMaximumLength(java.lang.Integer value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>information_schema.columns.character_maximum_length</code>.
	 */
	public java.lang.Integer getCharacterMaximumLength() {
		return (java.lang.Integer) getValue(8);
	}

	/**
	 * Setter for <code>information_schema.columns.character_octet_length</code>.
	 */
	public void setCharacterOctetLength(java.lang.Integer value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>information_schema.columns.character_octet_length</code>.
	 */
	public java.lang.Integer getCharacterOctetLength() {
		return (java.lang.Integer) getValue(9);
	}

	/**
	 * Setter for <code>information_schema.columns.numeric_precision</code>.
	 */
	public void setNumericPrecision(java.lang.Integer value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>information_schema.columns.numeric_precision</code>.
	 */
	public java.lang.Integer getNumericPrecision() {
		return (java.lang.Integer) getValue(10);
	}

	/**
	 * Setter for <code>information_schema.columns.numeric_precision_radix</code>.
	 */
	public void setNumericPrecisionRadix(java.lang.Integer value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>information_schema.columns.numeric_precision_radix</code>.
	 */
	public java.lang.Integer getNumericPrecisionRadix() {
		return (java.lang.Integer) getValue(11);
	}

	/**
	 * Setter for <code>information_schema.columns.numeric_scale</code>.
	 */
	public void setNumericScale(java.lang.Integer value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>information_schema.columns.numeric_scale</code>.
	 */
	public java.lang.Integer getNumericScale() {
		return (java.lang.Integer) getValue(12);
	}

	/**
	 * Setter for <code>information_schema.columns.character_set_name</code>.
	 */
	public void setCharacterSetName(java.lang.String value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>information_schema.columns.character_set_name</code>.
	 */
	public java.lang.String getCharacterSetName() {
		return (java.lang.String) getValue(13);
	}

	/**
	 * Setter for <code>information_schema.columns.collation_name</code>.
	 */
	public void setCollationName(java.lang.String value) {
		setValue(14, value);
	}

	/**
	 * Getter for <code>information_schema.columns.collation_name</code>.
	 */
	public java.lang.String getCollationName() {
		return (java.lang.String) getValue(14);
	}

	/**
	 * Setter for <code>information_schema.columns.type_name</code>.
	 */
	public void setTypeName(java.lang.String value) {
		setValue(15, value);
	}

	/**
	 * Getter for <code>information_schema.columns.type_name</code>.
	 */
	public java.lang.String getTypeName() {
		return (java.lang.String) getValue(15);
	}

	/**
	 * Setter for <code>information_schema.columns.nullable</code>.
	 */
	public void setNullable(java.lang.Integer value) {
		setValue(16, value);
	}

	/**
	 * Getter for <code>information_schema.columns.nullable</code>.
	 */
	public java.lang.Integer getNullable() {
		return (java.lang.Integer) getValue(16);
	}

	/**
	 * Setter for <code>information_schema.columns.is_computed</code>.
	 */
	public void setIsComputed(java.lang.Boolean value) {
		setValue(17, value);
	}

	/**
	 * Getter for <code>information_schema.columns.is_computed</code>.
	 */
	public java.lang.Boolean getIsComputed() {
		return (java.lang.Boolean) getValue(17);
	}

	/**
	 * Setter for <code>information_schema.columns.selectivity</code>.
	 */
	public void setSelectivity(java.lang.Integer value) {
		setValue(18, value);
	}

	/**
	 * Getter for <code>information_schema.columns.selectivity</code>.
	 */
	public java.lang.Integer getSelectivity() {
		return (java.lang.Integer) getValue(18);
	}

	/**
	 * Setter for <code>information_schema.columns.check_constraint</code>.
	 */
	public void setCheckConstraint(java.lang.String value) {
		setValue(19, value);
	}

	/**
	 * Getter for <code>information_schema.columns.check_constraint</code>.
	 */
	public java.lang.String getCheckConstraint() {
		return (java.lang.String) getValue(19);
	}

	/**
	 * Setter for <code>information_schema.columns.sequence_name</code>.
	 */
	public void setSequenceName(java.lang.String value) {
		setValue(20, value);
	}

	/**
	 * Getter for <code>information_schema.columns.sequence_name</code>.
	 */
	public java.lang.String getSequenceName() {
		return (java.lang.String) getValue(20);
	}

	/**
	 * Setter for <code>information_schema.columns.remarks</code>.
	 */
	public void setRemarks(java.lang.String value) {
		setValue(21, value);
	}

	/**
	 * Getter for <code>information_schema.columns.remarks</code>.
	 */
	public java.lang.String getRemarks() {
		return (java.lang.String) getValue(21);
	}

	/**
	 * Setter for <code>information_schema.columns.source_data_type</code>.
	 */
	public void setSourceDataType(java.lang.Short value) {
		setValue(22, value);
	}

	/**
	 * Getter for <code>information_schema.columns.source_data_type</code>.
	 */
	public java.lang.Short getSourceDataType() {
		return (java.lang.Short) getValue(22);
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ColumnsRecord
	 */
	public ColumnsRecord() {
		super(org.plos.namedentity.persist.db.information_schema.tables.Columns.COLUMNS);
	}

	/**
	 * Create a detached, initialised ColumnsRecord
	 */
	public ColumnsRecord(java.lang.String tableCatalog, java.lang.String tableSchema, java.lang.String tableName, java.lang.String columnName, java.lang.Integer ordinalPosition, java.lang.String columnDefault, java.lang.String isNullable, java.lang.Integer dataType, java.lang.Integer characterMaximumLength, java.lang.Integer characterOctetLength, java.lang.Integer numericPrecision, java.lang.Integer numericPrecisionRadix, java.lang.Integer numericScale, java.lang.String characterSetName, java.lang.String collationName, java.lang.String typeName, java.lang.Integer nullable, java.lang.Boolean isComputed, java.lang.Integer selectivity, java.lang.String checkConstraint, java.lang.String sequenceName, java.lang.String remarks, java.lang.Short sourceDataType) {
		super(org.plos.namedentity.persist.db.information_schema.tables.Columns.COLUMNS);

		setValue(0, tableCatalog);
		setValue(1, tableSchema);
		setValue(2, tableName);
		setValue(3, columnName);
		setValue(4, ordinalPosition);
		setValue(5, columnDefault);
		setValue(6, isNullable);
		setValue(7, dataType);
		setValue(8, characterMaximumLength);
		setValue(9, characterOctetLength);
		setValue(10, numericPrecision);
		setValue(11, numericPrecisionRadix);
		setValue(12, numericScale);
		setValue(13, characterSetName);
		setValue(14, collationName);
		setValue(15, typeName);
		setValue(16, nullable);
		setValue(17, isComputed);
		setValue(18, selectivity);
		setValue(19, checkConstraint);
		setValue(20, sequenceName);
		setValue(21, remarks);
		setValue(22, sourceDataType);
	}
}
