#!/usr/bin/env bash
# This script builds a docker container (an Ubuntu 14.04 image with Percona server and client installed) from
# the Docker file and implements the database schema using plos_match_schema.sql.

DB_USER=ned
DB_PASSWORD=ned
DB_SCHEMA=namedEntities
MYSQL_ROOT_PASSWORD=root

function build () {

  # if image doesn't exist
  docker pull percona:5.6
  #docker build --rm -t "plos/percona" ${args[0]}

  docker run -d --name neddb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} percona:5.6

  if [ -x /usr/local/bin/boot2docker ]
  then
    DB_HOST=`boot2docker ip`
  else
    DB_HOST=`docker inspect --format '{{ .NetworkSettings.IPAddress }}' neddb`
  fi
  DB_PORT=3306

  MYSQL_ROOT="mysql -h $DB_HOST -P $DB_PORT --user=root --password=root --default-character-set=utf8"

  # We've seen a weird pattern (especially on teamcity build agents) where the percona
  # server is up, but it takes some unpredictable time until the correct permissions
  # are applied.  So we have to just try once per second until this works.
  until $MYSQL_ROOT -e exit
  do
    sleep 1
  done

  echo -e "\nCreating DB User (ned)"
  echo "CREATE USER 'ned' IDENTIFIED BY 'ned'" | ${MYSQL_ROOT}
  echo "GRANT ALL PRIVILEGES ON *.* TO 'ned'@'%' WITH GRANT OPTION; FLUSH PRIVILEGES" | ${MYSQL_ROOT}
  echo "Finished creating user."

  set -e
  
  #echo "DROP DATABASE IF EXISTS namedEntities;" | $MYSQL_ROOT
  #echo "CREATE DATABASE namedEntities;" | $MYSQL_ROOT

  # Load all schema migrations.
  # TODO: figure out if there is a way for flyway and maven to do this automatically.
  # The addition of docker (this script) complicates things.
  for F in `\ls ${args[2]}/*.sql`
  do
    cat "$F" | $MYSQL_ROOT namedEntities
  done

  for F in `\ls ${args[3]}/*.sql`
  do
    cat "$F" | $MYSQL_ROOT namedEntities
  done

  #echo "DROP DATABASE IF EXISTS ringgold;" | $MYSQL_ROOT
  #echo "CREATE DATABASE ringgold;" | $MYSQL_ROOT

  for F in `\ls ${args[3]}/ringgold/*.sql`
  do
    cat "$F" | $MYSQL_ROOT ringgold
  done

}

args=("$@")

if [ -x /usr/local/bin/boot2docker ]
then
  export DOCKER_HOST="tcp://$(boot2docker ip 2>/dev/null):2376"
  export DOCKER_CERT_PATH=${args[1]}
  export DOCKER_TLS_VERIFY=1
fi

docker ps | grep -q 'neddb'
if [ $? -eq 0 ]
then
  echo "xneddb container is running ..."
    build
else
  docker ps -a | grep -q 'neddb'
  if [ $? -eq 0 ]
  then
    docker start neddb
  else
      # we can use "docker images | grep 'neddb'" to add an additional check but since docker uses
      # cached images, it doesn't hurt to skip this step.
    build
  fi
fi
