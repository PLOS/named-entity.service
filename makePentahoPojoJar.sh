#!/bin/bash

cd target/classes
jar cvf ../ned-pojos.jar org/plos/namedentity/api/* org/plos/namedentity/validate/Validatable.class
cd ..

# make symlinks (examples below)

#ln -s `pwd`/ned-pojos.jar ~/applications/pentaho/lib
#ln -s `pwd`/ned-pojos.jar ~/src/named-entity.etl/update-ned.plugin/lib

