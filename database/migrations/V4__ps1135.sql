
ALTER TABLE namedEntities.uniqueIdentifiers
  ADD metadata text NULL
    AFTER sourceTypeId;
