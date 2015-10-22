# NED Client Libraries

These libraries are auto generated from NED's Swagger spec via swagger codegen.

This is a work in progress. Use at your own risk.

## Setup the code generator

See https://github.com/swagger-api/swagger-codegen

## Record the swagger spec

Its not required, but good to has a copy around for when the service is down.

    java -jar ~/applications/swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate \
      -i http://localhost:8080/api-docs \
      -l swagger \
      -o ./swagger


## Generate Python client

    java -jar ~/applications/swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate \
      -i http://localhost:8080/api-docs \
      -l python \
      -o ./python

### Run the sample

    pip install certifi six urllib3

    PYTHONPATH=python python sample.py

## Ruby

    java -jar ~/applications/swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate \
      -i http://localhost:8080/api-docs \
      -l ruby \
      -o ./ruby

### Ruby sample

    cd ruby
    gem build swagger_client.gemspec
    gem install swagger_client-1.0.0.gem
    cd ..
    ruby sample.rb

## Java

    java -jar ~/applications/swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate \
      -i http://localhost:8080/api-docs \
      -l java \
      -o ./java

### Java sample

    cd java && mvn clean install && cd ..
    cd sample-java
    mvn install -DskipTests && mvn exec:java