
USE namedEntities;

/* Delete 'User Applications' Type Class */
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='User Applications';
DELETE FROM globalTypes WHERE typeId=@typeIdVar;
DELETE FROM typeDescriptions WHERE id=@typeIdVar;

/* Delete Roles Type Class */
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Roles';
DELETE FROM globalTypes WHERE typeId=@typeIdVar;
DELETE FROM typeDescriptions WHERE id=@typeIdVar;
