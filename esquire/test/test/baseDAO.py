#!/usr/bin/python
__author__ = 'eaknowledge'

import MySQLdb
import pymssql
import jsonUtil

class DBChannel:

    dbType = None
    dbsrc = None
    name = None
    db_configs = None
    dbRootKey = None

    name = None
    host = None
    user = None
    password = None
    port = None

    '''
    We must at least get a
    '''
    def __init__(self, type, dbsource, dbname):
        self.dbType = type
        self.dbsrc = dbsource
        self.name = dbname
        self.db_configs = "configs/db/dbconfig.js"
        self.dbRootKey = "db"

    def getDBconfig(self):
        getQueryMap = jsonUtil.ToolsInit(self.db_configs)
        jroot = getQueryMap.getJSON(self.dbRootKey)
        rootkeys = getQueryMap.getEndPointKeysList(jroot[self.dbType])
        emsources = jroot[self.dbType]

        self.name = emsources[self.dbsrc]['name']
        self.host = emsources[self.dbsrc]['host']
        self.user = emsources[self.dbsrc]['user']
        self.password = emsources[self.dbsrc]['password']
        self.port = int(emsources[self.dbsrc]['port'])

    # Open database connection
    def openDBChannel(self):
        adb=None
        if self.dbType == 'mysql':
            # db = MySQLdb.connect(host='localhost', user='root', passwd='', db='information_schema', port=3307)
            print(self.host, self.user, self.password, self.name, self.port)

            adb = MySQLdb.connect(host=self.host, user=self.user, passwd=self.password, db=self.name, port=self.port)
        elif self.dbType == 'mssql':
            #db = pymssql.connect(host='198.115.92.150', user='speedy', password='gonzales', database='PONE', port=1433, as_dict=True)
            print(self.host, self.user, self.password, self.name, self.port)
            adb = pymssql.connect(host=self.host, user=self.user, password=self.password, database=self.name, port=self.port, as_dict=True)


        return adb