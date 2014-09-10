--SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
--SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
--SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS namedEntities;

CREATE SCHEMA IF NOT EXISTS namedEntities;
--    DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS namedEntities.typeDescriptions;
CREATE TABLE IF NOT EXISTS namedEntities.typeDescriptions (
    id INT NOT NULL AUTO_INCREMENT,
    description TEXT NOT NULL,
    howUsed TEXT NULL,
    PRIMARY KEY (id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.globalTypes;
CREATE TABLE IF NOT EXISTS namedEntities.globalTypes (
    id INT NOT NULL AUTO_INCREMENT,
    typeId INT NOT NULL,
    shortDescription TEXT NOT NULL,
    longDescription TEXT NULL,
    typeCode TEXT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    createdBy INT NULL,
    lastModifiedBy INT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (typeId) REFERENCES typeDescriptions(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.namedEntityIdentifiers;
CREATE TABLE IF NOT EXISTS namedEntities.namedEntityIdentifiers (
    id INT NOT NULL AUTO_INCREMENT,
    typeId INT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    createdBy INT NULL,
    lastModifiedBy INT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.individuals;
CREATE TABLE IF NOT EXISTS namedEntities.individuals (
    nedId INT NOT NULL,
    firstName TEXT NOT NULL,
    middleName TEXT NULL,
    lastName TEXT NOT NULL,
    nickName TEXT NULL,
    namePrefixTypeId INT NULL,
    nameSuffixTypeId INT NULL,
    displayName TEXT NOT NULL,
    preferredLanguageTypeId INT NULL,
    preferredCommunicationMethodTypeId INT NULL,
    photoImage VARBINARY(255) NULL,
    isActive TINYINT(1) NOT NULL,
    isVisible TINYINT(1) NOT NULL,
    PRIMARY KEY (nedId),
    FOREIGN KEY (namePrefixTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (nameSuffixTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (preferredLanguageTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (preferredCommunicationMethodTypeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.organizations;
CREATE TABLE IF NOT EXISTS namedEntities.organizations (
    nedId INT NOT NULL,
    typeId INT NULL,
    familiarName TEXT NULL,
    legalName TEXT NULL,
    mainContactId INT NULL,
    isActive TINYINT(1) NOT NULL,
    isVisible TINYINT(1) NOT NULL,
    PRIMARY KEY (nedId),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id),
    FOREIGN KEY (mainContactId) REFERENCES individuals(nedId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.addresses;
CREATE TABLE IF NOT EXISTS namedEntities.addresses (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NULL,
    addressLine1 TEXT NULL,
    addressLine2 TEXT NULL,
    addressLine3 TEXT NULL,
    city TEXT NULL,
    stateCodeTypeId INT NULL,
    countryCodeTypeId INT NOT NULL,
    postalCode TEXT NULL,
    mainContactNamedEntityId INT NULL,
    latitude INT NULL,
    longitude INT NULL,
    isPrimary TINYINT(1) NOT NULL,
    isActive TINYINT(1) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.emails;
CREATE TABLE IF NOT EXISTS namedEntities.emails (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NULL,
    emailAddress TEXT NOT NULL,
    isPrimary TINYINT(1) NOT NULL,
    isActive TINYINT(1) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.phoneNumbers;
CREATE TABLE IF NOT EXISTS namedEntities.phoneNumbers (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NULL,
    countryCodeTypeId INT NULL,
    phoneNumber TEXT NOT NULL,
    extension TEXT NULL,
    isPrimary TINYINT(1) NOT NULL,
    isActive TINYINT(1) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id),
    FOREIGN KEY (countryCodeTypeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.roles;
CREATE TABLE IF NOT EXISTS namedEntities.roles (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeID INT NOT NULL,
    sourceApplicationTypeId INT NULL,
    startDate DATE NULL,
    endDate DATE NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    createdBy INT NULL,
    lastModifiedBy INT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id),
    FOREIGN KEY (sourceApplicationTypeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.relationships;
CREATE TABLE IF NOT EXISTS namedEntities.relationships (
    id INT NOT NULL AUTO_INCREMENT,
    typeId INT NOT NULL,
    masterNamedEntityId INT NOT NULL,
    childNamedEntityId INT NOT NULL,
    title TEXT NULL,
    startDate DATE NULL,
    endDate DATE NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    createdBy INT NULL,
    lastModifiedBy INT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.sourceFields;
CREATE TABLE IF NOT EXISTS namedEntities.sourceFields (
    id INT NOT NULL AUTO_INCREMENT,
    sourceTable TEXT NOT NULL,
    sourceField TEXT NOT NULL,
    PRIMARY KEY (id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.auditTrail;
CREATE TABLE IF NOT EXISTS namedEntities.auditTrail (
    id INT NOT NULL AUTO_INCREMENT,
    sourceFieldId INT NOT NULL,
    rowNumber INT NULL,
    oldValue TEXT NOT NULL,
    newValue TEXT NOT NULL,
    lastModified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModifiedBy INT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (sourceFieldId) REFERENCES sourceFields(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.subjectAreas;
CREATE TABLE IF NOT EXISTS namedEntities.subjectAreas (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.journals;
CREATE TABLE IF NOT EXISTS namedEntities.journals (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.degrees;
CREATE TABLE IF NOT EXISTS namedEntities.degrees (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.urls;
CREATE TABLE IF NOT EXISTS namedEntities.urls (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    url TEXT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.uniqueIdentifiers;
CREATE TABLE IF NOT EXISTS namedEntities.uniqueIdentifiers (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NOT NULL,
    uniqueIdentifier TEXT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

/* ------------------------------------------------------------------------- */
/*  TYPE DESCRIPTIONS                                                        */
/* ------------------------------------------------------------------------- */

INSERT INTO namedEntities.typeDescriptions VALUES (1,'Named Party Types','Individual, Organization');
INSERT INTO namedEntities.typeDescriptions VALUES (2,'Source Applications','Editorial Manager, Ambra, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (3,'Organization Types','University, Department, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (4,'Roles','Academic Editor, Author, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (5,'Named Party Prefixes','Ms, Dr, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (6,'Named Party Suffixes','II, III, Jr, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (7,'Languages','English, Mandarin, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (8,'Communication Methods','Phone, Email, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (9,'Telephone Number Types','Home, Mobile, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (10,'Email Address Types','Home, Work, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (11,'Physical Address Types','Home, Work, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (12,'Relationship Types','AE/Author, Manager/Subordinate, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (13,'Country Codes for Phone Numbers','01, 44, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (14,'Subject Areas','Cancer, Urology, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (15,'Journal Types','PLOSOne, PLOS Biology, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (16,'Country Types','United States, Canada, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (17,'Unique Identifier Types','Ringgold, ORCID, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (18,'State and Province Codes','CA, ONT, etc');
INSERT INTO namedEntities.typeDescriptions VALUES (19,'Degrees','MD, PhD, etc');

/* ------------------------------------------------------------------------- */
/*  GLOBAL TYPES                                                             */
/* ------------------------------------------------------------------------- */

INSERT INTO namedEntities.globalTypes VALUES (1,1,'Individual',NULL,'IND','2014-03-23 00:00:00','2014-03-23 00:00:00',1,1);
INSERT INTO namedEntities.globalTypes VALUES (2,1,'Organization',NULL,'ORG','2014-03-23 20:33:48','2014-03-23 20:33:48',1,1);
INSERT INTO namedEntities.globalTypes VALUES (3,2,'Editorial Manager',NULL,'EM','2014-03-23 20:34:40','2014-03-23 20:34:40',1,1);
INSERT INTO namedEntities.globalTypes VALUES (4,2,'Ambra',NULL,'AMB','2014-03-23 20:35:05','2014-03-23 20:35:05',1,1);
INSERT INTO namedEntities.globalTypes VALUES (5,3,'Research Hospital',NULL,'HOSP','2014-03-23 20:43:09','2014-03-23 20:43:09',1,1);
INSERT INTO namedEntities.globalTypes VALUES (6,3,'University',NULL,'UNIV','2014-03-23 20:37:08','2014-03-23 20:37:08',1,1);
INSERT INTO namedEntities.globalTypes VALUES (7,3,'Department',NULL,'DEPT','2014-03-23 20:37:26','2014-03-23 20:37:26',1,1);
INSERT INTO namedEntities.globalTypes VALUES (8,4,'Author',NULL,'AUTH','2014-03-23 20:44:32','2014-03-23 20:44:32',1,1);
INSERT INTO namedEntities.globalTypes VALUES (9,4,'Co-Author',NULL,'COAU','2014-03-23 20:44:57','2014-03-23 20:44:57',1,1);
INSERT INTO namedEntities.globalTypes VALUES (10,4,'Academic Editor (PLOS ONE)',NULL,'AE_PLOSONE','2014-03-23 20:45:37','2014-03-23 20:45:37',1,1);
INSERT INTO namedEntities.globalTypes VALUES (11,4,'Reviewer',NULL,'REV','2014-03-23 20:46:08','2014-03-23 20:46:08',1,1);
INSERT INTO namedEntities.globalTypes VALUES (12,5,'Mr.',NULL,'MR','2014-03-23 20:47:09','2014-03-23 20:47:09',1,1);
INSERT INTO namedEntities.globalTypes VALUES (13,5,'Mrs.',NULL,'MRS','2014-03-23 20:47:26','2014-03-23 20:47:26',1,1);
INSERT INTO namedEntities.globalTypes VALUES (14,5,'Dr.',NULL,'DR','2014-03-23 20:47:41','2014-03-23 20:47:41',1,1);
INSERT INTO namedEntities.globalTypes VALUES (15,5,'Ms.',NULL,'MS','2014-03-23 20:48:07','2014-03-23 20:48:07',1,1);
INSERT INTO namedEntities.globalTypes VALUES (16,6,'III',NULL,'III','2014-03-23 20:48:44','2014-03-23 20:48:44',1,1);
INSERT INTO namedEntities.globalTypes VALUES (17,6,'II',NULL,'II','2014-03-23 20:48:55','2014-03-23 20:48:55',1,1);
INSERT INTO namedEntities.globalTypes VALUES (18,19,'PhD',NULL,'PHD','2014-03-23 20:49:14','2014-03-23 20:49:14',1,1);
INSERT INTO namedEntities.globalTypes VALUES (19,19,'MD',NULL,'MD','2014-03-23 20:49:30','2014-03-23 20:49:30',1,1);
INSERT INTO namedEntities.globalTypes VALUES (20,7,'English',NULL,'ENG','2014-03-23 20:50:48','2014-03-23 20:50:48',1,1);
INSERT INTO namedEntities.globalTypes VALUES (21,7,'Mandarin',NULL,'MAN','2014-03-23 20:51:11','2014-03-23 20:51:11',1,1);
INSERT INTO namedEntities.globalTypes VALUES (22,7,'Italian',NULL,'ITAL','2014-03-23 20:51:35','2014-03-23 20:51:35',1,1);
INSERT INTO namedEntities.globalTypes VALUES (23,7,'Spanish',NULL,'SPAN','2014-03-23 20:51:55','2014-03-23 20:51:55',1,1);
INSERT INTO namedEntities.globalTypes VALUES (24,7,'French',NULL,'FREN','2014-03-23 20:52:13','2014-03-23 20:52:13',1,1);
INSERT INTO namedEntities.globalTypes VALUES (25,8,'Phone',NULL,'PH','2014-03-23 20:53:09','2014-03-23 20:53:09',1,1);
INSERT INTO namedEntities.globalTypes VALUES (26,8,'Email',NULL,'EMAI','2014-03-23 20:53:43','2014-03-23 20:53:43',1,1);
INSERT INTO namedEntities.globalTypes VALUES (27,8,'Fax',NULL,'FAX','2014-03-23 20:54:04','2014-03-23 20:54:04',1,1);
INSERT INTO namedEntities.globalTypes VALUES (28,9,'Home',NULL,'HOME','2014-03-23 20:54:52','2014-03-23 20:54:52',1,1);
INSERT INTO namedEntities.globalTypes VALUES (29,9,'Office',NULL,'OFF','2014-03-23 20:55:13','2014-03-23 20:55:13',1,1);
INSERT INTO namedEntities.globalTypes VALUES (30,9,'Mobile',NULL,'CELL','2014-03-23 20:55:35','2014-03-23 20:55:35',1,1);
INSERT INTO namedEntities.globalTypes VALUES (31,10,'Personal',NULL,'PERS','2014-03-23 20:56:46','2014-03-23 20:56:46',1,1);
INSERT INTO namedEntities.globalTypes VALUES (32,10,'Work',NULL,'WORK','2014-03-23 20:57:07','2014-03-23 20:57:07',1,1);
INSERT INTO namedEntities.globalTypes VALUES (33,11,'Office',NULL,'WORK','2014-03-23 20:59:41','2014-03-23 20:59:41',1,1);
INSERT INTO namedEntities.globalTypes VALUES (34,11,'Home',NULL,'HOME','2014-03-23 20:58:06','2014-03-23 20:58:06',1,1);
INSERT INTO namedEntities.globalTypes VALUES (35,12,'Manager-Subordinate',NULL,'MGR','2014-03-23 21:19:21','2014-03-23 21:19:21',1,1);
INSERT INTO namedEntities.globalTypes VALUES (36,12,'AE-Author',NULL,'AEAU','2014-03-23 21:20:00','2014-03-23 21:20:00',1,1);
INSERT INTO namedEntities.globalTypes VALUES (37,12,'Organization-Author',NULL,'ORAU','2014-03-23 21:21:12','2014-03-23 21:21:12',1,1);
INSERT INTO namedEntities.globalTypes VALUES (38,12,'AE-Reviewer',NULL,'AERV','2014-03-23 21:22:07','2014-03-23 21:22:07',1,1);
INSERT INTO namedEntities.globalTypes VALUES (39,13,'01','United States','US','2014-03-23 21:26:59','2014-03-23 21:26:59',1,1);
INSERT INTO namedEntities.globalTypes VALUES (40,13,'44','United Kingdom','UK','2014-03-23 21:27:27','2014-03-23 21:27:27',1,1);
INSERT INTO namedEntities.globalTypes VALUES (41,14,'Urology',NULL,'URO','2014-03-23 21:29:24','2014-03-23 21:29:24',1,1);
INSERT INTO namedEntities.globalTypes VALUES (42,14,'Oncology',NULL,'ONC','2014-03-23 21:29:52','2014-03-23 21:29:52',1,1);
INSERT INTO namedEntities.globalTypes VALUES (43,15,'PLOSOne',NULL,'PL1','2014-03-23 21:33:02','2014-03-23 21:33:02',1,1);
INSERT INTO namedEntities.globalTypes VALUES (44,15,'PLOS Genetics',NULL,'PLGE','2014-03-23 21:33:25','2014-03-23 21:33:25',1,1);
INSERT INTO namedEntities.globalTypes VALUES (45,15,'PLOS Biology',NULL,'PLBI','2014-03-23 21:33:51','2014-03-23 21:33:51',1,1);
INSERT INTO namedEntities.globalTypes VALUES (46,16,'Canada',NULL,'CAN','2014-03-23 21:39:04','2014-03-23 21:39:04',1,1);
INSERT INTO namedEntities.globalTypes VALUES (47,16,'United States',NULL,'USA','2014-03-23 21:39:49','2014-03-23 21:39:49',1,1);
INSERT INTO namedEntities.globalTypes VALUES (48,16,'United Kingdom',NULL,'UK','2014-03-23 21:40:27','2014-03-23 21:40:27',1,1);
INSERT INTO namedEntities.globalTypes VALUES (49,17,'Ringgold',NULL,'RG','2014-03-23 21:43:08','2014-03-23 21:43:08',1,1);
INSERT INTO namedEntities.globalTypes VALUES (50,17,'ORCID',NULL,'ORC','2014-03-23 21:43:40','2014-03-23 21:43:40',1,1);
INSERT INTO namedEntities.globalTypes VALUES (51,18,'CA','California','CA','2014-03-23 21:44:57','2014-03-23 21:44:57',1,1);
INSERT INTO namedEntities.globalTypes VALUES (52,18,'NY','New York','NY','2014-03-23 21:45:22','2014-03-23 21:45:22',1,1);
INSERT INTO namedEntities.globalTypes VALUES (53,18,'ONT','Ontario','ONT','2014-03-23 21:46:35','2014-03-23 21:46:35',1,1);
INSERT INTO namedEntities.globalTypes VALUES (54,4,'NP System Administrator',NULL,'SANP','2014-03-28 08:38:17','2014-03-28 08:38:17',1,1);
INSERT INTO namedEntities.globalTypes VALUES (55,2,'Named Party DB',NULL,'NPDB','2014-03-28 08:39:15','2014-03-28 08:39:15',1,1);
INSERT INTO namedEntities.globalTypes VALUES (56,5,'Professor',NULL,'PROF','2014-03-31 00:00:00','2014-03-31 00:00:00',1,1);
INSERT INTO namedEntities.globalTypes VALUES (57,13,'886','Taiwan','TW','2014-03-23 21:27:27','2014-03-23 21:27:27',1,1);
INSERT INTO namedEntities.globalTypes VALUES (58,13,'31','Netherlands','NL','2014-03-23 21:27:27','2014-03-23 21:27:27',1,1);
INSERT INTO namedEntities.globalTypes VALUES (59,13,'49','Germany','DE','2014-03-23 21:27:27','2014-03-23 21:27:27',1,1);
INSERT INTO namedEntities.globalTypes VALUES (60,13,'86','China','CN','2014-03-23 21:27:27','2014-03-23 21:27:27',1,1);
INSERT INTO namedEntities.globalTypes VALUES (61,13,'61','Australia','AU','2014-03-23 21:27:27','2014-03-23 21:27:27',1,1);
INSERT INTO namedEntities.globalTypes VALUES (62,18,'NJ','New Jersey','NJ','2014-03-23 21:44:57','2014-03-23 21:44:57',1,1);
INSERT INTO namedEntities.globalTypes VALUES (63,18,'TX','Texas','TX','2014-03-23 21:44:57','2014-03-23 21:44:57',1,1);
INSERT INTO namedEntities.globalTypes VALUES (64,18,'WV','West Virginia','WV','2014-03-23 21:44:57','2014-03-23 21:44:57',1,1);
INSERT INTO namedEntities.globalTypes VALUES (65,18,'TN','Tennessee','TN','2014-03-23 21:44:57','2014-03-23 21:44:57',1,1);
INSERT INTO namedEntities.globalTypes VALUES (66,18,'IN','Indiana','IN','2014-03-23 21:44:57','2014-03-23 21:44:57',1,1);
INSERT INTO namedEntities.globalTypes VALUES (67,18,'Queensland','Australia','QNLD','2014-03-23 21:44:57','2014-03-23 21:44:57',1,1);
INSERT INTO namedEntities.globalTypes VALUES (68,18,'Victoria','Australia','VICT','2014-03-23 21:44:57','2014-03-23 21:44:57',1,1);
INSERT INTO namedEntities.globalTypes VALUES (69,18,'Zhejiang','China','ZHEJ','2014-03-23 21:44:57','2014-03-23 21:44:57',1,1);
INSERT INTO namedEntities.globalTypes VALUES (70,16,'Taiwan',NULL,'TW','2014-03-23 21:40:27','2014-03-23 21:40:27',1,1);
INSERT INTO namedEntities.globalTypes VALUES (71,16,'Netherlands',NULL,'NL','2014-03-23 21:40:27','2014-03-23 21:40:27',1,1);
INSERT INTO namedEntities.globalTypes VALUES (72,16,'Germany',NULL,'DE','2014-03-23 21:40:27','2014-03-23 21:40:27',1,1);
INSERT INTO namedEntities.globalTypes VALUES (73,16,'Australia',NULL,'AU','2014-03-23 21:40:27','2014-03-23 21:40:27',1,1);
INSERT INTO namedEntities.globalTypes VALUES (74,16,'China',NULL,'CN','2014-03-23 21:40:27','2014-03-23 21:40:27',1,1);
INSERT INTO namedEntities.globalTypes VALUES (75,17,'Editorial Manager',NULL,'EM','2014-03-23 20:34:40','2014-03-23 20:34:40',1,1);
INSERT INTO namedEntities.globalTypes VALUES (76,17,'CAS',NULL,'CAS','2014-03-23 20:34:40','2014-03-23 20:34:40',1,1);
INSERT INTO namedEntities.globalTypes VALUES (77,17,'Salesforce',NULL,'SF','2014-03-23 20:34:40','2014-03-23 20:34:40',1,1);

INSERT INTO namedEntities.namedEntityIdentifiers VALUES (1, 1, CURRENT_TIMESTAMP,  CURRENT_TIMESTAMP, NULL, NULL);
INSERT INTO namedEntities.individuals VALUES (1, 'NED', NULL, 'NED', NULL, NULL, NULL, 'NED', NULL, NULL, NULL, 1,  1);
