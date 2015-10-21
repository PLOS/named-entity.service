Named Entity Database
=====================

NED is a web service for hosting information about people and organizations. It provides a REST API backed my a MySQL database.

Dependencies
------------
    * Java 8
    * Docker (1.7+)
    * Docker Maven Plugin

You need to build and deploy the Docker Maven Plugin to your Maven repo before
building NED. See readme in config/docker-maven-plugin for details on how to do
this.

Ambra Integration
-----------------

NED is configured to TEMPORARIALLY also talk to the Ambra database for some of its requests. It should be noted that and Ambra database is assumed to be configured and running alongside NED.

You most configure your context file accordingly. See the sample in config/tomcat/

NED talks to the Ambra database via, a repackaged ambra-admin jar file that needs to be generated via the 'setup-ambra-libs.sh'. You need to run it once and it takes one argument which is the path to your local ambra-admin source directory.

Adding userapps
---------------

Applications that use NED must identify themselves. This is done with HTTP Basic
auth, and there are fields for the appname and password on the swagger
interface. To insert a userapp into the database, run this:

    ./ned.sh insertapp appname password
    
Running
-------

to start in embedded Tomcat instance

    ./ned.sh tomcat
    
if you are deploying to a system wide Tomcat instance you will need to add something like the following to your context.xml

    <Resource name="jdbc/ned"
              auth="Container"
              type="javax.sql.DataSource"
              factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
              validationQuery="SELECT 1"
              testOnBorrow="true"
              driverClassName="com.mysql.jdbc.Driver"
              username="ned"
              password=""
              url="jdbc:mysql://localhost:3306/namedEntities" />

to use the API and see the REST documentation visit the root of the service

    http://localhost:8080/

Running Tests
-------------

tests using an embedded jersey container (grizzly)

    ./ned.sh test

to run a specific test class

    mvn clean test -Dtest=NamedEntityServiceTest
    
to run a specific test method

    mvn clean test -Dtest='NamedEntityServiceTest#testCreateIndividualCompositeWithGroup'
    
Debugging
---------

to run Maven Tomcat plugin in DEBUG mode (port:8000)

    export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
    ./ned.sh tomcat
    
in separate window, attach with debug client

    mdb -attach 8000
    
to debug a unit test class
    
    mvnDebug clean test -DforkMode=never -Dtest=NamedEntityServiceTest
    
to attach to a debug unit test with IntelliJ, create a remote test config with the following command line
    
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000

Database Migrations
-------------------

NED uses Flyway to manage database migrations. See ned script for usage.

Generating Eclipse Project Files
--------------------------------

    mvn eclipse:clean eclipse:eclipse
