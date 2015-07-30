#!/bin/sh

args="$@"

if [ -x /usr/local/bin/boot2docker ]
then
  export DOCKER_HOST="tcp://$(boot2docker ip 2>/dev/null):2376" ;
  export DOCKER_CERT_PATH=${args[0]}
  export DOCKER_TLS_VERIFY=1
fi

docker ps | grep -q 'neddb'
if [ $? -eq 0 ]
then
  docker stop neddb
  docker rm neddb
fi
