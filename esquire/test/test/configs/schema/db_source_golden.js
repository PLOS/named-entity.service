{
    "dbsources": {
        "ambra": {
            "userProfile": [
                "userProfileID",
                "displayName",
                "givenNames",
                "surName",
                "email",
                "authId"
            ],
            "userOrcid": [
                "userProfileID",
                "orcid"
            ],
            "userProfileMetaData": [
                "userProfileID",
                "metaKey",
                "metaValue"
            ],
            "userProfileRoleJoinTable": [
                "userProfileID",
                "userRoleId"
            ],
            "userRole": [
                "userRoleID",
                "roleName"
            ]
        },
        "em": {
            "people": [
                "peopleid",
                "firstname",
                "middlename",
                "lastname",
                "ptitle",
                "inactive",
                "editorroleid",
                "url1",
                "url2",
                "url3",
                "row_lastmodified_timestamp",
                "guid"
            ],
            "address": [
                "peopleid",
                "phone",
                "email",
                "phone2",
                "ph2type",
                "institute",
                "address1",
                "address2",
                "address3",
                "address4",
                "country",
                "atype",
                "position",
                "city",
                "st",
                "zipcode",
                "inactive"
            ],
            "additional_people_detail_values": [
                "peopleid",
                "date_value",
                "apd_id",
                "date_value"
            ],
            "editorrole": [
                "editorroleid"
            ]
        }
    }
}
