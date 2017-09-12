Named Entity Database
=====================

NED is a web service for hosting information about people and organizations. It provides a REST API backed my a MySQL database.

Dependencies
------------
    * Java 8
    * Docker (1.10+)
    * [Docker Maven Plugin](https://github.com/PLOS/docker-maven-plugin)
    * Ringgold institution database

You need to build and deploy the Docker Maven Plugin to your Maven repo before
building NED.
    
Database Setup
--------------

Make sure you have a schema to hold the tables. 

    CREATE SCHEMA namedEntities DEFAULT CHARACTER SET utf8 COLLATE utf8_bin

And a user. For example:
    
    CREATE USER 'ned' IDENTIFIED BY '';
    GRANT ALL PRIVILEGES ON *.* TO 'ned'@'%' WITH GRANT OPTION;
    FLUSH PRIVILEGES;

NED uses Flyway to manage database migrations. See ned.sh script for usage.

Make sure you have a ringgold database setup as well.

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
              
    <Resource name="jdbc/ringgold"
              auth="Container"
              type="javax.sql.DataSource"
              factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
              validationQuery="SELECT 1"
              testOnBorrow="true"
              driverClassName="com.mysql.jdbc.Driver"
              username="ned"
              password=""
              url="jdbc:mysql://localhost:3306/ringgold" />
              
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
    
to use the API and see the REST documentation visit the root of the service, for example:

[http://localhost:8080/v1/](http://localhost:8080/v1/)

Logging
-------

log4j is configured to write to both stdout and local syslog. You will have to enable syslog on your system to get those messages.

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


Generating Eclipse Project Files
--------------------------------

    mvn eclipse:clean eclipse:eclipse
    
   
Troubleshooting
--------------------------------

if you are on mac, you might be using boot2docker

if you see this error: 
[ERROR] Failed to execute goal org.flywaydb:flyway-maven-plugin:3.2.1:migrate (default) on project named-entity-service: org.flywaydb.core.api.FlywayException: Unable to obtain Jdbc connection 
from DataSource (jdbc:mysql://172.17.0.2:3306/namedEntities?useUnicode=true&characterEncoding=utf8) for user 'ned': Communications link failure
[ERROR] The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server. Operation timed out

you have to change your config file(/src/main/resources). you have to change ${docker.ip.host} and ${ringgolddb.ip.host} with your docker ip address. 
so for example, your entry will look like db.url=jdbc:mysql://192.168.59.104:3306/namedEntities?useUnicode=true&amp;characterEncoding=utf8

If above solution won't work and you again get same error but with docker ip, then you might have to open ports. you can run below script:
  #!/bin/bash
  for i in {10000..10999}; do
  VBoxManage modifyvm "boot2docker-vm" --natpf1 "tcp-port$i,tcp,,$i,,$i";
  VBoxManage modifyvm "boot2docker-vm" --natpf1 "udp-port$i,udp,,$i,,$i";
  done

