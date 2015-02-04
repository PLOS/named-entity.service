
/* ------------------------------------------------------------------------- */
/*  TYPE DESCRIPTIONS                                                        */
/* ------------------------------------------------------------------------- */

INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (1,'Named Party Types','Individual, Organization');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (2,'User Applications','Editorial Manager, Ambra, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (3,'Organization Types','University, Department, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (4,'Roles','Academic Editor, Author, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (5,'Named Party Prefixes','Ms, Dr, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (6,'Named Party Suffixes','II, III, Jr, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (7,'Languages','English, Mandarin, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (8,'Communication Methods','Phone, Email, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (9,'Telephone Number Types','Home, Mobile, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (10,'Email Address Types','Home, Work, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (11,'Physical Address Types','Home, Work, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (12,'Relationship Types','AE/Author, Manager/Subordinate, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (13,'Country Codes for Phone Numbers','01, 44, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (14,'Subject Areas','Cancer, Urology, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (15,'Journal Types','PLOSOne, PLOS Biology, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (16,'Country Types','United States, Canada, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (17,'Unique Identifier Types','Ringgold, ORCID, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (18,'State and Province Codes','CA, ONT, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (19,'Degrees','MD, PhD, etc');
INSERT INTO namedEntities.typeDescriptions(id,description,howUsed) VALUES (20,'Source Applications','Editorial Manager, Ambra, etc');

/* ------------------------------------------------------------------------- */
/*  GLOBAL TYPES                                                             */
/* ------------------------------------------------------------------------- */

INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (1,1,'Individual',NULL,'IND',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (2,1,'Organization',NULL,'ORG',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (3,2,'Editorial Manager',NULL,'EM',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (4,2,'Ambra',NULL,'AMB',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (5,3,'Research Hospital',NULL,'HOSP',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (6,3,'University',NULL,'UNIV',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (7,3,'Department',NULL,'DEPT',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (8,4,'Author',NULL,'AUTH',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (9,4,'Co-Author',NULL,'COAU',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (10,4,'Academic Editor (PLOS ONE)',NULL,'AE_PLOSONE',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (11,4,'Reviewer',NULL,'REV',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (12,5,'Mr.',NULL,'MR',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (13,5,'Mrs.',NULL,'MRS',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (14,5,'Dr.',NULL,'DR',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (15,5,'Ms.',NULL,'MS',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (16,6,'III',NULL,'III',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (17,6,'II',NULL,'II',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (18,19,'PhD',NULL,'PHD',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (19,19,'MD',NULL,'MD',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (20,7,'English',NULL,'ENG',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (21,7,'Mandarin',NULL,'MAN',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (22,7,'Italian',NULL,'ITAL',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (23,7,'Spanish',NULL,'SPAN',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (24,7,'French',NULL,'FREN',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (25,8,'Phone',NULL,'PH',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (26,8,'Email',NULL,'EMAI',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (27,8,'Fax',NULL,'FAX',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (28,9,'Home',NULL,'HOME',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (29,9,'Office',NULL,'OFF',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (30,9,'Mobile',NULL,'CELL',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (31,10,'Personal',NULL,'PERS',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (32,10,'Work',NULL,'WORK',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (33,11,'Office',NULL,'WORK',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (34,11,'Home',NULL,'HOME',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (35,12,'Manager-Subordinate',NULL,'MGR',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (36,12,'AE-Author',NULL,'AEAU',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (37,12,'Organization-Author',NULL,'ORAU',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (38,12,'AE-Reviewer',NULL,'AERV',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (39,13,'01','United States','US',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (40,13,'44','United Kingdom','UK',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (41,14,'Urology',NULL,'URO',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (42,14,'Oncology',NULL,'ONC',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (43,15,'PLOSOne',NULL,'PL1',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (44,15,'PLOS Genetics',NULL,'PLGE',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (45,15,'PLOS Biology',NULL,'PLBI',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (46,16,'Canada',NULL,'CA',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (47,16,'United States of America',NULL,'US',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (48,16,'United Kingdom of Great Britain and Northern Ireland',NULL,'GB',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (49,17,'Ringgold',NULL,'RG',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (50,17,'ORCID',NULL,'ORC',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (51,18,'CA','California','CA',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (52,18,'NY','New York','NY',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (53,18,'ONT','Ontario','ONT',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (54,4,'NP System Administrator',NULL,'SANP',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (55,2,'Named Party DB',NULL,'NPDB',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (56,5,'Professor',NULL,'PROF',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (57,13,'886','Taiwan','TW',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (58,13,'31','Netherlands','NL',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (59,13,'49','Germany','DE',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (60,13,'86','China','CN',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (61,13,'61','Australia','AU',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (62,18,'NJ','New Jersey','NJ',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (63,18,'TX','Texas','TX',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (64,18,'WV','West Virginia','WV',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (65,18,'TN','Tennessee','TN',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (66,18,'IN','Indiana','IN',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (67,18,'Queensland','Australia','QNLD',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (68,18,'Victoria','Australia','VICT',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (69,18,'Zhejiang','China','ZHEJ',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (70,16,'Taiwan',NULL,'TW',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (71,16,'Netherlands',NULL,'NL',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (72,16,'Germany',NULL,'DE',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (73,16,'Australia',NULL,'AU',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (74,16,'China',NULL,'CN',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (75,17,'Editorial Manager',NULL,'EM',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (76,17,'CAS',NULL,'CAS',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (77,17,'Salesforce',NULL,'SF',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (78,20,'Editorial Manager',NULL,'EM',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (79,20,'Ambra',NULL,'AMB',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (80,20,'Salesforce',NULL,'SF',1,1);
INSERT INTO namedEntities.globalTypes(id, typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
	VALUES (81,17,'Ambra',NULL,'AMB',1,1);

INSERT INTO namedEntities.namedEntityIdentifiers(id,typeId) VALUES (1,1);

INSERT INTO namedEntities.individualProfiles (id, nedId, firstName, lastName, displayName, biography, sourceTypeId, isActive)
    VALUES (1,1,'NED','NED','NED','bio',78,1);
