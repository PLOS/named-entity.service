/*
 * Copyright (c) 2014-2019 Public Library of Science
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


USE namedEntities;

-- remove existing alert data

DELETE FROM alerts WHERE typeId IN (
  SELECT g.id from globalTypes g, typeDescriptions t WHERE t.id=g.typeId AND description = 'Alert Types' AND typeCode='journal'
);

-- remove columns from alerts table

SELECT constraint_name INTO @typeConstraint from information_schema.key_column_usage where constraint_schema='namedEntities' AND table_name='alerts' and column_name='typeId';
SET @dropFkeySql = CONCAT('ALTER TABLE alerts DROP FOREIGN KEY ', @typeConstraint);
PREPARE stmt1 FROM @dropFkeySql;
EXECUTE stmt1;
DEALLOCATE PREPARE stmt1;

SELECT constraint_name INTO @typeConstraint from information_schema.key_column_usage where constraint_schema='namedEntities' AND table_name='alerts' and column_name='journalTypeId';
SET @dropFkeySql = CONCAT('ALTER TABLE alerts DROP FOREIGN KEY ', @typeConstraint);
PREPARE stmt1 FROM @dropFkeySql;
EXECUTE stmt1;
DEALLOCATE PREPARE stmt1;


-- hack since I cant get the above to work with name resolution
-- ALTER TABLE alerts DROP FOREIGN KEY alerts_ibfk_2;
-- ALTER TABLE alerts DROP FOREIGN KEY alerts_ibfk_5;


ALTER TABLE alerts DROP COLUMN typeId, DROP COLUMN journalTypeId;

-- remove types

DELETE FROM globalTypes
  WHERE typeId IN (
      SELECT id FROM typeDescriptions WHERE description = 'Alert Types');

-- remove description

DELETE FROM typeDescriptions WHERE description = 'Alert Types';
