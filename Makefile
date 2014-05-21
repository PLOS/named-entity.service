
# mvn dependency:build-classpath 2>&1 | tee cp.mmddyy.txt
JOOQ_CLASSPATH=/home/mbowen/.m2/repository/org/jooq/jooq/3.3.2/jooq-3.3.2.jar:/home/mbowen/.m2/repository/org/jooq/jooq-codegen/3.3.2/jooq-codegen-3.3.2.jar:/home/mbowen/.m2/repository/org/jooq/jooq-meta/3.3.2/jooq-meta-3.3.2.jar
MYSQL_CLASSPATH=/home/mbowen/.m2/repository/mysql/mysql-connector-java/5.1.30/mysql-connector-java-5.1.30.jar

all: help 

clean:
	rm *.log; \
	rm -rf ./target

jooq-codegen:
	cp ./src/main/resources/jooq-config.mysql.xml ./target; \
	java -cp $(JOOQ_CLASSPATH):$(MYSQL_CLASSPATH):./target org.jooq.util.GenerationTool /jooq-config.mysql.xml

pkg:
	mvn -f pom.mysql.xml -Dmaven.test.skip=true clean package

test:
	mvn -f pom.h2.xml clean test

tomcat: pkg
	mvn -f pom.mysql.xml tomcat:run 

help:
	@echo  ""
	@echo  "  TARGETS:"
	@echo  "    help         : this page"
	@echo  "    test         : run tests (uses embedded H2 db)"
	@echo  "    jooq-codegen : run jOOQ Code Generator (MySQL)"
	@echo  "    pkg          : builds war file"
	@echo  "    tomcat       : run embedded Tomcat"

.PHONY: all clean help jooq-codegen pkg test tomcat
