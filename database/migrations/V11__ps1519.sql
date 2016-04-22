
USE namedEntities;

ALTER TABLE emails
MODIFY emailAddress varchar(255) COLLATE utf8_unicode_ci NOT NULL;
