FROM tomcat:6-jre8

RUN apt-get update && apt-get install -y mysql-client

ADD context.xml ${CATALINA_HOME}/conf/context.xml
ADD named-entity-service-*.war /root/ned.war
ADD run.sh /root/run.sh

CMD ["bash", "/root/run.sh"]

