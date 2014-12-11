#!/usr/bin/python
__author__ = 'eaknowledge'

import urllib2
import json
import operator


'''

::: Web Service Client

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

        #print(reponse_headers)
        #print("status:"+str(response_status))
        #print("return JSON:")
        #print(myjson)
        #print('___________________________________________________________________________________________________________________\n')

        if response_status==200:
            if 'content-length' in reponse_headers:
                if int(reponse_headers['content-length'].__len__()) > 2:
                    assert operator.lt(2, int(reponse_headers['content-length'].__len__())) == True, "No Data Return"+str(reponse_headers.dict)

            elif 'transfer-encoding' in reponse_headers:
                print("Output has been chunked and did not return the content-length  ")

            return "true"
        else:
            return "false"