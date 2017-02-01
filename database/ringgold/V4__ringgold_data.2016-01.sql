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


INSERT INTO ringgold.alt_names (rec_id,ringgold_id,name,city,country,language,notes,timestamp)
    VALUES (7610,6429,"J Henry Meyer Memorial Library","Stanford","US","","LB","2014-06-23 10:15:10");
INSERT INTO ringgold.alt_names (rec_id,ringgold_id,name,city,country,language,notes,timestamp)
    VALUES (7611,6429,"Green Library","Stanford","US","","LB","2014-06-23 10:15:10");
INSERT INTO ringgold.alt_names (rec_id,ringgold_id,name,city,country,language,notes,timestamp)
    VALUES (7612,6429,"Cecil H Green Library","Stanford","US","","LB","2014-06-23 10:15:10");

INSERT INTO ringgold.consortia (rec_id,consortia_ringgold_id,member_ringgold_id,name,timestamp)
    VALUES (1170,42263,6429,"Stanford University","2015-10-28 17:21:12");
INSERT INTO ringgold.consortia (rec_id,consortia_ringgold_id,member_ringgold_id,name,timestamp)
    VALUES (21735,122433,6429,"Stanford University","2015-07-23 16:28:50");
INSERT INTO ringgold.consortia (rec_id,consortia_ringgold_id,member_ringgold_id,name,timestamp)
    VALUES (41062,225245,6429,"Stanford University","2015-08-10 10:21:23");

INSERT INTO ringgold.identifiers (rec_id,ringgold_id,identifier_type,value,timestamp) 
    VALUES (6480,6429,"IPED","243744","2015-01-28 14:37:09");
INSERT INTO ringgold.identifiers (rec_id,ringgold_id,identifier_type,value,timestamp) 
    VALUES (6481,6429,"ISNI","0000000419368956","2015-03-31 23:36:04");

INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (4959,0,6429,"Stanford University","Stanford","94305","US","CA","academic","2014-06-23 10:15:10");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (4097,6429,5498,"Center for Advanced Study in the Behavioral Sciences at Stanford University","Stanford","94305-8090","US","CA","academic/psych","2014-06-23 10:15:18");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (4958,6429,6428,"Highwire Press Inc","Palo Alto","94304-1124","US","CA","academic/corporate","2014-06-05 16:57:48");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (22588,6429,25823,"Stanford Graduate School of Business","Stanford","94305-5015","US","CA","academic/bus","2015-01-28 14:44:51");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (22737,6429,25985,"Stanford University Stanford Law School","Stanford","94305-8610","US","CA","academic/law","2014-06-23 10:16:37");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (43650,6429,49035,"Stanford University Hoover Institution","Stanford","94305-6010","US","CA","academic/social","2015-01-28 15:47:36");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (84079,6429,92981,"Stanford University Hopkins Marine Station","Pacific Grove","93950-3094","US","CA","academic/earth","2014-06-23 09:16:15");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (108326,6429,118553,"Stanford University School of Engineering","Stanford","94305-4027","US","CA","academic/eng","2014-06-23 08:25:27");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (108327,6429,118554,"Stanford University School of Humanities and Science","Stanford","94305-2070","US","CA","academic/gen","2014-06-23 08:25:32");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (117179,6429,127874,"Lockss Program","Palo Alto","94304-1124","US","CA","other/cultural","2014-07-30 12:19:49");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (129269,6429,140388,"Stanford University Freeman Spogli Institute for International Studies","Stanford","94305-6055","US","CA","academic/gen","2014-06-23 09:20:18");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (143866,6429,155782,"Stanford University Woods Institute for the Environment","Stanford","94305","US","CA","academic/earth","2014-06-23 08:24:45");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (146411,6429,158423,"Stanford Medicine","Stanford","94305-5101","US","CA","health/system","2015-01-28 15:48:52");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (152637,6429,164890,"Stanford University School of Earth Sciences","Stanford","94305-2210","US","CA","academic/earth","2014-06-23 09:15:25");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (154293,6429,166667,"Stanford University LGBT Community Resources Center","Stanford","94305-8215","US","CA","academic/social","2014-06-23 10:24:03");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (161839,6429,174507,"Stanford University Press","Palo Alto","94304-1124","US","CA","academic/corporate","2013-10-17 09:38:17");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (185443,6429,198868,"Stanford University Graduate School of Education","Stanford","94305-3096","US","CA","academic/gen","2014-06-23 08:26:15");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (185453,6429,198878,"Stanford University Stanford Humanities Center","Stanford","94305-4015","US","CA","academic/gen","2014-06-23 09:13:28");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (185456,6429,198881,"Stanford University Center for the Study of Language and Information","Stanford","94305-4101","US","CA","academic/tech","2014-06-23 09:13:37");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (185465,6429,198890,"Stanford University Program in Writing and Rhetoric","Stanford","94305-3069","US","CA","academic/gen","2014-06-23 09:14:17");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (185470,6429,198895,"Stanford University Department of Environmental Health and Safety","Stanford","94305-8007","US","CA","academic/health","2014-06-23 09:14:29");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (185471,6429,198896,"Iris and B Gerald Cantor Center for Visual Arts at Stanford University","Stanford","94305-5060","US","CA","other/museum","2014-06-23 09:14:33");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (202000,6429,216100,"Stanford University Counseling and Psychological Services","Stanford","94305","US","CA","corporate/psych","2014-06-23 09:37:38");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (233775,6429,249363,"Stanford Alumni Association","Stanford","94305-6105","US","CA","other/assoc","2014-06-23 10:26:33");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (335201,6429,354635,"Stanford University Department of Linguistics","San Francisco","94110","US","CA","academic/lang","2014-06-23 10:26:24");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (347207,6429,367182,"Stanford University Center for Research on Education Outcomes","Stanford","94305-6003","US","CA","academic/gen","2014-06-23 09:16:35");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (359658,6429,380176,"Stanford University Occupational Health Center","Stanford","94305-8007","US","CA","corporate/medprac","2014-06-23 09:17:10");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (372132,6429,393360,"Stanford in Washington","Washington","20008","US","DC","academic/campus","2015-01-28 15:58:02");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (388588,6429,410797,"Stanford University Centre in Oxford","Oxford","OX1 4EL","GB","Oxfordshire","academic/campus","2014-09-15 20:50:13");
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (393851,6429,416226,"Stanford University in Berlin","Berlin","14195","DE","Berlin","academic/campus","2014-11-27 10:49:00");

INSERT INTO ringgold.sizes (rec_id,ringgold_id,size_type,value,timestamp) VALUES (4922,6429,"size","14219","2015-01-28 14:37:09");
INSERT INTO ringgold.sizes (rec_id,ringgold_id,size_type,value,timestamp) VALUES (4923,6429,"staff","1749","2015-01-28 14:37:09");

INSERT INTO ringgold.tiers (rec_id,ringgold_id,tier_type,value,timestamp) VALUES (14234,6429,"Carnegie-BASIC2005","15","2015-01-28 14:37:09");
INSERT INTO ringgold.tiers (rec_id,ringgold_id,tier_type,value,timestamp) VALUES (14235,6429,"Carnegie-BASIC2010","15","2015-01-28 14:37:09");
INSERT INTO ringgold.tiers (rec_id,ringgold_id,tier_type,value,timestamp) VALUES (14236,6429,"Carnegie-SizeSet2005","17","2015-01-28 14:37:09");
INSERT INTO ringgold.tiers (rec_id,ringgold_id,tier_type,value,timestamp) VALUES (14237,6429,"Carnegie-SizeSet2010","17","2015-01-28 14:37:09");
INSERT INTO ringgold.tiers (rec_id,ringgold_id,tier_type,value,timestamp) VALUES (14238,6429,"RGT","A4","2015-01-28 14:37:09");
INSERT INTO ringgold.tiers (rec_id,ringgold_id,tier_type,value,timestamp) VALUES (14239,6429,"SFJ","3.1","2015-01-28 14:37:09");

INSERT INTO ringgold.urls (rec_id,ringgold_id,url,notes,timestamp) VALUES (6634,6429,"www.stanford.edu","PD","2014-06-23 10:15:10");

/* test data to help verify correct sort order */
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (1000001,0,1000001,'Test Group1 I001','San Francisco','94101','US','CA','academic/medsch','2014-08-04 10:44:31');
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (1000002,0,1000002,'Test Group1 I002','San Francisco','94101','US','CA','academic/medsch','2014-08-04 10:44:31');
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (1000003,0,1000003,'Test Group1 I003','San Francisco','94101','US','CA','academic/medsch','2014-08-04 10:44:31');
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (1000004,0,1000004,'Test Group1 I004','Paris','75480','FR','','academic/health','2014-08-04 10:44:31');
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (1000005,0,1000005,'Test Group1 I005','Paris','75480','FR','','academic/health','2014-08-04 10:44:31');
INSERT INTO ringgold.institutions (rec_id,parent_ringgold_id,ringgold_id,name,city,post_code,country,state,type,timestamp) 
    VALUES (1000006,0,1000006,'Test Group1 I006','Paris','75480','FR','','academic/health','2014-08-04 10:44:31');

INSERT INTO ringgold.sizes (ringgold_id,size_type,value,timestamp) VALUES (1000001,'size','100','2015-01-28 06:37:09');
INSERT INTO ringgold.sizes (ringgold_id,size_type,value,timestamp) VALUES (1000002,'size','1','2015-01-28 06:37:09');
-- INSERT INTO ringgold.sizes (ringgold_id,size_type,value,timestamp) VALUES --(1000003,'size',NULL,'2015-01-28 06:37:09');
INSERT INTO ringgold.sizes (ringgold_id,size_type,value,timestamp) VALUES (1000004,'size','100','2015-01-28 06:37:09');
INSERT INTO ringgold.sizes (ringgold_id,size_type,value,timestamp) VALUES (1000005,'size','1','2015-01-28 06:37:09');
-- INSERT INTO ringgold.sizes (ringgold_id,size_type,value,timestamp) VALUES --(1000006,'size',NULL,'2015-01-28 06:37:09');
