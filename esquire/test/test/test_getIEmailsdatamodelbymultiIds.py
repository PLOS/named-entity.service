#!/usr/bin/python
__author__ = 'eaknowledge'

import MySQLdb
import urllib2
import json
import operator
import dockerUtil
import baseAPI
from unittest import TestCase, main



class verify_getEmailsbymultiIds(TestCase):

    '''
    ===================================================================================================================

    The Ned Id is the primary

    ===================================================================================================================
    '''

    def getDBResult(db, sql, rtype, ckey, vkey=None):


        # prepare a cursor object using cursor() method
        cursor = db.cursor(MySQLdb.cursors.DictCursor)

        # execute SQL query using execute() method.
        print(sql)

        cursor.execute(sql)

        # Fetch a single row using fetchone() method.
        result_set = cursor.fetchall()

        # Because Pythion acts stranged with multiple SQL executions we will
        # pass the return type (rtype) and try that
        # rtype=a (array)

        if rtype =="a":
            result_arry = []
            for row in result_set:
                result_arry.append(str(row[ckey]))
            return result_arry

        # rtype=a (dict)
        elif rtype =="d":
            result_dict= dict()
            for row in result_set:
                result_dict.update({row[ckey]:row[vkey]})
            return result_dict
        else:
            raise

        cursor.close()
        db.close()

    '''
    ===================================================================================================================

    Test drivers live here
    These loops are the engine BABY!
    # Individual service endpoint
        getIndividuals(NedSrvURL, neds)
    ===================================================================================================================
    '''

    def test_getEmailsbymultiIds(self):
        #..................................................................
        # Here's where we build some context to our container applications

        sql="""select id, nedId from emails;"""
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

        assert sql != None, "test_array_driver - variable not set: sql"
        assert ckey != None, "test_array_driver - variable not set: ckey"

        try:
            db = MySQLdb.connect(host=NedDBHost, user='ned', passwd='', db='namedEntities', port=3306)
            return db
        except MySQLdb.Error as e:
            raise
        else:
            db.close()

        data_model_as_dict = getDBResult(sql,'d', ckey, vkey)
        print("test_data_dict")
        print(data_model_as_dict)
        assert 0 < data_model_as_dict.__len__(), "database did not return a result data model obj"


