
NAMED ENTITY SERVICE 
===

TODO

Generating Eclipse Project Files
---
    mvn eclipse:clean eclipse:eclipse

Running Tests
---
    /* -------------------------------------------------------------------------- */
    /*  TESTS                                                                     */
    /* -------------------------------------------------------------------------- */

    * tests use an embedded jersey container (grizzly)

        make test 

    * running specific test classes

        mvn -f pom.h2.xml -Dtest=NamedEntityDBServiceImplTest test
        mvn -f pom.h2.xml -Dtest=NamedEntityResourceTest test

Debugging
---
    /* -------------------------------------------------------------------------- */
    /*  DEBUGGING                                                                 */
    /* -------------------------------------------------------------------------- */

    * here's how to run Maven Tomcat plugin in DEBUG mode (port:8000).

        export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
        mvn -f pom.mysql.xml tomcat:run

    * in separate window, attach with debug client

        mdb -attach 8000

Deployment
---
    /* -------------------------------------------------------------------------- */
    /*  DEPLOYMENT                                                                */
    /* -------------------------------------------------------------------------- */

    * deploy war to a standalone container (glassfish)

        make codegen-war 

        http://localhost:8080/api/ned/typeclasses/1

    * deploy and start embedded Tomcat instance

        make tomcat 
