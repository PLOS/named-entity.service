__author__ = 'eaknowledge'

import random



#List of things I would like to randomly select

# the following methods are only meant to be used local
# Using a start index and list length we use rand to return
# a random index
def getRandomIndexNumber(startrange,endrange):

    if ((startrange!=None or 0<=startrange)and(endrange!=None or 0<=endrange)):
        return random.randint(startrange,endrange)
    else:
        raise

# get a name using the random generated list
def getName(startindex,listLen,NameList):
    rIndex = getRandomIndexNumber(startindex,listLen)
    return NameList[rIndex]


class dataUtilChannel():


    def getAddressType(self):
        addressTypeList=["Home","Office"]
        count=addressTypeList.__len__()-1
        if count!=None and count!=0:
            randNum=getRandomIndexNumber(0,count)
            item=getName(0,count,addressTypeList)
            return item


    def getApplicationType(self):
        applicationTypeList=["Editorial Manager","Ambra","Named Party DB"]
        count=applicationTypeList.__len__()-1
        if count!=None and count!=0:
            randNum=getRandomIndexNumber(0,count)
            item=getName(0,count,applicationTypeList)
            return item


    def getEmailType(self):
        emailTypeList=["Personal","Work"]
        count=emailTypeList.__len__()-1
        if count!=None and count!=0:
            randNum=getRandomIndexNumber(0,count)
            item=getName(0,count,emailTypeList)
            return item


    def getRolesType(self):
        rolesTypeList=["Author","Co-Author","Academic Editor (PLOS ONE)","NP System Administrator"]
        count=rolesTypeList.__len__()-1
        if count!=None and count!=0:
            randNum=getRandomIndexNumber(0,count)
            item=getName(0,count,rolesTypeList)
            return item


    def getTrueFalse(self):
        trueFalseList=["true","false"]
        count=trueFalseList.__len__()-1
        if count!=None and count!=0:
            randNum=getRandomIndexNumber(0,count)
            item=getName(0,count,trueFalseList)
            return item


datautilchannel=dataUtilChannel()
print(datautilchannel.getTrueFalse())
print(datautilchannel.getAddressType())
print(datautilchannel.getApplicationType())
print(datautilchannel.getEmailType())
print(datautilchannel.getRolesType())
