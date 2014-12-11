#!/usr/bin/python
__author__ = 'eaknowledge'

import MySQLdb
import urllib2
import json
import operator
import dockerUtil






NedSrvHTTP = 'http://'
NedSrvIP = '172.17.0.9'
NedSrvPort = '8080'
NedSrvURL= NedSrvHTTP+NedSrvIP+':'+NedSrvPort


qryNedIdS = """select id, nedId, firstName from individualNames;"""
qryNedIdSCKey="nedId"

qryNedAddresses = """select id, nedId from addresses;"""
qryNedAddressesCKey = "nedId"
qryNedAddressesVKey = "id"

qryNedEmails = """select id, nedId from emails;"""
qryNedEmailsCKey = "nedId"
qryNedEmailsVKey = "id"

qryNedRoles = """select id, nedId from roles;"""
qryNedRolesCKey = "nedId"
qryNedRolesVKey = "id"

qryUIDTypes = """select uni.typeId as uidType, uni.uniqueIdentifier as uidValue from uniqueIdentifiers uni, individualNames idn WHERE idn.nedId=uni.nedId;"""
qryUIDTypesCKey = "uidValue"
qryUIDTypesVKey = "uidType"

qryOrganizations = """select org.nedId from organizations org, individualNames idn WHERE idn.nedId=org.nedId;"""
qryOrganizationsCKey = "nedId"

qryTypeClasses = """select id from typeDescriptions;"""
qryTypeClassesCKey = "id"

qryGlobalTypes = """select gt.typeid as typevaluesid from globalTypes gt, typeDescriptions td where td.id=gt.typeid;"""
qryGlobalTypesCKey = "typevaluesid"

def doURLCall(aURL):

    req = urllib2.Request(aURL)
    opener = urllib2.build_opener()
    opener.addheaders = [('Accept', 'application/json')]
    f = opener.open(req)

    # Let's get the Header info from our request
    reponse_headers = f.info()
    reponse_headers.dict

    # Let's get the Request Status from our Request
    response_status = f.getcode()

    myjson = json.loads(f.read())

    print("\n\n")
    print(reponse_headers)
    print("status:"+str(response_status))
    print(myjson)

    if response_status==200:
        if 'content-length' in reponse_headers:
            if int(reponse_headers['content-length'].__len__()) > 2:
                assert operator.lt(2, int(reponse_headers['content-length'].__len__())) == True, "No Data Return"+str(reponse_headers.dict)
        elif 'transfer-encoding' in reponse_headers:
            print("Output has been chunked and did not return the content-length  ")

def getDBResultArry(connection, sql, ckey):
    # Open database connection
    db = MySQLdb.connect(host='172.17.0.8', user='ned', passwd='', db='namedEntities', port=3306)

    # prepare a cursor object using cursor() method
    cursor = db.cursor(MySQLdb.cursors.DictCursor)

    # execute SQL query using execute() method.

    cursor.execute(sql)

    # Fetch a single row using fetchone() method.
    result_set = cursor.fetchall()
    arry_NedIDs = []


    for row in result_set:
        arry_NedIDs.append(str(row[ckey]))

    db.close()
    return arry_NedIDs


def getDBResultDict():
    # Open database connection
    db = MySQLdb.connect(host='172.17.0.8', user='ned', passwd='', db='namedEntities', port=3306)

    # prepare a cursor object using cursor() method
    cursor = db.cursor(MySQLdb.cursors.DictCursor)

    # execute SQL query using execute() method.

    SQLStatement="""select id, nedId from addresses;"""



    cursor.execute(SQLStatement)

    # Fetch a single row using fetchone() method.
    result_set = cursor.fetchall()
    dict_addressSets= dict()


    for row in result_set:
        dict_addressSets.update({row["nedId"]:row["id"]})

    db.close()
    return dict_addressSets



'''
XXXXX Individuals XXXXX
'''
# get /individuals/{nedId}
# Read individual by Ned ID
def getIndividuals(NedURI,nedId):
    querymap='/individuals/'
    querymap+=nedId
    NedURI+=querymap
    doURLCall(NedURI)


'''
:::ADDRESS GET METHODS
'''
# get /individuals/{nedId}/addresses
# List addresses
def getIndividualsAddressesById(NedURI,nedId):
    NedURI=str(NedURI)
    querymap='/individuals/'
    querymap+=str(nedId)
    querymap+='/addresses'
    NedURI+=str(querymap)
    print(NedURI)
    doURLCall(NedURI)


# get /individuals/{nedId}/addresses/{addressId}
# List addresses
def getIndividualsAddressesByIds(NedURI,nedId,addressId):
    NedURI=str(NedURI)
    querymap='/individuals/'
    querymap+=str(nedId)
    querymap+='/addresses/'
    querymap+=str(addressId)
    NedURI+=str(querymap)
    doURLCall(NedURI)


'''
:::Degrees
'''
# get /individuals/{nedId}/degrees
# List degrees
def getIndividualsDegreesById(NedURI,nedId):
    NedURI=str(NedURI)
    querymap='/individuals/'
    querymap+=str(nedId)
    querymap+='/degrees'
    NedURI+=str(querymap)
    doURLCall(NedURI)


'''
:::Emails
'''
# get /individuals/{nedId}/emails
# List emails
def getIndividualsEmailsById(NedURI,nedId):
    NedURI=str(NedURI)
    querymap='/individuals/'
    querymap+=str(nedId)
    querymap+='/emails'
    NedURI+=str(querymap)
    doURLCall(NedURI)


# get /individuals/{nedId}/emails/{emailId}
# List emails
def getIndividualsEmailsByIds(NedURI,nedId,emailId):
    NedURI=str(NedURI)
    querymap='/individuals/'
    querymap+=str(nedId)
    querymap+='/emails/'
    querymap+=str(emailId)
    NedURI+=str(querymap)
    doURLCall(NedURI)


'''
:::PhoneNumbers
'''
# get /individuals/{nedId}/phonenumbers
# List phonenumbers
def getIndividualsPhoneNumbersById(NedURI,nedId):
    NedURI=str(NedURI)
    querymap='/individuals/'
    querymap+=str(nedId)
    querymap+='/phonenumbers'
    NedURI+=str(querymap)
    doURLCall(NedURI)


'''
:::Roles
'''
# get /individuals/{nedId}/roles
# List roles
def getIndividualsRolesById(NedURI,nedId):
    NedURI=str(NedURI)
    querymap='/individuals/'
    querymap+=str(nedId)
    querymap+='/roles'
    NedURI+=str(querymap)
    doURLCall(NedURI)


# get /individuals/{nedId}/roles/{roleId}
# Read Role
def getIndividualsRolesByIds(NedURI,nedId,roleId):
    NedURI=str(NedURI)
    querymap='/individuals/'
    querymap+=str(nedId)
    querymap+='/roles/'
    querymap+=str(roleId)
    NedURI+=str(querymap)
    doURLCall(NedURI)



'''
:::UIDS
'''

# get /individuals/{nedId}/uids
# List UIDs
def getIndividualsUIDsById(NedURI,nedId):
    NedURI=str(NedURI)
    querymap='/individuals/'
    querymap+=str(nedId)
    querymap+='/uids'
    NedURI+=str(querymap)
    doURLCall(NedURI)


# get /individuals/{nedId}/emails/{emailId}
# List emails
def getUIdTypesByIds(NedURI,uidValue,uidTypeId):
    NedURI=str(NedURI)
    querymap='/individuals/'
    querymap+=str(uidTypeId)
    querymap+='/'
    querymap+=str(uidValue)
    NedURI+=str(querymap)
    doURLCall(NedURI)



'''
XXXXX Organizations XXXXX
'''

# get /organizations
# List organizations
def getOrganizations(NedURI):
    NedURI=str(NedURI)
    querymap='/organizations'
    NedURI+=str(querymap)
    doURLCall(NedURI)

'''
:::Addresses
'''
# get /organizations/{nedId}/addresses
# List addresses

def getOrgAddressesById(NedURI, nedId):
    NedURI=str(NedURI)
    querymap='/organizations/'
    querymap+=str(nedId)
    querymap+='/addresses'
    NedURI+=str(querymap)
    doURLCall(NedURI)

'''
:::Emails
'''
# get /organizations/{nedId}/emails
# List emails

def getOrgEmailsById(NedURI,nedId):
    NedURI=str(NedURI)
    querymap='/organizations/'
    querymap+=str(nedId)
    querymap+='/emails'
    NedURI+=str(querymap)
    doURLCall(NedURI)

'''
:::Phonenumbers
'''
# get /organizations/{nedId}/phonenumbers
# List phone numbers

def getOrgPhoneNumbersById(NedURI, nedId):
    NedURI=str(NedURI)
    querymap='/organizations/'
    querymap+=str(nedId)
    querymap+='/phonenumbers'
    NedURI+=str(querymap)
    doURLCall(NedURI)

'''
:::UIDS
'''
# get /organizations/{nedId}/uids
# List UIDs

def getOrgUidsById(NedURI, nedId):
    NedURI=str(NedURI)
    querymap='/organizations/'
    querymap+=str(nedId)
    querymap+='/uids'
    NedURI+=str(querymap)
    doURLCall(NedURI)


'''
XXXXX Service XXXXX
'''
# get /service/config
# Config info
def getServiceConfigInfo(NedURI):
    NedURI=str(NedURI)
    querymap='/service/config'
    NedURI+=str(querymap)
    doURLCall(NedURI)



'''
XXXXX typeClasses XXXXX
'''
# get /typeclasses
# List typeclasses
def getTypeClasses(NedURI):
    NedURI=str(NedURI)
    querymap='/typeclasses'
    NedURI+=str(querymap)
    doURLCall(NedURI)



# get /typeclasses/{id}
# Read typeclasses By Id
def getTypeClassesById(NedURI, id):
    NedURI=str(NedURI)
    querymap='/typeclasses/'
    querymap+=str(id)
    NedURI+=str(querymap)
    doURLCall(NedURI)


# get /typeclasses/{typeclassid}/typevalues
# List global types by Id
def getTypeClassesByTypeclassid(NedURI, typeclassid):
    NedURI=str(NedURI)
    querymap='/typeclasses/'
    querymap+=str(typeclassid)
    querymap+='/typevalues'
    NedURI+=str(querymap)
    print("XXXXXXXXXXXXXXXXX"+NedURI)
    doURLCall(NedURI)


# get /typeclasses/{typeclassid}/typevalues/{typevalueid}
# Read global type by Ids

def getTypeClassesByIds(NedURI,typeclassid,typevalueid):
    NedURI=str(NedURI)
    querymap='/typeclasses/'
    querymap+=str(typeclassid)
    querymap+='/typevalues/'
    querymap+=str(typevalueid)
    NedURI+=str(querymap)
    doURLCall(NedURI)

'''
Test drivers live here
These loops are the engine BABY!

'''

test_arry= []
test_arry=getNedIds()



for neds in test_arry:
    # Individual service endpoint
    getIndividuals(NedSrvURL, neds)
    getIndividualsAddressesById(NedSrvURL, neds)
    getIndividualsDegreesById(NedSrvURL, neds)
    getIndividualsEmailsById(NedSrvURL, neds)
    getIndividualsPhoneNumbersById(NedSrvURL, neds)
    getIndividualsRolesById(NedSrvURL, neds)
    getIndividualsUIDsById(NedSrvURL, neds)

'''
Organization Tests will fail because there is no data in the db
'''

getOrganizations(NedSrvURL)
#test_arry= []
#test_arry=getOrganizations()
#for neds in test_arry:
    # Organizations service endpoint
    # getOrgAddressesById(NedSrvURL, neds)
    # getOrgEmailsById(NedSrvURL, neds)
    # getOrgPhoneNumbersById(NedSrvURL, neds)
    # getOrgUidsById(NedSrvURL, neds)



'''
Typeclasses
'''
getTypeClasses(NedSrvURL)

typeDescription_Arry= []
typeDescription_Arry=getTypeclasses()

typevalueid_Arry= []
typevalueid_Arry=getGlobalTypes()

for ids in typeDescription_Arry:
    getTypeClassesById(NedSrvURL, ids)

for typeclassid in typevalueid_Arry:
        getTypeClassesByTypeclassid(NedSrvURL,typeclassid)
'''
for ids in typeDescription_Arry:
    getTypeClassesById(NedSrvURL, ids)
    for typedvalueid in typevalueid_Arry:
        getTypeClassesByIds(NedSrvURL,ids,typedvalueid)
'''



'''
Test that require NED ID + additional criterion
'''
address_obj=dict()
address_obj=getNedAddresses()
for neds in address_obj:
    getIndividualsAddressesByIds(NedSrvURL, neds, address_obj[neds])

emails_obj=dict()
emails_obj=getNedEmails()
for neds in emails_obj:
    getIndividualsEmailsByIds(NedSrvURL, neds, emails_obj[neds])

roles_obj=dict()
roles_obj=getNedRoles()
for neds in roles_obj:
    getIndividualsRolesByIds(NedSrvURL, neds, roles_obj[neds])

uidTypes_obj=dict()
uidTypes_obj=getUIDTypes()
for uidValue in uidTypes_obj:
    getUIdTypesByIds(NedSrvURL, uidValue, uidTypes_obj[uidValue])
