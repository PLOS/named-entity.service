__author__ = 'eaknowledge'

import jsonUtil
import json
from random import randint

# Good job for the Json Util to pull the desired Json block
# First load the Json file
jutil=jsonUtil.ToolsInit('./resources/address_data.json')

# offset for array 0 index
offset=-1

# Pull the desired block from common_names Json
addressLists = jutil.getJSON('address')


# In Order to make this selection random we need the Json length
addressListsLen=addressLists.__len__()-1



# the following methods are only meant to be used ///
# Using a start index and list length we use rand to return
# a random index
def getRandomNameIndexNumber(startrange,endrange):

    if ((startrange!=None or  startrange< 0)and(endrange!=None or 0<=endrange)):
        a=randint(startrange,endrange)
        if(((a!=None) or( a!=''))and(a>=0 )):
            return a
        else:
            return 0
    else:
        raise
# get a name using the random generated list
def getList(startindex,listLen,NameList):
    randomIndex = getRandomNameIndexNumber(startindex,listLen)
    return NameList[randomIndex]

def getItem(itemName):
    itemList={}
    itemList=getList(0,addressListsLen,addressLists)
    a=itemList[itemName]
    return a
#
# DataChannel Class is exposed for general consumption
#
class AddressChannel():

    def getStreetNumber(self):
        a=getItem('streetNumber')
        return getRandomNameIndexNumber(1,a)

    def getStreet(self):
        return getItem('street')

    def getCity(self):
        return getItem('city')

    def getState(self):
        return getItem('state')

    def getZipCode(self):
        return getItem('zipCode')

    def getCountry(self):
        return getItem('country')

    def getPhoneNumber(self):
        return getItem('phoneNumber')



addressChannel=AddressChannel()
print(addressChannel.getStreetNumber())
print(addressChannel.getStreet())
print(addressChannel.getCity())
print(addressChannel.getState())
print(addressChannel.getZipCode())
print(addressChannel.getCountry())
print(addressChannel.getPhoneNumber())