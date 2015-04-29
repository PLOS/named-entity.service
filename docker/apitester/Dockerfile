FROM python:2.7

# RUN apt-get install -y  libmysqlclient-dev #python-dev

RUN apt-get update && apt-get install -y mysql-client  # TODO: replace with python

RUN pip install requests # MySQL-python

ADD * /root/

CMD ["python", "/root/run_api_tests.py"]
