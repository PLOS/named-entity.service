# NED Client Libraries

These libraries are auto generated from NED's Swagger spec via swagger codegen.

## Setup the code generator

See https://github.com/swagger-api/swagger-codegen

## Generate/Update the client libraries

    ./generate_clients.sh

## Distribute

### Java

Make sure your settings have the ambraproject.org credentials in them. After the clients have been generated, you should be able to run:

    java_deploy/deploy.sh

Then you should be able to find them in
http://maven.ambraproject.org/maven2/release/org/plos/ned-client

### Ruby

TODO

### Python

TODO

## Run the samples

### Java

    cd java && mvn clean install && cd ..
    cd sample-java
    mvn install -DskipTests && mvn exec:java

### Ruby

    cd ruby
    gem build swagger_client.gemspec
    gem install swagger_client-1.0.0.gem
    cd ..
    ruby sample.rb

### Python

    pip install certifi six urllib3

    PYTHONPATH=python python sample.py
