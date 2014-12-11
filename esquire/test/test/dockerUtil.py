#!/usr/bin/python
__author__ = 'eaknowledge'

import jsonUtil
import urllib2
import json
from baseAPI import doURLCall

'''

Like procedural language function have to be parse before they are called by code

'''
ned_service_container = {}
ned_database_container = {}

ned_service_ip = None
ned_database_ip = None



docker_api_url = 'http://localhost:2323'
docker_api_endpoint = '/containers/json'
docker_api_query_params = '?all=1'
docker_api_list_all_containers_url=docker_api_url+docker_api_endpoint+docker_api_query_params

dockerContainer_statuses=['Up','Exited']

'''

these Methods

'''




'''
Docker API URL Methods
'''
def startDocker(dockerAPIURL, container1_Id):
    apiURL=dockerAPIURL
    apiURL+='/containers/'
    apiURL+=container1_Id
    apiURL+='/start'
    return apiURL


def inspectDocker(dockerAPIURL, cntrs):
    apiURL=dockerAPIURL
    apiURL+= "/containers/"
    apiURL+= cntrs
    apiURL+= '/json'
    return apiURL

'''
=======================================================================

We always - only - expect two containers of the latest returns

=======================================================================

'''

def build_app_context(dock_data, dockerAPIURL):
    docker_Obj={}
    print(dock_data)


    container1_Id = str(json.dumps(dock_data[0]['Id'])).replace('"/','').replace('"','')
    container2_Id = str(json.dumps(dock_data[1]['Id'])).replace('"/','').replace('"','')

    container1_status = str(json.dumps(dock_data[0]['Status'])).replace('"/','').replace('"','')
    container2_status = str(json.dumps(dock_data[1]['Status'])).replace('"/','').replace('"','')


    container_Obj={}
    container_Obj.update({container1_Id:container1_status})
    container_Obj.update({container2_Id:container2_status})




    for key in container_Obj.keys():
        if "Up" in str(container_Obj[key]):
            dockInfo= doURLCall(str(docker_api_list_all_containers_url), None, None)
        elif "Exited" in str(container_Obj[key]):
            startContainer = startDocker(dockerAPIURL, key)
            status=doURLCall(str(startContainer), 1, '')
        else:
            print("better rebuild")

        inspectContainer = inspectDocker(dockerAPIURL, key)

        print('---------------------------')
        print(inspectContainer)
        docker_inspect_data=doURLCall(str(inspectContainer),None,None)
        print(docker_inspect_data)
        print('---------------------------')

        if 'docker_nedsvc' in str(docker_inspect_data['Name']):
            ned_service_container.update({'name':docker_inspect_data['Name'], 'ip': docker_inspect_data['NetworkSettings']['IPAddress']})
        elif 'docker_neddb' in str(docker_inspect_data['Name']):
            ned_database_container.update({'name':docker_inspect_data['Name'], 'ip': docker_inspect_data['NetworkSettings']['IPAddress']})
        else:
            raise


        docker_Obj.update({'service':ned_service_container, 'database':ned_database_container})

    return docker_Obj



#//////////////////////////////////////////////////////////////////////
# Execution happens here

dock_data = doURLCall(str(docker_api_list_all_containers_url), None, None)
containerInfo = build_app_context(dock_data, docker_api_url)
if 0 < containerInfo.__len__():
    print(containerInfo)
