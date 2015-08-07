#!/bin/sh

#CLASSPATH=<insert dependencies classpath from "mvn dependency:build-classpath" here>

CLASSPATH="/home/mbowen/.m2/repository/org/apache/maven/maven-plugin-api/3.0.5/maven-plugin-api-3.0.5.jar:/home/mbowen/.m2/repository/org/apache/maven/maven-model/3.0.5/maven-model-3.0.5.jar:/home/mbowen/.m2/repository/org/codehaus/plexus/plexus-utils/2.0.6/plexus-utils-2.0.6.jar:/home/mbowen/.m2/repository/org/apache/maven/maven-artifact/3.0.5/maven-artifact-3.0.5.jar:/home/mbowen/.m2/repository/org/sonatype/sisu/sisu-inject-plexus/2.3.0/sisu-inject-plexus-2.3.0.jar:/home/mbowen/.m2/repository/org/codehaus/plexus/plexus-component-annotations/1.5.5/plexus-component-annotations-1.5.5.jar:/home/mbowen/.m2/repository/org/codehaus/plexus/plexus-classworlds/2.4/plexus-classworlds-2.4.jar:/home/mbowen/.m2/repository/org/sonatype/sisu/sisu-inject-bean/2.3.0/sisu-inject-bean-2.3.0.jar:/home/mbowen/.m2/repository/org/sonatype/sisu/sisu-guice/3.1.0/sisu-guice-3.1.0-no_aop.jar:/home/mbowen/.m2/repository/org/sonatype/sisu/sisu-guava/0.9.9/sisu-guava-0.9.9.jar:/home/mbowen/.m2/repository/org/apache/maven/plugin-tools/maven-plugin-annotations/3.4/maven-plugin-annotations-3.4.jar"

CLASSPATH=./target/classes:${CLASSPATH}

java -cp "$CLASSPATH" org.plos.maven.DockerMojo
