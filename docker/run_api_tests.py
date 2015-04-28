#!/usr/bin/python
__author__ = 'jfinger'

import os, sys
import docker_tester

# change to the script directory to run compose
os.chdir(os.path.dirname(__file__))

dt = docker_tester.DockerTester()

print ("Building service")
dt.cmd_stream("cd builder && docker-compose build && docker-compose up")

dt.containers_up()

# TODO: check for the presence of .war before starting

service_ip = dt.get_container_ip("docker_nedapi_1")
db_ip = dt.get_container_ip("docker_neddb_1")
base_url = "http://%s:8080" % service_ip

print ("Checking database container at %s" % db_ip)
if not dt.wait_for_process("mysql -h %s -u ned namedEntities -pned -e exit" % db_ip):
  sys.exit("Database was never ready")

print ("Database ready")

print ("Checking service container at %s" % base_url)
if not dt.wait_for_web("%s/service/config" % base_url):
  sys.exit("Service was never ready")

print ("Service is up, running tests")

try:

  try:

    # NOTE to run the sample do:
    # > PYTHONPATH=$PYTHONPATH:/path/to/your/etl_data_tester/dir/ ./run_api_tests.py
    #
    # If you do not specify a path to your tests, the ones in sampletest/ will run

    try:
      user_paths = os.environ['PYTHONPATH'].split(os.pathsep)
    except KeyError:
      sys.path.append("sampletest")

    import rest_tester

    print ("Running external REST tests")
    t = rest_tester.NedRestTester(base_url)
    t.run()
  except ImportError, e:
    print ("Skipping external REST tests since none were found.")

  print ("TESTS PASSED")
except Exception, e:
  print ("TEST FAILED")
  print (e)
finally:
  dt.containers_down()
