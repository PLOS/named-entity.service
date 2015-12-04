package org.plos.namedentity.service;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.entity.Entity;

public interface AmbraService {

  Long createUser(IndividualComposite composite);

  void deleteUser(int nedId);

  <S extends Entity> void update(S entity);

}
