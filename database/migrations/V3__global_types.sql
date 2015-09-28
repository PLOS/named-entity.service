
USE namedEntities;

/* Delete Roles Type Class */
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Roles';
DELETE FROM globalTypes WHERE typeId=@typeIdVar;
DELETE FROM typeDescriptions WHERE id=@typeIdVar;

/* User Applications */
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='User Applications';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base', NULL, 'KB', CURRENT_TIMESTAMP);
