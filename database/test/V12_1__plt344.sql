
/* Consumers */

/*INSERT INTO namedEntities.consumers (id,name, password) VALUES (1,'test',/*test*/ '$2a$04$mM/5KJeXvHBqkX1wxnwzjuvXKewnKfZtgG3ZcExG56yenWgFQcWFm');*/
SELECT id INTO @consumerIdVar FROM namedEntities.consumers WHERE name = 'test';

/* Seed Individual Entity */

INSERT INTO namedEntities.namedEntityIdentifiers(id,typeId)
  VALUES (1, (select gt.id from namedEntities.globalTypes gt
                join namedEntities.typeDescriptions td on gt.typeid = td.id
               where td.description='Named Party Types' and gt.shortDescription='Individual'));

SELECT gt.id INTO @srcTypeIdVar
  FROM namedEntities.globalTypes gt
  JOIN namedEntities.typeDescriptions td ON gt.typeid = td.id
 WHERE td.description='Source Applications' AND gt.shortDescription='Ambra';

INSERT INTO namedEntities.individualProfiles (id, nedId, firstName, lastName, displayName, biography, isActive, createdBy, lastModifiedBy, sourceTypeId)
  VALUES (1,1,'Cosmo','Kramer','ckramer','a short bio',1, @consumerIdVar, @consumerIdVar, @srcTypeIdVar);

INSERT INTO namedEntities.emails (id, nedId, emailAddress, createdBy, lastModifiedBy, sourceTypeId)
    VALUES (3,3, 'testckramer@plos.org', @consumerIdVar, @consumerIdVar, @srcTypeIdVar);

INSERT INTO namedEntities.authCas (id, nedId, emailId, authId, password, createdBy, lastModifiedBy)
    VALUES (1,1,1, UUID(),  /* password = "password1" */
            'f953d98b896b3739bf925346cffb4e2e9b5e724d14bc66bc6562672944ae21d1f76bf904416af9ce52ddff3d9b46b447a4d1d6e17318299e967d0362d0df0ad6',
            @consumerIdVar, @consumerIdVar);

INSERT INTO namedEntities.uniqueIdentifiers (id, nedId, typeId, uniqueIdentifier, sourceTypeId, createdBy, lastModifiedBy)
    VALUES (1, 1,
        (select gt.id from namedEntities.globalTypes gt
           join namedEntities.typeDescriptions td on gt.typeid = td.id
          where td.description='UID Individual Types' and gt.shortDescription='Ambra'),
        '1', @srcTypeIdVar, @consumerIdVar, @consumerIdVar);

/* Seed Organization Entity */

INSERT INTO namedEntities.namedEntityIdentifiers(id,typeId)
  VALUES (2, (select gt.id from namedEntities.globalTypes gt
                join namedEntities.typeDescriptions td on gt.typeid = td.id
               where td.description='Named Party Types' and gt.shortDescription='Organization'));

INSERT INTO  namedEntities.organizations (id, nedId, familiarName, legalName, isActive, createdBy, lastModifiedBy, sourceTypeId)
  VALUES (1,2,'ABC''s Inc (FN)','ABC''s Inc (LN)',1,1,1,
    (select gt.id from namedEntities.globalTypes gt
       join namedEntities.typeDescriptions td on gt.typeid = td.id
      where td.description='Source Applications' and gt.shortDescription='Ambra'));
