
/* migrate users from ned->ambra using nedid as userprofileid */

INSERT INTO ambra.userProfile
  (userProfileId, userProfileURI, displayName, givenNames, surName, realName, biography, title, email, city,
   country, postalAddress, homePage, password, authId)
    SELECT p.nedId, CONCAT("doi://bogus-", p.nedId), p.displayName, p.firstName, p.lastName, 
           CONCAT(p.firstName,' ',p.lastName) realName,
           p.biography, gt1.shortDescription namePrefix, e.emailAddress, a.city,
           gt2.shortDescription country,
           CONCAT(a.addressLine1,'\n',a.addressLine2,'\n',a.addressLine3) postalAddress,
           u.url, auth.password, auth.authId
      FROM namedEntities.individualProfiles p
      JOIN namedEntities.emails e             ON e.nedId    = p.nedId
      JOIN namedEntities.authCas auth         ON auth.nedId = p.nedId
      LEFT JOIN namedEntities.globalTypes gt1 ON gt1.id     = p.namePrefixTypeId
      LEFT JOIN namedEntities.addresses a     ON a.nedId    = p.nedId
      LEFT JOIN namedEntities.globalTypes gt2 ON gt2.id     = a.countryCodeTypeId
      LEFT JOIN namedEntities.urls u          ON u.nedId    = p.nedId;

/* ambra.userprofile is now the generator of nedids; set its auto increment to next nedid */

SELECT @nextNedId := MAX(userProfileID)+1 FROM ambra.userProfile;
SET @setAutoIncrementSql = CONCAT('ALTER TABLE ambra.userProfile AUTO_INCREMENT = ', @nextNedId);
PREPARE stmt FROM @setAutoIncrementSql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

