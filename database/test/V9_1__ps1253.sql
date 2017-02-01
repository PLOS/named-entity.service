/*
 * Copyright (c) 2017 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

SELECT gt.id INTO @alertTypeJournalIdVar
  FROM namedEntities.globalTypes gt
  JOIN namedEntities.typeDescriptions td ON gt.typeid = td.id
 WHERE td.description='Alert Types' AND gt.shortDescription='journal';

SELECT gt.id INTO @alertTypeSearchIdVar
  FROM namedEntities.globalTypes gt
  JOIN namedEntities.typeDescriptions td ON gt.typeid = td.id
 WHERE td.description='Alert Types' AND gt.shortDescription='search';

SELECT gt.id INTO @alertFreqTypeIdVar
  FROM namedEntities.globalTypes gt
  JOIN namedEntities.typeDescriptions td ON gt.typeid = td.id
 WHERE td.description='Alert Frequency' AND gt.shortDescription='weekly';

SELECT gt.id INTO @journalTypeBioIdVar
  FROM namedEntities.globalTypes gt
  JOIN namedEntities.typeDescriptions td ON gt.typeid = td.id
 WHERE td.description='Journal Types' AND gt.shortDescription='PLOS Biology';

SELECT gt.id INTO @journalTypeOneIdVar
  FROM namedEntities.globalTypes gt
  JOIN namedEntities.typeDescriptions td ON gt.typeid = td.id
 WHERE td.description='Journal Types' AND gt.shortDescription='PLOS ONE';

SELECT gt.id INTO @srcTypeIdVar
   FROM namedEntities.globalTypes gt
   JOIN namedEntities.typeDescriptions td ON gt.typeid = td.id
  WHERE td.description='Source Applications' AND gt.shortDescription='Ambra';

SELECT id INTO @consumerIdVar FROM namedEntities.consumers WHERE name = 'test';

-- note: this one will be deleted via migration 10
INSERT INTO namedEntities.alerts(id, nedId, typeId, frequencyTypeId, journalTypeId, name, query, sourceTypeId, createdBy, lastModifiedBy)
    VALUES (1, 1, @alertTypeJournalIdVar, @alertFreqTypeIdVar, @journalTypeBioIdVar, "alert one", "", @srcTypeIdVar, @consumerIdVar, @consumerIdVar);

INSERT INTO namedEntities.alerts(id, nedId, typeId, frequencyTypeId, journalTypeId, name, query, sourceTypeId, createdBy, lastModifiedBy)
    VALUES (2, 1, @alertTypeSearchIdVar, @alertFreqTypeIdVar, @journalTypeBioIdVar, "alert two", "{\"query\":\"goes here\"}", @srcTypeIdVar, @consumerIdVar, @consumerIdVar);

INSERT INTO namedEntities.alerts(id, nedId, typeId, frequencyTypeId, journalTypeId, name, query, sourceTypeId, createdBy, lastModifiedBy)
    VALUES (3, 1, @alertTypeSearchIdVar, @alertFreqTypeIdVar, @journalTypeOneIdVar, "alert three", "{\"query\":\"goes here too\"}", @srcTypeIdVar, @consumerIdVar, @consumerIdVar);
