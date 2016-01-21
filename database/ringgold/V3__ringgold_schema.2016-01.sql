-- delete tables from previous version of ringgold schema. observe that some
-- of the table names have changed (ex: singular name -> plural name)

DROP TABLE IF EXISTS ringgold.alt_name;
DROP TABLE IF EXISTS ringgold.consortia;
DROP TABLE IF EXISTS ringgold.deleted_id;
DROP TABLE IF EXISTS ringgold.identifiers;
DROP TABLE IF EXISTS ringgold.multi;
DROP TABLE IF EXISTS ringgold.notes;
DROP TABLE IF EXISTS ringgold.parents;
DROP TABLE IF EXISTS ringgold.rg_tiers;
DROP TABLE IF EXISTS ringgold.sizes;
DROP TABLE IF EXISTS ringgold.tiers;
DROP TABLE IF EXISTS ringgold.types;
DROP TABLE IF EXISTS ringgold.url;

CREATE TABLE ringgold.alt_names (
    rec_id int(11) NOT NULL AUTO_INCREMENT,
    ringgold_id int(11) NOT NULL DEFAULT '0',
    name varchar(255) NOT NULL DEFAULT '',
    city varchar(100) DEFAULT NULL,
    country varchar(10) NOT NULL DEFAULT '',
    language varchar(35) DEFAULT NULL,
    notes varchar(60) DEFAULT NULL,
    timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (rec_id)
) ENGINE=MyISAM;

CREATE TABLE ringgold.consortia (
  rec_id int(11) NOT NULL AUTO_INCREMENT,
  consortia_ringgold_id int(11) NOT NULL DEFAULT '0',
  member_ringgold_id int(11) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (rec_id)
) ENGINE=MyISAM;

CREATE TABLE ringgold.deleted_ids (
  rec_id int(11) NOT NULL AUTO_INCREMENT,
  old_ringgold_id int(11) NOT NULL DEFAULT '0',
  new_ringgold_id int(11) NOT NULL DEFAULT '0',
  details varchar(150) DEFAULT NULL,
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (rec_id)
) ENGINE=MyISAM;

CREATE TABLE ringgold.identifiers (
  rec_id int(11) NOT NULL AUTO_INCREMENT,
  ringgold_id int(11) NOT NULL DEFAULT '0',
  identifier_type varchar(30) NOT NULL DEFAULT '',
  value varchar(35) NOT NULL DEFAULT '0',
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (rec_id),
  KEY ringgold_id_3 (ringgold_id)
) ENGINE=MyISAM;

CREATE TABLE ringgold.institutions (
  rec_id int(11) NOT NULL AUTO_INCREMENT,
  parent_ringgold_id int(11) NOT NULL DEFAULT '0',
  ringgold_id int(11) NOT NULL DEFAULT '0',
  top_ringgold_id int(11) NOT NULL DEFAULT '0',
  name varchar(255) NOT NULL DEFAULT '',
  city varchar(100) NOT NULL DEFAULT '',
  post_code varchar(30) DEFAULT NULL,
  country varchar(10) NOT NULL DEFAULT '',
  state varchar(25) DEFAULT NULL,
  type varchar(35) NOT NULL DEFAULT '',
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (rec_id),
  UNIQUE KEY ringgold_id (ringgold_id)
) ENGINE=MyISAM;

CREATE TABLE ringgold.multies (
  rec_id int(11) NOT NULL AUTO_INCREMENT,
  ringgold_id int(11) NOT NULL DEFAULT '0',
  parent_ringgold_id int(11) NOT NULL DEFAULT '0',
  relationship varchar(20) NOT NULL DEFAULT '',
  number smallint(6) DEFAULT NULL,
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (rec_id)
) ENGINE=MyISAM;

CREATE TABLE ringgold.notes (
  rec_id int(11) NOT NULL AUTO_INCREMENT,
  ringgold_id int(11) NOT NULL DEFAULT '0',
  notes varchar(250) NOT NULL DEFAULT '',
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (rec_id),
  UNIQUE KEY ringgold_id (ringgold_id)
) ENGINE=MyISAM;

CREATE TABLE ringgold.sizes (
  rec_id int(11) NOT NULL AUTO_INCREMENT,
  ringgold_id int(11) NOT NULL DEFAULT '0',
  size_type varchar(30) NOT NULL DEFAULT '',
  value varchar(255) NOT NULL DEFAULT '',
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (rec_id),
  KEY ringgold_id_3 (ringgold_id)
) ENGINE=MyISAM;

CREATE TABLE ringgold.taxonomy (
  rec_id int(11) NOT NULL AUTO_INCREMENT,
  vocabulary varchar(255) NOT NULL DEFAULT '0',
  name varchar(255) NOT NULL DEFAULT '',
  description text,
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (rec_id)
) ENGINE=MyISAM;

CREATE TABLE ringgold.tiers (
  rec_id int(11) NOT NULL AUTO_INCREMENT,
  ringgold_id int(11) NOT NULL DEFAULT '0',
  tier_type varchar(25) NOT NULL DEFAULT '',
  value varchar(40) NOT NULL DEFAULT '',
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (rec_id),
  KEY ringgold_id_3 (ringgold_id)
) ENGINE=MyISAM;

CREATE TABLE ringgold.urls (
  rec_id int(11) NOT NULL AUTO_INCREMENT,
  ringgold_id int(11) NOT NULL DEFAULT '0',
  url varchar(255) NOT NULL DEFAULT '',
  notes varchar(200) NOT NULL DEFAULT '',
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (rec_id),
  KEY ringgold_id (ringgold_id)
) ENGINE=MyISAM;
