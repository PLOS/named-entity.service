
USE namedEntities;

/* Knowledge Base Application Type */
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='User Applications';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base', NULL, 'KB', CURRENT_TIMESTAMP);

/* Knowledge Base Groups */
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Roles';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'AE-PLOSONE', NULL, 'PLOSONE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Editor-BIO', NULL, 'BIO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Editor-CB', NULL, 'CB', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Editor-Gen', NULL, 'GEN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Editor-MED', NULL, 'MED', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Editor-NTDs', NULL, 'NTD', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Editor-Pathog', NULL, 'PATHOG', CURRENT_TIMESTAMP);
