
insert into userProfile (userProfileID, authId, userProfileURI, realName, givenNames, surName, title, email, displayName, suffix, password)
    values (1, '34MJNXIPNEX99YZZ4QOP1MTFBN3Y27KA', 'http://gooduri.com/1', 'realname', 'givenname1', 'surname1', 'title1', 'good@email.com', 'displayname1', 'suffix1', 'encryptedpassword');

insert into userOrcid (userProfileID, orcid) values (1,'0000-0002-9430-100X');

insert into userProfileMetaData (userProfileMetaDataID, userProfileID, metaKey, metaValue) values (1, 1, 'GUID', 'PONE-1111');

insert into userProfileMetaData (userProfileMetaDataID, userProfileID, metaKey, metaValue) values (2, 1, 'profile_field_SalesforceID', '101U000000NxN3Z');

insert into userProfileRoleJoinTable (userRoleID, userProfileID) values (1, 1);
