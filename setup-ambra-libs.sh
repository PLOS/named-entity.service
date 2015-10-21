#!/bin/bash

AMBRA_ADMIN_DIR=$1

if [[ -z $1 ]]; then
  AMBRA_ADMIN_DIR="../ambra-admin"
fi

echo "Building ambra-admin.jar"

cp ./ambra-admin-pom.xml $AMBRA_ADMIN_DIR/
cd $AMBRA_ADMIN_DIR
mvn -f ./ambra-admin-pom.xml -DskipTests clean install
rm ./ambra-admin-pom.xml
