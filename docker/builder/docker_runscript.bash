#!/bin/bash

cd /src
./ned.sh package

echo target dir:
ls target

cp target/*.?ar /build

echo /build dir:
ls -l /build


# build with : docker-compose build && docker-compose up
# list assets with : docker run -i -t --volumes-from builder_nedbuilder_1 ubuntu ls /build
