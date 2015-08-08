Docker Maven Plugin
===================

This Maven plugin attaches a shutdown hook to the jvm which stops and removes
the Docker instance used in NED's build process (neddb).

Build and deploy to Maven repo.

    mvn clean install

You can test the shutdown hook by running the docker mojo tester script. You'll
need to update the CLASSPATH - see script for details.

    ./docker-mojo-tester.sh

TODO: unfortunately, invoking class with mvn exec doesn't invoke shutdown hook
-- research this?!

mvn exec:java -Dexec.mainClass="org.plos.maven.DockerMojo"
