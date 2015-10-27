package org.plos.namedentity.service;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.entity.Entity;

public interface AmbraService {

  Long createUser(IndividualComposite composite);

  <S extends Entity> void update(S entity);

  // TODO: move this function into update
  void updatePassword(String plaintext, int nedId);

  public UserRegistrationService getUserRegistrationService() {
    return userRegistrationService;
  }

  public void setUserRegistrationService(UserRegistrationService userRegistrationService) {
    this.userRegistrationService = userRegistrationService;
  }

  public NamedEntityDBService getNamedEntityDBService() {
    return namedEntityDBService;
  }

  public void setNamedEntityDBService(NamedEntityDBService namedEntityDBService) {
    this.namedEntityDBService = namedEntityDBService;
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}
