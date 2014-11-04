#!/bin/bash

function do_mvn {
    echo "mvn -P ${MVN_PROFILE} ${MVN_TARGETS}"
    # tip: add -X to debug 
    mvn -P ${MVN_PROFILE} ${MVN_TARGETS}
}

MVN_DEFAULT_TARGET=${1:-clean}

MVN_PROFILE=mysql
MVN_TARGETS=$@

case "$1" in
clean) ;; 

codegen-h2)
    MVN_PROFILE=h2
    MVN_TARGETS="generate-sources jooq-codegen:generate test-compile"
    do_mvn
    ;;

codegen-mysql)
    MVN_TARGETS="generate-sources jooq-codegen:generate"
    do_mvn
    ;;

test)
    MVN_PROFILE=h2
    MVN_TARGETS="clean test"
    do_mvn
    ;;

package)
    MVN_TARGETS="clean package"
    do_mvn
    ;;

install)
    MVN_TARGETS="clean install"
    do_mvn
    ;;

tomcat)
    MVN_TARGETS="clean tomcat:run"
    do_mvn
    ;;

dbreset)
    mysql -u root < src/main/resources/ned-schema.mysql.sql
    mysql -u root < src/main/resources/ned-data.mysql.sql
    ;;
    
container-start)
		cd docker
		fig up -d
		echo MySQL DB = `docker inspect --format '{{ .NetworkSettings.IPAddress }}' docker_neddb_1`:3306
		echo Ned Service = http://`docker inspect --format '{{ .NetworkSettings.IPAddress }}' docker_nedsvc_1`:8080
		;;
		
container-stop)
		cd docker
		fig stop
		;;

container-test)
		MVN_TARGETS="clean install" do_mvn
		cd docker
		time ./run_tests.py
		;;
		

*)
    echo -e "\nUsage: `basename $0` (codegen-h2|codegen-mysql|dbreset|install|package|test|tomcat|container-start|container-stop|container-test)"
    echo -e "\n  tomcat url -> http://localhost:8080\n"
    exit 0
    ;;
esac

