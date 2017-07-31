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

USE namedEntities;

-- define "alert" type class
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Alert Types','Saved Searches, Taxonomy');
SELECT id INTO @alertTypeClass FROM typeDescriptions WHERE description='Alert Types';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@alertTypeClass, 'Journal Search', NULL, 'journal', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@alertTypeClass, 'Preprints Search', NULL, 'preprints', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@alertTypeClass, 'Plosone Taxonomy', NULL, 'plosonetaxonomy', CURRENT_TIMESTAMP);

-- add alert type to alerts table
ALTER TABLE alerts ADD typeId INT NOT NULL DEFAULT 0;

-- perform migration
SELECT id INTO @journalTypeId FROM globalTypes 
    WHERE shortDescription='Journal Search' AND typeId=@alertTypeClass;

SELECT id INTO @taxonomyTypeId FROM globalTypes 
    WHERE shortDescription='Plosone Taxonomy' AND typeId=@alertTypeClass;

UPDATE alerts set typeId=@taxonomyTypeId WHERE name='PLoSONE' AND typeId=0;
UPDATE alerts set typeId=@journalTypeId WHERE typeId=0;

-- all alert rows should have a valid alert type. add foreign key constraint.
ALTER TABLE alerts ADD CONSTRAINT fk_alert_type FOREIGN KEY (typeId) REFERENCES globalTypes(id);
