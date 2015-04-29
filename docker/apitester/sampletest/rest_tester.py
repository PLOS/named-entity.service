#!/usr/bin/python

import requests
import json

# NOTE: This is a dummy class to be replaced with external tests created by QA

class NedRestTester:

  def __init__(self, base_url):
    self.base_url = base_url

  def run(self):

    print ("WARNING: Running sample API tests")

    r = requests.get("%s/service/config" % self.base_url)
    data = json.loads(r.text)
    assert data['version']

    r = requests.get("%s/typeclasses" % self.base_url)
    data = json.loads(r.text)
    assert len(data) > 10
    assert r.status_code == requests.codes.ok
    
    r = requests.get("%s/typeclasses/2" % self.base_url)
    assert r.status_code == requests.codes.ok

    r = requests.get("%s/boguspath" % self.base_url)
    assert r.status_code == requests.codes.not_found
