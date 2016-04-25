
USE namedEntities;

SET foreign_key_checks = 0;

ALTER TABLE emails
MODIFY emailAddress varchar(255) COLLATE utf8_unicode_ci NOT NULL;

SET foreign_key_checks = 1;
