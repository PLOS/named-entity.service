FROM tomcat:6-jre8

RUN apt-get update && apt-get install -y mysql-client

#ADD /build/* /root/

ADD docker_runscript.bash /root/docker_runscript.bash
CMD ["bash", "/root/docker_runscript.bash"]
