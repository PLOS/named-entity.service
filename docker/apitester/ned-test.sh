#!/bin/bash

cd ../builder
./ned-build.sh

echo Build finished

cd -
docker-compose build && docker-compose run nedapitester python /root/run_api_tests.py

# TODO: figure out how to expose logs to host/teamcity
