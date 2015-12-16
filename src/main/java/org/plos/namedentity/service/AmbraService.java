package org.plos.namedentity.service;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.entity.Entity;

public interface AmbraService {

  Long createUser(IndividualComposite composite);

  <S extends Entity> void update(S entity);

  <S extends Entity> void create(S entity);

  <S extends Entity> void delete(S entity);

}
