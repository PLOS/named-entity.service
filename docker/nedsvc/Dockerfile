FROM ubuntu:14.04

MAINTAINER Public Library Of Science (PLOS)

RUN apt-get update && apt-get -y install software-properties-common
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get update && apt-get -y upgrade

# automatically accept oracle license
RUN echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections

# and install java 7 oracle jdk
RUN apt-get -y install oracle-java7-installer && apt-get clean
RUN update-alternatives --display java
RUN echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle" >> /etc/environment

# install mysql client (so we can verify when mysql server has started)
RUN apt-get -y install mysql-client

# install tomcat7
RUN apt-get -y install tomcat7

# change path for tomcat7
RUN echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle" >> /etc/default/tomcat7

ADD run.sh /root/run.sh
RUN chmod +x /root/run.sh

ADD context.xml /etc/tomcat7/context.xml

ENV MYSQL_HOSTNAME neddb

EXPOSE 8080

CMD ["/root/run.sh"]
