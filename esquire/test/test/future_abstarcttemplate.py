#!/usr/bin/python
__author__ = 'eaknowledge'

import MySQLdb
import urllib2
import json
import operator
import dockerUtil
from API import doURLCall




#..................................................................
# Here's where we build some context to our container applications

dock_data = dockerUtil.doURLCall(str(dockerUtil.docker_api_list_all_containers_url), None, None)
containerInfo = dockerUtil.build_app_context(dock_data, dockerUtil.docker_api_url)
if 0 < containerInfo.__len__():
    NedSrvHTTP = 'http://'
    NedSrvIP = str(containerInfo['service']['ip'])
    NedSrvPort = '8080'
    NedSrvURL= NedSrvHTTP+NedSrvIP+':'+NedSrvPort
    NedDBHost=containerInfo['database']['ip']
else:
    raise

'''

Create a Database Connections

'''

def openDBChannel():
    try:
        db = MySQLdb.connect(host=NedDBHost, user='ned', passwd='', db='namedEntities', port=3306)
        return db
    except MySQLdb.Error as e:
        raise
    else:
        db.close()



def getDBResult(sql, rtype, ckey, vkey=None):
    db_channel = openDBChannel()
    # prepare a cursor object using cursor() method
    cursor = db_channel.cursor(MySQLdb.cursors.DictCursor)

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

:::UIDS

===================================================================================================================
'''
# get /individuals/{nedId}/emails/{emailId}
# List emails
def getUIdTypesByIds( NedURI,uidValue,uidTypeId):
    NedURI=str(NedURI)
    querymap='/individuals/'
    querymap+=str(uidTypeId)
    querymap+='/'
    querymap+=str(uidValue)
    NedURI+=str(querymap)
    print(NedURI)
    doURLCall(NedURI)

'''
===================================================================================================================

The Ned Id is the primary

===================================================================================================================
'''
uidTypes_obj = {'qry':"""select uni.typeId as uidType, uni.uniqueIdentifier as uidValue from uniqueIdentifiers uni, individualNames idn WHERE idn.nedId=uni.nedId;""", 'ckey':'uidValue', 'vkey':'uidType', 'method':getUIdTypesByIds}

'''
===================================================================================================================

Test drivers live here
These loops are the engine BABY!
# Individual service endpoint
    getUIdTypesByIds

===================================================================================================================
'''

def test_dict_driver(sql, ckey, vkey, neduri, test_method):
    test_data_dict = getDBResult(sql,'d', ckey, vkey)
    print("test_data_dict")
    print(test_data_dict)
    for val in test_data_dict:
        test_method(neduri, val, test_data_dict[val]) #NedSrvURL, neds, address_obj[neds]

test_dict_driver(uidTypes_obj['qry'], uidTypes_obj['ckey'], uidTypes_obj['vkey'], NedSrvURL, uidTypes_obj['method'])


