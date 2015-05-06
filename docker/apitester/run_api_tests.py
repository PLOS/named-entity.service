#!/usr/bin/python
__author__ = 'jfinger'

import os, sys
import docker_tester

# change to the script directory to run compose
os.chdir(os.path.dirname(__file__))

dt = docker_tester.DockerTester()

base_url = "http://nedapi:8080"

print ("Checking service container at %s" % base_url)
if not dt.wait_for_web("%s/service/config" % base_url):
  sys.exit("Service was never ready")

print ("Service is up, running tests")

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
  print ("===== Skipping external REST tests since none were found ====")

print ("TESTS PASSED")
