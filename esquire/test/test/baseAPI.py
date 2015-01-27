#!/usr/bin/python
__author__ = 'eaknowledge'

import jsonUtil
import urllib2
import json


'''
Like procedural language function have to be parse before they are called by code

'''
'''

def doURLCall(aURL):

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


    if int(reponse_headers['content-length']) > 2:
        print('XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
        print(response_status)
        print(reponse_headers.dict)
        print(json.dumps(myjson, sort_keys=True, indent=4, separators=(',', ': ')))
        print("\n\n")
    else:
        print('XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
        print("No Data Returned")
        print(reponse_headers.dict)
        print("\n\n")
'''


def doURLCall(aURL, method, data):
    if method == None:
        req = urllib2.Request(aURL)
    elif method == 1:
        req = urllib2.Request(aURL, data)

    opener = urllib2.build_opener()
    opener.addheaders = [('Accept', 'application/json')]
    f = opener.open(req)

    # Let's get the Header info from our request
    reponse_headers = f.info()
    reponse_headers.dict

    # Let's get the Request Status from our Request
    response_status = f.getcode()
    if method == None:
        myjson = json.loads(f.read())

    if "content-length" in reponse_headers:
        if int(reponse_headers['content-length']) > 2:
            if method == None:
                return myjson
    elif response_status == 200:
        if method == None:
            return myjson
    else:
        print('XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
        print("No Data Returned")
        print(reponse_headers.dict)
        print("\n\n")
        return ""


# POST
#address#
def getcreateIndividualAddress(nedId):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint += '/addresses'
    return endpoint

def getupdateIndividualAddress(nedId, addressId):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint += '/addresses/'
    endpoint += addressId
    return endpoint  #emails#

def getcreateIndividualEmail(nedId):
    endpoint += '/individuals/'
    endpoint += nedId
    endpoint += '/emails'
    return endpoint

def getupdateIndividualEmail(nedId, emailId):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint += '/emails/'
    endpoint += emailId
    return endpoint  #individualprofile#

def getcreateIndividualprofiles(nedId):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint += '/individualprofiles'
    return endpoint

def getupdateIndividualprofiles(nedId, profilesId):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint += '/individualprofiles/'
    endpoint += profilesId
    return endpoint  #roles#

def getcreateIndividualRole(nedId):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint += '/roles'
    return endpoint

def getupdateIndividualRole(nedId, roleId):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint += '/roles/'
    endpoint += roleId
    return endpoint  #uids#

def getcreateIndividualUID(nedId):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint += '/uids'
    return endpoint

def getupdateIndividualUID(nedId, id):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint += '/uids/'
    endpoint += id
    return endpoint  #typeclasses#

def getcreateTypeclasses():
    endpoint = '/typeclasses'
    return endpoint


def getupdateTypeclasses(id):
    endpoint = '/typeclasses/'
    endpoint += id
    return endpoint


def getcreateGlobalType(typeclassesid):
    endpoint = '/typeclasses/'
    endpoint += typeclassesid
    endpoint += '/typevalues'
    return endpoint


def getupdateGlobalType(typeclassesid, typevalueid):
    endpoint = '/tyepclasses/'
    endpoint += typeclassesid
    endpoint += '/typevalues/'
    endpoint += typevalueid
    return endpoint


#DELETE#
def getdeleteIndividualAddress(nedId, addressId):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint += '/addresses/'
    endpoint += addressId
    return endpoint


def getdeleteIndividualprofile(nedId, profilesId):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint += '/individualprofiles/'
    endpoint += profilesId
    return endpoint


def getdeleteIndividualRole(nedId, roleId):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint +='/roles/'
    endpoint += roleId
    return endpoint


def getdeleteIndividualUID(nedId, id):
    endpoint = '/individuals/'
    endpoint += nedId
    endpoint +='/uids/'
    endpoint += id
    return endpoint


def getdeleteTypeclasses(id):
    endpoint = '/typeclasses/'
    endpoint += id
    return endpoint


def getdeleteGlobaltype(typeclassesid, typevalueid):
    endpoint = '/tyepclasses/'
    endpoint += typeclassesid
    endpoint += '/typevalues/'
    endpoint += typevalueid
    return endpoint
