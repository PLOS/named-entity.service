#!/usr/bin/python
__author__ = 'jfinger'

import requests
import json
import docker_tester

dt = docker_tester.DockerTester()

dt.containers_up()

service_ip = dt.get_container_ip("docker_nedsvc_1")
db_ip = dt.get_container_ip("docker_neddb_1")
base_url = "http://%s:8080" % service_ip

print ("Checking database container at %s" % db_ip)
dt.wait_for_process("mysql -h %s -u ned namedEntities -e exit" % db_ip)
print ("Database ready")

print ("Checking service container at %s" % base_url)
dt.wait_for_web("%s/service/config" % base_url)
print ("Service is up, running tests")

try:
	
	r = requests.get("%s/service/config" % base_url)
	data = json.loads(r.text)
	assert(data['version'])

	r = requests.get("%s/typeclasses" % base_url)
	data = json.loads(r.text)
	assert(len(data) > 10)
	assert(r.status_code == requests.codes.ok)
	
	r = requests.get("%s/typeclasses/2" % base_url)
	assert(r.status_code == requests.codes.ok)

	r = requests.get("%s/boguspath" % base_url)
	assert(r.status_code == requests.codes.not_found)

	try:
		# TODO: find this class with an external path via PYTHONPATH
		# ie > PYTHONPATH=$PYTHONPATH:/path/to/integrationtest/dir ./run_tests.py
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

