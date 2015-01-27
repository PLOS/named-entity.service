__author__ = 'eaknowledge'


class DataModel():

    #Individuals
    def createObjectIndividualprofiles(ifirstname,imiddlename,ilastname,inickname,inameprefix,inamesuffix,idisplayname,ibiography,iisactive,inedId,isource):
        dictobj={}
        if ifirstname !=None and ifirstname!='':
            dictobj["firstname"]=ifirstname

        if imiddlename!=None and imiddlename!='':
            dictobj["middlename"]=imiddlename

        if ilastname!=None and ilastname!='':
            dictobj["lastname"] = ilastname

        if inickname!=None and inickname!='':
            dictobj["nickname"] = inickname

        if inameprefix!=None and inameprefix!='':
            dictobj["nameprefix"] = inameprefix

        if inamesuffix!=None and inamesuffix!='':
            dictobj["namesuffix"] = inamesuffix

        if idisplayname!=None and idisplayname!='':
            dictobj["displayname"] = idisplayname

        if ibiography!=None and ibiography!='':
            dictobj["biography"] = ibiography

        if iisactive!=None and iisactive!='':
            dictobj["isactive"] = iisactive

        if inedId!=None and inedId!='':
            dictobj["nedid"] = inedId

        if isource !=None and isource!='':
            dictobj["source"] = isource

        return dictobj


    #Address
    def createObjectAddress(itype, iaddressline1, iaddressline2, iaddressline3, icity, icountrycodetype, ipostalcode, iisactive, inedId, isource):
        dictobj={}


        dictobj["type"] = itype  #Home , Office ,
        if iaddressline1 !=None and iaddressline1!='':
            dictobj["addressline1"] = iaddressline1 #optional

        if iaddressline2 !=None and iaddressline2!='':
            dictobj["addressline2"] = iaddressline2 #optional

        if iaddressline3 !=None and iaddressline3!='':
            dictobj["addressline3"] = iaddressline3 #optional

        if icity !=None and icity !='':
            dictobj["city"] = icity #optional

        if icountrycodetype !=None and icountrycodetype!='':
            dictobj["countrycodetype"] = icountrycodetype #short description

        if ipostalcode !=None and ipostalcode!='':
            dictobj["postalcode"] = ipostalcode


        dictobj["isactive"] = iisactive #true or false

        if inedId !=None and inedId!='':
            dictobj["nedid"] = inedId

        if isource !=None and isource!='':
            dictobj["source"] = isource

        return dictobj

    #Email
    def createObjectEmail(itype,iemailaddress,iisactive,inedId,isource):
        dictobj={}


        dictobj["type"] = itype #Personal, Work


        if iemailaddress !=None and iemailaddress!='':
            dictobj["emailaddress"] = iemailaddress

        dictobj["isactive"]= iisactive #true or false

        if inedId !=None and inedId!='':
            dictobj["nedid"] = inedId

        if isource !=None and isource!='':
            dictobj["source"] = isource
        return dictobj


    def createObjectDegrees(itype,isource):
        dictobj={}
        dictobj["type"]=itype
        if isource !=None and isource!='':
            dictobj["source"]= isource
        return dictobj

    #Roles
    def createObjectRoles(itype,istartdate,ienddate,iapplicationtype,inedId,isource):
        dictobj={}


        dictobj["type"] = itype #Author, Co-Author, Academic Editor (PLOS ONE), NP System Administrator

        if istartdate !=None and istartdate!='':
            dictobj["startdate"]=  istartdate #join date EM

        if ienddate !=None and ienddate!='':
            dictobj["enddate"] = ienddate  # end date Saleforce



        dictobj["applicationtype"]=iapplicationtype #Editorial Manager, Ambra, Named Party DB

        if inedId !=None and inedId!='':
            dictobj["nedid"] = inedId

        if isource !=None and isource!='':
            dictobj["source"] = isource
        return dictobj


    #Uniqueidentifiers
    def createObjectUniqueidentifiers(itype,iuniqueidentifier,inedId,isource):
        dictobj={}
        dictobj["type"] = itype #Ringgold, ORCID , Editorial Manager, CAS, Salesforce, Ambra

        if iuniqueidentifier !=None and iuniqueidentifier!='':
            dictobj["uniqueidentifier"] = iuniqueidentifier

        if inedId !=None and inedId!='':
            dictobj["nedid"] = inedId

        if isource !=None and isource!='':
            dictobj["source"] = isource #Ambra or Editoral Manager

        return dictobj




createObjectIndividualprofiles(ifirstname,imiddlename,ilastname,inickname,inameprefix,inamesuffix,idisplayname,ibiography,iisactive,inedId,isource):