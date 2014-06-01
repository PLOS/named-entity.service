package org.plos.namedentity.persist;

import java.util.List;

import org.plos.namedentity.api.dto.AddressDTO;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.dto.IndividualDTO;
import org.plos.namedentity.api.dto.PhonenumberDTO;
import org.plos.namedentity.api.dto.RoleDTO;
import org.plos.namedentity.api.dto.UniqueidentifierDTO;

public interface NamedEntityQueries {

    IndividualDTO		      findIndividualByNedId  (Integer nedId);
    List<IndividualDTO>       findIndividualsByUid   (Integer srcTypeId, String uid);
    List<AddressDTO>          findAddressesByNedId   (Integer nedId);
    List<EmailDTO>            findEmailsByNedId      (Integer nedId);
    List<PhonenumberDTO>      findPhoneNumbersByNedId(Integer nedId);
    List<RoleDTO>             findRolesByNedId       (Integer nedId);
    List<UniqueidentifierDTO> findUniqueIdsByNedId   (Integer nedId);
}
