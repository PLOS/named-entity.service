
USE namedEntities;

SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Relationship Types';

DELETE FROM globalTypes WHERE shortDescription='Manager-Subordinate' AND typeId=@typeIdVar;
DELETE FROM globalTypes WHERE shortDescription='AE-Author' AND typeId=@typeIdVar;
DELETE FROM globalTypes WHERE shortDescription='Organization-Author' AND typeId=@typeIdVar;
DELETE FROM globalTypes WHERE shortDescription='AE-Reviewer' AND typeId=@typeIdVar;
