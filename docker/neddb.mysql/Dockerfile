FROM ubuntu:14.04

MAINTAINER Public Library Of Science (PLOS)

# install latest updates
RUN apt-get update
RUN apt-get upgrade -y

# install mysql client and server
RUN apt-get -y install mysql-client mysql-server curl

# enable remote access (default is localhost only, we change this
# otherwise our database would not be reachable from outside the container)
RUN sed -i -e"s/^bind-address\s*=\s*127.0.0.1/bind-address = 0.0.0.0/" /etc/mysql/my.cnf

ENV DB_USER ned

# install start script
ADD ./start-database.sh /usr/local/bin/start-database.sh
RUN chmod +x /usr/local/bin/start-database.sh

EXPOSE 3306

CMD ["/usr/local/bin/start-database.sh"]

