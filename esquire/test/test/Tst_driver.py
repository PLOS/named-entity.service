#!/usr/bin/python
__author__ = 'eaknowledge'

import DAO as dbchannel
import API as apichannel


class tst_driver():

    def test_array_driver(sql, ckey, neduri, test_method):


        print(sql)
        print(ckey)
        print(neduri)
        print(test_method)



        dbchannel.openDBChannel()
        test_data_arry = dbchannel.getDBResult(sql,'a', ckey)
        #print("test_data_arry")
        #print(test_data_arry)
        for val in test_data_arry:
            apiEndPoint=test_method(neduri, val)
            apichannel.doURLCall(apiEndPoint)

    def test_dict_driver(sql, ckey, vkey, neduri, test_method):
        dbchannel.openDBChannel()
        test_data_dict = dbchannel.getDBResult(sql,'d', ckey, vkey)
        #print("test_data_dict")
        #print(test_data_dict)
        for val in test_data_dict:
            apiEndPoint=test_method(neduri, val, test_data_dict[val]) #NedSrvURL, neds, address_obj[neds]
            apichannel.doURLCall(apiEndPoint)


'''
test_array_driver(qryNedIdS, qryNedIdSCKey, NedSrvURL, method_obj)
test_dict_driver(obj['qry'], obj['ckey'], obj['vkey'], NedSrvURL,obj['method'])
'''

