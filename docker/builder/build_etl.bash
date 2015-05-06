#!/bin/bash

BUILDDIR="/build"

echo Building ETL

cd /src/lib
./install-jars-in-local-mvn-repo.sh
cd ..
mvn clean package

cp target/*.jar $BUILDDIR
ln -s $BUILDDIR/named-entity-etl-*-jar-with-dependencies.jar $BUILDDIR/ned-etl.jar

rm -rf target


echo $BUILDDIR dir:
ls -l $BUILDDIR
