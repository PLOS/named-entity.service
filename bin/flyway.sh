#!/usr/bin/env bash

# set -x

# this is a workaround since there is no public deb for flyway

#FLYWAY_DOWNLOAD=https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/4.0.3/flyway-commandline-4.0.3.tar.gz

FLYWAY_DOWNLOAD=https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/3.2.1/flyway-commandline-3.2.1.tar.gz

# install flyway next to the migration script that is calling it
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
pwd

CMD=$DIR/flyway-*/flyway

# use flyway installed on system, or pull one down to use
if type flyway &>/dev/null 2>&1; then
	CMD=flyway
elif ls $DIR/flyway-* &>/dev/null 2>&1; then
	echo
else
	wget -O - $FLYWAY_DOWNLOAD -nv | tar xz -C $DIR
fi

$CMD -v > /dev/null|| (echo "Error: Flyway not found" && exit 2)

$CMD $@

# example use:  ./flyway.sh -url="jdbc:mysql://localhost/namedEntities" -user=name -password=password -locations=filesystem:./migrations migrate
