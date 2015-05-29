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
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
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
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
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
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    createdBy INT NULL,
    lastModifiedBy INT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.individualProfiles;
CREATE TABLE IF NOT EXISTS namedEntities.individualProfiles (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    firstName TEXT NULL,
    middleName TEXT NULL,
    lastName TEXT NULL,
    nickName TEXT NULL,
    namePrefixTypeId INT NULL,
    nameSuffixTypeId INT NULL,
    displayName VARCHAR(255) NULL,
    biography TEXT NULL,
    sourceTypeId INT NOT NULL,
    isActive TINYINT(1) NOT NULL DEFAULT 1,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (sourceTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (namePrefixTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (nameSuffixTypeId) REFERENCES globalTypes(id),
    UNIQUE (displayName, sourceTypeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.organizations;
CREATE TABLE IF NOT EXISTS namedEntities.organizations (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NULL,
    familiarName TEXT NOT NULL,
    legalName VARCHAR(255) NOT NULL,
    mainContactId INT NULL,
    sourceTypeId INT NOT NULL,
    isActive TINYINT(1) NOT NULL DEFAULT 1,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id),
    FOREIGN KEY (sourceTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (mainContactId) REFERENCES individualProfiles(nedId),
    UNIQUE (legalName, sourceTypeId)
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
    sourceTypeId INT NOT NULL,
    isActive TINYINT(1) NOT NULL DEFAULT 1,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (sourceTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.emails;
CREATE TABLE IF NOT EXISTS namedEntities.emails (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NULL,
    emailAddress VARCHAR(255) NOT NULL,
    sourceTypeId INT NOT NULL,
    verified TINYINT(1) NOT NULL DEFAULT 0,
    isActive TINYINT(1) NOT NULL DEFAULT 1,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (sourceTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id),
    UNIQUE (emailAddress, sourceTypeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.phoneNumbers;
CREATE TABLE IF NOT EXISTS namedEntities.phoneNumbers (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NULL,
    countryCodeTypeId INT NULL,
    phoneNumber TEXT NULL,
    extension TEXT NULL,
    sourceTypeId INT NOT NULL,
    isActive TINYINT(1) NOT NULL DEFAULT 1,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id),
    FOREIGN KEY (sourceTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (countryCodeTypeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.roles;
CREATE TABLE IF NOT EXISTS namedEntities.roles (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeID INT NOT NULL,
    applicationTypeId INT NULL,
    startDate DATE NULL,
    endDate DATE NULL,
    sourceTypeId INT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    createdBy INT NULL,
    lastModifiedBy INT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id),
    FOREIGN KEY (sourceTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (applicationTypeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.relationships;
CREATE TABLE IF NOT EXISTS namedEntities.relationships (
    id INT NOT NULL AUTO_INCREMENT,
    typeId INT NOT NULL,
    nedId INT NOT NULL,
    nedIdRelated INT NOT NULL,
    title TEXT NULL,
    sourceTypeId INT NOT NULL,
    startDate DATE NULL,
    endDate DATE NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    createdBy INT NULL,
    lastModifiedBy INT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (nedIdRelated) REFERENCES namedEntityIdentifiers(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.sourceFields;
CREATE TABLE IF NOT EXISTS namedEntities.sourceFields (
    id INT NOT NULL AUTO_INCREMENT,
    sourceTable TEXT NOT NULL,
    sourceField TEXT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.auditTrail;
CREATE TABLE IF NOT EXISTS namedEntities.auditTrail (
    id INT NOT NULL AUTO_INCREMENT,
    sourceFieldId INT NOT NULL,
    rowNumber INT NULL,
    oldValue TEXT NOT NULL,
    newValue TEXT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    lastModifiedBy INT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (sourceFieldId) REFERENCES sourceFields(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.subjectAreas;
CREATE TABLE IF NOT EXISTS namedEntities.subjectAreas (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.journals;
CREATE TABLE IF NOT EXISTS namedEntities.journals (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.degrees;
CREATE TABLE IF NOT EXISTS namedEntities.degrees (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NOT NULL,
    sourceTypeId INT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (sourceTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.urls;
CREATE TABLE IF NOT EXISTS namedEntities.urls (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    url TEXT NOT NULL,
    sourceTypeId INT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (sourceTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.uniqueIdentifiers;
CREATE TABLE IF NOT EXISTS namedEntities.uniqueIdentifiers (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    typeId INT NOT NULL,
    uniqueIdentifier VARCHAR(255) NOT NULL,
    sourceTypeId INT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (sourceTypeId) REFERENCES globalTypes(id),
    FOREIGN KEY (typeId) REFERENCES globalTypes(id),
    UNIQUE (uniqueIdentifier, sourceTypeId, typeId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.authCas;
CREATE TABLE IF NOT EXISTS namedEntities.authCas (
    id INT NOT NULL AUTO_INCREMENT,
    nedId INT NOT NULL,
    emailId INT NOT NULL,
    authId VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    passwordReset TINYINT(1) NOT NULL DEFAULT 0,
    verificationToken VARCHAR(255) NULL,
    verified TINYINT(1) NOT NULL DEFAULT 0,
    isActive TINYINT(1) NOT NULL DEFAULT 1,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (nedId) REFERENCES namedEntityIdentifiers(id),
    FOREIGN KEY (emailId) REFERENCES emails(id),
    UNIQUE (authId)
)   ENGINE=INNODB;

DROP TABLE IF EXISTS namedEntities.consumers;
CREATE TABLE IF NOT EXISTS namedEntities.consumers (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastModified TIMESTAMP NOT NULL AS CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE (name)
)   ENGINE=INNODB;
