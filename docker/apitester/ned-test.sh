#!/bin/bash

cd ../builder
./ned-build.sh

echo Build finished

cd -
docker-compose build && docker-compose run nedapitester python /root/run_api_tests.py
