#!/bin/bash

IMAGE=maven:3.3-jdk-8
MAVEN_CACHE=maven_cache
NED_BUILD=nedbuild

if [ "$1" == "clean" ]; then
   echo Removing cache
   docker rm $MAVEN_CACHE
   exit 0
fi

# create the cache for mavan
docker create -v /build --name $NED_BUILD $IMAGE /bin/true

# create the volume for sharing compiled java assets
docker create -v /root/.m2 --name $MAVEN_CACHE $IMAGE /bin/true

# build API
docker run -it \
   --volumes-from $MAVEN_CACHE \
   --volumes-from $NED_BUILD \
   --volume `pwd`/../../:/src \
   --volume `pwd`:/scripts $IMAGE \
   bash /scripts/build_ned.bash

# build ETL
docker run -it \
   --volumes-from $MAVEN_CACHE \
   --volumes-from $NED_BUILD \
   --volume `pwd`/../../../named-entity.etl:/src \
   --volume `pwd`:/scripts $IMAGE \
   bash /scripts/build_etl.bash
