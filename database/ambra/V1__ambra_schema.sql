CREATE TABLE ambra.userProfile (
    userProfileID bigint(20) NOT NULL AUTO_INCREMENT,
    userProfileURI varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    authId varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    realName varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    givenNames varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    surName varchar(65) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    title varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    gender varchar(15) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    email varchar(255) DEFAULT NULL,
    homePage varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    weblog varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    publications varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    displayName varchar(255) DEFAULT NULL,
    suffix varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    positionType varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    organizationName varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    organizationType varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    organizationVisibility tinyint(1) NOT NULL DEFAULT '0',
    postalAddress text CHARACTER SET utf8 COLLATE utf8_bin,
    city varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    country varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    biography text CHARACTER SET utf8 COLLATE utf8_bin,
    interests text CHARACTER SET utf8 COLLATE utf8_bin,
    researchAreas text CHARACTER SET utf8 COLLATE utf8_bin,
    alertsJournals text CHARACTER SET utf8 COLLATE utf8_bin,
    created datetime NOT NULL,
    lastModified datetime NOT NULL,
    password varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    passwordReset bit(1) NOT NULL DEFAULT b'0',
    verificationToken varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    verified bit(1) NOT NULL DEFAULT b'1',
    active bit(1) NOT NULL DEFAULT b'1',
    PRIMARY KEY (userProfileID),
    UNIQUE KEY userProfileURI (userProfileURI),
    UNIQUE KEY authId (authId),
    UNIQUE KEY email (email),
    UNIQUE KEY displayName (displayName)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE ambra.userProfileMetaData (
    userProfileMetaDataID bigint(20) NOT NULL AUTO_INCREMENT,
    userProfileID bigint(20) NOT NULL,
    metaKey varchar(50) COLLATE utf8_bin NOT NULL,
    metaValue varchar(255) COLLATE utf8_bin DEFAULT NULL,
    lastModified datetime NOT NULL,
    created datetime NOT NULL,
    PRIMARY KEY (userProfileMetaDataID),
    UNIQUE KEY userProfileID (userProfileID,metaKey),
    KEY metaKey (metaKey),
    CONSTRAINT userProfileMetaData_ibfk_1 FOREIGN KEY (userProfileID) REFERENCES userProfile (userProfileID)
    ) ENGINE=InnoDB AUTO_INCREMENT=85020 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE ambra.userOrcid (
    userProfileID bigint(20) NOT NULL,
    orcid varchar(25) COLLATE utf8_bin NOT NULL,
    accessToken varchar(50) COLLATE utf8_bin NOT NULL,
    refreshToken varchar(50) COLLATE utf8_bin NOT NULL,
    tokenScope varchar(100) COLLATE utf8_bin NOT NULL,
    tokenExpires datetime NOT NULL,
    lastModified datetime NOT NULL,
    created datetime NOT NULL,
    PRIMARY KEY (userProfileID),
    UNIQUE KEY orcid (orcid),
    CONSTRAINT userOrcid_ibfk_1 FOREIGN KEY (userProfileID) REFERENCES userProfile (userProfileID)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE ambra.userLogin (
    userLoginID bigint(20) NOT NULL AUTO_INCREMENT,
    userProfileID bigint(20) NOT NULL,
    sessionID varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    IP varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    userAgent varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    created datetime NOT NULL,
    PRIMARY KEY (userLoginID),
    KEY userProfileID (userProfileID),
    CONSTRAINT userLogin_ibfk_1 FOREIGN KEY (userProfileID) REFERENCES userProfile (userProfileID)
    ) ENGINE=InnoDB AUTO_INCREMENT=529408 DEFAULT CHARSET=utf8;

CREATE TABLE ambra.userSearch (
    userSearchID bigint(20) NOT NULL AUTO_INCREMENT,
    userProfileID bigint(20) NOT NULL,
    searchTerms text CHARACTER SET utf8 COLLATE utf8_bin,
    searchString text CHARACTER SET utf8 COLLATE utf8_bin,
    created datetime NOT NULL,
    PRIMARY KEY (userSearchID),
    KEY userProfileID (userProfileID),
    CONSTRAINT userSearch_ibfk_1 FOREIGN KEY (userProfileID) REFERENCES userProfile (userProfileID)
    ) ENGINE=InnoDB AUTO_INCREMENT=241783 DEFAULT CHARSET=utf8;

CREATE TABLE ambra.savedSearchQuery (
    savedSearchQueryID bigint(20) NOT NULL AUTO_INCREMENT,
    searchParams text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    hash varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    created datetime NOT NULL,
    lastmodified datetime NOT NULL,
    PRIMARY KEY (savedSearchQueryID),
    UNIQUE KEY hash_2 (hash),
    KEY hash (hash)
    ) ENGINE=InnoDB AUTO_INCREMENT=21203 DEFAULT CHARSET=utf8;

CREATE TABLE ambra.savedSearch (
    savedSearchID bigint(20) NOT NULL AUTO_INCREMENT,
    userProfileID bigint(20) NOT NULL,
    savedSearchQueryID bigint(20) NOT NULL,
    searchName varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    searchType varchar(16) NOT NULL,
    lastWeeklySearchTime datetime NOT NULL,
    lastMonthlySearchTime datetime NOT NULL,
    monthly bit(1) DEFAULT b'0',
    weekly bit(1) DEFAULT b'0',
    created datetime NOT NULL,
    lastModified datetime NOT NULL,
    PRIMARY KEY (savedSearchID),
    UNIQUE KEY userProfileID (userProfileID,searchName),
    KEY savedSearchQueryID (savedSearchQueryID),
    CONSTRAINT savedSearch_ibfk_1 FOREIGN KEY (userProfileID) REFERENCES userProfile (userProfileID),
    CONSTRAINT savedSearch_ibfk_2 FOREIGN KEY (savedSearchQueryID) REFERENCES savedSearchQuery (savedSearchQueryID)
    ) ENGINE=InnoDB AUTO_INCREMENT=20095 DEFAULT CHARSET=utf8;

CREATE TABLE ambra.userRole (
    userRoleID bigint(20) NOT NULL AUTO_INCREMENT,
    roleName varchar(15) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    created datetime NOT NULL,
    lastModified datetime NOT NULL,
    PRIMARY KEY (userRoleID),
    UNIQUE KEY roleName (roleName)
    ) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

CREATE TABLE ambra.userProfileRoleJoinTable (
    userRoleID bigint(20) NOT NULL,
    userProfileID bigint(20) NOT NULL,
    PRIMARY KEY (userRoleID,userProfileID),
    KEY userProfileID (userProfileID),
    CONSTRAINT userProfileRoleJoinTable_ibfk_1 FOREIGN KEY (userRoleID) REFERENCES userRole (userRoleID),
    CONSTRAINT userProfileRoleJoinTable_ibfk_2 FOREIGN KEY (userProfileID) REFERENCES userProfile (userProfileID)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ambra.annotation (
    annotationID bigint(20) NOT NULL AUTO_INCREMENT,
    annotationURI varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    articleID bigint(20) DEFAULT NULL,
    parentID bigint(20) DEFAULT NULL,
    userProfileID bigint(20) NOT NULL,
    type varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    title text CHARACTER SET utf8 COLLATE utf8_bin,
    body text CHARACTER SET utf8 COLLATE utf8_bin,
    competingInterestBody text CHARACTER SET utf8 COLLATE utf8_bin,
    highlightedText text CHARACTER SET utf8 COLLATE utf8_bin,
    created datetime NOT NULL,
    lastModified datetime NOT NULL,
    PRIMARY KEY (annotationID),
    UNIQUE KEY annotationURI (annotationURI),
    KEY articleID (articleID),
    KEY parentID (parentID),
    KEY userProfileID (userProfileID),
    CONSTRAINT annotation_ibfk_3 FOREIGN KEY (parentID) REFERENCES annotation (annotationID),
    CONSTRAINT annotation_ibfk_4 FOREIGN KEY (userProfileID) REFERENCES userProfile (userProfileID)
    ) ENGINE=InnoDB AUTO_INCREMENT=87444 DEFAULT CHARSET=utf8;

CREATE TABLE ambra.annotationFlag (
    annotationFlagID bigint(20) NOT NULL AUTO_INCREMENT,
    annotationID bigint(20) NOT NULL,
    userProfileID bigint(20) NOT NULL,
    reason varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    comment text CHARACTER SET utf8 COLLATE utf8_bin,
    created datetime NOT NULL,
    lastModified datetime NOT NULL,
    PRIMARY KEY (annotationFlagID),
    KEY annotationID (annotationID),
    KEY userProfileID (userProfileID),
    CONSTRAINT annotationFlag_ibfk_1 FOREIGN KEY (annotationID) REFERENCES annotation (annotationID),
    CONSTRAINT annotationFlag_ibfk_2 FOREIGN KEY (userProfileID) REFERENCES userProfile (userProfileID)
    ) ENGINE=InnoDB AUTO_INCREMENT=14396 DEFAULT CHARSET=utf8;

CREATE TABLE ambra.userArticleView (
    userArticleViewID bigint(20) NOT NULL AUTO_INCREMENT,
    userProfileID bigint(20) NOT NULL,
    articleID bigint(20) NULL,
    created datetime NOT NULL,
    type varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    PRIMARY KEY (userArticleViewID),
    KEY userProfileID (userProfileID),
    CONSTRAINT userArticleView_ibfk_1 FOREIGN KEY (userProfileID) REFERENCES userProfile (userProfileID)
    ) ENGINE=InnoDB AUTO_INCREMENT=345986 DEFAULT CHARSET=utf8;

CREATE TABLE ambra.articleCategoryFlagged (
    articleID bigint(20) NOT NULL,
    categoryID bigint(20) NOT NULL,
    userProfileID bigint(20) DEFAULT NULL,
    created datetime NOT NULL,
    lastModified datetime NOT NULL,
    UNIQUE KEY articleID (articleID,categoryID,userProfileID),
    KEY categoryID (categoryID),
    KEY userProfileID (userProfileID),
    CONSTRAINT articleCategoryFlagged_ibfk_3 FOREIGN KEY (userProfileID) REFERENCES userProfile (userProfileID)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
