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

	r = requests.get("%s/typeclasses" % base_url)
	print (r.text)
	assert(r.status_code == 200)
	
	r = requests.get("%s/typeclasses/2" % base_url)
	assert(r.status_code == 200)


print ("Building containers...")

print (cmd("fig build"))  # TODO: stream output

cmd("fig up -d")

service_host = "docker_nedsvc_1"

db_host = "docker_neddb_1"

service_ip = cmd("docker inspect --format '{{ .NetworkSettings.IPAddress }}' %s" % service_host)

db_ip = cmd("docker inspect --format '{{ .NetworkSettings.IPAddress }}' %s" % db_host)

print ("service_ip = [%s]" % service_ip)
sleep(15)

test_url = "http://%s:8080/service/config" % service_ip

# check that the db is up

test_sql = "mysql -h %s -u ned namedEntities -e exit" % db_ip

for i in range(0, 30):
	sql_proc_return = cmd_return(test_sql)

	if sql_proc_return == 0:
		break

	print ("Database not ready ... waiting")
	sleep(1)

print ("Database ready")


# check that the service is up

for i in range(0,30):
	try:
		print ("Visiting %s" % test_url)
		r = requests.get(test_url)
	except requests.exceptions.ConnectionError, e:
		print (e)
		sleep(1)
		continue

	print (r)
	
	if r.status_code != 200:
		continue
		
	break



print ("Service is up, running tests...")

try:
	run_tests("http://%s:8080" % service_ip)
	print ("TESTS PASSED")
except:
	print ("TEST FAILED")
finally:
	print ("Stopping containers")
	cmd("fig stop && fig rm --force")

