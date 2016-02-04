
USE namedEntities;

-- SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Alert Types';

-- SELECT id INFO @typeJournalVar from globalTypes g, typeDescriptions t WHERE t.id=g.typeId AND description = 'Alert Types' AND typeCode='journal';


DELETE FROM alerts WHERE typeId IN (
  SELECT g.id from globalTypes g, typeDescriptions t WHERE t.id=g.typeId AND description = 'Alert Types' AND typeCode='journal'
);

ALTER TABLE alerts DROP COLUMN i, DROP COLUMN ;

DELETE FROM globalTypes
  WHERE shortDescription='journal'
    AND typeId IN (
      SELECT id FROM typeDescriptions WHERE description = 'Alert Types');
