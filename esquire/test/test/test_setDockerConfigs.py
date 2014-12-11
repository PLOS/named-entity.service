#!/usr/bin/python
__author__ = 'eaknowledge'

import MySQLdb
import urllib2
import json
import operator
from unittest import TestCase, main
import dockerUtil


#..................................................................
# Here's where we build some context to our container applications


class verify_ContainerConfigInfoSetup(TestCase):


    def test_getContainerInfo(self):
        dock_data = dockerUtil.doURLCall(str(dockerUtil.docker_api_list_all_containers_url), None, None)
        containerInfoObj = dockerUtil.build_app_context(dock_data, dockerUtil.docker_api_url)

        if 0 < containerInfoObj.__len__():
            NedSrvHTTP = 'http://'
            NedSrvIP = str(containerInfoObj['service']['ip'])
            NedSrvPort = '8080'
            NedSrvURL= NedSrvHTTP+NedSrvIP+':'+NedSrvPort
            NedDBHost=containerInfoObj['database']['ip']
            self.assertTrue(containerInfoObj.items())

            self.assertTrue(NedSrvHTTP!= None)
            self.assertTrue(NedSrvIP!= None)
            self.assertTrue(NedSrvPort!= None)
            self.assertTrue(NedSrvURL!= None)
            self.assertTrue(NedDBHost!= None)
        else:
            raise


            if__name__=='__main__'