#!/usr/bin/python
__author__ = 'jfinger'

import json
import os
import requests
import subprocess
from time import sleep

def cmd(command):
	return subprocess.Popen(command, shell=True, stdout=subprocess.PIPE).stdout.read().strip()

def cmd_return(command):
	proc = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE)
	proc.communicate()
	return proc.returncode
	
def run_tests(base_url):

	r = requests.get("%s/service/config" % base_url)
	data = json.loads(r.text)
	assert(data['version'])

	r = requests.get("%s/typeclasses" % base_url)
	data = json.loads(r.text)
	assert(len(data) > 10)
	assert(r.status_code == 200)
	
	r = requests.get("%s/typeclasses/2" % base_url)
	assert(r.status_code == 200)

	r = requests.get("%s/boguspath" % base_url)
	assert(r.status_code == 404)


print ("Building containers...")

print (cmd("fig build"))  # TODO: stream output since it can take a while

cmd("fig up -d")

wait_secs = 3

service_host = "docker_nedsvc_1"

db_host = "docker_neddb_1"

service_ip = cmd("docker inspect --format '{{ .NetworkSettings.IPAddress }}' %s" % service_host)

db_ip = cmd("docker inspect --format '{{ .NetworkSettings.IPAddress }}' %s" % db_host)

#print ("service_ip = [%s]" % service_ip)

test_url = "http://%s:8080/service/config" % service_ip

# check that the db is up

test_sql = "mysql -h %s -u ned namedEntities -e exit" % db_ip

for i in range(0, 30):
	sql_proc_return = cmd_return(test_sql)

	if sql_proc_return == 0:
		break

	print ("Database not ready ... waiting")
	sleep(3)

print ("Database ready")


# check that the service is up

for i in range(0,30):
	try:
		print ("Visiting %s" % test_url)
		r = requests.get(test_url)
	except requests.exceptions.ConnectionError, e:
		print (e)
		sleep(3)
		continue

	print (r)
	
	if r.status_code != 200:
		continue
		
	json.dumps(r.text, sort_keys=True, indent=4)

	break


print ("Service is up, running tests...")

try:
	run_tests("http://%s:8080" % service_ip)
	print ("TESTS PASSED")
except Exception, e:
	print ("TEST FAILED")
	print (e)
finally:
	print ("Stopping containers")
	cmd("fig stop && fig rm --force")
