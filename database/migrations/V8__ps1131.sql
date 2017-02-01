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


USE namedEntities;

SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Degrees';

/* Replace seeded placeholder degrees with these values */

DELETE FROM globalTypes WHERE typeId=@typeIdVar;

INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) 
  VALUES (@typeIdVar, 'Doctorate', 'Doctorate (Ph.D., M.D., etc.)', 'PHD', CURRENT_TIMESTAMP);

INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) 
  VALUES (@typeIdVar, 'Masters', 'Masters (MS, MA, etc.)', 'MS', CURRENT_TIMESTAMP);

INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) 
  VALUES (@typeIdVar, 'Bachelors', NULL, 'BS', CURRENT_TIMESTAMP);

INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) 
  VALUES (@typeIdVar, 'Associates', NULL, 'ASSOC', CURRENT_TIMESTAMP);

INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) 
  VALUES (@typeIdVar, 'Diploma', NULL, 'DIP', CURRENT_TIMESTAMP);

INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) 
  VALUES (@typeIdVar, 'High School', NULL, 'HS', CURRENT_TIMESTAMP);

/* Modify Degrees table. Add title attribute and make type id optional. */

ALTER TABLE namedEntities.degrees
  ADD fulltitle varchar(250) NULL
    AFTER typeId;

ALTER TABLE namedEntities.degrees MODIFY COLUMN typeId INT NULL;
