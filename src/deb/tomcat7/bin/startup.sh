#!/bin/sh

export CATALINA_BASE="$( dirname "${BASH_SOURCE[0]}" )"/..
#export CATALINA_BASE="/opt/plos/ned"
/usr/share/tomcat7/bin/startup.sh
echo "Tomcat started"
