
USE namedEntities;

/* Akita Admin Application Type */
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='User Applications';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Akita Admin', NULL, 'ADMIN', CURRENT_TIMESTAMP);

/* Akita Admin Roles */
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Roles';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Admin', NULL, 'ADMIN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Manage User', NULL, 'USER', CURRENT_TIMESTAMP);
