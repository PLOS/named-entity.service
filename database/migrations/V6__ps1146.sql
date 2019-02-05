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

USE namedEntities;

DROP TABLE journals;

SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Journal Types';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'PLOS Computational Biology', NULL, 'compbiol', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'PLOS Medicine', NULL, 'medicine', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'PLOS Neglected Tropical Diseases', NULL, 'ntds', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'PLOS Pathogens', NULL, 'pathogens', CURRENT_TIMESTAMP);


UPDATE globalTypes SET typeCode='one' WHERE shortDescription='PLOSOne';

UPDATE globalTypes SET typeCode='genetics' WHERE shortDescription='PLOS Genetics';
UPDATE globalTypes SET typeCode='biology' WHERE shortDescription='PLOS Biology';

UPDATE globalTypes SET shortDescription='PLOS ONE' WHERE shortDescription='PLOSOne';

INSERT INTO typeDescriptions(description, howUsed) VALUES ('Alert Frequency','Weekly, Monthly, etc');
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Alert Frequency';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'weekly', NULL, 'weekly', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'monthly', NULL, 'monthly', CURRENT_TIMESTAMP);

INSERT INTO typeDescriptions(description, howUsed) VALUES ('Alert Types','Journal, Search, etc');
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Alert Types';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'journal', NULL, 'journal', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'search', NULL, 'search', CURRENT_TIMESTAMP);


CREATE TABLE IF NOT EXISTS alerts (
  id INT NOT NULL AUTO_INCREMENT,
  nedId INT NOT NULL,
  typeId INT NOT NULL,
  frequencyTypeId INT,
  journalTypeId INT NOT NULL,
  name TEXT NULL,
  query TEXT NULL,
  sourceTypeId INT NOT NULL,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  createdBy INT NOT NULL,
  lastModified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  lastModifiedBy INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
  FOREIGN KEY (typeId) REFERENCES globalTypes(id),
  FOREIGN KEY (sourceTypeId) REFERENCES globalTypes(id),
  FOREIGN KEY (frequencyTypeId) REFERENCES globalTypes(id),
  FOREIGN KEY (journalTypeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;



SET foreign_key_checks = 0;
DROP INDEX uniqueIdentifier on uniqueIdentifiers;
SET foreign_key_checks = 1;

ALTER TABLE uniqueIdentifiers ADD UNIQUE INDEX uniqueIdentifier (nedId, uniqueIdentifier, sourceTypeId, typeId);
