# NED Client Libraries

These libraries are auto generated from NED's Swagger spec via swagger codegen.

## Setup the code generator

See https://github.com/swagger-api/swagger-codegen

## Generate/Update the client libraries

    ./generate_clients.sh

## Distribute

TODO
ambra maven repo?

## Run the samples

### Python

    pip install certifi six urllib3

    PYTHONPATH=python python sample.py

### Ruby

    cd ruby
    gem build swagger_client.gemspec
    gem install swagger_client-1.0.0.gem
    cd ..
    ruby sample.rb

### Java

    cd java && mvn clean install && cd ..
    cd sample-java
    mvn install -DskipTests && mvn exec:java
