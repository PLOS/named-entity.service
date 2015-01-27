__author__ = 'eaknowledge'

import jsonUtil
from random import randint

# Good job for the Json Util to pull the desired Json block
# First load the Json file
jutil=jsonUtil.ToolsInit('./resources/email_domains.json')

# offset for array 0 index
offset=-1

# Pull the desired block from common_names Json
domainNameList = jutil.getJSON('domains')


# In Order to make this selection random we need the Json length
domainNameListLen=domainNameList.__len__()+offset


# the following methods are only meant to be used local
# Using a start index and list length we use rand to return
# a random index
def getRandomNameIndexNumber(startrange,endrange):
    if ((startrange!=None or 0<=startrange)and(endrange!=None or 0<=endrange)):
        return randint(startrange,endrange)
    else:
        raise
# get a name using the random generated list
def getDomain(startindex,listLen,NameList):
    randomIndex = getRandomNameIndexNumber(startindex,listLen)
    return NameList[randomIndex]


#
# DataChannel Class is exposed for general consumption
#
class DomainChannel():

    def getDomainName(self):
        return getDomain(0,domainNameListLen,domainNameList)

    def getEmail(self,firstName,lastName,domain):
        first=[]
        first=firstName
        a=first[0].lower()  #insure the first letter is lowercase
        a+=lastName
        a+='@'
        a+='foo'
        a+='.'
        a+=domain
        return a


domainChannel=DomainChannel()
print(domainChannel.getDomainName())

