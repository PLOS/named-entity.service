package org.plos.namedentity.persist;

import org.plos.namedentity.api.dto.IndividualDTO;

public interface NamedEntityQueries {

    IndividualDTO findIndividualById(Integer individualId);
}
