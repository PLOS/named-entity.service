#!/usr/bin/python
__author__ = 'eaknowledge'

import MySQLdb
import urllib2
import json
import operator
import dockerUtil
from unittest import TestCase

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


class test_ned_api(TestCase):


    def doURLCall(self, aURL):

        req = urllib2.Request(aURL)
        opener = urllib2.build_opener()
        opener.addheaders = [('Accept', 'application/json')]
        f = opener.open(req)

        # Let's get the Header info from our request
        reponse_headers = f.info()
        reponse_headers.dict

        # Let's get the Request Status from our Request
        response_status = f.getcode()

        myjson = json.loads(f.read())

        print(reponse_headers)
        print("status:"+str(response_status))
        print("return JSON:")
        print(myjson)
        print('___________________________________________________________________________________________________________________\n')

        if response_status==200:
            if 'content-length' in reponse_headers:
                if int(reponse_headers['content-length'].__len__()) > 2:
                    assert operator.lt(2, int(reponse_headers['content-length'].__len__())) == True, "No Data Return"+str(reponse_headers.dict)
            elif 'transfer-encoding' in reponse_headers:
                print("Output has been chunked and did not return the content-length  ")



    def openDBChannel(self):
        try:
            db = MySQLdb.connect(host=NedDBHost, user='ned', passwd='', db='namedEntities', port=3306)
            return db
        except MySQLdb.Error as e:
            raise
        else:
            db.close()



    def getDBResult(self, sql, rtype, ckey, vkey=None):
        db_channel = self.openDBChannel()
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

    ::: Individuals

    ===================================================================================================================
    '''
    # get /individuals/{nedId}
    # Read individual by Ned ID
    def getIndividuals(self, NedURI,nedId):
        querymap='/individuals/'
        querymap+=nedId
        NedURI+=querymap
        print(NedURI)
        self.doURLCall(NedURI)


    '''
    ===================================================================================================================

    :::ADDRESS GET METHODS

    ===================================================================================================================
    '''
    # get /individuals/{nedId}/addresses
    # List addresses
    def getIndividualsAddressesById(self, NedURI,nedId):
        NedURI=str(NedURI)
        querymap='/individuals/'
        querymap+=str(nedId)
        querymap+='/addresses'
        NedURI+=str(querymap)
        print(NedURI)
        self.doURLCall(NedURI)

    # get /individuals/{nedId}/addresses/{addressId}
    # List addresses
    def getIndividualsAddressesByIds(self, NedURI,nedId,addressId):
        NedURI=str(NedURI)
        querymap='/individuals/'
        querymap+=str(nedId)
        querymap+='/addresses/'
        querymap+=str(addressId)
        NedURI+=str(querymap)
        print(NedURI)
        self.doURLCall(NedURI)

    '''
    ===================================================================================================================

    :::Degrees

    ===================================================================================================================
    '''
    # get /individuals/{nedId}/degrees
    # List degrees
    def getIndividualsDegreesById(self, NedURI,nedId):
        NedURI=str(NedURI)
        querymap='/individuals/'
        querymap+=str(nedId)
        querymap+='/degrees'
        NedURI+=str(querymap)
        print(NedURI)
        self.doURLCall(NedURI)


    '''
    ===================================================================================================================

    :::Emails

    ===================================================================================================================
    '''
    # get /individuals/{nedId}/emails
    # List emails
    def getIndividualsEmailsById(self, NedURI,nedId):
        NedURI=str(NedURI)
        querymap='/individuals/'
        querymap+=str(nedId)
        querymap+='/emails'
        NedURI+=str(querymap)
        print(NedURI)
        self.doURLCall(NedURI)

    # get /individuals/{nedId}/emails/{emailId}
    # List emails
    def getIndividualsEmailsByIds(self, NedURI,nedId,emailId):
        NedURI=str(NedURI)
        querymap='/individuals/'
        querymap+=str(nedId)
        querymap+='/emails/'
        querymap+=str(emailId)
        NedURI+=str(querymap)
        print(NedURI)
        self.doURLCall(NedURI)

    '''
    ===================================================================================================================

    :::PhoneNumbers

    ===================================================================================================================
    '''
    # get /individuals/{nedId}/phonenumbers
    # List phonenumbers
    def getIndividualsPhoneNumbersById(self, NedURI,nedId):
        NedURI=str(NedURI)
        querymap='/individuals/'
        querymap+=str(nedId)
        querymap+='/phonenumbers'
        NedURI+=str(querymap)
        print(NedURI)
        self.doURLCall(NedURI)


    '''
    ===================================================================================================================

    :::Roles

    ===================================================================================================================
    '''
    # get /individuals/{nedId}/roles
    # List roles
    def getIndividualsRolesById(self, NedURI,nedId):
        NedURI=str(NedURI)
        querymap='/individuals/'
        querymap+=str(nedId)
        querymap+='/roles'
        NedURI+=str(querymap)
        print(NedURI)
        self.doURLCall(NedURI)

    # get /individuals/{nedId}/roles/{roleId}
    # Read Role
    def getIndividualsRolesByIds(self, NedURI,nedId,roleId):
        NedURI=str(NedURI)
        querymap='/individuals/'
        querymap+=str(nedId)
        querymap+='/roles/'
        querymap+=str(roleId)
        NedURI+=str(querymap)
        print(NedURI)
        self.doURLCall(NedURI)

    '''
    ===================================================================================================================

    :::UIDS

    ===================================================================================================================
    '''

    # get /individuals/{nedId}/uids
    # List UIDs
    def getIndividualsUIDsById(self, NedURI,nedId):
        NedURI=str(NedURI)
        querymap='/individuals/'
        querymap+=str(nedId)
        querymap+='/uids'
        NedURI+=str(querymap)
        print(NedURI)
        self.doURLCall(NedURI)

    # get /individuals/{nedId}/emails/{emailId}
    # List emails
    def getUIdTypesByIds(self, NedURI,uidValue,uidTypeId):
        NedURI=str(NedURI)
        querymap='/individuals/'
        querymap+=str(uidTypeId)
        querymap+='/'
        querymap+=str(uidValue)
        NedURI+=str(querymap)
        print(NedURI)
        self.doURLCall(NedURI)







    '''
    ===================================================================================================================

    The Ned Id is the primary

    ===================================================================================================================
    '''
    qryNedIdS = """select id, nedId, firstName from individualNames;"""
    qryNedIdSCKey="nedId"




    addresses_obj = {'qry':"""select id, nedId from addresses;""", 'ckey':'nedId', 'vkey':'id', 'method':getIndividualsAddressesByIds}
    emails_obj = {'qry':"""select id, nedId from emails;""", 'ckey':'nedId', 'vkey':'id', 'method':getIndividualsEmailsByIds}
    nedRoles_obj = {'qry':"""select id, nedId from roles;""", 'ckey':"nedId", 'vkey':"id", 'method':getIndividualsRolesByIds}
    uidTypes_obj = {'qry':"""select uni.typeId as uidType, uni.uniqueIdentifier as uidValue from uniqueIdentifiers uni, individualNames idn WHERE idn.nedId=uni.nedId;""", 'ckey':'uidValue', 'vkey':'uidType', 'method':getUIdTypesByIds}




    organizations_obj = {'qry':"""select org.nedId from organizations org, individualNames idn WHERE idn.nedId=org.nedId;""", 'ckey': 'nedId'}
    typeClasses_obj ={'qry': """select id from typeDescriptions;""", 'ckey': "id"}
    globalTypes_obj = {'qry':"""select gt.typeid as typevaluesid from globalTypes gt, typeDescriptions td where td.id=gt.typeid;""", 'ckey':"typevaluesid"}







    '''
    ===================================================================================================================

    Test drivers live here
    These loops are the engine BABY!
    # Individual service endpoint
        getIndividuals(NedSrvURL, neds)
        getIndividualsAddressesById(NedSrvURL, neds)
        getIndividualsDegreesById(NedSrvURL, neds)
        getIndividualsEmailsById(NedSrvURL, neds)
        getIndividualsPhoneNumbersById(NedSrvURL, neds)
        getIndividualsRolesById(NedSrvURL, neds)
        getIndividualsUIDsById(NedSrvURL, neds)

    ===================================================================================================================
    '''

    def test_array_driver(self, sql, ckey, neduri, test_method):
        test_data_arry = self.getDBResult(sql,'a', ckey)
        print("test_data_arry")
        print(test_data_arry)
        for val in test_data_arry:
            test_method(neduri, val)

    def test_dict_driver(self, sql, ckey, vkey, neduri, test_method):
        test_data_dict = self.getDBResult(sql,'d', ckey, vkey)
        print("test_data_dict")
        print(test_data_dict)
        for val in test_data_dict:
            test_method(neduri, val, test_data_dict[val]) #NedSrvURL, neds, address_obj[neds]


    testCaseArry=[getIndividuals, getIndividualsAddressesById, getIndividualsDegreesById, getIndividualsEmailsById, getIndividualsPhoneNumbersById, getIndividualsRolesById, getIndividualsUIDsById]

    for method_obj in testCaseArry:
        print("\n\n")
        print('===================================================================================================================')
        print(method_obj)
        print('===================================================================================================================')
        print(method_obj)


        print(qryNedIdS)
        print(qryNedIdSCKey)
        print(NedSrvURL)
        print(method_obj)


        test_array_driver(qryNedIdS, qryNedIdSCKey, NedSrvURL, method_obj)


    #getIndividualsEmailsByIds
    testCaseArry=[addresses_obj, emails_obj, nedRoles_obj, uidTypes_obj]

    for obj in testCaseArry:
        test_dict_driver(obj['qry'], obj['ckey'], obj['vkey'], NedSrvURL,obj['method'])


