#!/bin/sh

#CLASSPATH="<insert dependencies classpath from 'mvn dependency:build-classpath' here>"

CLASSPATH=./target/classes:${CLASSPATH}

java -cp "$CLASSPATH" org.plos.maven.DockerMojo
