#!/usr/bin/python
__author__ = 'eaknowledge'

import MySQLdb
import urllib2
import json
import operator
import dockerUtil
from unittest import TestCase, main
import baseAPI



class verify_postIndividuals(TestCase):

    '''
    ===================================================================================================================

    Test drivers live here
    These loops are the engine BABY!
    # Individual service endpoint
        getIndividuals(NedSrvURL, neds)
    ===================================================================================================================
    '''

    def test_postIndividuals(self):
        #..................................................................
        # Here's where we build some context to our container applications

        sql= """select id, nedId from addresses;"""
        ckey="nedId"
        vkey='id'
        api=baseAPI

        endpoint=str(dockerUtil.docker_api_url)

        data='{"individualprofiles": [{"firstname":"QaMan","middlename":"","lastname":"OleTesty","nickname":"dogman","nameprefix":"Bae.","displayname":"allegory","biography":"This is a test of the ETL system. Had this been an actual test, you have been informed of any new developments. This is only a test","isactive":false,"source":"Ambra"}]}'

        protocol='http://'
        ned_service_host=str(dockerUtil.containerInfo["service"]['ip'])
        ned_service_colon=':'
        ned_service_port=str(8080)
        endpoint='/individuals'

        endpoint_url=protocol+ned_service_host+ned_service_colon+ned_service_port+endpoint
        print(endpoint_url)



        data = json.dumps(data)

        req = urllib2.Request(endpoint_url, data, {'Content-Type': 'application/json'})
        f = urllib2.urlopen(req)

        response = f.read()
        print(response)

        f.close()



