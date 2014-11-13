
insert into userProfile (userProfileID, authId, userProfileURI, realName, givenNames, surName, title, email, displayName, suffix)
    values (1, 1, 'http://gooduri.com/1', 'realname', 'givenname1', 'surname1', 'title1', 'good@email.com', 'displayname1', 'suffix1');

insert into userOrcid (userProfileID, orcid) values (1,1);

insert into userProfileMetaData (userProfileMetaDataID, userProfileID, metaKey, metaValue) values (1, 1, 'GUID', 'PONE-1111');

insert into userProfileMetaData (userProfileMetaDataID, userProfileID, metaKey, metaValue) values (2, 1, 'profile_field_SalesforceID', 'SF-1111');

insert into userProfileRoleJoinTable (userRoleID, userProfileID) values (1, 1);

