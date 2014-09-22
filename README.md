Named Entity Database
=====================

NED is a web service for hosting information about people and organizations. It provides a REST API backed my a MySQL database.

Database Setup
--------------

set up the database by running the following in MySql

    source src/main/resources/ned-schema.mysql.sql;
    source src/main/resources/ned-data.mysql.sql;
    
    create user 'ned'@'localhost' identified by '';
    grant all privileges on namedEntities.* to ned@'localhost';
    create user 'ned'@'%' identified by '';
    grant all privileges on namedEntities.* to ned@'%';
    flush privileges;
    
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

tests use an embedded jersey container (grizzly)

    ./ned.sh test

to run a specific test class

    mvn -P h2 clean test -Dtest=NamedEntityServiceTest
    
to run a specific test method

    mvn -P h2 clean test -Dtest='NamedEntityServiceTest#testCreateIndividualCompositeWithRole'
    
Debugging
---------

to run Maven Tomcat plugin in DEBUG mode (port:8000)

    export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
    ./ned.sh tomcat
    
in separate window, attach with debug client

    mdb -attach 8000
    
to debug a unit test class
    
    mvnDebug -P h2 clean test -DforkMode=never -Dtest=NamedEntityServiceTest
    
to attach to a debug unit test with IntelliJ, create a remote test config with the following command line
    
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000

Generating Eclipse Project Files
--------------------------------

    mvn eclipse:clean eclipse:eclipse
