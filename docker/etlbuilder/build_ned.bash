#!/bin/bash

BUILDDIR="/build"
CONTEXTFILE=$BUILDDIR/context.xml

rm $BUILDDIR/*

echo Building NED

cd /src
./ned.sh install

echo target dir:
#ls target

cp target/*.?ar $BUILDDIR

ln -s $BUILDDIR/named-entity-service-*.war $BUILDDIR/ned.war
cp src/main/resources/ned-*.mysql.sql $BUILDDIR


if [ ! -f "${BUILDDIR}/context.xml" ]; then
	echo Creating $CONTEXTFILE
	cp config/tomcat/context.xml $CONTEXTFILE

	# TODO: source these vars from a shared environment file

	sed -i 's/${db.username}/ned/' $CONTEXTFILE
	sed -i 's/${db.password}/ned/' $CONTEXTFILE
	sed -i 's/${db.driver}/com.mysql.jdbc.Driver/' $CONTEXTFILE
	sed -i 's/${db.url}/jdbc:mysql:\/\/neddb:3306\/namedEntities/' $CONTEXTFILE
fi

rm -rf target

#echo $CONTEXTFILE
#cat $CONTEXTFILE
