package org.plos.namedentity.service;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.entity.IndividualEntity;

/* -------------------------------------------------------------------------- */
/*   Named Entity Service High-Level Convenience API                          */
/* -------------------------------------------------------------------------- */

public interface NamedEntityServiceHighApi {

    public IndividualEntity createIndividual(IndividualComposite composite);
}
