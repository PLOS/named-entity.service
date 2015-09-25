
USE namedEntities;

/* Add NED Roles Type Class */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('NED Roles','Admin, Manage Users, ...');
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='NED Roles';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Admin', NULL, 'ADMIN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Manage Users', NULL, 'USER', CURRENT_TIMESTAMP);
