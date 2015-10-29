#!/bin/bash

CODEGEN=~/applications/swagger-codegen

SERVICE=http://localhost:8080/v0

SWAGGER=$SERVICE/swagger.json

CONFIG=$SERVICE/service/config

VERSION=$(echo "import json, requests; print(requests.get('${CONFIG}').json()['version'].split(' ')[0])" | python)

GENERATE="java -jar $CODEGEN/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate"


# JSON SWAGGER SPEC

$GENERATE -i $SWAGGER -l swagger -o ./swagger

# STATIC HTML DOC

$GENERATE -i $SWAGGER -l html -o ./html

# PYTHON

echo '{
  "packageName": "ned_client",
  "packageVersion": "'${VERSION}'"
}' > ned_python.json

# $GENERATE -i $SWAGGER -l python -o ./python -c ned_python.json

# RUBY

echo '{
  "gemName": "ned_client",
  "moduleName": "NedClient",
  "gemVersion": "'${VERSION}'"
}' > ned_ruby.json

# $GENERATE -i $SWAGGER -l ruby -o ./ruby -c ned_ruby.json \
#   && cd ruby && gem build ned_client.gemspec && cd ..

# JAVA

echo '{
  "groupId": "org.plos",
  "artifactId": "NedClient",
  "artifactVersion": "'${VERSION}'"
}' > ned_java.json

#
# $GENERATE -i $SWAGGER -l java -o ./java -c ned_java.json \
#   && cd java && mvn clean install && cd ..
