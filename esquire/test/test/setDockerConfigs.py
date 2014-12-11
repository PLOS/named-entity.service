#!/usr/bin/python
__author__ = 'eaknowledge'

import MySQLdb
import urllib2
import json
import operator
import dockerUtil


#..................................................................
# Here's where we build some context to our container applications

dock_data = dockerUtil.doURLCall(str(dockerUtil.docker_api_list_all_containers_url), None, None)
containerInfo = dockerUtil.build_app_context(dock_data, dockerUtil.docker_api_url)


def getContainerInfo(containerInfoObj):
    if 0 < containerInfoObj.__len__():
        NedSrvHTTP = 'http://'
        NedSrvIP = str(containerInfoObj['service']['ip'])
        NedSrvPort = '8080'
        NedSrvURL= NedSrvHTTP+NedSrvIP+':'+NedSrvPort
        NedDBHost=containerInfoObj['database']['ip']
        return "true"
    else:
        raise