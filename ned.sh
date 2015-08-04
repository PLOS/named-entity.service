#!/bin/bash

shopt -s nullglob

function check_ringgold_env {
    if [[ -z ${RINGGOLD_DB_DIR} ]]; then
        echo -e "\nUndefined RINGGOLD_DB_DIR (ex: export RINGGOLD_DB_DIR=~/work)\n"
        exit 1
    fi
    if [[ ! -d ${RINGGOLD_DB_DIR} ]]; then
        echo -e "\nRINGGOLD_DB_DIR : invalid directory (${RINGGOLD_DB_DIR})\n"
        exit 1
    fi
}

function import_ringgold {
    cd "${RINGGOLD_DB_DIR}"

    # delete sql file plus backup
    rm -f PLOS_Identify_*_utf8.sql*
    rm -f PLOS_Identify_*_counts.txt

    ringgold_zip=(PLOS_Identify_*.zip)

    if [ ${#ringgold_zip[@]} -ne 1 ]; then
        echo -e "\nUnexpected # of Ringgold zip's found (expected:1 found:${#ringgold_zip[@]}). Aborting.\n"
        exit 1
    fi

    unzip -o $ringgold_zip
    ringgold_sql=(PLOS_Identify_*_utf8.sql)

    # add ".bak" to -i option for compatibility between GNU/BSD sed flavors
    # rename ringgold schema: identify_test -> ringgold
    sed -i.bak 's/identify_test/ringgold/g' $ringgold_sql

    # remove "_new" from table names
    sed -i.bak 's/_new / /g' $ringgold_sql

    echo -e "\nImporting Ringgold ... (this may take a few minutes)"
    mysql -u ned < $ringgold_sql

    echo "Finished"
}

function deploy_jar {
    ned_jar=(./target/$1-*.jar)

    # get groupId and version from pom. 
    # repositoryId = server id in your mvn settings.xml
    mvn -P ${MVN_PROFILE} deploy:deploy-file -DpomFile=./pom.xml \
        -DartifactId=$1 \
        -Dpackaging=jar \
        -Dfile=${ned_jar} \
        -DrepositoryId=ambra \
        -Durl=sftp://maven.ambraproject.org/home/maven2/repository/release

    echo "Deployed ${ned_jar}"
}

function clean_db {
    db_host=${1:-localhost}
    db_port=${2:-3306}
    db_url="jdbc:mysql://${db_host}:${db_port}/namedEntities?useUnicode=true&amp;characterEncoding=utf8"
    mvn -Ddb.url="$db_url" properties:read-project-properties flyway:clean
}

function migrate_db {
    db_host=${1:-localhost}
    db_port=${2:-3306}
    mvn_profile=${3:-deploy}
    db_url="jdbc:mysql://${db_host}:${db_port}/namedEntities?useUnicode=true&amp;characterEncoding=utf8"
    #mvn -P $mvn_profile -Ddb.url="$db_url" -Dflyway.target=1 compile flyway:migrate
    mvn -P $mvn_profile -Ddb.url="$db_url" compile flyway:migrate
}

case "$1" in
codegen)
    mvn clean generate-sources jooq-codegen:generate
    ;;

test)
    mvn clean test
    ;;

package)
    mvn clean package
    ;;

install)
    mvn clean install
    ;;

deploy)
    mvn clean install
    deploy_jar "named-entity-pojos"
    deploy_jar "named-entity-password"
    ;;

tomcat)
    mvn clean tomcat:run
    ;;

insertapp)
    echo INSERT APP

    if [[ -z $2 ]]; then
        echo -e "Error: App name not specified"
        exit 1
    fi

    if [[ -z $3 ]]; then
        echo -e "Error: App password not specified"
        exit 1
    fi

    if [[ ! -f "target/test-classes/org/plos/namedentity/spring/security/BCrypt.class" ]]; then
        mvn test-compile
    fi

    HASHED=$(mvn -q exec:java -Dexec.mainClass=org.plos.namedentity.spring.security.BCrypt -Dexec.args="$3")

    echo "REPLACE INTO namedEntities.consumers (name, password) VALUES ('$2','$HASHED');"
    #echo "REPLACE INTO namedEntities.consumers (name, password) VALUES ('$2','$HASHED');" | mysql -u ned
    ;;

db-clean)
    clean_db $2 $3
    ;;

db-migrate)
    migrate_db $2 $3 $4
    ;;

db-ringgold)
    check_ringgold_env
    import_ringgold
    ;;

*)
    echo -e "\nUsage: `basename $0` (codegen|db-clean|db-migrate|db-ringgold|insertapp|test|package|install|deploy|tomcat)"
    echo -e "\n  tomcat url -> http://localhost:8080\n"
    exit 0
    ;;
esac

# cleanup. stop docker container if running.
./database/docker/db_stop.sh
