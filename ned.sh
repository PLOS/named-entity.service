#!/bin/bash

MVN_DEFAULT_TARGET=${1:-clean}

MVN_PROFILE=mysql
MVN_TARGETS=$@

case "$1" in
clean) ;; 

codegen-h2)
    MVN_PROFILE=h2
    MVN_TARGETS="generate-sources jooq-codegen:generate test-compile"
    ;;

codegen-mysql)
    MVN_TARGETS="generate-sources jooq-codegen:generate"
    ;;

test)
    MVN_PROFILE=h2
    ;;

package)
    MVN_TARGETS="clean package"
    ;;

tomcat)
    MVN_TARGETS="clean tomcat:run"
    ;;

*)
    echo -e "\nUsage: `basename $0` (codegen-h2|codgen-mysql|package|test|tomcat)"
    echo -e "\n  tomcat url -> http://localhost:8080/typeclasses\n"
    exit 0
    ;;
esac

echo "mvn -P ${MVN_PROFILE} ${MVN_TARGETS}"
# tip: add -X to debug 
mvn -P ${MVN_PROFILE} ${MVN_TARGETS}
