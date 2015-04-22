#!/bin/bash

./compile.sh

docker-compose rm --force -v && docker-compose build && docker-compose up
