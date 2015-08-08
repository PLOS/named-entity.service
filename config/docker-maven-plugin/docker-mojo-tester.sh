#!/bin/sh

#CLASSPATH=<insert dependencies classpath from "mvn dependency:build-classpath" here>

CLASSPATH="/home/mbowen/.m2/repository/org/apache/maven/maven-plugin-api/3.3.3/maven-plugin-api-3.3.3.jar:/home/mbowen/.m2/repository/org/apache/maven/maven-model/3.3.3/maven-model-3.3.3.jar:/home/mbowen/.m2/repository/org/codehaus/plexus/plexus-utils/3.0.20/plexus-utils-3.0.20.jar:/home/mbowen/.m2/repository/org/apache/maven/maven-artifact/3.3.3/maven-artifact-3.3.3.jar:/home/mbowen/.m2/repository/org/eclipse/sisu/org.eclipse.sisu.plexus/0.3.0/org.eclipse.sisu.plexus-0.3.0.jar:/home/mbowen/.m2/repository/javax/enterprise/cdi-api/1.0/cdi-api-1.0.jar:/home/mbowen/.m2/repository/javax/annotation/jsr250-api/1.0/jsr250-api-1.0.jar:/home/mbowen/.m2/repository/javax/inject/javax.inject/1/javax.inject-1.jar:/home/mbowen/.m2/repository/org/eclipse/sisu/org.eclipse.sisu.inject/0.3.0/org.eclipse.sisu.inject-0.3.0.jar:/home/mbowen/.m2/repository/org/codehaus/plexus/plexus-component-annotations/1.5.5/plexus-component-annotations-1.5.5.jar:/home/mbowen/.m2/repository/org/codehaus/plexus/plexus-classworlds/2.5.2/plexus-classworlds-2.5.2.jar:/home/mbowen/.m2/repository/org/apache/maven/plugin-tools/maven-plugin-annotations/3.4/maven-plugin-annotations-3.4.jar"

CLASSPATH=./target/classes:${CLASSPATH}

java -cp "$CLASSPATH" org.plos.maven.DockerMojo
