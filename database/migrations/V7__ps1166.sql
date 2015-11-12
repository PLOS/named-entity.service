
USE namedEntities;

/* Delete Knowledge base groups */

DELETE FROM globalTypes WHERE shortDescription LIKE "Knowledge Base%";
