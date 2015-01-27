#!/usr/bin/python
__author__ = 'eaknowledge'

import MySQLdb
import urllib2
import json
import operator
import dockerUtil
from unittest import TestCase, main
import baseAPI
import DAO



class verify_createGlobaltype(TestCase):
    '''
    ===================================================================================================================

    Test drivers live here
    These loops are the engine BABY!
    # Individual service endpoint
        getIndividuals(NedSrvURL, neds)
    ===================================================================================================================
    '''






    def test_createGlobaltype(self):
        #..................................................................
        # Here's where we build some context to our container applications

        sql= """select typeid from globalTypes;"""

        ckey="nedId"
        vkey='id'
        api=baseAPI



        dock_data = api.doURLCall(str(dockerUtil.docker_api_list_all_containers_url), None, None)
        containerInfo = dockerUtil.build_app_context(dock_data, dockerUtil.docker_api_url)
        if 0 < containerInfo.__len__():
            NedSrvHTTP = 'http://'
            NedSrvIP = str(containerInfo['service']['ip'])
            NedSrvPort = '8080'
            NedSrvURL= NedSrvHTTP+NedSrvIP+':'+NedSrvPort
            NedDBHost=containerInfo['database']['ip']
        else:
            raise

        try:
            db = MySQLdb.connect(host=NedDBHost, user='ned', passwd='', db='namedEntities', port=3306)
            return db

        except MySQLdb.Error as e:
            raise
        else:
            db.close()

        result = db.getDBResult(sql, "a", ckey, None)
        print(result)

        #!/usr/bin/python
__author__ = 'eaknowledge'

import MySQLdb
import urllib2
import json
import operator
import dockerUtil
from unittest import TestCase, main
import baseAPI
import DAO



class verify_createGlobaltype(TestCase):
    '''
    ===================================================================================================================

    Test drivers live here
    These loops are the engine BABY!
    # Individual service endpoint
        getIndividuals(NedSrvURL, neds)
    ===================================================================================================================
    '''






    def test_createGlobaltype(self):
        #..................................................................
        # Here's where we build some context to our container applications

        sql= """select typeid from globalTypes;"""

        ckey="nedId"
        vkey='id'
        api=baseAPI



        dock_data = api.doURLCall(str(dockerUtil.docker_api_list_all_containers_url), None, None)
        containerInfo = dockerUtil.build_app_context(dock_data, dockerUtil.docker_api_url)
        if 0 < containerInfo.__len__():
            NedSrvHTTP = 'http://'
            NedSrvIP = str(containerInfo['service']['ip'])
            NedSrvPort = '8080'
            NedSrvURL= NedSrvHTTP+NedSrvIP+':'+NedSrvPort
            NedDBHost=containerInfo['database']['ip']
        else:
            raise

        try:
            db = MySQLdb.connect(host=NedDBHost, user='ned', passwd='', db='namedEntities', port=3306)
            return db

        except MySQLdb.Error as e:
            raise
        else:
            db.close()

        result = db.getDBResult(sql, "a", ckey, None)
        data='{"individualprofiles": [{"firstname":"QaMan","middlename":"","lastname":"OleTesty","nickname":"dogman","nameprefix":"Bae.","displayname":"allegory","biography":"This is a test of the ETL system. Had this been an actual test, you have been informed of any new developments. This is only a test","isactive":false,"source":"Ambra"}]}'

        url = baseAPI.getcreateGlobalType("123455")
        protocol='http://'
        print(url)
        ned_service_host=str(dockerUtil.containerInfo["service"]['ip'])
        ned_service_colon=':'
        ned_service_port=str(8080)
        endpoint=url

        endpoint_url=protocol+ned_service_host+ned_service_colon+ned_service_port+endpoint
        print(endpoint_url)


        data = json.dumps(data)

        req = urllib2.Request(endpoint_url, data, {'Content-Type': 'application/json'})
        f = urllib2.urlopen(req)

        response = f.read()
        print(response)

        f.close()