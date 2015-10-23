package org.plos.namedentity.service;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Individualprofile;

public interface AmbraService {

  Long createUser(IndividualComposite composite);
  void update(Address address, int nedId);
  void update(Email email, int nedId);
  void update(Individualprofile profile, int nedId);
  void updatePassword(String plaintext, int nedId);

}
