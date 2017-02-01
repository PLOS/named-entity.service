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
package org.plos.namedentity.persist.db.ringgold;


import javax.annotation.Generated;

import org.plos.namedentity.persist.db.ringgold.tables.AltNames;
import org.plos.namedentity.persist.db.ringgold.tables.Consortia;
import org.plos.namedentity.persist.db.ringgold.tables.DeletedIds;
import org.plos.namedentity.persist.db.ringgold.tables.Identifiers;
import org.plos.namedentity.persist.db.ringgold.tables.Institutions;
import org.plos.namedentity.persist.db.ringgold.tables.Multies;
import org.plos.namedentity.persist.db.ringgold.tables.Notes;
import org.plos.namedentity.persist.db.ringgold.tables.Sizes;
import org.plos.namedentity.persist.db.ringgold.tables.Taxonomy;
import org.plos.namedentity.persist.db.ringgold.tables.Tiers;
import org.plos.namedentity.persist.db.ringgold.tables.Urls;


/**
 * Convenience access to all tables in ringgold
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.3"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

	/**
	 * The table ringgold.alt_names
	 */
	public static final AltNames ALT_NAMES = org.plos.namedentity.persist.db.ringgold.tables.AltNames.ALT_NAMES;

	/**
	 * The table ringgold.consortia
	 */
	public static final Consortia CONSORTIA = org.plos.namedentity.persist.db.ringgold.tables.Consortia.CONSORTIA;

	/**
	 * The table ringgold.deleted_ids
	 */
	public static final DeletedIds DELETED_IDS = org.plos.namedentity.persist.db.ringgold.tables.DeletedIds.DELETED_IDS;

	/**
	 * The table ringgold.identifiers
	 */
	public static final Identifiers IDENTIFIERS = org.plos.namedentity.persist.db.ringgold.tables.Identifiers.IDENTIFIERS;

	/**
	 * The table ringgold.institutions
	 */
	public static final Institutions INSTITUTIONS = org.plos.namedentity.persist.db.ringgold.tables.Institutions.INSTITUTIONS;

	/**
	 * The table ringgold.multies
	 */
	public static final Multies MULTIES = org.plos.namedentity.persist.db.ringgold.tables.Multies.MULTIES;

	/**
	 * The table ringgold.notes
	 */
	public static final Notes NOTES = org.plos.namedentity.persist.db.ringgold.tables.Notes.NOTES;

	/**
	 * The table ringgold.sizes
	 */
	public static final Sizes SIZES = org.plos.namedentity.persist.db.ringgold.tables.Sizes.SIZES;

	/**
	 * The table ringgold.taxonomy
	 */
	public static final Taxonomy TAXONOMY = org.plos.namedentity.persist.db.ringgold.tables.Taxonomy.TAXONOMY;

	/**
	 * The table ringgold.tiers
	 */
	public static final Tiers TIERS = org.plos.namedentity.persist.db.ringgold.tables.Tiers.TIERS;

	/**
	 * The table ringgold.urls
	 */
	public static final Urls URLS = org.plos.namedentity.persist.db.ringgold.tables.Urls.URLS;
}
