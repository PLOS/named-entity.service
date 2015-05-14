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
    mysql -u ned < src/main/resources/ned-schema.mysql.sql
    mysql -u ned < src/main/resources/ned-data.mysql.sql
    mysql -u ned < src/main/resources/ringgold-schema.mysql.sql
    ;;

db-ringgold-data)
    echo -e "\nImporting Ringgold data ... (this may take a few minutes)"
    gunzip src/main/resources/ringgold-data.mysql.sql.gz
    time mysql -u ned < src/main/resources/ringgold-data.mysql.sql
    gzip src/main/resources/ringgold-data.mysql.sql
    ;;

container-start)
    cd docker/builder
    ./ned-build.sh
    cd ..
    docker-compose build && docker-compose up -d

    echo MySQL DB = `docker inspect --format '{{ .NetworkSettings.IPAddress }}' docker_neddb_1`:3306

    echo "Bringing up NED service..."

    SERVICE_IP=`docker inspect --format '{{ .NetworkSettings.IPAddress }}' docker_nedapi_1`

    CURL_CMD="curl http://${SERVICE_IP}:8080/service/config"

    $CURL_CMD
    CURL_RETURN_CODE=$?
        if [ $CURL_RETURN_CODE -eq 6 ] ; then
            echo -e "\nERROR: unable to resolve NED Service host. Exiting."
            echo -e "Possible Reason: NED Service war on host hasn't been built.\n"
            exit 1
        fi

    while [ $CURL_RETURN_CODE -ne 0 ] ; do
      echo "Service not ready. Waiting..."
      sleep 3
      $CURL_CMD
      CURL_RETURN_CODE=$?
    done;

    echo NED Service = http://${SERVICE_IP}:8080

    echo Launching web browser  # Linux desktop only
    xdg-open "http://${SERVICE_IP}:8080"
    ;;
    
container-stop)
    cd docker
    docker-compose stop && docker-compose rm --force
    ;;

container-test)
    cd docker/apitester
    time ./ned-test.sh

    cd ../etltester
    time ./ned-test.sh
    ;;

*)
    echo -e "\nUsage: `basename $0` (codegen-h2|codegen-mysql|dbreset|db-ringgold-data|install|package|test|tomcat|container-start|container-stop|container-test)"
    echo -e "\n  tomcat url -> http://localhost:8080\n"
    exit 0
    ;;
esac
