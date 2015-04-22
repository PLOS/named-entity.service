#!/usr/bin/python

import requests
import json
import subprocess
import os
from time import sleep

class DockerTester:

  def __init__(self):
    self.wait_secs = 3

  def cmd_read(self, command):
    return subprocess.Popen(command, shell=True, stdout=subprocess.PIPE).stdout.read().strip()

  def cmd_return(self, command):
    proc = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=open(os.devnull, 'wb'))
    proc.communicate()
    return proc.returncode

  def cmd_stream(self, command):
    proc = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE)
    while proc.poll() is None:
      print (proc.stdout.readline().rstrip())

  def containers_up(self):
    # TODO: cd to this directory in order to handle docker-compose remote
    print ("Building containers... (be patient; this may take 5-10 minutes the first time)")
    self.cmd_stream("docker-compose build && docker-compose up -d")

  def containers_down(self):
    print ("Stopping containers")
    self.cmd_stream("docker-compose stop && docker-compose rm --force")

  def get_container_ip(self, name):
    return self.cmd_read("docker inspect --format '{{ .NetworkSettings.IPAddress }}' %s" % name)

  def wait_for_process(self, command):

    for i in range(0, 30):
      proc_return = self.cmd_return(command)

      if proc_return == 0:
        return True

      print ("not ready ... waiting %d seconds" % self.wait_secs)
      sleep (self.wait_secs)

    return False

  def wait_for_web(self, url):

    for i in range(0, 30):
      try:
        print ("Visiting %s" % url)
        r = requests.get(url)
      except requests.exceptions.ConnectionError, e:
        print (e)
        print ("site not ready ... waiting %d seconds" % self.wait_secs)
        sleep (self.wait_secs)
        continue

      print (r)
      
      if r.status_code != requests.codes.ok:
        continue
        
      print (json.dumps(r.text, sort_keys=True, indent=4))

      return True

    return False
