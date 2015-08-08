
/* Consumers */
INSERT INTO namedEntities.consumers (id,name, password) VALUES (1,'test',/*test*/ '$2a$04$mM/5KJeXvHBqkX1wxnwzjuvXKewnKfZtgG3ZcExG56yenWgFQcWFm');

/* Seed Individual Entity */
INSERT INTO namedEntities.namedEntityIdentifiers(id,typeId)
  VALUES (1, (select gt.id from namedEntities.globalTypes gt
                join namedEntities.typeDescriptions td on gt.typeid = td.id
               where td.description='Named Party Types' and gt.shortDescription='Individual'));

INSERT INTO namedEntities.individualProfiles (id, nedId, firstName, lastName, displayName, biography, isActive, createdBy, lastModifiedBy, sourceTypeId)
  VALUES (1,1,'NED','NED','NED','bio',1,1,1,
    (select gt.id from namedEntities.globalTypes gt
       join namedEntities.typeDescriptions td on gt.typeid = td.id
      where td.description='Source Applications' and gt.shortDescription='Ambra'));

/* Seed Organization Entity */
INSERT INTO namedEntities.namedEntityIdentifiers(id,typeId)
  VALUES (2, (select gt.id from namedEntities.globalTypes gt
                join namedEntities.typeDescriptions td on gt.typeid = td.id
               where td.description='Named Party Types' and gt.shortDescription='Organization'));

INSERT INTO  namedEntities.organizations (id, nedId, familiarName, legalName, isActive, createdBy, lastModifiedBy, sourceTypeId)
  VALUES (1,2,'ABC Inc (FN)','ABC Inc (LN)',1,1,1,
    (select gt.id from namedEntities.globalTypes gt
       join namedEntities.typeDescriptions td on gt.typeid = td.id
      where td.description='Source Applications' and gt.shortDescription='Ambra'));
