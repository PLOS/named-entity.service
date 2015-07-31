CREATE TABLE ringgold.alt_name (
    Rec_ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    P_Code BIGINT(20) NOT NULL DEFAULT '0',
    Name VARCHAR(250) NOT NULL DEFAULT '',
    Ext_Name VARCHAR(250) NULL,
    City VARCHAR(100) NULL,
    Ext_City VARCHAR(100) NULL,
    Country VARCHAR(10) NOT NULL DEFAULT '',
    Lang VARCHAR(35) NULL,
    Notes VARCHAR(60) NULL,
    Timestamp TIMESTAMP NOT NULL,
    PRIMARY KEY (Rec_ID) ) Engine=MyISAM;

CREATE TABLE ringgold.consortia (
    Rec_ID bigint(20) NOT NULL AUTO_INCREMENT,
    Consortia_ID BIGINT(30) NOT NULL DEFAULT '0',
    P_Code BIGINT(20) NULL,
    Name VARCHAR(130) NULL,
    Timestamp TIMESTAMP NOT NULL,
    PRIMARY KEY (Rec_ID) ) Engine=MyISAM;

CREATE TABLE ringgold.deleted_id (
    ID__No bigint(20) NOT NULL auto_increment,
    old_id bigint(20) NOT NULL default '0',
    new_id bigint(20) NOT NULL default '0',
    details varchar(150) NULL,
    Timestamp timestamp NOT NULL,
    PRIMARY KEY (ID__No) ) Engine=MyISAM;

CREATE TABLE ringgold.identifiers ( 
    Rec_ID bigint(20) NOT NULL auto_increment,
    P_Code bigint(20) NOT NULL default '0',
    Identifier varchar(30) NOT NULL default '',
    Value varchar(35) NOT NULL default '0',
    Timestamp timestamp NOT NULL,
    PRIMARY KEY (Rec_ID) ) Engine=MyISAM;

CREATE TABLE ringgold.multi ( 
    Rec_ID int(11) NOT NULL auto_increment,
    GP_Code bigint(20) NOT NULL default '0',
    P_Code bigint(20) NOT NULL default '0',
    Class varchar(20) NOT NULL default '',
    Number smallint(6) NULL,
    Timestamp timestamp NOT NULL,
    PRIMARY KEY (Rec_ID) ) Engine=MyISAM;

CREATE TABLE ringgold.notes (
    Rec_ID int(11) NOT NULL auto_increment,
    P_Code bigint(20) NOT NULL default '0',
    Notes varchar(250) NOT NULL default '',
    Timestamp timestamp NOT NULL,
    PRIMARY KEY (Rec_ID),
    UNIQUE (P_Code) ) Engine=MyISAM;

CREATE TABLE ringgold.parents (
    Rec_ID bigint(20) NOT NULL auto_increment,
    GP_Code bigint(20) NULL default '0',
    P_Code bigint(20) NOT NULL default '0',
    Name varchar(250) NOT NULL default '',
    Ext_Name varchar(250) NULL,
    City varchar(100) NOT NULL default '',
    Ext_City varchar(100) NULL,
    Zip varchar(30) NULL,
    Country varchar(10) NOT NULL default '',
    State varchar(25) NULL,
    Type varchar(35) NOT NULL default '',
    Timestamp timestamp NOT NULL,
    PRIMARY KEY (Rec_ID),
    UNIQUE (P_Code) ) Engine=MyISAM;

CREATE TABLE ringgold.sizes (
    Rec_ID bigint(20) NOT NULL auto_increment,
    P_Code bigint(20) NOT NULL default '0',
    Kind varchar(30) NOT NULL default '',
    Value mediumint(9) NOT NULL default '0',
    Timestamp timestamp NOT NULL,
    PRIMARY KEY (Rec_ID) ) Engine=MyISAM;

CREATE TABLE ringgold.tiers ( 
    Rec_ID int(11) NOT NULL auto_increment,
    P_Code bigint(20) NOT NULL default '0',
    Tier_Type varchar(25) NOT NULL default '',
    Tier varchar(40) NOT NULL default '',
    Timestamp timestamp NOT NULL,
    PRIMARY KEY (Rec_ID) ) Engine=MyISAM;

CREATE TABLE ringgold.types ( 
    ID mediumint(9) NOT NULL auto_increment,
    Type varchar(50) NULL,
    Def varchar(150) NULL,
    Timestamp timestamp NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE (Type) ) Engine=MyISAM;

CREATE TABLE ringgold.rg_tiers ( 
    ID_No mediumint(9) NOT NULL auto_increment,
    RG_tier char(3) NULL,
    RG_Tier_Description varchar(100) NULL,
    Example text,
    Timestamp timestamp NOT NULL,
    PRIMARY KEY (ID_No) ) Engine=MyISAM;

CREATE TABLE ringgold.url ( 
    Rec_ID int(11) NOT NULL auto_increment,
    P_Code bigint(20) NOT NULL default '0',
    URL varchar(200) NOT NULL default '',
    alt_url tinyint(1) NOT NULL default '0',
    Timestamp timestamp NOT NULL,
    PRIMARY KEY (Rec_ID) ) Engine=MyISAM;
