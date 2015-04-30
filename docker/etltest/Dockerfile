FROM python:2.7

# setup python

RUN apt-get update && apt-get install -y mysql-client  # TODO: replace with python

RUN apt-get install -y  libmysqlclient-dev #python-dev

RUN pip install requests MySQL-python docopt

# setup java

RUN apt-get install -y openjdk-7-jre && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME /usr/lib/jvm/java-7-openjdk-amd64

ADD * /root/

# CMD ["python", "/root/run_api_tests.py"]
