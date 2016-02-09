
USE namedEntities;

-- remove existing alert data

DELETE FROM alerts WHERE typeId IN (
  SELECT g.id from globalTypes g, typeDescriptions t WHERE t.id=g.typeId AND description = 'Alert Types' AND typeCode='journal'
);

-- remove columns from alerts table

SELECT constraint_name INTO @typeConstraint from information_schema.key_column_usage where constraint_schema='namedEntities' AND table_name='alerts' and column_name='typeId';
SET @dropFkeySql = CONCAT('ALTER TABLE alerts DROP FOREIGN KEY ', @typeConstraint);
PREPARE stmt1 FROM @dropFkeySql;
EXECUTE stmt1;
DEALLOCATE PREPARE stmt1;

SELECT constraint_name INTO @typeConstraint from information_schema.key_column_usage where constraint_schema='namedEntities' AND table_name='alerts' and column_name='journalTypeId';
SET @dropFkeySql = CONCAT('ALTER TABLE alerts DROP FOREIGN KEY ', @typeConstraint);
PREPARE stmt1 FROM @dropFkeySql;
EXECUTE stmt1;
DEALLOCATE PREPARE stmt1;


-- hack since I cant get the above to work with name resolution
-- ALTER TABLE alerts DROP FOREIGN KEY alerts_ibfk_2;
-- ALTER TABLE alerts DROP FOREIGN KEY alerts_ibfk_5;


ALTER TABLE alerts DROP COLUMN typeId, DROP COLUMN journalTypeId;

-- remove types

DELETE FROM globalTypes
  WHERE typeId IN (
      SELECT id FROM typeDescriptions WHERE description = 'Alert Types');

-- remove description

DELETE FROM typeDescriptions WHERE description = 'Alert Types';
