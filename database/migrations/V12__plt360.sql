USE namedEntities;

--turn off FK
SET foreign_key_checks = 0;

Use namedEntities;
--Get globalType ID
SET @glbid =(select id from globalTypes where shortDescription = 'Taiwan (Province of China)');

SET @newVal='Taiwan';
PREPARE stmt1 FROM 'UPDATE namedEntities.globalTypes SET shortDescription=? where ID=?';
EXECUTE stmt1 USING @newVal, @glbid;
DEALLOCATE PREPARE stmt1;

SET foreign_key_checks = 1;

