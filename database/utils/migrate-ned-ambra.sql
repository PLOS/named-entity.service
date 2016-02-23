/*
    migrate users from ned->ambra using nedid as userprofileid

    this was created as a temp workaround for migrating from a NED that existed before the
    adapter to one that uses the adapter.

    It might have other uses as well, but I cant think of any after the adapter is dead and gone.
*/

INSERT INTO ambra.userProfile
  (userProfileId, userProfileURI, displayName, givenNames, surName, realName,
  biography, title, email, city, country, postalAddress, homePage,
    password, authId, created, lastModified)
    SELECT p.nedId, CONCAT("doi://bogus-", p.nedId), p.displayName, p.firstName, p.lastName, 
           CONCAT(p.firstName,' ',p.lastName) realName,
           p.biography, gt1.shortDescription namePrefix, e.emailAddress, a.city,
           gt2.shortDescription country,
           CONCAT(a.addressLine1,'\n',a.addressLine2,'\n',a.addressLine3) postalAddress,
           u.url, auth.password, auth.authId, NOW(), NOW()
      FROM namedEntities.individualProfiles p
      JOIN namedEntities.emails e             ON e.nedId    = p.nedId
      JOIN namedEntities.authCas auth         ON auth.nedId = p.nedId
      LEFT JOIN namedEntities.globalTypes gt1 ON gt1.id     = p.namePrefixTypeId
      LEFT JOIN namedEntities.addresses a     ON a.nedId    = p.nedId
      LEFT JOIN namedEntities.globalTypes gt2 ON gt2.id     = a.countryCodeTypeId
      LEFT JOIN namedEntities.urls u          ON u.nedId    = p.nedId;
