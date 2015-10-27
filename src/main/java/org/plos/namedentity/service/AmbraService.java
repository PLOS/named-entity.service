package org.plos.namedentity.service;

import org.ambraproject.service.user.UserRegistrationService;
import org.ambraproject.service.user.UserService;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.persist.NamedEntityDBService;

public interface AmbraService {

  Long createUser(IndividualComposite composite);

  <S extends Entity> void update(S entity);

  // TODO: move this function into update
  void updatePassword(String plaintext, int nedId);
}
