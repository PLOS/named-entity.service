#!/usr/bin/env bash

DB_PORT=3306

function run_docker {
  docker run -d --name neddb ${1} -e MYSQL_ROOT_PASSWORD=root percona:5.6 2> /dev/null
}

function build () {

  docker images | grep -qw 'percona'
  if [ $? -eq 1 ]
  then
      # percona image not found. pull it from docker hub.
      docker pull percona:5.6
  fi

  if boot2docker ip >/dev/null 2>&1 ; then
    # <docker-vm-port>:<docker-container-port>
    run_docker "-p ${DB_PORT}:${DB_PORT}"
    DB_HOST=$(boot2docker ip)
  elif docker-machine ip default >/dev/null 2>&1 ; then
    run_docker "-p ${DB_PORT}:${DB_PORT}"
    DB_HOST=$(docker-machine ip default)
  else
    run_docker
    DB_HOST=$(docker inspect --format '{{ .NetworkSettings.IPAddress }}' neddb)
  fi

  MYSQL_ROOT="mysql -h $DB_HOST -P $DB_PORT --user=root --password=root --default-character-set=utf8"

  # We've seen a weird pattern (especially on teamcity build agents) where the percona
  # server is up, but it takes some unpredictable time until the correct permissions
  # are applied.  So we have to just try once per second until this works.
  TRY_CONNECT_COUNT=0

  until $MYSQL_ROOT -e exit 2> /dev/null
  do
    echo "Trying to connect to MySQL $DB_HOST:$DB_PORT  ($TRY_CONNECT_COUNT)"
    ((TRY_CONNECT_COUNT++))
    if [ $TRY_CONNECT_COUNT -gt 60 ]
    then
      echo "MySQL connection tries exceeded"
      exit 1
    fi
    sleep 1
  done

  echo "Docker IP: $DB_HOST"

  echo "SET GLOBAL max_connections = 500" | $MYSQL_ROOT 2>/dev/null

  echo "Create DB User: ned"
  $MYSQL_ROOT << NED_USER_SQL 2>/dev/null
    CREATE USER 'ned' IDENTIFIED BY '';
    GRANT ALL PRIVILEGES ON *.* TO 'ned'@'%' WITH GRANT OPTION;
    FLUSH PRIVILEGES;
NED_USER_SQL

  set -e

  echo "Create Schema: namedEntities"
  $MYSQL_ROOT << NED_SCHEMA_SQL 2>/dev/null
    DROP SCHEMA IF EXISTS namedEntities;
    CREATE SCHEMA namedEntities DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
NED_SCHEMA_SQL

  echo "Create Schema: ringgold"
  $MYSQL_ROOT << RINGGOLD_SCHEMA_SQL 2>/dev/null
    DROP SCHEMA IF EXISTS ringgold;
    CREATE SCHEMA ringgold DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
RINGGOLD_SCHEMA_SQL

  for F in `ls -v ringgold/V*.sql`
  do
    cat "$F" | $MYSQL_ROOT ringgold 2>/dev/null
  done
}

args=("$@")

docker ps | grep -q 'neddb'
if [ $? -eq 0 ]
then
  echo -e "\n(neddb) Docker container is running ..."
else
  docker ps -a | grep -q 'neddb'
  if [ $? -eq 0 ]
  then
    echo -e "\nStarting (neddb) Docker container ..."
    docker start neddb
  fi
fi
build
