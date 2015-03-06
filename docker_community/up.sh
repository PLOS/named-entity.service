#!/bin/bash

cp ../target/named-entity-service-*.war nedsvc
docker-compose rm --force -v && docker-compose build && docker-compose up
