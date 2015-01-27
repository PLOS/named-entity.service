#!/usr/bin/python
__author__ = 'eaknowledge'

import MySQLdb
import urllib2
import json
import operator
import dockerUtil
from unittest import TestCase, main
import baseAPI
import api_channel



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
        apiChannel=api_channel

        endpoint=str(dockerUtil.docker_api_url)

        data='''{"individualprofiles" : [{"firstname" : "Everett","middlename" : "L","namesuffix" : "III","lastname" : "Qatest","nameprefix" : "Mr.","biography" : " I hickery dickery doc!","source": "Ambra","displayname": "jdoe"},{"firstname" : "John","lastname" : "Doe","source": "Editorial Manager"}],"phonenumbers" : [{"extension" : "x012","isprimary" : "true","phonenumber" : "650-123-4567","countrycodetype" : "1","source": "Editorial Manager"},{"isprimary" : "false","phonenumber" : "650-111-2222","type" : "Mobile","countrycodetype" : "1","source": "Editorial Manager"}],"addresses" : [{"statecodetype" : "CA","isprimary" : "true","city" : "San Francisco","addressline1" : "123 Maple Street","addresstype" : "Office","postalcode" : "94501","countrycodetype" : "United States of America","source": "Editorial Manager"}],"emails" : [{"isprimary" : "false","type" : "Personal","emailaddress" : "jane.q.doe.personal@foo.com","source": "Editorial Manager"},{"isprimary" : "true","type" : "Work","emailaddress" : "jane.q.doe.work@foo.com","source": "Editorial Manager"}],"degrees" : [{"type":"MD","source": "Editorial Manager"},{"type":"PhD","source": "Editorial Manager"}],"roles" : [{"source": "Editorial Manager","startdate": "2014-01-16","type": "Academic Editor (PLOS ONE)"}],"uniqueidentifiers" : [{"type" : "ORCID","uniqueidentifier" : "0000-0002-9430-319X","source": "Editorial Manager"},{"type" : "Editorial Manager","uniqueidentifier" : "PONE-579386","source": "Editorial Manager"},{"type" : "Ambra","uniqueidentifier" : "123","source": "Ambra"}]}'''

        dock_data = api.doURLCall(str(dockerUtil.docker_api_list_all_containers_url), None, None)
        containerInfo = dockerUtil.build_app_context(dock_data, dockerUtil.docker_api_url)

        protocol='http://'
        ned_service_host=str(containerInfo["service"]['ip'])
        ned_service_colon=':'
        ned_service_port=str(8080)
        endpoint='/individuals'

        endpoint_url=protocol+ned_service_host+ned_service_colon+ned_service_port+endpoint
        print(endpoint_url)


        #data = json.dumps(data)
        print(data)
        print(endpoint_url)

        response=apiChannel.postToURL(endpoint_url,data)
        print(response)
'''
        req = urllib2.Request(endpoint_url)
        req.add_header('Accept','application/json')
        req.add_header('Content-Type', 'application/json')
        f = urllib2.urlopen(req, data)
        response = f.read()
        f.close()
'''

