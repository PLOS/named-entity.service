__author__ = 'eaknowledge'

import jsonUtil
import nameGen
import dataGen
import emailGen

from random import randint


# Good job for the Json Util to pull the desired Json block
# First load the Json file
jutil=jsonUtil.ToolsInit('./resources/composite_types.json')

# offset for array 0 index
offset=-1

# Pull the desired block from composite_types Json
addressTypeList = jutil.getJSON('addressType')
emailTypeList = jutil.getJSON('emailType')
roleTypeList = jutil.getJSON('roleType')
roleAppTypeList = jutil.getJSON('roleAppType')
uniqueIdentifiersTypeList = jutil.getJSON('uniqueIdentifiersType')


# In Order to make this selection random we need the Json length
addressTypeListLen=addressTypeList.__len__()+offset
emailTypeListLen=emailTypeList.__len__()+offset
roleTypeListLen=roleTypeList.__len__()+offset
roleAppTypeListLen=roleAppTypeList.__len__()+offset
uniqueIdentifiersTypeListLen=uniqueIdentifiersTypeList.__len__()+offset




#List of things I would like to randomly select

# the following methods are only meant to be used local
# Using a start index and list length we use rand to return
# a random index
def getRandomIndexNumber(startrange,endrange):

    if ((startrange!=None or 0<=startrange)and(endrange!=None or 0<=endrange)):
        return randint(startrange,endrange)
    else:
        raise
# get a name using the random generated list
def getName(startindex,listLen,NameList):
    rIndex = getRandomIndexNumber(startindex,listLen)
    return NameList[rIndex]


#
# DataChannel Class is exposed for general consumption
#
class DataChannel():

    def getAddressType(self):
        return getName(0,addressTypeListLen,addressTypeList)

    def getEmailType(self):
        return getName(0,emailTypeListLen,emailTypeList)

    def getRoleType(self):
            return getName(0,roleTypeListLen,roleTypeList)

    def getRoleAppType(self):
            return getName(0,roleAppTypeListLen,roleAppTypeList)

    def getUniqueIdentifiersType(self):
            return getName(0,uniqueIdentifiersTypeListLen,uniqueIdentifiersTypeList)