__author__ = 'eaknowledge'

import nameGen
import dataGen
import emailGen
import data_composite




#Setup Individualprofile
namegen=nameGen.NameChannel()
datagen=dataGen.DataChannel()
emailgen=emailGen.DomainChannel()
datamodel=data_composite.DataModel();






fname=namegen.getfirstName()
lname=namegen.getlastName()
displayname=fname+lname

#(ifirstname,imiddlename,ilastname,inickname,inameprefix,inamesuffix,idisplayname,ibiography,iisactive,inedId,isource):
datamodel.createObjectIndividualprofiles(fname,'',lname,'','','',displayname,'Somedays are quiet, while other days long for silence',true,'Ambra')






print(datagen.getAddressType())
print(datagen.getEmailType())
print(datagen.getRoleAppType())
print(datagen.getRoleType())
print(datagen.getUniqueIdentifiersType())
print(emailgen.getDomainName())
print(emailgen.getEmail(fname,lname,emailgen.getDomainName()))



