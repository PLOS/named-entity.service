#!/bin/bash

BACKGROUND=""

if [ "$1" = "background" ]; then
	BACKGROUND="-d"
fi

docker-compose rm --force -v && docker-compose build && docker-compose up $BACKGROUND
