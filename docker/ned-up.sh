#!/bin/bash

cd builder
./ned-build.sh

cd ..
#docker-compose rm --force -v &&
docker-compose build && docker-compose up
