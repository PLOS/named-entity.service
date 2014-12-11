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

