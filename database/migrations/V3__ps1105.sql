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

/* Delete Roles Type Class */
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Roles';
DELETE FROM globalTypes WHERE typeId=@typeIdVar;
DELETE FROM typeDescriptions WHERE id=@typeIdVar;

/* User Applications */
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='User Applications';
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base', NULL, 'KB', CURRENT_TIMESTAMP);

/* Add Groups Type Class */
INSERT INTO typeDescriptions(description, howUsed) VALUES ('Groups','Roles, Membership, ...');
SELECT id INTO @typeIdVar FROM typeDescriptions WHERE description='Groups';

/* NED Admin Groups */
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'NED Admin', NULL, 'NED-ADMIN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'NED Manage Users', NULL, 'NED-USER', CURRENT_TIMESTAMP);

/* Knowledge Base Groups */
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - PLOSONE', NULL, 'KB-PLOSONE', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - Biology', NULL, 'KB-BIO', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - Computational Biology', NULL, 'KB-CB', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - Genetics', NULL, 'KB-GEN', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - Medicine', NULL, 'KB-MED', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - Neglected Tropical Diseases', NULL, 'KB-NTD', CURRENT_TIMESTAMP);
INSERT INTO globalTypes (typeId,shortDescription,longDescription,typeCode,created) VALUES (@typeIdVar, 'Knowledge Base - Pathogens', NULL, 'KB-PATHOG', CURRENT_TIMESTAMP);

ALTER TABLE roles RENAME groups;
