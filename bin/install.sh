#!/usr/bin/env bash

# stop script if any command exits with non-zero (same as set -e)
set -o errexit

T1=`date`

echo "/*---------------------------------------------------------------------*/"
echo "/* Applying DB Migrations (schema:namedEntities)                       */"
echo "/*---------------------------------------------------------------------*/"

bash ./flyway -url="jdbc:mysql://${NED_DB_HOST}:3306/namedEntities" \
     -user=${NED_DB_USER} -password=${NED_DB_PASSWORD} -locations=filesystem:../database/migrations migrate


ned_war=(named-entity-service*.war)
if [ ${#ned_war[@]} -ne 1 ]; then
    echo -e "\nUnexpected # of NED war's found (expected:1 found:${#ned_war[@]}). Aborting.\n"
    exit 1
fi

echo "/*---------------------------------------------------------------------*/"
echo "/* Deploying WAR: $ned_war                                             */"
echo "/*---------------------------------------------------------------------*/"

sudo cp $ned_war ${NED_WEBAPPS}/v1.war


ringgold_gz=(${RINGGOLD_DIR}/ringgold*.gz)
if [ ${#ringgold_gz[@]} -ne 1 ]; then
    echo -e "\nUnexpected # of Ringgold zip's found (expected:1 found:${#ringgold_gz[@]}). Aborting.\n"
    exit 1
fi

echo "/*---------------------------------------------------------------------*/"
echo "/* Importing Ringgold Database                                         */"
echo "/*---------------------------------------------------------------------*/"

# check if ringgold data exists. abort.

mysql -user=${NED_DB_USER} -password=${NED_DB_PASSWORD} < $ringgold_gz

echo "Started  : $T1"
echo "Finished : `date`"
