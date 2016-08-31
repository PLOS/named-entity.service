USE namedEntities;

--turn off FK
SET foreign_key_checks = 0;

Use namedEntities;
--Get globalType ID
SET @glbid =(select id from globalTypes where shortDescription = "Taiwan \(Province of China\)");

SET @newVal='Taiwan';
SET @updatGlobalSql='UPDATE globalTypes SET shortDescription=@newVal where ID=@glblid';
PREPARE stmt1 FROM @updatGlobalSql;
EXECUTE stmt1;
DEALLOCATE PREPARE stmt1;

SET foreign_key_checks = 1;

