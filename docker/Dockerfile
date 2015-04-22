FROM tomcat:6-jre8

RUN apt-get update && apt-get install -y mysql-client

ADD build/* /root/
ADD run.sh /root/run.sh

CMD ["bash", "/root/run.sh"]
