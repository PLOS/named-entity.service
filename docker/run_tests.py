#!/usr/bin/python
__author__ = 'jfinger'

import json
import os
import requests
import subprocess
from time import sleep

def cmd_read(command):
	return subprocess.Popen(command, shell=True, stdout=subprocess.PIPE).stdout.read().strip()

def cmd_return(command):
	proc = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=open(os.devnull, 'wb'))
	proc.communicate()
	return proc.returncode

def cmd_stream(command):
	proc = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE)
	while proc.poll() is None:
		print (proc.stdout.readline().rstrip())
	
def run_tests(base_url):

	# TODO: look for ENV variable to specify location of external python integration tests

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



print ("Building containers... (be patient; this may take 5-10 minutes the first time)")

cmd_stream("fig build && fig up -d")

wait_secs = 3

service_host = "docker_nedsvc_1"

db_host = "docker_neddb_1"

service_ip = cmd_read("docker inspect --format '{{ .NetworkSettings.IPAddress }}' %s" % service_host)

db_ip = cmd_read("docker inspect --format '{{ .NetworkSettings.IPAddress }}' %s" % db_host)

test_url = "http://%s:8080/service/config" % service_ip

test_sql = "mysql -h %s -u ned namedEntities -e exit" % db_ip

print ("Checking database container at %s" % db_ip)

for i in range(0, 30):
	sql_proc_return = cmd_return(test_sql)

	if sql_proc_return == 0:
		break

	print ("Database not ready ... waiting %d seconds" % wait_secs)
	sleep (wait_secs)

print ("Database ready")

print ("Checking service container at %s" % service_ip)

for i in range(0, 30):
	try:
		print ("Visiting %s" % test_url)
		r = requests.get(test_url)
	except requests.exceptions.ConnectionError, e:
		print (e)
		sleep (wait_secs)
		continue

	print (r)
	
	if r.status_code != requests.codes.ok:
		continue
		
	print (json.dumps(r.text, sort_keys=True, indent=4))

	break

print ("Service is up, running tests")

try:
	run_tests("http://%s:8080" % service_ip)
	print ("TESTS PASSED")
except Exception, e:
	print ("TEST FAILED")
	print (e)
finally:
	print ("Stopping containers")
	cmd_stream("fig stop && fig rm --force")
