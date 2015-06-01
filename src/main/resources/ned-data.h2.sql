
/* Named Party Types */
INSERT INTO namedEntities.typeDescriptions(description,howUsed) VALUES ('Named Party Types','Individual, Organization');
INSERT INTO namedEntities.globalTypes(typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
    VALUES ((select max(id) from namedEntities.typeDescriptions),'Individual',NULL,'IND',1,1);
INSERT INTO namedEntities.globalTypes(typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
    VALUES ((select max(id) from namedEntities.typeDescriptions),'Organization',NULL,'ORG',1,1);

/* User Applications */
INSERT INTO namedEntities.typeDescriptions(description,howUsed) VALUES ('User Applications','Editorial Manager, Ambra, etc');
INSERT INTO namedEntities.globalTypes(typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
    VALUES ((select max(id) from namedEntities.typeDescriptions),'Editorial Manager',NULL,'EM',1,1);
INSERT INTO namedEntities.globalTypes(typeId, shortDescription, longDescription, typeCode, createdBy, lastModifiedBy)
    VALUES ((select max(id) from namedEntities.typeDescriptions),'Ambra',NULL,'AMB',1,1);

/* Source Applications */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Source Applications','Editorial Manager, Ambra, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Editorial Manager', NULL, 'EM');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Ambra', NULL, 'AMB');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Salesforce', NULL, 'SF');

/* Organization Types */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Organization Types','University, Department, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Research Hospital', NULL, 'HOSP');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'University', NULL, 'UNIV');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Department', NULL, 'DEPT');

/* Roles */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Roles','Academic Editor, Author, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Author', NULL, 'AUTH');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Co-Author', NULL, 'COAU');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Academic Editor (PLOS ONE)', NULL, 'AE_PLOSONE');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'NP System Administrator', NULL, 'SANP');

/* Named Party Prefixes */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Named Party Prefixes','Ms, Dr, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Mr.', NULL, 'MR');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Mrs.', NULL, 'MRS');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Dr.', NULL, 'DR');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Ms.', NULL, 'MS');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Professor', NULL, 'PROF');

/* Named Party Suffixes */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Named Party Suffixes','II, III, Jr, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'III', NULL, 'III');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'II', NULL, 'II');

/* Languages */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Languages','English, Mandarin, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'English', NULL, 'ENG');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Mandarin', NULL, 'MAN');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Italian', NULL, 'ITAL');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Spanish', NULL, 'SPAN');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'French', NULL, 'FREN');

/* Communication Methods */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Communication Methods','Phone, Email, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Phone', NULL, 'PH');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Email', NULL, 'EMAI');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Fax', NULL, 'FAX');

/* Telephone Number Types */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Telephone Number Types','Home, Mobile, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Home', NULL, 'HOME');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Office', NULL, 'OFF');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Mobile', NULL, 'CELL');

/* Email Address Types */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Email Address Types','Home, Work, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Personal', NULL, 'PERS');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Work', NULL, 'WORK');

/* Physical Address Types */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Physical Address Types','Home, Work, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Office', NULL, 'WORK');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Home', NULL, 'HOME');

/* Relationship Types */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Relationship Types','AE/Author, Manager/Subordinate, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Manager-Subordinate', NULL, 'MGR');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'AE-Author', NULL, 'AEAU');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Organization-Author', NULL, 'ORAU');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'AE-Reviewer', NULL, 'AERV');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Individual Affiliated with Organization', NULL, 'I-AFF-O');

/* Country Codes for Phone Numbers */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Country Codes for Phone Numbers','01, 44, etc');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Afghanistan','AF','93');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Albania','AL','355');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Algeria','DZ','213');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'American Samoa','AS','1 684 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Andorra','AD','376');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Angola','AO','244');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Anguilla','AI','1 264 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Antarctica','AQ','672');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Antigua and Barbuda','AG','1 268 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Argentina','AR','54');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Armenia','AM','374');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Aruba','AW','297');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Australia','AU','61');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Austria','AT','43');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Azerbaijan','AZ','994');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Bahamas','BS','1 242 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Bahrain','BH','973');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Bangladesh','BD','880');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Barbados','BB','1 246 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Belarus','BY','375');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Belgium','BE','32');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Belize','BZ','501');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Benin','BJ','229');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Bermuda','BM','1 441 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Bhutan','BT','975');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Bolivia','BO','591');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Bosnia and Herzegovina','BA','387');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Botswana','BW','267');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Brazil','BR','55');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'British Virgin Islands','VG','1 284 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Brunei','BN','673');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Bulgaria','BG','359');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Burkina Faso','BF','226');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Burma (Myanmar)','MM','95');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Burundi','BI','257');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Cambodia','KH','855');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Cameroon','CM','237');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Canada','CA','1');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Cape Verde','CV','238');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Cayman Islands','KY','1 345 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Central African Republic','CF','236');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Chad','TD','235');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Chile','CL','56');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'China','CN','86');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Christmas Island','CX','61');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Cocos (Keeling) Islands','CC','61');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Colombia','CO','57');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Comoros','KM','269');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Cook Islands','CK','682');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Costa Rica','CR','506');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Croatia','HR','385');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Cuba','CU','53');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Cyprus','CY','357');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Czech Republic','CZ','420');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Democratic Republic of the Congo','CD','243');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Denmark','DK','45');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Djibouti','DJ','253');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Dominica','DM','1 767 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Dominican Republic','DO','1 809 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Ecuador','EC','593');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Egypt','EG','20');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'El Salvador','SV','503');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Equatorial Guinea','GQ','240');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Eritrea','ER','291');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Estonia','EE','372');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Ethiopia','ET','251');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Falkland Islands','FK','500');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Faroe Islands','FO','298');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Fiji','FJ','679');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Finland','FI','358');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'France','FR','33');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'French Polynesia','PF','689');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Gabon','GA','241');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Gambia','GM','220');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Georgia','GE','995');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Germany','DE','49');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Ghana','GH','233');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Gibraltar','GI','350');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Greece','GR','30');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Greenland','GL','299');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Grenada','GD','1 473 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Guam','GU','1 671 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Guatemala','GT','502');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Guinea','GN','224');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Guinea-Bissau','GW','245');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Guyana','GY','592');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Haiti','HT','509');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Holy See (Vatican City)','VA','39');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Honduras','HN','504');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Hong Kong','HK','852');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Hungary','HU','36');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Iceland','IS','354');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'India','IN','91');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Indonesia','ID','62');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Iran','IR','98');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Iraq','IQ','964');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Ireland','IE','353');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Isle of Man','IM','44');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Israel','IL','972');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Italy','IT','39');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Ivory Coast','CI','225');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Jamaica','JM','1 876 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Japan','JP','81');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Jordan','JO','962');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Kazakhstan','KZ','7');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Kenya','KE','254');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Kiribati','KI','686');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Kosovo','/  ','381');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Kuwait','KW','965');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Kyrgyzstan','KG','996');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Laos','LA','856');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Latvia','LV','371');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Lebanon','LB','961');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Lesotho','LS','266');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Liberia','LR','231');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Libya','LY','218');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Liechtenstein','LI','423');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Lithuania','LT','370');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Luxembourg','LU','352');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Macau','MO','853');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Macedonia','MK','389');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Madagascar','MG','261');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Malawi','MW','265');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Malaysia','MY','60');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Maldives','MV','960');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Mali','ML','223');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Malta','MT','356');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Marshall Islands','MH','692');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Mauritania','MR','222');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Mauritius','MU','230');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Mayotte','YT','262');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Mexico','MX','52');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Micronesia','FM','691');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Moldova','MD','373');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Monaco','MC','377');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Mongolia','MN','976');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Montenegro','ME','382');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Montserrat','MS','1 664 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Morocco','MA','212');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Mozambique','MZ','258');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Namibia','NA','264');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Nauru','NR','674');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Nepal','NP','977');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Netherlands Antilles','AN','599');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Netherlands','NL','31');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'New Caledonia','NC','687');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'New Zealand','NZ','64');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Nicaragua','NI','505');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Niger','NE','227');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Nigeria','NG','234');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Niue','NU','683');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Norfolk Island','/ NFK ','672');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'North Korea','KP','850');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Northern Mariana Islands','MP','1 670 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Norway','NO','47');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Oman','OM','968');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Pakistan','PK','92');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Palau','PW','680');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Panama','PA','507');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Papua New Guinea','PG','675');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Paraguay','PY','595');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Peru','PE','51');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Philippines','PH','63');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Pitcairn Islands','PN','870');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Poland','PL','48');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Portugal','PT','351');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Puerto Rico','PR','1');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Qatar','QA','974');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Republic of the Congo','CG','242');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Romania','RO','40');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Russia','RU','7');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Rwanda','RW','250');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Saint Barthelemy','BL','590');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Saint Helena','SH','290');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Saint Kitts and Nevis','KN','1 869 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Saint Lucia','LC','1 758 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Saint Martin','MF','1 599 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Saint Pierre and Miquelon','PM','508');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Saint Vincent and the Grenadines','VC','1 784 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Samoa','WS','685');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'San Marino','SM','378');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Sao Tome and Principe','ST','239');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Saudi Arabia','SA','966');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Senegal','SN','221');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Serbia','RS','381');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Seychelles','SC','248');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Sierra Leone','SL','232');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Singapore','SG','65');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Slovakia','SK','421');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Slovenia','SI','386');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Solomon Islands','SB','677');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Somalia','SO','252');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'South Africa','ZA','27');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'South Korea','KR','82');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Spain','ES','34');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Sri Lanka','LK','94');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Sudan','SD','249');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Suriname','SR','597');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Swaziland','SZ','268');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Sweden','SE','46');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Switzerland','CH','41');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Syria','SY','963');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Taiwan','TW','886');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Tajikistan','TJ','992');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Tanzania','TZ','255');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Thailand','TH','66');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Timor-Leste','TL','670');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Togo','TG','228');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Tokelau','TK','690');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Tonga','TO','676');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Trinidad and Tobago','TT','1 868 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Tunisia','TN','216');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Turkey','TR','90');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Turkmenistan','TM','993');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Turks and Caicos Islands','TC','1 649 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Tuvalu','TV','688');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'US Virgin Islands','VI','1 340 ');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Uganda','UG','256');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Ukraine','UA','380');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'United Arab Emirates','AE','971');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'United Kingdom','GB','44');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'United States','US','01');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Uruguay','UY','598');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Uzbekistan','UZ','998');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Vanuatu','VU','678');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Venezuela','VE','58');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Vietnam','VN','84');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Wallis and Futuna','WF','681');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Yemen','YE','967');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Zambia','ZM','260');
INSERT INTO namedEntities.globalTypes (typeId, longDescription, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'Zimbabwe','ZW','263');

/* Subject Areas */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Subject Areas','Cancer, Urology, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Urology', NULL, 'URO');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'Oncology', NULL, 'ONC');

/* Journal Types */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Journal Types','PLOSOne, PLOS Biology, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'PLOSOne', NULL, 'PL1');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'PLOS Genetics', NULL, 'PLGE');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions), 'PLOS Biology', NULL, 'PLBI');

/* Country Types */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Country Types','United States, Canada, etc');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AD','Andorra');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AE','United Arab Emirates');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AF','Afghanistan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AG','Antigua and Barbuda');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AI','Anguilla');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AL','Albania');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AM','Armenia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AO','Angola');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AQ','Antarctica');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AR','Argentina');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AS','American Samoa');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AT','Austria');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AU','Australia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AW','Aruba');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AX','Åland Islands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'AZ','Azerbaijan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BA','Bosnia and Herzegovina');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BB','Barbados');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BD','Bangladesh');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BE','Belgium');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BF','Burkina Faso');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BG','Bulgaria');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BH','Bahrain');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BI','Burundi');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BJ','Benin');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BL','Saint Barthélemy');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BM','Bermuda');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BN','Brunei Darussalam');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BO','Bolivia (Plurinational State of)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BQ','Bonaire, Sint Eustatius and Saba');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BR','Brazil');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BS','Bahamas');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BT','Bhutan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BV','Bouvet Island');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BW','Botswana');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BY','Belarus');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'BZ','Belize');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CA','Canada');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CC','Cocos (Keeling) Islands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CD','Congo (the Democratic Republic of the)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CF','Central African Republic');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CG','Congo');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CH','Switzerland');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CI','Côte d''Ivoire');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CK','Cook Islands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CL','Chile');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CM','Cameroon');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CN','China');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CO','Colombia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CR','Costa Rica');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CU','Cuba');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CV','Cabo Verde');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CW','Curaçao');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CX','Christmas Island');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CY','Cyprus');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'CZ','Czech Republic');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'DE','Germany');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'DJ','Djibouti');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'DK','Denmark');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'DM','Dominica');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'DO','Dominican Republic');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'DZ','Algeria');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'EC','Ecuador');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'EE','Estonia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'EG','Egypt');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'EH','Western Sahara');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'ER','Eritrea');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'ES','Spain');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'ET','Ethiopia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'FI','Finland');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'FJ','Fiji');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'FK','Falkland Islands (Malvinas)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'FM','Micronesia (Federated States of)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'FO','Faroe Islands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'FR','France');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GA','Gabon');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GB','United Kingdom of Great Britain and Northern Ireland');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GD','Grenada');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GE','Georgia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GF','French Guiana');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GG','Guernsey');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GH','Ghana');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GI','Gibraltar');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GL','Greenland');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GM','Gambia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GN','Guinea');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GP','Guadeloupe');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GQ','Equatorial Guinea');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GR','Greece');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GS','South Georgia and the South Sandwich Islands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GT','Guatemala');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GU','Guam');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GW','Guinea-Bissau');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'GY','Guyana');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'HK','Hong Kong');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'HM','Heard Island and McDonald Islands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'HN','Honduras');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'HR','Croatia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'HT','Haiti');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'HU','Hungary');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'ID','Indonesia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'IE','Ireland');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'IL','Israel');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'IM','Isle of Man');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'IN','India');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'IO','British Indian Ocean Territory');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'IQ','Iraq');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'IR','Iran (Islamic Republic of)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'IS','Iceland');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'IT','Italy');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'JE','Jersey');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'JM','Jamaica');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'JO','Jordan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'JP','Japan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'KE','Kenya');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'KG','Kyrgyzstan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'KH','Cambodia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'KI','Kiribati');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'KM','Comoros');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'KN','Saint Kitts and Nevis');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'KP','Korea (the Democratic People''s Republic of)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'KR','Korea (the Republic of)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'KW','Kuwait');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'KY','Cayman Islands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'KZ','Kazakhstan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'LA','Lao People''s Democratic Republic');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'LB','Lebanon');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'LC','Saint Lucia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'LI','Liechtenstein');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'LK','Sri Lanka');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'LR','Liberia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'LS','Lesotho');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'LT','Lithuania');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'LU','Luxembourg');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'LV','Latvia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'LY','Libya');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MA','Morocco');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MC','Monaco');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MD','Moldova (the Republic of)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'ME','Montenegro');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MF','Saint Martin (French part)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MG','Madagascar');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MH','Marshall Islands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MK','Macedonia (the former Yugoslav Republic of)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'ML','Mali');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MM','Myanmar');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MN','Mongolia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MO','Macao');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MP','Northern Mariana Islands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MQ','Martinique');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MR','Mauritania');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MS','Montserrat');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MT','Malta');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MU','Mauritius');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MV','Maldives');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MW','Malawi');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MX','Mexico');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MY','Malaysia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'MZ','Mozambique');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'NA','Namibia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'NC','New Caledonia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'NE','Niger');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'NF','Norfolk Island');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'NG','Nigeria');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'NI','Nicaragua');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'NL','Netherlands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'NO','Norway');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'NP','Nepal');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'NR','Nauru');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'NU','Niue');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'NZ','New Zealand');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'OM','Oman');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PA','Panama');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PE','Peru');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PF','French Polynesia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PG','Papua New Guinea');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PH','Philippines');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PK','Pakistan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PL','Poland');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PM','Saint Pierre and Miquelon');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PN','Pitcairn');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PR','Puerto Rico');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PS','Palestine, State of');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PT','Portugal');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PW','Palau');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'PY','Paraguay');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'QA','Qatar');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'RE','Réunion');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'RO','Romania');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'RS','Serbia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'RU','Russian Federation');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'RW','Rwanda');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SA','Saudi Arabia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SB','Solomon Islands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SC','Seychelles');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SD','Sudan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SE','Sweden');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SG','Singapore');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SH','Saint Helena, Ascension and Tristan da Cunha');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SI','Slovenia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SJ','Svalbard and Jan Mayen');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SK','Slovakia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SL','Sierra Leone');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SM','San Marino');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SN','Senegal');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SO','Somalia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SR','Suriname');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SS','South Sudan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'ST','Sao Tome and Principe');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SV','El Salvador');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SX','Sint Maarten (Dutch part)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SY','Syrian Arab Republic');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'SZ','Swaziland');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TC','Turks and Caicos Islands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TD','Chad');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TF','French Southern Territories');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TG','Togo');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TH','Thailand');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TJ','Tajikistan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TK','Tokelau');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TL','Timor-Leste');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TM','Turkmenistan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TN','Tunisia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TO','Tonga');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TR','Turkey');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TT','Trinidad and Tobago');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TV','Tuvalu');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TW','Taiwan (Province of China)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'TZ','Tanzania, United Republic of');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'UA','Ukraine');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'UG','Uganda');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'UM','United States Minor Outlying Islands');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'US','United States of America');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'UY','Uruguay');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'UZ','Uzbekistan');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'VA','Holy See');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'VC','Saint Vincent and the Grenadines');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'VE','Venezuela (Bolivarian Republic of)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'VG','Virgin Islands (British)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'VI','Virgin Islands (U.S.)');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'VN','Viet Nam');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'VU','Vanuatu');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'WF','Wallis and Futuna');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'WS','Samoa');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'YE','Yemen');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'YT','Mayotte');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'ZA','South Africa');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'ZM','Zambia');
INSERT INTO namedEntities.globalTypes (typeId, typeCode, shortDescription) VALUES ((select max(id) from namedEntities.typeDescriptions),'ZW','Zimbabwe');

/* Unique Identifier Types (INDIVIDUAL) */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('UID Individual Types','ORCID, etc');
INSERT INTO namedEntities.globalTypes (typeId, shortDescription, typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'ORCID','ORC');
INSERT INTO namedEntities.globalTypes (typeId, shortDescription, typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'Editorial Manager','EM');
INSERT INTO namedEntities.globalTypes (typeId, shortDescription, typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'Salesforce','SF');
INSERT INTO namedEntities.globalTypes (typeId, shortDescription, typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'Ambra','AMB');

/* Unique Identifier Types (ORGANIZATION) */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('UID Organization Types','Ringgold, etc');
INSERT INTO namedEntities.globalTypes (typeId, shortDescription, typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'Ringgold','RG');

/* State and Province Codes */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('State and Province Codes','CA, ONT, etc');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'CA','California','CA');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'NY','New York','NY');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'ONT','Ontario','ONT');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'NJ','New Jersey','NJ');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'TX','Texas','TX');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'WV','West Virginia','WV');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'TN','Tennessee','TN');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'IN','Indiana','IN');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'Queensland','Australia','QNLD');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'Victoria','Australia','VICT');
INSERT INTO namedEntities.globalTypes (typeId,shortDescription,longDescription,typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'Zhejiang','China','ZHEJ');

/* Degrees */
INSERT INTO namedEntities.typeDescriptions(description, howUsed) VALUES ('Degrees','MD, PhD, etc');
INSERT INTO namedEntities.globalTypes (typeId, shortDescription, typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'PhD','PHD');
INSERT INTO namedEntities.globalTypes (typeId, shortDescription, typeCode) VALUES ((select max(id) from namedEntities.typeDescriptions),'MD','MD');

/* Seed Individual Entity */
INSERT INTO namedEntities.namedEntityIdentifiers(id,typeId)
  VALUES (1, (select gt.id from namedEntities.globalTypes gt
                join namedEntities.typeDescriptions td on gt.typeid = td.id
               where td.description='Named Party Types' and gt.shortDescription='Individual'));

INSERT INTO namedEntities.individualProfiles (id, nedId, firstName, lastName, displayName, biography, isActive, sourceTypeId)
  VALUES (1,1,'NED','NED','NED','bio',1,
    (select gt.id from namedEntities.globalTypes gt
       join namedEntities.typeDescriptions td on gt.typeid = td.id
      where td.description='Source Applications' and gt.shortDescription='Ambra'));

/* Seed Organization Entity */
INSERT INTO namedEntities.namedEntityIdentifiers(id,typeId)
  VALUES (2, (select gt.id from namedEntities.globalTypes gt
                join namedEntities.typeDescriptions td on gt.typeid = td.id
               where td.description='Named Party Types' and gt.shortDescription='Organization'));

INSERT INTO  namedEntities.organizations (id, nedId, familiarName, legalName, isActive, sourceTypeId)
  VALUES (1,2,'ABC Inc (FN)','ABC Inc (LN)',1,
    (select gt.id from namedEntities.globalTypes gt
       join namedEntities.typeDescriptions td on gt.typeid = td.id
      where td.description='Source Applications' and gt.shortDescription='Ambra'));

/* Consumers */
INSERT INTO namedEntities.consumers (name, password) VALUES ('tahi' ,/*tahi*/  '$2a$04$8HIFWewGAkrJDGAvCktCUunEm0Mb2Tz100zGRcJ.SVMQqqkGXZt0G');
INSERT INTO namedEntities.consumers (name, password) VALUES ('akita',/*akita*/ '$2a$04$bhCH3yv8auaZpzW0ZGwrHOdIkJpFQwCABXZnd8fo7B4LyIE1MMxve');
INSERT INTO namedEntities.consumers (name, password) VALUES ('etl'  ,/*etl*/   '$2a$04$.sb5zU1tnhxJc2.4IvAu3uahi6NZdjNWzcDkxttY22JFrHc/IgrKu');
