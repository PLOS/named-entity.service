USE namedEntities;

DROP TABLE journals;

SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Journal Types';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'PLOS Computational Biology', NULL, 'compbiol', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'PLOS Medicine', NULL, 'medicine', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'PLOS Neglected Tropical Diseases', NULL, 'ntds', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'PLOS Pathogens', NULL, 'pathogens', CURRENT_TIMESTAMP);


UPDATE typeDescriptions SET typeCode='one' WHERE shortDescription='PLOSOne';

UPDATE typeDescriptions SET typeCode='genetics' WHERE shortDescription='PLOS Genetics';
UPDATE typeDescriptions SET typeCode='biology' WHERE shortDescription='PLOS Biology';

UPDATE typeDescriptions SET shortDescription='PLOS ONE' WHERE shortDescription='PLOSOne';

INSERT INTO typeDescriptions(description, howUsed) VALUES ('Alert frequency','Weekly, Monthly, etc');
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Alert frequency';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'weekly', NULL, 'weekly', CURRENT_TIMESTAMP);INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'monthly', NULL, 'monthly', CURRENT_TIMESTAMP);

INSERT INTO typeDescriptions(description, howUsed) VALUES ('Alert type','Journal, Search, etc');
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Alert type';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'journal', NULL, 'journal', CURRENT_TIMESTAMP);INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'search', NULL, 'search', CURRENT_TIMESTAMP);


CREATE TABLE IF NOT EXISTS alerts (
  id INT NOT NULL AUTO_INCREMENT,
  nedId INT NOT NULL,
  typeId INT NOT NULL,
  frequencyTypeId INT,
  journalTypeId INT NOT NULL,
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
