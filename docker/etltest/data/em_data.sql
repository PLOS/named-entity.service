-- MySQL dump 10.13  Distrib 5.5.40, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: em_data
-- ------------------------------------------------------
-- Server version	5.5.40-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `additional_people_detail_values`
--

DROP TABLE IF EXISTS `additional_people_detail_values`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `additional_people_detail_values` (
  `APDV_ID` int(11) NOT NULL,
  `PEOPLEID` int(11) NOT NULL,
  `APD_ID` int(11) NOT NULL,
  `DATE_VALUE` datetime DEFAULT NULL,
  `INTEGER_VALUE` int(11) DEFAULT NULL,
  `DECIMAL_VALUE` decimal(18,5) DEFAULT NULL,
  `DROP_DOWN_ITEM_ID` int(11) DEFAULT NULL,
  `TEXT_VALUE` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `NOTES_VALUE` varchar(1001) CHARACTER SET utf8 DEFAULT NULL,
  `Row_LastModified_TimeStamp` datetime NOT NULL,
  PRIMARY KEY (`APDV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `additional_people_detail_values`
--

LOCK TABLES `additional_people_detail_values` WRITE;
/*!40000 ALTER TABLE `additional_people_detail_values` DISABLE KEYS */;
INSERT INTO `additional_people_detail_values` VALUES (6,316788,2,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-31 14:59:49'),(7,764956,2,'2013-07-23 00:00:00',NULL,NULL,NULL,NULL,NULL,'2014-01-07 15:47:45'),(8,267395,2,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-30 20:03:15'),(9,764958,2,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-30 19:22:51'),(12,517036,2,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-30 15:20:53');
/*!40000 ALTER TABLE `additional_people_detail_values` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `MAIL` bit(1) DEFAULT NULL,
  `INACTIVE` bit(1) NOT NULL,
  `ATYPE` varchar(8) DEFAULT NULL,
  `POSITION` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `DEPARTMENT` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `INSTITUTE` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `ADDRESS1` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `ADDRESS2` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `ADDRESS3` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `ADDRESS4` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `CITY` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `ST` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `ZIPCODE` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `COUNTRY` varchar(60) DEFAULT NULL,
  `PHONE` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `PHONE2` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `PH2TYPE` varchar(8) DEFAULT NULL,
  `FAX` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `EMAIL` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `CONTACTTYPE` varchar(10) NOT NULL,
  `LASTUPDATE` datetime NOT NULL,
  `PEOPLEID` int(11) NOT NULL,
  `ADDRESSID` int(11) NOT NULL,
  `PRIMARY` bit(1) NOT NULL,
  `STARTDATE` datetime DEFAULT NULL,
  `ENDDATE` datetime DEFAULT NULL,
  `ISO_CODE` char(2) DEFAULT NULL,
  `ALTERNATECONTACTNOTES` varchar(1001) CHARACTER SET utf8 DEFAULT NULL,
  `Row_LastModified_TimeStamp` datetime NOT NULL,
  `INSTITUTEID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES ('\0','','work','','','North Carolina State University','','','','','','','','','','','','','elizabeth@stat.duke.edu','email','2014-11-02 07:41:28',764961,1097710,'','0000-00-00 00:00:00','0000-00-00 00:00:00','','','2014-11-02 07:41:28',0),('\0','','work','Professor','Faculty of Advanced Life Science','Hokkaido University','Kita 10, Nishi 8, Kita-ku','','','','Sapporo','Hokkaido','060-0810','JAPAN','81-11-706-4481','','mobile','81-11-706-4481','yao@castor.sci.hokudai.ac.jp','email','2014-04-08 02:29:22',433084,764961,'','0000-00-00 00:00:00','0000-00-00 00:00:00','jp','','2014-04-08 02:29:22',0),('\0','','work','','','University of South Florida','','','','','','','','','','','','','','post','2014-11-02 07:41:28',764960,1097709,'','0000-00-00 00:00:00','0000-00-00 00:00:00','','','2014-11-02 07:41:28',0),('\0','','work','','','','','','','','','','','UNITED STATES','','','mobile','','giovanna_scapin@merck.com','email','2011-11-28 09:36:03',433083,764960,'','0000-00-00 00:00:00','0000-00-00 00:00:00','us','','2011-11-28 09:36:03',0),('\0','','work','','','University of Southern California','','','','','','','','','','','','','dowell@usc.edu','email','2014-11-02 07:41:28',764959,1097708,'','0000-00-00 00:00:00','0000-00-00 00:00:00','','','2014-11-02 07:41:28',0),('\0','','work','','International Centre for Ecotourism Research','Griffith University','Parklands Drive','','','','Gold Coast','Qld','4222','AUSTRALIA','','+41764959660','mobile','','a.mossaz@griffith.edu.au','email','2011-11-18 03:14:20',431277,763144,'','0000-00-00 00:00:00','0000-00-00 00:00:00','au','','2012-08-23 12:20:43',0),('\0','','work','','Biochemistry','University of Bayreuth','Universitaetsstr. 30 ','','','','Bayreuth','','','GERMANY','','','mobile','','Clemens.Steegborn@uni-bayreuth.de','email','2012-07-24 13:59:02',433082,764959,'','0000-00-00 00:00:00','0000-00-00 00:00:00','de','','2012-07-24 13:59:02',0),('\0','','work','','','University of Michigan','','','','','','','','','','','','','scavia@umich.edu','email','2014-11-02 07:41:28',764958,1097707,'','0000-00-00 00:00:00','0000-00-00 00:00:00','','','2014-11-02 07:41:28',0),('\0','','','','Veterinary Medicine','University of Cambridge','Madingley Road',' ','','','Cambridge',' ','CB3 0ES','UNITED KINGDOM','0044 (1223) 764958',' ','Work',' ','lg317@cam.ac.uk;laugar77@hotmail.com','email','2010-07-20 16:59:23',267105,597639,'','0000-00-00 00:00:00','0000-00-00 00:00:00','gb','','2010-07-20 16:59:23',0),('\0','','','','Veterinary Medicine','University of Cambridge','Madingley Road',' ','','','Cambridge','Cambridgeshire','CB3 0ES','UNITED KINGDOM','0044 (1223) 764958',' ','Work',' ','hl209@cam.ac.uk','email','2010-07-20 16:59:23',267162,597696,'','0000-00-00 00:00:00','0000-00-00 00:00:00','gb','','2010-10-19 16:31:00',0),('\0','','work','','Department of Gastroenterology and Hepatology','Graduate School of Medicine Kyoto University','54 Kawaharacho, Shogoin, Sakyo-ku','','','','Kyoto','','','JAPAN','','','mobile','','thorimat@kuhp.kyoto-u.ac.jp','email','2011-11-28 09:30:54',433081,764958,'','0000-00-00 00:00:00','0000-00-00 00:00:00','jp','','2011-11-28 09:30:54',0),('\0','','work','','Department of Internal Medicine and Gerontology','Medical Colllege, Jagiellonian University','Åšniadeckich 10','','','','KrakÃ³w','','31-51','POLAND','+48 12 424 88 00','+48 502 246 186','mobile','+48 12 424 88 53','marcincw@poczta.onet.pl','email','2014-11-02 07:37:08',764957,1097706,'','0000-00-00 00:00:00','0000-00-00 00:00:00','PL','','2014-11-02 07:37:08',0),('\0','','work','Head of Department','Molecular Microbiology & Bioenergetics','Goethe University Frankfurt','Max-von-Laue-Str. 9','','','','Frankfurt','','60438','GERMANY','+496979829507','','mobile','+496979829306','vmueller@bio.uni-frankfurt.de','email','2013-04-26 13:40:53',433080,764957,'','0000-00-00 00:00:00','0000-00-00 00:00:00','de','','2013-04-26 13:40:53',0),('\0','','work','','Molecular Microbiology','Humboldt-Universitat zu Berlin','Chausseestr. 117','','','','Berlin','','10115','GERMANY','+49-30-2093-8111','','mobile','','natalia.tschowri@hu-berlin.de','email','2014-11-02 07:22:44',764956,1097705,'','0000-00-00 00:00:00','0000-00-00 00:00:00','','','2014-11-02 07:22:44',0),('\0','','work','','Department of Structual Pathology, Niigata University','Institute of Nephrology','1-757 Asahimachi-dori, Chuo-ku','','','','Niigata','Niigata','951-8510','JAPAN','','','mobile','','ren-path@med.niigata-u.ac.jp','email','2011-12-12 21:43:59',433079,764956,'','0000-00-00 00:00:00','0000-00-00 00:00:00','jp','','2011-12-12 21:43:59',0),('\0','','work','','Urology','Huashan Hospital Fudan University','NO.12 WuLuMuQi Middle Road','','','','Shanghai','','200040','CHINA','+8613764956213','','mobile','','haowenj73@126.com','email','2014-10-06 21:28:32',755409,1088149,'','0000-00-00 00:00:00','0000-00-00 00:00:00','CN','','2014-10-06 21:28:32',159397),('\0','','work','','Department of Tuberculosis','Shanghai Pulmonary Hospital ','507 ZhengMin Road','','','','Shanghai','','200433','CHINA','86-21-65115006','','mobile','','fanlinsj@163.com','email','2014-11-02 07:17:06',764955,1097704,'','0000-00-00 00:00:00','0000-00-00 00:00:00','CN','','2014-11-02 07:17:06',0),('\0','','work','Professor','Pediatrics','University of California, Davis','Primate Center','Pedrick and Hutchison Roads','','','Davis','CA','95616-8542','UNITED STATES','530-752-6680','530-752-3266','asst','530-752-2880','aftarantal@primate.ucdavis.edu','email','2013-05-01 13:43:48',433078,764955,'','0000-00-00 00:00:00','0000-00-00 00:00:00','us','','2013-05-01 13:43:49',0),('\0','','work','','Department of Physics',' University of Mazandaran','Bagher Tangeh','','','','Babolsar','','1355764955','IRAN, ISLAMIC REPUBLIC OF','+98 112-5342482','','mobile','','Ashkarran@gmail.com','email','2013-02-27 15:28:24',544410,876650,'','0000-00-00 00:00:00','0000-00-00 00:00:00','ir','','2014-05-19 17:30:33',0),('\0','','work','','','','','','','','','','','SWITZERLAND','','','mobile','','bianca.saladin@wsl.ch','email','2014-11-02 07:14:26',764954,1097703,'','0000-00-00 00:00:00','0000-00-00 00:00:00','CH','','2014-11-02 07:14:26',0),('\0','','work','','Translational Medicine and Neurogenetics','IGBMC','1, rue Laurent Fries','','','','Illkirch-Graffenstaden','','','FRANCE','','','mobile','','ricci@igbmc.fr','email','2011-11-28 09:20:32',433077,764954,'','0000-00-00 00:00:00','0000-00-00 00:00:00','fr','','2012-08-23 12:20:43',0),('\0','','work','','Center for Environment, Health and Welfare','Korea Institute of Science and Technology','39-1 Hawolgok-dong, Seungbuk-gu','','','','Seoul','','136-791','KOREA, REPUBLIC OF','82-2-958-5718','','mobile','','jaehee@kaist.ac.kr','email','2014-11-02 07:09:12',764953,1097702,'','0000-00-00 00:00:00','0000-00-00 00:00:00','KR','','2014-11-02 07:09:12',58975),('\0','','work','lecturer','Division of Applied Medicine','University of Aberdeen','Institute of Medical Sciences','Foresterhill','','','Aberdeen','','AB25 2ZD','UNITED KINGDOM','01224 437350','','mobile','','h.m.wilson@abdn.ac.uk','email','2013-05-23 03:31:57',433076,764953,'','0000-00-00 00:00:00','0000-00-00 00:00:00','gb','','2013-05-23 03:31:57',0),('\0','','work','','Key Laboratory of Ion Beam Bioengineering','Chinese Academy of Sciences','350 Shushanhu Road, Hefeiï¼Œ230031ï¼ŒAnhui Province, P.R. China','','','','hefei','','230031','CHINA','+86 551 65593148','','mobile','','zhengzhiming2011@gmail.com','email','2014-11-02 07:04:53',764952,1097701,'','0000-00-00 00:00:00','0000-00-00 00:00:00','CN','','2014-11-02 07:04:53',0),('\0','','work','','Department of Pediatrics','University Clinic Carl Gustav Carus Dresden','Fetscherstr. 74','','','','Dresden','','D-01307','GERMANY','+493514586885','','mobile','','cornelia.richter@uniklinikum-dresden.de','email','2013-03-25 03:39:48',433075,764952,'','0000-00-00 00:00:00','0000-00-00 00:00:00','de','','2013-03-25 03:39:48',0);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editorrole`
--

DROP TABLE IF EXISTS `editorrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `editorrole` (
  `EDITORROLEID` int(11) NOT NULL,
  `NAME` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `NEWSUBMISSION` bit(1) NOT NULL,
  `ASSIGNEDITOR` bit(1) NOT NULL,
  `VIEWASSIGNED` bit(1) NOT NULL,
  `VIEWCOMPLETED` bit(1) NOT NULL,
  `VIEWEDITORDECISION` bit(1) NOT NULL,
  `VIEWCURASSIGNMENTS` bit(1) NOT NULL,
  `vIEWCOMPLETEDASSIGNMENTS` bit(1) NOT NULL,
  `SENDREVIEWERREMINDER` bit(1) NOT NULL,
  `REPORTS` bit(1) NOT NULL,
  `SYSADMIN` bit(1) NOT NULL,
  `CLASSIFY` bit(1) NOT NULL,
  `SELECTREVIEWERS` bit(1) NOT NULL,
  `FINALDISPOSITION` bit(1) NOT NULL,
  `VIEWSTATHIST` bit(1) NOT NULL,
  `VIEWCORRHIST` bit(1) NOT NULL,
  `ATTACHEMAIL` bit(1) NOT NULL,
  `ATTACHFILES` bit(1) NOT NULL,
  `NOTIFYAUTHORREVISION` bit(1) NOT NULL,
  `NOTIFYAUTHORDECISION` bit(1) NOT NULL,
  `SEARCHDOCS` bit(1) NOT NULL,
  `RECEIVEASSIGNMENTS` bit(1) NOT NULL,
  `VIEWALLASSIGNMENTS` bit(1) NOT NULL,
  `EDITALLASSIGNMENTS` bit(1) NOT NULL,
  `MAKEDECISION` bit(1) NOT NULL,
  `NOTIFYAUTHORDISPOSITION` bit(1) NOT NULL,
  `SIMILARMEDLINESEARCH` bit(1) NOT NULL,
  `REQUESTUNREGREVIEWER` bit(1) NOT NULL,
  `REVIEWERDEADLINES` bit(1) NOT NULL,
  `AUTOASSIGN` bit(1) NOT NULL,
  `REQUEST_BY_AUTHOR` bit(1) NOT NULL,
  `SENDBATCHMAIL` bit(1) NOT NULL,
  `EDITMANUNOTES` bit(1) NOT NULL,
  `VIEWMANUNOTES` bit(1) NOT NULL,
  `VIEWPEOPLENOTES` bit(1) NOT NULL,
  `EDITPEOPLENOTES` bit(1) NOT NULL,
  `EDITREVIEWERCOMMENTS` bit(1) NOT NULL,
  `INITIATEREBUTTAL` bit(1) NOT NULL,
  `UNDOEDITORASSIGNMENT` bit(1) NOT NULL,
  `UNINVITEREVIEWERS` bit(1) NOT NULL,
  `SELECTALTERNATES` bit(1) NOT NULL,
  `PROMOTEALTERNATES` bit(1) NOT NULL,
  `REMOVEALTERNATES` bit(1) NOT NULL,
  `EDITAFTERDECISION` bit(1) NOT NULL,
  `VIEWCOMPLETEDGRID` bit(1) NOT NULL,
  `TURNONMANUNOTESFLAG` bit(1) NOT NULL,
  `TURNOFFMANUNOTESFLAG` bit(1) NOT NULL,
  `EDITOTHERPEOPLEINFO` bit(1) NOT NULL,
  `SHOWPASSWORDS` bit(1) NOT NULL,
  `SEARCHONLYASSIGNEDMANUSCRIPTS` bit(1) NOT NULL,
  `VIEWTRANSMITTALFORM` bit(1) NOT NULL,
  `EDITTRANSMITTALFORMFIELDS` bit(1) NOT NULL,
  `RELEASETRANSMITTALFORM` bit(1) NOT NULL,
  `MODIFYEDITORDECISION` bit(1) NOT NULL,
  `VIEW_WEBFIRST` bit(1) NOT NULL,
  `VIEWWEBFIRSTTOC_SUBSCRIBERVIEW` bit(1) NOT NULL,
  `VIEWWEBFIRSTTOC_PAYPERVIEW` bit(1) NOT NULL,
  `MERGE_DUP_USERS` bit(1) NOT NULL,
  `INACTIVATE_USERS` bit(1) NOT NULL,
  `SEARCH_REVIEWERS_IN_OTHER_JOURNALS` bit(1) NOT NULL,
  `PROXY_REG_USER` bit(1) NOT NULL,
  `VIEW_PEOPLE_URL` bit(1) NOT NULL,
  `EDIT_PEOPLE_URL` bit(1) NOT NULL,
  `SENDEDITORREMINDER` bit(1) NOT NULL,
  `SENDAUTHORREMINDER` bit(1) NOT NULL,
  `RESTRICT_REMINDER_REPORTS` bit(1) NOT NULL,
  `PROXY_FOR_AUTHORS` bit(1) NOT NULL,
  `PROXY_FOR_REVIEWERS` bit(1) NOT NULL,
  `PROXY_FOR_EDITORS` bit(1) NOT NULL,
  `PROXY_FOR_PUBLISHERS` bit(1) NOT NULL,
  `REDIRECT_EDITOR` bit(1) NOT NULL,
  `CHANGECORRESEDITOR` bit(1) NOT NULL,
  `UNDO_ASSIGNMENTS_TO_SELF` bit(1) NOT NULL,
  `VIEWPEOPLEACTIVITYDETAILS` bit(1) NOT NULL,
  `BLIND_EDITORS` bit(1) NOT NULL,
  `OVERRIDE_AUTH_REVISION_DUE_DATE` bit(1) NOT NULL,
  `INITIATE_PROPOSALS` bit(1) NOT NULL,
  `INVITE_AUTHORS` bit(1) NOT NULL,
  `VIEW_ALL_PROP_AND_SUB_WITH_COM` bit(1) NOT NULL,
  `SET_FINAL_DISP_ON_PROPOSALS` bit(1) NOT NULL,
  `OVERRIDE_AUTHOR_INVITED_SUB_DUE_DATES` bit(1) NOT NULL,
  `EDIT_EDITOR_COMMENTS_AFTER_DECISION` bit(1) NOT NULL,
  `EDIT_EDITOR_RATINGS_DURING_DECISION` bit(1) NOT NULL,
  `DOWNLOADREVIEWERATTACHMENTS` bit(1) NOT NULL,
  `EDITREVIEWERATTACHMENTS` bit(1) NOT NULL,
  `DOWNLOAD_SOURCE_FILES` bit(1) NOT NULL,
  `SEARCH_MANAGE_CONFERENCE_SUBMISSIONS` bit(1) NOT NULL,
  `VIEW_REFERENCE_CHECKING_RESULTS` bit(1) NOT NULL,
  `SEND_ADHOC_EMAIL` bit(1) NOT NULL,
  `AUTHORMEDLINESEARCH` bit(1) NOT NULL,
  `CHANGE_EMAIL_ADDRESS` bit(1) NOT NULL,
  `CHANGE_PREF_CONTACT_METHOD` bit(1) NOT NULL,
  `CHANGE_EDITOR_ROLE` bit(1) NOT NULL,
  `TECHNICAL_CHECK` bit(1) NOT NULL,
  `VIEW_ADDITIONAL_PEOPLE_DETAILS` bit(1) NOT NULL,
  `EDIT_ADDITIONAL_PEOPLE_DETAILS` bit(1) NOT NULL,
  `VIEW_ADDITIONAL_MANUSCRIPT_DETAILS` bit(1) NOT NULL,
  `EDIT_ADDITIONAL_MANUSCRIPT_DETAILS` bit(1) NOT NULL,
  `VIEW_LEGACY_MANUSCRIPTS` bit(1) NOT NULL,
  `EDIT_LEGACY_MANUSCRIPT_FIELDS` bit(1) NOT NULL,
  `VIEW_SUBMISSION_FLAGS` bit(1) NOT NULL,
  `TURN_ON_SUBMISSION_FLAGS` bit(1) NOT NULL,
  `TURN_OFF_SUBMISSION_FLAGS` bit(1) NOT NULL,
  `TERMINATE_OUTSTANDING_ASSIGNMENTS` bit(1) NOT NULL,
  `VIEW_PRODUCTION_STATUS_HISTORY` bit(1) NOT NULL,
  `VIEW_PRODUCTION_CORR_HISTORY` bit(1) NOT NULL,
  `VIEW_PRODUCTION_NOTES_ON_MANU_DETAILS` bit(1) NOT NULL,
  `EDIT_PRODUCTION_NOTES_ON_MANU_DETAILS` bit(1) NOT NULL,
  `UPLOAD_REMOVE_COMPANION_FILES` bit(1) NOT NULL,
  `DOWNLOAD_COMPANION_FILES` bit(1) NOT NULL,
  `INITIATE_PRODUCTION` bit(1) NOT NULL,
  `MANAGE_SCHEDULE_GROUPS` bit(1) NOT NULL,
  `VIEW_PRODUCTION_STATUS_GRID` bit(1) NOT NULL,
  `VIEW_ALL_SUBMISSIONS_IN_PRODUCTION` bit(1) NOT NULL,
  `END_PRODUCTION_RETURN_TO_PRODUCTION` bit(1) NOT NULL,
  `ASSIGN_PRODUCTION_TASK` bit(1) NOT NULL,
  `CANCEL_PRODUCTION_TASK_ASSIGNMENT` bit(1) NOT NULL,
  `OVERRIDE_PRODUCTION_TASK_DUE_DATE` bit(1) NOT NULL,
  `RECEIVE_PRODUCTION_TASK` bit(1) NOT NULL,
  `SEND_PRODUCTION_REMINDERS` bit(1) NOT NULL,
  `EDIT_SUB_TARGET_ONLINE_PUB_DATE` bit(1) NOT NULL,
  `CAN_BE_CORR_PROD_ED` bit(1) NOT NULL,
  `CHANGE_CORR_PROD_ED` bit(1) NOT NULL,
  `VIEW_SCHEDULE_GROUPS` bit(1) NOT NULL,
  `VIEW_PRODUCTION_DETAILS` bit(1) NOT NULL,
  `CAN_RESCIND_DECISION` bit(1) NOT NULL,
  `CAN_VIEW_AT_RISK_SUBMISSIONS` bit(1) NOT NULL,
  `VIEW_PRODUCTION_TASK_ASSIGNMENT_HISTORY` bit(1) NOT NULL,
  `EDITORIAL_LAYOUT_ID` int(11) NOT NULL,
  `PROPOSAL_LAYOUT_ID` int(11) NOT NULL,
  `PRODUCTION_LAYOUT_ID` int(11) NOT NULL,
  `CAN_RUN_PROD_TASK_ASSIGN_TOT_RPT` bit(1) NOT NULL,
  `SEARCH_ALL_PROPOSALS` bit(1) NOT NULL,
  `CAN_VIEW_LINKED_SUBMISSION_GROUPS` bit(1) NOT NULL,
  `CAN_EDIT_LINKED_SUBMISSION_GROUPS` bit(1) NOT NULL,
  `CAN_CHANGE_ACTIVE_STATUS_OF_LINKED_SUMISSION_GROUP` bit(1) NOT NULL,
  `CAN_PROPOSE_REVIEWERS` bit(1) NOT NULL,
  `CAN_REMOVE_PROPOSED_REVIEWERS` bit(1) NOT NULL,
  `CAN_LINK_UNLINK_ALT_REVIEWERS` bit(1) NOT NULL,
  `Row_LastModified_TimeStamp` datetime NOT NULL,
  `SYSADMIN_RESTRICT_ACCESS` bit(1) NOT NULL,
  `CAN_INITIATE_CROSSCHECK` bit(1) NOT NULL,
  `CAN_SELECT_ALTERNATE_AUTHORS` bit(1) NOT NULL,
  `CAN_PROMOTE_ALTERNATE_AUTHORS` bit(1) NOT NULL,
  `CAN_REMOVE_ALTERNATE_AUTHORS` bit(1) NOT NULL,
  `VIEW_SG_PRODUCTION_TASK_ASSIGNMENT_HISTORY` bit(1) NOT NULL,
  `VIEW_SG_PRODUCTION_TASK_CORR_HISTORY` bit(1) NOT NULL,
  `ASSIGN_SG_PRODUCTION_TASK` bit(1) NOT NULL,
  `OVERRIDE_SG_PRODUCTION_TASK_DUE_DATE` bit(1) NOT NULL,
  `ACCESS_GOTO_PUB_LIST` bit(1) NOT NULL,
  `AUTO_LOGIN_TO_THIS_ROLE` bit(1) NOT NULL,
  `CREATE_SUBMISSION` bit(1) NOT NULL,
  `VIEW_FEES_AND_PAYMENTS` bit(1) NOT NULL,
  `EDIT_FEES_AND_PAYMENTS` bit(1) NOT NULL,
  `ADD_EDIT_EXTERNAL_CORRESPONDENCE` bit(1) NOT NULL,
  `CREATE_AUTOMATED_REVIEWER_REMINDERS` bit(1) NOT NULL,
  `CAN_SHARE_SEARCH` bit(1) NOT NULL,
  `CAN_MANAGE_OTHER_AUTHORS` bit(1) NOT NULL,
  `CAN_ACCESS_TRANSFERRED_SUBMISSIONS_FOLDER` bit(1) NOT NULL,
  `MANAGE_DISCUSSIONS` bit(1) NOT NULL,
  `VIEW_ALL_DISCUSSIONS` bit(1) NOT NULL,
  `CROSS_PUB_REPORTING_ENABLED` bit(1) NOT NULL,
  `VIEW_COS_SCHOLAR_UNIVERSE_AUTHOR_PROFILES` bit(1) NOT NULL,
  `NOTIFY_AUTHOR_AFTER_DECISION` bit(1) NOT NULL,
  `NOTIFY_AUTHOR_AFTER_DECISIONS_SPECIFIED` bit(1) NOT NULL,
  `USE_SUGGEST_EDITOR` bit(1) NOT NULL,
  `NOTIFY_AUTHOR_DRAFT_ONLY` bit(1) NOT NULL,
  `CAN_SCHEDULE_REPORTS` bit(1) NOT NULL,
  `CLASSIFICATION_RANKINGS_REQUIRED` bit(1) NOT NULL,
  `CAN_CLOSE_SUGGEST_EDITOR_QUEUE` bit(1) NOT NULL,
  `CREATE_AUTOMATED_AUTHOR_REVISION_REMINDERS` bit(1) NOT NULL,
  `CAN_SEND_USERNAME_PASSWORD` bit(1) NOT NULL,
  `CAN_ADD_EMAIL_ATTACHMENT` bit(1) NOT NULL,
  `CAN_VIEW_CROSSCHECK_RESULTS` bit(1) DEFAULT NULL,
  `CAN_SUBMIT_MANUSCRIPT_AS_AN_EDITOR` bit(1) NOT NULL,
  `RESTRICT_TO_EDITOR_PUBLISHER_USE_ONLY_ARTICLE_TYPES` bit(1) NOT NULL,
  `CAN_SET_ACCEPT_ON_SUBMISSION` bit(1) NOT NULL,
  `ALLOW_ASSIGN_TASK_OR_TO_SCHED_GROUP_ON_PRODUCTION_INITIATION` bit(1) NOT NULL,
  `CROSS_PUB_EAR_REPORTING_ENABLED` bit(1) NOT NULL,
  `VIEW_PEOPLE_FLAGS` bit(1) NOT NULL,
  `TURN_ON_PEOPLE_FLAGS` bit(1) NOT NULL,
  `TURN_OFF_PEOPLE_FLAGS` bit(1) NOT NULL,
  `CAN_ASSIGN_REVIEWERS` bit(1) NOT NULL,
  `REOPEN_REVIEW` bit(1) NOT NULL,
  `CAN_EDIT_OTHER_AUTHOR_EMAIL` bit(1) NOT NULL,
  `CAN_DRAFT_NOTIFY_AUTHOR_LETTER_FOR_ANY_DECISION` bit(1) NOT NULL,
  `CAN_SEND_NOTIFY_AUTHOR_LETTER_FOR_ANY_DECISION` bit(1) NOT NULL,
  `CAN_CREATE_AUTOMATED_AUTHOR_TRANSFER_REMINDERS` bit(1) NOT NULL,
  `CAN_MANAGE_ALL_DISCUSSIONS` bit(1) NOT NULL,
  `CAN_EDIT_LETTER_WHEN_RESEND` bit(1) NOT NULL,
  `CAN_ASSIGN_SUBMISSIONS_TO_MULTIPLE_SCHEDULE_GROUPS` bit(1) NOT NULL,
  `CAN_COPY_SCHEDULE_GROUP_CONTENTS_WHEN_COPYING` bit(1) NOT NULL,
  `CAN_VIEW_DUPLICATE_SUBMISSION_CHECK_RESULTS` bit(1) NOT NULL,
  `CAN_EDIT_TERMINATED_REVIEWS` bit(1) NOT NULL,
  `SHARE_REVIEWS_FOR_LINKED_SUBMISSIONS` bit(1) NOT NULL,
  `ISEMAILREQUIRED_AT_PROXYREGISTRATION` bit(1) NOT NULL,
  PRIMARY KEY (`EDITORROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editorrole`
--

LOCK TABLES `editorrole` WRITE;
/*!40000 ALTER TABLE `editorrole` DISABLE KEYS */;
INSERT INTO `editorrole` VALUES (1,'Staff','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',10,2,14,'','','','','','','','','2014-07-15 16:38:12','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(2,'Section Editor','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',9,2,3,'','','','','','','','','2013-12-19 18:17:50','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(3,'Academic Editor','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',7,1,3,'','','','','','','','','2013-12-19 18:18:02','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(4,'Administrator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',11,2,14,'','','','','','','','','2014-10-30 12:40:50','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(6,'Production Editor','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',10,1,14,'','','','','','','','','2014-10-30 19:35:38','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(7,'Staff Editor','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',12,2,3,'','','','','','','','','2013-12-19 20:24:49','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(8,'Staff EO','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',10,1,14,'','','','','','','','','2014-10-28 11:46:31','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(9,'Staff Admin','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',10,1,14,'','','','','','','','','2014-10-28 11:47:23','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(10,'Staff Single Select','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',10,1,3,'','','','','','','','','2013-12-19 20:25:26','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(11,'Subscan','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',29,10,3,'','','','','','','','','2014-09-25 13:53:46','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(12,'Managing Editor','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',12,1,3,'','','','','','','','','2014-07-15 16:43:00','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(13,'Consulting Editor','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',12,1,3,'','','','','','','','','2013-12-19 18:18:43','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(14,'Production Manager','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',11,1,14,'','','','','','','','','2013-12-19 20:47:14','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(15,'Billing','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',1,1,3,'','','','','','','','','2013-09-07 10:54:48','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(16,'Discussion Forum','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',10,2,3,'','','','','','','','','2014-07-15 16:43:20','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(17,'Staff ZYG','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',10,1,14,'','','','','','','','','2013-12-19 20:48:35','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(18,'Collection Editor','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',9,1,3,'','','','','','','','','2013-12-19 18:19:19','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(19,'Staff Other Journal','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',10,1,3,'','','','','','','','','2013-12-19 20:25:57','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(20,'Collection Editor (E)','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',1,1,3,'','','','','','','','','2013-09-07 10:54:48','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(21,'Creative/Marketing','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',1,2,3,'','','','','','','','','2013-09-07 10:54:48','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(22,'CW','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',14,14,14,'','','','','','','','','2013-09-07 10:54:48','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(23,'Guest Editor','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',7,1,3,'','','','','','','','','2013-12-19 18:19:53','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(24,'Freelance Editor','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',1,2,3,'','','','','','','','','2013-09-07 10:54:48','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(25,'Staff KW','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',10,2,3,'','','','','','','','','2014-05-08 14:57:59','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(26,'Staff J&J','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',1,2,3,'','','','','','','','','2013-12-19 20:47:38','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(27,'Editor (Production, placeholder)','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',1,2,3,'','','','','','','','','2013-09-07 10:54:48','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(28,'Staff EO Lead','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',10,10,14,'','','','','','','','','2014-10-28 11:47:11','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(29,'Academic Editor (resigned)','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',7,1,3,'','','','','','','','','2013-12-19 18:20:37','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(31,'SA','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',10,1,14,'','','','','','','','','2013-12-19 20:26:37','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(32,'Academic Editor (pending)','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',1,2,3,'','','','','','','','','2013-09-07 10:54:48','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(33,'Journal Office ONE','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',10,2,3,'','','','','','','','','2014-07-15 16:43:46','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(34,'Advisory Group','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',29,10,0,'','','','','','','','','2013-09-07 10:54:48','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(35,'Editorial Consultant','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',1,2,0,'','','','','','','','','2014-07-15 16:44:04','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(36,'Place Holder','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',1,2,0,'','','','','','','','','2013-10-04 18:42:05','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(37,'Academic Editor (sabbatical)','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',1,2,0,'','','','','','','','','2014-01-16 18:54:51','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''),(38,'Apex','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',14,14,14,'','','','','','','','','2014-10-31 19:08:47','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','');
/*!40000 ALTER TABLE `editorrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `PEOPLEID` int(11) NOT NULL,
  `FIRSTNAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `MIDDLENAME` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `LASTNAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `PTITLE` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `DEGREE` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `GREETING` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `WLOGIN` varchar(80) CHARACTER SET utf8 NOT NULL,
  `WPASSWORD` varchar(100) CHARACTER SET utf8 NOT NULL,
  `SSAN` char(11) DEFAULT NULL,
  `PNOTE` varchar(1001) CHARACTER SET utf8 DEFAULT NULL,
  `PUBLISHER` bit(1) NOT NULL,
  `PUBLISHERROLEID` int(11) DEFAULT NULL,
  `EDITOR` bit(1) NOT NULL,
  `EDITORROLEID` int(11) DEFAULT NULL,
  `REVIEWER` bit(1) NOT NULL,
  `REVIEWERROLEID` int(11) DEFAULT NULL,
  `FORBIDREVIEW` bit(1) NOT NULL,
  `BOARD` bit(1) NOT NULL,
  `REPRINTS` bit(1) DEFAULT NULL,
  `ADSALES` bit(1) DEFAULT NULL,
  `LASTUPDATE` datetime NOT NULL,
  `INACTIVE` bit(1) NOT NULL,
  `PROTECT_CONTACT_INFO` bit(1) NOT NULL,
  `LAST_SYNC_DATE` datetime DEFAULT NULL,
  `GUID` varchar(100) DEFAULT NULL,
  `PROXY_REG` tinyint(4) NOT NULL,
  `REG_DATE` datetime NOT NULL,
  `PROXY_REG_OPERATOR` int(11) DEFAULT NULL,
  `URL1` varchar(500) DEFAULT NULL,
  `URL2` varchar(500) DEFAULT NULL,
  `URL3` varchar(500) DEFAULT NULL,
  `EDITOR_DESCRIPTION` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `AVAILABLE_FOR_ROTATION` bit(1) NOT NULL,
  `LAST_ROTATION` datetime DEFAULT NULL,
  `USID` varchar(65) NOT NULL,
  `PREFERRED_LOGIN` int(11) NOT NULL,
  `PASSWORD_ENCRYPTED` bit(1) NOT NULL,
  `DEFAULT_LOGIN_MENU` int(11) NOT NULL,
  `NEVER_LOGGED_IN_BEFORE` bit(1) NOT NULL,
  `IMPORT_PEOPLE_ID` varchar(20) DEFAULT NULL,
  `RESET_PWD` bit(1) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCK_DATE` datetime DEFAULT NULL,
  `Row_LastModified_TimeStamp` datetime NOT NULL,
  `CROSS_PUB_LOGIN_LIST` varchar(1001) DEFAULT NULL,
  `DEFAULT_LANGUAGE` int(11) DEFAULT NULL,
  `PERSONAL_IDENTIFIERS_ID` int(11) DEFAULT NULL,
  `IMPORT_PEOPLE_ORCID` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`PEOPLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (764952,'zhiming','','zheng','pro','','','zhengzhiming2011','oAfgLwG9Ti5ZcfLL8LSZFg==','','','',0,'',0,'',3,'','','','','2014-11-02 09:19:28','','','2014-11-02 07:04:53','PONE-764952',0,'2014-11-02 07:04:49',0,'','','','','','0000-00-00 00:00:00','458CFC42-7374-43B2-B9CA-477342D9DB01',1,'',0,'','','','','0000-00-00 00:00:00','2014-11-02 09:19:28','',1,289162,''),(764953,'Jaehee','','Jung','Dr.','','','jaehee@kaist.ac.kr','+NVX9gGWrXEtQ09Z/mzGDQ==','','','',0,'',0,'',3,'','','','','2014-11-02 07:09:12','','','2014-11-02 07:09:12','PONE-764953',0,'2014-11-02 07:09:10',0,'','','','','','0000-00-00 00:00:00','5A3A62D5-2590-4076-B613-41E489359992',1,'',0,'','','','','0000-00-00 00:00:00','2014-11-02 07:10:21','',0,289163,''),(764954,'Bianca','','Saladin','Mrs','','','BSaladin-645','lS86bo1WMU1m/XURsYnYtQ==','','','',0,'',0,'',3,'','','','','2014-11-02 07:14:29','','','2014-11-02 07:14:29','PONE-764954',1,'2014-11-02 07:14:24',239003,'','','','','','0000-00-00 00:00:00','C4186187-C2C6-4348-B1F3-26DFE3395DB6',1,'',0,'','','','','0000-00-00 00:00:00','2014-11-02 07:14:29','',0,289164,''),(764955,'Lin','','Fan','Dr','','','fanlin','RsvRjVhWkwd+3X1Ze4Ch2Q==','','','',0,'',0,'',3,'','','','','2014-11-02 09:00:17','','','2014-11-02 07:17:06','PONE-764955',0,'2014-11-02 07:17:04',0,'','','','','','0000-00-00 00:00:00','1DFBC4AE-D916-43B0-8D0F-0408354B5191',1,'',0,'','','','','0000-00-00 00:00:00','2014-11-02 09:00:17','',1,289165,''),(764956,'Natalia','','Tschowri','Dr','','','tschowrn','ecD62Pk0YLcJluhW5srzgw==','','','',0,'',0,'',3,'','','','','2014-11-02 07:22:44','','','2014-11-02 07:22:44','PGENETICS-766717',0,'2014-11-02 07:22:44',0,'','','','','','0000-00-00 00:00:00','9ABFE40A-FC70-4220-BD35-7F4C530EC47F',1,'',0,'','','','','0000-00-00 00:00:00','2014-11-02 07:22:44','',0,289166,''),(764957,'Marcin','','Cwynar','Dr.','Ph.D.','marcincw','marcincw','QeywufqURIxBQgozJUefbA==','','','',0,'',0,'',3,'','','','','2014-11-03 03:46:19','','','2014-11-02 07:37:08','PONE-764957',0,'2014-11-02 07:37:05',0,'','','','','','0000-00-00 00:00:00','7D2B4583-2899-43F6-B277-58F55342FFF0',1,'',0,'','','','','0000-00-00 00:00:00','2014-11-03 03:46:19','',1,289167,''),(764958,'Donald','','Scavia','','','','DScavia-233','C+DZMoUdK6UCLyLMJVMSQw==','','','',0,'',0,'',3,'','','','','2014-11-02 07:41:28','','','0000-00-00 00:00:00','',1,'2014-11-02 07:41:27',176273,'','','','','','0000-00-00 00:00:00','EE1B3632-4051-40E4-8FD2-33F736AC58FD',1,'',0,'','','','','0000-00-00 00:00:00','2014-11-02 07:41:28','',0,289168,''),(764959,'Dowell','','Myers','','','','DMyers-349','BDnSNN0PeaGcV69pjM00sA==','','','',0,'',0,'',3,'','','','','2014-11-02 07:41:28','','','0000-00-00 00:00:00','',1,'2014-11-02 07:41:28',176273,'','','','','','0000-00-00 00:00:00','F6E89E9C-AA44-40D5-ACB6-FA180CEEC4A8',1,'',0,'','','','','0000-00-00 00:00:00','2014-11-02 07:41:28','',0,289169,''),(764960,'Jane','L','Messina','','','','JMessina-284','LcAy8g3GDLFSuXnEeY5rZQ==','','','',0,'',0,'',3,'','','','','2014-11-02 07:41:28','','','0000-00-00 00:00:00','',1,'2014-11-02 07:41:28',176273,'','','','','','0000-00-00 00:00:00','D8BD2F89-1773-48C0-9CA3-E4771E3364F3',1,'',0,'','','','','0000-00-00 00:00:00','2014-11-02 07:41:28','',0,289170,''),(764961,'Elizabeth','C.','Mannshardt-Shamseldin','','','','EMannshardt-Shamseldin-243','NhzZW3/srIGY/Gaxr9gOLw==','','','',0,'',0,'',3,'','','','','2014-11-02 07:41:28','','','0000-00-00 00:00:00','',1,'2014-11-02 07:41:28',176273,'','','','','','0000-00-00 00:00:00','8F9CCF45-53CC-47B8-8578-8ED70BBDDCF1',1,'',0,'','','','','0000-00-00 00:00:00','2014-11-02 07:41:28','',0,289171,'');
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-06 17:34:43
