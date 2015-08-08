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

function process_db_args {
    db_host=${1:-localhost}
    db_port=${2:-3306}
    db_username=${3:-ned}
    db_password=${4}
    db_url="jdbc:mysql://${db_host}:${db_port}/namedEntities?useUnicode=true&amp;characterEncoding=utf8"
}

function clean_db {
    process_db_args

    echo -e "\nAbout to destroy namedEntities schema on ${db_host}:${db_port} user:${db_username} password:'${db_password}'"
    read -p "Are you sure? " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Yy]$ ]]
    then
        mvn -Ddb.url="$db_url" -Ddb.username="$db_username" -Ddb.password="$db_password" \
            properties:read-project-properties flyway:clean
    fi
}

function db_info {
    process_db_args
    mvn -Ddb.url="$db_url" -Ddb.username="$db_username" -Ddb.password="$db_password" \
        properties:read-project-properties flyway:info
}

function migrate_db {
    process_db_args
    mvn -P deploy -Ddb.url="$db_url" -Ddb.username="$db_username" -Ddb.password="$db_password" \
        compile flyway:migrate

    # here's how to migrate up to a specific target version. migrations with a
    # higher version number will not be applied (ex: apply v1 and v2).
    #mvn -P deploy -Ddb.url="$db_url" -Dflyway.target=2 compile flyway:migrate
}

function insert_app {
    if [[ -z $2 ]]; then
        echo -e "Error: App name not specified"
        exit 1
    fi

    if [[ -z $3 ]]; then
        echo -e "Error: App password not specified"
        exit 1
    fi

    app_username=$2
    app_password=$3
    db_host=${4:-localhost}
    db_port=${5:-3306}
    db_username=${6:-ned}
    db_password=${7}

    if [[ ! -f "target/test-classes/org/plos/namedentity/spring/security/BCrypt.class" ]]; then
        mvn test-compile
    fi

    HASHED=$(mvn -q exec:java -Dexec.mainClass=org.plos.namedentity.spring.security.BCrypt -Dexec.args="$app_password")

    echo "REPLACE INTO namedEntities.consumers (name, password) VALUES ('$app_username','$HASHED');" \
        | mysql -h $db_host -P $db_port --user=$db_username --password=$db_password
}

case "$1" in
codegen)
    mvn clean generate-sources jooq-codegen:generate
    ;;

db-clean)
    clean_db "$@" 
    ;;

db-info)
    db_info "$@" 
    ;;

db-migrate)
    migrate_db "$@"
    ;;

db-ringgold)
    check_ringgold_env
    import_ringgold
    ;;

insertapp)
    insert_app "$@"
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

*)
    echo -e "\nUsage: `basename $0` (codegen|db-clean|db-info|db-migrate|db-ringgold|insertapp|test|package|install|deploy|tomcat)\n"
    echo "  codegen  # runs jooq code generator against docker instance with latest migrations"
    echo ""
    echo "  db-clean   [<host> <port> <username> <password>] # cleans ned schema        (localhost:3306:ned:<empty>)"
    echo "  db-info    [<host> <port> <username> <password>] # shows applied migrations (localhost:3306:ned:<empty>)"
    echo ""
    echo "  db-migrate [<host> <port> <username> <password>] # applies migrations (localhost:3306:ned:<empty>)"
    echo "  db-migrate devbox01 3304                         # applies migrations (devbox01:3304:ned:<empty>)"
    echo "  db-migrate localhost 3306 ned ned                # applies migrations (localhost:3306:ned:ned)"
    echo ""
    echo "  db-ringgold                 # extracts and import ringgold archive"
    echo ""
    echo "  insertapp <app_username> <app_password> [<host> <port> <db_username> <db_password>]"
    echo "                              # inserts app user into database (localhost:3306:ned:<empty>)"
    echo ""
    echo "  test                        # runs unit tests"
    echo "  package                     # generates war and pojo's"
    echo "  install                     # copies war/pojo's to internal maven repo"
    echo ""
    echo "  deploy                      # deploys pojo's to external ambra maven repo"
    echo "                              # (http://maven.ambraproject.org/maven2/release/org/plos/)"
    echo ""
    echo "  tomcat                      # starts embedded tomcat (http://localhost:8080, mysql(3306))"
    echo
    exit 0
    ;;
esac
