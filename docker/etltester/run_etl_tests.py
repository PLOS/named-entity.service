#!/usr/bin/python

__author__ = 'jfinger'

import os, sys, inspect
import traceback
import docker_tester

# MAIN

etl_jar = "/build/ned-etl.jar"

if not os.path.exists(etl_jar):
  print ("ETL jar not found: %s" % etl_jar)
  raise

dt = docker_tester.DockerTester()

ambra_mysql_ip = "ambradb"
ned_service_ip = "nedapi"
ned_db_ip = "neddb"
ned_base_url = "http://%s:8080" % ned_service_ip

print ("Checking AMBRA database container at %s" % ambra_mysql_ip)
dt.wait_for_process("mysql -h %s -u dummyuser --password=password -e exit" % ambra_mysql_ip)
print ("Database ready")

# print ("Checking NED database container at %s" % ned_db_ip)
# dt.wait_for_process("mysql -h %s -u ned namedEntities -pned -e exit" % ned_db_ip)
# print ("Database ready")

print ("Checking NED service container at %s" % ned_base_url)
dt.wait_for_web("%s/service/config" % ned_base_url)
print ("Service ready")

try:

  try:
    # NOTE to run the sample do:
    # > PYTHONPATH=/path/to/your/etl_data_tester/dir/ ./run_etl_tests.py
    #
    # If you do not specify a path to your tests, the ones in sampletest/ will run

    try:
      user_paths = os.environ['PYTHONPATH'].split(os.pathsep)
    except KeyError:
      sys.path.append("sampletest")

    import etl_data_tester
    print ("Running external tests found here: " + etl_data_tester.__file__)
    t = etl_data_tester.NedEtlDataTester(ambra_mysql_ip, "nedetl_test_ambra", "dummyuser", "password", 3306, etl_jar, ned_base_url)
    t.run()
  except ImportError, e:
    print ("Skipping external tests since none were found.")

  print ("TESTS PASSED")
except Exception, e:
  print ("TEST FAILED")
  print (e)
  traceback.print_exc(file=sys.stdout)
  raise

