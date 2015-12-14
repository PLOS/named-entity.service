
USE namedEntities;

SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Degrees';

/* Replace seeded placeholder degrees with these values */

DELETE FROM globalTypes WHERE typeId=@typeIdVar;

INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) 
  VALUES (@typeIdVar, 'Doctorate', 'Doctorate (Ph.D., M.D., etc.)', 'PHD', CURRENT_TIMESTAMP);

INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) 
  VALUES (@typeIdVar, 'Masters', 'Masters (MS, MA, etc.)', 'MS', CURRENT_TIMESTAMP);

INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) 
  VALUES (@typeIdVar, 'Bachelors', NULL, 'BS', CURRENT_TIMESTAMP);

INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) 
  VALUES (@typeIdVar, 'Associates', NULL, 'ASSOC', CURRENT_TIMESTAMP);

INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) 
  VALUES (@typeIdVar, 'Diploma', NULL, 'DIP', CURRENT_TIMESTAMP);

INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) 
  VALUES (@typeIdVar, 'High School', NULL, 'HS', CURRENT_TIMESTAMP);

/* Modify Degrees table. Add title attribute and make type id optional. */

ALTER TABLE namedEntities.degrees
  ADD fulltitle varchar(250) NULL
    AFTER typeId;

ALTER TABLE namedEntities.degrees MODIFY COLUMN typeId INT NULL;
