#!/bin/bash

TESTDIR=`pwd`

cd ../builder
docker-compose build && docker-compose up

cd $TESTDIR
docker-compose build && docker-compose up

# TODO: figure out how to return error code or expose logs to host/teamcity
