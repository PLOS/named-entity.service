#!/usr/bin/python
"""
Data tests for NED etl

Usage:
  run_tests.py [options]

Options:
  --etl_jar=<path>    The name of the ETL jar file
                      [default: ../../../named-entity.etl/target/named-entity-etl-0.2.0-SNAPSHOT-jar-with-dependencies.jar]
"""

__author__ = 'jfinger'


from docopt import docopt
import traceback
import NedEtlDataTester
import os,sys,inspect

currentdir = os.path.dirname(os.path.abspath(inspect.getfile(inspect.currentframe())))
parentdir = os.path.dirname(currentdir)
sys.path.insert(0,parentdir) 

import docker_tester

# MAIN

args = docopt(__doc__, version=1)
print (args)

etl_jar = args['--etl_jar']

dt = docker_tester.DockerTester()

assert os.path.isfile(etl_jar), "jar not found: " + etl_jar

dt.containers_up()

ambra_mysql_ip = dt.get_container_ip("etltest_ambradb_1")
ned_service_ip = dt.get_container_ip("etltest_nedsvc_1")
ned_db_ip = dt.get_container_ip("etltest_neddb_1")
ned_base_url = "http://%s:8080" % ned_service_ip

print ("Checking AMBRA database container at %s" % ambra_mysql_ip)
dt.wait_for_process("mysql -h %s -u dummyuser --password=password -e exit" % ambra_mysql_ip)
print ("Database ready")

print ("Checking NED database container at %s" % ned_db_ip)
dt.wait_for_process("mysql -h %s -u ned namedEntities -e exit" % ned_db_ip)
print ("Database ready")

print ("Checking NED service container at %s" % ned_base_url)
dt.wait_for_web("%s/service/config" % ned_base_url)
print ("Service is up, running tests")

try:

  print ("Running external tests")
  t = NedEtlDataTester.NedEtlDataTester(ambra_mysql_ip, "nedetl_test_ambra", "dummyuser", "password", 3306, etl_jar, ned_base_url)
  t.run()

  print ("TESTS PASSED")
except Exception, e:
  print ("TEST FAILED")
  print (e)
  traceback.print_exc(file=sys.stdout)
finally:
  dt.containers_down()
