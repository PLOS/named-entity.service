package org.plos.namedentity.api;

import java.util.List;

import org.plos.namedentity.api.dto.AddressDTO;
/*
        final String NEW_TYPE_JSON_PAYLOAD = "{"
            + "\"firstname\":\"Tom\","
            + "\"addresses\": ["
            + "{ \"city\":\"eugene\" },"
            + "{ \"city\":\"washington\" }"
            + "]"
            + "}";
*/
public class TestComposite {
     
    String firstname;

    List<AddressDTO> addresses;
    
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }
}
