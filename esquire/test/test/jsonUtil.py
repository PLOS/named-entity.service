#!/usr/bin/python
__author__ = 'eaknowledge'

import json

# Key: endpoint - is the root level of the API_EndPoint_JSON.json

class ToolsInit:
    def __init__(self, filePath):
        self.inputFile = filePath

    def getJSON(self, ep):
        with open(self.inputFile, 'r')as f:
            data = json.load(f)

            '''
            We need to define our object path to make this a mapped process
            '''
            return data[ep]

    def getEndPointKeysList(self, sdata):
            root = sdata
            return root.keys()


            '''
            organizations = data['endpoints']['organizations']
            individuals = data['endpoints']['individuals']
            typeclasses = data['endpoints']['typeclasses']
            '''

            print(root.keys())


    def showKeys(self, d):

        for key in d:
            print key


            #print(str(typeclasses['urlendpoint']))
            #return self.inputFile+"/"+str(typeclasses['urlendpoint'])


