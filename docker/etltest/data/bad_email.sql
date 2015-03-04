
insert into userProfile (userProfileID, authId, userProfileURI, realName, givenNames, surName, title, email, displayName, suffix)
    values (5, '14MJNXIPNEX99YZZ4QOP1MTFBN3Y27KA', 'http://gooduri.com/5', 'realname5', 'givenname5', 'surname5', 'title5', 'bad@email', 'displayname5', 'suffix5');

insert into userOrcid (userProfileID, orcid) values (5, '1000-0002-9430-100X');

insert into userProfileMetaData (userProfileMetaDataID, userProfileID, metaKey, metaValue) values (5, 5, 'GUID', 'PONE-5555');

insert into userProfileMetaData (userProfileMetaDataID, userProfileID, metaKey, metaValue) values (6, 5, 'profile_field_SalesforceID', '201U000000NxN3Z');

insert into userProfileRoleJoinTable (userRoleID, userProfileID) values (1, 5);

