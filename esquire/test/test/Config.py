__author__ = 'eaknowledge'



class Config:
    def __init__(self):
        self.host='127.0.0.1'
        self.user='ned'
        self.passwd=''
        self.db='namedEntities'
        self.port=3306


    def getdbHost(self):
        return self.host

    def getDBUser(self):
        return self.user

    def getDBPasswd(self):
        return self.passwd

    def getDBName(self):
        return self.db

    def getDBPort(self):
        return self.port