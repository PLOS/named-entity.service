#!/usr/bin/python
__author__ = 'eaknowledge'

import MySQLdb
import Config as context

class dbchannel:

    def openDBChannel(self):
        try:
            db = MySQLdb.connect(host=context.getdbHost, user=context.getDBUser, passwd=context.getDBPasswd, db=context.getDBName, port=context.getDBPort)
            return db
        except MySQLdb.Error as e:
            raise
        else:
            db.close()

    def createDBChannel(self,ahost,auser,apasswd,adb,aport):
        try:
            db = MySQLdb.connect(host=ahost, user=auser, passwd=apasswd, db=adb, port=aport)
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
            #print(sql)

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

    def qrydDBResult(self,ahost,auser,apasswd,adb,aport,sql,rtype, ckey, vkey=None):
            db_channel = self.createDBChannel(ahost,auser,apasswd,adb,aport,)
            # prepare a cursor object using cursor() method
            cursor = db_channel.cursor(MySQLdb.cursors.DictCursor)

            # execute SQL query using execute() method.
            #print(sql)

            cursor.execute(sql)

            # Fetch a single row using fetchone() method.
            result_set = cursor.fetchall()

            # Because Python acts strange with multiple SQL executions we will
            # pass the return type (rtype) and try that



            # rtype can be "a"(array) or "d"(dict)
            #
            if ((ckey==None or ckey=='') and rtype!=None):
               return result_set
            elif rtype!=None and ckey!=None:
                if rtype =="a":
                    result_arry = []
                    for row in result_set:
                        print(row)
                        result_arry.append(str(row[ckey]))
                    return result_arry

                # rtype=a (dict)
                elif rtype =="d" and ckey!=None and vkey!=None:
                    result_dict= dict()
                    for row in result_set:
                        print(row)
                        result_dict.update({row[ckey]:row[vkey]})
                    return result_dict
                else:
                    raise
            else:raise

            cursor.close()
            db.close()


# Sample of how to get a generic
#sql='''Select * from individualProfiles LIMIT 10'''
'''
_dbchannel=dbchannel()

result=_dbchannel.qrydDBResult('172.17.0.8','ned','','namedEntities',3306,sql,"a", 'nedId', None)
print(result)
'''