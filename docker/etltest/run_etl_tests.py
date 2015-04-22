#!/usr/bin/python
"""
Data tests for NED etl

Usage:
  run_tests.py [options]

Options:
  --etl_jar=<path>    The path to the the ETL jar file

"""

__author__ = 'jfinger'

import glob
import os, sys
from docopt import docopt
import traceback
import os,sys,inspect

# a little magic to import from the parent directory without a package

currentdir = os.path.dirname(os.path.abspath(inspect.getfile(inspect.currentframe())))
parentdir = os.path.dirname(currentdir)
sys.path.insert(0,parentdir) 

import docker_tester

# MAIN

args = docopt(__doc__, version=1)

etl_jar = args['--etl_jar']

# change to the script directory to run compose
os.chdir(os.path.dirname(__file__))

if not etl_jar:
  jars = glob.glob("../../../named-entity.etl/target/named-entity-etl-*-jar-with-dependencies.jar")
  if len(jars) == 0:
    raise Exception('No ETL jar found')
  etl_jar = jars[0]

assert os.path.isfile(etl_jar), "jar not found: " + etl_jar

print ("ETL jar: " + etl_jar)

dt = docker_tester.DockerTester()

# build the API
cwd = os.path.dirname(os.path.realpath(__file__))
os.chdir("..")
dt.cmd_return("bash compile.sh")
os.chdir(cwd)

dt.containers_up()

ambra_mysql_ip = dt.get_container_ip("etltest_ambradb_1")
ned_service_ip = dt.get_container_ip("etltest_nedapi_1")
ned_db_ip = dt.get_container_ip("etltest_neddb_1")
ned_base_url = "http://%s:8080" % ned_service_ip

print ("Checking AMBRA database container at %s" % ambra_mysql_ip)
dt.wait_for_process("mysql -h %s -u dummyuser --password=password -e exit" % ambra_mysql_ip)
print ("Database ready")

print ("Checking NED database container at %s" % ned_db_ip)
dt.wait_for_process("mysql -h %s -u ned namedEntities -pned -e exit" % ned_db_ip)
print ("Database ready")

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
finally:
  dt.containers_down()
