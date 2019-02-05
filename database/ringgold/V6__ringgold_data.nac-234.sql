/*
 * Copyright (c) 2014-2019 Public Library of Science
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

DELETE FROM ringgold.alt_names;
DELETE FROM ringgold.consortia;
DELETE FROM ringgold.deleted_ids;
DELETE FROM ringgold.identifiers;
DELETE FROM ringgold.institutions;
DELETE FROM ringgold.multies;
DELETE FROM ringgold.notes;
DELETE FROM ringgold.sizes;
DELETE FROM ringgold.taxonomy;
DELETE FROM ringgold.tiers;
DELETE FROM ringgold.urls;

INSERT INTO ringgold.institutions (ringgold_id,name) VALUES 
    (142559, "Smithfield Foods Inc"),
    (137977, "Johnston County School District Smithfield"),
    (33139,  "GlaxoSmithKline USA"),
    (137765, "Fort Smith Public Schools");

INSERT INTO ringgold.institutions (ringgold_id,name) VALUES 
    (10200, "institution-1"),
    (10100, "institution-2"),
    (10000, "institution-3");

INSERT INTO ringgold.institutions (ringgold_id,name) VALUES
    (43568, "Smiths Medical"),
    (14842, "University of Arkansas at Fort Smith"),
    (132564, "Ladysmith-Hawkins School District"),
    (416450, "Dixon-Smith Middle School"),
    (6476, "Paul Smith's College");
