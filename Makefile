
# mvn dependency:build-classpath 2>&1 | tee cp.mmddyy.txt
JOOQ_MYSQL_CLASSPATH=./lib/jooq-3.3.2.jar:./lib/jooq-codegen-3.3.2.jar:./lib/jooq-meta-3.3.2.jar:./lib/mysql-connector-java-5.1.30.jar

all: help 

clean:
	rm *.log; \
	rm -rf ./target

jooq-codegen:
	cp ./src/main/resources/jooq-config.mysql.xml ./target; \
	java -cp $(JOOQ_MYSQL_CLASSPATH):./target org.jooq.util.GenerationTool /jooq-config.mysql.xml

codegen-war: jooq-codegen
	mvn -f pom.xml -Dmaven.test.skip=true clean package

war:
	mvn -f pom.xml -Dmaven.test.skip=true clean package

test:
	mvn -f pom.h2.xml clean test

tomcat: codegen-war 
	mvn -f pom.xml tomcat:run

help:
	@echo  ""
	@echo  "  TARGETS:"
	@echo  "    help         : this page"
	@echo  "    test         : run tests (uses embedded H2 db)"
	@echo  "    jooq-codegen : run jOOQ Code Generator (MySQL)"
	@echo  "    pkg          : builds war file"
	@echo  "    tomcat       : run embedded Tomcat"
	@echo  ""
	@echo  "  RECIPES:"
	@echo  "    Run unit tests                  : make test"
	@echo  ""
	@echo  "    Regenerate schema and build war : make codegen-war"
	@echo  "      (tip: use this to regenerate jooq files after running tests)"
	@echo  ""
	@echo  "    Build war only for deployment   : make war"
	@echo  ""
	@echo  "    Run embedded Tomcat             : make tomcat"
	@echo  "      (http://localhost:8080/typeclasses/1)"
	@echo  ""

.PHONY: all clean help jooq-codegen codegen-war test tomcat war
