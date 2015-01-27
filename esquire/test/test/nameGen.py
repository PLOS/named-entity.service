__author__ = 'eaknowledge'

import jsonUtil
from random import randint

# Good job for the Json Util to pull the desired Json block
# First load the Json file
jutil=jsonUtil.ToolsInit('./resources/common_names.json')

# offset for array 0 index
offset=-1

# Pull the desired block from common_names Json
firstNameList = jutil.getJSON('first_names')
lastNameList = jutil.getJSON('last_names')

# In Order to make this selection random we need the Json length
firstNameListLen=firstNameList.__len__()+offset
lastNameListLen=lastNameList.__len__()+offset


# the following methods are only meant to be used local
# Using a start index and list length we use rand to return
# a random index
def getRandomNameIndexNumber(startrange,endrange):

    if ((startrange!=None or 0<=startrange)and(endrange!=None or 0<=endrange)):
        return randint(startrange,endrange)
    else:
        raise
# get a name using the random generated list
def getName(startindex,listLen,NameList):
    randomIndex = getRandomNameIndexNumber(startindex,listLen)
    return NameList[randomIndex]


#
# DataChannel Class is exposed for general consumption
#
class NameChannel():

    def getfirstName(self):
        return getName(0,firstNameListLen,firstNameList)

    def getlastName(self):
        return getName(0,lastNameListLen,lastNameList)

