#!/bin/bash

cd target/classes
jar cvf ../ned-pojos.jar org/plos/namedentity/api/* org/plos/namedentity/validate/Validatable.class META-INF
cd ..

# make symlinks or copy the jar to external directories (examples below)

#ln -s `pwd`/ned-pojos.jar ~/applications/pentaho/lib
cp ned-pojos.jar ~/applications/pentaho/lib
#ln -s `pwd`/ned-pojos.jar ~/src/named-entity.etl/update-ned.plugin/lib
cp ned-pojos.jar ~/src/named-entity.etl/update-ned.plugin/lib

