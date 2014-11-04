#!/bin/bash

# TODO: check for file http://stackoverflow.com/questions/6363441/check-if-a-file-exists-with-wildcard-in-shell-script
echo Deleting contents of webapps/
rm -rf /var/lib/tomcat7/webapps/*
echo Copying WAR to webapps
cp /build/named-entity-service-*.war /var/lib/tomcat7/webapps/ROOT.war # quit if there is an error

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

