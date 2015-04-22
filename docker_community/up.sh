#!/bin/bash

DIR=`pwd`

WAR="../target/named-entity-service-*.war"

BUILDDIR="build"

if [ -f $WAR ]; then
	echo found .war file: $WAR
else
	echo Building .war
	cd ..
	./ned.sh install
	cd $DIR
fi

pwd
echo Prepping build directory

# TODO: build context.xml from config directory

mkdir -p $BUILDDIR


if [ ! -f "${BUILDDIR}/ned.war" ]; then
	cp $WAR "${BUILDDIR}/ned.war"
fi

cp ../src/main/resources/ned-*.mysql.sql build

ls build

docker-compose rm --force -v && docker-compose build && docker-compose up
