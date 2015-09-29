
USE namedEntities;

/* Delete Roles Type Class */
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Roles';
DELETE FROM globalTypes WHERE typeId=@typeIdVar;
DELETE FROM typeDescriptions WHERE id=@typeIdVar;

/* User Applications */
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='User Applications';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base', NULL, 'KB', CURRENT_TIMESTAMP);

/* Add Groups Type Class */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Groups','Roles, Membership, ...');
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Groups';

/* NED Admin Groups */
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'NED Admin', NULL, 'NED-ADMIN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'NED Manage Users', NULL, 'NED-USER', CURRENT_TIMESTAMP);

/* Knowledge Base Groups */
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - PLOSONE', NULL, 'KB-PLOSONE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - Biology', NULL, 'KB-BIO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - Computational Biology', NULL, 'KB-CB', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - Genetics', NULL, 'KB-GEN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - Medicine', NULL, 'KB-MED', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - Neglected Tropical Diseases', NULL, 'KB-NTD', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - Pathogens', NULL, 'KB-PATHOG', CURRENT_TIMESTAMP);

ALTER TABLE roles RENAME groups;
