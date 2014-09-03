SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS namedEntities;

CREATE SCHEMA IF NOT EXISTS namedEntities 
    DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE namedEntities;

DROP TABLE IF EXISTS namedEntities.typeDescriptions;
CREATE TABLE IF NOT EXISTS namedEntities.typeDescriptions (
    typeId INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(45) NULL,
    howUsed VARCHAR(45) NULL,
    PRIMARY KEY (typeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.globalTypes;
CREATE TABLE IF NOT EXISTS namedEntities.globalTypes (
    globalTypeId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    typeId INT NOT NULL,
    shortDescription VARCHAR(32) NOT NULL,
    longDescription VARCHAR(128) NULL,
    typeCode VARCHAR(4) NULL,
    created TIMESTAMP NOT NULL DEFAULT 0, 
    lastModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy INT NULL,
    lastModifiedBy INT NULL,
    FOREIGN KEY (typeId) REFERENCES typeDescriptions(typeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.namedEntityIdentifiers;
CREATE TABLE IF NOT EXISTS namedEntities.namedEntityIdentifiers (
    namedEntityId INT NOT NULL AUTO_INCREMENT,
    typeId INT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT 0, 
    lastModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy INT NULL,
    lastModifiedBy INT NULL,
    PRIMARY KEY (namedEntityId),
    FOREIGN KEY (typeId) REFERENCES globalTypes(globalTypeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.individuals;
CREATE TABLE IF NOT EXISTS namedEntities.individuals (
    namedEntityId INT NOT NULL,
    firstName VARCHAR(45) NULL,
    middleName VARCHAR(45) NULL,
    lastName VARCHAR(45) NULL,
    nickName VARCHAR(45) NULL,
    namePrefixTypeId INT NULL,
    nameSuffixTypeId INT NULL,
    displayName VARCHAR(45) NULL,
    preferredLanguageTypeId INT NULL,
    preferredCommunicationMethodTypeId INT NULL,
    photoImage VARBINARY(256) NULL,
    url VARCHAR(45) NULL,
    isActive TINYINT(1) NULL,
    isVisible TINYINT(1) NULL,
    PRIMARY KEY (namedEntityId),
    FOREIGN KEY (namePrefixTypeId) REFERENCES globalTypes(globalTypeId),
    FOREIGN KEY (nameSuffixTypeId) REFERENCES globalTypes(globalTypeId),
    FOREIGN KEY (preferredLanguageTypeId) REFERENCES globalTypes(globalTypeId),
    FOREIGN KEY (preferredCommunicationMethodTypeId) REFERENCES globalTypes(globalTypeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.organizations;
CREATE TABLE IF NOT EXISTS namedEntities.organizations (
    namedEntityId INT NOT NULL,
    organizationTypeId INT NULL,
    organizationFamiliarName VARCHAR(100) NULL,
    organizationLegalName VARCHAR(100) NULL,
    organizationMainContactId INT NULL,
    isActive TINYINT(1) NULL,
    isVisible TINYINT(1) NULL,
    url VARCHAR(45) NULL,
    PRIMARY KEY (namedEntityId),
    FOREIGN KEY (organizationTypeId) REFERENCES globalTypes(globalTypeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.addresses;
CREATE TABLE IF NOT EXISTS namedEntities.addresses (
    addressId INT NOT NULL AUTO_INCREMENT,
    namedEntityId INT NOT NULL,
    addressTypeId INT NULL,
    addressLine1 VARCHAR(128) NULL,
    addressLine2 VARCHAR(128) NULL,
    addressLine3 VARCHAR(128) NULL,
    city VARCHAR(50) NULL,
    stateCodeTypeId INT NULL,  
    countryCodeTypeId INT NULL,
    postalCode VARCHAR(20) NULL,
    mainContactNamedEntityId INT NULL,
    latitude INT NULL,
    longitude INT NULL,
    isPrimary TINYINT(1) NULL,
    isActive TINYINT(1) NULL,
    PRIMARY KEY (addressId),
    FOREIGN KEY (namedEntityId) REFERENCES namedEntityIdentifiers(namedEntityId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.emails;
CREATE TABLE IF NOT EXISTS namedEntities.emails (
    emailId INT NOT NULL AUTO_INCREMENT,
    namedEntityId INT NOT NULL,
    emailTypeId INT NULL,
    emailAddress VARCHAR(255) NULL,
    isPrimary TINYINT(1) NULL,
    isActive TINYINT(1) NULL,
    PRIMARY KEY (emailId),
    FOREIGN KEY (namedEntityId) REFERENCES namedEntityIdentifiers(namedEntityId),
    FOREIGN KEY (emailTypeId) REFERENCES globalTypes(globalTypeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.phoneNumbers;
CREATE TABLE IF NOT EXISTS namedEntities.phoneNumbers (
    phoneNumberId INT NOT NULL AUTO_INCREMENT,
    namedEntityId INT NOT NULL,
    phoneNumberTypeId INT NULL,
    countryCodeTypeId INT NULL,
    phoneNumber TEXT NULL,
    extension TEXT NULL,
    isPrimary TINYINT(1) NULL,
    isActive TINYINT(1) NULL,
    PRIMARY KEY (phoneNumberId),
    FOREIGN KEY (namedEntityId) REFERENCES namedEntityIdentifiers(namedEntityId),
    FOREIGN KEY (phoneNumberTypeId) REFERENCES globalTypes(globalTypeId),
    FOREIGN KEY (countryCodeTypeId) REFERENCES globalTypes(globalTypeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.roles;
CREATE TABLE IF NOT EXISTS namedEntities.roles (
    roleId INT NOT NULL AUTO_INCREMENT,
    namedEntityId INT NOT NULL,
    sourceApplicationTypeId INT NULL,
    roleTypeID INT NULL,
    startDate DATE NULL,
    endDate DATE NULL,
    created TIMESTAMP NOT NULL DEFAULT 0,
    lastModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy INT NULL,
    lastModifiedBy INT NULL,
    PRIMARY KEY (roleId),
    FOREIGN KEY (namedEntityId) REFERENCES namedEntityIdentifiers(namedEntityId),
    FOREIGN KEY (sourceApplicationTypeId) REFERENCES globalTypes(globalTypeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.relationships;
CREATE TABLE IF NOT EXISTS namedEntities.relationships (
    relationshipId INT NOT NULL AUTO_INCREMENT,
    masterNamedEntityId INT NOT NULL,
    childNamedEntityId INT NULL,
    relationshipTypeId INT NULL,
    startDate DATE NULL,
    endDate DATE NULL,
    created TIMESTAMP NOT NULL DEFAULT 0, 
    lastModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy INT NULL,
    lastModifiedBy INT NULL,
    PRIMARY KEY (relationshipId),
    FOREIGN KEY (relationshipTypeId) REFERENCES globalTypes(globalTypeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.auditTrail;
CREATE TABLE IF NOT EXISTS namedEntities.auditTrail (
    auditTrailId INT NOT NULL AUTO_INCREMENT,
    sourceFieldId INT NULL,
    rowNumber INT NULL,
    oldValue VARCHAR(45) NULL,
    newValue VARCHAR(45) NULL,
    lastModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    lastModifiedBy INT NULL,
    PRIMARY KEY (auditTrailId),
    FOREIGN KEY (sourceFieldId) REFERENCES sourceFields(sourceFieldId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.sourceFields;
CREATE TABLE IF NOT EXISTS namedEntities.sourceFields (
    sourceFieldId INT NOT NULL AUTO_INCREMENT,
    sourceTable VARCHAR(45) NULL,
    sourceField VARCHAR(45) NULL,
    PRIMARY KEY (sourceFieldId) );

DROP TABLE IF EXISTS namedEntities.subjectAreas;
CREATE TABLE IF NOT EXISTS namedEntities.subjectAreas (
    subjectAreaId INT NOT NULL AUTO_INCREMENT,
    namedEntityId INT NOT NULL,
    subjectAreaTypeId INT NULL,
    PRIMARY KEY (subjectAreaId),
    FOREIGN KEY (namedEntityId) REFERENCES namedEntityIdentifiers(namedEntityId),
    FOREIGN KEY (subjectAreaTypeId) REFERENCES globalTypes(globalTypeId)
)   ENGINE=INNODB;

-- merge with unique identifiers?
DROP TABLE IF EXISTS namedEntities.cas;
CREATE TABLE IF NOT EXISTS namedEntities.cas (
    casId INT NOT NULL,
    namedEntityId INT NOT NULL,
    sourceApplicationTypeId INT NULL,
    PRIMARY KEY (casId),
    FOREIGN KEY (namedEntityId) REFERENCES namedEntityIdentifiers(namedEntityId),
    FOREIGN KEY (sourceApplicationTypeId) REFERENCES globalTypes(globalTypeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.journals;
CREATE TABLE IF NOT EXISTS namedEntities.journals (
    journalId INT NOT NULL AUTO_INCREMENT,
    namedEntityId INT NOT NULL,
    journalTypeId INT NOT NULL,
    PRIMARY KEY (journalId),
    FOREIGN KEY (namedEntityId) REFERENCES namedEntityIdentifiers(namedEntityId),
    FOREIGN KEY (journalTypeId) REFERENCES globalTypes(globalTypeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.degrees;
CREATE TABLE IF NOT EXISTS namedEntities.degrees (
    degreeId INT NOT NULL AUTO_INCREMENT,
    namedEntityId INT NOT NULL,
    degreeTypeId INT NULL,
    PRIMARY KEY (degreeId),
    FOREIGN KEY (namedEntityId) REFERENCES namedEntityIdentifiers(namedEntityId),
    FOREIGN KEY (degreeTypeId) REFERENCES globalTypes(globalTypeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.uniqueIdentifiers;
CREATE TABLE IF NOT EXISTS namedEntities.uniqueIdentifiers (
    uniqueIdentifiersId INT NOT NULL AUTO_INCREMENT,
    namedEntityId INT NOT NULL,
    uniqueIdentifierTypeId INT NULL,
    uniqueIdentifier VARCHAR(45) NOT NULL,
    PRIMARY KEY (uniqueIdentifiersId),
    FOREIGN KEY (namedEntityId) REFERENCES namedEntityIdentifiers(namedEntityId),
    FOREIGN KEY (uniqueIdentifierTypeId) REFERENCES globalTypes(globalTypeId)
)   ENGINE=INNODB;

/* Named Party Types */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Named Party Types','Individual, Organization');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Named Party Types';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Individual', NULL, 'IND', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Organization', NULL, 'ORG', CURRENT_TIMESTAMP);

/* Source Applications */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Source Applications','Editorial Manager, Ambra, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Source Applications';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Editorial Manager', NULL, 'EM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Ambra', NULL, 'AMB', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Named Party DB', NULL, 'NPDB', CURRENT_TIMESTAMP);

/* Organization Types */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Organization Types','University, Department, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Organization Types';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Research Hospital', NULL, 'HOSP', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'University', NULL, 'UNIV', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Department', NULL, 'DEPT', CURRENT_TIMESTAMP);

/* Roles */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Roles','Academic Editor, Author, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Roles';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Author', NULL, 'AUTH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Co-Author', NULL, 'COAU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Academic Editor (PLOSONE)', NULL, 'AE1', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'NP System Administrator', NULL, 'SANP', CURRENT_TIMESTAMP);

/* Named Party Prefixes */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Named Party Prefixes','Ms, Dr, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Named Party Prefixes';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Mr.', NULL, 'MR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Mrs.', NULL, 'MRS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Dr.', NULL, 'DR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Ms.', NULL, 'MS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Professor', NULL, 'PROF', CURRENT_TIMESTAMP);

/* Named Party Suffixes */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Named Party Suffixes','II, III, Jr, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Named Party Suffixes';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'III', NULL, 'III', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'II', NULL, 'II', CURRENT_TIMESTAMP);

/* Languages */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Languages','English, Mandarin, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Languages';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'English', NULL, 'ENG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Mandarin', NULL, 'MAN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Italian', NULL, 'ITAL', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Spanish', NULL, 'SPAN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'French', NULL, 'FREN', CURRENT_TIMESTAMP);

/* Communication Methods */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Communication Methods','Phone, Email, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Communication Methods';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Phone', NULL, 'PH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Email', NULL, 'EMAI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Fax', NULL, 'FAX', CURRENT_TIMESTAMP);

/* Telephone Number Types */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Telephone Number Types','Home, Mobile, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Telephone Number Types';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Home', NULL, 'HOME', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Office', NULL, 'OFF', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Mobile', NULL, 'CELL', CURRENT_TIMESTAMP);

/* Email Address Types */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Email Address Types','Home, Work, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Email Address Types';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Personal', NULL, 'PERS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Work', NULL, 'WORK', CURRENT_TIMESTAMP);

/* Physical Address Types */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Physical Address Types','Home, Work, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Physical Address Types';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Office', NULL, 'WORK', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Home', NULL, 'HOME', CURRENT_TIMESTAMP);

/* Relationship Types */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Relationship Types','AE/Author, Manager/Subordinate, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Relationship Types';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Manager-Subordinate', NULL, 'MGR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'AE-Author', NULL, 'AEAU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Organization-Author', NULL, 'ORAU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'AE-Reviewer', NULL, 'AERV', CURRENT_TIMESTAMP);

/* Country Codes for Phone Numbers */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Country Codes for Phone Numbers','01, 44, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Country Codes for Phone Numbers';
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Afghanistan','AF','93',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Albania','AL','355',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Algeria','DZ','213',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'American Samoa','AS','1 684 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Andorra','AD','376',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Angola','AO','244',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Anguilla','AI','1 264 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Antarctica','AQ','672',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Antigua and Barbuda','AG','1 268 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Argentina','AR','54',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Armenia','AM','374',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Aruba','AW','297',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Australia','AU','61',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Austria','AT','43',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Azerbaijan','AZ','994',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Bahamas','BS','1 242 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Bahrain','BH','973',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Bangladesh','BD','880',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Barbados','BB','1 246 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Belarus','BY','375',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Belgium','BE','32',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Belize','BZ','501',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Benin','BJ','229',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Bermuda','BM','1 441 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Bhutan','BT','975',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Bolivia','BO','591',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Bosnia and Herzegovina','BA','387',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Botswana','BW','267',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Brazil','BR','55',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'British Virgin Islands','VG','1 284 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Brunei','BN','673',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Bulgaria','BG','359',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Burkina Faso','BF','226',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Burma (Myanmar)','MM','95',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Burundi','BI','257',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Cambodia','KH','855',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Cameroon','CM','237',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Canada','CA','1',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Cape Verde','CV','238',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Cayman Islands','KY','1 345 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Central African Republic','CF','236',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Chad','TD','235',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Chile','CL','56',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'China','CN','86',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Christmas Island','CX','61',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Cocos (Keeling) Islands','CC','61',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Colombia','CO','57',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Comoros','KM','269',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Cook Islands','CK','682',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Costa Rica','CR','506',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Croatia','HR','385',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Cuba','CU','53',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Cyprus','CY','357',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Czech Republic','CZ','420',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Democratic Republic of the Congo','CD','243',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Denmark','DK','45',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Djibouti','DJ','253',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Dominica','DM','1 767 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Dominican Republic','DO','1 809 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Ecuador','EC','593',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Egypt','EG','20',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'El Salvador','SV','503',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Equatorial Guinea','GQ','240',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Eritrea','ER','291',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Estonia','EE','372',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Ethiopia','ET','251',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Falkland Islands','FK','500',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Faroe Islands','FO','298',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Fiji','FJ','679',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Finland','FI','358',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'France','FR','33',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'French Polynesia','PF','689',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Gabon','GA','241',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Gambia','GM','220',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Georgia','GE','995',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Germany','DE','49',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Ghana','GH','233',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Gibraltar','GI','350',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Greece','GR','30',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Greenland','GL','299',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Grenada','GD','1 473 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Guam','GU','1 671 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Guatemala','GT','502',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Guinea','GN','224',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Guinea-Bissau','GW','245',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Guyana','GY','592',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Haiti','HT','509',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Holy See (Vatican City)','VA','39',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Honduras','HN','504',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Hong Kong','HK','852',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Hungary','HU','36',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Iceland','IS','354',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'India','IN','91',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Indonesia','ID','62',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Iran','IR','98',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Iraq','IQ','964',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Ireland','IE','353',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Isle of Man','IM','44',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Israel','IL','972',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Italy','IT','39',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Ivory Coast','CI','225',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Jamaica','JM','1 876 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Japan','JP','81',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Jordan','JO','962',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Kazakhstan','KZ','7',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Kenya','KE','254',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Kiribati','KI','686',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Kosovo','/  ','381',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Kuwait','KW','965',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Kyrgyzstan','KG','996',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Laos','LA','856',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Latvia','LV','371',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Lebanon','LB','961',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Lesotho','LS','266',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Liberia','LR','231',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Libya','LY','218',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Liechtenstein','LI','423',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Lithuania','LT','370',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Luxembourg','LU','352',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Macau','MO','853',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Macedonia','MK','389',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Madagascar','MG','261',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Malawi','MW','265',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Malaysia','MY','60',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Maldives','MV','960',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Mali','ML','223',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Malta','MT','356',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Marshall Islands','MH','692',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Mauritania','MR','222',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Mauritius','MU','230',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Mayotte','YT','262',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Mexico','MX','52',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Micronesia','FM','691',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Moldova','MD','373',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Monaco','MC','377',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Mongolia','MN','976',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Montenegro','ME','382',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Montserrat','MS','1 664 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Morocco','MA','212',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Mozambique','MZ','258',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Namibia','NA','264',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Nauru','NR','674',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Nepal','NP','977',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Netherlands Antilles','AN','599',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Netherlands','NL','31',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'New Caledonia','NC','687',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'New Zealand','NZ','64',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Nicaragua','NI','505',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Niger','NE','227',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Nigeria','NG','234',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Niue','NU','683',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Norfolk Island','/ NFK ','672',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'North Korea','KP','850',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Northern Mariana Islands','MP','1 670 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Norway','NO','47',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Oman','OM','968',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Pakistan','PK','92',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Palau','PW','680',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Panama','PA','507',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Papua New Guinea','PG','675',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Paraguay','PY','595',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Peru','PE','51',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Philippines','PH','63',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Pitcairn Islands','PN','870',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Poland','PL','48',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Portugal','PT','351',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Puerto Rico','PR','1',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Qatar','QA','974',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Republic of the Congo','CG','242',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Romania','RO','40',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Russia','RU','7',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Rwanda','RW','250',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Saint Barthelemy','BL','590',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Saint Helena','SH','290',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Saint Kitts and Nevis','KN','1 869 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Saint Lucia','LC','1 758 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Saint Martin','MF','1 599 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Saint Pierre and Miquelon','PM','508',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Saint Vincent and the Grenadines','VC','1 784 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Samoa','WS','685',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'San Marino','SM','378',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Sao Tome and Principe','ST','239',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Saudi Arabia','SA','966',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Senegal','SN','221',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Serbia','RS','381',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Seychelles','SC','248',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Sierra Leone','SL','232',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Singapore','SG','65',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Slovakia','SK','421',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Slovenia','SI','386',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Solomon Islands','SB','677',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Somalia','SO','252',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'South Africa','ZA','27',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'South Korea','KR','82',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Spain','ES','34',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Sri Lanka','LK','94',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Sudan','SD','249',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Suriname','SR','597',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Swaziland','SZ','268',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Sweden','SE','46',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Switzerland','CH','41',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Syria','SY','963',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Taiwan','TW','886',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Tajikistan','TJ','992',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Tanzania','TZ','255',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Thailand','TH','66',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Timor-Leste','TL','670',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Togo','TG','228',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Tokelau','TK','690',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Tonga','TO','676',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Trinidad and Tobago','TT','1 868 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Tunisia','TN','216',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Turkey','TR','90',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Turkmenistan','TM','993',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Turks and Caicos Islands','TC','1 649 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Tuvalu','TV','688',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'US Virgin Islands','VI','1 340 ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Uganda','UG','256',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Ukraine','UA','380',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'United Arab Emirates','AE','971',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'United Kingdom','GB','44',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'United States','US','1',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Uruguay','UY','598',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Uzbekistan','UZ','998',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Vanuatu','VU','678',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Venezuela','VE','58',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Vietnam','VN','84',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Wallis and Futuna','WF','681',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Yemen','YE','967',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Zambia','ZM','260',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, longDescription, typeCode, shortDescription, created) VALUES (@typeIdVar,'Zimbabwe','ZW','263',CURRENT_TIMESTAMP);

/* Subject Areas */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Subject Areas','Cancer, Urology, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Subject Areas';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Urology', NULL, 'URO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Oncology', NULL, 'ONC', CURRENT_TIMESTAMP);

/* Journal Types */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Journal Types','PLOSOne, PLOS Biology, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Journal Types';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'PLOSOne', NULL, 'PL1', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'PLOS Genetics', NULL, 'PLGE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'PLOS Biology', NULL, 'PLBI', CURRENT_TIMESTAMP);

/* Country Types */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Country Types','United States, Canada, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Country Types';
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ANDORRA', 'AD', CURRENT_TIMESTAMP );
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'UNITED ARAB EMIRATES','AE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'AFGHANISTAN','AF', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ANTIGUA AND BARBUDA','AG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ANGUILLA','AI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'FRENCH AFARS AND ISSAS','AI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ALBANIA','AL', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ARMENIA','AM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NETHERLANDS ANTILLES','AN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ANGOLA','AO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ANTARCTICA','AQ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ARGENTINA','AR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'AMERICAN SAMOA','AS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'AUSTRIA','AT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'AUSTRALIA','AU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ARUBA','AW', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ÅLAND ISLANDS','AX', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'AZERBAIJAN','AZ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BOSNIA AND HERZEGOVINA','BA', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BARBADOS','BB', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BANGLADESH','BD', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BELGIUM','BE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BURKINA FASO','BF', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BULGARIA','BG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BAHRAIN','BH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BURUNDI','BI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BENIN','BJ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SAINT BARTHÉLEMY','BL', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BERMUDA','BM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BRUNEI DARUSSALAM','BN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BOLIVIA','BO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BRITISH ANTARCTIC TERRITORY','BQ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BONAIRE','BQ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BRAZIL','BR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BAHAMAS','BS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BHUTAN','BT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BURMA','BU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BOUVET ISLAND','BV', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BOTSWANA','BW', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BYELORUSSIAN SSR','BY', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BELARUS','BY', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BELIZE','BZ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CANADA','CA', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'COCOS (KEELING) ISLANDS','CC', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CONGO (THE DEMOCRATIC REPUBLIC OF THE)','CD', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CENTRAL AFRICAN REPUBLIC','CF', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CONGO','CG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SWITZERLAND','CH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CÔTE D''IVOIRE','CI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'COOK ISLANDS','CK', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CHILE','CL', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CAMEROON','CM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CHINA','CN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'COLOMBIA','CO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'COSTA RICA','CR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CZECHOSLOVAKIA','CS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SERBIA AND MONTENEGRO','CS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CANTON AND ENDERBURY ISLANDS','CT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CUBA','CU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CABO VERDE','CV', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CURAÇAO','CW', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CHRISTMAS ISLAND','CX', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CYPRUS','CY', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CZECH REPUBLIC','CZ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GERMAN DEMOCRATIC REPUBLIC','DD', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GERMANY','DE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'DJIBOUTI','DJ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'DENMARK','DK', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'DOMINICA','DM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'DOMINICAN REPUBLIC','DO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'DAHOMEY','DY', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ALGERIA','DZ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ECUADOR','EC', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ESTONIA','EE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'EGYPT','EG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'WESTERN SAHARA','EH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ERITREA','ER', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SPAIN','ES', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ETHIOPIA','ET', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'FINLAND','FI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'FIJI','FJ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'FALKLAND ISLANDS','FK', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MICRONESIA','FM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'FAROE ISLANDS','FO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'FRENCH SOUTHERN AND ANTARCTIC TERRITORIES','FQ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'FRANCE','FR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GABON','GA', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'UNITED KINGDOM','GB', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GRENADA','GD', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GILBERT AND ELLICE ISLANDS','GE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GEORGIA','GE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'FRENCH GUIANA','GF', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GUERNSEY','GG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GHANA','GH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GIBRALTAR','GI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GREENLAND','GL', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GAMBIA','GM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GUINEA','GN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GUADELOUPE','GP', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'EQUATORIAL GUINEA','GQ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GREECE','GR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS','GS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GUATEMALA','GT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GUAM','GU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GUINEA-BISSAU','GW', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'GUYANA','GY', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'HONG KONG','HK', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'HEARD ISLAND AND MCDONALD ISLANDS','HM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'HONDURAS','HN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CROATIA','HR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'HAITI','HT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'HUNGARY','HU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'UPPER VOLTA','HV', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'INDONESIA','ID', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'IRELAND','IE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ISRAEL','IL', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ISLE OF MAN','IM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'INDIA','IN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'BRITISH INDIAN OCEAN TERRITORY','IO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'IRAQ','IQ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'IRAN','IR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ICELAND','IS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ITALY','IT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'JERSEY','JE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'JAMAICA','JM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'JORDAN','JO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'JAPAN','JP', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'JOHNSTON ISLAND','JT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'KENYA','KE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'KYRGYZSTAN','KG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CAMBODIA','KH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'KIRIBATI','KI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'COMOROS','KM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SAINT KITTS AND NEVIS','KN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'KOREA (THE DEMOCRATIC PEOPLE''S REPUBLIC OF)','KP', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'KOREA','KR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'KUWAIT','KW', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CAYMAN ISLANDS','KY', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'KAZAKHSTAN','KZ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'LAO PEOPLE''S DEMOCRATIC REPUBLIC','LA', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'LEBANON','LB', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SAINT LUCIA','LC', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'LIECHTENSTEIN','LI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SRI LANKA','LK', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'LIBERIA','LR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'LESOTHO','LS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'LITHUANIA','LT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'LUXEMBOURG','LU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'LATVIA','LV', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'LIBYA','LY', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MOROCCO','MA', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MONACO','MC', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MOLDOVA (THE REPUBLIC OF)','MD', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MONTENEGRO','ME', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SAINT MARTIN (FRENCH PART)','MF', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MADAGASCAR','MG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MARSHALL ISLANDS','MH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MIDWAY ISLANDS','MI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MACEDONIA','MK', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MALI','ML', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MYANMAR','MM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MONGOLIA','MN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MACAO','MO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NORTHERN MARIANA ISLANDS','MP', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MARTINIQUE','MQ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MAURITANIA','MR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MONTSERRAT','MS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MALTA','MT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MAURITIUS','MU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MALDIVES','MV', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MALAWI','MW', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MEXICO','MX', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MALAYSIA','MY', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MOZAMBIQUE','MZ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NAMIBIA','NA', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NETHERLANDS','NL', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NEW CALEDONIA','NC', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NIGER','NE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NORFOLK ISLAND','NF', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NIGERIA','NG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NEW HEBRIDES','NH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NICARAGUA','NI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NETHERLANDS (THE)','NL', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NORWAY','NO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NEPAL','NP', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'DRONNING MAUD LAND','NQ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NAURU','NR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NEUTRAL ZONE','NT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NIUE','NU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'NEW ZEALAND','NZ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'OMAN','OM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PANAMA','PA', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PACIFIC ISLANDS','PC', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PERU','PE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'FRENCH POLYNESIA','PF', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PAPUA NEW GUINEA','PG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PHILIPPINES','PH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PAKISTAN','PK', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'POLAND','PL', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SAINT PIERRE AND MIQUELON','PM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PITCAIRN','PN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PUERTO RICO','PR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PALESTINE','PS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PORTUGAL','PT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'UNITED STATES PACIFIC ISLANDS','PU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PALAU','PW', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PARAGUAY','PY', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PANAMA CANAL ZONE','PZ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'QATAR','QA', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'RÉUNION','RE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SOUTHERN RHODESIA','RH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ROMANIA','RO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SERBIA','RS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'RUSSIAN FEDERATION','RU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'RWANDA','RW', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SAUDI ARABIA','SA', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SOLOMON ISLANDS','SB', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SEYCHELLES','SC', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SUDAN','SD', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SWEDEN','SE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SINGAPORE','SG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SAINT HELENA','SH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SLOVENIA','SI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SVALBARD AND JAN MAYEN','SJ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SLOVAKIA','SK', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SIKKIM','SK', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SIERRA LEONE','SL', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SAN MARINO','SM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SENEGAL','SN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SOMALIA','SO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SURINAME','SR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SOUTH SUDAN','SS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SAO TOME AND PRINCIPE','ST', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'USSR','SU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'EL SALVADOR','SV', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SINT MAARTEN (DUTCH PART)','SX', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SYRIAN ARAB REPUBLIC','SY', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SWAZILAND','SZ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TURKS AND CAICOS ISLANDS','TC', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CHAD','TD', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'FRENCH SOUTHERN TERRITORIES','TF', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TOGO','TG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'THAILAND','TH', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TAJIKISTAN','TJ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TOKELAU','TK', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TIMOR-LESTE','TL', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TURKMENISTAN','TM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TUNISIA','TN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TONGA','TO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'EAST TIMOR','TP', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TURKEY','TR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TRINIDAD AND TOBAGO','TT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TUVALU','TV', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TAIWAN','TW', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'TANZANIA','TZ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'UKRAINE','UA', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'UGANDA','UG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'UNITED STATES MINOR OUTLYING ISLANDS','UM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'UNITED STATES','US', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'URUGUAY','UY', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'UZBEKISTAN','UZ', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'VATICAN CITY STATE','VA', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SAINT VINCENT AND THE GRENADINES','VC', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'VENEZUELA','VE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'VIRGIN ISLANDS (British)','VG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'VIRGIN ISLANDS (U.S.)','VI', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'VIET NAM','VN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'VANUATU','VU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'WALLIS AND FUTUNA','WF', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'WAKE ISLAND','WK', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SAMOA','WS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'YEMEN','YE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MAYOTTE','YT', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'YUGOSLAVIA','YU', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'SOUTH AFRICA','ZA', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ZAMBIA','ZM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ZAIRE','ZR', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ZIMBABWE','ZW', CURRENT_TIMESTAMP);

/* Unique Identifier Types */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Unique Identifier Types','Ringgold, ORCID, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Unique Identifier Types';
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'Ringgold','RG', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'ORCID','ORC', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'Editorial Manager','EM', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'CAS','CAS', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'Salesforce','SF', CURRENT_TIMESTAMP);

/* State and Province Codes */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('State and Province Codes','CA, ONT, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='State and Province Codes';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar,'CA','California','CA',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar,'NY','New York','NY',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar,'ONT','Ontario','ONT',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar,'NJ','New Jersey','NJ',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar,'TX','Texas','TX',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar,'WV','West Virginia','WV',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar,'TN','Tennessee','TN',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar,'IN','Indiana','IN',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar,'Queensland','Australia','QNLD',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar,'Victoria','Australia','VICT',CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar,'Zhejiang','China','ZHEJ',CURRENT_TIMESTAMP);

/* Degrees */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Degrees','MD, PhD, etc');
SELECT typeId INTO @typeIdVar FROM typeDescriptions WHERE description='Degrees';
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'PhD','PHD', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId, shortDescription, typeCode, created) VALUES (@typeIdVar,'MD','MD', CURRENT_TIMESTAMP);
