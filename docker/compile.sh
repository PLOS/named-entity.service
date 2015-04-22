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

echo Prepping build directory

mkdir -p $BUILDDIR

if [ ! -f "${BUILDDIR}/ned.war" ]; then
	cp $WAR "${BUILDDIR}/ned.war"
fi

if [ ! -f "${BUILDDIR}/context.xml" ]; then
	echo Creating context.xml
	cp ../config/tomcat/context.xml $BUILDDIR

	# TODO: source these vars from a shared environment file

	sed -i 's/${db.username}/ned/' $BUILDDIR/context.xml
	sed -i 's/${db.password}/ned/' $BUILDDIR/context.xml
	sed -i 's/${db.driver}/com.mysql.jdbc.Driver/' $BUILDDIR/context.xml
	sed -i 's/${db.url}/jdbc:mysql:\/\/neddb:3306\/namedEntities/' $BUILDDIR/context.xml
fi

cp ../src/main/resources/ned-*.mysql.sql build

ls build
