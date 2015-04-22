#!/bin/bash

BUILD_DIR=/root

# wait for DB to be ready for schema

MYSQL="mysql --default-character-set=utf8 -h ${MYSQL_HOSTNAME} -u root -p${MYSQL_ROOT_PASSWORD}"

${MYSQL} < ${BUILD_DIR}/ned-schema.mysql.sql
MYSQL_NOT_CONNECTING=$?
while [ $MYSQL_NOT_CONNECTING -ne 0 ] ; do
    sleep 1;
    ${MYSQL} < ${BUILD_DIR}/ned-schema.mysql.sql
    MYSQL_NOT_CONNECTING=$?
    echo -e "\nDatabase (${MYSQL_HOSTNAME}) not ready for schema ... waiting"
done;

# set up db

echo -e "\nCreating NED Schema"
${MYSQL} < ${BUILD_DIR}/ned-schema.mysql.sql

echo -e "\nSeeding NED Schema"
${MYSQL} < ${BUILD_DIR}/ned-data.mysql.sql

echo 'SELECT User FROM mysql.user' | ${MYSQL}

echo -e "\nCreating DB User (ned)"
echo "CREATE USER '${MYSQL_NED_USER}' IDENTIFIED BY '${MYSQL_NED_PASSWORD}'" | ${MYSQL}
echo "GRANT ALL PRIVILEGES ON *.* TO '${MYSQL_NED_USER}'@'%' WITH GRANT OPTION; FLUSH PRIVILEGES" | ${MYSQL}
echo "Finished creating user."

# set up tomcat

echo Deleting contents of webapps/
rm -rf ${CATALINA_HOME}/webapps/*

NED_SVC_WAR=ned.war

echo Checking that WAR exists

WARCOUNT=$(ls ${BUILD_DIR}/${NED_SVC_WAR} 2> /dev/null | wc -l)

if [ $WARCOUNT -ne 0 ] ; then
  echo Copying WAR to webapps
  cp `ls -t ${BUILD_DIR}/${NED_SVC_WAR} | head -1` ${CATALINA_HOME}/webapps/ROOT.war
else
  echo "WAR file not found in ${BUILD_DIR}. Exiting..."
  exit 1
fi

# wait for db to be ready for service

MYSQL_CMD="mysql -h ${MYSQL_HOSTNAME} -u ${MYSQL_NED_USER} -p${MYSQL_NED_PASSWORD} ${MYSQL_DATABASE}"

$MYSQL_CMD -e 'exit'
MYSQL_NOT_CONNECTING=$?
while [ $MYSQL_NOT_CONNECTING -ne 0 ] ; do
    sleep 1;
    $MYSQL_CMD -e 'exit'
    MYSQL_NOT_CONNECTING=$?
    echo -e "\nDatabase (${MYSQL_HOSTNAME}) not ready for API ... waiting"
done;

echo -e "\nDatabase (${MYSQL_HOSTNAME}) ready!"

echo `ip addr`

# bring up service

${CATALINA_HOME}/bin/catalina.sh run
