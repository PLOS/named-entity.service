FROM ubuntu:14.04

MAINTAINER Public Library Of Science (PLOS)

# for percona
RUN apt-key adv --keyserver keys.gnupg.net --recv-keys 1C4CBDCDCD2EFD2A
RUN echo "deb http://repo.percona.com/apt trusty main" >> /etc/apt/sources.list
RUN echo "deb-src http://repo.percona.com/apt trusty main" >> /etc/apt/sources.list

# pin percona packages to avoid upgrades from distro repos
ADD ./00percona.pref /etc/apt/preferences.d/00percona.pref  

# install latest updates
RUN apt-get update
RUN apt-get upgrade -y

# install percona client and server
RUN apt-get -y install --force-yes percona-server-server-5.6 percona-server-client-5.6 curl

# enable remote access (default is localhost only, we change this
# otherwise our database would not be reachable from outside the container)
RUN sed -i -e"s/^bind-address\s*=\s*127.0.0.1/bind-address = 0.0.0.0/" /etc/mysql/my.cnf

# install start script
ADD ./start-database.sh /usr/local/bin/start-database.sh
RUN chmod +x /usr/local/bin/start-database.sh

EXPOSE 3306

CMD ["/usr/local/bin/start-database.sh"]
