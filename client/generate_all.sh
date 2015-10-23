#!/bin/bash

CODEGEN=~/applications/swagger-codegen

SWAGGER=http://localhost:8080/api-docs

GENERATE="java -jar $CODEGEN/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate"

$GENERATE -i $SWAGGER -l swagger -o ./swagger

$GENERATE -i $SWAGGER -l python -o ./python

$GENERATE -i $SWAGGER -l ruby -o ./ruby \
  && cd ruby && gem build swagger_client.gemspec && cd ..

$GENERATE -i $SWAGGER -l java -o ./java \
  && cd java && mvn clean install && cd ..
