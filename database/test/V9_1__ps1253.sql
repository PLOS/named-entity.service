SELECT gt.id INTO @alertTypeJournalIdVar
  FROM namedEntities.globalTypes gt
  JOIN namedEntities.typeDescriptions td ON gt.typeid = td.id
 WHERE td.description='Alert Types' AND gt.shortDescription='Journal';

SELECT gt.id INTO @alertTypeSearchIdVar
  FROM namedEntities.globalTypes gt
  JOIN namedEntities.typeDescriptions td ON gt.typeid = td.id
 WHERE td.description='Alert Types' AND gt.shortDescription='Search';

SELECT gt.id INTO @alertFreqTypeIdVar
  FROM namedEntities.globalTypes gt
  JOIN namedEntities.typeDescriptions td ON gt.typeid = td.id
 WHERE td.description='Alert Frequency' AND gt.shortDescription='weekly';

SELECT gt.id INTO @journalTypeIdVar
  FROM namedEntities.globalTypes gt
  JOIN namedEntities.typeDescriptions td ON gt.typeid = td.id
 WHERE td.description='Journal' AND gt.shortDescription='PLOS Biology';

SELECT gt.id INTO @srcTypeIdVar
   FROM namedEntities.globalTypes gt
   JOIN namedEntities.typeDescriptions td ON gt.typeid = td.id
  WHERE td.description='Source Applications' AND gt.shortDescription='Ambra';

SELECT id INTO @consumerIdVar FROM namedEntities.consumers WHERE name = 'test';

INSERT INTO namedEntities.alerts(id, nedId, typeId, frequencyTypeId, journalTypeId, name, query, sourceTypeId, createdBy, lastModifiedBy)
    VALUES (1, 1, @alertTypeJournalIdVar, @alertFreqTypeIdVar, @journalTypeIdVar, "alert one", "", @sourceTypeId, @consumerIdVar, @consumerIdVar);

INSERT INTO namedEntities.alerts(id, nedId, typeId, frequencyTypeId, journalTypeId, name, query, sourceTypeId, createdBy, lastModifiedBy)
    VALUES (1, 1, @alertTypeSearchIdVar, @alertFreqTypeIdVar, @journalTypeIdVar, "alert two", "{\"query\":\"goes here\"}", @sourceTypeId, @consumerIdVar, @consumerIdVar);
