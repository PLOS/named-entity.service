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

$GENERATE -i $SWAGGER -l python -o ./python -c ned_python.json

# RUBY

echo '{
  "gemName": "ned_client",
  "moduleName": "NedClient",
  "gemVersion": "'${VERSION}'"
}' > ned_ruby.json

$GENERATE -i $SWAGGER -l ruby -o ./ruby -c ned_ruby.json \
  && cd ruby && gem build ned_client.gemspec && cd ..

# JAVA

echo '{
  "groupId": "org.plos",
  "artifactId": "ned-client",
  "apiPackage": "org.plos.ned_client.api",
  "modelPackage": "org.plos.ned_client.model",
  "invokerPackage": "org.plos.ned_client",
  "artifactVersion": "'${VERSION}'"
}' > ned_java.json

echo 'mvn deploy:deploy-file \
		-DpomFile="pom.xml" \
		-DgroupId="org.plos" \
		-Dversion="'${VERSION}'" \
		-DartifactId="ned-client" \
    -Dpackaging=jar \
    -Dfile="../java/target/ned-client-'${VERSION}'.jar" \
    -DrepositoryId=ambra \
    -Durl=sftp://maven.ambraproject.org/home/maven2/repository/release
' > java_deploy/deploy.sh

$GENERATE -i $SWAGGER -l java -o ./java -c ned_java.json \
  && cd java && mvn clean install && cd ..
