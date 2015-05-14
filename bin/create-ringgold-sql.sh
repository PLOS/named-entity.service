#!/bin/bash

ringgold_zip=(PLOS_Identify_*.zip)

if [ ! -f $ringgold_zip ]; then
    echo -e "\nNo Ringgold archive found (ex: PLOS_Identify_20150615.zip)\n"
    exit
fi

echo -e "\nUnzipping $ringgold_zip"
unzip $ringgold_zip

ringgold_all_sql=(PLOS_Identify_*_utf8.sql)

ringgold_schema_sql=ringgold-schema.mysql.sql
ringgold_data_sql=ringgold-data.mysql.sql

echo -e "\nCreating Ringgold SCHEMA file ($ringgold_schema_sql)"
cat > $ringgold_schema_sql <<- EOM
DROP SCHEMA IF EXISTS ringgold;
CREATE SCHEMA ringgold DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE ringgold;
EOM
grep "^CREATE TABLE" $ringgold_all_sql | sed -e "s/_new//g" >> $ringgold_schema_sql

# delete schema and create table statements from ringgold sql file.
# remaining lines will be inserts, some of which span more than one line.
# these will be appended to the data file.

sed -i -e 1,3d $ringgold_all_sql
sed -i '/^CREATE TABLE/d' $ringgold_all_sql

echo -e "Creating Ringgold DATA file ($ringgold_data_sql)\n"
cat > $ringgold_data_sql <<-EOM
USE ringgold;
EOM
cat $ringgold_all_sql | sed -e "s/_new//g" >> $ringgold_data_sql
