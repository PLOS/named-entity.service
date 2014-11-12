
insert into userProfile (userProfileID, authId, userProfileURI, realName, givenNames, surName, title, email, displayName, suffix)
    values (5, 5, 'http://gooduri.com/5', 'realname5', 'givenname5', 'surname5', 'title5', 'bad@email', 'displayname5', 'suffix5');

insert into userOrcid (userProfileID, orcid) values (5,5);

insert into userProfileMetaData (userProfileMetaDataID, userProfileID, metaKey, metaValue) values (5, 5, 'GUID', 'PONE-5555');

insert into userProfileMetaData (userProfileMetaDataID, userProfileID, metaKey, metaValue) values (6, 5, 'profile_field_SalesforceID', 'SF-5555');

insert into userProfileRoleJoinTable (userRoleID, userProfileID) values (1, 5);

