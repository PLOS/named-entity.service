/**
 * This class is generated by jOOQ
 */
package org.plos.namedentity.persist.db.ringgold;

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
public class Ringgold extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = -1784038670;

	/**
	 * The reference instance of <code>RINGGOLD</code>
	 */
	public static final Ringgold RINGGOLD = new Ringgold();

	/**
	 * No further instances allowed
	 */
	private Ringgold() {
		super("RINGGOLD");
	}

	@Override
	public final java.util.List<org.jooq.Sequence<?>> getSequences() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getSequences0());
		return result;
	}

	private final java.util.List<org.jooq.Sequence<?>> getSequences0() {
		return java.util.Arrays.<org.jooq.Sequence<?>>asList(
			org.plos.namedentity.persist.db.ringgold.Sequences.SYSTEM_SEQUENCE_032F1AE8_5B74_4651_98F0_A30151FAE331,
			org.plos.namedentity.persist.db.ringgold.Sequences.SYSTEM_SEQUENCE_27C28552_F2DD_443C_A11C_4F66CF212A70,
			org.plos.namedentity.persist.db.ringgold.Sequences.SYSTEM_SEQUENCE_3E3502D3_1C0F_42B6_9D3D_6E9B9BCCF6E7,
			org.plos.namedentity.persist.db.ringgold.Sequences.SYSTEM_SEQUENCE_3FB4B3B9_BF2C_4E2B_8B5F_9CBBF901332A,
			org.plos.namedentity.persist.db.ringgold.Sequences.SYSTEM_SEQUENCE_55516962_4E24_438E_8D8C_1CF8E0ACB08D,
			org.plos.namedentity.persist.db.ringgold.Sequences.SYSTEM_SEQUENCE_631131FF_401A_44C9_BB3E_8DEFFA098268,
			org.plos.namedentity.persist.db.ringgold.Sequences.SYSTEM_SEQUENCE_68A1AAE3_4E4C_4AE4_8B7C_E9CAC10B6812,
			org.plos.namedentity.persist.db.ringgold.Sequences.SYSTEM_SEQUENCE_8F4E137C_1A8F_4F35_9539_2CEF33F76357,
			org.plos.namedentity.persist.db.ringgold.Sequences.SYSTEM_SEQUENCE_99393E2C_D3B1_45FD_A608_EF508B4AA79D,
			org.plos.namedentity.persist.db.ringgold.Sequences.SYSTEM_SEQUENCE_9A17708F_1EC2_4F8B_8A97_74837A89BA1B,
			org.plos.namedentity.persist.db.ringgold.Sequences.SYSTEM_SEQUENCE_A52A7244_7989_4A0E_9269_29EDEEF8F8DD,
			org.plos.namedentity.persist.db.ringgold.Sequences.SYSTEM_SEQUENCE_AB0D2747_B5CB_4AE1_8D42_F1B0E188E355);
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final java.util.List<org.jooq.Table<?>> getTables0() {
		return java.util.Arrays.<org.jooq.Table<?>>asList(
			org.plos.namedentity.persist.db.ringgold.tables.AltName.ALT_NAME,
			org.plos.namedentity.persist.db.ringgold.tables.Consortia.CONSORTIA,
			org.plos.namedentity.persist.db.ringgold.tables.DeletedId.DELETED_ID,
			org.plos.namedentity.persist.db.ringgold.tables.Identifiers.IDENTIFIERS,
			org.plos.namedentity.persist.db.ringgold.tables.Multi.MULTI,
			org.plos.namedentity.persist.db.ringgold.tables.Notes.NOTES,
			org.plos.namedentity.persist.db.ringgold.tables.Parents.PARENTS,
			org.plos.namedentity.persist.db.ringgold.tables.Sizes.SIZES,
			org.plos.namedentity.persist.db.ringgold.tables.Tiers.TIERS,
			org.plos.namedentity.persist.db.ringgold.tables.Types.TYPES,
			org.plos.namedentity.persist.db.ringgold.tables.RgTiers.RG_TIERS,
			org.plos.namedentity.persist.db.ringgold.tables.Url.URL);
	}
}
