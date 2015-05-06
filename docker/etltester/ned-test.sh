#!/bin/bash

cd ../builder
./ned-build.sh

echo Build finished

cd -
docker-compose build && docker-compose run nedetltester python /root/run_etl_tests.py

echo Return code: $?
