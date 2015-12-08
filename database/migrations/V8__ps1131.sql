
USE namedEntities;

SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Degrees';

/* Replace placeholder degrees with these */
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
