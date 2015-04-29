#!/bin/bash

cd builder
docker-compose build && docker-compose up

cd ..

#docker-compose rm --force -v &&
docker-compose build && docker-compose up
