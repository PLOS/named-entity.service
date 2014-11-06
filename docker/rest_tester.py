#!/usr/bin/python

import requests
import json

# NOTE: This is a dummy class to be replaced with external tests created by QA

class NedRestTester:

  def __init__(self, base_url):
    self.base_url = base_url

  def run(self):
    r = requests.get("%s/service/config" % self.base_url)
    data = json.loads(r.text)
    assert(data['version'])
