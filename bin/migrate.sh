#!/usr/bin/env bash

# stop script if any command exits with non-zero (same as set -e)
set -o errexit

# change to this directory
cd "$( dirname "${BASH_SOURCE[0]}" )"

function exec_sql {
    sql=${1}
    echo $(mysql --host=${NED_DB_HOST} --user=${NED_DB_USER} --password=${NED_DB_PASSWORD} -se "$sql" 2>/dev/null)
}

echo -e "\n==> Applying DB Migrations (schema:namedEntities)"

bash ./flyway.sh -url="jdbc:mysql://${NED_DB_HOST}:3306/namedEntities" \
     -user=${NED_DB_USER} -password=${NED_DB_PASSWORD} -locations=filesystem:${MIGRATIONS_DIR} migrate


ringgold_gz=(${RINGGOLD_DIR}/ringgold*.gz)
if [ ${#ringgold_gz[@]} -ne 1 ]; then
    echo -e "\nUnexpected # of Ringgold zip's found (expected:1 found:${#ringgold_gz[@]}). Aborting.\n"
    exit 1
fi

echo -e "\n==> Importing Ringgold Database ($ringgold_gz)\n"

ringgold_db_sql="
  select count(*) from information_schema.tables
    where lower(table_schema)='ringgold' and lower(table_name)='institutions'"
ringgold_db_exist=$(exec_sql "$ringgold_db_sql")

if [ $ringgold_db_exist -eq 0 ]; then
    gunzip -c -d $ringgold_gz | mysql --host=${NED_DB_HOST} --user=${NED_DB_USER} --password=${NED_DB_PASSWORD} 2>/dev/null
    echo "Done"
else
    echo "Non-empty Ringgold DB detected! Skipping Ringgold import."
fi

echo "Done"
