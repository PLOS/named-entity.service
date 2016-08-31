
USE namedEntities;

SET foreign_key_checks = 0;

Use namedEntities;
--Get globalType ID 
Select id INTO @glblid from globalTypes where shortDescription LIKE 'Taiwan (Province of China)'

-- Update globalType By ID 
SET @updatGlobalSql= ('UPDATE globalTypes SET shortDescription="Taiwan" where ID=', @glblid);
PREPARE stmt1 FROM @updatGlobalSql;
EXECUTE stmt1;
DEALLOCATE PREPARE stmt1;

SET foreign_key_checks = 1;
