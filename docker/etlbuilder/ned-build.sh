#!/bin/bash

IMAGE=maven:3.3-jdk-8
CACHE_CONTAINER=nedbuild_cache

if [ $1 == clean ]; then
   docker rm $NAME
fi

docker create -v /build -v /root/.m2 --name $CACHE_CONTAINER $IMAGE /bin/true

docker run -it --volumes-from $CACHE_CONTAINER \
   -v `pwd`/../../:/src \
   -v `pwd`:/scripts $IMAGE \
   bash /scripts/build_ned.bash

docker run -it --volumes-from $CACHE_CONTAINER \
   -v `pwd`/../../../named-entity.etl:/src \
   -v `pwd`:/scripts $IMAGE \
   bash /scripts/build_etl.bash
