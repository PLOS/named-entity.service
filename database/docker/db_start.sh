#!/usr/bin/env bash

DB_PORT=3306

function build () {

  docker images | grep -qw 'percona'
  if [ $? -eq 1 ]
  then
      # percona image not found. pull it from docker hub.
      docker pull percona:5.6
  fi

  docker run -d --name neddb -p 3304:$DB_PORT -e MYSQL_ROOT_PASSWORD=root percona:5.6 2> /dev/null

  if [ -x /usr/local/bin/boot2docker ]
  then
    DB_HOST=`boot2docker ip`
  else
    DB_HOST=`docker inspect --format '{{ .NetworkSettings.IPAddress }}' neddb`
  fi

  MYSQL_ROOT="mysql -h $DB_HOST -P $DB_PORT --user=root --password=root --default-character-set=utf8"

  # We've seen a weird pattern (especially on teamcity build agents) where the percona
  # server is up, but it takes some unpredictable time until the correct permissions
  # are applied.  So we have to just try once per second until this works.
  until $MYSQL_ROOT -e exit
  do
    sleep 1
  done

  echo -e "Docker IP: $DB_HOST"

  echo -e "Create DB User: ned"
  echo "CREATE USER 'ned' IDENTIFIED BY 'ned'" | $MYSQL_ROOT 2>/dev/null
  echo "GRANT ALL PRIVILEGES ON *.* TO 'ned'@'%' WITH GRANT OPTION; FLUSH PRIVILEGES" | $MYSQL_ROOT

  set -e

  echo "Create Schema: namedEntities"
  echo "DROP SCHEMA IF EXISTS namedEntities;" | $MYSQL_ROOT
  echo "CREATE SCHEMA namedEntities DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;" | $MYSQL_ROOT

  echo "Create Schema: ringgold"
  echo "DROP SCHEMA IF EXISTS ringgold;" | $MYSQL_ROOT
  echo "CREATE SCHEMA ringgold;" | $MYSQL_ROOT

  for F in `\ls -v test/ringgold/V*.sql`
  do
    cat "$F" | $MYSQL_ROOT ringgold
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
