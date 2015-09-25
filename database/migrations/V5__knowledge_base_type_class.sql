
USE namedEntities;

/* Add Knowledge Base Type Class */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Knowledge Base Groups','Biology, Genetics, ...');
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Knowledge Base Groups';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'PLOSONE', NULL, 'PLOSONE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Biology', NULL, 'BIO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Computational Biology', NULL, 'CB', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Genetics', NULL, 'GEN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Medicine', NULL, 'MED', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Neglected Tropical Diseases', NULL, 'NTD', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Pathogens', NULL, 'PATHOG', CURRENT_TIMESTAMP);
