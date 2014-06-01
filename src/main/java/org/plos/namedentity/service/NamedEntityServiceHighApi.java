package org.plos.namedentity.service;

import java.util.List;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.dto.AddressDTO;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.dto.IndividualDTO;
import org.plos.namedentity.api.dto.PhonenumberDTO;
import org.plos.namedentity.api.dto.RoleDTO;
import org.plos.namedentity.api.dto.UniqueidentifierDTO;

/* -------------------------------------------------------------------------- */
/*   Named Entity Service High-Level Convenience API                          */
/* -------------------------------------------------------------------------- */

public interface NamedEntityServiceHighApi {

    public IndividualDTO             createIndividual       (IndividualComposite composite);

    public IndividualDTO			 findIndividualByNedId  (Integer nedId);
    public List<IndividualDTO>       findIndividualsByUid   (Integer srcTypeId, String uid);
    public List<AddressDTO>			 findAddressesByNedId   (Integer nedId);
    public List<EmailDTO>			 findEmailsByNedId      (Integer nedId);
    public List<PhonenumberDTO>		 findPhoneNumbersByNedId(Integer nedId);
    public List<RoleDTO>			 findRolesByNedId       (Integer nedId);
    public List<UniqueidentifierDTO> findUniqueIdsByNedId   (Integer nedId);
}
