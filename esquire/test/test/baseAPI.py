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



def doURLCall(aURL,method,data):

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
    elif response_status ==200:
        if method == None:
            return myjson
    else:
        print('XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
        print("No Data Returned")
        print(reponse_headers.dict)
        print("\n\n")
        return ""



