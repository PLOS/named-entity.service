#!/bin/bash

echo Deleting contents of webapps/
rm -rf /var/lib/tomcat7/webapps/*

BUILD_DIR=/build
NED_SVC_WAR=named-entity-service-*.war

echo Checking that WAR exists

WARCOUNT=$(ls ${BUILD_DIR}/${NED_SVC_WAR} 2> /dev/null | wc -l)

if [ $WARCOUNT -ne 0 ] ; then
  echo Copying WAR to webapps
  cp `ls -t ${BUILD_DIR}/${NED_SVC_WAR} | head -1` /var/lib/tomcat7/webapps/ROOT.war
else
  echo "WAR file not found in ${BUILD_DIR}. Exiting..."
  exit 1
fi

MYSQL_CMD="mysql -h ${MYSQL_HOSTNAME} -u ned namedEntities"

$MYSQL_CMD -e 'exit'
MYSQL_NOT_CONNECTING=$?
while [ $MYSQL_NOT_CONNECTING -ne 0 ] ; do
    sleep 1;
    $MYSQL_CMD -e 'exit'
    MYSQL_NOT_CONNECTING=$?
    echo -e "\nDatabase (${MYSQL_HOSTNAME}) not ready ... waiting"
done;

echo -e "\nDatabase (${MYSQL_HOSTNAME}) ready!"

/etc/init.d/tomcat7 start

# The container will run as long as the script is running, 
# that's why we need something long-lived here
exec tail -f /var/log/tomcat7/catalina.out
