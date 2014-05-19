05/16/2014

// generate eclipse project files

    mvn eclipse:clean eclipse:eclipse


/* -------------------------------------------------------------------------- */
/*  TESTS                                                                     */
/* -------------------------------------------------------------------------- */

* tests use an embedded jersey container (grizzly)

    make test 

* running specific testing classes

    mvn -f pom.h2.xml -Dtest=NamedEntityDBServiceImplTest test
    mvn -f pom.h2.xml -Dtest=NamedEntityResourceTest test

/* -------------------------------------------------------------------------- */
/*  DEBUGGING                                                                 */
/* -------------------------------------------------------------------------- */

// here's how to run Maven Tomcat plugin in DEBUG mode (port:8000).

    export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
    mvn -f pom.mysql.xml tomcat:run

// in separate window, attach with debug client

    mdb -attach 8000


/* -------------------------------------------------------------------------- */
/*  DEPLOYMENT                                                                */
/* -------------------------------------------------------------------------- */

* war deployed to a standalone container (glassfish)

    make pkg

    GLASSFISH (root context = named-entity-service):
    http://localhost:8080/named-entity-service/api/namedentity/typeclasses/1

http://localhost:8080/api/namedentity/typeclasses/1


/* -------------------------------------------------------------------------- */
/*  TROUBLESHOOTING                                                           */
/* -------------------------------------------------------------------------- */

TRANSITIVE DEPENDENCY HELL
I was getting this exception when I tried to deploy to container (Jetty,
Glassfish, Grizzly, Tomcat) and also trying to run from cmd line
(ie, debug.sh or zzz.sh). 

    Caused by: java.lang.IncompatibleClassChangeError: class
        org.springframework.cglib.core.DebuggingClassWriter has interface
        org.springframework.asm.ClassVisitor as super class

Googling revealed incompatibility with Spring-asm. I believe this is the
ASM bytecode manipulation library (http://asm.ow2.org). Spring 3.2.x 
uses ASM 4.x; prior versions ASM 3.x. 

ASM 4.x is bundled in spring-core, however, some other library is pulling in
(referencing) the 3.x version. Unfortunately, "mvn dependency:tree" didn't
reveal who. Hmmm... Strangely, "mvn dependency:list" lists spring-asm:3.0.0
as a dependency.

What to do? identify jar and put in an exclusion, something like this.
BTW, this doesn't fix problem, and reference is by some other jar.

      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aop</artifactId>
        <version>${org.springframework.version}</version>
        <exclusions>
          <exclusion>
            <groupId>org.springframework</groupId>
            <artifactId>spring-asm</artifactId>
          </exclusion>
        </exclusions>
      </dependency>

A workaround is to manually delete this jar. So, when generate classpath
explicilty delete spring-asm-3.x.x jar from classpath.

    mvn dependency:build-classpath 2>&1 | tee cp.mmddyy.txt

When package war, unpack, delete spring-asm, and re-pack... 

    mvn clean package
    cd target
    jar xvf named-entity-service.war
    cd WEB-INF/lib
    rm spring-asm-3.0.0.RC3.jar
    cd ../..
    jar cvf named-entity-service.war index.html WEB-INF/
